package studentCoursesMgmt.driver;

public class CoursePreference {
    private Course course;
    private int preference;

    public CoursePreference(Course requestedCourse, int preference) {
        course = requestedCourse;
        this.preference = preference;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getPreference(){
        return preference;
    }

    public void setPreference(int preference){
        this.preference = preference;
    }

    public String toString(){
        return course.getCourseName() + " " + preference;
    }
}
