import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * The casino class which handles the money and selection of games the money interacts with.
 * @author Tyler Johnston-Kent
 * @version February 25, 2020
 */


public class Casino {
	
	private Menu deckMenu;
	private ConsoleComunicationController comm;
	private hiLo hl; private puBanco pb;
	public Player player;
	private Banker banker;
	private boolean woah = true;
	private Deck playerHiLoDeck;
	private Deck bankerHiLoDeck;
	private Deck puntoBankDeck;
	private fileManager theFile = new fileManager();
	public Casino() {
		/*Initiates and shuffles decks to send to games.*/
		playerHiLoDeck = new Deck();
		bankerHiLoDeck = new Deck();
		puntoBankDeck = new Deck();
		shuffleDecks();
		comm = new ConsoleComunicationController();
		deckMenu = new Menu(comm);
	}
	
	private void casinoMenu() {		
		System.out.println("Welcome to my Casino application.\nIt is more easily played with the console fullscreen.");
		
		deckMenu.addMenuOption( new MenuOption('1',"Play Hi-Lo") );
		deckMenu.addMenuOption( new MenuOption('2',"Play Punta Banco") );
		deckMenu.addMenuOption( new MenuOption('3',"Quit") );
	}	
	
	private void shuffleDecks() {		
		for (int i = 0; i < 2; i++) {
			playerHiLoDeck.shuffle();
			bankerHiLoDeck.shuffle();
			puntoBankDeck.shuffle();
		}		
	}
	
	
	public void readFile()throws IOException {
   		
		int initialChips = 1000;
		String fName = "playerFile.txt";
		File file = new File(fName);		
		if(!file.exists()) {			
		    System.out.println("Sorry that doesn't exist.. Let's make a new one!");
		    String name = comm.getInput_String("Please enter a name: ");
		    Player newPlayer = new Player(name,initialChips);
		    theFile.bigSave(newPlayer); 
		    } 
		else {
		   	System.out.println("Let's load the player!");
		}		
		try{	   	
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String line;	    
	    while((line = in.readLine()) != null){
	    	String[] columns = line.split(", ");
	    	String pName= columns[0];                    
            String pAgeUse = columns[1];
            int casinoChips = Integer.valueOf(pAgeUse);
	    	Player passLoad = new Player(pName,casinoChips); 
            player = passLoad;
	        System.out.println("Loading player...."+line+"<--Chips");
	    }
	    in.close();
	   	}
	   	catch(Exception e){
	   	    System.out.println("Unable to open: " + fName);
	   	}
	}
	
	/**
	 * runs the selected user choice
	 * @param userSelection the validated selection given by the user
	 * @throws IOException 
	 */
	
	public void run() throws IOException {
		readFile();
		casinoMenu();
		char selection = deckMenu.getUserChoice();
		
		while(woah) {
			switch(selection) {
			case '1': playHiLo(); break;		
			case '2': playPuntaBanco(); break;
			case '3': theFile.bigSave(player); woah = false; break;
			}			
		}		
	}
	//
	public void playHiLo() throws IOException {
		hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		hl.run();		
	}
	//
	public void playPuntaBanco() throws IOException {
		pb = new puBanco(player,banker,puntoBankDeck);
		pb.run();			
	}
	//
}
