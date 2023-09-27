package studentCoursesMgmt.util;

public interface StdoutDisplayInterface {
    public void printErrorMessageToStdError(String message);
    public void printRegConflictsToStdOut(String message);
    public void printResultsToStdOut(String message);
}
