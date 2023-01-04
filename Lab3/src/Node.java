import java.util.ArrayList;

public class Node {

    ArrayList<Edge> edges = new ArrayList<>();
    int inDegree;
    int outDegree;
    String name;

    // used by the class "Tree"
    Node father = null;
    ArrayList<Node> children = new ArrayList<>();

    public Node(String name){  // constructor
        this.inDegree = 0;
        this.outDegree = 0;
        this.name = name;
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }   // returns the edges

    public void addEdge(Edge e1, boolean isIn){   // add an edge to the list
        if (!edges.contains(e1)) {
            edges.add(e1);
            if (e1.getClass().getName().equals("DirectedEdge")) {
                if (isIn)
                    this.inDegree ++;
                else
                    this.outDegree ++;
            }
            else {
                this.inDegree ++;
                this.outDegree ++;
            }
        }
    }

    public void removeEdge(Edge e1, boolean isIn){    // remove an edge

        if (e1.getClass().getName().equals("DirectedEdge")) {
            if (isIn)
                this.inDegree --;
            else
                this.outDegree --;
        }
        else {
            this.inDegree --;
            this.outDegree --;
        }
        edges.remove(e1);
    }

    public int getInDegree(){   // get InDegree
        return this.inDegree;
    }
    public int getOutDegree(){  // get OutDegree
        return this.outDegree;
    }

    public String getName() {
        return this.name;
    }
}
