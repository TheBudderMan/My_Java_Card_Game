import java.io.IOException;

/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */

public class puBanco {
	
	private Casino casino = new Casino(); Runner main = new Runner();	
	private Menu betMenu; private Menu betChoice; private Menu initialMenu;
	private Deck theDeck; private Menu betAmount;
	private ConsoleComunicationController comm;
	private Player player;
	private Banker banker;
	private Table table = new Table(player,banker);
	private int casinoChips; private int theBet;
	private final int ONECARD = 1; private final int BETSTOPONE = 8; private final int BETSTOPTWO = 9; 
	private fileManager file = new fileManager();

	/*
	 * I think later here we would be accepting the currency as a parameter from the casino
	 * @param n sets the rank of the card ( values 2 to 14, based upon rank constants )
	 * @param p sets the player object ( can be dealt a hand and be given a name. will be able to handle currency later )
	 * */
	//
	
	/**
	 * @param p The Player
	 * @param b The Banker
	 * @param c The deck
	 */
	public puBanco(Player p, Banker b, Deck c) {		
		player=p; banker=b;
		casinoChips = player.getChips();
		comm = new ConsoleComunicationController();
		theDeck = c;
	}	
	//		
	
	/*Player and card information tracked here.*/
	public void playerInformation() {		
		if (player.getHand() !=null) {
			comm.println("\n[Player Name:" + player.getName() + "]\n[Chip Counter:" + player.getChips() + "]\n[" + player.getHand() +"]");
			
		}
		else {
			comm.println("\n[Player Name:" + player.getName() + "]\n[Chip Counter:" + player.getChips() + "]\n");
		}
	}
	/*Makes a new deck if the size becomes 0*/
	
	public void makeNewDecks() {		
		if (theDeck.size() == 0) {
			theDeck = new Deck();
		}		
	}
	
	//
	
	/**
	 * Says if the players hand is greater than 2 to determine if they drew a third
	 * @param player
	 * @return result
	 */
	public boolean playerDraw(Player player) {
		boolean result = false;
		if (player.getHand().size() > 2) {
			result = true;
		}
		return result;
	}
	//
	
	/**
	 * @param a
	 * @return
	 */
	private int handSum(int a) {		
		return ((a)%10); //the sum modulo 10
	}
	
	
	/**
	 * so the cards should just be dealt from 
	 * playerDeck to player
	 * bankerDeck to banker
	 * 
	 * each player gets 1 card for their hands. 
	 * @param a
	 * @return
	 */
	public int handCardValue(int a) {
		int result = a;
		if (a == 14) {
			//don't give the hand any value
			result = 1;
		}
		if (a == 13 || 
			a == 12 || 
			a == 11 ||
			a == 10 ) {
			//don't give the hand any value		
			result = 0;
		}
		//should return the value of the int passed through unless the values specified above
		return result;
	}
	//
	
	/**
	 * @param a
	 * @param playerHand
	 * @return
	 */
	public int playerThirdDraw(int a, CardHand playerHand) {
		int thirdpCardRankValue = 0; 
		//if the sum of the players hand is less than 5, draw another card.
	 	if (a < 5) {
		playerHand.addCard(theDeck.dealCard()); 
		player.setHand(playerHand);	
		// dealing third card from the deck to each players hand
		thirdpCardRankValue = playerHand.get(2).getRank();		
		a = a + handCardValue(thirdpCardRankValue);//adds the appropriate value to the handSum counter now.
		a = handSum(a);
	 	} 
		return a;
	}
	
	/**
	 * @param a
	 * @param bankerHand
	 * @return
	 */
	public int bankerThirdDraw(int a, CardHand bankerHand) {
		int thirdbCardRankValue = 0; 
		//if the sum of the players hand is less than 5, draw another card.
	 	if (a < 5) {
	 	bankerHand.addCard(theDeck.dealCard()); 
	 	banker.setHand(bankerHand);	
		// dealing third card from the deck to each players hand
		thirdbCardRankValue = bankerHand.get(2).getRank();		
		a = a + handCardValue(thirdbCardRankValue);//adds the appropriate value to the handSum counter now.
		a = handSum(a);
	 	} 
		return a;
	}
	
