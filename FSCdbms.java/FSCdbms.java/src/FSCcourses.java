/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class FSCcourses {
    private FSCcourse head;

    //constructors
    public FSCcourses() {
        this.head = null;
    }

    public FSCcourses(FSCcourse head) {
        this.head = head;
    }
    
    //getter and setter
	public FSCcourse getHead() {
		return head;
	}

    public void setHead(FSCcourse head) {
        this.head = head;
    }

    
    /* Below are MANY methods that are used on Linked Lists.
	 * 
	 * Examples:
	 * search, insert, delete, isEmpty, sumNodes, and many more
	 */
	
	//
	// calculate GPA
	//
    public double calculateGPA() {
        return calculateGPA(head);
    }

    //method for calculating the gpa
    private double calculateGPA(FSCcourse head) {
        FSCcourse helpPtr = head;

        double subtotal = 0; 
		int hours = 0;
		while (helpPtr != null) {
			int grade = helpPtr.getGrade();
			int points = 0;

            //depending on what the student's grade is he gets certain amount of points
			if (grade >= 90) points = 4; // A
			else if (grade < 90 && grade >= 80) points = 3; // B
			else if (grade < 80 && grade >= 70) points = 2; // C
			else if (grade < 70 && grade >= 60) points = 1; // D
			else points = 0; // F

			// Increase sum
			subtotal += points * 4;
			hours += 4;

			// Step one node over
			helpPtr = helpPtr.getNext();
		}

        //if number of course hours is more than 0 return divide subtotal with number of hours to get GPA
		if (hours > 0) 
        return subtotal / hours;

        //if it's less than 0 return -1
		else 
        return -1;
    }


	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return head == null;
	}
	

	//
	// boolean | search(FSCcourse)
	//
	public boolean search(FSCcourse data) {
		return search(head, data);
	}
	//
	// boolean | search(LLnode, FSCcourse)
	//
	private boolean search(FSCcourse p, FSCcourse data) {
		FSCcourse helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr == data)
				return true;
			helpPtr = helpPtr.getNext();			
		}
		return false;
	}
	
	//
	// boolean | search(String)
	//
	public FSCcourse searchByID(String id) {
		return search(head, id);
	}
	//
	// boolean | search(FSCcourse, String)
	//
	private FSCcourse search(FSCcourse p, String id) {
		FSCcourse helpPtr = p;
		while (helpPtr != null) {
			if (helpPtr.getID().equals(id))
				return helpPtr;
			helpPtr = helpPtr.getNext();			
		}
		return null;
	}


	//
	// FSCcourse | findNode(FSCcourse)
	//
	public FSCcourse findNode(FSCcourse data) {
		return findNode(head, data);
	}
	//
	// FSCcourse | findNode(FSCcourse, FSCcourse)
	//
	private FSCcourse findNode(FSCcourse p, FSCcourse data) {
		FSCcourse helpPtr = p;
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
	// void | PrintList(FSCcourse)
	//
	private void PrintList(FSCcourse head) {
		System.out.println("\tAll courses saved in FSCdbms:");
        System.out.println("\tCOURSE NAME         AVERAGE GRADE");

		// We need to traverse...so we need a help ptr
		FSCcourse helpPtr = head;
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Print the data value of the node and average grade
			System.out.printf("\t%-20s%5d%n",helpPtr.getID(), (int)(helpPtr.getGrade() / helpPtr.getNum()));
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
	}
	
	
	//
	// int | sumNodes()
	//
	public int countNodes() {
		return countNodes(head);
	}
	//
	// int | sumNodes(BSTnode)
	//
	private int countNodes(FSCcourse head) {
		// We need to traverse...so we need a help ptr
		FSCcourse helpPtr = head;
		int count = 0; // counter
		// Traverse to correct insertion point
		while (helpPtr != null) {
			// Increase sum
			count += 1; //helpPtr.getData();
			// Step one node over
			helpPtr = helpPtr.getNext();
		}
		return count;
	}
	
	
	//
	// void | insert(FSCcourse)
	//
	public void insert(FSCcourse data) {
		head = insert(head, data);
	}
	//
	// FSCcourse | insert(FSCcourse, value)
	//
	private FSCcourse insert(FSCcourse head, FSCcourse data) {
		// IF there is no list, newNode will be the first node, so just return it
		if (head == null || head.getID().compareToIgnoreCase(data.getID()) > 0) {
			head = new FSCcourse(data, head);
			return head;
		}
		
		// ELSE, we have a list. Insert the new node at the correct location
		else {
			// We need to traverse to the correct insertion location...so we need a help ptr
			FSCcourse helpPtr = head;
			// Traverse to correct insertion point
			while (helpPtr.getNext() != null) {
				if (helpPtr.getNext().getID().compareToIgnoreCase(data.getID()) > 0)
					break; // we found our spot and should break out of the while loop
				helpPtr = helpPtr.getNext();
			}
			// Now make the new node. Set its next to point to the successor node.
			// And then make the predecessor node point to the new node
			FSCcourse newNode = new FSCcourse(data, helpPtr.getNext());
			helpPtr.setNext(newNode);
		}
		// Return head
		return head;
	}
	
	
	//
	// void | delete(FSCcourse)
	//
	public void delete(FSCcourse data) {
		head = delete(head, data);
	}
	//
	// FSCcourse | delete(FSCcourse, value)
	//
	private FSCcourse delete(FSCcourse head, FSCcourse data) {
		// We can only delete if the list has nodes (is not empty)
		if (!isEmpty()) {
			// IF the first node (at the head) has the data value we are wanting to delete
			// we found it. Delete by skipping the node and making head point to the next node.
			if (head == data) {
				head = head.getNext();
			}
			// ELSE, the data is perhaps somewhere else in the list...so we must traverse and look for it
			else {
				// We need to traverse to find the data we want to delete...so we need a help ptr
				FSCcourse helpPtr = head;
				// Traverse to correct deletion point
				while (helpPtr.getNext() != null) {
					if (helpPtr.getNext().getID().compareToIgnoreCase(data.getID()) == 0) {
						helpPtr.setNext(helpPtr.getNext().getNext());
						break; // we deleted the value and should break out of the while loop
					}
					helpPtr = helpPtr.getNext();
				}
			}
			// return the possibly updated head of the list
			return head;
		}
		return head;
	}



}
