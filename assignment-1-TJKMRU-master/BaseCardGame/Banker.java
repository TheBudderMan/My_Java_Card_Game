/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */
public class Banker {
	/*
	 * create the banker data here.
	 * */
	int numCards = 0;
	CardHand hand=null;
	final String name = "The Banker";
	public Banker() {
		
	}
	public CardHand getHand() { return hand; }
	public void setHand(CardHand a) {this.hand = a;}
	/**
	 * converts the object to a string that can be used to display the cards informon 
	 */
	public String toString()
	{
		return "Banker name:" + name + "\nBanker hand\n" + hand.toString();   
	}
}
