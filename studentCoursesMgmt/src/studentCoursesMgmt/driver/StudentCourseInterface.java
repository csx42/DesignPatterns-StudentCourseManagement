package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;

import java.io.IOException;

public interface StudentCourseInterface {
    Course[] allocateCourses(Course[] availableCourseList);
    void calculateTotalSatisfactionRate();
    float getAverageSatisfactionRate();
    void setPreferredCourses(String[] courses);
    void assignCourse(Course requestedCourse);
    void printResults(FileDisplayInterface results) throws IOException;

}
