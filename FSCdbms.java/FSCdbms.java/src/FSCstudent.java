/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class FSCstudent {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String phone;
    private int level;
    private FSCcourses courses;
    private double gpa;
    private FSCstudent left;
    private FSCstudent right;

    //Constructors
    public FSCstudent(int ID, String firstName, String lastName, String email, int age, String phone){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.level = 1;
        this.courses = new FSCcourses();
        this.gpa = this.courses.calculateGPA();
        this.left = this.right = null;
    }

    public FSCstudent(FSCstudent student, FSCstudent left, FSCstudent right) {
        this.ID = student.ID;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.age = student.age;
        this.phone = student.phone;
        this.level = student.level;
        this.courses = student.courses;
        this.gpa = student.gpa;
        this.left = left;
        this.right = right;
    }

    public FSCstudent(FSCstudent student) {
        this.ID = student.ID;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.age = student.age;
        this.phone = student.phone;
        this.level = student.level;
        this.courses = student.courses;
        this.gpa = student.gpa;
        this.left = student.getLeft();
        this.right = student.getRight();
    }

    //getters and setters
    public FSCstudent() {
        this.left = this.right = null;
    }

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public FSCcourses getCourses() {
        return courses;
    }
    public void setCourses(FSCcourses courses) {
        this.courses = courses;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public FSCstudent getLeft() {
        return left;
    }
    public void setLeft(FSCstudent left) {
        this.left = left;
    }
    public FSCstudent getRight() {
        return right;
    }
    public void setRight(FSCstudent right) {
        this.right = right;
    }
    
    //when calling this method it will return first name and last name separated by space
    public String getFullName() {
        return firstName + " " + lastName;
    }

    //this method is returning what year the student is in based on the level number
    public String getLevelString() {
        if (level == 1) {
            return "1st Year"; 
        }
        else if (level == 2) {
            return "2nd Year"; 
        }
        else if (level == 3) {
            return "3rd Year"; 
        }
        else if (level == 4) {
            return "4th Year"; 
        }
        return "";
    }

    //updating the level number based on hours of classes taken
    public void updateLevel() {

        //multiplying amount of courses a student has taken with 4 to get total number of course hours
        int coursesHours = this.getCourses().countNodes() * 4;

        //setting student's level based on amount of hours the student has taken
        if (coursesHours < 30) this.level = 1;
        else if (coursesHours >= 30 && coursesHours < 60) this.level = 2;
        else if (coursesHours >= 60 && coursesHours < 93) this.level = 3;
        else this.level = 4;
    }

    //when calling this method we will set all of the information about the sudent 
    public void setData(FSCstudent student) {
        this.ID = student.ID;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.age = student.age;
        this.phone = student.phone;
        this.level = student.level;
        this.courses = student.courses;
        this.gpa = student.gpa;
        this.setLeft(this.left);
        this.setRight(this.right);
    }

    //when calling this method we will get all information from the student
    public FSCstudent getData() {
        FSCstudent temp = new FSCstudent();
        temp.setID(this.ID);
        temp.setFirstName(this.firstName);
        temp.setLastName(this.lastName);
        temp.setEmail(this.email);
        temp.setAge(this.age);
        temp.setPhone(this.phone);
        temp.setLevel(this.level);
        temp.setCourses(this.courses);
        temp.setGpa(this.gpa);
        temp.setLeft(null);
        temp.setRight(null);
        return temp;
    }
    
    //method for printing the record, so in the main method we just call it
    public void printRecord() {

        System.out.printf("\tStudent Record for ID %d%n", this.getID());
        System.out.printf("\tFirst Name:  %s%n", this.getFirstName());
        System.out.printf("\tLast Name:   %s%n", this.getLastName());
        System.out.printf("\tEmail:       %-34s Phone:  %s%n", this.getEmail(), this.getPhone());
        System.out.printf("\tAge:         %-34d Level:  %s%n", this.getAge(), this.getLevelString());

        //if student's gpa is -1, print N/A
        if (this.getGpa() == -1) 
        System.out.printf("\tGPA:         N/A%n");

        //else, print it's gpa
        else 
        System.out.printf("\tGPA:         %1.2f%n", this.getGpa()); 


        System.out.printf("\tCourse Record:%n");

        //if number of courses is 0
        if (this.courses.countNodes() == 0) 
        System.out.println("\t\tStudent has not taken any courses");

        //making a help pointer to traverse through linked list
        FSCcourse helpPtr = this.courses.getHead();

		// Traverse to correct insertion point
		while (helpPtr != null) {

			// Print the data value of the node
			System.out.printf("\t\tCourse ID:  %-10sGrade: %4d%n", helpPtr.getID(), helpPtr.getGrade());

			// Step one node over
			helpPtr = helpPtr.getNext();
		}

    }


}
