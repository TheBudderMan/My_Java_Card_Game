import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class fileManager {
	private ConsoleComunicationController comm;
	
	
	
	
	
	
	public void readFile(Player player)throws IOException {
   		
		int initialChips = 1000;
		String fName = "playerFile.txt";
		File file = new File(fName);		
		if(!file.exists()) {			
		    System.out.println("Sorry that doesn't exist.. Let's make a new one!");
		    String name = comm.getInput_String("Please enter a name: ");
		    Player newPlayer = new Player(name,initialChips);
		    bigSave(newPlayer); 
		    } 
		else {
		   	System.out.println("Let's load the player!");
		}		
		try{	   	
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String line;	    
	    while((line = in.readLine()) != null){
	    	String[] columns = line.split(", ");
	    	String pName= columns[0];                    
            String pAgeUse = columns[1];
            int casinoChips = Integer.valueOf(pAgeUse);
	    	Player passLoad = new Player(pName,casinoChips); 
            player = passLoad;
	        System.out.println("Loading player...."+line+"<--Chips");
	    }
	    in.close();
	   	}
	   	catch(Exception e){
	   	    System.out.println("Unable to open: " + fName);
	   	}
	}
	
	/**
	 * This method saves the passenger data to the index file.
     * @param fName is the string used to find the file to load. 
	 * @throws IOException
	 */
	public void bigSave(Player player) throws IOException {
		String fName = "playerFile.txt";
        PrintWriter out = new PrintWriter(fName);        
        Player passIn;
        passIn = player;                    
        out.print(passIn.getName() + ", ");
        out.println(passIn.getChips());
        
        out.flush();
        out.close();        
    }

}
