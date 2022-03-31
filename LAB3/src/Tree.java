import java.util.ArrayList;

public class Tree extends DirectedGraph {


    // Adds a new Edge to the tree
    /* Constrains :
        1. NewEdge Must be Directed.
        2. NewEdge Must not connect its enter node to its exit node.
        3. If a NewEdge is approved, both nodes and the edge itself are added to the tree.
    */
    void AddEdge(Edge NewEdge) {
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
    Node GetFather(Node NewNode) {
        for(int i = 0; i < Edge_AL.size(); i++) {
            if(Edge_AL.get(i).Node[0] == NewNode) {
                return Edge_AL.get(i).Node[1];
            }
        }
        // System.out.println("GetFather - Error !");
        return null;
    }

    ArrayList<Node> GetChildren(Node NewNode) {
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

    /* Search Algorithm in Directed Trees is easier
    than other Tree types due to the fact that either
    Node1 is one of Node2's Ancestors or other way
    around. So we only need to track one of them to
    it's ancestor. If we perform the search for both
    nodes. we come across 3 conditions :
    I. Node1 is Node2's Ancestor.
    II. Node2 is Node1's Ancestor.
    III. There is no path that connects Node1 to Node2

    Alert : THERE IS ALWAYS A PATH THAT CONNECTS NODE1
    TO NODE2 IN NORMAL TREES, THE ONLY REASON HERE WE
    MIGHT NOT FIND A PATH, IS BECAUSE TREES ARE DIRECTED !
     */
    ArrayList<Node> GetPath(Node Node1, Node Node2) {
        ArrayList<Node> Path = new ArrayList<Node>();
        while(GetFather(Node1) != null){
            Path.add(Node1);
            if(Node1 == Node2)
                return Path;
            else
                Node1 = GetFather(Node1);   
        }

        if(Node1 == Node2){
            Path.add(Node1);
            return Path;
        }

        while(GetFather(Node2) != null){
            Path.add(Node2);
            if(Node1 == Node2)
                return Path;
            else
                Node2 = GetFather(Node2);
        }

        if(Node1 == Node2){
            Path.add(Node2);
            return Path;
        }

        System.out.println("No Path Found !");
        return null;
    }
}