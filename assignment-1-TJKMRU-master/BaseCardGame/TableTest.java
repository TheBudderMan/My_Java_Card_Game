import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TableTest {

	
	@Test
	void test() {
		int pSum = 6; int bSum = 2; int initialChips = 1000;
		CardHand playerHand = null,bankerHand = null;
		Deck theDeck = new Deck();
		playerHand = theDeck.dealHand(1); bankerHand = theDeck.dealHand(1);
		
		Player player = new Player("Tyler",initialChips);
		Banker banker = new Banker();
		//You gotta set the hand there sir.
		player.setHand(playerHand); banker.setHand(bankerHand);
		
		Table table = new Table(player,banker);
		table.showPuntoTable(player, banker, pSum, bSum);
		
		assertEquals(playerHand, player.getHand()); // is the playerHand real	
		assertEquals(bankerHand, banker.getHand());
		table.showPreHiLoTable(player,banker);
		
		table.showHiLoTable(player,banker);
	}

}
