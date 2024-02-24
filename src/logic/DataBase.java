package logic;


import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Integer> Codes = new ArrayList<>();
    public ArrayList<Course> mathCourses = new ArrayList<>();
    public ArrayList<Course> physicsCourses = new ArrayList<>();
    public ArrayList<Course> chemistryCourses = new ArrayList<>();
    public ArrayList<Course> lawCourses = new ArrayList<>();
    public ArrayList<Course> financeCourses = new ArrayList<>();
    //private Storage storage = new Storage();
    private Student loggedInUser;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Student loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ArrayList<Integer> getCodes() {
        return Codes;
    }
}