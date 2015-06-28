package aca14md;
/* 
 * This is the main class of our
 * chess game. This class basically
 * controls all components of the GUI,
 * as well as checking for when a game is
 * over (checkmate, stalemate).
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 15/05/2015
 */
import java.util.ArrayList;

public class Chess {
	
	// Moves made in the game... useful for check detection
	public static ArrayList<Move> movesMade = new ArrayList<>();
	
	// GUI needs to be accessed by other objects
	// Also, don't change resolution, the LayoutManager is a pain
	//in the back side.
	public static GraphicalDisplay gui = new GraphicalDisplay(714, 500);
	
	public static void main(String[] args){
		
		while(true){ //Sorry about while(true), no other way to do this :(
			
			// Create object needed for the game
			Board board = new Board();	
			
			// Both set of pieces, diff colour
			Pieces white = new Pieces(board, PieceCode.WHITE);
			Pieces black = new Pieces(board, PieceCode.BLACK);
			
			// Show pieces initially on board
			gui.setVisible(true);
			gui.showPiecesOnBoard(board.getData());
			
			// Create menu adapter to get menu settings
			MenuAdapter menuControl = gui.getMenuControl();
			menuControl.waitCurrentButton();
			
			// Get delay between turns (useful for AI testing)
			int delay = menuControl.getDelay();
			
			// Get board adapter to control board view
			BoardGUIAdapter boardControl = gui.getBoardControl();
		
			// Both players
			Player playerOne = menuControl.getPlayerOne(white, board);
			Player playerTwo = menuControl.getPlayerTwo(black, board);
			
			// Set both opponents
			playerOne.setOpponent(playerTwo);
			playerTwo.setOpponent(playerOne);
			
			// Other vars needed for the game mechanics
			Player player = null; // null neded to compile...
			int turn = 0;
			boolean checkMate = false,
					staleMate = false;
			
			while(!(checkMate || staleMate)){
				
				// Alternate turns
				if(turn % 2 == 0) player = playerOne;
				else player = playerTwo;
				
				
				/* 
				 * The reason why I ignored null pointer exceptions
				 * in the methods related to making moves is 
				 * because when a null pointer exception is thrown,
				 * this means there's no available moves and since
				 * this player is not in check, then it's stale mate.
				 */
				try{
					// bool to check valid move
					boolean validMove = false;
					while(!validMove){
						menuControl.setMessage(player+"'s turn. ");
						validMove = player.makeMove();
					}
				}
				catch(NullPointerException e){
					System.out.println("This is a typical stale-mate! Game is a draw.");
					staleMate = true;
				}
				
				
				// Get last move made			
				Move lastMove = movesMade.get(movesMade.size() - 1);
				int xFrom = lastMove.getXFrom(),
					yFrom = lastMove.getYFrom(),
					xTo = lastMove.getXTo(),
					yTo = lastMove.getYTo();
					
				
				// Causes bugs when stale mate - o last move made
				if(!staleMate)
					boardControl.updateMove(xFrom, yFrom, xTo, yTo);
				
				
				/* 
				 * Check mate testing works by firstly checking
				 * if this players opponent is in check. If he is,
				 * then we proceed to check if he has available moves.
				 * If not, then it's check mate.
				 */
				if(player.getOpponent().isCheck(null))
					checkMate = player.getOpponent().isMate();
				
				/*
				 * This is the other case where a stale-mate might
				 * occur. If there are only two kings lefts, then
				 * no checkmate is possible because you have to get 
				 * out of check in the first case.
				 */
				if(black.getNumPieces() == 1 && white.getNumPieces() == 1)
					staleMate = true;
				
				
				turn++; // Other players turn
				
				// Puts delay if selected
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			// Game over therefore show winner/draw
			if(staleMate)
				menuControl.setMessage("Stale Mate! It's a draw.");
			else
				menuControl.setMessage("Congratulations!!! "+player+" has won the game.");
			
			
			// Gets response from user whether to continue or not
			menuControl.addResetButton();
			menuControl.waitCurrentButton();
			menuControl.addStartButton();
		}
	}
}
