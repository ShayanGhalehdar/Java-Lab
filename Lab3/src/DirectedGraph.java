import java.util.ArrayList;

public class DirectedGraph extends Graph{


    public DirectedGraph(String name) {
        super(name);
    }

    public DirectedGraph(String name, DirectedEdge[] edges){
        super(name, edges);
    }

    @Override
    public void addEdge(Edge e1) {  // when the edge is undirected, don't add it.

    }

    public void addEdge(DirectedEdge e1) {  // only add the edge if it is directed.
        super.addEdge(e1);
    }

}
