import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * The major things tested here were:
 * - if the decks made new ones after they were size 0
 * - are the results correct 
 * - are the points adding correctly 
 * - does it implement the third draw rules correctly 
 *  
 *   the money addition and subtraction is pretty blunt and straight forward 
 *   so I decided not to test them as they've been tested through use of the program.
 *   The outputs are correct though and it saves to the "playerFile.txt"
 *   and it also prompts the user for input for a name 
 *   and gives the name 1000 chips if the file doesn't exists.
 * 
 * @author Tyler Johnston Kent
 * @version March 12th 2020
 */

class puBancoTest {
	
	@Test
	void testMakeNewDecks() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null,playerDHand = null,bankerDHand = null;
		Deck theDeck = new Deck();
		//so we create a discard hand, which should create a new deck.
		playerDHand = theDeck.dealHand(26); bankerDHand =  theDeck.dealHand(26);
		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		assertEquals(theDeck.size(), 0); // did it empty the deck
		assertEquals(playerDHand.size(), 26); // did it empty the decks
		assertEquals(bankerDHand.size(), 26); 
		puBanco pb = new puBanco(player,banker,theDeck);
		pb.makeNewDecks(); //runs the deck maker
		playerHand = theDeck.dealHand(1); bankerHand =  theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);
		assertEquals(playerHand, player.getHand()); // is the playerHand real
		assertEquals(bankerHand, banker.getHand()); // is the bankerHand real
	}
	@Test
	void testPuntoBancoResults() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();
		
		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		
		
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = pb.puntaBancoResult(1,1);
		
		assertEquals(test1, 3); 
		int test2 = pb.puntaBancoResult(1,2);
		
		assertEquals(test2, 2); 
		int test3 = pb.puntaBancoResult(2,1);
		
		assertEquals(test3, 1); 
	}
	@Test
	void testBetResults() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();
		
		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		
		
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = pb.betResults(1,1,3);
		
		assertEquals(test1, 3); 
		int test2 = pb.betResults(1,1,2);
		
		assertEquals(test2, 2); 
		int test21 = pb.betResults(1,1,1);
		
		assertEquals(test21, 2); 
		
		int test3 = pb.betResults(2,1,1);
		
		assertEquals(test3, 1); 
		int test31 = pb.betResults(2,1,2);
		
		assertEquals(test31, 2); 
		int test32 = pb.betResults(2,1,3);
		
		assertEquals(test32, 2); 
		
		int test4 = pb.betResults(2,1,2);
		
		assertEquals(test4, 2); 
		int test41 = pb.betResults(2,1,1);
		
		assertEquals(test41, 1); 
		int test42 = pb.betResults(2,1,3);
		
		assertEquals(test42, 2); 
		
		int test43 = pb.betResults(1,2,2);
		
		assertEquals(test43, 4); 
	}
	@Test
	void testSelectWinner() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();				
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);	
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = pb.selectWinner(1,1,1);	
		assertEquals(test1, 2);
		
		int test12 = pb.selectWinner(1,1,2);	
		assertEquals(test12, 2);
		
		int test13 = pb.selectWinner(1,1,3);	
		assertEquals(test13, 3);
		
		int test2 = pb.selectWinner(1,2,2);	
		assertEquals(test2, 4);  		
		int test21 = pb.selectWinner(1,2,1);	
		assertEquals(test21, 2);  
		int test213 = pb.selectWinner(1,2,3);	
		assertEquals(test213, 2); 		
		int test3 = pb.selectWinner(3,3,1);	
		assertEquals(test3, 2);		
	}
	
	@Test
	void testFinalBankerAct() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();				
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);		
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = pb.selectWinner(1,1,1);	
		assertEquals(test1, 2);
		
		int test12 = pb.selectWinner(1,1,2);	
		assertEquals(test12, 2);
		
		int test13 = pb.selectWinner(1,1,3);	
		assertEquals(test13, 3);
		
		int test2 = pb.selectWinner(1,2,2);	
		assertEquals(test2, 4);  		
		int test21 = pb.selectWinner(1,2,1);	
		assertEquals(test21, 2);  
		int test213 = pb.selectWinner(1,2,3);	
		assertEquals(test213, 2); 		
		int test3 = pb.selectWinner(3,3,1);	
		assertEquals(test3, 2);		
	}
	@Test
	void testPlayerDraw() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();				
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);	
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		boolean test1 = pb.playerDraw(player);
		assertEquals(test1, false);
		playerHand = theDeck.dealHand(3);
		player.setHand(playerHand);
		boolean test2 = pb.playerDraw(player);		
		assertEquals(test2, true);				
	}
	@Test
	void testPlayerThirdDraw() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();			
		Card d6 = new Card(6,0);
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(0); bankerHand =  theDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6);
		player.setHand(playerHand); banker.setHand(bankerHand);	
		
		int t = pb.playerThirdDraw(6,playerHand);
		
		
		assertEquals(t, 6);				
	}
	@Test
	void testBankerThirdDraw() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();			
		Card d6 = new Card(6,0);
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(0); bankerHand =  theDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6);
		player.setHand(playerHand); banker.setHand(bankerHand);	
		
		int t = pb.bankerThirdDraw(6,bankerHand);		
		assertEquals(t, 6);		
		
		
	}
	
	@Test
	void testHandCardValue() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();				
		puBanco pb = new puBanco(player,banker,theDeck);
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);	
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = pb.handCardValue(14);
		assertEquals(test1, 1);
		int test2 = pb.handCardValue(10);
		assertEquals(test2, 0);
				
	}
	

}
