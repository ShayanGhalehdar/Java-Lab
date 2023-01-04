import java.util.ArrayList;

public class Graph {

    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    String name;

    public Graph(String name){
        this.name = name;
    }

    public Graph(String name, Edge[] edges){
        this.name = name;

        for (Edge edge : edges) {
            if (!this.edges.contains(edge)) {
                this.edges.add(edge);

                Node[] inOutNode = edge.getter();
                inOutNode[0].addEdge(edge, true);
                inOutNode[1].addEdge(edge, false);

                if (!this.nodes.contains(inOutNode[0]))
                    this.nodes.add(inOutNode[0]);
                if (!this.nodes.contains(inOutNode[1]))
                    this.nodes.add(inOutNode[1]);

            }
        }
    }


    public void addNode(Node n1){   // adds a single node to graph
        if (!this.nodes.contains(n1))    // checks if the node was already in the nodes list
            this.nodes.add(n1);
    }

    public void addEdge(Edge e1){   // adds an edge and its corresponding nodes to the graph
        if (!this.edges.contains(e1)) {
            this.edges.add(e1);

            Node[] inOutNode = e1.getter();
            inOutNode[0].addEdge(e1, true);
            inOutNode[1].addEdge(e1, false);

            if (!this.nodes.contains(inOutNode[0]))
                this.nodes.add(inOutNode[0]);
            if (!this.nodes.contains(inOutNode[1]))
                this.nodes.add(inOutNode[1]);
        }
    }

    public void removeNode(Node n1){    // remove a node from the graph
        this.nodes.remove(n1);
    }

    public void removeEdge(Edge e1){    // remove an edge from the graph

        Node[] inOutNode = e1.getter();
        inOutNode[0].removeEdge(e1, true);
        inOutNode[1].removeEdge(e1, false);

        this.edges.remove(e1);
    }

    public ArrayList<Node> getNodes(){    // get the nodes of the graph
        return this.nodes;
    }

    public ArrayList<Edge> getEdges(){    // get the edges of the graph
        return this.edges;
    }

    public String getName() {
        return this.name;
    }

}
