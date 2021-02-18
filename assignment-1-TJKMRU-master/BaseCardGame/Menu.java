import java.util.ArrayList;
//import java.util.Scanner;

/**
 * Used to manage printing a menu of choices and getting the proper selected choice from the user
 * @author jkidney
 * @version March 11, 2013 
 */
public class Menu 
{
	private ConsoleComunicationController comm;
	private ArrayList<MenuOption> menuChoices;

	/**
	 * Default Constructor
	 */
	public Menu(ConsoleComunicationController comm)
	{
		this.comm = comm;
		menuChoices = new ArrayList<MenuOption>();
	}

	public Menu()
	{
		comm = new ConsoleComunicationController();
		menuChoices = new ArrayList<MenuOption>();
	}

	
	/**
	 * Adds a menu option that the user can select. This method does not 
	 * check for duplicate choices/options
	 * 
	 * @param option the option object to add to the possible choices 
	 */
	public void addMenuOption(MenuOption option)
	{
		menuChoices.add(option);  
	}


	/**
	 * Prints all the choices to the user and gets a valid choice from the user.
	 * @return the selected option (based upon the options that have been given to the user)
	 */
	public char getUserChoice()
	{
		char selection = ' ';
        boolean end = false;
		//keep asking for the choice until they enter to correct value
		do
		{
		    comm.println("Menu: ");
			//print out all options
			for(MenuOption option: menuChoices )
				comm.println(option.toString());
			
			//get user choice
			selection = Character.toUpperCase( comm.getInput_char("Enter choice: ") );
		
			if(isValidChoice(selection) == false)
			{
				comm.println("Error: invalid choice");
			}
			else
				end = true;
		
			
		}while(!end);

		return selection;
	}


	/**
	 * Determines if the given choice is valid based upon the current options in the menu
	 * @param choice the choice made by the user
	 * @return true if it is a valid choice, false otherwise
	 */
	private boolean isValidChoice(char choice)
	{
		boolean matchFound = false;
		
		for(MenuOption option: menuChoices )
		{
			if(option.isAMatch(choice) )
			{
				matchFound = true;
				break;
			}
			
		}
		
		return matchFound;
	}
}

