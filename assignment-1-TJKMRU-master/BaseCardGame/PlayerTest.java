import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	public void testPlayerConstructor() {
		int initialChips = 1000;
		Deck playerHiLoDeck = new Deck();
		CardHand playerHand=null;
		Player p = new Player("Testy",initialChips);
		playerHand = playerHiLoDeck.dealHand(1); // deals the hand
		p.setHand(playerHand);
		assertEquals("Testy", p.getName());  // returns the 
		assertEquals(initialChips, p.getChips()); //returns the amount of chips
		/*
		 * So it was hard to set the hand within the constructor 
		 * so I made separate setters for the hand.
		 * */
		assertEquals(playerHand, p.getHand()); //gets the hand
	}

}
