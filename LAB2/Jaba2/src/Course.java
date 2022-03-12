import java.util.TreeMap;
import java.util.concurrent.TransferQueue;
import java.util.Scanner;

public class Course {
    String Course_Name;
    String Instructor;
    String Department;
    String Credits;
    String Course_ID;
    String CPassword;
    String[] Trainees = new String[50];
    double[] CGrades = new double[50];

    void Course_Info_Print() {
        System.out.printf("%s\n", Course_Name);
        System.out.printf("%s\n", Instructor);
        System.out.printf("%s\n", Department);
        System.out.printf("%s\n", Credits);
        System.out.printf("%s\n", Course_ID);
        for(int i = 0; i < Trainees.length; i++) {
            if(Trainees[i] != null) {
                System.out.printf("%d %s\n", i, Trainees[i]);
            }
        }
        System.out.println("-- -- -- Divider Line -- -- --");
    }

    void Trainees_Print() {
        for(int i = 0; i < Trainees.length; i++) {
            if(Trainees[i] != null) {
                System.out.printf("%d %s\n", i, Trainees[i]);
            }
        }
        System.out.println("-- -- -- Divider Line -- -- --");
    }

    void AR_Trainees(String Action, String New_Stud) {
        int i = 0;
        while(Trainees[i] != null) {
            i++;
        }
        if(Action.equals("Add")) {
            Trainees[i] = New_Stud;
        }
        if(Action.equals("Remove") && i >= 0) {
            Trainees[i-1] = null;
        }
    }

    void Grade(String Password, String CID, String Student, double Grade) {

    }
}
