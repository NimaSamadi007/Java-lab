public class Student{
    private String name;
    private String last_name;
    private String id;
    private String student_num;
    private String major;
    private Course[] registered_courses;
    private float[] grades; // each student must have a list of grades so we can calculate 
                            // his average - not mentioned in pdf file?!

    // constructor:
    public Student(String name, String last_name, String id, String student_num, String major){
        this.name = name;
        this.last_name = last_name;
        this.id = id;
        this.student_num = student_num;
        this.major = major;  
        this.registered_courses = new Course[0];                     
        this.grades = new float[0];
    }
    // methods:
    // method for printing each student information
    public void printInfo(){
        System.out.print("name: " + name + ", last name: " + last_name +
                         ", id: " + id + ", student number: " + student_num + 
                         ", major: " + major + ", courses: [");
        
        int i;
        for (i = 0; i < registered_courses.length - 1; i++)
            System.out.print(registered_courses[i].getName() + " : " + grades[i] + ", ");
        System.out.println(registered_courses[i].getName() + " : " + grades[i] + "]");
    }
    // method for adding a course the the list of registered courses
    public void addCourse(Course new_course, float grade){
        Course[] new_registered_courses = new Course[registered_courses.length + 1];
        float[] new_grades = new float[grades.length + 1];
        int i;
        for (i = 0; i < registered_courses.length; i++){
            new_registered_courses[i] = registered_courses[i];
            new_grades[i] = grades[i];
        }
        new_registered_courses[i] = new_course;
        new_grades[i] = grade;

        registered_courses = new_registered_courses;
        grades = new_grades;

        // add this student to the list of registered students in course object
        registered_courses[i].addStudent(this);

        System.out.println("List of courses updated!");
        System.out.println("------------------------");
    }
    public String getName(){
        return name + " " + last_name;
    }
    // calculating student's average method
    public double calAvg(){
        double total_grade = 0.0;
        int total_credit = 0;
        int current_course_credit;
        for (int i = 0; i < grades.length; i++){
            current_course_credit = registered_courses[i].getCredit();
            total_grade += (grades[i] * current_course_credit);
            total_credit += current_course_credit;
        }
        return (total_grade / total_credit);
    }
    // print student average:
    public void printAverage(){
        System.out.printf("%s %s's average grade is %.2f \n", name, last_name, this.calAvg());
    }

}
