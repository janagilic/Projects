/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class CSstudent{
    private String firstName;
    private String lastName;
    private int ID;
    private int laptopSerialNumber;
    private int numCourses;
    private CScourse[] courses;
    private int enterTime;
    private int timeRemaining;
    private int timeRegistered;
    private CSstudent next;

    //constructors
    public CSstudent(CSstudent student){
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.ID = student.getID();
        this.laptopSerialNumber = student.getLaptopSerialNumber();
        this.numCourses = student.getNumCourses();
        this.courses = student.getCourses();
        this.enterTime = student.getEnterTime();
        this.timeRemaining = student.getTimeRemaining();
        this.timeRegistered = student.timeRegistered;

        this.next = student.getNext();
    }

    //constructors
    public CSstudent(CSstudent student, CSstudent next) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.ID = student.getID();
        this.laptopSerialNumber = student.getLaptopSerialNumber();
        this.numCourses = student.getNumCourses();
        this.courses = student.getCourses();
        this.enterTime = student.getEnterTime();
        this.timeRemaining = student.getTimeRemaining();
        this.timeRegistered = student.timeRegistered;

        this.next = next;
    
    }

    public CSstudent(){
        this.next = null;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getLaptopSerialNumber() {
        return laptopSerialNumber;
    }

    public void setLaptopSerialNumber(int laptopSerialNumber) {
        this.laptopSerialNumber = laptopSerialNumber;
    }

    public int getNumCourses() {
        return numCourses;
    }

    public void setNumCourses(int numCourses) {
        this.numCourses = numCourses;
    }

    public CScourse[] getCourses() {
        return courses;
    }

    public void setCourses(CScourse[] courses) {
        this.courses = courses;
    }

    public int getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(int enterTime) {
        this.enterTime = enterTime;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(int timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public CSstudent getNext() {
        return next;
    }

    public void setNext(CSstudent next) {
        this.next = next;
    }
    
    
}