	/**
	 * @param bSum
	 * @param bankerHand
	 * @param playerHand
	 * @return bankerHandSumFinal
	 */
	private int finalBankerAct(int bSum, CardHand bankerHand, CardHand playerHand) {
		int thirdpCardRankValue = 0; int bankerHandSumFinal = 0;
		if (playerDraw(player) == false && bSum <= 5) {
	 		bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
	 	}
	 	if (playerDraw(player) == true) {
	 		thirdpCardRankValue = playerHand.get(2).getRank();
	 		//if player third cardvalue is 8 and bankers sum is 2, draw third banker card.
	 		if (thirdpCardRankValue == 8 && bSum <= 2) {
	 			bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
		 	} //if player third cardvalue is 6 or 7 and bankers sum is less than 6, draw third banker card.
	 		if (thirdpCardRankValue == 7  || thirdpCardRankValue == 6 && bSum <= 6) {
	 			bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
		 	}//if player third cardvalue is 4 or 5 and bankers sum is less than 5, draw third banker card.
	 		if (thirdpCardRankValue == 5  || thirdpCardRankValue == 4 && bSum <= 5) {
	 			bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
		 	}//if player third cardvalue is 2or 3 and bankers sum is less than 5, draw third banker card.
	 		if (thirdpCardRankValue == 3 || thirdpCardRankValue == 2 && bSum <= 4) {
	 			bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
		 	}//if player third cardvalue is 0 or 1 and bankers sum is less than 3, draw third banker card.
	 		if (thirdpCardRankValue == 1 || thirdpCardRankValue == 0 && bSum <= 3) {
	 			bankerHandSumFinal = bankerThirdDraw(bSum,bankerHand);	 		
		 	}
	 	}
	 	return bankerHandSumFinal;		
	}
	
	/**
	 * Says at the end if the player or banker wins.
	 * @param a the player
	 * @param b the banker
	 */
	private void peanutOrButter(int a, int b) {
		String p = "Player"; String q = "Banker"; String ans = " ";
		if(a > b) {
			ans = p;
			System.out.println("The " + ans + " wins!");
		}
		else if(a < b) {
			ans = q;
			System.out.println("The " + ans + " wins!");
		}		
	}
	
	private void playGame() throws IOException {  //started down at 228
		/*We need to set up the point counter
		 * you add up the values of the hands.
		 * */
		//you only need to deal 1 card.				
		CardHand playerHand=null, bankerHand=null;
		int firstpCardRankValue = 0; int secondpCardRankValue = 0; 
		int playerHandSum = 0; int playerHandSumFinal = 0; int pSum = 0;    
		int firstbCardRankValue = 0; int secondbCardRankValue = 0; 
		int bankerHandSum = 0; int bankerHandSumFinal = 0; int bSum = 0; 
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		//should ask if the player wants to place a bet
		//betMenu(pSum,bSum); //this initiates the rest of the game, 
		int betChoice = chooseBet(); //so setting a value for bet choice here.
		
		//all the steps happen automatically, after you choose p b or t.
		/*STEP 1*/
		// first a card from each deck is dealt to each players hand
		playerHand = theDeck.dealHand(ONECARD); //must be broken down further to determine it's value
		
		firstpCardRankValue = playerHand.get(0).getRank();		
		
		playerHandSum = handCardValue(firstpCardRankValue);//adds the appropriate value to the handSum counter now.
		//sums the player hand modulo 10.
	 	pSum = handSum(playerHandSum);	
	 		 	
	 	/////////////////////////////////////////////////////////////////////
		bankerHand = theDeck.dealHand(ONECARD);
		
		firstbCardRankValue = bankerHand.get(0).getRank();
		bankerHandSum = handCardValue(firstbCardRankValue);
		//sums the banker hand modulo 10.
	 	bSum = handSum(bankerHandSum);
		////
		////////
		//the hands are given to the player and banker objects for the first time creating new objects.
		player.setHand(playerHand); banker.setHand(bankerHand);		
		//the hands are then shown to the user for the first time.
	 	comm.println("Table #1");
		table.showPuntoTable(player,banker,pSum,bSum);	 	
	 	/*STEP 2*/	 	
	 	
	    // dealing second players card
	 	playerHand.addCard(theDeck.dealCard()); 
	 	secondpCardRankValue = playerHand.get(1).getRank();		
	 	playerHandSum = playerHandSum + handCardValue(secondpCardRankValue);//adds the appropriate value to the handSum counter now.
	 	pSum = handSum(playerHandSum);
	 	
	 	//Now doing the banker hand
	 	bankerHand.addCard(theDeck.dealCard());
		secondbCardRankValue = bankerHand.get(1).getRank();
		bankerHandSum = bankerHandSum + handCardValue(secondbCardRankValue);//concatanates the value of the original sum with the second cards value.
		bSum = handSum(bankerHandSum);
	 	//the hands are given to the player and banker objects again.
	 	player.setHand(playerHand); banker.setHand(bankerHand);
	    
	 	//the hands are then shown to the user, again.
	 	comm.println("Table #2");
	 	table.showPuntoTable(player,banker,pSum,bSum);
	 	// technically step 5 in the list,
	 	if (pSum == BETSTOPONE ||pSum == BETSTOPTWO ||bSum == BETSTOPONE ||bSum == BETSTOPTWO) {
	 		//if the player or bankers hand sums are either 8 or 9, finish the game and 
	 		peanutOrButter(pSum,bSum);
	 		selectWinner(pSum,bSum,betChoice);	 		
	 	}
	 	else if(pSum <= 7){
	 		/*STEP 6-7. Step 7 sublist if conditionals done in finalBankerAct.*/
		 	//returns the value of the 
		 	//if the sum of the players hand is less than 5, draw another card.
		 	playerHandSumFinal = playerThirdDraw(pSum, playerHand);//returns the value of the hand.	 	
		 	
		 	//this is for determining if the banker draws a card now, returns the bankers hand value.
		 	bankerHandSumFinal = finalBankerAct(bSum,bankerHand,playerHand);			
			/*I tried to show functions by step but
			 * the values returned from user input methods were more functional 
			 * as switch selectors to make the correct choices in an easier to read state.
			 */
			//This should be the final 'table' after the third draw.
		 	comm.println("Table #3");
			table.showPuntoTable(player,banker,playerHandSumFinal,bankerHandSumFinal);	 	
			//
			peanutOrButter(playerHandSumFinal,bankerHandSumFinal);
			selectWinner(playerHandSumFinal,bankerHandSumFinal,betChoice);
	 	} //end of else	 	
	 	
	 	makeNewDecks(); // checks if decks empty
	}
	
