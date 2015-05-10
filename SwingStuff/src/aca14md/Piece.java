package aca14md;
import java.util.*;

/*
 * Piece.java  	1.1 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* Piece.java 
*
* Abstract class to represent chess pieces
*
* @version 1.1 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public abstract class Piece {

  // instance fields to store piece symbol, location, colour and board
  private char data;
  private int x,y;
  private int colour;
  private Board board;

  // constructor
  public Piece (int i, int ix, int iy, int c, Board b) {
    colour = c;
    data = PieceCode.intToChar(i, c);
    x = ix; 
    y = iy;
    board = b;
  }
  
  public boolean equals(Piece p) {
    return (data==p.data) && (x==p.x) && (y==p.y) 
           && (colour==p.colour);
  }

  // abstract method, to be implemented in subclasses
  public abstract ArrayList<Move> availableMoves();

  public char getChar(int i) {
    return PieceCode.intToChar(i, colour);
  }

  public char getChar() {
    return data;
  }

  public void setPosition(int i, int j) {
    x = i; 
    y = j;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getColour() {
    return colour;
  }

  public char getColourChar() {
    if (colour==PieceCode.WHITE) return 'w'; else return 'b';
  }

  public Board getBoard() {
    return board;
  }

  public String toString () {
    return ""+data;
  }

}
