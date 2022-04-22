package Tree;
public class DirectedGraph extends Graph{

    public DirectedEdge RemoveEdge(DirectedEdge EdgeNumber) {
        if(Edge_AL.contains(EdgeNumber)){
            Edge_AL.remove(EdgeNumber);
            EdgeNumber.Node[0].Edge_AL.remove(EdgeNumber);
            EdgeNumber.Node[1].Edge_AL.remove(EdgeNumber);
            return null;   
        }
        else {
            System.out.printf("This Edge is not a part of this graph yet\n");
            return EdgeNumber;
        }
    }

    // Adds a new Edge to the directed graph
    public void AddEdge(Edge NewEdge) {
        if(NewEdge.EdgeType == 'D') {
            if (!Node_AL.contains(NewEdge.Node[0]))
                Node_AL.add(NewEdge.Node[0]);
            if (!Node_AL.contains(NewEdge.Node[1]))
                Node_AL.add(NewEdge.Node[1]);
            Edge_AL.add(NewEdge);
        }
        else {
            System.out.println("Error !");
        }
    }
}
