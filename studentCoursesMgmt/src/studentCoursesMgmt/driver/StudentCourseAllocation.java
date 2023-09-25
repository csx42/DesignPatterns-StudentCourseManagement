package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.Results;
import studentCoursesMgmt.util.StdoutDisplayInterface;
import java.io.IOException;

/**
 * This class allocate courses to a student according the preferences specified.
 * @author Spoorthi
 * @version 1.0
 * @since 2023-09-29
 */

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

    /**
     * @param requestedCourse
     * @return It returns preferred course object.
     */
    private CoursePreference getPreferredCourseObject(Course requestedCourse){
        for (CoursePreference preferredCourse : preferredCourses) {
            if (requestedCourse.equals(preferredCourse.getCourse())) {
                return preferredCourse;
            }
        }
        return null;
    }

    /**
     * This method assign a course to the student. It gets reference of the preferred course and assign it the allocated course.
     * @param requestedCourse - requested course object.
     */
    public void assignCourse(Course requestedCourse){
        allocatedCourses[noOfCoursesAllocated]= getPreferredCourseObject(requestedCourse);
        noOfCoursesAllocated+=1;
    }

    /**
     * This method calculates and returns total satisfaction rate.
     */
    public int calculateTotalSatisfactionRate(){
        for (CoursePreference allocatedCourse : allocatedCourses) {
            this.totalSatisfactionRate += allocatedCourse.getPreference();
        }
        return totalSatisfactionRate;
    }

    /**
     * This method calculates average satisfaction rate
     * @return float value of satisfaction rate or -1 if denominator is 0.
     */
    public float getAverageSatisfactionRate() {
        calculateTotalSatisfactionRate();
        try {
            return (float) totalSatisfactionRate/noOfCoursesAllocated;
        }
        catch (ArithmeticException e){
            System.err.println("divide by zero exception.");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * This method adds courses to the preferred courses. It does not create course object for each preference it gets the object from available
     * course and assigns it to the preferred course. The order of the course also represents the preference number.
     * @param courses list of preferred courses in string array
     * @param availableCourseList list of available courses
     */
    public void setPreferredCourses(String[] courses, Course[] availableCourseList){
        for(int i=0; i<courses.length; i++){
            for(Course course : availableCourseList){
                if(course.getCourseName().equals(courses[i]))
                    preferredCourses[i]=new CoursePreference(course,noOfPreferredCourses-i);
            }
        }
    }

    /**
     * This method allocates courses to the student based on first come first serve. It checks for time conflicts between the courses. It there is a time conflict
     * it adds a message in regConflicts.txt. If requested has no seats remaining it reports an error to the errorLog.txt.
     * @throws IOException throws io exception when files are not found.
     */
    public void allocateCourses() throws IOException {
        int[] times = new int[3];
        for (CoursePreference preferredCourse : preferredCourses) {
            if(noOfCoursesAllocated<maximumCourseAllocation){
                Course course = preferredCourse.getCourse();
                if (course.getMaxCapacity() > course.getNoOfFilledSeats()) {
                    int ifTimeConflict = timeConflict(times, course.getTime());
                    if (ifTimeConflict == -1) {
                        times[noOfCoursesAllocated] = course.getTime();
                        assignCourse(course);
                        course.fillASeat();
                    } else {
                        FileDisplayInterface print = new Results("/Users/spoorthisanjay/DP/regConflicts.txt");
                        print.getFileForWrite();
                        String message = "Student with id " + student.getId() + " cannot be allocated course " + preferredCourse.getCourse().getCourseName()
                                + " since it has a time conflict with " + allocatedCourses[ifTimeConflict].getCourse().getCourseName()
                                + " which is already been assigned.\n";
                        print.printOutputToFile(message);
                        print.closeFileWriter();
                    }
                }
                else{
                    FileDisplayInterface print = new Results("/Users/spoorthisanjay/DP/errorLog.txt");
                    print.getFileForWrite();
                    String message = "Student with id " + student.getId() +" cannot be allocated course "+ course.getCourseName()
                            + " as it is completely filled. \n";
                    print.printOutputToFile(message);
                    print.closeFileWriter();

                    StdoutDisplayInterface stdout = new Results();
                    stdout.printOutputToStdout(message);
                }
            }
        }
    }

    /**
     * This method checks whether a course has time conflict with already allocated courses.
     * @param times list of time slots of already allocated courses
     * @param currTime time slot of the course yet to assign
     * @return a boolean value representing the index of the course with which there is a time conflict or -1 if there is no time conflict.
     */
    public int timeConflict(int[] times, int currTime){
        for(int i=0; i<noOfCoursesAllocated; i++){
            if (currTime==times[i]){
                return i;
            }
        }
        return -1;
    }

    /**
     * This method prints the results to the output file.
     * @param outputFile output file path
     * @throws IOException throws io exception if file is not found in the specified location.
     */
    public void printResults(String outputFile) throws IOException {
        String output = this.toString();

        FileDisplayInterface print = new Results(outputFile);
        print.getFileForWrite();
        print.printOutputToFile(output);
        print.closeFileWriter();

        StdoutDisplayInterface stdout = new Results();
        stdout.printOutputToStdout(output);
    }

    /**
     * @return the string representation of the allocated courses. In this case this string has the format required for the output.
     */
    public String toString(){
        StringBuilder output = new StringBuilder(student.getId() + ":");
        for (int i = 0; i < noOfCoursesAllocated; i++) {
            if(i==noOfCoursesAllocated-1) {
                output.append(allocatedCourses[i].getCourse().getCourseName());
            }
            else{
                output.append(allocatedCourses[i].getCourse().getCourseName()).append(",");
            }
        }
        output.append("::").append(getAverageSatisfactionRate()).append("\n");
        return output.toString();
    }

}
