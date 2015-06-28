package aca14md;
/*
 * This class represents a human player
 * in our chess game. Here, it uses
 * a control to select a move from the Gui.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 14/05/2015
 */

import java.util.ArrayList;

public class HumanPlayer extends Player {
	
	private BoardGUI view;
	private PieceModel model;
	private PieceController controller;
	
	public HumanPlayer(String n, Pieces p, Board b, Player o){
		super(n, p, b, o);
		view = Chess.gui.getBoard();
		model = new PieceModel(getPieces(), view.getCellHolder());
		controller = new PieceController(model, view);
	}

	/**
	 * This method will get input from user
	 * and make a move if information given is valid.
	 * 
	 * @return true if movement was made.
	 */	
	public boolean makeMove(){
		
		// Use controller to get move string
		String moveString = controller.getMoveString();
		
		// Get position information from move string
		int xFrom = Integer.parseInt(moveString.substring(0, 1)),
			yFrom = Integer.parseInt(moveString.substring(1, 2)),
			xTo = Integer.parseInt(moveString.substring(2, 3)),
			yTo = Integer.parseInt(moveString.substring(3, 4));
		
		// Create move object
		Move m = null;
		
		// Piece to be moved
		Piece piece = getBoard().getPiece(xFrom, yFrom);
		
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
			return false;
		}
	}
}
