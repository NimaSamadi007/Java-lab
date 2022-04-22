import java.util.ArrayList;

public class Node {
    // List of Edges Connected to this particular node
    ArrayList<Edge> Edge_AL = new ArrayList<Edge>();

    // A method to connect a new Edge to this node
    void AddEdge(Edge NewEdge) {
        Edge_AL.add(NewEdge);
    }

    // A method to remove an Edge to this node
    void RemoveEdge(Edge NewEdge) {
        if(Edge_AL.contains(NewEdge)) {
            Edge_AL.remove(NewEdge);
        }
        else {
            System.out.printf("This Edge is not connected to this Node yet\n");
        }
    }

    int GetInDegree() {
        int InDeg = 0;
        for(int i = 0; i < Edge_AL.size(); i++) {
            if (Edge_AL.get(i).EdgeType == 'D') {
                if(Edge_AL.get(i).Node[0] == this) {
                    InDeg++;
                }
            }
            else if(Edge_AL.get(i).EdgeType == 'U') {
                InDeg++;
            }
            else {
                System.out.println("Node Type Error !");
            }
        }
        return InDeg;
    }

    int GetOutDegree() {
        int OutDeg = 0;
        for(int i = 0; i < Edge_AL.size(); i++) {
            if (Edge_AL.get(i).EdgeType == 'D') {
                if(Edge_AL.get(i).Node[1] == this) {
                    OutDeg++;
                }
            }
            else if(Edge_AL.get(i).EdgeType == 'U') {
                OutDeg++;
            }
            else {
                System.out.println("Node Type Error !");
            }
        }
        return OutDeg;
    }

    // Returns all currently connected Edges
    ArrayList<Edge> GetEdges() {
        return Edge_AL;
    }
}