package logic;

import java.util.ArrayList;

public class Student {
    ArrayList<Course> StudentCourses = new ArrayList<>();
     int numVahed;
     int numVahedGeneral;
     int studentCode;
     public void printInfoStudents()
     {
         System.out.println("num vahed:"+numVahed+" num Vahed General:"+numVahedGeneral+" student Code:"+studentCode);
         //for (int i=0; i<StudentCourses.size(); i++)
         //    StudentCourses.get(i).printInfo();
     }

}
