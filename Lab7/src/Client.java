import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    final static int serverPort = 1234;

    public static void main(String[] args) throws IOException {

        Scanner scn = new Scanner(System.in);

        InetAddress ip = InetAddress.getByName("localhost");

        Socket s = new Socket(ip, serverPort);

        // input and output streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        // handling sending messages to server
        SendMessage sm = new SendMessage(s, scn, dos);
        Thread sendMessage = new Thread(sm);

        // handling receiving messages from server
        ReadMessage rm = new ReadMessage(s, dis);
        Thread readMessage = new Thread(rm);

        sendMessage.start();
        readMessage.start();

    }
}

class SendMessage implements Runnable{

    final Scanner scn;
    final DataOutputStream dos;
    Socket s;

    SendMessage(Socket s, Scanner scn, DataOutputStream dos) {
        this.scn = scn;
        this.dos = dos;
        this.s = s;
    }

    @Override
    public void run() {

        while (true) {

            String message = scn.nextLine();

            try {
                dos.writeUTF(message);
            }
            // handling server disconnection
            catch (IOException e) {
                try {
                    System.out.println("Disconnected!");
                    dos.close();
                    s.close();
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

class ReadMessage implements Runnable{

    final DataInputStream dis;
    Socket s;

    ReadMessage(Socket s, DataInputStream dis) {
        this.dis = dis;
        this.s = s;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String message = dis.readUTF();
                System.out.println(message);
            }
            // handling server disconnection
            catch (IOException e) {
                try {
                    System.out.println("Disconnected!");
                    dis.close();
                    s.close();
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
