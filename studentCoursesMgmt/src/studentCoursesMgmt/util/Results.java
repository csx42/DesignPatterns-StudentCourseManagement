package studentCoursesMgmt.util;

import studentCoursesMgmt.driver.CoursePreference;
import java.io.IOException;
import java.text.DecimalFormat;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    FileProcessorInterface fileProcessor1;
    FileProcessorInterface fileProcessor2;
    FileProcessorInterface fileProcessor3;
    private CoursePreference[] allocatedCourses;
    private int noOfCoursesAllocated;
    private int maximumCourseAllocation;


    public Results(String result, String conflicts, String error){
        fileProcessor1 = new FileProcessor(result);
        fileProcessor2 = new FileProcessor(conflicts);
        fileProcessor3 = new FileProcessor(error);
        noOfCoursesAllocated = 0;
        maximumCourseAllocation = 3;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
    }

    public Results(String result, String conflicts, String error, int maximum) {
        fileProcessor1 = new FileProcessor(result);
        fileProcessor2 = new FileProcessor(conflicts);
        fileProcessor3 = new FileProcessor(error);
        noOfCoursesAllocated = 0;
        maximumCourseAllocation = maximum;
        allocatedCourses = new CoursePreference[maximumCourseAllocation];
    }

    public CoursePreference[] getAllocatedCourses() {
        return allocatedCourses;
    }

    public void setAllocatedCourses(CoursePreference[] allocatedCourses) {
        this.allocatedCourses = allocatedCourses;
    }

    public int getMaximumCourseAllocation() {
        return maximumCourseAllocation;
    }

    public void setMaximumCourseAllocation(int maximumCourseAllocation) {
        this.maximumCourseAllocation = maximumCourseAllocation;
    }

    public void assignCourse(CoursePreference coursePreference){
        allocatedCourses[noOfCoursesAllocated]= coursePreference;
        noOfCoursesAllocated+=1;
    }

    public int getNoOfCoursesAllocated() {
        return noOfCoursesAllocated;
    }

    /**
     * This method calculates and returns total satisfaction rate.
     */
    public int calculateTotalSatisfactionRate(){
        int totalSatisfactionRate = 0;
        for (CoursePreference allocatedCourse : allocatedCourses) {
            totalSatisfactionRate += allocatedCourse.getPreference();
        }
        return totalSatisfactionRate;
    }

    /**
     * This method calculates average satisfaction rate
     * @return float value of satisfaction rate or -1 if denominator is 0.
     */
    public float getAverageSatisfactionRate() {
        DecimalFormat decfor = new DecimalFormat("0.00");
        int totalSatisfactionRate = calculateTotalSatisfactionRate();
        try {
            return Float.valueOf(decfor.format((float)totalSatisfactionRate/noOfCoursesAllocated));
        }
        catch (ArithmeticException e){
            System.err.println("divide by zero exception.");
            e.printStackTrace();
        }
        return -1;
    }

    public String getAllocatedCourseName(int index){
        return allocatedCourses[index].getCourse().getCourseName();
    }

    public void printErrorMessage(String message) throws IOException {
        printErrorMessageToFile(message);
        printErrorMessageToStdError(message);
    }

    public void printErrorMessageToFile(String message) throws IOException{
        fileProcessor3.getFileForWrite();
        fileProcessor3.writeToFile(message);
        fileProcessor3.closeFile();
    }

    public void printErrorMessageToStdError(String message){
        System.err.print(message);
    }

    public void printRegConflicts(String message) throws IOException {
        printErrorMessageToFile(message);
        printErrorMessageToStdError(message);
    }

    public void printRegConflictsToFile(String message) throws IOException{
        fileProcessor2.getFileForWrite();
        fileProcessor2.writeToFile(message);
        fileProcessor2.closeFile();
    }

    public void printRegConflictsToStdOut(String message){
        System.out.print(message);
    }

    public void printResultsToFile(String message) throws IOException{
        fileProcessor3.getFileForWrite();
        fileProcessor3.writeToFile(message);
        fileProcessor3.closeFile();
    }

    public void printResultsToStdOut(String message){
        System.out.print(message);
    }

    /**
     * This method prints the results to the output file.
     * @throws IOException throws io exception if file is not found in the specified location.
     */
    public void printResults(int id) throws IOException {
        String output = this.toString(id);
        printResultsToFile(output);
        printResultsToStdOut(output);
    }

    /**
     * @return the string representation of the allocated courses. In this case this string has the format required for the output.
     */
    public String toString(int id){
        StringBuilder output = new StringBuilder(id + ":");
        for (int i = 0; i < noOfCoursesAllocated; i++) {
            if(i==noOfCoursesAllocated-1) {
                output.append(allocatedCourses[i].getCourse().getCourseName());
            }
            else{
                output.append(allocatedCourses[i].getCourse().getCourseName()).append(",");
            }
        }
        output.append("::SatisfactionRating=").append(getAverageSatisfactionRate()).append("\n");
        return output.toString();
    }

}
