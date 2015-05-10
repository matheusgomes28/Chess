package aca14md;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String n, Pieces p, Board b, Player o){
		super(n, p, b, o);
	}

	/**
	 * This method will get input from user
	 * and make a move if information given is valid.
	 * 
	 * @return true if movement was made.
	 */
	public boolean makeMove() {
		
		// Get user's input for the piece
		System.out.println("Please enter a valid move input:");
		String input = UsefulCode.getConsoleInput();
		input = input.replaceAll("\\s", "").toLowerCase();
		
		// Check for a valid input
		if(!UsefulCode.validInput(input)){
			System.out.println("Not a valid input");
			System.out.println();
			return false;
		}
		
		// Extract position from input
		int x = input.charAt(0) - 'a',
			y = input.charAt(1) - '0';
		
		// Check if user owns piece at location
		if(!getBoard().occupied(x, y) ||
		   !UsefulCode.piecesContains(getPieces(),
				   					  getBoard().getPiece(x, y))){
			System.out.println("Not a valid piece chosen.");
			System.out.println();
			return false;
		}
		
		// Get the piece at chosen position
		Piece chosenPiece = getBoard().getPiece(x, y);
		
		// Check if piece has moves
		if(chosenPiece.availableMoves() == null){
			System.out.println("Piece chosen has no available moves");
			System.out.println();
			return false;
		}
		
		
		// Get x,y for the movement
		int xTo = input.charAt(2) - 'a',
			yTo = input.charAt(3) - '0';
		
		// Create move object
		Move m;
		
		// Instantiate m with correct instances
		if(getBoard().occupied(xTo, yTo))
			m = new Move(chosenPiece, x, y, xTo, yTo, true);
		else
			m = new Move(chosenPiece, x, y, xTo, yTo, false);
		
		
		// Check if move is correct by checking
		if(chosenPiece.availableMoves().contains(m)){
		
			// Carry out the move
			if(m.equals(m)){
				
				// Delete peace from opponent
				if(m.isOccupied())
					getOpponent().deletePiece(getBoard().getPiece(xTo, yTo));
				
				m.carryOutMove(); // Set positions etc...
				Chess.movesMade.add(m); // Add move to list of moves made
				return true;
			}
		}
			
		// If loop went through then move was not made
		System.out.println("Wrong move given. Try again.");
		System.out.println();
		return false;
	}
	
	
	public boolean makeGUIMove(GraphicalDisplay gui){
		
		addListeners(Chess.gui);
		
		while(gui.getMoveString() == ""){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		removeListeners(gui);
		
		// VERY IMPORTNT TO RESET
		String moveString = gui.getMoveString();
		gui.setMoveString("");
		
		
		
		// Get position information from move string
		int xFrom = Integer.parseInt(moveString.substring(0, 1)),
			yFrom = Integer.parseInt(moveString.substring(1, 2)),
			xTo = Integer.parseInt(moveString.substring(2, 3)),
			yTo = Integer.parseInt(moveString.substring(3, 4));
		
		// Create move object
		Move m = null;
		
		// Piece to be moved
		Piece piece = getBoard().getPiece(xFrom, yFrom);
		System.out.println(piece);
		
		if(getBoard().occupied(xTo, yTo))
			m = new Move(piece, xFrom, yFrom, xTo, yTo, true);
		else
			m = new Move(piece, xFrom, yFrom, xTo, yTo, false);
		
		// Get all the legal moves of this player
		ArrayList<Move> allMoves = getLegalMoves();
		
		if(allMoves.contains(m)){
			// Delete peace from opponent
			if(m.isOccupied())
				getOpponent().deletePiece(getBoard().getPiece(xTo, yTo));
			
			m.carryOutMove(getBoard().getPiece(xTo, yTo)); // Set positions etc...
			Chess.movesMade.add(m); // Add move to list of moves made
			return true;
		}
		else{
			System.out.println("Move chosen is illegal. Can't get into check");
			return false;
		}
	}
	
	public void addListeners(GraphicalDisplay gui){
		
		// Get the cell holder
		JPanel[][] cellHolder = gui.getCellHolder();
		
		// Get all player's pieces
		Pieces myPieces = getPieces();
	
		
		
		// Loop to add listeners to every piece on board
		for(int i = 0; i < myPieces.getNumPieces(); i++){
			
			// Current piece and position
			Piece piece = myPieces.getPiece(i);
			
			if(piece.availableMoves() != null){
				int x = piece.getX(),
						y = 7 - piece.getY();
					
					// JLabel representing piece
					JLabel label = (JLabel) cellHolder[y][x].getComponent(0);
					
					label.addMouseListener(new MouseAdapter(){
						
						public void mouseClicked(MouseEvent e){
							
							if(gui.getSelectedPiece() != null){
								// Unselect previous moves if needed
								gui.unselectMoves(gui.getSelectedPiece());
								
								// Select or unselect this pieces moves
								if(gui.getSelectedPiece() != piece){
									gui.selectMoves(piece);
									gui.setSelectedPiece(piece);
								}
								else // Piece was just unselected
									gui.setSelectedPiece(null);
							}
							else{
								gui.selectMoves(piece);
								gui.setSelectedPiece(piece);
							}
							
						}
						
					});
			}
		}
	}
	
	
	public void removeListeners(GraphicalDisplay gui){
		
		// Get the JPanel cellHolder
		JPanel cellHolder[][] = gui.getCellHolder();
		
		// Get pieces that belong to player
		Pieces myPieces = getPieces();
		
		// Loop to remove all listeners of the pieces
		for(int i = 0; i < myPieces.getNumPieces(); i++){
			
			// Get current piece
			Piece piece = myPieces.getPiece(i);
			
			// Only has listeners if has moves
			if(piece.availableMoves() != null){
				
				// Get position to be removed
				int x = piece.getX(),
					y = 7 - piece.getY();
				
				// Get the only label representing piece
				JLabel label = (JLabel) cellHolder[y][x].getComponent(0);
				
				// Get mouse listeners to be removed
				MouseListener[] listeners = label.getMouseListeners();
				
				// Shows error if there's no mouse listener (expected to be 1);
				if(listeners.length == 0 ) System.out.println("erro, no listeners");
				else for(MouseListener listener : listeners)
					label.removeMouseListener(listener);
			}
		}
	}
}
