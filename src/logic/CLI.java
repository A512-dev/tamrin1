
package logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CLI {
    public void init() {
        Scanner scanner = new Scanner(System.in);
        DataBase dataBase = new DataBase();
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
            } else if (input.equals("2")) {
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
