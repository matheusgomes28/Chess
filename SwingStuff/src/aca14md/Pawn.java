package aca14md;
import java.util.*;

/*
 * Pawn.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Pawn.java 
*
* Concrete class to represent a pawn
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/


public class Pawn extends Piece {

  public Pawn (int ix, int iy, int c, Board b) {
    super(PieceCode.PAWN, ix, iy, c, b);
  }
  
  /**
	 * This method will return an array
	 * with all the available moves.
	 * 
	 * @return null if no moves or an array
	 * list of moves otherwise.
	 */
  public ArrayList<Move> availableMoves() {
    if (getColour()==PieceCode.WHITE) return whitePawn();
    else return blackPawn();
  }

  // method to return Vector of legal moves for a white pawn
  private ArrayList<Move> whitePawn() {
    int x = getX();
    int y = getY();
      
    // return null if the pawn is at the edge of the board, or if the
    // next move takes it out of range
    if (y==7) return null;
    if (getBoard().outOfRange(x,y+1)) return null;
      
    // otherwise create a new vector to store legal moves
    ArrayList<Move> v = new ArrayList<Move>();
      
    // set up m to refer to a Move object  
    Move m = null;
    
    // First move can move up to 2 places up 
    if(getY() == 1 &&
       !getBoard().occupied(x, y + 1) &&
       !getBoard().occupied(x, y + 2)){
    	
    	m = new Move(this, x, y, x, y + 2, false);
    	v.add(m);
    }
    
    // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied  
    if (!getBoard().occupied(x,y+1)) {
      m = new Move(this, x,y,x,y+1,false);
      v.add(m);
    }
      
    // second legal move is to go from x,y to x+1,y+1 if x+1,y+1 is occupied 
    // by a black piece
    if (!getBoard().outOfRange(x+1, y+1)
       && getBoard().occupied(x+1, y+1)
       && (getBoard().getPiece(x+1, y+1).getColour()
           !=this.getColour())) {
       m = new Move(this, x,getY(),x+1,y+1,true);
       v.add(m);
    }
      
    // third legal move is to go from x,y to x-1,y+1 if x-1,y+1 is occupied 
    // by a black piece  
    if (!getBoard().outOfRange(x-1, y+1)
       && getBoard().occupied(x-1, y+1)
       && (getBoard().getPiece(x-1, y+1).getColour()
           !=this.getColour())) {
       m = new Move(this, x,y,x-1,y+1,true);
       v.add(m);
    }
      
    if (v.isEmpty()) return null;
    return v;
  }

  // method to return Vector of legal moves for a white pawn
  private ArrayList<Move> blackPawn() {
    int x = getX();
    int y = getY();
      
    // return null if the pawn is at the edge of the board, or if the
    // next move takes it out of range
    if (y==0) return null;
    if (getBoard().outOfRange(x,y-1)) return null;
      
    ArrayList<Move> v = new ArrayList<Move>();
    
    // set up m to refer to a Move object        
    Move m = null;
    
    // First move can move up to 2 places down - FIX IT
    if(getY() == 6 &&
       !getBoard().occupied(x, y - 1) &&
       !getBoard().occupied(x, y - 2)){
    	
    	m = new Move(this, x, y, x, y - 2, false);
    	v.add(m);
    }
    
    // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied  
    if (!getBoard().occupied(x,y-1)) {
      m = new Move(this, x,y,x,y-1,false);
      v.add(m);
    }
      
    // second legal move is to go from x,y to x+1,y+1 if x+1,y+1 is occupied 
    // by a white piece      
    if (!getBoard().outOfRange(x+1, y-1)
       && getBoard().occupied(x+1, y-1)
       && (getBoard().getPiece(x+1, y-1).getColour()
          !=this.getColour())) {
       m = new Move(this, x,y,x+1,y-1,true);
       v.add(m);
    }
      
    // third legal move is to go from x,y to x-1,y+1 if x-1,y+1 is occupied 
    // by a white piece       
    if (!getBoard().outOfRange(x-1, y-1)
       && getBoard().occupied(x-1, y-1)
       && (getBoard().getPiece(x-1, y-1).getColour()
          !=this.getColour())) {
       m = new Move(this, x,y,x-1,y-1,true);
       v.add(m);
    }
      
    if (v.isEmpty()) return null;
    return v;
  }

}
