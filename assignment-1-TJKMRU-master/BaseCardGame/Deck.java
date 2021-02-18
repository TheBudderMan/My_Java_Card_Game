import java.util.ArrayList;
import java.util.Random;

/**
 * used to represent a standard deck of cards
 * @author jkidney
 */
public class Deck 
{
	private ArrayList<Card> cards;
	private Random ranGen;


	/**
	 * Default constructor that will create all 52 cards for the deck
	 */
	public Deck()
	{
		cards = new ArrayList<Card>();
		ranGen = new Random();
		setUpAllCards();
	}

	/**
	 * Sets up all 52 cards
	 */
	private void setUpAllCards()
	{
		cards.clear(); // remove any cards that may already be in the deck

		for(int index = 0; index < Card.SUITS.length; index++)
			for(int rank=Card.MIN_CARD_VALUE; rank <= Card.MAX_CARD_VALUE; rank++)
				cards.add(  new Card( rank, Card.SUITS[index] ) );
	}

	/**
	 * Does a shuffle of cards not suing the Colection.shuffle() method
	 */
	public void shuffle()
	{
		Card card1=null, card2=null; // used when swapping cards in the deck
		int shuffleSteps = 0;
		int index1 = 0, index2 = 0;

		//only shuffle if there are cards currently in the deck
		if(cards.size() > 0)
		{
			//generate a random number in the range of 20 to 49
			shuffleSteps = 20 + ranGen.nextInt( 50 - 20);

			for(int step = 0; step < shuffleSteps; step++)
			{
				//pick 2 random locations for cards to swap
				index1 = ranGen.nextInt( cards.size() );
				index2 = ranGen.nextInt( cards.size() );

				card1 = cards.get(index1);
				card2 = cards.get(index2);

				//swap the cards in the arraylist
				cards.set(index1, card2 );
				cards.set(index2, card1 );
			}

		}

	}
	/**
	 * Returns the number of cards still in the deck
	 * @return the number of cards in the deck
	 */
	public int size()
	{
		return cards.size();
	}
	/**
	 * Returns the top card on the deck without removing it
	 * @return a reference to the top card. Null if the deck is empty
	 */
	public Card topCard()
	{
		Card top = null;

		if(cards.size() > 0 )
			top = cards.get(0);

		return top;
	}

	/**
	 * Cuts the deck randomly. resulting in the revealed card being at the bottom of the deck, 
	 * and the card which was originally under that card at the top of the deck.
	 * @return a reference to the cut card. it will not be removed from the deck
	 */
	public Card cut()
	{
		Card cut = null, tmp = null;
		//ArrayList<Card> tempList = null;
		int cutIndex = 0;

		if(cards.size() > 0)
		{
			cutIndex = ranGen.nextInt( cards.size() );
			cut = cards.get(cutIndex);
			for(int index=0; index <= cutIndex; index++)
			{
				//takes the current card at the top and moves it to the bottom of the deck
				tmp = cards.remove(0);
				cards.add(tmp);
			}

		}

		return cut;
	}

	/**
	 * Deals the top card from the deck
	 * @return the top card. null if the deck is empty
	 */
	public Card dealCard()
	{
		Card dealCard = null;

		if(cards.size() > 0)
			dealCard = cards.remove(0);

		return dealCard;
	}
	/**
	 * Deals out a single hand with the requested number of cards.
	 * If the requested number of cards are not in the deck it will 
	 * fill the had with as many as it can
	 * @param numCards the requested number of cards in the hand
	 * @return A new hand object with the dealt cards
	 */
	public CardHand dealHand(int numCards)
	{
       int count = numCards;
       CardHand hand = new CardHand();
      
       if(count > cards.size())
    	   count = cards.size();

       for(int index = 0; index < count; index++)
           hand.addCard(  dealCard() );
       
       return hand;
	}
	
	/**
	 * Builds display string for testing purposes
	 */
	public String toString()
	{
		String newLine = System.getProperty("line.separator");
		String ret = "Deck " + newLine + "{" + newLine + "\t";
		
		for(int index=0; index < cards.size(); index++)
		{
		    Card curr = cards.get(index);
			ret += String.format(" %2d",index) + "[" + String.format("%17s", curr) + "]";
			
			if( ( (index+1) % 3) ==  0) ret += newLine + "\t"; // every third card add a new line
		}
		
		return  ret +"}";
	}
}
