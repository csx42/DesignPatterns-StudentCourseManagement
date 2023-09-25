package studentCoursesMgmt.driver;

public class Course {

    private String courseName;
    private int classTime;
    private int maxCapacity;
    private int noOfFilledSeats;

    public Course(String name, int time, int capacity) {
        courseName = name;
        classTime = time;
        maxCapacity = capacity;
        noOfFilledSeats = 0;
    }

    //If time,maxCapacity is 0 - course has not been assigned time and maxCapacity yet.
    public Course(String name) {
        courseName = name;
        classTime = 0;
        maxCapacity = 0;
        noOfFilledSeats =0;
    }

    public Course(Course requestedCourse) {
        courseName = requestedCourse.getCourseName();
        classTime = requestedCourse.getTime();
        maxCapacity = requestedCourse.getMaxCapacity();
        noOfFilledSeats = requestedCourse.getNoOfFilledSeats();
    }

    public int getNoOfFilledSeats() {
        return noOfFilledSeats;
    }
    public void setCourseName(String Name) {
        courseName = Name;
    }
    public int getTime() {
        return classTime;
    }

    public void setTime(int time) {
        classTime = time;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public String getCourseName() {
        return courseName;
    }

    public void fillASeat(){
        noOfFilledSeats+=1;
    }

    public String toString(){
        return courseName + " " + classTime + " " + maxCapacity + " " + noOfFilledSeats;
    }

}
