/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CSregistrations {
    private CSstudent head;

     // CONSTRUCTORS
     public CSregistrations() {
        this.head = null;
    }
	
    public CSstudent getHead() {
        return head;
    }

    public void setHead(CSstudent head) {
        this.head = head;
    }

	/* Below are MANY methods that are used on Linked Lists.
	 * 
	 * Examples:
	 * search, insert, delete, isEmpty, sumNodes, and many more
	 */
	
	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return head == null;
	}
	
	
	//
	// boolean | search(int)
	//
	public boolean search(CSstudent data) {
		return search(head, data);
	}
	//
	// boolean | search(LLnode, int)
	//
	private boolean search(CSstudent p, CSstudent data) {
		CSstudent helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr == data)
				return true;
			helpPtr = helpPtr.getNext();			
		}
		return false;
	}


	//
	// LLnode | findNode(int)
	//
	public CSstudent findNode(CSstudent data) {
		return findNode(head, data);
	}
	//
	// LLnode | findNode(LLnode, int)
	//
	private CSstudent findNode(CSstudent p, CSstudent data) {
		CSstudent helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr == data)
				return helpPtr;
			helpPtr = helpPtr.getNext();			
		}
		return null;
	}
	
	
	//
	// void | PrintList()
	//
	public void PrintList() {
		PrintList(head);
	}
	//
	// void | PrintList(LLnode)
	//
	private void PrintList(CSstudent head) {
		// We need to traverse...so we need a help ptr
		CSstudent helpPtr = head;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("h:mm a");
		// Traverse to correct insertion point
		while (helpPtr != null) {

			int hours = 12 + (int)(helpPtr.getTimeRegistered()/60);
			int mins = helpPtr.getTimeRegistered()%60;
			LocalTime time = LocalTime.of(hours, mins);

			// Printing the data of the student 
			System.out.printf("%s, %s, ID # %d%n", helpPtr.getLastName(), helpPtr.getFirstName(), helpPtr.getID());
			System.out.printf("\tTime Registered:  " + time.format(dtf) + "%n");
			System.out.printf("\tClasses:%n");
			for (int i = 0; i < helpPtr.getNumCourses(); i++) {
				System.out.printf("\t| %-8s | %-5s | %-19s |%n", helpPtr.getCourses()[i].getCourseNumber(), helpPtr.getCourses()[i].getCourseDays(), helpPtr.getCourses()[i].getCourseTime());
			}
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		System.out.println();
	}
	

	
	//
	// int | sumNodes()
	//
	public int sumNodes() {
		return sumNodes(head);
	}
	//
	// int | sumNodes(BSTnode)
	//
	private int sumNodes(CSstudent head) {
		// We need to traverse...so we need a help ptr
		CSstudent helpPtr = head;
		int sum = 0; // counter
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Increase sum
			sum ++;
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		return sum;
	}
	
	
	//
	// void | insert(int)
	//
	public void insert(CSstudent data) {
		head = insert(head, data);
	}
	//
	// LLnode | insert(LLnode, value)
	//
	private CSstudent insert(CSstudent head, CSstudent data) {
		// IF there is no list, newNode will be the first node, so just return it
		if (head == null || head.getLastName().compareToIgnoreCase(data.getLastName()) > 0) {
			head = new CSstudent(data, head);
			return head;
		}
		
		// ELSE, we have a list. Insert the new node at the correct location
		else {
			// We need to traverse to the correct insertion location...so we need a help ptr
			CSstudent helpPtr = head;
			// Traverse to correct insertion point
			while (helpPtr.getNext() != null) {
				if (helpPtr.getNext().getLastName().compareToIgnoreCase(data.getLastName()) > 0)
					break; // we found our spot and should break out of the while loop
				helpPtr = helpPtr.getNext();
			}
			// Now make the new node. Set its next to point to the successor node.
			// And then make the predecessor node point to the new node
			CSstudent newNode = new CSstudent(data, helpPtr.getNext());
			helpPtr.setNext(newNode);
		}
		// Return head
		return head;
	}
}
