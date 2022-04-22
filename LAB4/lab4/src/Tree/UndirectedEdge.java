package Tree;

public class UndirectedEdge extends Edge {

    // Constructors [ One without input, One with 2 inputs ]
    public UndirectedEdge() {
        EdgeType = 'U';
    }
    public UndirectedEdge(Node[] Nodes) {
        Node[0] = Nodes[0];
        Node[1] = Nodes[1];
        EdgeType = 'U';
    }

    // Getter Method
    public Node[] Getter() {
        return Node;
    }
    // Setter Method
    public void Setter(Node[] Nodes) {
        Node[0] = Nodes[0];
        Node[1] = Nodes[1];
    }
}
