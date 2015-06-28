package aca14md;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/*
 * This class is supposed to represent
 * the model object for the pieces when
 * making a move using the GUI. Proper
 * MCV was not implemented so this class
 * might not actually be limited to model
 * actions. Data in this case is the
 * pieces of a player and the cell holder
 * of JPanels that can change state.
 * 
 * Lecturer: Rihard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 12/05/2015
 */
public class PieceModel {
	
	// Store the pieces data
	private Pieces pieces;
	
	// Store cellHolder of piece panels
	JPanel[][] cellHolder;
	
	// Move string for making a move
	private String moveStr = "";
	
	// Variable for currently selected piece
	Piece selectedPiece = null;
	
	public PieceModel(Pieces pieces, JPanel[][] cellHolder){
		// Just initialising instance variables
		this.pieces = pieces;
		this.cellHolder = cellHolder;
	}
	
	// Get methods needed
	public Pieces getPieces(){return pieces;}
	public String getMoveString(){return moveStr;}
	public Piece getSelectedPiece(){return selectedPiece;}
	
	// Set methods needed
	public void setMoveString(String str){moveStr = str;}
	public void setSelectedPiece(Piece p){selectedPiece = p;}
	
	
	/**
	 * This method changes the state of the cells.
	 * It selects/highlights the moves of a 
	 * certain piece on the cellHolder and adds
	 * listeners to possible moves.
	 * @param p The piece to be selected.
	 * @param listener The desired listener to be 
	 * added by the controller.
	 */
	public void selectMoves(Piece p){
		
		// Change state of move string
		moveStr = "";
		
		// Get available moves of a piece
		ArrayList<Move> moves = p.availableMoves();
		
		// Loop to highlight pieces
		for(Move move : moves){
			// Positions to hightlight
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Add listener that changes the move string
			cellHolder[y][x].addMouseListener(new PieceListeners.CellListener(this, p, x, y));
			
			
			// Change colour to green
			cellHolder[y][x].setBackground(new Color(127, 95, 39));
			cellHolder[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
			cellHolder[y][x].revalidate();
			cellHolder[y][x].repaint();
		}
	}
	
	
	/**
	 * This method changes the state of a piece
	 * to unselected. It changes background
	 * colours of piece panels back to normal.
	 * @param p
	 */
	public void unselectMoves(Piece p){
		
		// Get available moves of a piece
		ArrayList<Move> moves = p.availableMoves();
		
		// Loop to highlight pieces
		if(moves != null) for(Move move : moves){
			// Positions to highlight
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Change colour to normal
			// Change colour of this panel
			Color cellColor;
			if((x+y) % 2 == 1){
				cellColor = new Color(130, 82, 1);
				cellHolder[y][x].setBackground(cellColor);
			}
			else{
				cellColor = new Color(255, 189, 78);
				cellHolder[y][x].setBackground(cellColor);
			}
			
			// Removing border and repaint
			cellHolder[y][x].setBorder(BorderFactory.createLineBorder(cellColor));
			cellHolder[y][x].revalidate();
			cellHolder[y][x].repaint();
			
			// Clear listeners that might have been added.
			clearMoveListeners(p);
		}
	}
	
	/**
	 * This method clears the move listeners 
	 * of the piece passed. Needed for 
	 * changing state of piece to unselected.
	 * @param piece The piece containing moves 
	 * that was previously selected.
	 */
	public void clearMoveListeners(Piece piece){
		
		for(Move move : piece.availableMoves()){
			
			// Get position to remove listeners
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Boiler code to remove all mouse listeners in a component
			MouseListener[] listeners = cellHolder[y][x].getMouseListeners();
			for(MouseListener listener : listeners)
				cellHolder[y][x].removeMouseListener(listener);
			
		}
	}
	
	
}
