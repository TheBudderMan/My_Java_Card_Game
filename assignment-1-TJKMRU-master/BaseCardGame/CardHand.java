import java.util.ArrayList;

/**
 * Used to represent a hand dealt to a player. Basic for now but can be added to
 * @author jkidney
 * @version March 11, 2013
 */
public class CardHand 
{
    private ArrayList<Card> cards;
    
    /**
     * Constructor
     */
    public CardHand()
    {
    	cards = new ArrayList<Card>();
    }
    
    /**
     * Gives the number of cards in the hand
     * @return the number of cards in the hand
     */
    public int size() { return cards.size(); }
    
    /**
     * Adds a single card to the current hand object
     * @param addedCard the reference to the card to add
     */
    public void addCard(Card addedCard) { cards.add(addedCard); }
    
    /**
     * returns the given card in the hand
     * @param index the index ( array based ) for a specific card in the had
     * @return the card requested
     */
    public Card get(int index) { return cards.get(index); }

	/**
	 * Builds a string to output the hand object for testing
	 */
	public String toString() 
	{ 
		String ret = "Hand ("+cards.size()+") [cards=";
		
		for(int index=0; index < cards.size(); index++)
		{
		    Card curr = cards.get(index);
			ret += String.format(" %2d",index) + "[" + String.format("%17s", curr) + "]";
		}
		
		return ret + "]"; }
}
