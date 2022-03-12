import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // array of students - initial size is 10
        Student[] students = new Student[10];

        // initialize 10 random students
        students[0] = new Student("Nima", "Samadi", "001213", "97101010", "Electrical Enginnering");
        students[1] = new Student("Ali", "Yousefi", "002781", "97101020", "Computer Enginnering");
        students[2] = new Student("Farzaneh", "Moosavi", "003791", "97101030", "Industrial Enginnering");
        students[3] = new Student("Asghar", "Abbasi", "007581", "97101040", "Electrical Enginnering");
        students[4] = new Student("Mohammad", "Goli", "006489", "97101050", "Electrical Enginnering");
        students[5] = new Student("Sara", "Salabati", "006813", "97101060", "Polymer Engineering");
        students[6] = new Student("Mahsa", "Safari", "121315", "97201010", "Industrial Enginnering");
        students[7] = new Student("Taher", "Abazari", "017591", "97301040", "Mechanical Enginnering");
        students[8] = new Student("Gholam", "Gholami", "134679", "97102040", "Chemistry Enginnering");
        students[9] = new Student("Atena", "pourmohammad", "000213", "98151040", "Chemistry Enginnering");
        
        // array of courses - 3 courses available
        Course[] courses = new Course[3];

        courses[0] = new Course("Linear Algebra", "Sadeghi", "mathematic", 4, "251234", "1234");
        courses[1] = new Course("General Physics1", "Tabatabaee", "physics", 3, "377121", "1234");
        courses[2] = new Course("Islamic Thoughts1", "Norozi", "theology", 2, "751278", "1234");

        // Now main program begins:
        Scanner scn = new Scanner(System.in);
        String command;
        System.out.println("Welcome to student management system");
        main_loop: while(true){
            System.out.println("----------------------------------");
            System.out.println("Available options are:"); 
            System.out.printf("1) add student \n2) add course \n3) student login \n4) print courses \n5) print students \n6) grade student \n0) quit \n");
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
                case "3": // student login to add or remove courses    
                    System.out.print("Enter your Username: ");
                    String username = scn.nextLine();
                    System.out.print("Enter your Password: ");
                    String password = scn.nextLine();
                    int status = isStudentExist(students, username, password);
                    if (status == -2){
                        System.out.println("Student doesn't exist!");
                        break;
                    }
                    else if (status == -1){
                        System.out.println("Wrong password!");
                        break;
                    }
                    System.out.println("Successfully logged in!");
                    studentLoginSession(scn, students, courses, status);
                    System.out.println("Ending login session");
                    break;
                case "4":
                    printCourses(courses);
                    break;
                case "5":
                    printStudents(students);
                    break;
                case "6":
                    System.out.print("Enter the course code: ");
                    String course_code = scn.nextLine();
                    System.out.print("Enter password: ");
                    String in_password = scn.nextLine();
                    // check if course exists and password is currect
                    int index = isCourseExist(courses, course_code, in_password);
                    if (index == -1)
                        System.out.println("Wrong password!");
                    else if (index == -2)
                        System.out.println("Course dosen't exist!");
                    else{
                        System.out.print("Enter student number: ");
                        String stu_num = scn.nextLine();
                        System.out.print("Enter grade: ");
                        String grade = scn.nextLine();
                        boolean succ = courses[index].gradeStudent(stu_num, Float.parseFloat(grade));
                        if (succ)
                            System.out.println("student's grade updated successfully");
                        else
                            System.out.println("Student not found!");
                    }
                    break;
                default:
                    System.out.println("Unknown command! Try again....");
                    break;
            }
        }
        scn.close();
    }
    public static int isCourseExist(Course[] courses, String course_code, String password){
        for (int i = 0; i < courses.length; i++)
            if (courses[i].getCourseCode().equals(course_code))
                if (courses[i].checkPassword(password))
                    return i;
                else
                    return -1;
        return -2;
    }
    public static int isStudentExist(Student[] students, String username, String password){
        for (int i = 0; i < students.length; i++)
            if (students[i].getStudentNum().equals(username))
                if (students[i].getStudentId().equals(password))
                    return i;
                else
                    return -1; // wrong password
        
        return -2; // username not found
    }
    public static void studentLoginSession(Scanner scn_obj, Student[] students, Course[] courses, int student_index){
        System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
        String command, course_code;
        boolean flag;
        main_loop: while(true){
            System.out.println("Enter one of the following options: ");
            System.out.printf("1) add course \n2) remove course \n3) get average \n4) display information \n0) quit \n");
            System.out.print("Command option number: ");
            command = scn_obj.nextLine();
            
            switch(command){
                case "0": //quit command
                    System.out.println("Goodbye " + students[student_index].getName() + " !");
                    break main_loop;
                case "1": // add course
                    System.out.print("Enter course code: ");
                    flag = false;
                    course_code = scn_obj.nextLine();
                    for(int i = 0; i < courses.length; i++)
                        if (courses[i].getCourseCode().equals(course_code)){ // if course code exists
                            if (students[student_index].isCourseExist(course_code) >= 0){
                                System.out.println("Course already exists!");
                            }
                            else{
                                students[student_index].registerCourse(courses[i]);
                                System.out.println("Successfully registered!");
                            }
                            flag = true;
                            break;
                        }
                    if (!flag)
                        System.out.println("Course code not found!");
                    break;
                case "2": // remove course
                    flag = false;
                    System.out.print("Enter course code: ");
                    course_code = scn_obj.nextLine();
                    for(int i = 0; i < courses.length; i++)
                        if (courses[i].getCourseCode().equals(course_code)){ // check if course code exists in list of courses
                            int course_index = students[student_index].isCourseExist(course_code); // check if student has the course
                            if (course_index >= 0){
                                students[student_index].removeCourse(course_index);
                                System.out.println("Successfully removed!");
                                flag = true;
                            }
                        }
                    if (!flag)
                        System.out.println("Course code not found!");
                    break;
                case "3": // get average 
                    students[student_index].printAverage();
                    break;
                case "4": // print information 
                    students[student_index].printInfo();
                    break;
                default:
                    System.out.println("Unknowkn command!, try again");
                    break;
            }
        }
        System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
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
        System.out.print("password: ");
        String password = scn_obj.nextLine();

        new_courses[i] = new Course(name, professor, faculty, Integer.parseInt(credit), course_code, password);
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
