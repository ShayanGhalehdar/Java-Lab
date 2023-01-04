import java.util.ArrayList;

//  The test topology:
//
//                           ------------------ [n1] ------------------
//                           |                                        |
//                      (e1) |                                        | (e2)
//                           |                                        |
//                          [n2]                         ----------- [n3] -----------
//                           |                           |                          |
//                      (e5) |                      (e3) |                          | (e4)
//                           |                           |                          |
//                          [n6]                ------- [n4] -------               [n5]
//                                              |                  |
//                                         (e7) |                  | (e6)
//                                              |                  |
//                                             [n8]               [n7]
//                                              |
//                                         (e8) |
//                                              |
//                                             [n9]
//


public class main {
    public static void main(String[] args){

        Node n1 = new Node("n1");
        Node n2 = new Node("n2");
        Node n3 = new Node("n3");
        Node n4 = new Node("n4");
        Node n5 = new Node("n5");
        Node n6 = new Node("n6");
        Node n7 = new Node("n7");
        Node n8 = new Node("n8");
        Node n9 = new Node("n9");

        DirectedEdge e1 = new DirectedEdge("e1", n2, n1);
        DirectedEdge e2 = new DirectedEdge("e2", n3, n1);
        DirectedEdge e3 = new DirectedEdge("e3", n4, n3);
        DirectedEdge e4 = new DirectedEdge("e4", n5, n3);
        DirectedEdge e5 = new DirectedEdge("e5", n6, n2);
        DirectedEdge e6 = new DirectedEdge("e6", n7, n4);
        DirectedEdge e7 = new DirectedEdge("e7", n8, n4);
        DirectedEdge e8 = new DirectedEdge("e8", n9, n8);

        Tree T1 = new Tree("T1");
        T1.addEdge(e1);
        T1.addEdge(e2);
        T1.addEdge(e3);
        T1.addEdge(e4);
        T1.addEdge(e5);
        T1.addEdge(e6);
        T1.addEdge(e7);
        T1.addEdge(e8);

        System.out.println("Ancestors of n9:");
        for (Node n : T1.getAncestors(n9))
            System.out.println(n.getName());

        System.out.println("\npath between n3 and n7:");
        for (DirectedEdge d : T1.getPath(n7, n3))
            System.out.println(d.getName());

    }
}
