package studentCoursesMgmt.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor implements FileProcessorInterface{
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

    public Scanner getFileForRead() {
        try {
            file = new File(filePath);
            scanner = new Scanner(file);
            return scanner;
        }
        catch (IOException e){
            System.err.println(filePath + " file not found.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public FileWriter getFileForWrite() {
        try {
            file = new File(filePath);
            fileWriter = new FileWriter(file,true);
            return fileWriter;
        }
        catch (IOException e){
            System.err.println(filePath + " file not found.");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public String readFile() {
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        }
        else{
            return null;
        }
    }

    public void writeToFile(String output) throws IOException {
        if(output!=null){
            fileWriter.write(output);
        }
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("File path is "+ filePath);
        return str.toString();
    }

}
