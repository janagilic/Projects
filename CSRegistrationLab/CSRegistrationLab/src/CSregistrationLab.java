/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSregistrationLab {

	public static void main(String[] args) throws Exception {

		
        	
        // FileInputStream is = new FileInputStream(new File("CSregistrationLab.in")); //***
        // System.setIn(is); //***

        // PrintStream os = new PrintStream(new File("HW4_2.txt")); //***
        // System.setOut(os); //***
     
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
		
		// Make variables
		int numLaptops;
		int numDays;
		int numStudents;

		// Make Scanner for input
		Scanner input = new Scanner(System.in);

		// Scan 1st line of input file (number of laptops)
		numLaptops = input.nextInt();

		// Make a new Stack of Laptops
		CSlaptopStack laptops = new CSlaptopStack(numLaptops);

		// Scan laptops and PUSH them into the stack
		for (int i = 0; i < numLaptops; i++) {
			laptops.push(input.nextInt());
		}

		// Make the FOUR queues for each of the needed queues
		CSqueue outsideLine = new CSqueue();
		CSqueue laptopCheckOutLine = new CSqueue();
		CSqueue studentsRegistering = new CSqueue();
		CSqueue laptopReturnLine = new CSqueue();

		// Make ONE linked-list for the Daily Registration Report
		CSregistrations dailyRegistrations = new CSregistrations();

		// Make LCM & LRM
		CSstudent studentWithLCM = null; // Reference to student who is working with the LCM
		CSstudent studentWithLRM = null; // Reference to student who is working with the LRM
		// We use them to point to students who are currently interacting with the LCM or LRM

		// Scan number of days of the simulation
		numDays = input.nextInt();

		// OUTER FOR LOOP over all days of simulation
		for (int i = 0; i < numDays; i++) {
			// Print header message to output for given day
			System.out.println("**********");
			System.out.println("Day " + (i + 1) + ":");
			System.out.println("**********");
			System.out.println();

			// Scan the number of students for this day
			numStudents = input.nextInt();
			int studentsRemaining = numStudents; // used for MAIN LOOP condition

			// INNER FOR LOOP to scan/save all students for given day
			for (int j = 0; j < numStudents; j++) {
				// Make a NEW CSstudent object and SAVE data
				CSstudent tempStudent = new CSstudent();
				tempStudent.setEnterTime(input.nextInt());
				tempStudent.setLastName(input.next());
				tempStudent.setFirstName(input.next());
				tempStudent.setID(input.nextInt());
				tempStudent.setNumCourses(input.nextInt());
				tempStudent.setNext(null);

				// Make temp CScourse array to temporarily hold the courses for this particular student
				CScourse[] tempCourses = new CScourse[tempStudent.getNumCourses()];

				// FOR loop over the number of courses for above NEW student
				for (int k = 0; k < tempStudent.getNumCourses(); k++) {
					// Make a NEW CScourse object and SAVE data
					CScourse tempCourse = new CScourse();
					tempCourse.setCourseNumber(input.next());
					tempCourse.setCourseDays(input.next());
					String courseTime = input.nextLine();
					tempCourse.setCourseTime(courseTime.substring(1));
					// Save the course into the tempCourses array
					tempCourses[k] = tempCourse;
				}

				// Finally, save the array of courses into the tempStudent courses member
				tempStudent.setCourses(tempCourses);

				// ENQUEUE tempStudent into outsideLine queue
				outsideLine.enqueue(tempStudent);

			} // END loop SCANNING/SAVING all students to outsideLine
			//**********************************************************************/
			//********************** MAIN SIMULATION FOR LOOP **********************/
			//**********************************************************************/
			for (int minutes = 0; minutes < 300 || studentsRemaining > 0; minutes++) {

				// Each iteration of this for loop represents a SINGLE minute of the simulation
				// Many things happen during each minute.
				
				// You need to identify those things, make AMAZING comments for them, 
				// and then code each one of them up.
				
				// Most important is the ORDER of each of these things. Even if you coded
				// each thing correctly, you output will only match the expected output
				// if and when you order these components correctly.

				//setting the time to start at 12:00pm
				int hours = 12 + (int)(minutes/60);
				int mins = minutes%60;
				LocalTime time = LocalTime.of(hours, mins);


				// Process Laptop Return Line
				if (studentWithLRM != null){

					// Get next student from Laptop Return Line and set it to talk to laptop registration minion
					CSstudent currSt = studentWithLRM;

					//Return serial number of Laptop if the stack of laptops is not full
					if (!laptops.isFull()) laptops.push(currSt.getLaptopSerialNumber());

					// Remove student from laptop Return Line
					CSstudent temp = laptopReturnLine.dequeue();

					//printing out the time student has returned their laptop with sudent's information
					System.out.printf(time.format(dtf) + ":  %s %s successfully registered and returned laptop # %d.%n", 
						temp.getFirstName(), temp.getLastName(), temp.getLaptopSerialNumber());

					// Update student data
					currSt.setLaptopSerialNumber(0);
					currSt.setTimeRegistered(minutes);

					// Add completed registrations to Daily Registration Report
					dailyRegistrations.insert(temp);

					// Set the registraion minion to null because it stopped talking to a student
					studentWithLRM = null;

					// Decrement the students remaining 
                    studentsRemaining--;

				}


				// Process Laptop CheckOut Line
				if (studentWithLCM != null){

					//set Laptop check out minion to talk to a student
					CSstudent currSt = studentWithLCM;

					// Assign laptop to student and set the remaining time to 5 minutes
					currSt.setLaptopSerialNumber(laptops.peek());
					currSt.setTimeRemaining(5);

					// Remove laptop from stack
					laptops.pop();

					// remove student from Laptop Check-out Line 
					CSstudent temp = laptopCheckOutLine.dequeue();
					temp.setNext(null);

					// Add student to Registering line
					studentsRegistering.enqueue(temp);

					//printing the time the student has checked out laptop with student's data
					System.out.printf(time.format(dtf) + ":  %s %s has checked-out laptop # %d.%n", 
						temp.getFirstName(), temp.getLastName(), temp.getLaptopSerialNumber());

					//setting the check out minion to null because it stopped talking to a student
					studentWithLCM = null;

				}


				// Process registering
				if (!studentsRegistering.isEmpty()){

					// Check all students in registering process
					CSstudent tempStudent = studentsRegistering.peek();

					//loop while there is no more students in the registering line
					while (tempStudent != null) {

						// Check if student is finished registering
						if (tempStudent.getTimeRemaining() == 0){

							// Remove student Registering list
							CSstudent temp = studentsRegistering.dequeue();
							temp.setNext(null);

							// Add student to Laptop Return Line
							laptopReturnLine.enqueue(temp);

							//print the time of registration and data of a student 
							System.out.printf(time.format(dtf) + ":  %s %s finished registering and entered the Laptop Return Line.%n", 
								temp.getFirstName(), temp.getLastName());

						}
						// step one node over
						tempStudent = tempStudent.getNext(); 
					}

					// Decrese remaining time for all students in registering process
					tempStudent = studentsRegistering.peek();
					while (tempStudent != null) {
						tempStudent.setTimeRemaining(tempStudent.getTimeRemaining() - 1);

						// step one node over
						tempStudent = tempStudent.getNext(); 
					}

				}


				// Process “brief interaction” with the Laptop Return Minion
				if (!laptopReturnLine.isEmpty()){

					// Get next student from Laptop Return Line
					CSstudent currSt = laptopReturnLine.peek();

					// Check if somebody already talking with Laptop Return Minion
					if (studentWithLRM == null){
						// Enter in “brief interaction” with the Laptop Return Minion
						studentWithLRM = currSt;
					}
				}


				// Get next student from Outside Line
				if (!outsideLine.isEmpty()){

					// In case more then one student have the same arrival time we need loop to get them all
					while (true) {

						// Get next student from Outside Line
						CSstudent currSt = outsideLine.peek();
	
						//if the line is empty, break
						if (currSt == null) break;
	
						// Check if student arrives for registration
						if (currSt.getEnterTime() == minutes) {
	
							// remove student from Outside Line 
							CSstudent temp = outsideLine.dequeue();
							temp.setNext(null);
	
							// put Student in Laptop Check out Line
							laptopCheckOutLine.enqueue(temp);
	
							//print the arrival time of a student 
							System.out.printf(time.format(dtf) + ":  %s %s has arrived at the Registration Lab and entered the Laptop Check-out Line.%n", 
								temp.getFirstName(), temp.getLastName());
	
						}
						else {
							// break condition 
							break;
						}
	
					}
				}


				//Process “brief interaction” with the Laptop Check-out Minion
				if (!laptopCheckOutLine.isEmpty()){

					// Check available laptops
					if (!laptops.isEmpty()){

						// Get next student from Laptop CheckOut Line
						CSstudent currSt = laptopCheckOutLine.peek();

						// Check if somebody already talking with Laptop Check-out Minion
						if (studentWithLCM == null){
							// Enter in “brief interaction” with the Laptop Check-out Minion
							studentWithLCM = currSt;
						}
					}
				}


			} //*********** END main FOR Loop of Simulation ***********//


			// PRINT Daily Registration Report
			// Notice that the printing of the daily registration report happens AFTER the
			// main for loop over each minute...and that should make sense, right? Once the day
			// has completed, then and only then are you ready to traverse/print the linked-list
			// of the completed registrations.

			//printing out daily registration report
			System.out.println();
			System.out.printf("*** Day %d:  FCIT Daily Registration Report ***:%n", i+1);
			System.out.println();
			System.out.printf("The Registration Lab received %d registrations as follows:%n", dailyRegistrations.sumNodes());
			System.out.println();

			dailyRegistrations.PrintList();
			System.out.println();

			//Clear today registrations
			dailyRegistrations = new CSregistrations();

		}
	}

}
