public class Course {
    private String name;
    private String professor;
    private String faculty;
    private int credit;
    private String course_code;
    private Student[] registered_students;

    // Constructor:
    public Course(String name, String professor, String faculty, int credit, String course_code){
        this.name = name;
        this.professor = professor;
        this.faculty = faculty;
        this.credit = credit;
        this.course_code = course_code;
        this.registered_students = new Student[0];
    }

    // methods:
    public void printInfo(){
        System.out.print("name: " + name + ", professor: " + professor +
                         ", faculty: " + faculty+ ", credit: " + credit + 
                         ", course number: " + course_code + ", registered students: [");
        
        int i;
        for (i = 0; i < registered_students.length - 1; i++)
            System.out.print(registered_students[i].getName() + ", ");
        if ( registered_students.length >= 1 )
            System.out.println(registered_students[i].getName() + "]");
        else // no student found
            System.out.println(" ]");
    }
    // add student to the list of registered students
    public void addStudent(Student new_student){
        Student[] new_registered_students = new Student[registered_students.length+1];
        int i;
        for (i = 0; i < registered_students.length; i++)
            new_registered_students[i] = registered_students[i];
        new_registered_students[i] = new_student;

        registered_students = new_registered_students;        
        System.out.println("List of registered students updated!");
    }
    public String getCourseCode(){
        return course_code;
    }
    // course name getter
    public String getName(){
        return name;
    }
    // credit getter
    public int getCredit(){
        return credit;
    }
}
