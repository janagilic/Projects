/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class CSqueue {
    private CSstudent front;
    private CSstudent back;

    // CONSTRUCTOR
    public CSqueue() {
        front = null;
		back = null;
    }
	
	public CSstudent getFront() {
		return front;
	}


	public void setFront(CSstudent front) {
		this.front = front;
	}


	public CSstudent getBack() {
		return back;
	}


	public void setBack(CSstudent back) {
		this.back = back;
	}

	
	/* Below are MANY methods that are used on stacks.
	 * 
	 * Examples:
	 * isEmpty, PUSH, POP, PEEK, and more.
	 */
	
	
	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return front == null;
	}
	
	//
	// void | enqueue(CSstudent)
	//
	public void enqueue(CSstudent data) {
		if (isEmpty()) {
			front = back = enqueue(front, back, data);
		}
		else {
			back = enqueue(front, back, data);
		}
		
	}
	//
	// QueueNode | enqueue(QueueNode, QueueNode, int)
	//
	private CSstudent enqueue(CSstudent front, CSstudent back, CSstudent data) {
		// Make a new QueueNode with "data" as the data value
		CSstudent temp = new CSstudent(data);
		
		// Now, if the list is empty, return the reference for temp
		// and save this reference into both "front" and "back"
		// Why? Since this is the only node in the queue, it will be the front and back node
		if (isEmpty()) {
			return temp;
		}
		// ELSE, the queue is not empty. We need to insert temp at the back of the queue.
		// So save the address of the new node into the next of back.
		// Then, make back "traverse" one node over, so it now points to the new back node.
		// Finally, return the updated address of back.
		else {
			back.setNext(temp);
			back = back.getNext();
			return back;
		}
	}
	
	
	//
	// QueueNode | dequeue()
	//
	public CSstudent dequeue() {
		CSstudent temp = front;
		front = dequeue(front);
		if (front == null)
			back = null;
		return temp;
	}
	//
	// QueueNode | dequeue(QueueNode)
	//
	private CSstudent dequeue(CSstudent front) {
		front = front.getNext();
		return front;
	}
	
	
	//
	// int | peek()
	//
	public CSstudent peek() {
		// Invoke the peek method with front as a parameter
		CSstudent frontValue = peek(front);
		
		// return topValue
		return frontValue;
	}
	//
	// int | peek(QueueNode)
	//
	private CSstudent peek(CSstudent front) {
		// Return the data value of the front node.
		// You can see that we do NOT dequeue. We are only returning the data value.
		return front;
	}

}
