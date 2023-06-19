/*
 * Jana Gilic
 * 1271874
 * 11/2/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */

public class CSlaptopStack{
    private int[] stack;
    private int maxSize;
    private int top;

    // Constructor
	public CSlaptopStack(int size) {
		this.maxSize = size;             // set array size
		this.stack = new int[maxSize];   // create array for stack
		this.top = -1;                   // set top to -1 (no items in stack yet)
	}
	
	
	public int[] getStack() {
		return stack;
	}

	public void setStack(int[] stack) {
		this.stack = stack;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	
	//
	// boolean | isFull()
	//
	public boolean isFull () {
		return top == maxSize-1;
	}
	
	
	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return top == -1;
	}
	
	
	//
	// int | pop()
	//
	public int pop() {
		// temporarily save the value at top into "temp"
		int temp = stack[top];
		
		// decrement top
		top--;
		
		// return the value that WAS at the top
		return temp;
	}
	
	
	//
	// int | peek()
	//
	public int peek() {
		return stack[top];
	}
	
	
	//
	// void | push(int)
	//
	public void push(int value) {
		// increment the top pointer
		top++;
		
		// then save the value into stack[top]
		stack[top] = value;
	}

}