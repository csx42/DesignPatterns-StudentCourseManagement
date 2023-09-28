package studentCoursesMgmt.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public interface FileProcessorInterface {

    public Scanner getFileForRead();

    public FileWriter getFileForWrite();

    public String readFile();

    public void writeToFile(String output);

    public void closeWriteFile();
    public void closeReadFile();
}
