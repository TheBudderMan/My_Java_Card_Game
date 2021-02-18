import java.io.IOException;

/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */

public class hiLo {
	
	private Casino casino = new Casino(); Runner main = new Runner();
	private Menu betMenu; private Menu betChoice; private Menu initialMenu;
	private Menu betAmount;
	private Deck playerDeck;
	private Deck bankerDeck;
	private ConsoleComunicationController comm;
	private Player player;
	private Banker banker;
	private Table table = new Table(player,banker);
	private int theBet = 0; private int chipCount = 0;
	private fileManager file = new fileManager();
		
	
	/**
	 * @param p The Player
	 * @param b The Banker
	 * @param c The Player Deck
	 * @param d The Banker Deck
	 */
	public hiLo(Player p, Banker b, Deck c, Deck d) {		
		player=p; banker=b;
		chipCount = player.getChips();
		comm = new ConsoleComunicationController();
		playerDeck = c;
		bankerDeck = d;		
		
	}	
	
	
	/*Player and card information tracked here.*/
	public void playerInformation() {		
		if (player.getHand() !=null) {
			comm.println("\n[Player Name:" + player.getName() + "]\n[Chip Counter:" + player.getChips() + "]\n[" + player.getHand() +"]");
			
		}
		else {
			comm.println("\n[Player Name:" + player.getName() + "]\n[Chip Counter:" + player.getChips() + "]\n");
		}
	}
	public boolean suitMatch(Player p, Banker b) {
		boolean result = false;
		//if the players hands suit matches the bankers hands suit return true
		if (p.getHand().get(0).getSuit() == b.getHand().get(0).getSuit()){
			result = true;
		}
		return result;
	}
	
	/*Makes new decks when they're empty*/
	public void makeNewDecks() {
		if (playerDeck.size() == 0) {
			playerDeck = new Deck();
		}
		if (bankerDeck.size() == 0) {
			bankerDeck = new Deck();
		}
	}
	//
	/*
	 * so the cards should just be dealt from 
	 * playerDeck to player
	 * bankerDeck to banker	 * 
	 * each player gets 1 card for their hands. 
	 * */
	
	/**
	 * @throws IOException
	 */
	private void playGame() throws IOException {		
		//you only need to deal 1 card.
		int numCards = 1;
		CardHand playerHand=null, bankerHand=null;
		// first a card from each deck is dealt to each players hand
		playerHand = playerDeck.dealHand(numCards); bankerHand = bankerDeck.dealHand(numCards);
		//the hands are given to the player and banker objects 
		player.setHand(playerHand); 
		banker.setHand(bankerHand);
		//the table before the showdown and bet selection.
		table.showPreHiLoTable(player,banker);
		//should ask if the player wants to place a bet
		betMenu();
		//this is the showdown table to finish the game.		
		table.showHiLoTable(player,banker);	
		makeNewDecks();		
	}
	//So selectWinner is initiated first, which runs gameResults
	
	
	/**
	 * using the int returned from betResults,
	 * selectWinner outputs the necessary information 
	 * and initiates the correct functions post-play.
	 * @throws IOException
	 */
	public int selectWinner(int a, int b,boolean c) throws IOException {
		int result = betResults(a,b,c);
		switch(result){
		case 1: 
			System.out.println("\n\nYay, you won! (2:1)\n");			
			//this is when we would add the amount bet to the players account
			//you bet 20, you get 40 back so you really get + 20 back.
			chipCount = chipCount + theBet;
			player.setChips(chipCount);
			System.out.println("You won:" +theBet+" Your total is:"+player.getChips());
			file.bigSave(player);
		break;
		case 2: 
			//this is when we would remove the amount bet from the players account
			System.out.println("\n\nOh no, you lost!\n");
			chipCount = chipCount - theBet;
			player.setChips(chipCount);
			System.out.println(player.getChips());
			file.bigSave(player);
			
		break;
		case 3: 
			System.out.println("\n\nThat's a really lucky match! Your bet has doubled (3:1).\n");
			//the bet should add to itself so you bet 20, you get 60 but it's really 40.
			theBet = theBet + theBet; //so if you choose 20, this should be 40.
			//technically you minus 20, and then re-add 60 so you should only add 40.
			chipCount = chipCount + theBet;
			player.setChips(chipCount);
			System.out.println(player.getChips());
			file.bigSave(player);
			
		break;
		}		
		file.bigSave(player);		
		return result;
	}
	//gameResults here initiates chooseBet which is where the user decides how much they want to
	
	/**
	 * returns an integer for case selection to determine 
	 * if the player either wins loses or ties against
	 * the banker.
	 * @return
	 * @throws IOException
	 */
	public int betResults(int a, int b, boolean c) throws IOException {		
		int win = 1; int lose = 2; int tie = 3; 
		//result initialized at 0;
		int result = 0;				
		//this will just say no matter what if these match, you lose. 
		if (a == tie && a == b && c == true){
			comm.println("That's a really unlucky match! You lost your bet.");
			chipCount = chipCount - theBet;
			player.setChips(chipCount);
			file.bigSave(player);
			result = lose;
		}		
		//if the bet the user chooses matches the game result and it's also a tie, 
		if (a == b && a == tie){
			result = tie; // make it a tie
		}
		
		//if the users says the player would lose and they win you should lose.
		if (a == lose && b == win ||
			a == win && b == lose){ //if you say players hi and they're low
			result = lose; // they should lose
		}
		//if the users says they would be low and they are
		if (a == lose && b == lose || 
			a == win && b  == win){ // if the user says hi and wins
			result = win; // they should win
		}
		return result;
	}
		
