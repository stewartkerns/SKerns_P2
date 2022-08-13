/*
 * Stewart Kerns
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package SKerns_P2;

import java.io.*;
import java.util.Scanner;

/**
 * This...................................................
 *
 * @author Stewart Kerns
 * @version 1.0
 */
public class MessageDecoder {
    /**
     * This class is to create a Node for a linked list
     *
     * @author Stewart Kerns
     * @verion 1.0
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
     * This method returns an integer value for the size of the linked list
     * @return int value representing the size of the linked list
     */
    public int size(){
        //create a count
        int count = 0;
        //Create a Node object for traversing
        Node p = first;

        //traverse through the linked list
        while (p != null){
            //increment count for each node
            count++;
            p = p.next;
        }

        //return the count
        return count;
    }

    /**
     * This method removes any duplicate values from the linked list
     */
    public void removeDuplicate(){
        //create nodes for the first and second values in the linked list
        Node current = first.next;
        Node pred = first;

        //create a for loop to traverse through the linked list
        for (int i = 1; i < size(); i++) {

            //if the pred and current node are the same and current isn't null,
            // delete current
            while (current != null && current.value == pred.value) {
                pred.next = current.next;

                //for setting last if the list has been fully gone through
                if (current.next == null) {
                    last = pred;
                }

                //move current but stay in the while loop to check for multiple
                //multiples
                current = pred.next;
            }

            //if current isn't null, move to the next two nodes
            if (current != null) {
                pred = pred.next;
                current = current.next;
            }
        }
    }

    /**
     * This method checks to see if the linked list is empty and if so, it
     * returns true
     *
     * @return boolean value for if the linked list is empty
     */
    public boolean isEmpty() {
        return (first == null);
    }

    /**
     * This method takes in a number and then creates a node for it and places
     * it into the linked list in a sorted position from lowest to highest
     *
     * @param number an integer input for the new node
     */
    public void insertInOrder(int number, char character) {
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
     * Checks to see that the user-specified file name refers to a valid
     * file on the disk and not a directory. Displays an error message to the
     * user if that is not the case.
     * @param fileName file name string to check
     * @return true if file exists on disk and is not a directory
     */
    private static boolean isValidFile(String fileName) {
        File path = new File(fileName);
        boolean isValid = path.exists() && !path.isDirectory();
        if (!isValid) {
            // TODO: Display a proper error message
        }
        return isValid;
    }

    /**
     * This method checks to make sure that the data being put in is valid
     * data, meaning the value of the integer input is larger than 0
     * @param number an integer for the data being input
     * @return boolean value of if the data is valid and good for use
     */
    private boolean isGoodData(int number){
        return number > 0;
    }

    private void readFileIntoLinkedList(String fileName) throws IOException{
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);
        char character;
        int number;
        while (inputFile.hasNext()){
            String line = inputFile.nextLine();
            character = line.charAt(0);
            number = Integer.parseInt(line.substring(2));
//            number = line.charAt(2) - '0';



            System.out.println(character);
            System.out.println("This is number: " + number);
//            System.out.println("This is a test of multiplication: " + (number * number));
            if (isGoodData(number)){
                insertInOrder(number, character);
            }
        }
    }

    /**
     * This method prints out the contents of the linked list
     */
    public void printLinkedList() {
        //set the current node to first
        Node current = first;

        //loop through the contents until the end and print each number
        while (current != null) {
            System.out.println(current.character);
            //move current to the next value
            current = current.next;
        }
    }

    public void testPrint(String fileName) throws IOException{
        readFileIntoLinkedList(fileName);
        printLinkedList();

    }

}
