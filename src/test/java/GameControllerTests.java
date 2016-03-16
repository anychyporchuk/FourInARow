import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controllers.GameController;

public class GameControllerTests {
	
	@BeforeClass
	public static void initializeGame(){
		GameController.initializeGame();
	}

	@Test
	public void getPositionTest() {
		assertEquals(GameController.getPosition(0),0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getOutOfArrayPositionTest() {	
			GameController.getPosition(30);
	}

}
