import java.util.ArrayList;
import java.util.List;

public class Graph {
    ArrayList<Edge> Edge_AL = new ArrayList<Edge>();
    ArrayList<Node> Node_AL = new ArrayList<Node>();

    // Constructor
    Graph() {}
    Graph(Edge[] Edges) {
        Edge_AL.addAll(List.of(Edges));
    }
    // Returns all Edges that are currently a part of this graph
    ArrayList<Edge> GetEdges() {
        return Edge_AL;
    }

    // Returns all Nodes that are currently a part of this graph
    ArrayList<Node> GetNodes() {
        return Node_AL;
    }

    // Adds a new Edge to the graph
    void AddEdge(Edge NewEdge) {
        if(!Node_AL.contains(NewEdge.Node[0]))
            Node_AL.add(NewEdge.Node[0]);
        if(!Node_AL.contains(NewEdge.Node[1]))
            Node_AL.add(NewEdge.Node[1]);
        Edge_AL.add(NewEdge);
    }

    // Removes a Previously connected Edge from the graph
    void RemoveEdge(Edge EdgeNumber) {
        if(Edge_AL.contains(EdgeNumber)){
            Edge_AL.remove(EdgeNumber);
            EdgeNumber.Node[0].Edge_AL.remove(EdgeNumber);
            EdgeNumber.Node[1].Edge_AL.remove(EdgeNumber);
        }
        else {
            System.out.printf("This Edge is not a part of this graph yet\n");
        }
    }

    // Adds a new Node to the graph
    void AddNode(Node NewNode) {
        Node_AL.add(NewNode);
    }

    // Removes a Previously connected Node from the graph
    void RemoveNode(Node NewNode) {
        if(Node_AL.contains(NewNode)){
            Node_AL.remove(NewNode);
        }
        else {
            System.out.printf("This Node is not a part of this graph yet\n");
        }
    }

}