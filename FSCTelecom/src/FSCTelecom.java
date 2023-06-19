/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */
import java.util.*;

public class FSCTelecom {

	// FINAL (CONSTANT) variables
	public static final double ratePerMinute = 0.05;
	public static final double smsRate = 0.02;
	public static final double dataRatePerKB = (1.0 / 1024.0) / 100.0;

	//create new Linked List named studentsList
	public static FSCTelecomAccounts studentsList = new FSCTelecomAccounts();

	
	public static void addAccount(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		// Note that this method should use the insert() method of the Linked List class

		System.out.println("Command: ADDACCOUNT");

		//create new student
		Student student = new Student(Integer.parseInt(inputLine[1]), inputLine[2], inputLine[3], inputLine[4], null);

		//check if a student already has an account
		Student found = studentsList.findNode(student.getID());

		if (found == null){
			//insert student in studentList
			studentsList.insert(student);
			//incrementing number of student
			Student.setNumStudents(Student.getNumStudents() + 1);

			//printing the output
			System.out.printf("\tName:          %s %s%n\tStudent ID:    %d%n\tPhone Number:  %s%n\tBalance:       $%1.2f%n", 
					student.getFirstName(), student.getLastName(), student.getID(), student.getPhoneNumber(), student.getBalance());
		}
		//if the new added student is already in the list
		else{
			System.out.printf("\tStudent with ID %d already have account in FSC Telecom System.%n", student.getID());
		}

		System.out.println(" ");
	}

