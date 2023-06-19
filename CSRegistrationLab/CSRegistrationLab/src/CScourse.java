/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */ 

public class CScourse {
    private String courseNumber;
    private String courseDays;
    private String courseTime;

    //constructor
    public CScourse(){
        courseNumber = null;
        courseDays = null;
        courseTime = null;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseDays() {
        return courseDays;
    }

    public void setCourseDays(String courseDays) {
        this.courseDays = courseDays;
    }

    public String getCourseTime() {
        return courseTime;
    }
    
    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }


}
