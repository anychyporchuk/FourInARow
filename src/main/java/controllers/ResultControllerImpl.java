package controllers;

import models.DataContainer;

public class ResultControllerImpl implements ResultController {
	
	private int[][] board;
	
	
	public ResultControllerImpl(){
		board = DataContainer.getBoard();
	}
	


	
	/**
	 * Searches the combination all possible ways
	 * row - the desired row
	 * col - index of last added coin by column
	 * @return result of searching
	 */
	public boolean checkResult(int row, int col) {
		return (checkHorizontal(row)) || (checkVertical(col)) ||
		         (checkLeftDiagonal(row, col)) || (checkRightDiagonal(row, col));
		
	}
	
	/**
	 * Searches the combination horizontally
	 * row - the desired row
	 * @return result of searching
	 */
	public boolean checkHorizontal(int row)
    {
        int countOfIdent = 1;
        for (int i = 1; i < DataContainer.COLUMNSC; i++)
            if (board[row][i] != 0 && board[row][i - 1] == board[row][i])
            {
                countOfIdent++;
                if (countOfIdent == DataContainer.COUNTFORWIN) return true;
            }
            else countOfIdent = 1;
        return false;
    }
	 
	/**
	 * Searches the combination vertically
	 * row - the desired row
	 * @return result of searching
	 */
	public boolean checkVertical(int col)
    {
        int countOfIdent = 1;
        for (int i = 1; i < DataContainer.ROWSC; i++)
            if (board[i][col] != 0 && board[i - 1][col] == board[i][col])
            {
                countOfIdent++;
                if (countOfIdent == DataContainer.COUNTFORWIN) return true;
            }
            else countOfIdent = 1;
        return false;
    }
	
	/**
	 * Searches the combination from the left top to the right bottom diagonally
	 * row - the desired row
	 * col - the desired column
	 * @return result of searching
	 */
	public boolean checkLeftDiagonal(int row, int col)
    {
        for (int i = 0; i < DataContainer.COUNTFORWIN; i++)
        {
            int countOfIdent = 0;
            for (int j = 0; j < DataContainer.COUNTFORWIN; j++)
            {
                if (row + j - i >= 0 && row + j - i < DataContainer.ROWSC
                    && col - j + i >= 0 && col - j + i < DataContainer.COLUMNSC
                    && board[row][col] == board[row + j - i][col - j + i])
                {
                    countOfIdent++;
                }
                if (countOfIdent >= DataContainer.COUNTFORWIN) return true;
            }
        }
        return false;
    }
	
	/**
	 * Searches the combination from the right top to the left bottom diagonally
	 * row - index of last added coin by row
	 * col - index of last added coin by column
	 * @return result of searching
	 */
	public boolean checkRightDiagonal(int row, int col)
    {
        for (int i = 0; i < DataContainer.COUNTFORWIN; i++)
        {
            int countOfIdent = 0;
            for (int j = 0; j < DataContainer.COUNTFORWIN; j++)
            {
                if (row + j - i >= 0 && row + j - i < DataContainer.ROWSC
                    && col + j - i >= 0 && col + j - i < DataContainer.COLUMNSC
                    && board[row][col] == board[row + j - i][col + j - i])
                {
                    countOfIdent++;
                }
                if (countOfIdent >= DataContainer.COUNTFORWIN) return true;
            }
        }
        return false;
    }

}
