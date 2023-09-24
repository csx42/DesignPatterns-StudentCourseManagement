package studentCoursesMgmt.driver;

public class AvailableCourses {

    private int noOfCoursesAvailable;
    private Course[] availableCourses;
    private int count;

    public AvailableCourses(int courseCount) {
        noOfCoursesAvailable = courseCount;
        availableCourses = new Course[noOfCoursesAvailable];
        count = 0;
    }

    public void addToAvailableCourse(String name, int time, int maxCapacity){
        availableCourses[count]= new Course(name,time,maxCapacity);
        count+=1;
    }

    public Course[] getAvailableCourses(){
        return availableCourses;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Available courses are: ");
        for(Course availableCourse : availableCourses){
            str.append(availableCourse.getCourseName());
        }

        return str.toString();
    }

}
