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
        if (registered_courses.length >= 1)
            System.out.println(registered_courses[i].getName() + " : " + grades[i] + "]");
        else // no course found
            System.out.println(" ]");
    }
    // method for adding a course the the list of registered courses
    // also adds student to that course's student list
    public void registerCourse(Course new_course){
        Course[] new_registered_courses = new Course[registered_courses.length + 1];
        float[] new_grades = new float[grades.length + 1];
        int i;
        for (i = 0; i < registered_courses.length; i++){
            new_registered_courses[i] = registered_courses[i];
            new_grades[i] = grades[i];
        }
        new_registered_courses[i] = new_course;
        new_grades[i] = 0.0f; // default grade is zero unless instructor changes it

        registered_courses = new_registered_courses;
        grades = new_grades;

        // add this student to the list of registered students in course object
        System.out.println("List of courses updated");
        registered_courses[i].addStudent(this);
    }
    // method for removing a course from the list of registerd courses
    // also remove student from that course's students list
    public void removeCourse(int course_index){
        Course[] new_registered_courses = new Course[registered_courses.length - 1];
        float[] new_grades = new float[grades.length - 1];
        
        // remove course from registered list
        int index = 0;
        for (int i = 0; i < registered_courses.length; i++){
            if (i != course_index){ // keep this course
                new_registered_courses[index] = registered_courses[i];
                new_grades[index] = grades[i];
                ++index;
            }
        }

        System.out.println("List of courses updated");
        // now remove student from the course's list
        registered_courses[course_index].removeStudent(this);

        registered_courses = new_registered_courses;
        grades = new_grades;
    }

    // calculating student's average method
    private double calAvg(){
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
    // checks if a course already exists in the list of registered courses
    public int isCourseExist(String course_code){
        for(int i = 0; i < registered_courses.length; i++)
            if(registered_courses[i].getCourseCode().equals(course_code))
                return i;
        return -1;
    }
    // print student average:
    public void printAverage(){
        System.out.printf("%s %s's average grade is %.2f \n", name, last_name, this.calAvg());
    }
    // some getters
    public String getStudentId(){
        return id;
    }
    public String getStudentNum(){
        return student_num;
    }
    public String getName(){
        return name + " " + last_name;
    }

}
