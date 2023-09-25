package studentCoursesMgmt.driver;

import java.io.IOException;

public interface StudentCourseInterface {
    void allocateCourses() throws IOException;
    int calculateTotalSatisfactionRate();
    float getAverageSatisfactionRate();
    void setPreferredCourses(String[] courses, Course[] availableCourseList);
    void assignCourse(Course requestedCourse);
    void printResults(String filePath) throws IOException;
    public int timeConflict(int[] times, int currTime);
}
