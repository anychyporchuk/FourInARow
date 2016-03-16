package controllers;
import java.util.Arrays;

import models.DataContainer;


public class GameController {
	private static int[][] board;	//1-first player, -1 - second player, 0 - empty
	
	
	private static int[] indexEmptyCoins;	//service array indexes of desired empty places in a board
	public static int currentPlayer;
	public static boolean canPlay;
	
	/**
	 * Initialize board, service array and current player
	 */
	public static void initializeGame(){
		DataContainer.initializeBoard();
		board = DataContainer.getBoard();
		indexEmptyCoins = new int[DataContainer.COLUMNSC];
		currentPlayer = 0;
		canPlay = true;	
	}

//	
	/**
	 * Finds position for the coin in the column
	 * @param column - the desired column
	 * @return the empty right place in the column
	 */
	public static int getPosition(int column){
		int currpl = currentPlayer == 0 ? -1 : 1;
			int res = indexEmptyCoins[column];
			if(res<DataContainer.ROWSC){
				int isfull = addCoin(currpl,column);
				return isfull == DataContainer.FULL ? DataContainer.FULL : res;
			} else 
				return DataContainer.ROWSC;
		
	}
	
	
	 /**
	  * Adds the coin to the board
	 * @param currpl - index of current player
	 * @param column - the desired column
	 * @return
	 */
	private static int addCoin(int currpl, int column)
     {
         board[indexEmptyCoins[column]][column] = currpl;
         indexEmptyCoins[column]++;
         int sum = Arrays.stream(indexEmptyCoins).sum();
         if (sum == (DataContainer.COLUMNSC) * (DataContainer.ROWSC))
             return DataContainer.FULL;
         else return 0;
     }
	 
	 
}
