public class DirectedGraph extends Graph{



    // Adds a new Edge to the directed graph
    void AddEdge(Edge NewEdge) {
        if(NewEdge.EdgeType == 'D') {
            if (!Edge_AL.contains(NewEdge.Node[0]))
                Node_AL.add(NewEdge.Node[0]);
            if (!Edge_AL.contains(NewEdge.Node[1]))
                Node_AL.add(NewEdge.Node[1]);
            Edge_AL.add(NewEdge);
        }
        else {
            System.out.println("Error !");
        }
    }
}
