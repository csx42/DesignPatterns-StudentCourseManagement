package studentCoursesMgmt.util;

import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	private String filePath;
    FileProcessor fileProcessor;

    public Results(String filePath) {
        this.filePath = filePath;
        fileProcessor = new FileProcessor(filePath);
    }

    public void getFileForWrite() throws IOException {
        fileProcessor.getFileForWrite();
    }

    public void printOutputToFile(String output) throws IOException {
        fileProcessor.writeToFile(output);
    }

    public void closeFileWriter() throws IOException{
        fileProcessor.closeFile();
    }

    public void printOutputToStdout(){

    }

    public void printErrorToStdout(){

    }
}
