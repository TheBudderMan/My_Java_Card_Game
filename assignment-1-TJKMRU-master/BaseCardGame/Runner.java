import java.io.IOException;

/**
 * Main used to run the program for testing the basic required features
 * @author jkidney
 * @version March 11, 2013
 */

public class Runner 
{
	private Menu deckMenu; private Menu mainMenu; 
	private boolean endProgram;
	private Deck gameDeck;
	private ConsoleComunicationController comm;
	private boolean woah;
	/**
	 * Default constructor 
	 */
	public Runner()
	{
		comm = new ConsoleComunicationController();
		endProgram = false; woah = true;
		gameDeck = new Deck();		
		deckMenu = new Menu(comm);
		mainMenu = new Menu(comm);
		setUpMenu();
		setUpMainMenu();
	}
	/**
	 * Sets up the menu that will be used just for part one of the program
	 */
	private void setUpMainMenu() {
		mainMenu.addMenuOption( new MenuOption('1',"Use the Runner app.") );
		mainMenu.addMenuOption( new MenuOption('2',"Use the Casino app.") );
		mainMenu.addMenuOption( new MenuOption('3',"Quit") );
	}
	/**
	 * Sets up the menu that will be used just for part one of the program
	 */
	private void setUpMenu()
	{
		deckMenu.addMenuOption( new MenuOption('N',"Create New deck") );
		deckMenu.addMenuOption( new MenuOption('S',"Shuffle the deck") );
		deckMenu.addMenuOption( new MenuOption('T',"Show the top card") );
		deckMenu.addMenuOption( new MenuOption('C',"Cut the deck") );
		deckMenu.addMenuOption( new MenuOption('D',"Deal the hands") );
		deckMenu.addMenuOption( new MenuOption('P',"Print deck") );
		deckMenu.addMenuOption( new MenuOption('Q',"Quit") );
	}

	/**
	 * runs the selected user choice
	 * @param userSelection the validated selection given by the user
	 */
	private void runSelection(char userSelection)
	{
		int numCards = 0;
		CardHand hand1=null, hand2=null;
		
		switch(userSelection)
		{
		case 'N': gameDeck = new Deck(); break;
		case 'S': 
			System.out.println("Before Shuffle");
			System.out.println(gameDeck);

			gameDeck.shuffle();

			System.out.println("After Shuffle");
			System.out.println(gameDeck);


			break;
		case 'T': System.out.println("Top Card = " + gameDeck.topCard() ); break;
		case 'C': 
			System.out.println("Before Cut");
			System.out.println(gameDeck);

			System.out.println("Cut Card = " + gameDeck.cut() );
			
			System.out.println("After Cut");
			System.out.println(gameDeck);

			break;
		case 'D':
			 numCards = comm.getInput_Int("Enter the number of cards you want in the hands: ");
			 
		     hand1 = gameDeck.dealHand(numCards);
		     hand2 = gameDeck.dealHand(numCards);
			
		     System.out.println("Hand1="+hand1);
		     System.out.println("Hand2="+hand2);
		     
			break;
		case 'P': System.out.println(gameDeck); break;
		case 'Q': endProgram = true; break;
		}
	}

	/**
	 * Main Startup method for the part one of the program. It will
	 * run the entire interaction with the user.
	 */
	public void run() {		
		char selection = ' ';

		while(!endProgram)
		{
			selection = deckMenu.getUserChoice();
			runSelection(selection);
		}
	}
	//the main menu
	public void mainMenu(Runner main, Casino casino) throws IOException {
		char selection = ' ';		
		comm.println("Welcome to the main menu.\nPlease choose a program.");		
		while(woah) {
			selection = mainMenu.getUserChoice();
			switch(selection){
			case '1': main.run();		
			break;
			case '2': casino.run();		
			break;
			case '3': comm.print("Thanks for playing with us! Bye!"); woah = false; System.exit(0); break;
			}				
		}
		
	}
	/**
	 * Main
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Runner main = new Runner();
		Casino casino = new Casino();
		//
		main.mainMenu(main,casino);		
	}

}
