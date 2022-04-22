public class App {
    public static void main(String[] args) {
        FileHandler f;

        // Test1:
        System.out.println("Test1 - given testcase");
        f = new FileHandler("inputFiles/input1.txt", 
                            "Path1.txt");
        f.readFile();
        f.constructTree();
        f.findAllPath();

        // Test2:
        // System.out.println("Test2 - testing input2.txt file");
        // f = new FileHandler("inputFiles/input2.txt", 
        //                     "Path2.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test3:
        // System.out.println("Test3 - file not found exception test");
        // f = new FileHandler("inputFiles/inputssss.txt", 
        //                     "Path3.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test4:
        // System.out.println("Test4 - no `Find` directive found");
        // f = new FileHandler("inputFiles/input3.txt", 
        //                     "Path4.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test5:
        // System.out.println("Test5 - empty find section");
        // f = new FileHandler("inputFiles/input4.txt", 
        //                     "Path5.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test6:
        // System.out.println("Test6 - more or less than 2 nodes in one line");
        // f = new FileHandler("inputFiles/input5.txt", 
        //                     "Path6.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test7:
        // System.out.println("Test7 - at least one of the nodes in find section couldn't be found in tree");
        // f = new FileHandler("inputFiles/input6.txt", 
        //                     "Path7.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test8:
        // System.out.println("Test8 - input7.txt test (which is 1.png tree)");
        // f = new FileHandler("inputFiles/input7.txt", 
        //                     "Path8.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test9:
        // System.out.println("Test9 - input8.txt test (which is 3.png tree)");
        // f = new FileHandler("inputFiles/input8.txt", 
        //                     "Path9.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();

        // Test10:
        // System.out.println("Test10 - input8.txt test but one line is seperated with tab not space");
        // f = new FileHandler("inputFiles/input8.txt", 
        //                     "Path10.txt");
        // f.readFile();
        // f.constructTree();
        // f.findAllPath();
    }
}
