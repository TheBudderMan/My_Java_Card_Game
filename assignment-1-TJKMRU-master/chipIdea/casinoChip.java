import java.util.ArrayList;
import java.util.Random;
public class casinoChip {
	private Random ranGen;
	private chipID cID;
	final int chipValue;
	private int index1, index2, index3, index4;
	private ArrayList<chipID> chipID;
	public casinoChip(chipID a) {
		chipValue = 1; cID = a; //chipID accepted as parameter and getters used to define IDNumber
		index1 = cID.getID1(); index2 = cID.getID2();
		index3 = cID.getID3(); index4 = cID.getID4();
	}
	//so we take the chipID object in as a parameter and jumble the values here with rng
	private ArrayList<chipID> chipID() { 		
		cID = new chipID(rnGenerator(index1),rnGenerator(index2),rnGenerator(index3),rnGenerator(index4));		
		chipID.add(cID);
		return chipID;
	}
	
	private chipID getID() {return cID;}
	
	private int rnGenerator(int a) {
		/*So the hopes is to create a unique IDNumber for each chip*/		
		//generate a random number in the range of 1 to 49
		a = 1 + ranGen.nextInt( 50 - 1);
		return a;
	}
	
	public int getChipValue() {return chipValue;}
	
	public String toString() {
		return "CHIPID#" + cID + " ";
	}
}
