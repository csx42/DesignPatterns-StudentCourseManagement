package studentCoursesMgmt.driver;

public interface StudentCourseInterface {
    void allocateCourse(Course requestedCourse);
    void calculateTotalSatisfactionRate();
    float getAverageSatisfactionRate();
    void setPreferredCourses(String[] courses);

}
