import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Server {

    // stores a list of all clients in the memory
    static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Server is up on port 1234");
        Socket s;
        int i = 0;
        while (true) {

            s = ss.accept();

            System.out.println("client " + i + " is connected to server!");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // main class to handle every new client
            ClientHandler handler = new ClientHandler(s, dis, dos, "client "+i);
            Thread t = new Thread(handler);

            clients.add(handler);
            t.start();
            i++;
        }
    }

}


class ClientHandler implements Runnable {

    // username and password
    private String username;
    private String password = "";

    // is true after signing up
    private boolean isSignedUp = false;

    // client which sent connection request to you
    public ClientHandler reqFrom = null;
    // client which you sent connection request to
    public ClientHandler reqTo = null;
    // client which you are connected to
    public ClientHandler recipient = null;

    Socket s;

    // this says if client is online or not
    boolean isLoggedIn;

    final DataInputStream dis;
    final DataOutputStream dos;

    ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, String username) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.isLoggedIn = true;
        this.username = username;
    }

    @Override
    public void run() {

        try {
            // asking for username and passport
            authentication();

            String received;

            while (true) {

                // read client input
                received = dis.readUTF();

                // answer to connection request
                if (this.reqFrom != null) {
                    this.reqFrom.receiveAccepted(received.equals("Y"));
                    this.reqFrom = null;
                }
                // client goes offline
                else if (received.equals("--logout")) {
                    System.out.println("User " + username + " logged out.");
                    if (this.recipient != null) {
                        this.recipient.recipient = null;
                        this.recipient.dos.writeUTF(this.username + " is disconnected");
                    }
                    this.recipient = null;
                    this.isLoggedIn = false;
                    this.s.close();
                    break;
                }
                // print the list of all clients on the server
                else if (received.equals(("--list")) || received.equals(("--list on")) || received.equals(("--list off"))) {
                    dos.writeUTF("****************************************************");
                    for (ClientHandler h : Server.clients) {
                        if (h.isSignedUp)
                            if (h.isLoggedIn) {
                                if (!received.equals(("--list off")))
                                    dos.writeUTF(h.username + "\t\t\t Online");
                            }
                            else {
                                if (!received.equals(("--list on")))
                                    dos.writeUTF(h.username + "\t\t\t Offline");
                            }
                    }
                    dos.writeUTF("****************************************************");
                }
                // connect two clients
                else if (received.equals("--connect")) {
                    sendConnectionRequest();
                }
                else {
                    // invalid command
                    if (this.recipient == null) {
                        this.dos.writeUTF("Invalid argument!");
                    }
                    // chat
                    else {
                        System.out.println("\"" + received + "\" said " + this.username + " to " + this.recipient.username);
                        this.recipient.dos.writeUTF(this.username + ": " + received);
                    }
                }
            }
        }
        // handling client disconnection
        catch (IOException e) {
            try {
                this.isLoggedIn = false;
                if (this.recipient != null) {
                    this.recipient.recipient = null;
                    this.recipient.dos.writeUTF(this.username + " is disconnected");
                }
                this.recipient = null;
                this.s.close();
                System.out.println("User " + username + " disconnected.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // close data streams
        finally {
            try {
                this.dis.close();
                this.dos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void authentication() throws IOException {
        // This method gets the username and password of client

        dos.writeUTF("please enter your username..");

        String received;
        while (true) {
            // get username
            received = dis.readUTF();
            String signInStatus = usernameVerification(received);

            if (signInStatus.equals("declined"))
                continue;
            else if (signInStatus.equals("signup")) {
                passwordSignIn("Up");
            }
            else {
                passwordSignIn("In");
                Server.clients.remove(getPreviousInstance(received));
            }

            username = received;
            dos.writeUTF("Your username is \"" + received + "\"");
            break;
        }

        isSignedUp = true;
        System.out.println("User " + username + " is logged in");

        // print the list of commands for client
        dos.writeUTF("Type \"--logout\" to logout!");
        dos.writeUTF("Type \"--list\" to list all users!");
        dos.writeUTF("Type \"--list on\" to list all online users!");
        dos.writeUTF("Type \"--list off\" to list all offline users!");
        dos.writeUTF("Type \"--connect\" to connect to an online user!");
    }

    private ClientHandler getPreviousInstance(String received) {
        for (ClientHandler h: Server.clients) {
            if (h.username.equals(received)) {
                return h;
            }
        }
        return null;
    }

    private String usernameVerification(String received) throws IOException{

        for (ClientHandler h: Server.clients) {
            if (h.username.equals(received)) {
                if (h.isLoggedIn && h.isSignedUp) {
                    // the user is online
                    dos.writeUTF("This user is currently online. Please enter another username..");
                    return "declined";
                }
                else if (!h.isLoggedIn && h.isSignedUp) {
                    // log in
                    dos.writeUTF("Logging in as " + h.username);
                    password = h.password;
                    return "login";
                }
                else {
                    // sign up
                    dos.writeUTF("Signing up..");
                    return "signup";
                }
            }
        }
        dos.writeUTF("Signing up..");
        return "signup";
    }

    private void passwordSignIn(String inOrUp) throws IOException {

        dos.writeUTF("please enter your password..");
        String received;
        while (true) {
            // get password
            received = dis.readUTF();

            if (inOrUp.equals("Up")) {
                // password strength check
                if (!passwordStrengthCheck(received)) {
                    dos.writeUTF("Password should contain at least 8 characters, containing lowercase, uppercase and numbers!");
                    dos.writeUTF("Please re enter your password..");
                    continue;
                }
            }

            // password hashing
            String temp = passwordHashing(received);
            if (temp != null) {
                if (inOrUp.equals("Up")) {
                    dos.writeUTF("You are now Signed up!");
                    password = temp;
                }
                else {
                    // verifying password for authentication
                    if (temp.equals(password)) {
                        dos.writeUTF("You are now logged in!");
                    }
                    else {
                        dos.writeUTF("Wrong password! Please try again!");
                        continue;
                    }
                }
                break;
            }
            else {
                dos.writeUTF("Something went wrong! please try another password!");
                password = "";
            }
        }
    }


    private boolean passwordStrengthCheck(String password) {
        if (password.length() < 8)
            return false;

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasNumber = false;
        char[] ch = password.toCharArray();
        for (char c: ch) {
            if (Character.isUpperCase(c))
                hasUpperCase = true;
            else if (Character.isLowerCase(c))
                hasLowerCase = true;
            else if (Character.isDigit(c))
                hasNumber = true;

            if (hasLowerCase && hasUpperCase && hasNumber)
                return true;
        }
        return false;
    }

    private String passwordHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private void sendConnectionRequest() throws IOException{
        // this method sends the connection request to other client

        String received;
        dos.writeUTF("Which user would you like to chat with?");

        outerLoop:
        while (true) {
            received = dis.readUTF();

            if (received.equals(this.username)) {
                dos.writeUTF("You can not chat with yourself! please enter another username!");
                continue;
            }

            // check which client has the specified username
            for (ClientHandler h: Server.clients) {
                if (h.username.equals(received) && h.isLoggedIn) {
                    dos.writeUTF("Sending request to " + h.username);

                    this.reqTo = h;
                    // current client request is sent to the specified client
                    h.reqFrom = this;
                    h.dos.writeUTF(username + " would like to chat with you. (Y/n)?");

                    break outerLoop;
                }
            }
            dos.writeUTF("Invalid username! please enter another username!");
        }
    }

    public void receiveAccepted(boolean accepted) throws IOException{
        // after the reply to connection request, this method is called for final arrangement

        if (accepted) {
            // if request is accepted

            // the previous connections should close for current client
            if (this.recipient != null) {
                System.out.println(username + " is disconnected from " + this.recipient.username);
                dos.writeUTF("You are now disconnected from " + this.recipient.username);
                this.recipient.dos.writeUTF("You are disconnected from " + this.username);
                this.recipient.recipient = null;
            }

            // the previous connections should close for target client
            if (this.reqTo.recipient != null) {
                System.out.println(this.reqTo.username + " is disconnected from " + this.reqTo.recipient.username);
                this.reqTo.dos.writeUTF("You are now disconnected from " + this.reqTo.recipient.username);
                this.reqTo.recipient.dos.writeUTF("You are disconnected from " + this.reqTo.username);
                this.reqTo.recipient.recipient = null;
            }

            // connection is established
            System.out.println(username + " is connected to " + reqTo.username);
            reqTo.dos.writeUTF("You are successfully connected to " + username);
            dos.writeUTF("Request accepted! You are successfully connected to " + reqTo.username);
            this.recipient = reqTo;
            reqTo.recipient = this;
            this.reqTo = null;
        }
        // request rejected
        else {
            dos.writeUTF("Request rejected! Try again later!");
        }
    }
}
