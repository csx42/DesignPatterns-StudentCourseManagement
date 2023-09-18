package studentCoursesMgmt.driver;

public interface StudentCourseInterface {
    Course[] allocateCourses(Course[] availableCourseList);
    void calculateTotalSatisfactionRate();
    float getAverageSatisfactionRate();
    void setPreferredCourses(String[] courses);
    void assignCourse(Course requestedCourse);
    void printResults();

}
