package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.Results;
import studentCoursesMgmt.util.StdoutDisplayInterface;
import java.io.IOException;
import java.text.DecimalFormat;  

/**
 * This class allocate courses to a student according the preferences specified.
 * @author Spoorthi
 * @version 1.0
 * @since 2023-09-29
 */

public class StudentCourseAllocation implements StudentCourseInterface{

    private Student student;
    private CoursePreference[] preferredCourses;
    private int noOfPreferredCourses;
    private Results results;

    public StudentCourseAllocation(int id, Results result) {
        student = new Student(id);
        noOfPreferredCourses = 9;
        preferredCourses = new CoursePreference[noOfPreferredCourses];
        results = result;
    }

    public StudentCourseAllocation(int id, Results result, int numberOfPreferredCourses) {
        student = new Student(id);
        noOfPreferredCourses = numberOfPreferredCourses;
        preferredCourses = new CoursePreference[noOfPreferredCourses];
        results = result;
    }

    public CoursePreference[] getPreference() {
        return preferredCourses;
    }

    public int getStudentId(){
        return student.getId();
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
        results.assignCourse(getPreferredCourseObject(requestedCourse));
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
    public void allocateCourses(String regConflictsFilepath, String errorLogsFilePath) throws IOException {
        int[] times = new int[3];
        for (CoursePreference preferredCourse : preferredCourses) {
            if(results.getNoOfCoursesAllocated()<results.getMaximumCourseAllocation()){
                Course course = preferredCourse.getCourse();
                if (course.getMaxCapacity() > course.getNoOfFilledSeats()) {
                    int ifTimeConflict = timeConflict(times, course.getTime());
                    if (ifTimeConflict == -1) {
                        times[results.getNoOfCoursesAllocated()] = course.getTime();
                        assignCourse(course);
                        course.fillASeat();
                    } else {
                        String message = "Student with id " + student.getId() + " cannot be allocated course " + preferredCourse.getCourse().getCourseName()
                                + " since it has a time conflict with " + results.getAllocatedCourseName(ifTimeConflict)
                                + " which is already been assigned.\n";
                        results.printRegConflictsToFile(message);
                    }
                }
                else{
                    String message = "Student with id " + student.getId() +" cannot be allocated course "+ course.getCourseName()
                            + " as it is completely filled. \n";
                    results.printErrorMessage(message);
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
        for(int i=0; i<results.getNoOfCoursesAllocated(); i++){
            if (currTime==times[i]){
                return i;
            }
        }
        return -1;
    }



}
