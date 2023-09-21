package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;

import java.io.IOException;

public class StudentCourseAllocation implements StudentCourseInterface{

    private Student student;
    private CoursePreference[] preferredCourses;
    private CoursePreference[] allocatedCourses;
    private int noOfCoursesAllocated;
    private int noOfPreferredCourses;
    private int maximumCourseAllocation;
    private int totalSatisfactionRate;

    public StudentCourseAllocation(int id) {
        student = new Student(id);
        noOfCoursesAllocated = 0;
        noOfPreferredCourses = 9;
        maximumCourseAllocation = 3;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(int id, int noOfPreferredCourses) {
        student = new Student(id);
        this.noOfPreferredCourses = noOfPreferredCourses;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(int id, int maximumCourseAllocation, int noOfPreferredCourses) {
        student = new Student(id);
        this.maximumCourseAllocation = maximumCourseAllocation;
        this.noOfPreferredCourses = noOfPreferredCourses;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
        preferredCourses = new CoursePreference[noOfPreferredCourses];
    }

    public StudentCourseAllocation(String firstName, String lastName, int id, int noOfCoursesAllocated, int noOfPreferredCourses, int maximumCourseAllocation) {
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
            if (requestedCourse.getCourseName().equals(preferredCourse.getCourse().getCourseName())) {
                return preferredCourse;
            }
        }
        return null;
    }

    public void assignCourse(Course requestedCourse){
        allocatedCourses[noOfCoursesAllocated]= getPreferredCourseObject(requestedCourse);
        noOfCoursesAllocated+=1;
    }

    public void calculateTotalSatisfactionRate(){
        for (CoursePreference allocatedCourse : allocatedCourses) {
            this.totalSatisfactionRate += allocatedCourse.getPreference();
        }
    }

    public float getAverageSatisfactionRate() {
        calculateTotalSatisfactionRate();
        return (float) totalSatisfactionRate/noOfCoursesAllocated;
    }

    public void setPreferredCourses(String[] courses){
        for(int i=0; i<courses.length; i++){
            preferredCourses[i]=new CoursePreference(new Course(courses[i]),noOfPreferredCourses-i);
        }
    }

    public Course[] allocateCourses(Course[] availableCourseList){
        int[] times = new int[3];
        for (CoursePreference preferredCourse : preferredCourses) {
            if(noOfCoursesAllocated<maximumCourseAllocation){
                for(Course course : availableCourseList){
                    if (course.getCourseName().equals(preferredCourse.getCourse().getCourseName())
                        && course.getMaxCapacity()>course.getNoOfFilledSeats()
                        && timeConflict(times,course.getTime())){
                            times[noOfCoursesAllocated]=course.getTime();
                            assignCourse(course);
                            course.fillASeat();
                    }
                }
            }
            else{
                break;
            }
        }
        return availableCourseList;
    }

    public boolean timeConflict(int[] times, int currTime){
        for(int i=0; i<noOfCoursesAllocated; i++){
            if (currTime==times[i]){
                return false;
            }
        }
        return true;
    }

    public void printResults(FileDisplayInterface results) throws IOException {
        StringBuilder output = new StringBuilder(student.getId() + ":");
        for (int i = 0; i < noOfCoursesAllocated; i++) {
            if(i==noOfCoursesAllocated-1) {
                output.append(allocatedCourses[i].getCourse().getCourseName());
            }
            else{
                output.append(allocatedCourses[i].getCourse().getCourseName()+",");
            }
        }
        output.append("::" + getAverageSatisfactionRate()+"\n");
        results.printOutputToFile(output.toString());
    }

}