	//So selectWinner is initiated first, which runs gameResults
		
	/**
	 * @param a playerHandSum 
	 * @param b bankerHandSum
	 * @param c 
	 * @throws IOException
	 */
	public int selectWinner(int a, int b, int c) throws IOException {	 
		int result = betResults(a,b,c);
		switch(result){
		case 1: 
			comm.println("Yay, you won! 2:1 Payout!");			
			//this is when we would add the amount bet to to players account
			casinoChips = casinoChips + theBet;
			player.setChips(casinoChips);
			file.bigSave(player);
		break;
		case 2: 
			//this is when we would remove the amount bet from the players account
			//removeBetFromPool();
			comm.println("Oh no, you lost your bet!");
			casinoChips = casinoChips - theBet;
			player.setChips(casinoChips);
			file.bigSave(player);
		break;
		case 3: 
			comm.println("That's was an egalite! 8:1 payout!");
			theBet = theBet*7;
			casinoChips = casinoChips + theBet;
			player.setChips(casinoChips);	
			file.bigSave(player);
		break;
		case 4: 
			comm.println("That's was a banker win! 1.95:1 payout!");
			if (theBet == 100) { 
				theBet = theBet - 5;
			}
			if (theBet == 50) {
				theBet = theBet - 3;
			}
			if (theBet == 25) {
				theBet = theBet - 2;
			}
			casinoChips = casinoChips + theBet;
			player.setChips(casinoChips);	
			file.bigSave(player);
		break;
		}
		return result;
	}
	
