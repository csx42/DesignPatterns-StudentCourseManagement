package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.FileOutput;
import studentCoursesMgmt.util.StdoutDisplayInterface;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Results {
    private HashMap<Integer,CoursePreference[]> result = new HashMap<Integer,CoursePreference[]>();
    private float totalAvgSatisfactionRate = 0;
    public Results(){

    }

    /**
     * This method add allocated course result of each student to the hashMap.
     * @param id It is the student id.
     * @param allocated allocated courses.
     */
    public void addToResults(int id,CoursePreference[] allocated){
        result.put(id,allocated);
    }

    /**
     * This method prints the results to the output file.
     * @param outputFile output file path
     * @throws IOException throws io exception if file is not found in the specified location.
     */
    public void printResults(String outputFile) throws IOException {
        DecimalFormat decfor = new DecimalFormat("0.00");
        FileDisplayInterface print = new FileOutput(outputFile);
        print.getFileForWrite();
        StdoutDisplayInterface stdout = new FileOutput();
        for (HashMap.Entry<Integer,CoursePreference[]> mapElement : result.entrySet()){
            String output = toString(mapElement.getKey(),mapElement.getValue());
            print.printOutputToFile(output);
            stdout.printOutputToStdout(output);
        }

        try {
            float avg = Float.parseFloat(decfor.format((float)totalAvgSatisfactionRate/result.size()));
            print.printOutputToFile("AverageSatisfactionRating=" + avg +"\n");
            stdout.printOutputToStdout("AverageSatisfactionRating=" + avg + "\n");
        }
        catch (ArithmeticException e){
            System.err.println("divide by zero exception.");
            e.printStackTrace();
        }
        finally {
            print.closeFileWriter();
        }

    }

    /**
     * @return the string representation of the allocated courses. In this case this string has the format required for the output.
     */
    public String toString(int id,CoursePreference[] allocated){
        StringBuilder output = new StringBuilder(id + ":");
        for (int i = 0; i < allocated.length; i++) {
            if(i==allocated.length-1) {
                output.append(allocated[i].getCourse().getCourseName());
            }
            else{
                output.append(allocated[i].getCourse().getCourseName()).append(",");
            }
        }

        output.append("::SatisfactionRating=").append(getAverageSatisfactionRate(allocated)).append("\n");
        return output.toString();
    }

    /**
     * This method calculates average satisfaction rate
     * @return float value of satisfaction rate or -1 if denominator is 0.
     */
    public float getAverageSatisfactionRate(CoursePreference[] allocatedCourses) {
        DecimalFormat decfor = new DecimalFormat("0.00");
        int totalSatisfactionRate = 0;
        for (CoursePreference allocatedCourse : allocatedCourses) {
            totalSatisfactionRate += allocatedCourse.getPreference();
        }

        try {
            float avg = Float.parseFloat(decfor.format((float)totalSatisfactionRate/allocatedCourses.length));
            this.totalAvgSatisfactionRate += avg;
            return avg;
        }
        catch (ArithmeticException e){
            System.err.println("divide by zero exception.");
            e.printStackTrace();
        }
        return -1;
    }
}
