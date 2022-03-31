
public class UndirectedEdge extends Edge {

    // Constructors [ One without input, One with 2 inputs ]
    UndirectedEdge() {
        EdgeType = 'U';
    }
    UndirectedEdge(Node[] Nodes) {
        Node[0] = Nodes[0];
        Node[1] = Nodes[1];
        EdgeType = 'U';
    }

    // Getter Method
    Node[] Getter() {
        return Node;
    }
    // Setter Method
    void Setter(Node[] Nodes) {
        Node[0] = Nodes[0];
        Node[1] = Nodes[1];
    }
}
