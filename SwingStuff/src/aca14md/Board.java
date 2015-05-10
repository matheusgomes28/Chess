package aca14md;
/*
 * Board.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Board.java 
*
* Class to represent a chess board
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/


public class Board {

  private static final int BOARD_SIZE=8;
  private static final int EMPTY=0;

  // each board has an array of Piece objects, which stores the chess pieces at each location
  // on the board
  private Piece[][] data;

  public Board () {
    data = new Piece[BOARD_SIZE][BOARD_SIZE];
    for (int i=0; i<BOARD_SIZE; i++)
      for (int j=0; j<BOARD_SIZE; j++) {
         data[i][j] = null;
      }
  }

  // mrthod returns true if a particular location is occupied
  public boolean occupied(int i, int j) {
    return (data[i][j]!=null);
  }

  // method returns true if a partcular location is off the board
  public boolean outOfRange(int i, int j) {
    return (i<0) || (i>=BOARD_SIZE)
           || (j<0) || (j>=BOARD_SIZE);
  }

  // method to remove a piece from a particular location
  public void remove(int i, int j) {
    data[i][j] = null;
  }

  // method to place a piece at a particular location
  public void setPosition(int i, int j, Piece p) {
    data[i][j] = p;
  }

  // method to return the chess piece at a particular location
  public Piece getPiece(int x, int y) {
    return data[x][y];
  }

  // method to return the array of chess pieces on the board
  public Piece[][] getData() {
    return data;
  }

}
