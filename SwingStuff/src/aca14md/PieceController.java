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
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * LAst Edited: 14/05/2015
 */
public class PieceController {
	
	// Variables to hold the view and model
	private BoardGUI view;
	private PieceModel model;
	
	public PieceController(PieceModel model, BoardGUI view){
		// Settings the instance variables
		this.view = view;
		this.model = model;
	}
	
	/**
	 * This method will be used to add
	 * mouse listeners to every JLabel
	 * that represents this player's 
	 * pieces on the board
	 * @param myPieces The pieces to be added listeners
	 * to.
	 */
	public void addPieceListeners(Pieces myPieces){
		
		// Get the cell holder
		JPanel[][] cellHolder = view.getCellHolder();
		
				
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
					label.addMouseListener(new PieceListeners.PieceListener(model, piece));
			}
		}
	}
	
	/**
	 * This method will be used to add
	 * mouse listeners to every JLabel
	 * that represents this player's 
	 * pieces on the board
	 * @param myPieces the pieces object that holds
	 * a HumanPlayer's pieces (that were clickable).
	 */
	public void removePieceListeners(Pieces myPieces){
		
		// Get the JPanel cellHolder
		JPanel cellHolder[][] = view.getCellHolder();
		
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
	
	
	/**
	 * This method uses the model and the
	 * view to get a valid move string 
	 * from the player.
	 * @return a move string (containing positions)
	 */
	public String getMoveString(){
		
		// Add listeners to players pieces
		addPieceListeners(model.getPieces());
		
		// TRY WITH SEMAPORES
		while(model.getMoveString().length() < 4){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String input = model.getMoveString();
		model.setMoveString(""); // Reset move string
		
		if(model.getSelectedPiece() != null)
			model.unselectMoves(model.getSelectedPiece());
		
		
		// Remove listeners of piece so they can't be selected
		// by opponent
		removePieceListeners(model.getPieces());
		
		return input;
	}
	
}
