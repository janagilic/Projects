public class FSCcourse {
    private String ID;
    private int grade;
    private int num;
    private FSCcourse next;

    //constructors
    public FSCcourse(FSCcourse course, FSCcourse next) {
        this.ID = course.ID;
        this.grade = course.grade;
        this.num = course.num;
        this.next = next;
    }
    
    public FSCcourse(String iD, int grade, int num) {
        ID = iD;
        this.grade = grade;
        this.num = num;
        this.next = null;
    }

    public FSCcourse() {
        this.next = null;
    }

    //getters and setters
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public FSCcourse getNext() {
        return next;
    }
    public void setNext(FSCcourse next) {
        this.next = next;
    } 


}
