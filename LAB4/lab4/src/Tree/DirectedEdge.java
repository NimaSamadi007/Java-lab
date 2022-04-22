package Tree;
public class DirectedEdge extends Edge{

    // Indicating EnterNode and ExitNode of a DirectedEdge
    // Node Enter_Node = Node[0];
    // Node Exit_Node = Node[1];

    // Constructors [ One without input, One with 2 inputs ]
    public DirectedEdge() {
        EdgeType = 'D';
    }
    public DirectedEdge(Node S_Node, Node E_Node) {
        Node[0] = S_Node;
        Node[1] = E_Node;
        EdgeType = 'D';
    }

    // Getter Method
    public Node[] Getter() {
        Node[] Carrier = new Node[2];
        Carrier[0] = Node[0];
        Carrier[1] = Node[1];
        return Carrier;
    }
    // Setter Method
    public void Setter(Node S_Node, Node E_Node) {
        Node[0] = S_Node;
        Node[1] = E_Node;
    }
}