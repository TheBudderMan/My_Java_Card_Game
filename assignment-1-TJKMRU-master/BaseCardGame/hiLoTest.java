import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
/*
 * I've made only some tests for selectWinner()'s menus 
 * as they're the outcomes for the game. 
 * */
/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */

class hiLoTest {	
	
	@Test
	void testMakeNewDecks() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null,playerDHand = null,bankerDHand = null;
		Deck playerHiLoDeck = new Deck(); Deck bankerHiLoDeck = new Deck();
		//so we create a discard hand, which should create a new deck.
		playerDHand = playerHiLoDeck.dealHand(52); bankerDHand =  bankerHiLoDeck.dealHand(52);
		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		assertEquals(playerHiLoDeck.size(), 0); // did it empty the decks
		assertEquals(bankerHiLoDeck.size(), 0); 
		assertEquals(playerDHand.size(), 52); // did it empty the decks
		assertEquals(bankerDHand.size(), 52); 
		hiLo hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		hl.makeNewDecks(); //runs the deck maker
		playerHand = playerHiLoDeck.dealHand(1); bankerHand =  bankerHiLoDeck.dealHand(1);
		player.setHand(playerHand); banker.setHand(bankerHand);
		assertEquals(playerHand, player.getHand()); // is the playerHand real
		assertEquals(bankerHand, banker.getHand()); // is the bankerHand real
	}
	
	@Test
	void testSuitMatch() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck playerHiLoDeck = new Deck(); Deck bankerHiLoDeck = new Deck();
		Card d6 = new Card(6,0);
		
		playerHand = playerHiLoDeck.dealHand(0); bankerHand =  bankerHiLoDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6);
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		hiLo hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		assertEquals(playerHand.get(0), d6); // is the playerHand real
		assertEquals(bankerHand.get(0), d6); // is the bankerHand real
		
		boolean test = hl.suitMatch(player,banker);
		assertEquals(test, true); 		
	}	
	
	
	@Test
	void testBetResult() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck playerHiLoDeck = new Deck(); Deck bankerHiLoDeck = new Deck();
		Card d6 = new Card(6,0);
		
		playerHand = playerHiLoDeck.dealHand(0); bankerHand =  bankerHiLoDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6);
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		hiLo hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		int hTest1 = hl.betResults(1,1,true);
		assertEquals(hTest1, 1);
		int hTest2 = hl.betResults(2,2,false);
		assertEquals(hTest2, 1);
		int hTest3 = hl.betResults(3,3,true);
		assertEquals(hTest3, 3);
		int hTest4 = hl.betResults(1,2,false);
		assertEquals(hTest4, 2);
		int hTest5 = hl.betResults(2,1,false);
		assertEquals(hTest5, 2);
		
	}
	
	@Test
	void testHiLoResult() {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null, playerHand2 = null,bankerHand2 = null;
		Deck playerHiLoDeck = new Deck(); Deck bankerHiLoDeck = new Deck();
		Card d6 = new Card(6,0); Card d5 = new Card(5,0);
		
		playerHand = playerHiLoDeck.dealHand(0); bankerHand =  bankerHiLoDeck.dealHand(0);
		bankerHand2 =  bankerHiLoDeck.dealHand(0); playerHand2 = playerHiLoDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6); 
		bankerHand2.addCard(d5); 
		playerHand2.addCard(d5);
		Player player = new Player("Tyler",initialChips);
		Player player2 = new Player("Tyler",initialChips);
		player2.setHand(playerHand2);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		Banker banker2 = new Banker();
		banker2.setHand(bankerHand2);
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		hiLo hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		int test1 = hl.hiLoResult(player, banker);
		assertEquals(test1, 3);
		int test2 = hl.hiLoResult(player, banker2);
		assertEquals(test2, 1);
		int test3 = hl.hiLoResult(player2, banker);
		assertEquals(test3, 2);
		
	}
	
	@Test
	void testSelectWinner() throws IOException {
		int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck playerHiLoDeck = new Deck(); Deck bankerHiLoDeck = new Deck();
		Card d6 = new Card(6,0);
		
		playerHand = playerHiLoDeck.dealHand(0); bankerHand = bankerHiLoDeck.dealHand(0);
		playerHand.addCard(d6); bankerHand.addCard(d6);
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		banker.setHand(bankerHand);
		hiLo hl = new hiLo(player,banker,playerHiLoDeck,bankerHiLoDeck);
		
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		int test1 = hl.selectWinner(1,1,true);	
		assertEquals(test1, 1);
		
		int test2 = hl.selectWinner(2,1,false);	
		assertEquals(test2, 2);
		
		int test3 = hl.selectWinner(3,3,true);	
		assertEquals(test3, 3);
		
	}

}
