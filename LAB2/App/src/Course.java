public class Course {
    private String name;
    private String professor;
    private String faculty;
    private int credit;
    private String course_code;
    private String password;
    private Student[] registered_students;

    // Constructor:
    public Course(String name, String professor, String faculty, int credit, String course_code, String password){
        this.name = name;
        this.professor = professor;
        this.faculty = faculty;
        this.credit = credit;
        this.course_code = course_code;
        this.password = password;
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
        System.out.println("List of registered students updated");
    }
    // remove student from the list of registered students
    public void removeStudent(Student student){
        Student[] new_registered_students = new Student[registered_students.length-1];

        int index = 0;
        for(int i = 0; i < registered_students.length; i++)
            if(! registered_students[i].getStudentNum().equals(student.getStudentNum())) // if this is not the given student, keep him/her
                new_registered_students[index++] = registered_students[i];
        
        registered_students = new_registered_students;
        System.out.println("List of registered students updated!");
    }
    // method for grading each student
    public boolean gradeStudent(String stu_num, float grade){
        for (int i = 0; i < registered_students.length; i++)
            if (registered_students[i].getStudentNum().equals(stu_num)){
                registered_students[i].gradeCourse(this, grade);
                return true;
            }
        return false;
    }

    // method for checking if the input password is correct
    public boolean checkPassword(String in_password){
        if (this.password.equals(in_password))
            return true;
        else
            return false;
    }
    // getters:
    public String getCourseCode(){
        return course_code;
    }
    public String getName(){
        return name;
    }
    public int getCredit(){
        return credit;
    }
}
