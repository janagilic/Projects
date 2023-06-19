//Jana Gilic
//Algorithm Analysis
//2/18/2023

import java.util.*;

public class TowerOfHanoi{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //asking user for input of the amount of disks they want
        System.out.print("Please enter the number of disks: ");
        int n = input.nextInt();
        
        //calling the method
        tower(n, 'A', 'B', 'C');
    }

    //recursive method to compute the steps of moving disks
    public static void tower(int n, char start, char temp, char end){
        if(n == 1)
            System.out.println("Move disk number " + n + " from " + start + " pole to " + end + " pole.");
        else{
            tower(n-1, start, end, temp);
            System.out.println("Move disk number " + n + " from " + start + " pole to " + end + " pole.");
            tower(n-1, temp, start, end);
        }
    }
}