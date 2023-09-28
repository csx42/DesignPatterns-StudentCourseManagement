package studentCoursesMgmt.driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {
	public static void main(String[] args) throws IOException {

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

		StudentCourseManagement studentCourseManagement = new StudentCourseManagement();
		studentCourseManagement.argsCheck(args);
		Course[] availableCourseList = studentCourseManagement.readCourseFile(args[1]);
		StudentCourseInterface studentCourseAllocation = null;
		String[] preferred = new String[0];
		Results resultObj = new Results();
		studentCourseManagement.allocateAndPrintResult(studentCourseAllocation, preferred, availableCourseList, args[0],args[3], args[4],resultObj);
		studentCourseManagement.printResults(resultObj,args[2]);

	}


}
