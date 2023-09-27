package studentCoursesMgmt.util;

import java.io.IOException;

public class FileOutput implements FileDisplayInterface, StdoutDisplayInterface {
	private String filePath;
    FileProcessorInterface fileProcessor;

    public FileOutput(){

    }
    public FileOutput(String filePath) {
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

    public void printOutputToStdout(String output){
        System.out.print(output);
    }

    public void printErrorToStdout(String errorMessage){
        System.err.println(errorMessage);
    }

    public String toString(){
        return "File path is " + filePath;
    }
}
