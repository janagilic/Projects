/*
 * Jana Gilic
 * 1271874
 * 9/19/2022
 * jana.gilic@gmail.com
 * Honor Code:
 * “I will practice academic and personal integrity and excellence of character and expect the same from others.” 
 */
public class Student{

        private int ID;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private double balance;
        private String[] calledNumbers;
        private int[] callDuration;
        private String[] textedNumbers;
        private static int numStudents;
        private Student next;

        public Student(int iD, String firstName, String lastName, String phoneNumber, Student next) {
            ID = iD;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.next = next;
            this.balance = 20;
            this.calledNumbers = new String[10];
            this.callDuration = new int[10]; 
            this.textedNumbers = new String[10];
            Student.numStudents += 1;
        }

        //Constructors
        public Student() {
            ID = 0;
            next = null;
        }
        
        public Student(int ID) {
            this.ID = ID;
            next = null;
        }
        
        public Student(int ID, Student next) {
            this.ID = ID;
            this.next = next;
        }

        //Getters and setters
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String[] getCalledNumbers() {
            return calledNumbers;
        }

        public void setCalledNumbers(String[] calledNumbers) {
            this.calledNumbers = calledNumbers;
        }

        public int[] getCallDuration() {
            return callDuration;
        }

        public void setCallDuration(int[] callDuration) {
            this.callDuration = callDuration;
        }

        public String[] getTextedNumbers() {
            return textedNumbers;
        }

        public void setTextedNumbers(String[] textedNumbers) {
            this.textedNumbers = textedNumbers;
        }

        public static int getNumStudents() {
            return numStudents;
        }

        public static void setNumStudents(int numStudents) {
            Student.numStudents = numStudents;
        }

        public Student getNext() {
            return next;
        }

        public void setNext(Student next) {
            this.next = next;
        }

        //Adding called number into an array
        public void addCalledNumber(String calledNum){
            String result[] = new String[10];
            for(int i = 0; i < 10; i++){
                result[i] = this.calledNumbers[i];
            }
            this.calledNumbers[0] = calledNum;
            for(int i = 1; i < 10; i++){
                this.calledNumbers[i] = result[i - 1];
            }
        }

        //adding duration of the call into an array
        public void addCalledDuration(int calledDur){
            int result[] = new int[10];
            for(int i = 0; i < 10; i++){
                result[i] = this.callDuration[i];
            }
            this.callDuration[0] = calledDur;
            for(int i = 1; i < 10; i++){
                this.callDuration[i] = result[i - 1];
            }
        }

        //adding texted number into an array 
        public void addTextedNumber(String textedNum){
            String result[] = new String[10];
            for(int i = 0; i < 10; i++){
                result[i] = this.textedNumbers[i];
            }
            this.textedNumbers[0] = textedNum;
            for(int i = 1; i < 10; i++){
                this.textedNumbers[i] = result[i - 1];
            }
        }
        
}