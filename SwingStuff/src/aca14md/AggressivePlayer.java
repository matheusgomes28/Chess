package aca14md;
/*
 * This class represents an aggressive player
 * in the game. It inherits from random player
 * because it also makes random moves when
 * no piece can be taken.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 02/05/2015
 */
import java.util.ArrayList;

public class AggressivePlayer extends RandomPlayer {

	public AggressivePlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	/**
	 * Makes a move by selecting the move
	 * containing the highest ranked 
	 * piece. If no piece can be taken
	 * then a random move is done
	 */
	public boolean makeMove(){
		
		// Get all possible moves
		ArrayList<Move> allMoves = getLegalMoves();
		
		// No moves available;
		if(allMoves == null) return false;
		
		// Variable to store the best possible move
		Move bestMove = null;
	
		for(Move move : allMoves){
			
			if(move.isOccupied()){
				
				// Could be first best move
				if(bestMove == null) bestMove = move;
				else{
					// Get best move's destination
					int xTo = bestMove.getXTo(),
						yTo = bestMove.getYTo();
					
					// Get best piece that will be taken
					Piece bestPiece = getBoard().getPiece(xTo, yTo);
					
					// Get current piece taken
					Piece currentPiece = getBoard().getPiece(move.getXTo(), move.getYTo());
					
					if(PieceCode.charToInt(currentPiece.getChar()) > PieceCode.charToInt(bestPiece.getChar()))
						bestMove = move;
				}
	
			}
		}
		
		if(bestMove != null) makeMove(bestMove);
		else super.makeMove();
		
		return true;
	}

}
