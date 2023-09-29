package studentCoursesMgmt.util;

import java.io.IOException;

public class FileInput implements FileInputInterface{
    FileProcessorInterface fileProcessor;
    private String filePath;
    private String delimiter;

    public FileInput(String filePath, String separator) {
        this.filePath = filePath;
        fileProcessor = new FileProcessor(filePath);
        delimiter = separator;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void getFileForRead(){
        fileProcessor.getFileForRead();
    }

    public String[] readFileContent() {
        String input = fileProcessor.readFile();
        if (input != null) {
            return input.split(delimiter);
        } else {
            return null;
        }
    }

    public void closeFile(){
        fileProcessor.closeReadFile();
    }

    public String toString(){
        return filePath + " " + delimiter;
    }
}
