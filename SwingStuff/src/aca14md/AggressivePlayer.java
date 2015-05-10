package aca14md;

import java.util.ArrayList;

public class AggressivePlayer extends RandomPlayer {

	public AggressivePlayer(String n, Pieces p, Board b, Player o) {
		super(n, p, b, o);
	}
	
	
	public boolean makeMove(){
		
		// Get all possible moves
		ArrayList<Move> allMoves = getLegalMoves();
		
		// No moes available;
		if(allMoves == null) return false;
		
		// Var to store the best possible move
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
