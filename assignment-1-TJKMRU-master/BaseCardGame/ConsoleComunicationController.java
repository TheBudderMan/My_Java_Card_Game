/**
 * Handles basic input/output with the console 
 * 
 * @author JKidney
 * @version Feb 28th, 2012
 */

import java.util.*;

public class ConsoleComunicationController
{
    public static final char NO_CHAR_INPUT = ' ';	//default for character input
    private  Scanner input;

    /**
     * default constructor
     */
    public ConsoleComunicationController()
    {
        input = new Scanner(System.in); 
    }

    /**
     * helper method to contain printing of messages to the console
     * @param message the message to print
     */
    public void print(String message)
    {
        System.out.print(message); 
    }
    
    /**
     * helper method to contain printing of messages to the console with a new line
     * @param message the message to print
     */
    public void println(String message)
    {
        System.out.println(message); 
    }

    /**
     * Displays a message and waits to read an integer from the user
     * @param message the message to display
     * @return the integer inputed by the user
     */
    public int getInput_Int(String message)
    {
        int userInput = 0;
        print(message);
        userInput = input.nextInt();
        input.nextLine();
        return userInput;   
    }

   /**
     * gets a double from the input without displaying a message
     * @param message the message to display
     * @return the entered double
     */
    public double getInput_Double(String message)
    {
        double userInput = 0;
        print(message);
        userInput = input.nextDouble();
        input.nextLine();
        return userInput;   
    }
    
    /**
     * clears one line from the input console
     */
    public void clearInputLine()
    {
        input.nextLine();  
    }

    /**
     * gets an integer from the input without displaying a message
     * @return the entered integer
     */
    public int getInput_Int()
    {
        int userInput = 0;
        userInput = input.nextInt();
        input.nextLine();
        return userInput;   
    }

   
    /**
     * Displays a message and waits to read a line of text from the user
     * @param message the message to display
     * @return the line inputed by the user
     * */
    public String getInput_String(String message)
    {
        print(message);
        return input.nextLine().trim();
    }

    /**
     * reads one character from the input
     * @param message the message to display
     * @return the entered character or NO_CHAR_INPUT if no input
     */
    public char getInput_char(String message)
    {
        char result = NO_CHAR_INPUT;
        print(message);
        String inputLine = input.next().trim().toLowerCase();

        if(inputLine.length() > 0)
            result = inputLine.charAt(0);

        return result;
    }

  
}
