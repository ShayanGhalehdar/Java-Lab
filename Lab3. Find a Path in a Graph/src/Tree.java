import java.util.ArrayList;

public class Tree extends DirectedGraph {


    public Tree(String name) {
        super(name);
    }


    @Override
    public void addEdge(DirectedEdge e1) {

        Node[] inOutNode = e1.getter();

        if (!inOutNode[0].equals(inOutNode[1])) { // Loops are not allowed in trees

            if (this.nodes.size()==0 || (this.nodes.contains(inOutNode[1])) && (!this.nodes.contains(inOutNode[0]))) { // Connected graph and inDegree <= 1
                super.addEdge(e1);
                inOutNode[0].father = inOutNode[1];
                inOutNode[1].children.add(inOutNode[0]);
            }
        }
    }

    public Node getFather(Node n1) {
        return n1.father;
    }

    public ArrayList<Node> getChildren(Node n1) {
        return n1.children;
    }

    public ArrayList<Node> getAncestors(Node n1) {
        ArrayList<Node> ancestors = new ArrayList<>();

        Node currentNode = n1;
        while (currentNode.father != null) {
            ancestors.add(currentNode.father);
            currentNode = currentNode.father;
        }
        return ancestors;
    }

    public ArrayList<DirectedEdge> getPath(Node n1, Node n2) {
        ArrayList<Node> n1_ancestors = getAncestors(n1);
        ArrayList<Node> n2_ancestors = getAncestors(n2);

        // Check if any of the two nodes is the other one's ancestor
        ArrayList<DirectedEdge> path = new ArrayList<>();
        if (n1_ancestors.contains(n2)) {
            Node currentNode = n1;
            while(!currentNode.equals(n2)) {
                path.add(0, findInEdge(currentNode));
                currentNode = currentNode.father;
            }

            return path;
        }
        else if (n2_ancestors.contains(n1)) {
            Node currentNode = n2;
            while(!currentNode.equals(n1)) {
                path.add(0, findInEdge(currentNode));
                currentNode = currentNode.father;
            }
            return path;
        }

        return null;
    }

    private DirectedEdge findInEdge(Node n1) {  // returns the incoming edge to a node from its father

        for(Edge e : n1.getEdges())
            if (n1.father.getEdges().contains(e))
                return (DirectedEdge) e;

        return null;
    }
}
