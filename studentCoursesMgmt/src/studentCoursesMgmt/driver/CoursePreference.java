package studentCoursesMgmt.driver;

public class CoursePreference extends Course{

    private int preference;

    public CoursePreference(Course requestedCourse, int preference) {
        super(requestedCourse);
        this.preference = preference;
    }

    public int getPreference(){
        return preference;
    }

    public void setPreference(int preference){
        this.preference = preference;
    }
}
