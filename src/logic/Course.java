package logic;
public class Course {
    private String courseName;
    private String courseTeacher;
    private int courseCode;
    private int courseCapacity;
    private int courseSize;
    private String courseExamTime;
    private String courseTime;
    private CourseType courseType;
    private College collegeType;

    public Course(String courseTeacher, int courseCode, int courseCapacity, int courseSize, String courseExamTime, String courseTime, CourseType courseType, College collegeType) {
        this.courseTeacher = courseTeacher;
        this.courseCode = courseCode;
        this.courseCapacity = courseCapacity;
        this.courseSize = courseSize;
        this.courseExamTime = courseExamTime;
        this.courseTime = courseTime;
        this.courseType = courseType;
        this.collegeType = collegeType;
    }
}
