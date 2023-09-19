package studentCoursesMgmt.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {
	private String filePath;
    private File file;
    private Scanner scanner;
    private FileWriter fileWriter;

    public FileProcessor(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String file) {
        filePath = file;
    }

    public Scanner getFileForRead() throws IOException {
        file = new File(filePath);
        scanner = new Scanner(file);
        return scanner;
    }

    public FileWriter getFileForWrite() throws IOException {
        file = new File(filePath);
        fileWriter = new FileWriter(file,true);
        return fileWriter;
    }

    public String readFile() throws IOException{
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        }
        else{
            return null;
        }
    }

    public void writeToFile(String output) throws IOException{
        if(output!=null){
            fileWriter.write(output);
        }
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }


}
