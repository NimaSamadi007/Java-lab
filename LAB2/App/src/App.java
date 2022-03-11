import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // array of students - initial size is 10
        Student[] students = new Student[10];

        // initial 10 random students
        students[0] = new Student("Nima", "Samadi", "97101010", "001213", "Electrical Enginnering");
        students[1] = new Student("Ali", "Yousefi", "97101020", "002781", "Computer Enginnering");
        students[2] = new Student("Farzaneh", "Moosavi", "97101030", "003791", "Industrial Enginnering");
        students[3] = new Student("Asghar", "Abbasi", "97101040", "007581", "Electrical Enginnering");
        students[4] = new Student("Mohammad", "Goli", "97101050", "006489", "Electrical Enginnering");
        students[5] = new Student("Sara", "Salabati", "97101060", "006813", "Polymer Engineering");
        students[6] = new Student("Mahsa", "Safari", "97201010", "121315", "Industrial Enginnering");
        students[7] = new Student("Taher", "Abazari", "97301040", "017591", "Mechanical Enginnering");
        students[8] = new Student("Gholam", "Gholami", "97102040", "134679", "Chemistry Enginnering");
        students[9] = new Student("Atena", "pourmohammad", "98151040", "000213", "Chemistry Enginnering");
        
        // array of courses - 3 courses available
        Course[] courses = new Course[3];

        courses[0] = new Course("Linear Algebra", "Sadeghi", "mathematic", 4, "251234");
        courses[1] = new Course("General Physics1", "Tabatabaee", "physics", 3, "377121");
        courses[2] = new Course("Islamic Thoughts1", "Norozi", "theology", 2, "751278");

        // Now main program begins:
        Scanner scn = new Scanner(System.in);
        String command;
        System.out.println("Welcome to student management system");
        main_loop: while(true){
            System.out.println("----------------------------------");
            System.out.println("Available options are:"); 
            System.out.printf("1) add student \n2) add course \n3) student login \n4) print courses \n5) print studetns \n0) quit \n");
            System.out.println("----------------------------------");
            System.out.print("Enter command number: ");
            command = scn.nextLine();

            switch(command){
                case "0":
                    System.out.println("Goodbye!");
                    break main_loop;
                case "1": // add new student to the list of students - by this 
                          // option one can easily extend list of students
                    System.out.println("Enter student's information: ");            
                    Student[] new_students = new Student[students.length + 1];
                    addStudent(scn, students, new_students);
                    students = new_students;
                    System.out.println("Successfully added!");
                    break;
                case "2": // add new course to the list of courses - this options
                          // eliminates the need of specifying number of courses and 
                          // students in the beginning of the programm
                    System.out.println("Enter course's description: ");
                    Course[] new_courses = new Course[courses.length + 1];
                    addCourse(scn, courses, new_courses);
                    courses = new_courses;
                    System.out.println("Succefully added!");
                    break;
                case "4":
                    printCourses(courses);
                    break;
                case "5":
                    printStudents(students);
                    break;
                default:
                    System.out.println("Unknown command! Try again....");
                    break;
            }
        }
        scn.close();
    }
    // function to add new course:
    public static void addCourse(Scanner scn_obj, Course[] former_courses, Course[] new_courses){
        int i;
        for(i = 0; i < former_courses.length; i++)
            new_courses[i] = former_courses[i];
        
        System.out.print("name: ");
        String name = scn_obj.nextLine();
        System.out.print("professor: ");
        String professor = scn_obj.nextLine();
        System.out.print("faculty: ");
        String faculty = scn_obj.nextLine();
        System.out.print("credit: ");
        String credit= scn_obj.nextLine();
        System.out.print("course code: ");
        String course_code = scn_obj.nextLine();

        new_courses[i] = new Course(name, professor, faculty, Integer.parseInt(credit), course_code);
    }

    // function to add new student
    public static void addStudent(Scanner scn_obj, Student[] former_students, Student[] new_students){
        int i;
        for(i = 0; i < former_students.length; i++)
            new_students[i] = former_students[i];
        
        System.out.print("name: ");
        String name = scn_obj.nextLine();
        System.out.print("last name: ");
        String last_name = scn_obj.nextLine();
        System.out.print("id: ");
        String id = scn_obj.nextLine();
        System.out.print("student number: ");
        String student_num = scn_obj.nextLine();
        System.out.print("major: ");
        String major = scn_obj.nextLine();

        new_students[i] = new Student(name, last_name, id, student_num, major);
    }
    // method for printing available courses:
    public static void printCourses(Course[] courses){
        System.out.print("Available courses are: \n");
        for (int i = 0; i < courses.length; i++)
            courses[i].printInfo();
    }    
    // method for printing students
    public static void printStudents(Student[] students){
        System.out.print("Current students are: \n");
        for (int i = 0; i < students.length; i++)
            students[i].printInfo();
    }
}
