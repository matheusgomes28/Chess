package aca14md;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * This class was created to group up
 * all the listener code that goes into
 * elements of the GUI. The main purpose
 * of this class is to make the code more
 * readable.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Editted: 12/05/2015
 */
public class PieceListeners {
	
	// Hold the currently selected piece in the GUI
	Piece selectedPiece;
	
	/**
	 * This class holds the methods that
	 * are necessary for a label
	 * (representing a piece) on the
	 * GUI to move around properly.
	 * @author Matheus
	 *
	 */
	public static class PieceListener extends MouseAdapter {
		
		// Instances needed for the listener to work
		GraphicalDisplay gui;
		
		// Piece that owns the listener
		Piece piece;
		
		public PieceListener(GraphicalDisplay gui, Piece piece){
			// Boiler code to initialise fields
			this.piece = piece;
			this.gui = gui;
		}
		
		
		/**
		 * This method uses the GUI to select
		 * and highlight the possible pieces
		 * of a piece on the board.
		 */
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
	}
	
	
	/**
	 * This class holds the methods that
	 * are necessary for making a move using
	 * the GUI. It adds functionality to
	 * empty cells which are part of possible 
	 * moves.
	 * @author Matheus
	 *
	 */
	public static class CellListener extends MouseAdapter {

		// Piece that contains moves to be listened
		private Piece piece;
		
		// GUI containing the pieces
		private GraphicalDisplay gui;
		
		// X, Y position of cell
		int x, y;
		
		
		public CellListener(GraphicalDisplay gui, Piece piece, int x, int y){
			// Boiler code to initialise fields
			this.gui = gui;
			this.piece = piece;
			this.x = x;
			this.y = y;
		}
		
		
		/**
		 * This method will get positions
		 * from the cells when the mouse is
		 * clicked. It's used to make a 
		 * move using the GUI
		 */
		public void mouseClicked(MouseEvent e){
			
			// Update move String
			gui.setMoveString(""+piece.getX()+""+piece.getY()+""+x+""+(7-y)); 
			
			//Unselect and remove all listeners
			gui.selectedPiece = null;
			gui.unselectMoves(piece);
		}
	}
}
