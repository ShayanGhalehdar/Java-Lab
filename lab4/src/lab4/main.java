package lab4;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args){
        try{
        File input =new File("D:\\AP_Lab\\lab4\\Tree\\TreeSpec.txt");
        FileWriter output = new FileWriter("D:\\AP_Lab\\lab4\\Tree\\Path.txt");
        Scanner sc = new Scanner(input);
        Tree T1 = new Tree("T1");
        ArrayList<Node> Nodes = new ArrayList<>();
        ArrayList<String> NodeName = new ArrayList<>();
        ArrayList<DirectedEdge> Edges = new ArrayList<>();
        ArrayList<String> EdgeName = new ArrayList<>();
        int error=0 ,findDetector=0;
        for(int LineNumber=1;;LineNumber++)
        {
            String str = sc.nextLine();
            if(str.equals("Find"))
            {
                findDetector=1;
                break;
            }
            String[] word = str.split(" ");
            if(word.length>2)
            {
                output.write("Edge defined in line number "+LineNumber+" has more than two nodes!\r\n");
                error=1;
                continue;
            }
            try{
            for (int i=1 ; i>=0 ; i--)
            {
                if(!NodeName.contains(word[i]))
                {
                    Nodes.add(new Node(word[i]));
                    NodeName.add(word[i]);
                }
            }}
            catch(ArrayIndexOutOfBoundsException ex){
                output.write("Edge defined in line number "+LineNumber+" has less than two nodes!\r\n");
                error=1;
                continue;
            }
            Edges.add(new DirectedEdge(word[0]+word[1],Nodes.get(NodeName.indexOf(word[1])),Nodes.get(NodeName.indexOf(word[0]))));
            EdgeName.add(word[0]+word[1]);
            T1.addEdge(Edges.get(EdgeName.indexOf(word[0]+word[1])));
            if(!sc.hasNextLine())
                break;
        }
        if(findDetector==0)
            output.write("\"Find\" Command not found!\r\n");
        else
        {
            if(sc.hasNextLine())
            {
                String str = sc.nextLine();
                if(sc.hasNextLine())
                {
                    error=1;
                    output.write("Multiple paths to find!");
                }
                String[] target = str.split(" ");
        
                try{
                if(error==0)
                {
                    output.write(T1.getPath(Nodes.get(NodeName.indexOf(target[0])), Nodes.get(NodeName.indexOf(target[1]))).get(0).outNode.getName()+"\r\n");// starting node
                    for (DirectedEdge d : T1.getPath(Nodes.get(NodeName.indexOf(target[0])), Nodes.get(NodeName.indexOf(target[1])))) 
                        output.write(d.inNode.getName()+"\r\n"); // writes path nodes and the target node
                }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    output.write("No such target node!");
                }
                catch(Exception ex){
                    if(error==0) // if an error is witnessed, no need to write no path statement. 
                        output.write("No Path");
                }
            }
            else
                output.write("No path assigned to find!");

        }        
        sc.close();
        output.close();
        }
        catch(Exception ex){
            System.out.println("IN CATCH\n");
        }
        
        /*---------------------------------------
        ------------------Bonus------------------
        ---------------------------------------*/
        
        try{
        File input =new File("D:\\AP_Lab\\lab4\\Maze\\Maze.txt");
        FileWriter output = new FileWriter("D:\\AP_Lab\\lab4\\Maze\\MazePath.txt");
        Scanner sc = new Scanner(input);
        ArrayList<Node> Nodes = new ArrayList<>();
        ArrayList<String> NodeName = new ArrayList<>();
        ArrayList<DirectedEdge> Edges = new ArrayList<>();
        ArrayList<String> EdgeName = new ArrayList<>();
        int NodeTag=0,LineNumber,i,j,error=0;
        for(LineNumber=0;sc.hasNextLine();LineNumber++) //surfing through each line of input
        {
            String str = sc.nextLine();
            String[] word = str.split("");
            for (i=0 ; i<word.length ; i++)
            {
                try{
                if(word[i].equals(" "))
                    throw new Exception();
                }
                catch(Exception ex){
                    output.write("You shouldn't insert \"SPACE\" in your input!\r\n");
                    error=1;
                    break;
                }
                  
                Nodes.add(new Node(String.valueOf(NodeTag))); //adding nodes
                NodeName.add(word[i]);
                
                try{
                if(!"&".equals(word[i]) && !"0".equals(word[i]) && !"#".equals(word[i])) // check if input has other characters
                    throw new Exception();
                }
                catch(Exception ex){
                    error=1;
                    output.write("The input format is unrecognizable!\r\n");
                }
                NodeTag=NodeTag+1;
            }
        }
        if(error==0){
        if(NodeTag%LineNumber!=0) //check if maze is rectangular
        {
            output.write("The input is not a table!\r\n");
            error=1;
        } 
        
        for(i=0;i<LineNumber;i++)
        {
            for(j=0;j<NodeTag/LineNumber-1;j++)
            {
                int n0=j+i*(NodeTag/LineNumber); //index of pointer
                int n1=j+i*(NodeTag/LineNumber)+1;
                if((NodeName.get(n0).equals("&")||NodeName.get(n0).equals("0"))&& // check the possible edges between nodes
                   (NodeName.get(n1).equals("&")||NodeName.get(n1).equals("0")))
                {
                    Edges.add(new DirectedEdge(n0+"H"+n1,Nodes.get(n1),Nodes.get(n0)));
                    EdgeName.add(String.valueOf(n0)+"H"+String.valueOf(n1));
                    Edges.add(new DirectedEdge(n1+"H"+n0,Nodes.get(n0),Nodes.get(n1)));
                    EdgeName.add(String.valueOf(n1)+"H"+String.valueOf(n0));
                    Nodes.get(n0).addEdge(Edges.get(EdgeName.indexOf(n0+"H"+n1)), true); // adding edges of each node
                    Nodes.get(n1).addEdge(Edges.get(EdgeName.indexOf(n1+"H"+n0)), true);
                }
            }
        }
        for(i=0;i<(NodeTag/LineNumber);i++)
        {
            for(j=0;j<LineNumber-1;j++)
            {
                int n0=i+j*(NodeTag/LineNumber);
                int n1=i+(j+1)*(NodeTag/LineNumber);
                if((NodeName.get(n0).equals("&")||NodeName.get(n0).equals("0"))&& // check the possible edges between nodes
                   (NodeName.get(n1).equals("&")||NodeName.get(n1).equals("0")))
                {
                    Edges.add(new DirectedEdge(n0+"V"+n1,Nodes.get(n1),Nodes.get(n0)));
                    EdgeName.add(String.valueOf(n0)+"V"+String.valueOf(n1));
                    Edges.add(new DirectedEdge(n1+"V"+n0,Nodes.get(n0),Nodes.get(n1)));
                    EdgeName.add(String.valueOf(n1)+"V"+String.valueOf(n0));
                    Nodes.get(n0).addEdge(Edges.get(EdgeName.indexOf(n0+"V"+n1)), false); // adding edges of each node
                    Nodes.get(n1).addEdge(Edges.get(EdgeName.indexOf(n1+"V"+n0)), false);
                }
            }
        }
        int PortalNumbers=0;
        int StartNode,EndNode,CurrentNode;
        for(StartNode=0;StartNode<Nodes.size();StartNode++)
            if(NodeName.get(StartNode).equals("0"))
            {
                PortalNumbers++;
                break;
            }
        CurrentNode=StartNode;
        for(EndNode=StartNode+1;EndNode<Nodes.size();EndNode++)
            if(NodeName.get(EndNode).equals("0"))
            {
                PortalNumbers++;
                break;
            }
        try{
        for(int OtherNode=EndNode+1;OtherNode<Nodes.size();OtherNode++)
            if(NodeName.get(OtherNode).equals("0"))
            {
                PortalNumbers++;
                throw new Exception();
            }
        }
        catch(Exception ex){
            output.write("The input has more than two portals!\r\n");
        }
        try{
        if(PortalNumbers<2)
            throw new Exception();
        
        ArrayList<Integer> Stack = new ArrayList<>();
        Stack.add(StartNode);
        boolean[] Visited = new boolean[Nodes.size()];
        Arrays.fill(Visited,Boolean.FALSE);
        boolean[] Removed = new boolean[Nodes.size()];
        Arrays.fill(Removed,Boolean.FALSE);
        Visited[StartNode]=true;
        while(true)
        {
            int SS = Stack.size();
            boolean AllAdjacentsVisited =true; // to check if we can proceed from this node or to remove it
            ArrayList<Edge> Adjacents= Nodes.get(CurrentNode).getEdges();// number of starting node adjacent nodes
            for(i=0;i<Adjacents.size();i++)
            {
                String[] word= Adjacents.get(i).getName().split("");
                int AdjacentNode=0,pow=1;
                for(j=word.length-1;Character.isDigit(word[j].charAt(0))==true;j--)
                {
                    if(Character.isDigit(word[j].charAt(0)))
                    {
                        AdjacentNode=AdjacentNode+Integer.valueOf(word[j])*pow;
                        pow=pow*10;
                    }
                }

                if(Visited[AdjacentNode]==false)
                {
                    AllAdjacentsVisited = false;
                    Stack.add(AdjacentNode);
                    //for(j=0;j<Stack.size();j++)
                        //System.out.print(Stack.get(j)+" ");
                    //System.out.println("\n");

                    if(AdjacentNode==EndNode)
                    {
                        Visited[EndNode]=true;
                        break;
                    }
                }
            }
            
            if(Visited[EndNode]==true)
                break;
            if(AllAdjacentsVisited==true)
            {
                Removed[CurrentNode]=true;
                Stack.remove(Stack.size()-1);
                //for(j=0;j<Stack.size();j++)
                    //System.out.print(Stack.get(j)+" ");
                //System.out.println("\n");
                CurrentNode= Stack.get(Stack.size()-1);
                Visited[CurrentNode]=true;
                if(CurrentNode==StartNode && error==0)
                {
                    output.write("No Path");
                    break;
                }
            }
            else if(AllAdjacentsVisited==false)
            {
                Visited[Stack.get(Stack.size()-1)]=true;
                CurrentNode= Stack.get(Stack.size()-1);
            }
        }
        
        if(Visited[EndNode]=true && CurrentNode!=StartNode && PortalNumbers==2)
        {
            for(i=0;i<Stack.size();i++)
            {
                if(Visited[Stack.get(i)]==true && NodeName.get(Stack.get(i)).equals("&") && Removed[Stack.get(i)]==false)
                    NodeName.set(Stack.get(i), "@");
            }
            for(i=0;i<LineNumber;i++)
            {
                for(j=0;j<NodeTag/LineNumber;j++)
                    output.write(NodeName.get(i*(NodeTag/LineNumber)+j));                    
                output.write("\r\n");
            }
        }}
        catch(Exception ex){
            output.write("The input has less than two portals!\r\n");
        }
        sc.close();
        output.close();
        }
        }catch(Exception ex){
            System.out.println("IN CATCH\n");
        }
        
    }
}