/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */
public class Player {
	/*
	 * create the player data here.
	 * */
	int numCards = 0; int chips;
	CardHand hand=null;
	String name; 
	
	public Player(String s, int c) {
		this.name = s; 
		this.chips = c;
	}
	
	public String getName() { return name;}
	public CardHand getHand() { return hand; }
	public int getChips() {return chips;}
	public void setName(String a) {this.name = a;}
	public void setHand(CardHand a) {this.hand = a;}
	public void setChips(int a) {this.chips = a;}
	public void addChips(int a) {this.chips = chips + a;}
	public void takeChips(int a) {this.chips = chips - a;}
	/**
	 * converts the object to a string that can be used to display the players information
	 */
	public String toString()
	{
		return "Player name:" + name + "\nPlayer hand\n" + hand.toString();   
	}
}
