package logic;


import java.util.ArrayList;
import java.util.List;

public class DataBase {

     ArrayList<Student> students = new ArrayList<>();
     ArrayList<Integer> studentsCodes = new ArrayList<>();
    ArrayList<Integer> coursesCodes = new ArrayList<>();
    College Math = new College();
    College Physics = new College();
    College Chemistry = new College();
    College Law = new College();
    College Finance = new College();
    //private Storage storage = new Storage();
    private Student loggedInUser;

    public void init(){

        Course mathC1 = new Course("m1","teacher101",101,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Math);
        Course mathC2 = new Course("m2","teacher102",102,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Math);
        Course mathC3 = new Course("m3","teacher103",103,20,4,"24/6"
                ,"sunday3", CourseType.General,Math);
        coursesCodes.add(101);
        coursesCodes.add(102);
        coursesCodes.add(103);
        Math.CollegeCourses.add(mathC1);
        Math.CollegeCourses.add(mathC2);
        Math.CollegeCourses.add(mathC3);
        Course PhysicsC1 = new Course("p1","teacher201",201,20,4,"25/4"
                ,"monday1", CourseType.Exclusive,Physics);
        Course PhysicsC2 = new Course("p2","teacher202",202,20,4,"25/5"
                ,"monday2", CourseType.Exclusive,Physics);
        Course PhysicsC3 = new Course("p3","teacher203",203,20,4,"25/6"
                ,"monday3", CourseType.General,Physics);
        Physics.CollegeCourses.add(PhysicsC1);
        Physics.CollegeCourses.add(PhysicsC2);
        Physics.CollegeCourses.add(PhysicsC3);
        coursesCodes.add(201);
        coursesCodes.add(202);
        coursesCodes.add(203);
        Course ChemistryC1 = new Course("c1","teacher301",301,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Chemistry);
        Course ChemistryC2 = new Course("c2","teacher302",302,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Chemistry);
        Course ChemistryC3 = new Course("c3","teacher303",303,20,4,"24/6"
                ,"sunday3", CourseType.General,Chemistry);
        Chemistry.CollegeCourses.add(ChemistryC1);
        Chemistry.CollegeCourses.add(ChemistryC2);
        Chemistry.CollegeCourses.add(ChemistryC3);
        coursesCodes.add(301);
        coursesCodes.add(302);
        coursesCodes.add(303);
        Course LawC1 = new Course("l1","teacher401",401,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Chemistry);
        Course LawC2 = new Course("l2","teacher402",402,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Chemistry);
        Course LawC3 = new Course("l3","teacher403",403,20,4,"24/6"
                ,"sunday3", CourseType.General,Chemistry);
        Law.CollegeCourses.add(LawC1);
        Law.CollegeCourses.add(LawC2);
        Law.CollegeCourses.add(LawC3);
        coursesCodes.add(401);
        coursesCodes.add(402);
        coursesCodes.add(403);
        Course FinanceC1 = new Course("f1","teacher501",501,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Finance);
        Course FinanceC2 = new Course("f2","teacher502",502,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Finance);
        Course FinanceC3 = new Course("f3","teacher503",503,20,4,"24/6"
                ,"sunday3", CourseType.General,Finance);
        Finance.CollegeCourses.add(FinanceC1);
        Finance.CollegeCourses.add(FinanceC2);
        Finance.CollegeCourses.add(FinanceC3);
        coursesCodes.add(501);
        coursesCodes.add(502);
        coursesCodes.add(503);
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Student loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}