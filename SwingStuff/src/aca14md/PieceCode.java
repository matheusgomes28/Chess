package aca14md;
import java.util.*;

/*
 * PieceCode.java  	1.0 26/01/2015
 *
 * Copyright (c) University of Sheffield 2015
 */
 

/**
* PieceCode.java
*
* Provides static methods and variables to manage codes for
* different pieces on the chess board, and to return characters
* for display on the console.
*
* @version 1.0 26 January 2015
*
* @author Richard Clayton  (r.h.clayton@sheffield.ac.uk), Steve Maddock (s.c.maddock@sheffield.ac.uk)
*/

public final class PieceCode {
    
  // static variables for colours and pieces   
  public static final int BLACK = 0;
  public static final int WHITE = 1;

  public static final int PAWN = 1;
  public static final int KNIGHT = 2;
  public static final int BISHOP = 3;
  public static final int ROOK = 4;
  public static final int QUEEN = 5;
  public static final int KING = 6;

  public static final char PAWNWHITE = 'p';
  public static final char KNIGHTWHITE = 'n';
  public static final char BISHOPWHITE = 'b';
  public static final char ROOKWHITE = 'r';
  public static final char QUEENWHITE = 'q';
  public static final char KINGWHITE = 'k';
  public static final char PAWNBLACK = 'P';
  public static final char KNIGHTBLACK = 'N';
  public static final char BISHOPBLACK = 'B';
  public static final char ROOKBLACK = 'R';
  public static final char QUEENBLACK = 'Q';
  public static final char KINGBLACK = 'K';

  // method to return the symbol of a piece, given its numercal code and colour
  public static char intToChar(int i, int colour) {
    char data;
    switch (i) {
      case PAWN: if (colour==WHITE) data = 'p'; else data='P';
      break;
      case KNIGHT: if (colour==WHITE) data = 'n'; else data = 'N';
      break;
      case BISHOP: if (colour==WHITE) data = 'b'; else data = 'B';
      break;
      case ROOK: if (colour==WHITE) data = 'r'; else data = 'R';
      break;
      case QUEEN: if (colour==WHITE) data = 'q'; else data = 'Q';
      break;
      case KING: if (colour==WHITE) data = 'k'; else data = 'K';
      break;
      default: data = '.';
    }
    return data;
  }

  // method to return the numerical code of a piece, given its symbol    
  public static int charToInt(char ch) {
    int i;
    switch (ch) {
      case 'P': case 'p': i = PAWN;
      break;
      case 'N': case 'n': i = KNIGHT;
      break;
      case 'B': case 'b': i = BISHOP;
      break;
      case 'R': case 'r': i = ROOK;
      break;
      case 'Q': case 'q': i = QUEEN;
      break;
      case 'K': case 'k': i = KING;
      break;
      default: i = 0;
    }
    return i;
  }

}
