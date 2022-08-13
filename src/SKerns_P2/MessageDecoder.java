/*
 * Stewart Kerns
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package SKerns_P2;

import java.io.*;
import java.util.Scanner;

/**
 * This TODO Put in a class description
 *
 * @author Stewart Kerns
 * @version 1.0
 */
public class MessageDecoder {
    /**
     * This class is to create a Node for a linked list
     *
     * @author Stewart Kerns
     * @version 1.0
     */
    private class Node {
        //declare an integer value
        int value;
        char character;
        //declare a Node next
        Node next;


        /**
         * This constructor set the values of value and next for the node
         *
         * @param value an integer input for the value of the node
         * @param next  the next node
         */
        Node(int value, char character, Node next) {
            //set the value
            this.value = value;
            //set the character
            this.character = character;
            //set the next
            this.next = next;
        }

        /**
         * This constructor only takes in the value for the node and sets it
         * to value, it sets next to null
         *
         * @param value and integer input for the value of the node
         */
        Node(int value, char character) {
            //set the value
            this.value = value;
            //set the character
            this.character = character;
            //set next to null
            this.next = null;
        }
    }

    //create a field for the first node
    private Node first;
    //create a field for the last node
    private Node last;

    /**
     * This constructor sets both first and last to null
     */
    public MessageDecoder() {
        //set first to null
        first = null;
        //set last to null
        last = null;
    }


    /**
     * This method checks to see if the linked list is empty and if so, it
     * returns true
     *
     * @return boolean value for if the linked list is empty
     */
    private boolean isEmpty() {
        return (first == null);
    }

    /**
     * This method takes in a number and then creates a node for it and places
     * it into the linked list in a sorted position from lowest to highest
     *
     * @param number an integer input for the new node
     */
    private void insertInOrder(int number, char character) {
        //sets the current node to first
        Node current = first;
        //if the linked list is empty, it sets the first and last node to the
        // number input
        if (isEmpty()) {
            last = new Node(number, character);
            first = last;
        }

        //create a while loop to set the current node to the last value that is
        //less than number's value
        while ((current != null) && (current.value < number)) {
            //move current to the next node
            current = current.next;
        }

        //if the number is smaller than the current first node, set the number
        //to the first node and set the pointer to the previous first
        if (first.value > number) {
            //set first equal to a new node for number
            first = new Node(number, character);
            //set the point to the current node
            first.next = current;
        }

        //if the number is larger than all the other node values, set it to the
        //last node
        else if (current == null && first.value != number) {
            //set the node for number to the node after last
            last.next = new Node(number, character);
            //move the pointer for last to the new node for number
            last = last.next;
        }

        //if the node for number needs to fall somewhere in the middle of first
        //and last, the else executes
        else {
            //create a node for previous which will be one less than current
            Node previous = first;

            //create a while loop to loop until previous is one less than
            //current
            while (previous.next != null && previous.next.value <
                    current.value) {
                //set previous to the next node
                previous = previous.next;
            }

            //set the point from previous to the new node for number and use
            //the constructor to set the point from number node to current
            previous.next = new Node(number, character, current);
        }
    }


    /**
     * This method checks to make sure that the data being put in is valid
     * data, meaning the value of the integer input is larger than 0
     * @param number an integer for the data being input
     * @return boolean value of if the data is valid and good for use
     */
    private boolean numOver0(int number){
        //return if the number is larger than 0
        return number > 0;
    }

    /**
     * This method reads a file into the linked list by reading one line at a
     * time and then organizing the character by the number given by the file
     * @param filename the filename for the file to be read and passed in
     * @throws IOException
     */
    private void readFileIntoLinkedList(String filename) throws IOException{
        //create a File and Scanner object for reading the file
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        //declare a character and int that will be assigned to the node
        char character;
        int number;
        //declare a final int for the minimum length of the line
        final int STRING_MIN = 3;

        //create a while loop to run until the end of the file
        while (inputFile.hasNext()){
            //put the entire line into a string
            String line = inputFile.nextLine();

            //if the string is longer than the min length, move into putting the
            //data from the line into a node of the linked list
            if (line.length() >= STRING_MIN) {
                //take the first char of the string in for the character
                character = line.charAt(0);
                //separate the number area of the line and then pass it into
                //parstInt in order to convert it to a number
                number = Integer.parseInt(line.substring(2));

                //if the number is a valid number and not a repeat, insert it
                //into the linked list
                if (numOver0(number) && doesNotContainValue(number)) {
                    insertInOrder(number, character);
                }
            }
        }
        //close the input file
        inputFile.close();
    }

    /**
     * This method takes in a filename for a coded message and returns a string
     * of the decoded message
     * @param filename the filename of the coded message
     * @return String of the message decoded
     * @throws IOException
     */
    public String getPlainTextMessage(String filename)throws IOException {
        //pass the filename to readFileIntoLinkedList in order to create the
        //linked list
        readFileIntoLinkedList(filename);

        //create a node to traverse through the linked list
        Node p = first;
        //create a string that will be used to add each character into
        String builtString = "";

        //traverse the linked list and add each character in
        while (p != null){
            builtString += p.character;
            p = p.next;
        }

        //return the string after the linked list has been trasferred
        return builtString;
    }

    /**
     * This method returns a boolean of whether it was able to find value of
     * number in the linked list
     * @param number an int that the method will be searching for
     * @return boolean of if the value was found or not
     */
    private boolean doesNotContainValue(int number){
        //create a node for traversing through the linked list
        Node p = first;

        //traverse through the linked list and search for the number
        while (p != null){
            //return false if the number is found
            if (p.value == number){
                return false;
            }
            p = p.next;
        }

        //return true if the number is not found in the linked list values
        return true;
    }

    /**
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     * @param filename file name string to check
     * @return true if file exists on disk and is not a directory
     */
    public static boolean isValidFile(String filename) {
        //create a File object with the filename
        File path = new File(filename);

        //create a boolean for if the file exists and output a message if not
        boolean isValid = path.exists() && !path.isDirectory();
        if (!isValid) {
            System.out.println("This is not a valid filename.");
        }
        //return the boolean value for if it is valid or not
        return isValid;
    }

    /**
     * This method clears the linked list of all values by setting first to null
     */
    public void clearLinkedList(){
        //set first to null
        first = null;
        //set last to first
        last = first;
    }

}
