package studentCoursesMgmt.util;

import java.io.IOException;

public interface FileDisplayInterface {

    public void printErrorMessageToFile(String message) throws IOException;
    public void printRegConflictsToFile(String message) throws IOException;
    public void printResultsToFile(String message) throws IOException;
	
}
