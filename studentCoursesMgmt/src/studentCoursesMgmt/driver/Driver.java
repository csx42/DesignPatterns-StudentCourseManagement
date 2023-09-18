package studentCoursesMgmt.driver;

/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) {

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
		String[] courses = {"A","B","C","D","E","F","G","H","I"};
		int[] max = {3,2,5,3,7,5,7,8,2};
		int[] time = {11,33,11,44,55,66,66,22,22};
		AvailableCourses availableCourses = new AvailableCourses(9);
		for(int i=0; i<9; i++){
			availableCourses.addToAvailableCourse(courses[i],time[i], max[i]);
		}
		Course[] availableCourseList = availableCourses.getAvailableCourses();

		int id = 111;
		StudentCourseInterface studentCourseAllocation = new StudentCourseAllocation(id);
		String[] preferred = {"A","I","C","H","E","D","G","F","B"};
		studentCourseAllocation.setPreferredCourses(preferred);
		availableCourseList = studentCourseAllocation.allocateCourses(availableCourseList);
		studentCourseAllocation.printResults();

	}
}
