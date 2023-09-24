package studentCoursesMgmt.util;

import java.io.IOException;

public class FileInput {
    FileProcessorInterface fileProcessor;
    private String filePath;
    private String delimiter;

    public FileInput(String filePath, String separator) {
        this.filePath = filePath;
        fileProcessor = new FileProcessor(filePath);
        delimiter = separator;
    }

    public void getFileForRead() throws IOException{
        fileProcessor.getFileForRead();
    }

    public String[] readFileContent() throws IOException {
        String input = fileProcessor.readFile();
        if (input != null) {
            return input.split(delimiter);
        } else {
            return null;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(filePath + " " + delimiter );
        return str.toString();
    }
}
