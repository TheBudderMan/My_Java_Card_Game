
public class chipID {
	private int IDNUM1; private int IDNUM2;
	private int IDNUM3; private int IDNUM4;
	
	
	public chipID(int a, int b, int c, int d) {
		IDNUM1 = a; IDNUM2 = b;
		IDNUM3 = c; IDNUM4 = d;
	}
	
	public int getID1() {return IDNUM1;}
	public int getID2() {return IDNUM2;}
	public int getID3() {return IDNUM3;}
	public int getID4() {return IDNUM4;}
	
	public String toString() {
		return "["+IDNUM1 + "][" + IDNUM2 +"]["+ IDNUM3 +"]["+ IDNUM4 + "] ";
	}

}
