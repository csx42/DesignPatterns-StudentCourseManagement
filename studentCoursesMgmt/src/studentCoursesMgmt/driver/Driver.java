package studentCoursesMgmt.driver;

import java.io.IOException;


public class Driver {
	public static void main(String[] args) throws IOException {

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
