import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import controllers.ResultController;
import controllers.ResultControllerImpl;
import models.DataContainer;

public class ResultControllerTests {
	static ResultController resultController;
	static int[][] board;
	
	@BeforeClass
	public static void initialize(){
		DataContainer.initializeBoard();
		board = DataContainer.getBoard();
		resultController = new ResultControllerImpl();
	}
	
	@Test
	public void testHorizontal() {
		DataContainer.initializeBoard();
		for(int i = 0;i<DataContainer.COUNTFORWIN;i++)
			board[0][i] = 1;
		assertEquals(resultController.checkHorizontal(0),true);
	}
	
	@Test
	public void testVertical() {
		DataContainer.initializeBoard();
		for(int i = 0;i<DataContainer.COUNTFORWIN;i++)
			board[i][0] = 1;
		assertEquals(resultController.checkVertical(0),true);
	}
	
	@Test
	public void testLeftDiagonal() {
		DataContainer.initializeBoard();
		for(int i = 0;i<DataContainer.COUNTFORWIN;i++)
			board[DataContainer.COUNTFORWIN-i][i] = 1;
		assertEquals(resultController.checkLeftDiagonal(DataContainer.COUNTFORWIN-1,0),true);
	}
	
	@Test
	public void testRightDiagonal() {
		DataContainer.initializeBoard();
		for(int i = 0;i<DataContainer.COUNTFORWIN;i++)
			board[i][i] = 1;
		assertEquals(resultController.checkRightDiagonal(DataContainer.COUNTFORWIN-1,DataContainer.COUNTFORWIN-1),true);
	}

}
