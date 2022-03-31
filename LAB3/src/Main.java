import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        Tree G = new Tree();
        for (int i = 0; i < 5; i++)
            G.AddEdge(edges[i]);

        for (int i = 0; i < 7; i++)
            System.out.print(i + " : " + nodes[i] + ", ");
        System.out.print("\n");

        ArrayList<Node> path = G.GetPath(nodes[0], nodes[3]);
        for (int i = 0; i < path.size(); i++)
            System.out.print(path.get(i) + " -> ");
        // System.out.println(G.GetFather(G.GetFather(nodes[3])));

    }
}
