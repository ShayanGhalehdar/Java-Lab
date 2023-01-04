package lab4;

public class UndirectedEdge extends Edge{

    Node[] nodes = new Node[2];

    String name;

    public UndirectedEdge(String name){    // normal constructor
        this.name = name;
    }

    public UndirectedEdge(String name, Node node1, Node node2){  // constructor with initialization

        this.name = name;
        this.nodes[0] = node1;
        this.nodes[1] = node2;
    }

    public Node[] getter(){ // get the nodes
        return nodes;
    }

    public void setter(Node node1, Node node2){ // set the nodes if not set with constructor

        this.nodes[0] = node1;
        this.nodes[1] = node2;
    }

    public String getName() {
        return this.name;
    }

}