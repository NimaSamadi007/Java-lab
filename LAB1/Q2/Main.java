import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.print("Enter a number: ");
        Scanner scn = new Scanner(System.in); // making a scanner object
        double input = scn.nextDouble(); // getting a double input
        System.out.println("You Entered " + input + " !");
        scn.close();
    }
}
