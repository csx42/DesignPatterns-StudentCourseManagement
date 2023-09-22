package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;

import java.io.IOException;

public interface StudentCourseInterface {
    Course[] allocateCourses(Course[] availableCourseList) throws IOException;
    void calculateTotalSatisfactionRate();
    float getAverageSatisfactionRate();
    void setPreferredCourses(String[] courses);
    void assignCourse(Course requestedCourse);
    void printResults(String filePath) throws IOException;
    public int timeConflict(int[] times, int currTime);

}
