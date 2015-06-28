package aca14md;

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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		private PieceModel model;
		
		// Piece that owns the listener
		Piece piece;
		
		public PieceListener(PieceModel model, Piece piece){
			// Boiler code to initialise fields
			this.piece = piece;
			this.model = model;
		}
		
		
		/**
		 * This method uses the GUI to select
		 * and highlight the possible pieces
		 * of a piece on the board.
		 */
		public void mouseClicked(MouseEvent e){
			// COMMENT HERE MATE
			if(model.getSelectedPiece() != null){
				// Unselect previous moves if needed
				model.unselectMoves(model.getSelectedPiece());
				
				// Select or unselect this pieces moves
				if(model.getSelectedPiece() != piece){
					model.selectMoves(piece);
					model.setSelectedPiece(piece);
				}
				else // Piece was just unselected
					model.setSelectedPiece(null);
			}
			else{
				model.selectMoves(piece);
				model.setSelectedPiece(piece);
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
		
		// Model containing the piece data
		private PieceModel model;
		
		// X, Y position of cell
		int x, y;
		
		
		public CellListener(PieceModel model, Piece piece, int x, int y){
			// Boiler code to initialise fields
			this.model = model;
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
			model.setMoveString(""+piece.getX()+""+piece.getY()+""+x+""+(7-y)); 
			
			//Unselect and remove all listeners
			model.selectedPiece = null;
			model.unselectMoves(piece);
		}
	}
}
