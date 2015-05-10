package aca14md;

import java.util.ArrayList;

public class Chess {
	
	// Moves made in the game... useful for check detection - IGNORE
	public static ArrayList<Move> movesMade = new ArrayList<>();
	
	public static GraphicalDisplay gui = new GraphicalDisplay(500, 500);
	
	public static void main(String[] args){
		
		// Create object needed for the game
		Board board = new Board();
		TextDisplay display = new TextDisplay();
		
		// Both set of pieces, diff colour
		Pieces white = new Pieces(board, PieceCode.WHITE);
		Pieces black = new Pieces(board, PieceCode.BLACK);
		
		
		// Get players names
		System.out.println("Player one (white) enter name: ");
		String nameOne = UsefulCode.getConsoleInput();
		System.out.println();
		System.out.println("Player two (black) enter name: ");
		String nameTwo = UsefulCode.getConsoleInput();
		
		// Both players
		Player playerOne = new AgressivePlayer(nameOne, white, board, null);
		Player playerTwo = new RandomPlayer(nameTwo, black, board, null);
		
		// Set both opponents
		playerOne.setOpponent(playerTwo);
		playerTwo.setOpponent(playerOne);
		
		// Other vars needed for the game mechanics
		Player player = null; // null neded to compile...
		int turn = 0;
		boolean gameOver = false;
		
		showHelp(); // Show initial help
		display.showPiecesOnBoard(board.getData());
		gui.setVisible(true);
		gui.showPiecesOnBoard(board.getData());
		
		while(!gameOver){
			
			// Alternate turns
			if(turn % 2 == 0) player = playerOne;
			else player = playerTwo;
			
			// bool to check valid move
			boolean validMove = false;
			while(!validMove){
				System.out.println(player+"'s turn. ");
				
				if(player.getClass() == HumanPlayer.class){
					validMove = ((HumanPlayer) player).makeGUIMove(gui);
				}
				else
					validMove = player.makeMove();
					
			}
			
			Move lastMove = movesMade.get(movesMade.size() - 1);
			
			// Showing board and updating game
			display.showPiecesOnBoard(board.getData());
			gui.updateMove(lastMove.getXFrom(), lastMove.getYFrom(), lastMove.getXTo(), lastMove.getYTo());
			//gui.showPiecesOnBoard(board.getData());
			turn++;
			gameOver = UsefulCode.isGameOver(playerOne.getPieces(), 
											 playerTwo.getPieces());
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Gameover therefore show winner
		System.out.println("Congratulations!!! "+player+" has won the game.");
	}
	
	/**
	 * This method simply prints out text to the 
	 * console in order to help the user
	 * play the game. Mainly created to make code
	 * more readable.
	 */
	public static void showHelp(){
		System.out.println();
		System.out.println("### Chess Help ###");
		System.out.println();
		System.out.println("1 - Movements are in the form LetterNumberLetterNumber"
				           +" (E.g A1A2 - move pawn in A1 to A2).");
		System.out.println("2 - Spaces in the inputs are allowed, meaning that"
						   +"'A 2 A 4' = 'A  2A4'.");
		System.out.println("3 - The first coordinate represents the piece you chose to"
						   +"move and the second is the place to move to. E.g. "
						   + "'A2A3 means move piece in rank A file 2 to rank A "
						   + "file 3");
		System.out.println("4 - Black pieces will be at the top while the white "
						   +"pieces are initially located at the bottom of the board");
		System.out.println("5 - Pieces are represented with their first letter (with the "+
						   "exception of knights which are represented with the letter n.");
		System.out.println("6 - White pieces are represented with lowercase characters"
						   +" while black pieces are represented with uppercase characters.");
		System.out.println();
		System.out.println("Press enter to continue...");
		UsefulCode.getConsoleInput();
		System.out.println();
	}
}
