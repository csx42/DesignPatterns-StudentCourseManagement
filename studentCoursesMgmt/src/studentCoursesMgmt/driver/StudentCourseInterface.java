package studentCoursesMgmt.driver;

import java.io.IOException;

public interface StudentCourseInterface {
    void allocateCourses(String regConflictsPath, String errorLogFilePath,Results results) throws IOException;
    void setPreferredCourses(String[] courses, Course[] availableCourseList);
    void assignCourse(Course requestedCourse);
    public int timeConflict(int[] times, int currTime);
}
