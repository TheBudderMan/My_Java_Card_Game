import java.util.ArrayList;

/**
 * Used to represent a hand dealt to a player. Basic for now but can be added to
 * @author jkidney
 * @version March 11, 2013
 */

public class playerChipPool {
    private ArrayList<casinoChip> chipPool;
    
    /**
     * Constructor
     */
    public playerChipPool()
    {
    	chipPool = new ArrayList<casinoChip>();
    }
    
    /**
     * Gives the number of cards in the hand
     * @return the number of cards in the hand
     */
    public int size() { return chipPool.size(); }
    
    /**
     * Adds a single card to the current hand object
     * @param addedCard the reference to the card to add
     */
    public void addChip(casinoChip addedChip) { chipPool.add(addedChip); }
    
    /**
     * returns the given card in the hand
     * @param index the index ( array based ) for a specific card in the had
     * @return the card requested
     */
    public casinoChip get(int index) { return chipPool.get(index); }

	/**
	 * Builds a string to output the hand object for testing
	 */
	public String toString() 
	{ 
		String ret = "Hand ("+chipPool.size()+") [cards=";
		
		for(int index=0; index < chipPool.size(); index++)
		{
		    casinoChip curr = chipPool.get(index);
			ret += String.format(" %2d",index) + "[" + String.format("%17s", curr) + "]";
		}
		
		return ret + "]"; }
}

