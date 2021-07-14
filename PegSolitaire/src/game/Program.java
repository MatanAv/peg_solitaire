package game;

public class Program {
	public static void main(String[] args) {
		String board[][] = new String[7][7];
		int countMoves = 0;
		fillBoard(board);
		printBoard(board);
		playBoard(board, countMoves);
	}

	public static void fillBoard(String board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if ((i < 2 && j < 2) || (i < 2 && j > 4)
						|| (i > 4 && j < 2) || (i > 4 && j > 4))
					board[i][j] = "  ";
				else
					if (i == 3 && j == 3)
						board[i][j] = "- ";
					else
						board[i][j] = "o ";
			}
		}	
	}
	
	public static void printBoard(String board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				System.out.print(board[i][j]);
			System.out.println();
		}
	}
	
	public static void toTopMove(String board[][], int i, int j) {
		board[i-1][j] = "o ";
		board[i][j] = "- ";
		board[i+1][j] = "- ";
	}
	
	public static void toBottomMove(String board[][], int i, int j) {
		board[i+1][j] = "o ";
		board[i][j] = "- ";
		board[i-1][j] = "- ";
	}
	
	public static void toLeftMove(String board[][], int i, int j) {
		board[i][j-1] = "o ";
		board[i][j] = "- ";
		board[i][j+1] = "- ";
	}	
	
	public static void toRightMove(String board[][], int i, int j) {
		board[i][j+1] = "o ";
		board[i][j] = "- ";
		board[i][j-1] = "- ";
	}	
	
	public static boolean playBoard(String board[][], int countMoves) {		
		boolean noAttached = false;		// false == there's still attached balls in the game
		boolean madeMove = false;		// false == move has still not made in this turn
		int i=0, j=0;
		
		if (noAttached)						// base case
			printBoard(board);
		else {		
			while (i < 7 && !madeMove) {
				while (j < 7 && !madeMove) {				
					if (board[i][j].equals("o ")) {		// for iterate only in-game places and with ball places
						// Checking Edges
						if (i-1 < 0) {		// top edge
							if (board[i][j+1].equals("- ") && board[i][j-1].equals("o ")) {		// to-right move
								System.out.println(++countMoves);
								toRightMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i][j-1].equals("- ") && board[i][j+1].equals("o ")) {	// to-left move
								System.out.println(++countMoves);
								toLeftMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}	
						}
							
						else if (i+1 > 6) {		// bottom edge
							if (board[i][j+1].equals("- ") && board[i][j-1].equals("o ")) {		// to-right move
								System.out.println(++countMoves);
								toRightMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i][j-1].equals("- ") && board[i][j+1].equals("o ")) {	// to-left move
								System.out.println(++countMoves);
								toLeftMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
						}
						
						else if (j-1 < 0) {		// left edge
							if (board[i+1][j].equals("- ") && board[i-1][j].equals("o ")) {	// to-bottom move
								System.out.println(++countMoves);
								toBottomMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i-1][j].equals("- ") && board[i+1][j].equals("o ")) {	// to-top move
								System.out.println(++countMoves);
								toTopMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
						}
						
						else if (j+1 > 6) {		// right edge
							if (board[i+1][j].equals("- ") && board[i-1][j].equals("o ")) {	// to-bottom move
								System.out.println(++countMoves);
								toBottomMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i-1][j].equals("- ") && board[i+1][j].equals("o ")) {	// to-top move
								System.out.println(++countMoves);
								toTopMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
						}
						
						else {		// Not edges
							if (board[i][j+1].equals("- ") && board[i][j-1].equals("o ")) {		// to-right move
								System.out.println(++countMoves);
								toRightMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i][j-1].equals("- ") && board[i][j+1].equals("o ")) {	// to-left move
								System.out.println(++countMoves);
								toLeftMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i+1][j].equals("- ") && board[i-1][j].equals("o ")) {	// to-bottom move
								System.out.println(++countMoves);
								toBottomMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
							else if (board[i-1][j].equals("- ") && board[i+1][j].equals("o ")) {	// to-top move
								System.out.println(++countMoves);
								toTopMove(board, i, j);
								madeMove = true;
								printBoard(board);
							}
						}						
					}
					j++;
				}
				
				j = 0;
				i++;
				if (i == 7) {						// no move has been made in this turn
					madeMove = false;
					noAttached = true;
				}
			}
			if (!noAttached)
				playBoard(board, countMoves);		// recursive call
		}
		return noAttached;
	}
}
