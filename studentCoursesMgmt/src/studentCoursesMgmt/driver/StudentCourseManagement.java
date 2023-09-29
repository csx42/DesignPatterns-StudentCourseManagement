package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.FileInput;
import studentCoursesMgmt.util.FileInputInterface;
import studentCoursesMgmt.util.FileOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StudentCourseManagement implements StudentCourseManagementInterface{

    int noOfCourses = 9;
    int maximumCourseAllocation = 3;

    public void deleteOutputFiles(String[] args){
        try{
            Path myPath = Paths.get(args[2]);
            Files.deleteIfExists(myPath);
            myPath = Paths.get(args[3]);
            Files.deleteIfExists(myPath);
            myPath = Paths.get(args[4]);
            Files.deleteIfExists(myPath);
        }
        catch(IOException e){
            System.err.println("Unknown File exception.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void argsCheck(String[] args){
        if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
                || args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()
                || args[3].isEmpty() || args[4].isEmpty()) {

            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }
    }
    public void allocateAndPrintResult(Course[] availableCourseList, String coursePrefs, String regConflictsFilePath,
                                       String errorLogsFilePath,Results resultsObj) {
        StudentCourseInterface studentCourseAllocation;
        String[] preferred;
        FileInputInterface fileInput = new FileInput(coursePrefs, " ");
        fileInput.getFileForRead();
        String[] input = new String[0];
        try {
            do {
                input = fileInput.readFileContent();
                if (input != null) {
                    studentCourseAllocation = new StudentCourseAllocation(Integer.parseInt(input[0]));
                    preferred = Arrays.copyOfRange(input, 1, 10);
                    preferred[8] = preferred[8].substring(0, 1);
                    studentCourseAllocation.setPreferredCourses(preferred, availableCourseList);
                    studentCourseAllocation.allocateCourses(regConflictsFilePath, errorLogsFilePath, resultsObj);
                }
            } while (input != null);
        }
        catch (NumberFormatException e) {
            printErrorMessageToFile("NumberFormat Exception: invalid input.\n", errorLogsFilePath);
            System.err.println("NumberFormat Exception: invalid input.\n");
            e.printStackTrace();
            System.exit(0);
        } catch (NullPointerException e) {
            printErrorMessageToFile("NullPointerException: All the preferences are not specified for the " +
                    "student with id " + Integer.parseInt(input[0]) + ".\n", errorLogsFilePath);
            System.err.println("NullPointerException: All the preferences are not specified for the " +
                    "student with id " + Integer.parseInt(input[0]) + ".\n");
            e.printStackTrace();
            System.exit(0);
        }
        finally {
            fileInput.closeFile();
        }
    }

    public Course[] readCourseFile(String courseInfo){
        Course[] availableCourses = new Course[9];
        FileInputInterface fileInput = new FileInput(courseInfo,":");
        fileInput.getFileForRead();
        String[] input;
        int count =0;
        do{
            input = fileInput.readFileContent();
            if(input!=null) {
                availableCourses[count]=new Course(input[0], Integer.parseInt(input[2]), Integer.parseInt(input[1]));
                count+=1;
            }

        }while (input!=null);
        return availableCourses;
    }

    public void printErrorMessageToFile(String message, String file){
        FileDisplayInterface print = new FileOutput(file);
        print.getFileForWrite();
        print.printOutputToFile(message);
        print.closeFileWriter();
    }
}
