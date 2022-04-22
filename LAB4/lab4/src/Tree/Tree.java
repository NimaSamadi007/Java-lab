package Tree;
import java.util.ArrayList;

public class Tree extends DirectedGraph {


    // Adds a new Edge to the tree
    /* Constrains :
        1. NewEdge Must be Directed.
        2. NewEdge Must not connect its enter node to its exit node.
        3. If a NewEdge is approved, both nodes and the edge itself are added to the tree.
    */
    public void AddEdge(Edge NewEdge) {
        if((NewEdge.EdgeType == 'D') && (NewEdge.Node[0] != NewEdge.Node[1])) {
            if (Node_AL.contains(NewEdge.Node[0])) {
                if(!Node_AL.contains(NewEdge.Node[1]))
                    Node_AL.add(NewEdge.Node[1]);
                Edge_AL.add(NewEdge);
                // System.out.println("Added Node 1 only");
            }
            else if (Node_AL.contains(NewEdge.Node[1])) {
                if(!Node_AL.contains(NewEdge.Node[0]))
                    Node_AL.add(NewEdge.Node[0]);
                Edge_AL.add(NewEdge);
                // System.out.println("Added Node 0 only");
            }
            else {
                Node_AL.add(NewEdge.Node[0]);
                Node_AL.add(NewEdge.Node[1]);
                Edge_AL.add(NewEdge);
                // System.out.println("Added 2 Nodes");
            }
        }
        else {
            System.out.println("Add Edge Error !");
        }
    }

    /* To Find the father for a node we must first make sure
    that this node is marked as "Enter_Node" if so, the other
    end ot the Edge ( Node[1] ) is 100% Father of this particular
    Node.

    To Find Children of a Node, We can simply swap the indexes :
    Node[0] -> Node[1]
    Node[1] -> Node[0]
    The Logic behind this swapping is that if this node is the so
    called Exit_Node, then every single Enter_Node with this Exit_
    Node are its Children.
     */
    private Node GetFather(Node NewNode) {
        for(int i = 0; i < Edge_AL.size(); i++) {
            if(Edge_AL.get(i).Node[0] == NewNode) {
                return Edge_AL.get(i).Node[1];
            }
        }
        // System.out.println("GetFather - Error !");
        return null;
    }

    public ArrayList<Node> GetChildren(Node NewNode) {
        ArrayList<Node> Children = new ArrayList<Node>();
        for(int i = 0; i < Edge_AL.size(); i++) {
            if(Edge_AL.get(i).Node[0] == NewNode) {
                Children.add(Edge_AL.get(i).Node[1]);
            }
        }
        return Children;
    }

    Node GetAncestors(Node NewNode) {
        for(int i = 0; i < Edge_AL.size(); i++) {
            if(Edge_AL.get(i).Node[0] == NewNode) {
                if(GetAncestors(NewNode).equals(null))
                    return NewNode;
            }
        }
        return NewNode;
    }

    // First node1 is always ancesstor of node2 if there is a path 
    public ArrayList<Node> GetPath(Node Node1, Node Node2) {
        ArrayList<Node> Path = new ArrayList<Node>();

        while(GetFather(Node2) != null){
            Path.add(Node2);
            if(Node1 == Node2)
                return reversePath(Path);
            else
                Node2 = GetFather(Node2);
        }

        if(Node1 == Node2){
            Path.add(Node2);
            return reversePath(Path);
        }

        return null;
    }


    private ArrayList<Node> reversePath(ArrayList<Node> path){
        // reverses the path and returns reversed array
        ArrayList<Node> reversedPath = new ArrayList<Node>();
        for (int i = path.size() - 1; i >= 0; i--)
            reversedPath.add(path.get(i));
        
        return reversedPath;
    }

}