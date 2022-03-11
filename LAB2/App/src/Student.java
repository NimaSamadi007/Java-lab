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
        this.registered_courses = new Course[0];                     
    }
    // methods:
    // method for printing each student information
    public void printInfo(){
        System.out.print("name: " + name + ", last name: " + last_name +
                         ", id: " + id + ", student number: " + student_num + 
                         ", major: " + major + ", courses: [");
        
        int i;
        for (i = 0; i < registered_courses.length - 1; i++)
            System.out.print(registered_courses[i].getName() + ", ");
        System.out.println(registered_courses[i].getName() + "]");
    }
    // method for adding a course the the list of registered courses
    public void addCourse(Course new_course){
        Course[] new_registered_courses = new Course[registered_courses.length+1];
        int i;
        for (i = 0; i < registered_courses.length; i++)
            new_registered_courses[i] = registered_courses[i];
        new_registered_courses[i] = new_course;

        registered_courses = new_registered_courses;
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
        return 1.0;
    }

}
