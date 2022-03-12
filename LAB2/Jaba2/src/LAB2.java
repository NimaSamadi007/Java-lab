import java.util.Scanner;
import java.util.Random;

public class LAB2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int C_Num;
        int S_Num;
        System.out.println("Enter The Number of Courses : ");
        C_Num = input.nextInt();
        System.out.println("Enter The Number of Students : ");
        S_Num = input.nextInt();


        Course[] C = new Course[C_Num];
        Student[] S = new Student[S_Num];

        for(int i = 0; i < C_Num; i++) {
            Course CA = new Course();
            System.out.print("Course Name : ");
            CA.Course_Name = input.next();
            System.out.print("Instructor : ");
            CA.Instructor = input.next();
            System.out.print("Department : ");
            CA.Department = input.next();
            System.out.print("Credits : ");
            CA.Credits = input.next();
            System.out.print("Course ID : ");
            CA.Course_ID = input.next();
            System.out.print("Course Password : ");
            CA.CPassword = input.next();
            C[i] = CA;
        }
        for(int i = 0; i < S_Num; i++) {
            Student SA = new Student();
            System.out.print("Given_Name : ");
            SA.Given_Name = input.next();
            System.out.print("Sur_Name : ");
            SA.Sur_Name = input.next();
            System.out.print("National_ID : ");
            SA.National_ID = input.next();
            System.out.print("Student_ID : ");
            SA.Student_ID = input.next();
            System.out.print("Major : ");
            SA.Major = input.next();
            S[i] = SA;
        }

        for(int i = 0; i < S_Num; i++) {
            for(int j = 0 , CC = 0; j < C_Num; j++) {
                int RanInt = rand.nextInt(2);
                if(RanInt == 1) {
                    S[i].Courses[CC] = C[j].Course_Name;
                    System.out.printf("Enter %s %s's Grade for %s : ", S[i].Given_Name, S[i].Sur_Name, C[j].Course_Name);
                    S[i].CGrades[CC] = input.nextDouble();
                    CC++;
                }
            }
        }

        for(int i = 0; i < C_Num; i++) {
            for(int j = 0 , SC = 0; j < S_Num; j++) {
                int RanInt = rand.nextInt(2);
                if(RanInt == 1) {
                    C[i].Trainees[SC++] = S[j].Given_Name + " " + S[j].Sur_Name;
                }
            }
        }

        for(int i = 0; i < S_Num; i++){
            S[i].Student_Info_Print();
            S[i].Grade_Print();
        }

        String Password;
        String CID;
        String Student;
        String Temp;
        double Grade;
        System.out.print("Enter Password : \n");
        Password = input.next();
        System.out.print("Enter Course ID : \n");
        CID = input.next();
        System.out.print("Enter Student Given Name : \n");
        Temp = input.next();
        Student = Temp + " ";
        System.out.print("Enter Student Sur Name : \n");
        Temp = input.next();
        Student = Student + Temp;
        System.out.print("Enter Grade : \n");
        Grade = input.nextDouble();
        for(int i = 0; i < C_Num; i++) {
            if (CID.equals(C[i].Course_ID) && Password.equals(C[i].CPassword)){
                for(int j = 0; j < S_Num; j++) {
                    if((S[j].Given_Name + " " + S[j].Sur_Name).equals(Student)) {
                        for(int k = 0; k < C_Num; k++) {
                            if(S[j].Courses[k].equals(C[i].Course_Name)) {
                                S[j].CGrades[k] = Grade;
                                break;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < S_Num; i++){
            S[i].Student_Info_Print();
            S[i].Grade_Print();
        }
    }

}

