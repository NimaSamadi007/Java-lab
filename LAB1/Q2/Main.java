import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        System.out.print("Enter a number: ");
        Scanner scn = new Scanner(System.in);
        double input = scn.nextDouble();
        System.out.println("You Entered " + input + " !");
        scn.close();
    }
}
