/*
 * Stewart Kerns
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package SKerns_P2;

//import the io wildcard
import java.io.*;
//import the Scanner class
import java.util.Scanner;

/**
 * TODO write a description
 */
public class SecretMessage {

    /**
     * TODO write a description
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //create a Scanner object for taking in user input
        Scanner keyboardIn = new Scanner(System.in);
        //create a new MessageDecoder object
        MessageDecoder newMessage = new MessageDecoder();

        //declare Strings for the decoded message and filename
        String decodedMessage, filename;

        //welcome the user to the program
        welcome();

        //use a do while loop to allow the user to run the program as many
        //times as they want
        do {
            //Ask the user for the filename they would like to decode
            filename = getUserFilename(keyboardIn);
            //decode the message in the requested filename
            decodedMessage = newMessage.getPlainTextMessage(filename);

            //print out the decoded message
            System.out.println("\nThe decoded message is written below:");
            System.out.println(decodedMessage);

            //clear the message out of newMessage before asking if the user
            //would like to input another file
            newMessage.clearLinkedList();

            //ask the user if they want to repeat the program
        } while (repeatProgram(keyboardIn));

        //print a goodbye message to the user
        goodbye();
        //close the Scanner object
        keyboardIn.close();
    }

    /**
     * This method asks the user to input the filename of the file they would
     * like to use, it then checks that the file is valid and asks them again
     * if not
     * @param keyboardIn a Scanner object for taking in user input
     * @return String value of the filename
     */
    public static String getUserFilename(Scanner keyboardIn){
        //declare a String to hold the filename
        String filename;

        //create a do while loop to validate the users input
        do {
            //ask the user to input the filename
            System.out.print("\nPlease input the filename you would like to " +
                    "decode: ");
            filename = keyboardIn.nextLine();
            //validate that the filename is a valid path/file
        } while (!MessageDecoder.isValidFile(filename));

        //return the filename once it's valid
        return filename;
    }

    /**
     * This method asks the user if they want to repeat the program again and
     * only returns false if the user enters "no"
     * @param keyboardIn a Scanner object for taking in user input
     * @return boolean value for if they answered no or not
     */
    public static boolean repeatProgram(Scanner keyboardIn){
        //ask the user if they want to repeat the program
        System.out.print("\nWould you like to decode another secret message?" +
                "(enter no to quit): ");

        //return true if they enter anything other than "no"
        return !(keyboardIn.nextLine().equalsIgnoreCase("NO"));
    }

    /**
     * This method prints a welcome message to the user
     */
    public static void welcome(){
        System.out.println("This program reads an encoded message from a file" +
                " supplied by the user and\n displays the contents of the " +
                "message before it was encoded.");
    }

    /**
     * This method prints a goodbye message to the user
     */
    public static void goodbye(){
        System.out.println("Thank you for using the message decoder.");
    }

}
