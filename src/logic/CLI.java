
package logic;
import com.company.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    public void checkTimeInterference(Course course,Student student,DataBase dataBase)
    {
        for (int i=0; i<student.StudentCourses.size(); i++)
        {
            if (student.StudentCourses.get(i).courseExamTime.equals(course.courseExamTime) ||
                    student.StudentCourses.get(i).courseTime.equals(course.courseTime))
            {
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
                if (dataBase.getCodes().contains(code))
                {
                    int i=0;
                    while (i<dataBase.students.size() && dataBase.students.get(i).studentCode !=code)
                        i++;
                    System.out.println("1- Registered Courses And Removal");
                    System.out.println("1- Registering Courses");
                    System.out.println("3- Back to First Page");
                    int temp = scanner.nextInt();
                    if (temp==1)
                    {
                        for (int k=0; k< dataBase.students.get(i).StudentCourses.size(); k++)
                            dataBase.students.get(i).StudentCourses.get(k).printInfo();
                        int codeRem = scanner.nextInt();
                        int i2=0;
                        while (i2<dataBase.students.get(i).StudentCourses.size() && dataBase.students.get(i).StudentCourses.get(i2).courseCode !=codeRem)
                            i2++;

                        dataBase.students.get(i).numVahed -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
                        if (dataBase.students.get(i).StudentCourses.get(i2).courseType.equals(CourseType.General))
                            dataBase.students.get(i).numVahedGeneral -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
                        dataBase.students.get(i).StudentCourses.get(i2).courseCurrentCapacity--;
                        dataBase.students.get(i).StudentCourses.remove(i2);


                        if (dataBase.students.get(i).StudentCourses.isEmpty())
                            System.out.println("Empty");
                        for (int k=0; k< dataBase.students.get(i).StudentCourses.size(); k++)
                            dataBase.students.get(i).StudentCourses.get(k).printInfo();
                    }
                    else if (temp==2)
                    {
                        System.out.print("1:Math: ");
                        for(int j = 0; j < dataBase.Math.CollegeCourses.size(); j++) {
                            System.out.print(dataBase.Math.CollegeCourses.get(j).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("2:Physics: ");
                        for(int j = 0; j < dataBase.Physics.CollegeCourses.size(); j++) {
                            System.out.print(dataBase.Physics.CollegeCourses.get(j).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("3:Chemistry: ");
                        for(int j = 0; j < dataBase.Chemistry.CollegeCourses.size(); j++) {
                            System.out.print(dataBase.Chemistry.CollegeCourses.get(j).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("4:Law: ");
                        for(int j = 0; j < dataBase.Law.CollegeCourses.size(); j++) {
                            System.out.print(dataBase.Law.CollegeCourses.get(j).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("5:Finance: ");
                        for(int j = 0; j < dataBase.Finance.CollegeCourses.size(); j++) {
                            System.out.print(dataBase.Finance.CollegeCourses.get(j).courseName+" ");
                        }
                        System.out.println();
                        System.out.println("Choose the College by number");
                        int collegetem = scanner.nextInt();
                        switch (collegetem){
                            case 1:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp = scanner.nextInt();
                                if (coursetemp<dataBase.Math.CollegeCourses.size())
                                {
                                    dataBase.Math.CollegeCourses.get(coursetemp-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp5 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp5-1),dataBase.students.get(i),dataBase);
                                if (dataBase.Math.CollegeCourses.get(temp5-1).courseCapacity>dataBase.Math.CollegeCourses.get(temp5-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Math.CollegeCourses.get(temp5-1).courseType.equals(CourseType.General))
                                        if (dataBase.students.get(i).numVahedGeneral + dataBase.Math.CollegeCourses.get(temp5-1).courseSize>5)
                                            init(dataBase);
                                    if (dataBase.students.get(i).numVahed + dataBase.Math.CollegeCourses.get(temp5-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Math.CollegeCourses.get(temp5-1).courseType.equals(CourseType.General))
                                        dataBase.students.get(i).numVahedGeneral += dataBase.Math.CollegeCourses.get(temp5-1).courseSize;
                                    dataBase.students.get(i).StudentCourses.add(dataBase.Math.CollegeCourses.get(temp5-1));
                                    dataBase.students.get(i).numVahed += dataBase.Math.CollegeCourses.get(temp5-1).courseSize;
                                    dataBase.Math.CollegeCourses.get(temp5-1).courseCurrentCapacity++;
                                }
                                //newStudent.StudentCourses.get(0).printInfo();;
                                break;
                            case 2:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp2 = scanner.nextInt();
                                if (coursetemp2<dataBase.Physics.CollegeCourses.size())
                                {
                                    dataBase.Physics.CollegeCourses.get(coursetemp2-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp6 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp6-1),dataBase.students.get(i),dataBase);
                                if (dataBase.Physics.CollegeCourses.get(temp6-1).courseCapacity>dataBase.Physics.CollegeCourses.get(temp6-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Physics.CollegeCourses.get(temp6-1).courseType.equals(CourseType.General))
                                        if (dataBase.students.get(i).numVahedGeneral + dataBase.Physics.CollegeCourses.get(temp6-1).courseSize>5)
                                            init(dataBase);
                                    if (dataBase.students.get(i).numVahed + dataBase.Physics.CollegeCourses.get(temp6-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Physics.CollegeCourses.get(temp6-1).courseType.equals(CourseType.General))
                                        dataBase.students.get(i).numVahedGeneral += dataBase.Physics.CollegeCourses.get(temp6-1).courseSize;
                                    dataBase.students.get(i).StudentCourses.add(dataBase.Physics.CollegeCourses.get(temp6-1));
                                    dataBase.students.get(i).numVahed += dataBase.Physics.CollegeCourses.get(temp6-1).courseSize;
                                    dataBase.Physics.CollegeCourses.get(temp6-1).courseCurrentCapacity++;
                                }
                                break;
                            case 3:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp3 = scanner.nextInt();
                                if (coursetemp3<dataBase.Chemistry.CollegeCourses.size())
                                {
                                    dataBase.Chemistry.CollegeCourses.get(coursetemp3-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp7 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp7-1),dataBase.students.get(i),dataBase);
                                if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCapacity>dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseType.equals(CourseType.General))
                                        if (dataBase.students.get(i).numVahedGeneral + dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize>5)
                                            init(dataBase);
                                    if (dataBase.students.get(i).numVahed + dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseType.equals(CourseType.General))
                                        dataBase.students.get(i).numVahedGeneral += dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize;
                                    dataBase.students.get(i).StudentCourses.add(dataBase.Chemistry.CollegeCourses.get(temp7-1));
                                    dataBase.students.get(i).numVahed += dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize;
                                    dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCurrentCapacity++;
                                }
                                break;
                            case 4:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp4 = scanner.nextInt();
                                if (coursetemp4<dataBase.Law.CollegeCourses.size())
                                {
                                    dataBase.Law.CollegeCourses.get(coursetemp4-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp8 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp8-1),dataBase.students.get(i),dataBase);
                                if (dataBase.Law.CollegeCourses.get(temp8-1).courseCapacity>dataBase.Law.CollegeCourses.get(temp8-1).courseCurrentCapacity) {
                                    if (dataBase.Law.CollegeCourses.get(temp8 - 1).courseType.equals(CourseType.General))
                                        if (dataBase.students.get(i).numVahedGeneral + dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize > 5)
                                            init(dataBase);
                                    if (dataBase.students.get(i).numVahed + dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize > 20)
                                        init(dataBase);
                                    if (dataBase.Law.CollegeCourses.get(temp8 - 1).courseType.equals(CourseType.General))
                                        dataBase.students.get(i).numVahedGeneral += dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize;
                                    dataBase.students.get(i).StudentCourses.add(dataBase.Law.CollegeCourses.get(temp8 - 1));
                                    dataBase.students.get(i).numVahed += dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize;
                                    dataBase.Law.CollegeCourses.get(temp8 - 1).courseCurrentCapacity++;
                                }
                                break;
                            case 5:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp5 = scanner.nextInt();
                                if (coursetemp5<dataBase.Finance.CollegeCourses.size())
                                {
                                    dataBase.Finance.CollegeCourses.get(coursetemp5-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp9 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp9-1),dataBase.students.get(i),dataBase);
                                if (dataBase.Finance.CollegeCourses.get(temp9-1).courseCapacity>dataBase.Finance.CollegeCourses.get(temp9-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Finance.CollegeCourses.get(temp9-1).courseType.equals(CourseType.General))
                                        if (dataBase.students.get(i).numVahedGeneral + dataBase.Finance.CollegeCourses.get(temp9-1).courseSize>5)
                                            init(dataBase);
                                    if (dataBase.students.get(i).numVahed + dataBase.Finance.CollegeCourses.get(temp9-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Finance.CollegeCourses.get(temp9-1).courseType.equals(CourseType.General))
                                        dataBase.students.get(i).numVahedGeneral += dataBase.Finance.CollegeCourses.get(temp9-1).courseSize;
                                    dataBase.students.get(i).StudentCourses.add(dataBase.Finance.CollegeCourses.get(temp9-1));
                                    dataBase.students.get(i).numVahed += dataBase.Finance.CollegeCourses.get(temp9-1).courseSize;
                                    dataBase.Finance.CollegeCourses.get(temp9-1).courseCurrentCapacity++;
                                }
                                break;
                            default:
                                System.out.println(dataBase.Math.CollegeCourses.get(0).courseName);
                                init(dataBase);

                        }


                    }
                }
                else {
                    dataBase.Codes.add(code);
                    Student newStudent = new Student();
                    newStudent.studentCode = code;
                    dataBase.students.add(newStudent);
                    System.out.println("1- Registered Courses And Removal");
                    System.out.println("1- Registering Courses");
                    System.out.println("3- Back to First Page");
                    int temp = scanner.nextInt();
                    if (temp==1)
                    {
                        if (newStudent.StudentCourses.isEmpty())
                        {
                            System.out.println("you don't have any courses");
                            init(dataBase);
                        }

                        int numRem = scanner.nextInt();
                        newStudent.StudentCourses.remove(numRem-1);
                    }
                    else if (temp==2)
                    {
                        System.out.print("1:Math: ");
                        for(int i = 0; i < dataBase.Math.CollegeCourses.size(); i++) {
                            System.out.print(dataBase.Math.CollegeCourses.get(i).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("2:Physics: ");
                        for(int i = 0; i < dataBase.Physics.CollegeCourses.size(); i++) {
                            System.out.print(dataBase.Physics.CollegeCourses.get(i).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("3:Chemistry: ");
                        for(int i = 0; i < dataBase.Chemistry.CollegeCourses.size(); i++) {
                            System.out.print(dataBase.Chemistry.CollegeCourses.get(i).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("4:Law: ");
                        for(int i = 0; i < dataBase.Law.CollegeCourses.size(); i++) {
                            System.out.print(dataBase.Law.CollegeCourses.get(i).courseName+" ");
                        }
                        System.out.println();
                        System.out.print("5:Finance: ");
                        for(int i = 0; i < dataBase.Finance.CollegeCourses.size(); i++) {
                            System.out.print(dataBase.Finance.CollegeCourses.get(i).courseName+" ");
                        }
                        System.out.println();
                        System.out.println("Choose the College by number");
                        int collegetem = scanner.nextInt();
                        switch (collegetem){
                            case 1:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp = scanner.nextInt();
                                if (coursetemp<dataBase.Math.CollegeCourses.size())
                                {
                                    dataBase.Math.CollegeCourses.get(coursetemp-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp5 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp5-1),newStudent,dataBase);
                                if (dataBase.Math.CollegeCourses.get(temp5-1).courseCapacity>dataBase.Math.CollegeCourses.get(temp5-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Math.CollegeCourses.get(temp5-1).courseType.equals(CourseType.General))
                                        if (newStudent.numVahedGeneral + dataBase.Math.CollegeCourses.get(temp5-1).courseSize>5)
                                            init(dataBase);
                                    if (newStudent.numVahed + dataBase.Math.CollegeCourses.get(temp5-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Math.CollegeCourses.get(temp5-1).courseType.equals(CourseType.General))
                                        newStudent.numVahedGeneral += dataBase.Math.CollegeCourses.get(temp5-1).courseSize;
                                    newStudent.StudentCourses.add(dataBase.Math.CollegeCourses.get(temp5-1));
                                    newStudent.numVahed += dataBase.Math.CollegeCourses.get(temp5-1).courseSize;
                                    dataBase.Math.CollegeCourses.get(temp5-1).courseCurrentCapacity++;
                                }
                                //newStudent.StudentCourses.get(0).printInfo();;
                                break;
                            case 2:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp2 = scanner.nextInt();
                                if (coursetemp2<dataBase.Physics.CollegeCourses.size())
                                {
                                    dataBase.Physics.CollegeCourses.get(coursetemp2-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp6 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp6-1),newStudent,dataBase);
                                if (dataBase.Physics.CollegeCourses.get(temp6-1).courseCapacity>dataBase.Physics.CollegeCourses.get(temp6-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Physics.CollegeCourses.get(temp6-1).courseType.equals(CourseType.General))
                                        if (newStudent.numVahedGeneral + dataBase.Physics.CollegeCourses.get(temp6-1).courseSize>5)
                                            init(dataBase);
                                    if (newStudent.numVahed + dataBase.Physics.CollegeCourses.get(temp6-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Physics.CollegeCourses.get(temp6-1).courseType.equals(CourseType.General))
                                        newStudent.numVahedGeneral += dataBase.Physics.CollegeCourses.get(temp6-1).courseSize;
                                    newStudent.StudentCourses.add(dataBase.Physics.CollegeCourses.get(temp6-1));
                                    newStudent.numVahed += dataBase.Physics.CollegeCourses.get(temp6-1).courseSize;
                                    dataBase.Physics.CollegeCourses.get(temp6-1).courseCurrentCapacity++;
                                }
                                break;
                            case 3:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp3 = scanner.nextInt();
                                if (coursetemp3<dataBase.Chemistry.CollegeCourses.size())
                                {
                                    dataBase.Chemistry.CollegeCourses.get(coursetemp3-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp7 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp7-1),newStudent,dataBase);
                                if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCapacity>dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseType.equals(CourseType.General))
                                        if (newStudent.numVahedGeneral + dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize>5)
                                            init(dataBase);
                                    if (newStudent.numVahed + dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Chemistry.CollegeCourses.get(temp7-1).courseType.equals(CourseType.General))
                                        newStudent.numVahedGeneral += dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize;
                                    newStudent.StudentCourses.add(dataBase.Chemistry.CollegeCourses.get(temp7-1));
                                    newStudent.numVahed += dataBase.Chemistry.CollegeCourses.get(temp7-1).courseSize;
                                    dataBase.Chemistry.CollegeCourses.get(temp7-1).courseCurrentCapacity++;
                                }
                                break;
                            case 4:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp4 = scanner.nextInt();
                                if (coursetemp4<dataBase.Law.CollegeCourses.size())
                                {
                                    dataBase.Law.CollegeCourses.get(coursetemp4-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp8 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp8-1),newStudent,dataBase);
                                if (dataBase.Law.CollegeCourses.get(temp8-1).courseCapacity>dataBase.Law.CollegeCourses.get(temp8-1).courseCurrentCapacity) {
                                    if (dataBase.Law.CollegeCourses.get(temp8 - 1).courseType.equals(CourseType.General))
                                        if (newStudent.numVahedGeneral + dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize > 5)
                                            init(dataBase);
                                    if (newStudent.numVahed + dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize > 20)
                                        init(dataBase);
                                    if (dataBase.Law.CollegeCourses.get(temp8 - 1).courseType.equals(CourseType.General))
                                        newStudent.numVahedGeneral += dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize;
                                    newStudent.StudentCourses.add(dataBase.Law.CollegeCourses.get(temp8 - 1));
                                    newStudent.numVahed += dataBase.Law.CollegeCourses.get(temp8 - 1).courseSize;
                                    dataBase.Law.CollegeCourses.get(temp8 - 1).courseCurrentCapacity++;
                                }
                                break;
                            case 5:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp5 = scanner.nextInt();
                                if (coursetemp5<dataBase.Finance.CollegeCourses.size())
                                {
                                    dataBase.Finance.CollegeCourses.get(coursetemp5-1).printInfo();
                                }
                                System.out.println("input the index of the course if you want to take it");
                                int temp9 = scanner.nextInt();
                                checkTimeInterference(dataBase.Physics.CollegeCourses.get(temp9-1),newStudent,dataBase);
                                if (dataBase.Finance.CollegeCourses.get(temp9-1).courseCapacity>dataBase.Finance.CollegeCourses.get(temp9-1).courseCurrentCapacity)
                                {
                                    if (dataBase.Finance.CollegeCourses.get(temp9-1).courseType.equals(CourseType.General))
                                        if (newStudent.numVahedGeneral + dataBase.Finance.CollegeCourses.get(temp9-1).courseSize>5)
                                            init(dataBase);
                                    if (newStudent.numVahed + dataBase.Finance.CollegeCourses.get(temp9-1).courseSize>20)
                                        init(dataBase);
                                    if (dataBase.Finance.CollegeCourses.get(temp9-1).courseType.equals(CourseType.General))
                                        newStudent.numVahedGeneral += dataBase.Finance.CollegeCourses.get(temp9-1).courseSize;
                                    newStudent.StudentCourses.add(dataBase.Finance.CollegeCourses.get(temp9-1));
                                    newStudent.numVahed += dataBase.Finance.CollegeCourses.get(temp9-1).courseSize;
                                    dataBase.Finance.CollegeCourses.get(temp9-1).courseCurrentCapacity++;
                                }
                                break;
                            default:
                                System.out.println(dataBase.Math.CollegeCourses.get(0).courseName);
                                init(dataBase);

                        }



                    }

                }
            }
            else if (input.equals("2")) {
                /*System.out.println("username:");
                String username = scanner.next();
                System.out.println("password");
                String password = scanner.next();
                //logic.createUser(username, password);
            } else if (input.equals("back")) {
                break;*/
            }

        }
    }

}
