package aca14md;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HumanPlayer extends Player {
	
	private GraphicalDisplay view;
	private PieceModel model;
	private PieceController controller;
	
	public HumanPlayer(String n, Pieces p, Board b, Player o){
		super(n, p, b, o);
		view = Chess.gui;
		model = new PieceModel(getPieces(), view.getCellHolder());
		controller = new PieceController(model, view);
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
}
