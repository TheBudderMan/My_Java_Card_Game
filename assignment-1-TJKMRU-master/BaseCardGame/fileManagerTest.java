import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class fileManagerTest {

	@Test
	void test() throws IOException {
		int initialChips = 1000;
		fileManager theFile = new fileManager();
		
		Player player = new Player("Tyler",initialChips);
		theFile.bigSave(player);
		theFile.readFile(player);
		assertEquals("Tyler", player.getName());  // returns the values after saving and loading
		assertEquals(initialChips, player.getChips()); //returns the amount of chips as well
		
	}

}
