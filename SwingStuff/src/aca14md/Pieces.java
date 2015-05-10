package aca14md;
import java.util.*;

/*
 * Pieces.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Pieces.java 
*
* Class to keep and manage a collection of chess pieces, which are stored in an ArrayList
* Also used to place the pieces on the chess board at the start of a game
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public class Pieces {
  
  private static final int MAX_PIECES = 16;

  /* White pieces will be placed on the top two rows, 
     black on the the last two rows.
     Can switch display depending on what colour
     the player chooses
  */

  private ArrayList<Piece> data;
  private int numData;
  private Board board;
  private int colour;

  public Pieces (Board b, int c) {
    int j;
    Piece p;
    board = b;
    colour = c;
    data = new ArrayList<Piece>();
    
    // add 8 pawns
    for (int i=0; i<8; i++) {
      if (c==Player.WHITE) j=1; else j=6;
      p = new Pawn(i,j,c,board); 
      data.add(p);
      b.setPosition(i,j,p);
    }

    // add 2 knights
    if (c==Player.WHITE) { 
      p = new Knight(1,0,c,board);
      data.add(p);
      b.setPosition(1,0, p);
      p = new Knight(6,0,c,board);
      data.add(p);
      b.setPosition(6,0, p);
    }
    else {
      p = new Knight(1,7,c,board);
      data.add(p);
      b.setPosition(1,7, p);
      p = new Knight(6,7,c,board);
      data.add(p);
      b.setPosition(6,7, p);
    }

    // add 2 bishops
    if (c==Player.WHITE) { 
      p = new Bishop(2,0,c,board);
      data.add(p);
      b.setPosition(2,0, p);
      p = new Bishop(5,0,c,board);
      data.add(p);
      b.setPosition(5,0, p);
    }
    else {
      p = new Bishop(2,7,c,board);
      data.add(p);
      b.setPosition(2,7, p);
      p = new Bishop(5,7,c,board);
      data.add(p);
      b.setPosition(5,7, p);
    }

    // add 2 rooks
    if (c==Player.WHITE) { 
      p = new Rook(0,0,c,board);
      data.add(p);
      b.setPosition(0,0, p);
      p = new Rook(7,0,c,board);
      data.add(p);
      b.setPosition(7,0, p);
    }
    else {
      p = new Rook(0,7,c,board);
      data.add(p);
      b.setPosition(0,7, p);
      p = new Rook(7,7,c,board);
      data.add(p);
      b.setPosition(7,7, p);
    }

    // add 1 queen
    if (c==Player.WHITE) { 
      p = new Queen(3,0,c,board);
      data.add(p);
      b.setPosition(3,0, p);
    }
    else {
      p = new Queen(3,7,c,board);
      data.add(p);
      b.setPosition(3,7, p);
    }

    // and finally 1 King
    if (c==Player.WHITE) { 
      p = new King(4,0,c,board);
      data.add(p);
      b.setPosition(4,0, p);
    }
    else {
      p = new King(4,7,c,board);
      data.add(p);
      b.setPosition(4,7, p);
    }

    numData=10;
  }

  public int getNumPieces() {
    return data.size();
  }

  public Piece getPiece(int i) {
    return (Piece)data.get(i);
  }

  public void delete(Piece p) {
    boolean removed = data.remove(p);
    if (!removed) System.out.println("error");
  }

  public String toString () {
    String s = "";
    for (int i=0; i<data.size(); i++) {
      s = s+(Piece)data.get(i);
    }
    return s;
  }

}
