package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.*;

import java.util.Arrays;
import java.io.IOException;
import java.text.DecimalFormat;
import java.nio.file.FileSystems;

public class Driver {
	public static void main(String[] args) throws IOException {

	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[0].equals("") || args[1].equals("") || args[2].equals("")
				|| args[3].equals("") || args[4].equals("")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}

		String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        userDirectory += "/";
		
		Course[] availableCourseList = readCourseFile(userDirectory+args[1]);
		StudentCourseInterface studentCourseAllocation = null;
		String[] preferred = new String[0];
		allocateAndPrintResult(studentCourseAllocation, preferred, availableCourseList, userDirectory+args[0], userDirectory+args[2], userDirectory+args[3], userDirectory+args[4]);

	}

	public static void allocateAndPrintResult(StudentCourseInterface studentCourseAllocation, String[] preferred,
											  Course[] availableCourseList, String coursePrefs, String resultFileName, String regConflictsFilePath,
											  String errorLogsFilePath) throws IOException {
		FileInput fileInput = new FileInput(coursePrefs, " ");
		fileInput.getFileForRead();
		String[] input;
		float avg=0;
		int numberOfStudents = 0;
		do {
			input = fileInput.readFileContent();
			if (input != null) {
				Results resultsObj = new Results(resultFileName, regConflictsFilePath,errorLogsFilePath);
				studentCourseAllocation = new StudentCourseAllocation(Integer.parseInt(input[0]),resultsObj);
				preferred = Arrays.copyOfRange(input, 1, 10);
				preferred[8] = preferred[8].substring(0, 1);
				studentCourseAllocation.setPreferredCourses(preferred, availableCourseList);
				studentCourseAllocation.allocateCourses(regConflictsFilePath,errorLogsFilePath);
				resultsObj.printResults(studentCourseAllocation.getStudentId());
				avg+=resultsObj.getAverageSatisfactionRate();
				numberOfStudents+=1;
			}
		} while (input != null);

		
		printAverageSatisfactionRate(avg,numberOfStudents,resultFileName);
	}

	public static void printAverageSatisfactionRate(float averageSatisfactionRate,int numberOfStudents, String resultFileName) throws IOException{

		DecimalFormat decfor = new DecimalFormat("0.00");  

		try{
			averageSatisfactionRate = averageSatisfactionRate/numberOfStudents;
		}
		catch(ArithmeticException e){
			System.err.println("divide by zero exception.");
            e.printStackTrace();
		}

		FileProcessorInterface fileProcessorInterface = new FileProcessor(resultFileName);
		fileProcessorInterface.getFileForWrite();
		fileProcessorInterface.writeToFile("AverageSatisfactionRating="+decfor.format(averageSatisfactionRate));
		fileProcessorInterface.closeFile();

        System.out.println("AverageSatisfactionRating="+decfor.format(averageSatisfactionRate));

	}

	public static Course[] readCourseFile(String courseInfo) throws IOException{
		AvailableCourses availableCourses = new AvailableCourses(9);
		FileInput fileInput = new FileInput(courseInfo,":");
		fileInput.getFileForRead();
		String[] input;
		do{
			input = fileInput.readFileContent();
			if(input!=null) {
				availableCourses.addToAvailableCourse(input[0], Integer.parseInt(input[2]), Integer.parseInt(input[1]));
			}

		}while (input!=null);
		return availableCourses.getAvailableCourses();
	}

}
