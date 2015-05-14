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
		
		// Show stuff on board
		display.showPiecesOnBoard(board.getData());
		gui.setVisible(true);
		gui.showPiecesOnBoard(board.getData());
		
		// Create menu adapter to get menu settings
		MenuAdapter menu = new MenuAdapter(gui.getMenu());
		menu.waitStartButton();
		
		// Both players
		Player playerOne = menu.getPlayerOne(white, board);
		Player playerTwo = menu.getPlayerTwo(black, board);
		
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
			
			
			/* TESTING FOR STALE-MATE USING NULL POINTERS */
			try{
				// bool to check valid move
				boolean validMove = false;
				while(!validMove){
					menu.setMessage(player+"'s turn. ");
					
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
		
		// Gameover therefore show winner/draw
		if(staleMate)
			menu.setMessage("Stale Mate! It's a draw.");
		else
			menu.setMessage("Congratulations!!! "+player+" has won the game.");
	}
}
