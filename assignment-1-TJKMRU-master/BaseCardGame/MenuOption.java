/**
 * Represents a single menu option that can be printed and selected by the user
 * @author  jkidney
 * @version march 11, 2013 
 */
public class MenuOption 
{
	private char option;
	private String description;


	/**
	 * Constructor 
	 * @param option the option/character the user will select
	 * @param description the description for this option
	 */
	public MenuOption(char option, String description) 
	{
		this.option = Character.toUpperCase(option);
		this.description = description;
	}

	/**
	 * Determines if the given character matches the current menu option object
	 * @param usersChoice the char given by the user
	 * @return true for a match, false otherwise
	 */
    public boolean isAMatch(char usersChoice )
    {
      return (option == usersChoice);	
    }
    
	/**
	 *  Formated string for use when displaying all options for the menu
	 */
	public String toString()
	{
		return String.format("%3c: %-20s", option, description);
	}
}
