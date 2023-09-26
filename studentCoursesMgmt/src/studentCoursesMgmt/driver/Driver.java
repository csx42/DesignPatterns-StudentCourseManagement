package studentCoursesMgmt.driver;

import studentCoursesMgmt.util.FileInput;
import java.util.Arrays;
import java.io.IOException;
import studentCoursesMgmt.util.FileDisplayInterface;
import studentCoursesMgmt.util.Results;
import studentCoursesMgmt.util.StdoutDisplayInterface;
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
											  Course[] availableCourseList, String coursePrefs, String results, String regConflictsFilePath,
											  String errorLogsFilePath) throws IOException {
		FileInput fileInput = new FileInput(coursePrefs, " ");
		fileInput.getFileForRead();
		String[] input;
		float avg=0;
		int numberOfStudents = 0;
		do {
			input = fileInput.readFileContent();
			if (input != null) {
				studentCourseAllocation = new StudentCourseAllocation(Integer.parseInt(input[0]));
				preferred = Arrays.copyOfRange(input, 1, 10);
				preferred[8] = preferred[8].substring(0, 1);
				studentCourseAllocation.setPreferredCourses(preferred, availableCourseList);
				studentCourseAllocation.allocateCourses(regConflictsFilePath,errorLogsFilePath);
				studentCourseAllocation.printResults(results);
				avg+=studentCourseAllocation.getAverageSatisfactionRate();
				numberOfStudents+=1;
			}
		} while (input != null);

		
		printAverageSatisfactionRate(avg,numberOfStudents,results);
	}

	public static void printAverageSatisfactionRate(float averageSatisfactionRate,int numberOfStudents, String outputFile) throws IOException{

		DecimalFormat decfor = new DecimalFormat("0.00");  

		try{
			averageSatisfactionRate = averageSatisfactionRate/numberOfStudents;
		}
		catch(ArithmeticException e){
			System.err.println("divide by zero exception.");
            e.printStackTrace();
		}

		FileDisplayInterface print = new Results(outputFile);
        print.getFileForWrite();
        print.printOutputToFile("AverageSatisfactionRating="+decfor.format(averageSatisfactionRate));
        print.closeFileWriter();

        StdoutDisplayInterface stdout = new Results();
        stdout.printOutputToStdout("AverageSatisfactionRating="+decfor.format(averageSatisfactionRate));
		
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
