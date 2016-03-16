package models;

public class DataContainer {
	public final static int ROWSC = 6;
	public final static int COLUMNSC = 7;
	public final static int FULL = ROWSC*COLUMNSC;

	public final static int COUNTFORWIN = 4;
	
	private static int[][] board = new int[ROWSC][COLUMNSC];
	
	public static int[][] getBoard() {
		return board;
	}

	public static void initializeBoard() {
		for(int i=0;i<ROWSC;i++)
			for(int j=0;j<ROWSC;j++)
				board[i][j] = 0;
	}
	
}
