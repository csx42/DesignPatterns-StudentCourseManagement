package studentCoursesMgmt.driver;

public interface StudentCourseManagementInterface {
    public void deleteOutputFiles(String[] args);
    public void argsCheck(String[] args);
    public void allocateAndPrintResult(Course[] availableCourseList, String coursePrefs, String regConflictsFilePath,
                                       String errorLogsFilePath,Results resultsObj);
    public Course[] readCourseFile(String courseInfo);
    public void printErrorMessageToFile(String message, String file);
}
