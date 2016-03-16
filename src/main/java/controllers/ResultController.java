package controllers;

public interface ResultController {
	public boolean checkResult(int row, int col);
	public boolean checkHorizontal(int row);
	public boolean checkVertical(int col);
	public boolean checkLeftDiagonal(int row, int col);
	public boolean checkRightDiagonal(int row, int col);
}
