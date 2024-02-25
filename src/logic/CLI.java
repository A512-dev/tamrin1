
package logic;
import com.company.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
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
                    while (i<dataBase.getStudents().size() && dataBase.getStudents().get(i).studentCode!=code)
                        i++;
                    System.out.println("1- Registered Courses And Removal");
                    System.out.println("1- Registering Courses");
                    System.out.println("3- Back to First Page");
                    int temp = scanner.nextInt();
                    if (temp==1)
                    {
                        System.out.println(Arrays.toString(dataBase.getStudents().get(i).StudentCourses.toArray()));
                        int numRem = scanner.nextInt();
                        dataBase.getStudents().get(i).StudentCourses.remove(i-1);
                    }
                    else if (temp==2)
                    {
                        System.out.println("Math:"+Arrays.toString(dataBase.mathCourses.toArray()));
                        System.out.println("Physics:"+Arrays.toString(dataBase.physicsCourses.toArray()));
                        System.out.println("Chemistry:"+Arrays.toString(dataBase.chemistryCourses.toArray()));
                        System.out.println("Law:"+Arrays.toString(dataBase.lawCourses.toArray()));
                        System.out.println("Finance:"+Arrays.toString(dataBase.financeCourses.toArray()));
                        System.out.println("Choose the College");
                        String collegetem = scanner.nextLine();


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
                        System.out.println(newStudent.StudentCourses);
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

                                break;
                            case 2:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp2 = scanner.nextInt();
                                if (coursetemp2<dataBase.Physics.CollegeCourses.size())
                                {
                                    dataBase.Physics.CollegeCourses.get(coursetemp2-1).printInfo();
                                }
                                break;
                            case 3:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp3 = scanner.nextInt();
                                if (coursetemp3<dataBase.Chemistry.CollegeCourses.size())
                                {
                                    dataBase.Chemistry.CollegeCourses.get(coursetemp3-1).printInfo();
                                }
                                break;
                            case 4:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp4 = scanner.nextInt();
                                if (coursetemp4<dataBase.Law.CollegeCourses.size())
                                {
                                    dataBase.Law.CollegeCourses.get(coursetemp4-1).printInfo();
                                }
                                break;
                            case 5:
                                System.out.println("Choose which course you want to see information about by number");
                                int coursetemp5 = scanner.nextInt();
                                if (coursetemp5<dataBase.Finance.CollegeCourses.size())
                                {
                                    dataBase.Finance.CollegeCourses.get(coursetemp5-1).printInfo();
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