	/**
	 * returns a value to match ranks against chooseBet
	 * @return result
	 */
	public int hiLoResult(Player p, Banker b) {		
		int win = 1; int lose = 2; int tie = 3; 
		//result initialized at 0;
		int result = 0;
		
		if (p.getHand().get(0).getRank() == b.getHand().get(0).getRank()){
			//if you tie output 3
			result = tie;
		}
		else if (p.getHand().get(0).getRank() < b.getHand().get(0).getRank()){
			//if you lose output 2
			result = lose;
		}
		else if (p.getHand().get(0).getRank() > b.getHand().get(0).getRank()){
			//if you win output 1
			result = win;
		}
		return result;
	}
	////
	/////////////
	/////////////
	////
	private void setBetAmount(){
		betAmount = new Menu(comm);		
		comm.println("Choose 1 for 25 chips. 2 for 50 chips, and 3 for 100!");
		betAmount.addMenuOption( new MenuOption('1',"Bet 25 Chips.") );
		betAmount.addMenuOption( new MenuOption('2',"Bet 50 Chips.") );
		betAmount.addMenuOption( new MenuOption('3',"Bet 100 Chips.") );
	}
		
	/**
	 * returns an integer for case selection to determine 
	 * what the player is betting on.
	 * @return result is the amount for the bet
	 */
	public int chooseBetAmount() {		
		betAmount = new Menu(comm);
		playerInformation();
		char selection = ' '; 
		int hiBet = 100; int lowBet = 25; int midBet = 50; 
		//result initialized at 0;
		int result = 0;
		System.out.println("How much are we betting?");
		setBetAmount();
		selection = betAmount.getUserChoice();
		//
		switch(selection){
		case '1': result = lowBet; break;
		case '2': result = midBet; break;
		case '3': result = hiBet; break;
		}					
		return result;
	}
	//
	private void setBetChoice(){
		betChoice = new Menu(comm);		
		comm.println("Choose 1 for high, 2 for low and 3 for a tie!");
		betChoice.addMenuOption( new MenuOption('1',"The Players card will win (Be the high card).") );
		betChoice.addMenuOption( new MenuOption('2',"The Players card will lose (Be the low card).") );
		betChoice.addMenuOption( new MenuOption('3',"The cards will match.") );
	}
	/*
	 * returns an integer for case selection to determine 
	 * what the player is betting on.
	 * */
	
	/**
	 * result is the users choice for the games outcome.
	 * @return result of what the user chooses
	 */
	private int chooseBet() {		
		betChoice = new Menu(comm);
		playerInformation();
		char selection = ' '; 
		int high = 1; int low = 2; int same = 3; 
		//result initialized at 0;
		int result = 0;
		System.out.println("Who do you think is going to win?!");
		setBetChoice();
		selection = betChoice.getUserChoice();
		//
		switch(selection){
		case '1': result = high; break;
		case '2': result = low; break;
		case '3': result = same; break;
		}					
		return result;
	}		
	private void setBetMenu(){
		betMenu = new Menu(comm);
		System.out.println("Are we placing a bet?");
		betMenu.addMenuOption( new MenuOption('1',"Place bet.") );
		betMenu.addMenuOption( new MenuOption('2',"Hi-Low start menu.") );
	}
	//betMenu allows you to choose if you want to or not.
	
	/**
	 * @throws IOException
	 */
	private void betMenu() throws IOException {
		playerInformation();
		char selection = ' ';	
		setBetMenu();
		selection = betMenu.getUserChoice();
		switch(selection){
		// should select the amount we're betting, then play the game.
		case '1': 
			theBet = chooseBetAmount(); 
			int a = chooseBet();
			int b = hiLoResult(player,banker);
			boolean c =suitMatch(player,banker);
			selectWinner(a,b,c); 
			playerInformation();
			break;
		case '2': startMenu(); break;
		}			
	}
	/**
	 * Sets up the menu that will be used just for part one of the program
	 */
	private void initialMenu() {	
		//playerInformation();
		System.out.println("Welcome to our Hi-Lo game "+ player.getName() +"!");
		initialMenu = new Menu(comm);
		initialMenu.addMenuOption( new MenuOption('1',"Start the game!") );
		initialMenu.addMenuOption( new MenuOption('2',"Quit to the main menu!") );
	}
	/**
	 * runs the selected user from initialMenu
	 * @param userSelection the validated selection given by the user
	 * @throws IOException 
	 */
	private void startMenu() throws IOException {		
		//
		//casino.readFile();
		initialMenu();
		char selection = initialMenu.getUserChoice();
		switch(selection){
		case '1': playGame(); break;		
		case '2': main.mainMenu(main,casino); break;
		}			
	}	
	/**
	 * runs the game
	 * the user should be able to see the table currently, 
	 * if the table is empty, the user is prompted to draw a card.
	 * for incremental testing purposes the games will just play out 
	 * and currency will be added later.
	 * @throws IOException 
	 */
	public void run() throws IOException {
		startMenu();
	}	
}