import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankerTest {

	@Test
	public void testBankerConstructor() {
		//The banker only gets a hand so return a hand
		Deck bankerHiLoDeck = new Deck();

		CardHand bankerHand=null;
		bankerHand = bankerHiLoDeck.dealHand(1);
		Banker p = new Banker(); //sets with the constructor 
		p.setHand(bankerHand);
		assertEquals(bankerHand, p.getHand()); //shows the hand dealt
	}
}
