package aca14md;

import java.util.ArrayList;

public class Chess {
	
	// Moves made in the game... useful for check detection - IGNORE
	public static ArrayList<Move> movesMade = new ArrayList<>();
	
	public static GraphicalDisplay gui = new GraphicalDisplay(714, 500);
	
	public static void main(String[] args){
		
		// Create object needed for the game
		Board board = new Board();
		TextDisplay display = new TextDisplay();
		
		// Both set of pieces, diff colour
		Pieces white = new Pieces(board, PieceCode.WHITE);
		Pieces black = new Pieces(board, PieceCode.BLACK);
		
		
		// Get players names
		System.out.println("Player one (white) enter name: ");
		//String nameOne = UsefulCode.getConsoleInput();
		String nameOne = "a";
		System.out.println();
		System.out.println("Player two (black) enter name: ");
		//String nameTwo = UsefulCode.getConsoleInput();
		String nameTwo = "b";
		// Both players
		Player playerOne = new RandomPlayer(nameOne, white, board, null);
		Player playerTwo = new AggressivePlayer(nameTwo, black, board, null);
		
		// Set both opponents
		playerOne.setOpponent(playerTwo);
		playerTwo.setOpponent(playerOne);
		
		// Other vars needed for the game mechanics
		Player player = null; // null neded to compile...
		int turn = 0;
		boolean checkMate = false,
				staleMate = false;
		
		showHelp(); // Show initial help
		display.showPiecesOnBoard(board.getData());
		gui.setVisible(true);
		gui.showPiecesOnBoard(board.getData());
		
		while(!(checkMate || staleMate)){
			
			// Alternate turns
			if(turn % 2 == 0) player = playerOne;
			else player = playerTwo;
			
			
			/* TESTING FOR STALE-MATE USING NULL POINTERS */
			try{
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
			}
			catch(NullPointerException e){
				System.out.println("This is a typical stale-mate! Game is a draw.");
				staleMate = true;
			}
			/* END TESTING FOR STALE MATE */
			
			
			// Get last move made			
			Move lastMove = movesMade.get(movesMade.size() - 1);
			
			// Showing board and updating game
			System.out.println(lastMove.getPiece());
			System.out.println(lastMove);
			
			// Causes bugs when stale mate - o last move made
			if(!staleMate)
				gui.updateMove(lastMove.getXFrom(), lastMove.getYFrom(), lastMove.getXTo(), lastMove.getYTo());
			
			turn++;
			
			
			/* TESTING FOR CHECK AND MATE */
			if(player.getOpponent().isCheck(null))
				checkMate = player.getOpponent().isMate();
			/* TESTInG ENDS */
			
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
		//UsefulCode.getConsoleInput();
		System.out.println();
	}
}
