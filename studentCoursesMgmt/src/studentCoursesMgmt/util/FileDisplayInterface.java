package studentCoursesMgmt.util;

import java.io.IOException;

public interface FileDisplayInterface {

    public void getFileForWrite()throws IOException;
    public void printOutputToFile(String output)throws IOException;
    public void closeFileWriter() throws IOException;
	
}
