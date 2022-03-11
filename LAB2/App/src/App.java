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
        
        stu1.addCourse(course1, 20.0f);
        stu1.addCourse(course2, 12.7f);
        
        stu2.addCourse(course2, 15.7f);

        stu3.addCourse(course1, 18.5f);

        stu4.addCourse(course1, 19.7f);
        stu4.addCourse(course2, 14.8f);

        stu1.printInfo();
        stu1.printAverage();

        stu2.printInfo();
        stu2.printAverage();

        stu3.printInfo();
        stu3.printAverage();

        stu4.printInfo();
        stu4.printAverage();
        
        System.out.println("-----------------");
        
        course1.printInfo();
        course2.printInfo();
        System.out.println("-----------------");
    }
}
