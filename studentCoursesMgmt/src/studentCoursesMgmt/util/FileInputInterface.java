package studentCoursesMgmt.util;

public interface FileInputInterface {
    public String[] readFileContent();
    public String getFilePath();
    public void setFilePath(String filePath);
    public String getDelimiter();
    public void setDelimiter(String delimiter);
    public void getFileForRead();
    public void closeFile();
}
