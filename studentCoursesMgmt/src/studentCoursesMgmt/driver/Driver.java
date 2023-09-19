package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.FileInput;
import studentCoursesMgmt.util.Results;

import java.util.Arrays;
import java.io.IOException;

/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

//	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
//				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {
//
//			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
//			System.exit(0);
//		}

		Course[] availableCourseList = readCourseFile();

		StudentCourseInterface studentCourseAllocation = null;
		String[] preferred = new String[0];
		allocateAndPrintResult(studentCourseAllocation, preferred, availableCourseList);
	}

	public static void allocateAndPrintResult(StudentCourseInterface studentCourseAllocation, String[] preferred,
												  Course[] availableCourseList) throws IOException {
		FileInput fileInput = new FileInput("/Users/spoorthisanjay/DP/coursePrefs.txt", " ");
		fileInput.getFileForRead();
		String[] input;
		do {
			input = fileInput.readFileContent();
			if (input != null) {
				studentCourseAllocation = new StudentCourseAllocation(Integer.parseInt(input[0]));
				preferred = Arrays.copyOfRange(input, 1, 10);
				preferred[8] = preferred[8].substring(0, 1);
				studentCourseAllocation.setPreferredCourses(preferred);
				availableCourseList = studentCourseAllocation.allocateCourses(availableCourseList);
				FileDisplayInterface print = new Results("/Users/spoorthisanjay/DP/registration_results.txt");
				print.getFileForWrite();
				studentCourseAllocation.printResults(print);
				print.closeFileWriter();
			}
		} while (input != null);
	}

	public static Course[] readCourseFile() throws IOException{
		AvailableCourses availableCourses = new AvailableCourses(9);
		FileInput fileInput = new FileInput("/Users/spoorthisanjay/DP/courseInfo.txt",":");
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
