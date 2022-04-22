import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;;

public class FileHandler {
    private File fileptr;
    private File outFileptr;
    private Scanner fileReader;
    private String fileName;
    private ArrayList<String> edgeSectionLines;
    private ArrayList<String> findSectionLines;
    private Node[] nodesPtr;
    private ArrayList<String> nodesName;
    private DirectedEdge[] edgesPtr;
    private Tree T;

    // Constructor to initilize file name
    public FileHandler(String inputFileName){
        edgeSectionLines = new ArrayList<String>();
        findSectionLines = new ArrayList<String>();
        fileName = inputFileName;
        try{
            // input file
            fileptr = new File(fileName);
            fileReader = new Scanner(fileptr);
            // output file
            outFileptr = new File("Path.txt");
            outFileptr.createNewFile();
        }  // #TODO: Raise exception when file cannot be found 
        catch (FileNotFoundException err){
            System.out.println("File not found!");
            err.printStackTrace();
        }
        catch (IOException err){
            System.out.println("Error while creating file!");
            err.printStackTrace();
        }
    }

    public void readFile(){ // reads file and constructs find and edge sections
        boolean findLineSeen = false;
        while (fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            if (findLineSeen) // we are reading find section
                findSectionLines.add(data);
            // #TODO: Raise exception when there is no fine directive
            else // we are reading edge section or find directive itself
                if (data.equals("Find")) // we have reached fine line
                    findLineSeen = true; 
                else
                    edgeSectionLines.add(data);                    
        }
    }

    public void constructTree(){
        // first construct list of nodes
        constructNodes();
        // next construct list of edges
        constructEdges();
        // add nodes and edges to the tree
        addNodesAndEdgesToTree();
    }

    public void findAllPath(){
        //#TODO: Again check if find section array is not empty
        String[] splitedLine;
        String inode, fnode;
        int inodeIndx, fnodeIndx, nodeOnPathIndx;
        FileWriter filerWriter = null;
        try{
            filerWriter = new FileWriter("Path.txt");
        }
        catch (IOException err){
            System.out.println("Err occured!");
            err.printStackTrace();
        }
        System.out.println("----------^^^^^^^-----------");
        for (int i = 0; i < findSectionLines.size(); i++){
            // #TODO: raise error if there are more than two elements in one line
            splitedLine = findSectionLines.get(i).split(" ", 0);
            inode = splitedLine[0];
            fnode = splitedLine[1];
            inodeIndx = nodesName.indexOf(inode);
            fnodeIndx = nodesName.indexOf(fnode);

            ArrayList<Node> path = T.GetPath(nodesPtr[inodeIndx], nodesPtr[fnodeIndx]);

            if (path == null)
                try{
                    filerWriter.write("No Path \n");
                }
                catch (IOException err){
                    err.printStackTrace();
                }
            else
                // write to found path to file
                for (int j = 0; j < path.size(); j++){
                    nodeOnPathIndx = getNodeIndex(path.get(j));
                    try{
                        filerWriter.write(nodesName.get(nodeOnPathIndx)+"\n");
                    }
                    catch (IOException err){
                        err.printStackTrace();
                    }
                }
            // write this line to seperate outputs
            try{
                filerWriter.write("------------------\n");
            }
            catch (IOException err){
                err.printStackTrace();
            }
        }
        try{
            filerWriter.close();
        }
        catch (IOException err){
            err.printStackTrace();
        }
    }

    // get index of a given node in nodes array
    private int getNodeIndex(Node inNode){
        for (int i = 0; i < nodesPtr.length; i++)
            if (nodesPtr[i] == inNode)
                return i;
        return -1;
    }

    private void addNodesAndEdgesToTree(){
        T = new Tree();
        String[] thisLineNodes;
        String sNode, eNode;
        int sNodeIndx, eNodeIndx;
        for (int i = 0; i < edgeSectionLines.size(); i++){
            thisLineNodes = edgeSectionLines.get(i).split(" ", 0);
            
            // System.out.println(nodesName.size());
            // first add nodes to i'th edge
            sNode = thisLineNodes[1];
            eNode = thisLineNodes[0];
            sNodeIndx = nodesName.indexOf(sNode);
            eNodeIndx = nodesName.indexOf(eNode);
            
            //#TODO: Raise exception if indexOf returned zero
            edgesPtr[i].Setter(nodesPtr[sNodeIndx],
                               nodesPtr[eNodeIndx]);
            // then add this edge to both nodes
            nodesPtr[sNodeIndx].AddEdge(edgesPtr[i]);
            nodesPtr[eNodeIndx].AddEdge(edgesPtr[i]);

            // finally add this edge to tree
            T.AddEdge(edgesPtr[i]);
        }
    }

    // initilizes array of nodes
    private void constructNodes(){
        nodesName = new ArrayList<String>();
        String[] splitedLine;
        for (int i = 0; i < edgeSectionLines.size(); i++){
            splitedLine = edgeSectionLines.get(i).split(" ", 0);
            // check if each node exists in the array and if not, add it

            //#TODO: Raise exception when one line dosen't have exactly two nodes
            if (isElementExist(splitedLine[0], nodesName) == false)
                nodesName.add(splitedLine[0]);
            if (isElementExist(splitedLine[1], nodesName) == false)
                nodesName.add(splitedLine[1]);
        }
        // Now init nodes pointer itself 
        nodesPtr = new Node[nodesName.size()];
        for (int i = 0; i < nodesName.size(); i++){
            nodesPtr[i] = new Node();
            // System.out.println(nodesPtr[i] + " " + nodesName.get(i));
        }
    }

    private void constructEdges(){
        edgesPtr = new DirectedEdge[edgeSectionLines.size()];
        for (int i = 0; i < edgeSectionLines.size(); i++)
            edgesPtr[i] = new DirectedEdge();
        // System.out.println(edgesPtr.length);
    }

    private boolean isElementExist(String elem, ArrayList<String> inArr){
        for (int i = 0; i < inArr.size(); i++)
            if (inArr.get(i).equals(elem))
                return true;
        return false;
    }

    public void printEdgeSection(){
        System.out.println("Edge section:");
        for (int i = 0; i < edgeSectionLines.size(); i++)
            System.out.println(edgeSectionLines.get(i));
    }

    public void printFindSection(){
        System.out.println("Find section:");
        for (int i = 0; i < findSectionLines.size(); i++)
            System.out.println(findSectionLines.get(i));
    }

}
