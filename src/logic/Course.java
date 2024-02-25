package logic;
public class Course {
     String courseName;
     String courseTeacher;
     int courseCode;
     int courseCapacity;
     int courseSize;
     String courseExamTime;
     String courseTime;
     CourseType courseType;
     College collegeType;

    public Course(String Name,String courseTeacher, int courseCode, int courseCapacity, int courseSize, String courseExamTime, String courseTime, CourseType courseType, College collegeType) {
        this.courseTeacher = courseTeacher;
        this.courseName = Name;
        this.courseCode = courseCode;
        this.courseCapacity = courseCapacity;
        this.courseSize = courseSize;
        this.courseExamTime = courseExamTime;
        this.courseTime = courseTime;
        this.courseType = courseType;
        this.collegeType = collegeType;
    }
    public void printInfo()
    {
        System.out.println(this.courseName + " "+ this.courseTeacher+" "+ courseTime+" "+courseExamTime+" "+courseCapacity+" "+courseSize+" "+courseType+" "+courseCode);
    }
    //String information = this.courseName + " "+ this.courseTeacher+" "+ courseTime+" "+courseExamTime+" "+courseCapacity+" "+courseSize+" "+courseType+" "+courseCode;
}
