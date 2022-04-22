public class App {
    public static void main(String[] args) {
        FileHandler f = new FileHandler("inputFiles/input1.txt");
        f.readFile();
        f.constructTree();
        f.findAllPath();
    }
}
