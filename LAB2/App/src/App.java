public class App {
    public static void main(String[] args) throws Exception {
        // students:
        Student stu1 = new Student("Nima", "Samadi", "97101010", "001213", "Electrical Enginnering");
        Student stu2 = new Student("Ali", "Yousefi", "97101020", "002781", "Computer Enginnering");
        Student stu3 = new Student("Farzaneh", "Moosavi", "97101030", "003791", "Industrial Enginnering");
        Student stu4 = new Student("Asghar", "Abbasi", "97101040", "0758", "Electrical Enginnering");
        
        // courses:
        Course course1 = new Course("Linear Algebra", "Sadeghi", "mathematic", 4, "25123");
        Course course2 = new Course("General Physics1", "Tabatabaee", "physics", 3, "37712");
        
        stu1.addCourse(course1);
        stu1.addCourse(course2);
        
        stu2.addCourse(course2);

        stu3.addCourse(course1);

        stu4.addCourse(course1);
        stu4.addCourse(course2);

        stu1.printInfo();
        stu2.printInfo();
        stu3.printInfo();
        stu4.printInfo();
        System.out.println("-----------------");
        course1.printInfo();
        course2.printInfo();
        System.out.println("-----------------");
    }
}
