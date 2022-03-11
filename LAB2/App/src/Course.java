public class Course {
    private String name;
    private String master;
    private String faculty;
    private int credit;
    private String course_num;
    private Student[] registered_students;

    // Constructor:
    public Course(String name, String master, String faculty, int credit, String course_num){
        this.name = name;
        this.master = master;
        this.faculty = faculty;
        this.credit = credit;
        this.course_num = course_num;
        this.registered_students = new Student[0];
    }

    // methods:
    public void printInfo(){
        System.out.print("name: " + name + ", master: " + master +
                         ", faculty: " + faculty+ ", credit: " + credit + 
                         ", course number: " + course_num + ", registered students: [");
        
        int i;
        for (i = 0; i < registered_students.length - 1; i++)
            System.out.print(registered_students[i].getName() + ", ");
        System.out.println(registered_students[i].getName() + "]");
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
    // course name getter
    public String getName(){
        return name;
    }
    // credit getter
    public int getCredit(){
        return credit;
    }
}
