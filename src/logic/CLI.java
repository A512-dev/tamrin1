
package logic;
import com.company.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    public void checkTimeInterference(Course course, Student student, DataBase dataBase) {
        for (int i = 0; i < student.StudentCourses.size(); i++) {
            if (student.StudentCourses.get(i).courseExamTime.equals(course.courseExamTime) ||
                    student.StudentCourses.get(i).courseTime.equals(course.courseTime)) {
                System.out.println("the course you're trying to take has time interference");
                init(dataBase);
                break;
            }
        }

    }

    public void init(DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1- Student");
            System.out.println("2- Admin");
            String input = scanner.next();
            if (input.equals("1")) {
                System.out.println("Enter Your Student Code");
                int code = scanner.nextInt();
                if (!dataBase.getCodes().contains(code)) {
                    dataBase.Codes.add(code);
                    Student newStudent = new Student();
                    newStudent.studentCode = code;
                    dataBase.students.add(newStudent);
                }
                int i = 0;
                while (i < dataBase.students.size() && dataBase.students.get(i).studentCode != code)
                    i++;
                studentOptions(dataBase, i, scanner);
            }
        }
    }

    private void studentOptions(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("1- Registered Courses And Removal");
        System.out.println("1- Registering Courses");
        System.out.println("3- Back to First Page");
        int temp = scanner.nextInt();
        if (temp == 1) {
            studentOption1(dataBase, i, scanner);
        } else if (temp == 2) {
            studentOption2(dataBase,i,scanner);
        }
        else if (temp==3)
            studentOption3(dataBase);//exit
    }



    private void studentOption1 (DataBase dataBase,int i, Scanner scanner){
            if (dataBase.students.get(i).StudentCourses.isEmpty()) {
                System.out.println("You don't have any courses");
                studentOptions(dataBase, i, scanner);
            }
            for (int k = 0; k < dataBase.students.get(i).StudentCourses.size(); k++)
                dataBase.students.get(i).StudentCourses.get(k).printInfo();
            int codeRem = scanner.nextInt();
            int i2 = 0;
            while (i2 < dataBase.students.get(i).StudentCourses.size() && dataBase.students.get(i).StudentCourses.get(i2).courseCode != codeRem)
                i2++;
            if (i2 == dataBase.students.get(i).StudentCourses.size()) {
                System.out.println("you don't have course with this code");
                studentOptions(dataBase, i, scanner);
            }

            //adjustments
            dataBase.students.get(i).numVahed -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
            if (dataBase.students.get(i).StudentCourses.get(i2).courseType.equals(CourseType.General))
                dataBase.students.get(i).numVahedGeneral -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
            dataBase.students.get(i).StudentCourses.get(i2).courseCurrentCapacity--;
            //adjustments

            dataBase.students.get(i).StudentCourses.remove(i2);

            //After Change
            if (dataBase.students.get(i).StudentCourses.isEmpty())
                System.out.println("Empty");
            for (int k = 0; k < dataBase.students.get(i).StudentCourses.size(); k++)
                dataBase.students.get(i).StudentCourses.get(k).printInfo();

        }
    private void studentOption2(DataBase dataBase, int i, Scanner scanner) {
        System.out.print("1:Math: ");
        for (int j = 0; j < dataBase.Math.CollegeCourses.size(); j++) {
            dataBase.Math.CollegeCourses.get(j).printInfo();
        }
        System.out.println();
        System.out.print("2:Physics: ");
        for (int j = 0; j < dataBase.Physics.CollegeCourses.size(); j++) {
            dataBase.Physics.CollegeCourses.get(j).printInfo();
        }
        System.out.println();
        System.out.print("3:Chemistry: ");
        for (int j = 0; j < dataBase.Chemistry.CollegeCourses.size(); j++) {
            dataBase.Chemistry.CollegeCourses.get(j).printInfo();
        }
        System.out.println();
        System.out.print("4:Law: ");
        for (int j = 0; j < dataBase.Law.CollegeCourses.size(); j++) {
            dataBase.Law.CollegeCourses.get(j).printInfo();
        }
        System.out.println();
        System.out.print("5:Finance: ");
        for (int j = 0; j < dataBase.Finance.CollegeCourses.size(); j++) {
            dataBase.Finance.CollegeCourses.get(j).printInfo();
        }
        System.out.println("input the code of the course that you want to take");
        int temp5 = scanner.nextInt();
        int[] info = findInfoOfCourse(dataBase, temp5);
        if (info[0] == 0) {
            System.out.println("the code you entered doesn't belong to any code");
            studentOption2(dataBase, i, scanner);
        } else if (info[0] == 1) //college==Math, i= index of course
        {
            checkTimeInterference(dataBase.Math.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase);
            if (dataBase.Math.CollegeCourses.get(info[1]).courseCapacity > dataBase.Math.CollegeCourses.get(info[1]).courseCurrentCapacity) {
                if (dataBase.students.get(i).numVahed + dataBase.Math.CollegeCourses.get(info[1]).courseSize > 20) {
                    System.out.println("Size of your courses is more than 20");
                    studentOptions(dataBase, i, scanner);
                }
                if (dataBase.Math.CollegeCourses.get(info[1]).courseType.equals(CourseType.General)) {
                    if (dataBase.students.get(i).numVahedGeneral + dataBase.Math.CollegeCourses.get(info[1]).courseSize > 5) {
                        System.out.println("Size of your general courses is more than 5");
                        studentOptions(dataBase, i, scanner);
                    }
                    dataBase.students.get(i).numVahedGeneral += dataBase.Math.CollegeCourses.get(info[1]).courseSize;
                }
                dataBase.students.get(i).StudentCourses.add(dataBase.Math.CollegeCourses.get(info[1]));
                dataBase.students.get(i).numVahed += dataBase.Math.CollegeCourses.get(info[1]).courseSize;
                dataBase.Math.CollegeCourses.get(info[1]).courseCurrentCapacity++;
            }
        } else if (info[0] == 2) //college==Math, i= index of course
        {
            checkTimeInterference(dataBase.Physics.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase);
            if (dataBase.Physics.CollegeCourses.get(info[1]).courseCapacity > dataBase.Physics.CollegeCourses.get(info[1]).courseCurrentCapacity) {
                if (dataBase.students.get(i).numVahed + dataBase.Physics.CollegeCourses.get(info[1]).courseSize > 20) {
                    System.out.println("Size of your courses is more than 20");
                    studentOptions(dataBase, i, scanner);
                }
                if (dataBase.Physics.CollegeCourses.get(info[1]).courseType.equals(CourseType.General)) {
                    if (dataBase.students.get(i).numVahedGeneral + dataBase.Physics.CollegeCourses.get(info[1]).courseSize > 5) {
                        System.out.println("Size of your general courses is more than 5");
                        studentOptions(dataBase, i, scanner);
                    }
                    dataBase.students.get(i).numVahedGeneral += dataBase.Physics.CollegeCourses.get(info[1]).courseSize;
                }
                dataBase.students.get(i).StudentCourses.add(dataBase.Physics.CollegeCourses.get(info[1]));
                dataBase.students.get(i).numVahed += dataBase.Physics.CollegeCourses.get(info[1]).courseSize;
                dataBase.Physics.CollegeCourses.get(info[1]).courseCurrentCapacity++;
            }
        } else if (info[0] == 3) //college==Chemistry, i= index of course
        {
            checkTimeInterference(dataBase.Chemistry.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase);
            if (dataBase.Chemistry.CollegeCourses.get(info[1]).courseCapacity > dataBase.Chemistry.CollegeCourses.get(info[1]).courseCurrentCapacity) {
                if (dataBase.students.get(i).numVahed + dataBase.Chemistry.CollegeCourses.get(info[1]).courseSize > 20) {
                    System.out.println("Size of your courses is more than 20");
                    studentOptions(dataBase, i, scanner);
                }
                if (dataBase.Chemistry.CollegeCourses.get(info[1]).courseType.equals(CourseType.General)) {
                    if (dataBase.students.get(i).numVahedGeneral + dataBase.Chemistry.CollegeCourses.get(info[1]).courseSize > 5) {
                        System.out.println("Size of your general courses is more than 5");
                        studentOptions(dataBase, i, scanner);
                    }
                    dataBase.students.get(i).numVahedGeneral += dataBase.Chemistry.CollegeCourses.get(info[1]).courseSize;
                }
                dataBase.students.get(i).StudentCourses.add(dataBase.Chemistry.CollegeCourses.get(info[1]));
                dataBase.students.get(i).numVahed += dataBase.Chemistry.CollegeCourses.get(info[1]).courseSize;
                dataBase.Chemistry.CollegeCourses.get(info[1]).courseCurrentCapacity++;
            }
        } else if (info[0] == 4) //college==Law, i= index of course
        {
            checkTimeInterference(dataBase.Law.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase);
            if (dataBase.Law.CollegeCourses.get(info[1]).courseCapacity > dataBase.Law.CollegeCourses.get(info[1]).courseCurrentCapacity) {
                if (dataBase.students.get(i).numVahed + dataBase.Law.CollegeCourses.get(info[1]).courseSize > 20) {
                    System.out.println("Size of your courses is more than 20");
                    studentOptions(dataBase, i, scanner);
                }
                if (dataBase.Law.CollegeCourses.get(info[1]).courseType.equals(CourseType.General)) {
                    if (dataBase.students.get(i).numVahedGeneral + dataBase.Law.CollegeCourses.get(info[1]).courseSize > 5) {
                        System.out.println("Size of your general courses is more than 5");
                        studentOptions(dataBase, i, scanner);
                    }
                    dataBase.students.get(i).numVahedGeneral += dataBase.Law.CollegeCourses.get(info[1]).courseSize;
                }
                dataBase.students.get(i).StudentCourses.add(dataBase.Law.CollegeCourses.get(info[1]));
                dataBase.students.get(i).numVahed += dataBase.Law.CollegeCourses.get(info[1]).courseSize;
                dataBase.Law.CollegeCourses.get(info[1]).courseCurrentCapacity++;
            }
        } else if (info[0] == 5) //college==Finance, i= index of course
        {
            checkTimeInterference(dataBase.Finance.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase);
            if (dataBase.Finance.CollegeCourses.get(info[1]).courseCapacity > dataBase.Finance.CollegeCourses.get(info[1]).courseCurrentCapacity) {
                if (dataBase.students.get(i).numVahed + dataBase.Finance.CollegeCourses.get(info[1]).courseSize > 20) {
                    System.out.println("Size of your courses is more than 20");
                    studentOptions(dataBase, i, scanner);
                }
                if (dataBase.Finance.CollegeCourses.get(info[1]).courseType.equals(CourseType.General)) {
                    if (dataBase.students.get(i).numVahedGeneral + dataBase.Finance.CollegeCourses.get(info[1]).courseSize > 5) {
                        System.out.println("Size of your general courses is more than 5");
                        studentOptions(dataBase, i, scanner);
                    }
                    dataBase.students.get(i).numVahedGeneral += dataBase.Finance.CollegeCourses.get(info[1]).courseSize;
                }
                dataBase.students.get(i).StudentCourses.add(dataBase.Finance.CollegeCourses.get(info[1]));
                dataBase.students.get(i).numVahed += dataBase.Finance.CollegeCourses.get(info[1]).courseSize;
                dataBase.Finance.CollegeCourses.get(info[1]).courseCurrentCapacity++;
            }
        }
        studentOptions(dataBase, i, scanner);
    }
    private void studentOption3(DataBase dataBase)
    {
        init(dataBase);
    }
    private int[] findInfoOfCourse(DataBase dataBase, int temp5) {
        int i=0;
        int[] info = {0,0};
        //info[0]=number of college
        //info[1]=index of course in said college
        while (i<dataBase.Math.CollegeCourses.size() && dataBase.Math.CollegeCourses.get(i).courseCode!=temp5)
            i++;
        info[0] = 1;
        info[1] = i;
        if (i<dataBase.Math.CollegeCourses.size())
            return info;
        i=0;
        while (i<dataBase.Physics.CollegeCourses.size() && dataBase.Physics.CollegeCourses.get(i).courseCode!=temp5)
            i++;
        info[0] = 2;
        info[1] = i;
        if (i<dataBase.Physics.CollegeCourses.size())
            return info;
        i=0;
        while (i<dataBase.Chemistry.CollegeCourses.size() && dataBase.Chemistry.CollegeCourses.get(i).courseCode!=temp5)
            i++;
        info[0] = 3;
        info[1] = i;
        if (i<dataBase.Chemistry.CollegeCourses.size())
            return info;
        i=0;
        while (i<dataBase.Law.CollegeCourses.size() && dataBase.Law.CollegeCourses.get(i).courseCode!=temp5)
            i++;
        info[0] = 4;
        info[1] = i;
        if (i<dataBase.Law.CollegeCourses.size())
            return info;
        i=0;
        while (i<dataBase.Finance.CollegeCourses.size() && dataBase.Finance.CollegeCourses.get(i).courseCode!=temp5)
            i++;
        info[0] = 5;
        info[1] = i;
        if (i<dataBase.Finance.CollegeCourses.size())
            return info;
        else
            return info;
    }
}

