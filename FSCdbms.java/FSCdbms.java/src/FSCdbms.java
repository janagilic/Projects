/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

import java.util.*;

public class FSCdbms {

    //MAIN method
    public static void main(String[] args) throws Exception{

        //scanner class for calling the user input
        Scanner input = new Scanner(System.in);

        //make an array for saving user inputs 
        String[] inputLine;

        //make a variable for user input for number of commands that will be run on database - k
        int numCommands;

        //Make a binary tree of students
        FSCdbmsBST students = new FSCdbmsBST();

        //Make a linked-list of courses 
        FSCcourses courses = new FSCcourses();

        //Scan 1st line of input file
        numCommands = Integer.parseInt(input.nextLine());

        //loop user input while number is less than k
        for(int i = 0; i < numCommands; i++){

            //read commad and then process to call the apptopriate method
            inputLine = input.nextLine().split(" ");

            //if user input is "NEWSTUDENT" call NEW STUDENT method
            if(inputLine[0].equals("NEWSTUDENT") == true){
                addNewStudent(students, inputLine);
            }

            //if user input is "SEARCHNAME" call SEARCH NAME method
            else if(inputLine[0].equals("SEARCHNAME") == true){
                searchByName(students, inputLine);
            }

            //if user input is "SEARCHID" call SEARCHID method
            else if(inputLine[0].equals("SEARCHID") == true){
                searchById(students, inputLine);
            }

            //if user input is "ADDCOURSE" call ADD COURSE method
            else if(inputLine[0].equals("ADDCOURSE") == true){
                addCourse(students, courses, inputLine);
            }

            //if user input is "DELETE" call DELETE method
            else if(inputLine[0].equals("DELETE") == true){
                deleteStudentRecords(students, inputLine);
            }

            //if user input is "PRINTRECORD" call PRINT RECORD method
            else if (inputLine[0].equals("PRINTRECORD") == true) {
                printRecord(students, inputLine);
            }

            //if user input is "PRINTALLRECORDS" call PRINT ALL RECORDS method
            else if (inputLine[0].equals("PRINTALLRECORDS") == true) {
                printAllRecords(students);
            }

            //if user input is "PRINTALLCOURSES" call PRINT ALL COURSES method
            else if (inputLine[0].equals("PRINTALLCOURSES") == true) {
                printAllCourses(courses);
            }

            //if input is nothing from what's given above
            else {
                System.out.println("Invalid Command: input invalid.");
            }
            
        }
        input.close();
    }

    //NEW STUDENT method
    public static void addNewStudent(FSCdbmsBST students, String[] inputLine){

        System.out.println("NEWSTUDENT Command");

        //we have to make new student node with these parameters: ID, first and last name, email, age, phone number
        FSCstudent student = new FSCstudent(Integer.parseInt(inputLine[1]), inputLine[2], inputLine[3], inputLine[4], Integer.parseInt(inputLine[5]), inputLine[6]);

        //check if that student is not in the FSCdbms 
        if(students.findNode(student) == null){//if not 

            //then add it to BST
            students.insert(student);

            System.out.printf("\t%s %s (ID %d) has been inserted as a new student in FSCdbms.%n", student.getFirstName(), student.getLastName(), student.getID());
        }

        else{//if it is in the BTS 

            //print error
            System.out.printf("\tERROR: cannot add student. Student ID # %d already exist in FSCdbms.%n", student.getID());
        }
    }

    //SEARCHNAME method
    public static void searchByName(FSCdbmsBST students, String[] inputLine){
        
        System.out.println("SEARCHNAME Command");

        //search for a student with given first and last name 
        FSCstudent student = students.findNodeByName(inputLine[2] + " " + inputLine[1]);

        if(student == null){//if we cant find sudent with that name,

            //print an error
            System.out.printf("\t%s %s was not found in FSCdbms.%n", inputLine[1], inputLine[2]);
        }
        else {//if we find student with that name

            //then print the student info
            System.out.printf("\tFound:  ID %d, %s%n", student.getID(), student.getFullName());
        }
    }

    //SEARCHID method
    public static void searchById(FSCdbmsBST students, String[] inputLine){

        System.out.println("SEARCHID Command");

        //search student by their ID from user input
        FSCstudent student = students.findNodeByID(Integer.parseInt(inputLine[1]));
        
        if (student == null) {//if we can't find student with that ID

            //print an error
            System.out.printf("\tID %d was not found in FSCdbms.%n", Integer.parseInt(inputLine[1]));
        }
        else {//if we cant find sudent with that ID 

            //then print the student info
            System.out.printf("\tFound:  ID %d, %s%n", student.getID(), student.getFullName());
        } 
    }
        
