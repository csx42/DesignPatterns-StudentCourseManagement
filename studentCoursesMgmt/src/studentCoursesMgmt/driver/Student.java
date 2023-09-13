package studentCoursesMgmt.driver;

public class Student implements StudentInterface{

    private String FirstName;
    private String LastName;
    private String Id;

    public Student(String id) {
        Id = id;
    }

    public Student(String firstName, String lastName, String id) {
        FirstName = firstName;
        LastName = lastName;
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
