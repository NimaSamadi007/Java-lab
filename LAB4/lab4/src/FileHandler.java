import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import MyExceptions.*;
import Tree.*;


public class FileHandler {
    private File fileptr;
    private File outFileptr;
    private FileWriter filerWriter;
    private Scanner fileReader;    
    private String fileName;
    private ArrayList<String> edgeSectionLines;
    private ArrayList<String> findSectionLines;
    private Node[] nodesPtr;
    private ArrayList<String> nodesName;
    private DirectedEdge[] edgesPtr;
    private Tree T;

    // Constructor to initilize file name
    public FileHandler(String inputFileName, String outputFileName){
        edgeSectionLines = new ArrayList<String>();
        findSectionLines = new ArrayList<String>();
        fileName = inputFileName;
        filerWriter = null;
        try{
            // output file
            outFileptr = new File(outputFileName);
            outFileptr.createNewFile();
            filerWriter = new FileWriter(outputFileName);
            // input file
            fileptr = new File(fileName);
            fileReader = new Scanner(fileptr);
        } 
        catch (FileNotFoundException err){
            err.printStackTrace();
            writeToFile("File " + fileName + " not found!");
            closeFile();
            System.exit(1); // terminate program
        }
        catch (IOException err){
            System.out.println("Error while creating file!");
            err.printStackTrace();
            System.exit(1); // terminate program
        }
    }

    public void readFile(){ // reads file and constructs find and edge sections
        boolean findLineSeen = false;
        while (fileReader.hasNextLine()){
            String data = fileReader.nextLine();
            if (findLineSeen) // we are reading find section
                findSectionLines.add(data);
            else // we are reading edge section or find directive itself
                if (data.equals("Find")) // we have reached fine line
                    findLineSeen = true; 
                else
                    edgeSectionLines.add(data);                    
        }
        // if we didn't reach find directive in input file,
        // raise error and exit
        try{
            if (findLineSeen == false){
                FindDirectiveException findExc = new FindDirectiveException("No find directive found");
                throw findExc;
            }
        } 
        catch (FindDirectiveException err){
            writeToFile("No find directive found!");
            closeFile();
            err.printStackTrace();
            System.exit(1);
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
        String[] splitedLine;
        String inode, fnode;
        int inodeIndx, fnodeIndx, nodeOnPathIndx;

        // raise error if find section is empty
        try{
            if (findSectionLines.size() == 0){ // empty find section
                NullFindSection nullFind = new NullFindSection("Find section is empty!");
                throw nullFind;
            }
        } 
        catch (NullFindSection err){
            writeToFile("Find section is empty!");
            closeFile();
            err.printStackTrace();
            System.exit(1);
        }

        for (int i = 0; i < findSectionLines.size(); i++){
            splitedLine = findSectionLines.get(i).split(" ", 0);

            // check if more or less than 2 nodes are inserted
            try{
                if (splitedLine.length != 2){
                    NotExactElements nExactExc = new NotExactElements("There must be exactly 2 nodes - " 
                                                                    + splitedLine.length + " elements inserted");
                    throw nExactExc;
                }
            } 
            catch (NotExactElements err){
                writeToFile("There must be exactly 2 nodes - " + splitedLine.length + " elements inserted");
                closeFile();
                err.printStackTrace();
                System.exit(1);
            }
            
            inode = splitedLine[0];
            fnode = splitedLine[1];
            inodeIndx = nodesName.indexOf(inode);
            fnodeIndx = nodesName.indexOf(fnode);

            // It's possible that there is no such nodes in the tree to be found
            ArrayList<Node> path = null;
            try{
                path = T.GetPath(nodesPtr[inodeIndx], nodesPtr[fnodeIndx]);
            }
            catch (ArrayIndexOutOfBoundsException err){
                writeToFile("One or more nodes in find section cannot be found in the tree");
                closeFile();
                err.printStackTrace();
                System.exit(1);
            }

            if (path == null)
                writeToFile("No path");
            else{
                // write to found path to file
                for (int j = 0; j < path.size(); j++){
                    nodeOnPathIndx = getNodeIndex(path.get(j));
                    writeToFile(nodesName.get(nodeOnPathIndx));
                }
            }
            // write this line to seperate outputs
            writeToFile("--------------------");
                
        }
        closeFile();
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

            // check if more or less than 2 nodes are inserted
            try{
                if (splitedLine.length != 2){
                    NotExactElements nExactExc = new NotExactElements("There must be exactly 2 nodes - " 
                                                                    + splitedLine.length + " elements inserted");
                    throw nExactExc;
                }
            } 
            catch (NotExactElements err){
                writeToFile("There must be exactly 2 nodes - " + splitedLine.length + " elements inserted");
                closeFile();
                err.printStackTrace();
                System.exit(1);
            }

            // check if each node exists in the array and if not, add it
            if (isElementExist(splitedLine[0], nodesName) == false)
                nodesName.add(splitedLine[0]);
            if (isElementExist(splitedLine[1], nodesName) == false)
                nodesName.add(splitedLine[1]);
        }
        // Now init nodes pointer itself 
        nodesPtr = new Node[nodesName.size()];
        for (int i = 0; i < nodesName.size(); i++)
            nodesPtr[i] = new Node();
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

    private void writeToFile(String text){
        try{
            filerWriter.write(text+"\n");
        }
        catch (IOException err){
            System.out.println("Unable to write to file!");
            err.printStackTrace();
            System.exit(1);
        }
    }

    private void closeFile(){
        try {
            filerWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to close file!");
            e.printStackTrace();
        }
    }
}
