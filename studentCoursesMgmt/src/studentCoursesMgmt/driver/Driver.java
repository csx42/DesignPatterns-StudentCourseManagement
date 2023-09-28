package studentCoursesMgmt.driver;

import java.io.IOException;

public class Driver {
	public static void main(String[] args){

		StudentCourseManagement studentCourseManagement = new StudentCourseManagement();
		studentCourseManagement.argsCheck(args);
		studentCourseManagement.deleteOutputFiles(args);
		
		Course[] availableCourseList = studentCourseManagement.readCourseFile(args[1]);
		Results results = new Results();
		studentCourseManagement.allocateAndPrintResult(availableCourseList, args[0], args[3], args[4],results);
		results.printResults(args[2]);

	}

}
