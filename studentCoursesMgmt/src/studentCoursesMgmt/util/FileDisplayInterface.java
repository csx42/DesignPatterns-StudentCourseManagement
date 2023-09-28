package studentCoursesMgmt.util;

import java.io.IOException;

public interface FileDisplayInterface {

    public void getFileForWrite();
    public void printOutputToFile(String output);
    public void closeFileWriter();
	
}
