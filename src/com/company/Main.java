package com.company;

import logic.CLI;
import logic.College;
import logic.Course;
import logic.CourseType;

public class Main {

    public static void main(String[] args) {
        College Math = new College();
        College Physics = new College();
        College Chemistry = new College();
        College Law = new College();
        College Finance = new College();
        Course mathC1 = new Course("teacher101",101,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Math);
        Course mathC2 = new Course("teacher102",102,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Math);
        Course mathC3 = new Course("teacher103",103,20,4,"24/6"
                ,"sunday3", CourseType.General,Math);
        Math.CollegeCourses.add(mathC1);
        Math.CollegeCourses.add(mathC2);
        Math.CollegeCourses.add(mathC3);
        Course PhysicsC1 = new Course("teacher201",201,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Physics);
        Course PhysicsC2 = new Course("teacher202",202,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Physics);
        Course PhysicsC3 = new Course("teacher203",203,20,4,"24/6"
                ,"sunday3", CourseType.General,Physics);
        Physics.CollegeCourses.add(PhysicsC1);
        Physics.CollegeCourses.add(PhysicsC2);
        Physics.CollegeCourses.add(PhysicsC3);
        Course ChemistryC1 = new Course("teacher301",301,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Chemistry);
        Course ChemistryC2 = new Course("teacher302",302,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Chemistry);
        Course ChemistryC3 = new Course("teacher303",303,20,4,"24/6"
                ,"sunday3", CourseType.General,Chemistry);
        Chemistry.CollegeCourses.add(ChemistryC1);
        Chemistry.CollegeCourses.add(ChemistryC2);
        Chemistry.CollegeCourses.add(ChemistryC3);
        Course LawC1 = new Course("teacher401",401,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Chemistry);
        Course LawC2 = new Course("teacher402",402,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Chemistry);
        Course LawC3 = new Course("teacher403",403,20,4,"24/6"
                ,"sunday3", CourseType.General,Chemistry);
        Law.CollegeCourses.add(LawC1);
        Law.CollegeCourses.add(LawC2);
        Law.CollegeCourses.add(LawC3);
        Course FinanceC1 = new Course("teacher501",501,20,4,"24/4"
                ,"sunday1", CourseType.Exclusive,Finance);
        Course FinanceC2 = new Course("teacher502",502,20,4,"24/5"
                ,"sunday2", CourseType.Exclusive,Finance);
        Course FinanceC3 = new Course("teacher503",503,20,4,"24/6"
                ,"sunday3", CourseType.General,Finance);
        Finance.CollegeCourses.add(FinanceC1);
        Finance.CollegeCourses.add(FinanceC2);
        Finance.CollegeCourses.add(FinanceC3);



        CLI cli = new CLI();


	// write your code here
    }
}
