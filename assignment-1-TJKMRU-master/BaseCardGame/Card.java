/**
 * Represents all functionality for a basic card 
 * @author jkidney
 * @version March 11, 2013
 */
public class Card 
{
	//Constants used for rank
	public static final int JACK  = 11;
	public static final int QUEEN = 12;
	public static final int KING  = 13;
	public static final int ACE   = 14; // makes ace evaluate to high

	//Constants used for rank generation of all cards
	public static final int MIN_CARD_VALUE  = 2; // minimum value for a card
	public static final int MAX_CARD_VALUE  = 14; // maximum value for a card

	//constants for suit
	public static final int DIAMOND = 0;
	public static final int HEART   = 1;
	public static final int SPADE   = 2;
	public static final int CLUB    = 3;

	// all suit values, can be used for card generation
	public static final int[] SUITS = {DIAMOND, HEART, SPADE,CLUB }; 

	private int rank;
	private int suit;

	/**
	 * Constructor
	 * @param rank sets the rank of the card ( values 2 to 14, based upon rank constants )
	 * @param suit sets the suit of the card ( 0 to 3, based upon suit constants )
	 */
	public Card(int rank, int suit) 
	{
		this.rank = rank;
		this.suit = suit;
	}

	//getters and setters
	public int getRank() { return rank; }
	public int getSuit() { return suit; }

	/**
	 * convert the rank to a string with proper names
	 * @return the string representing the rank of the card
	 */
	private String rankToString()
	{
		String str = "";

		if( rank >= MIN_CARD_VALUE && rank < JACK )
			str += rank;
		else
		{
			switch(rank)
			{ 
			case  JACK: str = "Jack"; break;
			case QUEEN: str = "Queen"; break;
			case  KING: str = "King"; break;
			case   ACE: str = "Ace"; break;
			}

		}

		return str;
	}

	/**
	 * Compares to determine if the current object is the same as the object given
	 * @param compareToObj the object to compare the current one against
	 * @return true if they are the same. False otherwise
	 */
	public boolean equals(Card compareToObj ) 
	{
		return (rank == compareToObj.rank ) && ( suit == compareToObj.suit );
	}

	/**
	 * convert the suit to a string with proper names
	 * @return the string representing the suit of the card
	 */
	private String suitToString()
	{
		String str = "";

		switch(suit)
		{ 
		case DIAMOND: str = "Diamonds"; break;
		case   HEART: str = "Hearts"; break;
		case    CLUB: str = "Clubs"; break;
		case   SPADE: str = "Spades"; break;
		}

		return str;
	}

	/**
	 * converts the object to a string that can be used to display the cards informon 
	 */
	public String toString()
	{
		return rankToString() + " of " + suitToString();   
	}

}
