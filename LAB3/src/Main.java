import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        // graph nodes:
        Node[] nodes = new Node[7];
        for (int i = 0; i < 7; i++)
            nodes[i] = new Node();

        // graph edges:
        DirectedEdge[] edges = new DirectedEdge[5];
        for (int i = 0; i < 5; i++)
            edges[i] = new DirectedEdge();
        
        // add nodes to edges
        edges[0].Setter(nodes[1], nodes[0]); 
        edges[1].Setter(nodes[2], nodes[0]);
        edges[2].Setter(nodes[3], nodes[2]);
        edges[3].Setter(nodes[5], nodes[4]);
        edges[4].Setter(nodes[6], nodes[5]);
        
        // add edges to nodes
        nodes[0].AddEdge(edges[0]);
        nodes[0].AddEdge(edges[1]);
        nodes[1].AddEdge(edges[0]);
        nodes[2].AddEdge(edges[1]);
        nodes[2].AddEdge(edges[2]);
        nodes[3].AddEdge(edges[2]);
        nodes[4].AddEdge(edges[3]);
        nodes[5].AddEdge(edges[3]);
        nodes[6].AddEdge(edges[4]);

        // add all edges to G:
        Tree T = new Tree();
        for (int i = 0; i < 5; i++)
            T.AddEdge(edges[i]);

        // printing nodes' name and their address
        for (int i = 0; i < 7; i++)
            System.out.print(i + " : " + nodes[i] + ", ");
        System.out.print("\n");

        System.out.println("Printing degrees:");
        for (int i = 0; i < 7; i++)
            System.out.println("Node " + i + " : in = " + nodes[i].GetInDegree() 
                                       + ", : out = " + nodes[i].GetOutDegree());

        // get the path between node 0 and node 3 - there is one
        ArrayList<Node> path = T.GetPath(nodes[0], nodes[3]);
        pathPrinter(path);
        
        // there is no path between node 4 and 3
        ArrayList<Node> path2 = T.GetPath(nodes[4], nodes[3]);
        pathPrinter(path2);
        */
        System.out.println("--------------------------");
        // Testing directed graph:

        Node[] nodes2 = new Node[4];
        for (int i = 0; i < 4; i++)
            nodes2[i] = new Node();

        DirectedEdge[] edges2 = new DirectedEdge[5];
        for (int i = 0; i < 5; i++)
            edges2[i] = new DirectedEdge();
        
        // add nodes to edges
        edges2[0].Setter(nodes2[2], nodes2[0]); 
        edges2[1].Setter(nodes2[2], nodes2[3]);
        edges2[2].Setter(nodes2[0], nodes2[3]);
        edges2[3].Setter(nodes2[1], nodes2[0]);
        edges2[4].Setter(nodes2[1], nodes2[1]);
        
        // add edges to nodes
        nodes2[0].AddEdge(edges2[0]);
        nodes2[0].AddEdge(edges2[2]);
        nodes2[0].AddEdge(edges2[3]);
        nodes2[1].AddEdge(edges2[3]);
        nodes2[1].AddEdge(edges2[4]);
        nodes2[2].AddEdge(edges2[0]);
        nodes2[2].AddEdge(edges2[1]);
        nodes2[3].AddEdge(edges2[1]);
        nodes2[3].AddEdge(edges2[2]);
        
        
        DirectedGraph G = new DirectedGraph();
        for (int i = 0; i < 5; i++)
            G.AddEdge(edges2[i]);

        // printing nodes' name and their address
        for (int i = 0; i < 4; i++)
            System.out.print(i + " : " + nodes2[i] + ", ");
        System.out.print("\n");

        // get nodes between edge 3:
        Node[] node_edge3 = edges2[3].Getter();
        System.out.println("Edge 3 nodes: " + 
                            node_edge3[0] + " - " + 
                            node_edge3[1]);

        Node[] node_edge4 = edges2[4].Getter();
        System.out.println("Edge 4 nodes: " + 
                            node_edge4[0] + " - " + 
                            node_edge4[1]);

        ArrayList<Edge> all_edges = G.GetEdges();
        for (int i = 0; i < all_edges.size(); i++)
            System.out.print(all_edges.get(i) + ", ");
        System.out.print("\n");
        
        // remove edge 2:
        G.RemoveEdge(edges2[3]);
        
        // node_edge3 = edges2[3].Getter();
        // System.out.println("Edge 3 nodes: " + 
        //                     node_edge3[0] + " - " + 
        //                     node_edge3[1]);
        
        all_edges = G.GetEdges();
        for (int i = 0; i < all_edges.size(); i++)
            System.out.print(all_edges.get(i) + ", ");
        System.out.print("\n");
        
    }
    static public void pathPrinter(ArrayList<Node> path){
        if (path != null){
            for (int i = 0; i < path.size() - 1; i++)
                System.out.print(path.get(i) + " -> ");
            System.out.println(path.get(path.size()-1));
        }
        else 
            System.out.println("No path found!");
    }
}
