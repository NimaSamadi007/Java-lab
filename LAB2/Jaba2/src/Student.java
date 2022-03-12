
public class Student {
    String Given_Name;
    String Sur_Name;
    String National_ID;
    String Student_ID;
    String Major;
    String[] Courses = new String[50];
    double[] CGrades = new double[50];

    void Student_Info_Print() {
        System.out.printf("%s\n", Given_Name);
        System.out.printf("%s\n", Sur_Name);
        System.out.printf("%s\n", National_ID);
        System.out.printf("%s\n", Student_ID);
        System.out.printf("%s\n", Major);
        for(int i = 0; i < Courses.length; i++) {
            if(Courses[i] != null) {
                System.out.printf("%d - %s Grade = %f\n", i + 1, Courses[i], CGrades[i]);
            }
        }
        System.out.println("-- -- -- Divider Line -- -- --");
    }

    void Grade_Print() {
        double Sum = 0;
        for(int i = 0; i < CGrades.length; i++) {
            if(Courses[i] != null) {
                Sum = Sum + CGrades[i];
            }
            else if(Courses[i] == null) {
                System.out.printf("%s %s Avg : %f\n", Given_Name, Sur_Name, Sum / (i));
                break;
            }
        }
        System.out.println("-- -- -- Divider Line -- -- --");
    }

    void AR_Course(String Action, String New_Course) {
        int i = 0;
        while(Courses[i] != null) {
            i++;
        }
        if(Action.equals("Add")) {
            Courses[i] = New_Course;
        }
        if(Action.equals("Remove") && i >= 0) {
            Courses[i-1] = null;
        }
    }
}