	//gameResults here initiates chooseBet which is where the user will decide how much they want to later
	/*
	 * returns an integer for case selection to determine 
	 * if the player either wins loses or ties against
	 * the banker.
	 * */
	public int betResults(int a, int b, int c) {		
		int win = 1; int lose = 2; int tie = 3; int bankerWin = 4;
		//result initialized at 0;
		int result = 0;
		int betIChoose = c; // initiates chooseBet here	
		int selector = puntaBancoResult(a,b);
		//If I choose b, and it is b, this happens. if i bet on player losing and they lose, you win.
		if (betIChoose == selector && betIChoose ==lose){
			result = bankerWin;
		}
		//I believe to calculate the winnings correctly it's,	
		//If I choose c, and it is c, this happens. so if i bet it's an egalite, i get 8x payout. 
		if (betIChoose == selector && betIChoose ==tie){
			result = tie;
		}		
		
		//If I choose a, and it is a, this happens. if i bet on the player winning and they win, i win.
		if (betIChoose == selector && betIChoose ==win){
			result = win;
		}	
		//If I what i choose is not equal to the game outcome, i lose.
		if (betIChoose != selector){
			result = lose;
		}	
		return result;
	}	
	/* * returns a value to match against chooseBet * */
	public int puntaBancoResult(int a, int b) {		
		int win = 1; int lose = 2; int tie = 3; 
		//result initialized at 0;
		int result = 0;
		
		//a is play, b is banker.
		if (a == b){
			//if you tie output 3
			result = tie;
		}
		else if (a < b){
			//if you lose output 2
			result = lose;
		}
		else if (a > b){
			//if you win output 1
			result = win;
		}
		return result;
	}
	/*
	 * the menu for placing a bet made with the convenient menu class.
	 * */
	private void setBetChoice(){
		betChoice = new Menu(comm);		
		comm.println("Choose P for Punto win, B for Banco win and 3 for an Egalite!");		
		betChoice.addMenuOption( new MenuOption('P',"Player win!") );
		betChoice.addMenuOption( new MenuOption('B',"Banco win!") );
		betChoice.addMenuOption( new MenuOption('T',"Egalite!") );
	}
	/*
	 * returns an integer by case selection to determine 
	 * what the player is betting on.
	 * */
	private int chooseBet() {		
		betChoice = new Menu(comm);		
		char selection = ' '; 
		int high = 1; int low = 2; int same = 3; 
		//result initialized at 0;
		int result = 0;
		System.out.println("Let's place a bet!");
		setBetChoice();
		selection = betChoice.getUserChoice();
		switch(selection){
		case 'P': result = high; break;
		case 'B': result = low; break;
		case 'T': result = same; break;
		}				
		return result;
	}
	/*
	 * Bet amount is in a menu type selection for payout calculations simplicity
	 * */
	private void setBetAmount(){
		betAmount = new Menu(comm);		
		comm.println("Choose 1 for 25 chips. 2 for 50 chips, and 3 for 100!");
		betAmount.addMenuOption( new MenuOption('1',"Bet 25 Chips.") );
		betAmount.addMenuOption( new MenuOption('2',"Bet 50 Chips.") );
		betAmount.addMenuOption( new MenuOption('3',"Bet 100 Chips.") );
	}
	/*
	 * returns an integer for case selection to determine 
	 * what the player is betting on.
	 * */
	private int chooseBetAmount() {		
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
	
	/*
	 * returns an integer for case selection to determine 
	 * what the player is betting on.
	 * */
		
	private void setBetMenu(){
		betMenu = new Menu(comm);
		System.out.println("Let's place a bet!");
		betMenu.addMenuOption( new MenuOption('1',"Place bet.") );
		betMenu.addMenuOption( new MenuOption('2',"Punto Banco start menu.") );
	}
	//betMenu allows you to choose if you want to or not.
	private void betMenu() throws IOException {
		playerInformation();
		char selection = ' ';	
		setBetMenu();
		selection = betMenu.getUserChoice();
		switch(selection){
		// should select the amount we're betting, then play the game.
		case '1': theBet = chooseBetAmount(); break;
		case '2': startMenu(); break;
		}			
	}
	//
	/** Outputs the menu for users*/
	private void initialMenu() { 		
		System.out.println("Welcome to our Punto Banco game "+ player.getName()+"!");
		initialMenu = new Menu(comm);
		initialMenu.addMenuOption( new MenuOption('1',"Start the game!") );
		initialMenu.addMenuOption( new MenuOption('2',"Quit to the main menu!") );
	}
	/**                                                                                  
	 * runs the selected user from initialMenu                                              
	 * @throws IOException 
	 */                                                                                   
	private void startMenu() throws IOException {	 
		//initial menu triggered so user knows what to do.
		initialMenu();
		//methods copied from runner class for character selection.
		char selection = initialMenu.getUserChoice();
		switch(selection){
		case '1': betMenu(); playGame();  break; //starts the game from line 69
		//mainMenu triggered from main class to go back to the main menu.
		case '2': main.mainMenu(main,casino); break;
		}			
	}	
	/**
	 * Starts the chain of functions that runs the game. 
	 * @throws IOException 
	 */
	public void run() throws IOException {
		startMenu();
	}
}
