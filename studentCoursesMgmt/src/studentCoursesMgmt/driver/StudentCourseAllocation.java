package studentCoursesMgmt.driver;

public class StudentCourseAllocation implements StudentCourseInterface{

    private Student student;
    private CoursePreference[] preferredCourses;
    private CoursePreference[] allocatedCourses;
    private int noOfCoursesAllocated;
    private int noOfPreferredCourses;
    private int maximumCourseAllocation;
    private int totalSatisfactionRate;

    public StudentCourseAllocation(String id) {
        student = new Student(id);
        noOfCoursesAllocated = 0;
        noOfPreferredCourses = 9;
        maximumCourseAllocation = 3;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(String id, int noOfPreferredCourses) {
        student = new Student(id);
        this.noOfPreferredCourses = noOfPreferredCourses;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(String id, int maximumCourseAllocation, int noOfPreferredCourses) {
        student = new Student(id);
        this.maximumCourseAllocation = maximumCourseAllocation;
        this.noOfPreferredCourses = noOfPreferredCourses;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(String firstName, String lastName, String id, int noOfCoursesAllocated, int noOfPreferredCourses, int maximumCourseAllocation) {
        student = new Student(firstName,lastName,id);
        this.noOfCoursesAllocated = noOfCoursesAllocated;
        this.noOfPreferredCourses = noOfPreferredCourses;
        this.maximumCourseAllocation = maximumCourseAllocation;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public CoursePreference[] getPreference() {
        return preferredCourses;
    }

    public CoursePreference[] getAllocated() {
        return allocatedCourses;
    }

    public int getNoOfCoursesAllocated() {
        return noOfCoursesAllocated;
    }

    public void setNoOfCoursesAllocated(int noOfCoursesAllocated) {
        this.noOfCoursesAllocated = noOfCoursesAllocated;
    }

    public int getTotalSatisfactionRate() {
        return totalSatisfactionRate;
    }

    private CoursePreference getPreferredCourseObject(Course requestedCourse){
        for (CoursePreference preferredCourse : preferredCourses) {
            if (requestedCourse.getCourseName().equals(preferredCourse.getCourseName())) {
                return preferredCourse;
            }
        }
        return null;
    }

    public void allocateCourse(Course requestedCourse){
        if(noOfCoursesAllocated>=maximumCourseAllocation){
            //print error message;
        }
        allocatedCourses[noOfCoursesAllocated]= getPreferredCourseObject(requestedCourse);
        noOfCoursesAllocated+=1;

        calculateTotalSatisfactionRate();
    }

    public void calculateTotalSatisfactionRate(){
        for (CoursePreference allocatedCourse : allocatedCourses) {
            this.totalSatisfactionRate += allocatedCourse.getPreference();
        }
    }

    public float getAverageSatisfactionRate() {
        return (float) totalSatisfactionRate/noOfCoursesAllocated;
    }

    public void setPreferredCourses(String[] courses){
        for(int i=0; i<courses.length; i++){
            preferredCourses[i]=new CoursePreference(new Course(courses[i]),i);
        }
    }

}
