public class DirectedEdge extends Edge{

    // Indicating EnterNode and ExitNode of a DirectedEdge
    // Node Enter_Node = Node[0];
    // Node Exit_Node = Node[1];

    // Constructors [ One without input, One with 2 inputs ]
    DirectedEdge() {
        EdgeType = 'D';
    }
    DirectedEdge(Node S_Node, Node E_Node) {
        Node[0] = S_Node;
        Node[1] = E_Node;
        EdgeType = 'D';
    }

    // Getter Method
    Node[] Getter() {
        Node[] Carrier = new Node[2];
        Carrier[0] = Node[0];
        Carrier[1] = Node[1];
        return Carrier;
    }
    // Setter Method
    void Setter(Node S_Node, Node E_Node) {
        Node[0] = S_Node;
        Node[1] = E_Node;
    }
}