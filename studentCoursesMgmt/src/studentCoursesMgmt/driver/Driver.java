package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.FileInput;
import studentCoursesMgmt.util.FileOutput;

import java.util.Arrays;
import java.io.IOException;
import java.nio.file.FileSystems;
import studentCoursesMgmt.util.FileOutput;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {
	public static void main(String[] args) throws IOException {

	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[0].isEmpty() || args[1].isEmpty() || args[2].isEmpty()
				|| args[3].isEmpty() || args[4].isEmpty()) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}

		// String userDirectory = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        // userDirectory += "/";

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
		
		Course[] availableCourseList = readCourseFile(args[1]);
		StudentCourseInterface studentCourseAllocation = null;
		String[] preferred = new String[0];
		allocateAndPrintResult(studentCourseAllocation, preferred, availableCourseList, args[0], args[2], args[3], args[4]);

	}

	public static void allocateAndPrintResult(StudentCourseInterface studentCourseAllocation, String[] preferred,
											  Course[] availableCourseList, String coursePrefs, String resultsFile, String regConflictsFilePath,
											  String errorLogsFilePath) throws IOException {
		FileInput fileInput = new FileInput(coursePrefs, " ");
		fileInput.getFileForRead();
		String[] input;
		Results resultObj = new Results();
		do {
			input = fileInput.readFileContent();
			if (input != null) {
				try {
					studentCourseAllocation = new StudentCourseAllocation(Integer.parseInt(input[0]));
					preferred = Arrays.copyOfRange(input, 1, 10);
					preferred[8] = preferred[8].substring(0, 1);
					studentCourseAllocation.setPreferredCourses(preferred, availableCourseList);
					studentCourseAllocation.allocateCourses(regConflictsFilePath, errorLogsFilePath, resultObj);
				}
				catch (NumberFormatException e){
					printErrorMessageToFile("NumberFormat Exception: invalid input.\n",errorLogsFilePath);
					System.err.println("NumberFormat Exception: invalid input.\n");
					e.printStackTrace();
					System.exit(0);
				}
				catch (NullPointerException e){
					printErrorMessageToFile("NullPointerException: All the preferences are not specified for the " +
							"student with id " + Integer.parseInt(input[0]) + ".\n",errorLogsFilePath);
					System.err.println("NullPointerException: All the preferences are not specified for the " +
							"student with id " + Integer.parseInt(input[0]) + ".\n");
					e.printStackTrace();
					System.exit(0);
				}
			}
		} while (input != null);

		resultObj.printResults(resultsFile);
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

	public static void printErrorMessageToFile(String message, String file) throws IOException{
		FileDisplayInterface print = new FileOutput(file);
		print.getFileForWrite();
		print.printOutputToFile(message);
		print.closeFileWriter();
	}

}