	public static void makeCall(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class

		System.out.println("Command: MAKECALL");
		
		//find account
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));
		
		//check if the list is null
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform MAKECALL. Account does not exist in FSC Telecom System.%n");
		}
		//the list is not null 
		else{
			//get available balance
			double prevBalance = found.getBalance();

			//check if current balance is less then rate per minute
			if(prevBalance < ratePerMinute){
				//printing the output
				System.out.printf("\tCannot perform MAKECALL. Account has insufficient balance.%n");
			}

			//current balance is more then rate per minute
			else{
				//check available balance
				int callDuration = Integer.parseInt(inputLine[3]);
				//calculating the cost of the call
				double callCost = callDuration * ratePerMinute;
				//we use new balance for displaying the end balance after the call
				double newBalance = 0;


				//check if cost of the call is less or equal to current balance
				if(callCost <= prevBalance){ //ok
					//set new balance to be the difference between current balance and call cost
					newBalance = prevBalance - callCost;
					
					System.out.printf("\tName:           %s %s%n\tPhone Number:   %s%n\tNumber Called:  %s%n\tCall Duration:  %d minutes%n\tPrev Balance:   $%1.2f%n\tCall Cost:      $%1.2f%n\tNew Balance:    $%1.2f%n", 
					found.getFirstName(), found.getLastName(), found.getPhoneNumber(), inputLine[2], callDuration, prevBalance, callCost, newBalance);

				}

				//the call cost is more than current balance, so we will make the call happen for however much money on balance there is
				else{
					//calculate for how many minutes we can have the call 
					callDuration = (int)(prevBalance / ratePerMinute);
					//calculate the call cost
					callCost = callDuration * ratePerMinute;
					//update the new balance after the call is made
					newBalance = prevBalance - callCost;
					//terminate the call because of low balance
					System.out.printf("\tName:           %s %s%n\tPhone Number:   %s%n\tNumber Called:  %s%n\tCall Duration:  %d%n\tPrev Balance:   $%1.2f%n\tCall Cost:      $%1.2f%n\tNew Balance:    $%1.2f%n", 
					found.getFirstName(), found.getLastName(), found.getPhoneNumber(), inputLine[2], callDuration, prevBalance, callCost, newBalance);
					
					System.out.printf("\t***Call terminated due to low balance.%n");
				}

				//update account
				found.setBalance(newBalance);
				found.addCalledNumber(inputLine[2]);
				found.addCalledDuration(callDuration);
			}
		}

		System.out.println(" ");
	}

	public static void sendText(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		System.out.println("Command: SENDTEXT");
		
		//find account
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//if list is empty
		if (found == null){
			//printing the output
			System.out.printf("\tCannot perform SENDTEXT. Account does not exist in FSC Telecom System.%n");
		}
		//list is not empty
		else{
			//get available balance
			double prevBalance = found.getBalance();

			//check if available balance is less than sms rate
			if(prevBalance < smsRate){//it is less
				//printing the output
				System.out.printf("\tCannot perform SENDTEXT. Account has zero balance.%n");
			}
			//current balance is bigger than sms rate
			else{
				//decrease current balance and set it to new balance
				double newBalance = prevBalance - smsRate;

				//update account
				found.setBalance(newBalance);
				found.addTextedNumber(inputLine[2]);

				//printing the output
				System.out.printf("\tName:           %s %s%n\tPhone Number:   %s%n\tNumber Texted:  %s%n\tPrev Balance:   $%1.2f%n\tText Cost:      $%1.2f%n\tNew Balance:    $%1.2f%n", 
					found.getFirstName(), found.getLastName(), found.getPhoneNumber(), inputLine[2], prevBalance, smsRate, newBalance);
			}
			
		}
		
		System.out.println(" ");
	}

	public static void useData(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		System.out.println("Command: USEDATA");

		//finding account
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//check if the list is empty
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform USEDATA. Account does not exist in FSC Telecom System.%n");
		}
		else{//the list is not empty
			//get available balance in the account
			double prevBalance = found.getBalance();
			
			//Calculate data cost
			int kb = Integer.parseInt(inputLine[2]);
			double dataCost = Math.ceil(kb * dataRatePerKB * 100000.0) / 100000.0;
			dataCost = ((int)(dataCost * 100)) / 100.0;

			//check if current balance in the account is less than cost of data
			if(prevBalance < dataCost){
				//printing the output
				System.out.printf("\tCannot perform USEDATA. Account does not have enough balance.%n");
			}
			else{//current balance is more or equal to cost of data
				//set new balance to be the difference between current balance and data cost
				double newBalance = prevBalance - dataCost;

				//update the account
				found.setBalance(newBalance);

				//printing the output
				System.out.printf("\tName:           %s %s%n\tPhone Number:   %s%n\tKilobytes:      %d%n\tPrev Balance:   $%1.2f%n\tData Cost:      $%1.2f%n\tNew Balance:    $%1.2f%n", 
					found.getFirstName(), found.getLastName(), found.getPhoneNumber(), kb, prevBalance, dataCost, newBalance);
			}
		}

		System.out.println(" ");
	}

	public static void recharge(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		System.out.println("Command: RECHARGE");
		
		//finding the account
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//checking if the list is empty
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform RECHARGE. Account does not exist in FSC Telecom System.%n");
		}
		else{//the list is not empty
			//get available balance
			double prevBalance = found.getBalance();

			//get recharge amount
			int recharge = Integer.parseInt(inputLine[2]);

			//adding recharge to current balance and setting it up to new balance variable
			double newBalance = prevBalance + recharge;

			//update account
			found.setBalance(newBalance);

			//printing the output
			System.out.printf("\tName:            %s %s%n\tPhone Number:    %s%n\tRecharge Amount: $%1.2f%n\tNew Balance:     $%1.2f%n", 
				found.getFirstName(), found.getLastName(), found.getPhoneNumber(), (double)recharge, newBalance);

		}

		System.out.println(" ");
	}

	public static void deleteAccount(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		// Note that this method should use the delete() method of the Linked List class
		System.out.println("Command: DELETEACCOUNT");

		//finding the account in the list
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//if the list is empty
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform DELETEACCOUNT. Account does not exist in FSC Telecom System.%n");
		}
		else{//llist is not empty
			//deleting one student from list of students
			studentsList.delete(found.getID());
			//decrementing student list for one
			Student.setNumStudents(Student.getNumStudents() - 1);

			//printing the output
			System.out.printf("\tName:           %s %s%n\tStudent ID:     %d%n\tPhone Number:   %s%n\tBalance:        $%1.2f%n", 
				found.getFirstName(), found.getLastName(), found.getID(), found.getPhoneNumber(), found.getBalance());
			System.out.printf("\t***Account has been deleted.%n");
		}

		System.out.println(" ");
	}

	public static void search(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		System.out.println("Command: SEARCH");

		//finding the account from the list
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//check if the list is empty
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform SEARCH. Account does not exist in FSC Telecom System.%n");
		}
		else{//it is not empty
			//printing the output
			System.out.printf("\tName:          %s %s%n\tStudent ID:    %d%n\tPhone Number:  %s%n\tBalance:       $%1.2f%n", 
				found.getFirstName(), found.getLastName(), found.getID(), found.getPhoneNumber(), found.getBalance());
		}

		System.out.println(" ");
	}

	public static void displayDetails(FSCTelecomAccounts accounts, String[] inputLine) {
		// Note that this method should use the search() or findNode() method of the Linked List class
		System.out.println("Command: DISPLAYDETAILS");

		//finding the account from the list
		Student found = studentsList.findNode(Integer.parseInt(inputLine[1]));

		//check if the list is empty
		if(found == null){
			//printing the output
			System.out.printf("\tCannot perform DISPLAYDETAILS. Account does not exist in FSC Telecom System.%n");
		}
		else{//it is not empty
			//printing the output
			System.out.printf("\tName:          %s %s%n\tStudent ID:    %d%n\tPhone Number:  %s%n\tCalled Numbers and Duration:%n", 
				found.getFirstName(), found.getLastName(), found.getID(), found.getPhoneNumber(), found.getBalance());


			//counter for numbers that have been called
			int calledNo = 0;
			//traversing through the array of called numbers until it reaches the end of that array
			for (int i = 0; i < found.getCalledNumbers().length; i++){
				if (found.getCalledNumbers()[i] != null) {
					//increase the counter
					calledNo += 1;
					//print the number that is called and duration of that call both in the [i] spot of the array
					System.out.printf("\t\t%s (%d)%n", found.getCalledNumbers()[i], found.getCallDuration()[i]);
				}
			}
			if (calledNo == 0) //check if the user didnt make any calls yet
			//printing the output
			System.out.printf("\t\t(user has not made any calls yet)%n");


			//printing all the texted numbers
			System.out.printf("\tTexted Numbers:%n");
			//counter for the numbers that have been texted
			int textedNo = 0;
			//traversing through the array of texted numbers until it reaches the end of that array
			for(int i = 0; i < found.getTextedNumbers().length; i++){
				//if the array in the [i] spot is not empty
				if(found.getTextedNumbers()[i] != null){
					//increase counter
					textedNo += 1;
					//printing the number that is texted in [i] spot of an array
					System.out.printf("\t\t%s%n", found.getTextedNumbers()[i]);
				}
			}
			if (textedNo == 0) System.out.printf("\t\t(user has not made any texts yet)%n");

		}

		System.out.println(" ");
	}

	//*************//
	// MAIN METHOD //
	//*************//
	public static void main(String[] args) throws Exception {
        

		// Variables needed for program
		String[] inputLine; // used to save the command read from input file
		Scanner in = new Scanner(System.in);

		// Make linked-list
		FSCTelecomAccounts accounts = new FSCTelecomAccounts();
		// ^ ^ ^ ^ ^
		// | | | | |
		// | | | | |     "accounts" is the reference variable that points
		//               to your linked-list of Student objects
		
		// MAIN DO/WHILE LOOP:
		//    - We read commands and then process the commands by calling
		//      the appropriate methods.
		while (true) {
			inputLine = in.nextLine().split(" ");

			// ADDACCOUNT
			if (inputLine[0].equals("ADDACCOUNT") == true) {
				addAccount(accounts, inputLine);
				// NOTE: what are we sending to this method?
				//       Two things:
				//       1. a reference to the linked list (accounts)
				//       2. a reference to the array which has the inputLine
			}

			// MAKECALL
			else if (inputLine[0].equals("MAKECALL") == true) {
				makeCall(accounts, inputLine);
			}

			// SENDTEXT
			else if (inputLine[0].equals("SENDTEXT") == true) {
				sendText(accounts, inputLine);
			}

			// USEDATA
			else if (inputLine[0].equals("USEDATA") == true) {
				useData(accounts, inputLine);
			}

			// RECHARGE
			else if (inputLine[0].equals("RECHARGE") == true) {
				recharge(accounts, inputLine);
			}

			// DELETEACCOUNT
			else if (inputLine[0].equals("DELETEACCOUNT") == true) {
				deleteAccount(accounts, inputLine);
			}

			// SEARCH
			else if (inputLine[0].equals("SEARCH") == true) {
				search(accounts, inputLine);
			}

			// DISPLAYDETAILS
			else if (inputLine[0].equals("DISPLAYDETAILS") == true) {
				displayDetails(accounts, inputLine);
			}

			// Command QUIT: Quit the Program
			else if (inputLine[0].equals("QUIT") == true) {
				System.out.println("Command: QUIT.");
				System.out.println("\tExiting the FSC Telecom System...");
				System.out.println("\tGoodbye.");
				break;
			}

			// Invalid Command --- this will NEVER occur if you coded things correctly
			else {
				System.out.println("Invalid Command: input invalid.");
			}

		} // END while loop

	} // END main() method
}
