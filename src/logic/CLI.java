
package logic;

import java.util.Scanner;

public class CLI {
    public boolean checkTimeInterference(Course course, Student student, DataBase dataBase) {
        for (int i = 0; i < student.StudentCourses.size(); i++) {
            if (student.StudentCourses.get(i).courseExamTime.equals(course.courseExamTime) ||
                    student.StudentCourses.get(i).courseTime.equals(course.courseTime)) {
                System.out.println("the course you're trying to take has time interference");
                return true;
            }
        }
        return false;

    }


    public void init(DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1- Student");
            System.out.println("2- Admin");
            System.out.println("3- end");
            String input = scanner.next();
            switch (input) {
                case "1" -> {
                    System.out.println("Enter Your Student Code");
                    String codeString = scanner.next();
                    if (codeString.length()>9 || !isNumeric(codeString))
                    {
                        System.out.println("code wasn't viable");
                        init(dataBase);
                    }
                    int code = Integer.parseInt(codeString);
                    if (!dataBase.studentsCodes.contains(code)) {
                        dataBase.studentsCodes.add(code);
                        Student newStudent = new Student();
                        newStudent.studentCode = code;
                        dataBase.students.add(newStudent);
                    }
                    int i = 0;
                    while (i < dataBase.students.size() && dataBase.students.get(i).studentCode != code)
                        i++;
                    studentOptions(dataBase, i, scanner);
                }
                case "2" -> adminHomePage(dataBase, scanner);
                case "3", "end" -> System.exit(0);
                default -> {
                    System.out.println("choose between options");
                    init(dataBase);
                }
            }
        }
    }

    private void adminHomePage(DataBase dataBase, Scanner scanner) {
        System.out.println("1:See the courses and implement changes");
        System.out.println("2:see the info about a specific course and add or remove students");
        System.out.println("3:exit");
        String t = scanner.next();
        switch (t) {
            case "1" -> adminChangesCourses(dataBase, scanner);
            case "2" -> adminOption2(dataBase, scanner);
            case "3" -> init(dataBase);
            default -> {
                System.out.println("enter between 1-2-3");
                adminHomePage(dataBase, scanner);
            }
        }
    }

    private void adminChangesCourses(DataBase dataBase, Scanner scanner) {
        printTheCourses(dataBase, "6");
        System.out.println("1:add course");
        System.out.println("2:remove a course");
        System.out.println("3:add capacity to a course");
        System.out.println("4:back");
        System.out.println("5:exit");
        String temp5String = scanner.next();
        if (temp5String.equals("4") || temp5String.equals("back"))
            adminHomePage(dataBase, scanner);
        else if (temp5String.equals("5") || temp5String.equals("exit"))
            init(dataBase);
        if (!isNumeric(temp5String)) {
            System.out.println("choose between 1-2-3-4-5");
            adminChangesCourses(dataBase, scanner);
        }
        int temp5 = Integer.parseInt(temp5String);
        switch (temp5) {
            case 1 -> adminAddsCourse(dataBase, scanner);
            case 2 -> adminRemovesCourse(dataBase, scanner);
            case 3 -> adminAddsCapacity(dataBase, scanner);
            default -> {
                System.out.println("choose between 1-2-3-4-5");
                adminChangesCourses(dataBase, scanner);
            }
        }
    }

    private void adminOption2(DataBase dataBase, Scanner scanner) {
        System.out.println("1:see the courses");
        System.out.println("Enter the code of the course or write back or exit");
        String codeString = scanner.next();
        if (codeString.equals("back"))
            adminHomePage(dataBase, scanner);
        ExitString(dataBase, codeString);
        if (!isNumeric(codeString)) {
            System.out.println("write a viable code");
            adminOption2(dataBase, scanner);
        }
        int code = Integer.parseInt(codeString);
        if (code == 1) {
            printTheCourses(dataBase, "6");
            System.out.println("enter the code");
            code = scanner.nextInt();
        } else if (!dataBase.coursesCodes.contains(code)) {
            System.out.println("there is no course with that code");
            adminOption2(dataBase, scanner);
        }
        int[] info = findInfoOfCourse(dataBase, code);
        adminChangesStudentsOfACourse(dataBase, info[0], info[1], scanner);
    }

    private void adminChangesStudentsOfACourse(DataBase dataBase, int college, int i, Scanner scanner) {
        if (college == 1) {
            if (dataBase.Math.CollegeCourses.get(i).studentsOfCourse.size() == 0) {
                System.out.println("the course doesn't have any students");
            }
            for (int j = 0; j < dataBase.Math.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
                dataBase.Math.CollegeCourses.get(i).studentsOfCourse.get(j).printInfoStudents();
            }
            dataBase.Math.CollegeCourses.get(i).printInfo();
        }
        else if (college == 2) {
            if (dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.size() == 0) {
                System.out.println("the course doesn't have any students");
            }
            for (int j = 0; j < dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
                dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.get(j).printInfoStudents();
            }
            dataBase.Physics.CollegeCourses.get(i).printInfo();
        }
        else if (college == 3) {
            if (dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.size() == 0) {
                System.out.println("the course doesn't have any students");
            }
            for (int j = 0; j < dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
                dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.get(j).printInfoStudents();
            }
            dataBase.Chemistry.CollegeCourses.get(i).printInfo();
        }
        else if (college == 4) {
            if (dataBase.Law.CollegeCourses.get(i).studentsOfCourse.size() == 0) {
                System.out.println("the course doesn't have any students");
            }
            for (int j = 0; j < dataBase.Law.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
                dataBase.Law.CollegeCourses.get(i).studentsOfCourse.get(j).printInfoStudents();
            }
            dataBase.Law.CollegeCourses.get(i).printInfo();
        }
        else if (college == 5) {
            if (dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.size() == 0) {
                System.out.println("the course doesn't have any students");
            }
            for (int j = 0; j < dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
                dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.get(j).printInfoStudents();
            }
            dataBase.Finance.CollegeCourses.get(i).printInfo();
        }
        Course course = new Course("", "", 0, 0, 0, "", "", CourseType.Exclusive, dataBase.Math);
        switch (college) {
            case 1 -> course = dataBase.Math.CollegeCourses.get(i);
            case 2 -> course = dataBase.Physics.CollegeCourses.get(i);
            case 3 -> course = dataBase.Chemistry.CollegeCourses.get(i);
            case 4 -> course = dataBase.Law.CollegeCourses.get(i);
            case 5 -> course = dataBase.Finance.CollegeCourses.get(i);
            default -> adminHomePage(dataBase, scanner);
        }
        System.out.println("Enter the code of the student you want to add or remove or back or exit");
        String codeString = scanner.next();
        if (codeString.equals("back"))
            adminOption2(dataBase, scanner);
        else if (codeString.equals("exit"))
            init(dataBase);
        if (!isNumeric(codeString)) {
            System.out.println("enter a viable code");
            adminChangesStudentsOfACourse(dataBase, college, i, scanner);
        }
        int code = Integer.parseInt(codeString);
        System.out.println("1:add Student to Course");
        System.out.println("2:remove Student from Course");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> adminAddStudentToCourse(dataBase, code, course, scanner);
            case 2 -> adminRemovesStudentFromCourse(dataBase, code, course, scanner);
            default -> {
                System.out.println("number is wrong");
                adminHomePage(dataBase, scanner);
            }
        }
        adminHomePage(dataBase, scanner);
    }

    private void adminRemovesStudentFromCourse(DataBase dataBase, int code, Course course, Scanner scanner) {
        int i2 = 0;
        while (i2 < course.studentsOfCourse.size() && course.studentsOfCourse.get(i2).studentCode != code)
            i2++;
        if (i2 == course.studentsOfCourse.size()) {
            System.out.println("Student doesn't have this course");
            adminHomePage(dataBase, scanner);
        }
        course.studentsOfCourse.get(i2).numVahed -= course.courseSize;
        if (course.courseType == CourseType.General)
            course.studentsOfCourse.get(i2).numVahedGeneral -= course.courseSize;
        course.studentsOfCourse.get(i2).StudentCourses.remove(course);
        course.courseCurrentCapacity--;
        course.studentsOfCourse.remove(i2);
    }

    private void adminAddStudentToCourse(DataBase dataBase, int code, Course course, Scanner scanner) {
        Student newStudent = new Student();
        if (!dataBase.studentsCodes.contains(code)) {
            dataBase.studentsCodes.add(code);
            newStudent.studentCode = code;
            dataBase.students.add(newStudent);
        }
        int i = 0;
        while (i < dataBase.students.size() && dataBase.students.get(i).studentCode != code)
            i++;
        if (course.courseCurrentCapacity == course.courseCapacity) {
            System.out.println("course's capacity is full");
            adminHomePage(dataBase, scanner);
        } else if (dataBase.students.get(i).numVahed + course.courseSize > 20) {
            System.out.println("size of student's courses would pass 20");
            adminHomePage(dataBase, scanner);
        } else if (dataBase.students.get(i).numVahedGeneral + course.courseSize > 5) {
            System.out.println("size of student's general courses would pass 20");
            adminHomePage(dataBase, scanner);
        } else if ((dataBase.students.get(i).StudentCourses.contains(course))) {
            System.out.println("Student already has this course");
            adminHomePage(dataBase, scanner);
        } else {
            dataBase.students.get(i).StudentCourses.add(course);
            dataBase.students.get(i).numVahed += course.courseSize;
            if (dataBase.students.get(i).numVahedGeneral + course.courseSize > 5) {
                dataBase.students.get(i).numVahedGeneral += course.courseSize;
            }
            course.courseCurrentCapacity++;
            course.studentsOfCourse.add(dataBase.students.get(i));
            System.out.println("student added");
        }
    }


    private void adminAddsCapacity(DataBase dataBase, Scanner scanner) {
        System.out.println("1:see the courses");
        System.out.println("Enter the code of the course(or back or exit)");
        String codeChange = scanner.next();
        if (codeChange.equals("back"))
            adminChangesCourses(dataBase, scanner);
        else if (codeChange.equals("exit"))
            init(dataBase);
        else if (codeChange.equals("1")) {
            printTheCourses(dataBase, "6");
            adminAddsCapacity(dataBase, scanner);
        }
        else if (!isNumeric(codeChange)) {
            System.out.println("enter a viable code");
            adminAddsCapacity(dataBase, scanner);
        }
        int codeChangeNew = Integer.parseInt(codeChange);
        if (!dataBase.coursesCodes.contains(codeChangeNew)) {
            System.out.println("there is no course with that code");
            adminAddsCapacity(dataBase, scanner);
        }
        int[] info = findInfoOfCourse(dataBase, codeChangeNew);
        switch (info[0]) {
            case 1 -> addCapacityFromMaths(dataBase, info[1], scanner);
            case 2 -> addCapacityFromPhysics(dataBase, info[1], scanner);
            case 3 -> addCapacityFromChemistry(dataBase, info[1], scanner);
            case 4 -> addCapacityFromLaw(dataBase, info[1], scanner);
            case 5 -> addCapacityFromFinance(dataBase, info[1], scanner);
            default -> {
                System.out.println("The Code Isn't in the courses");
                adminAddsCapacity(dataBase, scanner);
            }
        }
        System.out.println("capacity changed");
        adminHomePage(dataBase, scanner);
    }
    private void addCapacityFromFinance(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("Current capacity:");
        System.out.println(dataBase.Finance.CollegeCourses.get(i).courseCapacity);
        System.out.println("number of seats taken:");
        System.out.println(dataBase.Finance.CollegeCourses.get(i).courseCurrentCapacity);
        System.out.println("Enter the new Capacity");
        int newCapacity = scanner.nextInt();
        dataBase.Finance.CollegeCourses.get(i).courseCapacity = newCapacity;
    }
    private void addCapacityFromLaw(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("Current capacity:");
        System.out.println(dataBase.Law.CollegeCourses.get(i).courseCapacity);
        System.out.println("number of seats taken:");
        System.out.println(dataBase.Law.CollegeCourses.get(i).courseCurrentCapacity);
        System.out.println("Enter the new Capacity");
        int newCapacity = scanner.nextInt();
        dataBase.Law.CollegeCourses.get(i).courseCapacity = newCapacity;
    }
    private void addCapacityFromChemistry(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("Current capacity:");
        System.out.println(dataBase.Chemistry.CollegeCourses.get(i).courseCapacity);
        System.out.println("number of seats taken:");
        System.out.println(dataBase.Chemistry.CollegeCourses.get(i).courseCurrentCapacity);
        System.out.println("Enter the new Capacity");
        int newCapacity = scanner.nextInt();
        dataBase.Chemistry.CollegeCourses.get(i).courseCapacity = newCapacity;
    }
    private void addCapacityFromPhysics(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("Current capacity:");
        System.out.println(dataBase.Physics.CollegeCourses.get(i).courseCapacity);
        System.out.println("number of seats taken:");
        System.out.println(dataBase.Physics.CollegeCourses.get(i).courseCurrentCapacity);
        System.out.println("Enter the new Capacity");
        int newCapacity = scanner.nextInt();
        dataBase.Physics.CollegeCourses.get(i).courseCapacity = newCapacity;
    }
    private void addCapacityFromMaths(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("Current capacity:");
        System.out.println(dataBase.Math.CollegeCourses.get(i).courseCapacity);
        System.out.println("number of seats taken:");
        System.out.println(dataBase.Math.CollegeCourses.get(i).courseCurrentCapacity);
        System.out.println("Enter the new Capacity");
        int newCapacity = scanner.nextInt();
        dataBase.Math.CollegeCourses.get(i).courseCapacity = newCapacity;
    }


    private void adminRemovesCourse(DataBase dataBase, Scanner scanner) {
        System.out.println("Enter the code of the course you want to remove(back or exit)");
        String codeRemove2 = scanner.next();
        if (codeRemove2.equals("back"))
            adminChangesCourses(dataBase, scanner);
        else if (codeRemove2.equals("exit"))
            init(dataBase);
        else if (!isNumeric(codeRemove2)) {
            System.out.println("Enter A number");
            adminRemovesCourse(dataBase, scanner);
        }
        int codeRemove = Integer.parseInt(codeRemove2);
        if (!dataBase.coursesCodes.contains(codeRemove)) {
            System.out.println("there is no course with that code");
            adminRemovesCourse(dataBase, scanner);
        }
        int[] info = findInfoOfCourse(dataBase, codeRemove);
        switch (info[0]) {
            case 1 -> removeCourseFromMaths(dataBase, info[1]);
            case 2 -> removeCourseFromPhysics(dataBase, info[1]);
            case 3 -> removeCourseFromChemistry(dataBase, info[1]);
            case 4 -> removeCourseFromLaw(dataBase, info[1]);
            case 5 -> removeCourseFromFinance(dataBase, info[1]);
            default -> {
                System.out.println("The Code Isn't in the courses");
                adminRemovesCourse(dataBase, scanner);
            }
        }
        System.out.println("course removed");

    }
    private void removeCourseFromMaths(DataBase dataBase, int i) {
        for (int j = 0; j < dataBase.Math.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
            dataBase.Math.CollegeCourses.get(i).studentsOfCourse.get(j).numVahed -= dataBase.Math.CollegeCourses.get(i).courseSize;
            if (dataBase.Math.CollegeCourses.get(i).courseType == CourseType.General)
                dataBase.Math.CollegeCourses.get(i).studentsOfCourse.get(j).numVahedGeneral -= dataBase.Math.CollegeCourses.get(i).courseSize;
            dataBase.Math.CollegeCourses.get(i).studentsOfCourse.get(j).StudentCourses.remove(dataBase.Math.CollegeCourses.get(i));
        }
        dataBase.Math.CollegeCourses.remove(dataBase.Math.CollegeCourses.get(i));
    }
    private void removeCourseFromPhysics(DataBase dataBase, int i) {
        for (int j = 0; j < dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
            dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.get(j).numVahed -= dataBase.Physics.CollegeCourses.get(i).courseSize;
            if (dataBase.Physics.CollegeCourses.get(i).courseType == CourseType.General)
                dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.get(j).numVahedGeneral -= dataBase.Physics.CollegeCourses.get(i).courseSize;
            dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.get(j).StudentCourses.remove(dataBase.Physics.CollegeCourses.get(i));
        }
        dataBase.Physics.CollegeCourses.remove(dataBase.Physics.CollegeCourses.get(i));
    }
    private void removeCourseFromChemistry(DataBase dataBase, int i) {
        for (int j = 0; j < dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
            dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.get(j).numVahed -= dataBase.Chemistry.CollegeCourses.get(i).courseSize;
            if (dataBase.Chemistry.CollegeCourses.get(i).courseType == CourseType.General)
                dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.get(j).numVahedGeneral -= dataBase.Chemistry.CollegeCourses.get(i).courseSize;
            dataBase.Chemistry.CollegeCourses.get(i).studentsOfCourse.get(j).StudentCourses.remove(dataBase.Chemistry.CollegeCourses.get(i));
        }
        dataBase.Chemistry.CollegeCourses.remove(dataBase.Chemistry.CollegeCourses.get(i));
    }
    private void removeCourseFromLaw(DataBase dataBase, int i) {
        for (int j = 0; j < dataBase.Physics.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
            dataBase.Law.CollegeCourses.get(i).studentsOfCourse.get(j).numVahed -= dataBase.Law.CollegeCourses.get(i).courseSize;
            if (dataBase.Law.CollegeCourses.get(i).courseType == CourseType.General)
                dataBase.Law.CollegeCourses.get(i).studentsOfCourse.get(j).numVahedGeneral -= dataBase.Law.CollegeCourses.get(i).courseSize;
            dataBase.Law.CollegeCourses.get(i).studentsOfCourse.get(j).StudentCourses.remove(dataBase.Law.CollegeCourses.get(i));
        }
        dataBase.Law.CollegeCourses.remove(dataBase.Law.CollegeCourses.get(i));
    }
    private void removeCourseFromFinance(DataBase dataBase, int i) {
        for (int j = 0; j < dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.size(); j++) {
            dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.get(j).numVahed -= dataBase.Finance.CollegeCourses.get(i).courseSize;
            if (dataBase.Finance.CollegeCourses.get(i).courseType == CourseType.General)
                dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.get(j).numVahedGeneral -= dataBase.Finance.CollegeCourses.get(i).courseSize;
            dataBase.Finance.CollegeCourses.get(i).studentsOfCourse.get(j).StudentCourses.remove(dataBase.Finance.CollegeCourses.get(i));
        }
        dataBase.Finance.CollegeCourses.remove(dataBase.Finance.CollegeCourses.get(i));
    }

    private int adminGivesCodeOfNewCourse(DataBase dataBase, Scanner scanner) {
        System.out.println("Enter the course's code");
        System.out.println("1:back or 2: exit");
        String code = scanner.next();
        //
        if (code.equals("back")|| code.equals("1"))
            adminChangesCourses(dataBase, scanner);
        if (code.equals("exit")|| code.equals("2"))
            init(dataBase);
        if (!isNumeric(code)) {
            System.out.println("write a code");
            adminGivesCodeOfNewCourse(dataBase, scanner);
        }
        int codeNew = Integer.parseInt(code);
        if (dataBase.coursesCodes.contains(codeNew)) {
            System.out.println("there is already a course with that code");
            adminGivesCodeOfNewCourse(dataBase, scanner);
        }
        return codeNew;
    }
    private String adminGivesCollegeOfNewCourse(DataBase dataBase,Scanner scanner) {
        System.out.println("Enter the college Of the course");
        System.out.println("1:Math " + "2:Physics " + "3:Chemistry " + "4:Law " + "5:Finance");
        System.out.println("6:back to previous step");
        System.out.println("7:go back to give the information again");
        System.out.println("8:exit");
        String college = scanner.next();
        //
        switch (college) {
            case "6" -> adminGivesCodeOfNewCourse(dataBase, scanner);
            case "7", "back" -> adminAddsCourse(dataBase, scanner);
            case "exit", "8" -> init(dataBase);
        }
        if (!isNumeric(college) || Integer.parseInt(college)>8 || Integer.parseInt(college)<1) {
            System.out.println("enter between 1-2-3-4-5-6-7-8");
            adminGivesCollegeOfNewCourse(dataBase, scanner);
        }
        return college;
    }
    private void checkNameInterference(DataBase dataBase,String nameNew,Scanner scanner) {
        for (int i=0; i<dataBase.Math.CollegeCourses.size(); i++) {
            if (dataBase.Math.CollegeCourses.get(i).courseName.equals(nameNew)) {
                System.out.println("name already is taken by another course");
                adminGivesNameOfNewCourse(dataBase,scanner);
            }
        }
        for (int i=0; i<dataBase.Physics.CollegeCourses.size(); i++) {
            if (dataBase.Physics.CollegeCourses.get(i).courseName.equals(nameNew)) {
                System.out.println("name already is taken by another course");
                adminGivesNameOfNewCourse(dataBase,scanner);
            }
        }
        for (int i=0; i<dataBase.Chemistry.CollegeCourses.size(); i++) {
            if (dataBase.Chemistry.CollegeCourses.get(i).courseName.equals(nameNew)) {
                System.out.println("name already is taken by another course");
                adminGivesNameOfNewCourse(dataBase,scanner);
            }
        }
        for (int i=0; i<dataBase.Law.CollegeCourses.size(); i++) {
            if (dataBase.Law.CollegeCourses.get(i).courseName.equals(nameNew)) {
                System.out.println("name already is taken by another course");
                adminGivesNameOfNewCourse(dataBase,scanner);
            }
        }
        for (int i=0; i<dataBase.Finance.CollegeCourses.size(); i++) {
            if (dataBase.Finance.CollegeCourses.get(i).courseName.equals(nameNew)) {
                System.out.println("name already is taken by another course");
                adminGivesNameOfNewCourse(dataBase,scanner);
            }
        }
    }
    private String adminGivesNameOfNewCourse(DataBase dataBase,Scanner scanner) {
        System.out.println("Enter the course's name or write back or exit");
        System.out.println("1:back one step");
        System.out.println("2:go back to give the information again");
        System.out.println("3:exit");
        String name = scanner.next();
        switch (name) {
            case "1" -> adminGivesCollegeOfNewCourse(dataBase, scanner);
            case "2", "back" -> adminAddsCourse(dataBase, scanner);
            case "3", "exit" -> init(dataBase);
        }
        checkNameInterference(dataBase,name,scanner);
        return name;
    }
    private String adminGivesNameTeacherOfNewCourse(DataBase dataBase,Scanner scanner) {
        System.out.println("write the course's teacher or");
        System.out.println("1:back one step");
        System.out.println("2:go back to give the information again");
        System.out.println("3:exit");
        String nameTeacher = scanner.next();
        //
        switch (nameTeacher) {
            case "1" -> adminGivesNameOfNewCourse(dataBase, scanner);
            case "back", "2" -> adminAddsCourse(dataBase, scanner);
            case "exit", "3" -> init(dataBase);
        }
        return nameTeacher;
    }
    private int adminGivesCapacityOfNewCourse(DataBase dataBase,Scanner scanner) {
        System.out.println("Enter the course's capacity or write back or exit");
        System.out.println("1:back one step");
        System.out.println("2:go back to give the information again");
        System.out.println("3:exit");
        String capacity = scanner.next();
        //
        if (capacity.equals("1"))
            adminGivesNameTeacherOfNewCourse(dataBase, scanner);
        else if (capacity.equals("back") || capacity.equals("2"))
            adminAddsCourse(dataBase, scanner);
        else if (capacity.equals("exit") || capacity.equals("3"))
            init(dataBase);
        else if (!isNumeric(capacity)) {
            System.out.println("write a number");
            adminGivesCapacityOfNewCourse(dataBase, scanner);
        }
        return Integer.parseInt(capacity);
    }
    private int adminGivesSizeOfNewCourse(DataBase dataBase,Scanner scanner) {
        System.out.println("Enter the course's size or write back or exit");
        System.out.println("1:back one step");
        System.out.println("2:go back to give the information again");
        System.out.println("3:exit");
        String size = scanner.next();
        //
        if (size.equals("1"))
            adminGivesCapacityOfNewCourse(dataBase, scanner);
        else if (size.equals("back") || size.equals("2"))
            adminAddsCourse(dataBase, scanner);
        else if (size.equals("exit") || size.equals("3"))
            init(dataBase);
        else if (!isNumeric(size)) {
            System.out.println("write a number");
            adminGivesSizeOfNewCourse(dataBase, scanner);
        }
        return Integer.parseInt(size);
    }
    private void adminAddsCourse(DataBase dataBase, Scanner scanner) {
        int codeNew = adminGivesCodeOfNewCourse(dataBase,scanner);
        //
        String college = adminGivesCollegeOfNewCourse(dataBase,scanner);
        //
        String name = adminGivesNameOfNewCourse(dataBase,scanner);
        //
        String nameTeacher = adminGivesNameTeacherOfNewCourse(dataBase,scanner);
        //
        int capacityNew = adminGivesCapacityOfNewCourse(dataBase,scanner);
        ///
        int sizeNew = adminGivesSizeOfNewCourse(dataBase,scanner);
        //
        System.out.println("Enter the course's ExamTime or write back or exit");
        String examTime = scanner.next();
        //
        if (examTime.equals("back"))
            adminAddsCourse(dataBase, scanner);
        else if (examTime.equals("exit"))
            init(dataBase);
        //////
        System.out.println("Enter the course's Time or write back or exit");
        String time = scanner.next();
        //
        if (time.equals("back"))
            adminAddsCourse(dataBase, scanner);
        else if (time.equals("exit"))
            init(dataBase);
        //
        System.out.println("Enter the course's type(Exclusive Or General) or write back or exit");
        String type = scanner.next();
        //
        if (type.equals("back"))
            adminAddsCourse(dataBase, scanner);
        else if (type.equals("exit"))
            init(dataBase);
        ////////
        CourseType typeNew;
        typeNew = CourseType.General;
        if (type.equals("Exclusive") || type.equals("exclusive"))
            typeNew = CourseType.Exclusive;
        else if (type.equals("General") || type.equals("general"))
            typeNew = CourseType.General;
        else {
            System.out.println("Types are Exclusive or General");
            adminAddsCourse(dataBase, scanner);
        }
        //

        //we have the information of the course
        switch (college) {
            case "1" -> {
                //college==math
                Course newCourse1 = new Course(name, nameTeacher, codeNew, capacityNew, sizeNew, examTime, time, typeNew, dataBase.Math);
                dataBase.Math.CollegeCourses.add(newCourse1);
                dataBase.coursesCodes.add(codeNew);
            }
            case "2" -> {
                Course newCourse2 = new Course(name, nameTeacher, codeNew, capacityNew, sizeNew, examTime, time, typeNew, dataBase.Physics);
                dataBase.Physics.CollegeCourses.add(newCourse2);
                dataBase.coursesCodes.add(codeNew);
            }
            case "3" -> {
                Course newCourse3 = new Course(name, nameTeacher, codeNew, capacityNew, sizeNew, examTime, time, typeNew, dataBase.Chemistry);
                dataBase.Chemistry.CollegeCourses.add(newCourse3);
                dataBase.coursesCodes.add(codeNew);
            }
            case "4" -> {
                Course newCourse4 = new Course(name, nameTeacher, codeNew, capacityNew, sizeNew, examTime, time, typeNew, dataBase.Law);
                dataBase.Law.CollegeCourses.add(newCourse4);
                dataBase.coursesCodes.add(codeNew);
            }
            case "5" -> {
                Course newCourse5 = new Course(name, nameTeacher, codeNew, capacityNew, sizeNew, examTime, time, typeNew, dataBase.Finance);
                dataBase.Finance.CollegeCourses.add(newCourse5);
                dataBase.coursesCodes.add(codeNew);
            }
            default -> {
                System.out.println("information had a problem");
                adminAddsCourse(dataBase, scanner);
            }
        }
    }



    private void studentOptions(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("1- Registered Courses And Removal");
        System.out.println("2- Registering Courses");
        System.out.println("3- exit");
        String temp2 = scanner.next();
        if (temp2.equals("exit") || temp2.equals("3"))
            init(dataBase);
        int temp = Integer.parseInt(temp2);
        if (temp == 1) {
            studentOption1(dataBase, i, scanner);
        } else if (temp == 2) {
            studentOption2(dataBase, i, scanner);
        } else if (temp == 3)
            studentOption3(dataBase);//exit
    }
    private void studentOption1(DataBase dataBase, int i, Scanner scanner) {
        if (dataBase.students.get(i).StudentCourses.isEmpty()) {
            System.out.println("You don't have any courses");
            studentOptions(dataBase, i, scanner);
        }
        for (int k = 0; k < dataBase.students.get(i).StudentCourses.size(); k++)
            dataBase.students.get(i).StudentCourses.get(k).printInfo();
        System.out.println("write the code of the course you want to remove or\n"+"1:back or 2:exit");
        String codeRem2 = scanner.next();
        if (codeRem2.equals("back") || codeRem2.equals("1"))
            studentOptions(dataBase, i, scanner);
        else if (codeRem2.equals("exit") || codeRem2.equals("2"))
            init(dataBase);
        else if (!isNumeric(codeRem2) || codeRem2.length()>9) {
            System.out.println("enter a viable code");
            studentOption1(dataBase, i, scanner);
        }
        int codeRem = Integer.parseInt(codeRem2);
        int i2 = 0;
        while (i2 < dataBase.students.get(i).StudentCourses.size() && dataBase.students.get(i).StudentCourses.get(i2).courseCode != codeRem)
            i2++;
        if (i2 == dataBase.students.get(i).StudentCourses.size()) {
            System.out.println("you don't have course with this code");
            studentOption1(dataBase, i, scanner);
        }

        //adjustments
        dataBase.students.get(i).numVahed -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
        if (dataBase.students.get(i).StudentCourses.get(i2).courseType.equals(CourseType.General))
            dataBase.students.get(i).numVahedGeneral -= dataBase.students.get(i).StudentCourses.get(i2).courseSize;
        dataBase.students.get(i).StudentCourses.get(i2).courseCurrentCapacity--;
        //adjustments

        dataBase.students.get(i).StudentCourses.remove(i2);
        System.out.println("course removed");
        //After Change
        if (dataBase.students.get(i).StudentCourses.isEmpty()) {
            System.out.println("Empty");
            studentOptions(dataBase, i, scanner);
        }
        studentOption1(dataBase, i, scanner);

    }
    private void studentOption2(DataBase dataBase, int i, Scanner scanner) {
        System.out.println("1:print Math Courses");
        System.out.println("2:print Physics Courses");
        System.out.println("3:print Chemistry Courses");
        System.out.println("4:print Law Courses");
        System.out.println("5:print Finance Courses");
        System.out.println("6:print all courses");
        System.out.println("or input the code of the course that you want to take");
        System.out.println("or write back or exit");

        String temp7 = scanner.next();
        if (temp7.equals("back"))
            studentOptions(dataBase,i, scanner);
        ExitString(dataBase,temp7);
        if (temp7.equals("1") || temp7.equals("2") || temp7.equals("3") || temp7.equals("4") || temp7.equals("5") || temp7.equals("6")) {
            printTheCourses(dataBase, temp7);
            System.out.println("///////////////////");
            System.out.println("input the code of the course that you want to take");
            System.out.println("or write back or exit");
            temp7 = scanner.next();
            if (temp7.equals("back"))
                studentOption2(dataBase, i, scanner);
            else if (temp7.equals("exit"))
                init(dataBase);
        }
        else if (!isNumeric(temp7)) {
            System.out.println("enter correctly");
            studentOption2(dataBase, i, scanner);
        }
        int temp5 = Integer.parseInt(temp7);
        int[] info = findInfoOfCourse(dataBase, temp5);
        if (info[0] == 0) {
            System.out.println("the code you entered doesn't belong to any course");
            studentOption2(dataBase, i, scanner);
        }
        else if (info[0] == 1) //college==Math, i= index of course
        {
            if (checkTimeInterference(dataBase.Math.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase))
                studentOption2(dataBase, i, scanner);
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
                //System.out.println(dataBase.Math.CollegeCourses.get(info[1]).courseCurrentCapacity+" "+dataBase.Math.CollegeCourses.get(info[1]).courseCode);
                dataBase.Math.CollegeCourses.get(info[1]).studentsOfCourse.add(dataBase.students.get(i));
            }
        }
        else if (info[0] == 2) //college==Math, i= index of course
        {
            if (checkTimeInterference(dataBase.Physics.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase))
                studentOption2(dataBase, i, scanner);
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
                dataBase.Physics.CollegeCourses.get(info[1]).studentsOfCourse.add(dataBase.students.get(i));
            }
        }
        else if (info[0] == 3) //college==Chemistry, i= index of course
        {
            if (checkTimeInterference(dataBase.Chemistry.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase))
                studentOptions(dataBase, i, scanner);
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
                dataBase.Chemistry.CollegeCourses.get(info[1]).studentsOfCourse.add(dataBase.students.get(i));
            }
        }
        else if (info[0] == 4) //college==Law, i= index of course
        {
            if (checkTimeInterference(dataBase.Law.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase))
                studentOptions(dataBase, i, scanner);
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
                dataBase.Law.CollegeCourses.get(info[1]).studentsOfCourse.add(dataBase.students.get(i));
            }
        }
        else if (info[0] == 5) //college==Finance, i= index of course
        {
            if (checkTimeInterference(dataBase.Finance.CollegeCourses.get(info[1]), dataBase.students.get(i), dataBase))
                studentOptions(dataBase, i, scanner);
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
                dataBase.Finance.CollegeCourses.get(info[1]).studentsOfCourse.add(dataBase.students.get(i));
            }
        }
        System.out.println("course addded");
        studentOptions(dataBase, i, scanner);
    }
    private void studentOption3(DataBase dataBase) {
        init(dataBase);
    }

    private int[] findInfoOfCourse(DataBase dataBase, int temp55) {
        int i = 0;
        int[] info = {0, 0};
        //info[0]=number of college
        //info[1]=index of course in said college
        while (i < dataBase.Math.CollegeCourses.size() && dataBase.Math.CollegeCourses.get(i).courseCode != temp55)
            i++;
        if (i < dataBase.Math.CollegeCourses.size()) {
            info[0] = 1;
            info[1] = i;
            return info;
        }
        i = 0;
        while (i < dataBase.Physics.CollegeCourses.size() && dataBase.Physics.CollegeCourses.get(i).courseCode != temp55)
            i++;
        if (i < dataBase.Physics.CollegeCourses.size()) {
            info[0] = 2;
            info[1] = i;
            return info;
        }
        i = 0;
        while (i < dataBase.Chemistry.CollegeCourses.size() && dataBase.Chemistry.CollegeCourses.get(i).courseCode != temp55)
            i++;
        if (i < dataBase.Chemistry.CollegeCourses.size()) {
            info[0] = 3;
            info[1] = i;
            return info;
        }
        i = 0;
        while (i < dataBase.Law.CollegeCourses.size() && dataBase.Law.CollegeCourses.get(i).courseCode != temp55)
            i++;
        if (i < dataBase.Law.CollegeCourses.size()) {
            info[0] = 4;
            info[1] = i;
            return info;
        }
        i = 0;
        while (i < dataBase.Finance.CollegeCourses.size() && dataBase.Finance.CollegeCourses.get(i).courseCode != temp55)
            i++;
        if (i < dataBase.Finance.CollegeCourses.size()) {
            info[0] = 5;
            info[1] = i;
            return info;
        }
        else
            return info;
    }


    private void ExitString(DataBase dataBase, String string) {
        if (string.equals("exit"))
            init(dataBase);
    }
    private void printTheCourses(DataBase dataBase, String inputCollege) {
        switch (inputCollege) {
            case "1" -> {
                System.out.println("Math:");
                for (int j = 0; j < dataBase.Math.CollegeCourses.size(); j++) {
                    dataBase.Math.CollegeCourses.get(j).printInfo();
                }
            }
            case "2" -> {
                System.out.println("Physics:");
                for (int j = 0; j < dataBase.Physics.CollegeCourses.size(); j++) {
                    dataBase.Physics.CollegeCourses.get(j).printInfo();
                }
            }
            case "3" -> {
                System.out.println("Chemistry:");
                for (int j = 0; j < dataBase.Chemistry.CollegeCourses.size(); j++) {
                    dataBase.Chemistry.CollegeCourses.get(j).printInfo();
                }
            }
            case "4" -> {
                System.out.println("Law:");
                for (int j = 0; j < dataBase.Law.CollegeCourses.size(); j++) {
                    dataBase.Law.CollegeCourses.get(j).printInfo();
                }
            }
            case "5" -> {
                System.out.println("Finance:");
                for (int j = 0; j < dataBase.Finance.CollegeCourses.size(); j++) {
                    dataBase.Finance.CollegeCourses.get(j).printInfo();
                }
            }
            case "6" -> {
                printTheCourses(dataBase, "1");
                printTheCourses(dataBase, "2");
                printTheCourses(dataBase, "3");
                printTheCourses(dataBase, "4");
                printTheCourses(dataBase, "5");
            }
        }

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}