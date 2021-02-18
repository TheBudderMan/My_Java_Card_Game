/**
 * @author Tyler Johnston Kent
 * @version March 6th 2020
 */
public class Table {
	private Player player; private Banker banker; private int pSum; private int bSum;
	public Table(Player p, Banker b) {
		player = p; banker = b;
	}
	//
	public Player getPlayer() {return player;}
	public Banker getBanker() {return banker;}
	public int getpHandSum() {return pSum;}
	public int getbHandSum() {return bSum;}
	public void showPuntoTable(Player player, Banker banker, int pSum, int bSum) {
		//the hands are then shown to the user, again.
		System.out.println("\n[" +"---------------" +"]");
		System.out.println("playerHand"+player.getHand());	 	
		System.out.println("   players points");
		System.out.println("      [-"+pSum+"-]   ");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println("      [-"+bSum+"-]   ");
		System.out.println("   bankers points");
		System.out.println("bankerHand="+banker.getHand());
		System.out.println("["+"---------------"+"]\n");
	}
	//
	public void showPreHiLoTable(Player player, Banker banker) {
		//the hands are then shown to the user, again.
		System.out.println("[" +"Pre-bet table." +"]");
		System.out.println("[" +"---------------" +"]");
		System.out.println("playerHand\n"+player.getHand().get(0));	 	
		System.out.println("   player hand size");
		System.out.println("      [-"+player.getHand().size()+"-]   ");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println("      ["+"| " + "|]");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println("      [-"+banker.getHand().size()+"-]   ");
		System.out.println("   banker hand size");
		System.out.println("[Bet to showdown with the banker!]" +"\nbankerHand");
		System.out.println("["+"---------------"+"]\n");
	}	
	//
	public void showHiLoTable(Player player, Banker banker) {
		//the hands are then shown to the user, again.
		Card out = new Card(0,0);
		Card out2 = new Card(0,0);
		CardHand outs = new CardHand();
		Card pCard = player.getHand().get(0);
		Card bCard = banker.getHand().get(0);
		int a = player.getHand().get(0).getRank();
		int b = banker.getHand().get(0).getRank();
		if (a > b) {
			out = pCard;
			outs.addCard(out);
		}
		if (a < b) {
			out = bCard;
			outs.addCard(out);
		}
		if (a == b) {
			out = bCard;
			out2 = pCard;
			outs.addCard(out);
			outs.addCard(out2);
		}
		System.out.println("[" +"Showdown Table!" +"]");
		System.out.println("[" +"---------------" +"]");
		System.out.println("playerHand="+player.getHand().get(0));	 	
		System.out.println("   players points");
		System.out.println("      [-"+player.getHand().size()+"-]   ");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println(" ["+"|" +outs+ "|] The winner!");
		System.out.println("    ["+"|==-==|"+"]");
		System.out.println("      [-"+banker.getHand().size()+"-]   ");
		System.out.println("   bankers points");
		System.out.println(banker.getHand().get(0)+"\nbankerHand");
		System.out.println("["+"---------------"+"]\n");
	}
}