    //ADDCOURSE 
    public static void addCourse(FSCdbmsBST students, FSCcourses courses, String[] inputLine){

        System.out.println("ADDCOURSE Command");

        //Get ID, course and grade from input lines
        int id = Integer.parseInt(inputLine[1]);
        String courseId = inputLine[2];
        int grade = Integer.parseInt(inputLine[3]);

        //Find student by their ID
        FSCstudent st = students.findNodeByID(id);

        //if there is no student w that ID
        if (st == null) {

            //print error
            System.out.printf("\tERROR: cannot add course. Student ID # %d was not found in FSCdbms.%n", id);

        }
        else {//if we find a student with that ID

            //Create course
            FSCcourse tmpCourse = new FSCcourse(courseId, grade, 1);

            //Check if course exists in master linked-list of courses
            FSCcourse tmpC = courses.searchByID(courseId);

            //if it doesn't exist
            if (tmpC == null) {

                //Add course to master linked-list of courses
                courses.insert(tmpCourse);
            }
            else {//if it does exist

                //Update course data in master linked-list of courses
                tmpC.setGrade(tmpC.getGrade() + grade);
                tmpC.setNum(tmpC.getNum() + 1);
            }

            //Find course in student's list of courses
            FSCcourse c = st.getCourses().searchByID(courseId);

            //if it's not in there
            if (c == null) {

                //Add course to student
                st.getCourses().insert(tmpCourse);

                //Update level
                st.updateLevel();

                //Recalculate GPA
                st.setGpa(st.getCourses().calculateGPA());

                System.out.printf("\t%s (Grade: %d) has been added to the record of Student ID %d.%n", tmpCourse.getID(), tmpCourse.getGrade(), st.getID());

            }
            else {//if it is in the list 
                
                //Update student course grade
                int prevGrade = c.getGrade();

                //if current grade is bigger than it's previous grade
                if (prevGrade < grade) {

                    //update grade with the new grade
                    c.setGrade(grade);
                    
                    //Recalculate GPA
                    st.setGpa(st.getCourses().calculateGPA());

                    System.out.printf("\t%s: grade has been changed/updated, to a %d, for Student ID %d.%n", 
                        c.getID(), grade, st.getID());

                 }
                else {//if current grade is smaller than previous

                    System.out.printf("\t%s: no change made for Student ID %d. New grade (%d) is not beter than previous grade (%d).%n", 
                        c.getID(), st.getID(), grade, prevGrade);

                }
                
            }

        }

    }

    //DELETE
    public static void deleteStudentRecords(FSCdbmsBST students, String[] inputLine){

        System.out.println("DELETE Command");

        //search for a student with given first and last name
        FSCstudent student = students.findNodeByName(inputLine[2] + " " + inputLine[1]);
        
        //if we don't find it
        if(student == null){

            //print an error message
            System.out.println("\tCannot Perform DELETE Command:");
            System.out.printf("\t\tStudent (%s %s) was not found in FSCdbms.%n", inputLine[1], inputLine[2]);
        }

        //if we find it
        else{

            //delete from the bst
            students.delete(student);

            System.out.printf("\tStudent (%s %s) has been removed from FSCdbms.%n", inputLine[1], inputLine[2]);
        }
    }

    //PRINTRECORD
    public static void printRecord(FSCdbmsBST students, String[] inputLine){

        System.out.println("PRINTRECORD Command");

        //search for a student with given first and last name
        FSCstudent student = students.findNodeByName(inputLine[2] + " " + inputLine[1]);

        //if we can't find it 
        if (student == null) {

            //print an error
            System.out.println("\tCannot Perform PRINTRECORD Command:");
            System.out.printf("\t\tStudent (%s %s) was not found in FSCdbms.%n", inputLine[1], inputLine[2]);

        }

        //else, 
        else {

            //print student record with all the information
            //(current grade, GPA, courses they have  taken and grades recieved)
            student.printRecord();
        }  
    }   

    //PRINTALLRECORDS
    public static void printAllRecords(FSCdbmsBST students){

        System.out.println("PRINTALLRECORDS Command");

        //if student bst is empty
        if (students.isEmpty()) {

            //print an error
            System.out.println("\tCannot Perform PRINTALLRECORDS Command:");
            System.out.println("\t\tThere are currently no student records saved in FSCdbms.");
        }
        else {//if it's not empty

            //print the bst
            students.inorder();
        }

    }

    //PRINTALLCOURSES
    public static void printAllCourses(FSCcourses courses){

        System.out.println("PRINTALLCOURSES Command");

        //prints all courses ever recorded in the FSCdbms
        courses.PrintList();
    }
}
    
