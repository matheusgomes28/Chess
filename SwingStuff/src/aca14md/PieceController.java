package aca14md;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class is meant to be the 
 * controller class for each piece
 * on the graphical board.  It
 * passes data between the
 * HumanPlayer and GraphicalDisplay
 * classes, as well as helping to
 * make a move using the GUI.
 */
public class PieceController {
	
	// The move string acquired after a move
	private String moveStr;
	
	private GraphicalDisplay view;
	private HumanPlayer model;
	/**
	 * This method will be used to add
	 * mouse listeners to every JLabel
	 * that represents this player's 
	 * pieces on the board
	 */
	public void addPieceListeners(){
		
		// Get the cell holder
		JPanel[][] cellHolder = view.getCellHolder();
		
		// Get all player's pieces
		Pieces myPieces = model.getPieces();
				
		// Loop to add listeners to every piece on board
		for(int i = 0; i < myPieces.getNumPieces(); i++){
			
			// Current piece and position
			Piece piece = myPieces.getPiece(i);
			
			// Only selects if it has moves
			if(piece.availableMoves() != null){
				int x = piece.getX(),
						y = 7 - piece.getY();
					
					// JLabel representing piece
					JLabel label = (JLabel) cellHolder[y][x].getComponent(0);
					
					// Add the listener class from PieceListeners class - read class
					label.addMouseListener(new PieceListeners.PieceListener(view, piece));
			}
		}
	}
	
	
	public void removePieceListeners(){
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
