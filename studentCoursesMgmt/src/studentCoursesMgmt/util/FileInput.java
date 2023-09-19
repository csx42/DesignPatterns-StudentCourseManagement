package studentCoursesMgmt.util;

import java.io.IOException;
import java.util.Scanner;

public class FileInput {
    FileProcessor fileProcessor;
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
            System.out.println(input);
            return input.split(delimiter);
        } else {
            return null;
        }
    }
}
