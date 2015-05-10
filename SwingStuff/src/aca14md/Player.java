package aca14md;
import java.util.*;

/*
 * Player.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Player.java 
*
* Abstract class to represent a chess player
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public abstract class Player {

  public static final int BLACK = 0;
  public static final int WHITE = 1;

  private String name;
  private Pieces pieces;
  private Board board;
  private Player opponent;

  public Player (String n, Pieces p, Board b, Player o) {
    name = n;
    pieces = p;
    board = b;
    opponent = o;
  }

  public Board getBoard() {
    return board;
  }

  public Player getOpponent() {
    return opponent;
  }

  public void setOpponent(Player p) {
    opponent = p;
  }

  public Pieces getPieces() {
    return pieces;
  }

  public abstract boolean makeMove();
 
  public void deletePiece(Piece p) {
    pieces.delete(p);
  }
 

  public String toString() {
    return name;
  }
  
  	public ArrayList<Move> getLegalMoves(){
  		
  		// All moves found in all pieces
  		ArrayList<Move> allMoves = new ArrayList<>();
  		
  		// Array to get all moves form every piece
  		for(int i = 0; i < getPieces().getNumPieces(); i++){
  			
  			// Get current piece and its moves
  			Piece currentPiece = getPieces().getPiece(i);
  			ArrayList<Move> moves = currentPiece.availableMoves();
  			
  			// Loop to see whether player is in check after each move 			
  			if(moves != null) for(Move move : moves){
  				
  				// Opponent piece that would be taken by this move
  				Piece pTaken = getBoard().getPiece(move.getXTo(), move.getYTo());
  				
  				// Check if it's not in check after move
  				move.carryOutMove(pTaken);
  				if(!isCheck(pTaken)) // Ignore the piece taken in check****
  					allMoves.add(move);
  				move.undoMove();
  			}
  		}
  		
  		// return null if no moves
  		if(allMoves.isEmpty()) return null;
  		else return allMoves;
  	}

  	
  	public boolean isCheck(Piece taken){
  		
  		System.out.println(taken);
  		
  		// Get opponents available piece
  		Pieces oPieces = getOpponent().getPieces();
  		
  		// Get my king => last piece 
  		Piece king = getPieces().getPiece(getPieces().getNumPieces() - 1);
  		
  		for(int i = 0; i < oPieces.getNumPieces(); i++){
  			
  			// Current opponent piece
  			Piece oPiece = oPieces.getPiece(i);
  			
  			// Get moves for that piece
  			ArrayList<Move> moves = oPiece.availableMoves();
  			
  			if(moves != null && oPiece != taken) for(Move move : moves){
  				
  				if(move.isOccupied()){
  					
  					// Get the piece that would have been taken by move
  					Piece pieceTaken = getBoard().getPiece(move.getXTo(), move.getYTo());
  					
  					// Return true if piece would take my king
  					if(pieceTaken.equals(king)) return true;
  				}
  			}
  		}
  		return false;
  	}
  	
  	public boolean isMate(){
  		
  		// Get the available moves
  		ArrayList<Move> moves = getLegalMoves();
  		
  		// If no legal moves => is mate.
  		if(moves == null) return true;
  		else return false;
  	}

}
