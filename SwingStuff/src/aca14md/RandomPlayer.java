package aca14md;
/*
 * This class represents a random AI
 * player in the chess game. It moves
 * by selecting a random available
 * move of the player.
 * 
 * Lecturer: Richard Clayton
 * Writtten By: Matheus Gomes
 * Last Edited: 02/05/2015
 */
import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player {

	public RandomPlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}

	/**
	 * This method makes a random move based 
	 * on the legal moves available.
	 * 
	 * @return true if the move was successfully 
	 * made.
	 */
	public boolean makeMove() {
		
		ArrayList<Move> allMoves = getLegalMoves();
		
		// Random number generator here
		Random generator = new Random();
		
		// Random move chosen
		Move randomMove = allMoves.get(generator.nextInt(allMoves.size()));
		
		makeMove(randomMove);		
		return true;
	}
	
	/**
	 * This method makes the move of a given
	 * move object. Useful for children classes
	 * @param m The move object to be made
	 */
	public void makeMove(Move m){
		// Get information from move
		int xTo = m.getXTo(),
			yTo = m.getYTo();
		
		
		// Check if move is going to take a piece
		if(m.isOccupied())
			getOpponent().deletePiece(getBoard().getPiece(xTo, yTo));
		
		// Carry out the move and append to movesMade
		m.carryOutMove(getBoard().getPiece(xTo, yTo));
		Chess.movesMade.add(m);
	}
}
