public class Student{
    private String name;
    private String last_name;
    private String id;
    private String student_num;
    private String major;
    private Course[] registered_courses;

    // constructor:
    public Student(String name, String last_name, String id, String student_num, String major){
        this.name = name;
        this.last_name = last_name;
        this.id = id;
        this.student_num = student_num;
        this.major = major;                       
    }
    // methods:
    // method for printing each student information
    public void printInfo(){}
    // method for adding a course the the list of registered courses
    public void addCourse(Course new_course){}
    // calculating student's average method
    public double calAvg(){
        return 1.0;
    }

}