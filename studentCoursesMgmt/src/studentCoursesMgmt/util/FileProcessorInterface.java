package studentCoursesMgmt.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface FileProcessorInterface {

    public Scanner getFileForRead() throws IOException;

    public FileWriter getFileForWrite() throws IOException;

    public String readFile() throws IOException;

    public void writeToFile(String output) throws IOException;

    public void closeFile() throws IOException;
}
