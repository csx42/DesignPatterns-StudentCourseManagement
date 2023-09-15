package studentCoursesMgmt.driver;

public class Course implements CourseInterface{

    private String CourseName;
    private int Time;
    private int maxCapacity;

    public Course(String courseName, int time, int maxCapacity) {
        CourseName = courseName;
        Time = time;
        this.maxCapacity = maxCapacity;
    }

    //If time,maxCapacity is 0 - course has not been assigned time and maxCapacity yet.
    public Course(String courseName) {
        CourseName = courseName;
        Time = 0;
        maxCapacity = 0;
    }

    public Course(Course requestedCourse) {
        CourseName = requestedCourse.getCourseName();
        Time = requestedCourse.getTime();
        maxCapacity = requestedCourse.getMaxCapacity();
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getCourseName() {
        return CourseName;
    }


}
