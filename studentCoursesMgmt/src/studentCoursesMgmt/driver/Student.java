package studentCoursesMgmt.driver;

public class Student {

    private String FirstName;
    private String LastName;
    private int Id;

    public Student(int id) {
        Id = id;
    }

    public Student(String firstName, String lastName, int id) {
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

}
