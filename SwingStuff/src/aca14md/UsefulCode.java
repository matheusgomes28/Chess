package aca14md;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.util.ArrayList;


public class UsefulCode {
	
	
	/**
	 * This method will get an user input
	 * from the console.
	 * @return the string entered in the console.
	 */
	public static String getConsoleInput(){
		
		// Try code for any io exceptions
		try{
			
			// Creating the stream and the console reader
			InputStreamReader stream = new InputStreamReader(System.in);
			BufferedReader console = new BufferedReader(stream);
			
			// Get the user input
			String input = console.readLine();
				
			return input;
		}
		catch(IOException e){
			
			// Print error info
			System.out.println("Input Reader Error");
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * This method will validate the input given by
	 * the user to check if the position is valid.
	 * @param s the input as a string
	 * @return true if position given is valid
	 */
	public static boolean validInput(String s){
		
		// Format the input given
		s = s.toLowerCase().replaceAll("\\s","");
					
		
		// checks if the length is valid
		if(s.length() != 4)
			return false;
		else{			
			
			// Get position in the board
			int x = s.charAt(0) - 'a';
			int y = s.charAt(1) - '0';
			int xTo = s.charAt(2) - 'a';
			int yTo = s.charAt(3) - '0';
			
			
			// Check the range
			if(inRange(x, 0, 7) && inRange(y, 0, 7) &&
			   inRange(xTo, 0, 7) && inRange(yTo, 0, 7))
				return true;
			
			else return false; 
		}
	}
	
	
	/**
	 * This method will check whether a character is 
	 * inside a given range. 
	 * 
	 * @param c the character given as the input
	 * @param lower the lower bound of the range
	 * @param higher the upper bound of the range
	 * @return true if character is in between the 
	 * upper and lower bounds.
	 */
	public static boolean inRange(int c, int lower, int higher){
		
		// Checking character range
		if(lower <= c && c <= higher)
			return true;
		else return false;
		
	}
	
	
	/**
	 * This method will check whether or not a
	 * pieces object contains a certain piece.
	 * 
	 * @param pieces the pieces object.
	 * @param p the piece to be checked in 
	 * pieces.
	 * @return true if the piece was found
	 */
	public static boolean piecesContains(Pieces pieces, Piece p){
		
		// loop tp go through all pieces in pieces
		for(int i = 0; i < pieces.getNumPieces(); i++){
			if(pieces.getPiece(i).equals(p)) return true;
		}
		
		// Return false if not found.
		return false;
	}
	
	
	/**
	 * This method will check whether or not the
	 * game is over by checking if each pieces
	 * object contains a king.
	 * @param p1 the first piece object
	 * @param p2 the second piece object in the game
	 * @return true if either pieces do not contain
	 * king.
	 */
	public static boolean isGameOver(Pieces p1, Pieces p2){
		
		// Get last pieces in both pieces objs
		Piece lastPieceOne = p1.getPiece(p1.getNumPieces() - 1),
			  lastPieceTwo = p2.getPiece(p2.getNumPieces() - 1);
		
		// Get character representations of both pieces
		char lastNameOne = Character.toLowerCase(lastPieceOne.getChar()),
			 lastNameTwo = Character.toLowerCase(lastPieceTwo.getChar());
		
		// If characters are different, then both are not kings
		return lastNameOne != lastNameTwo;
		
	}
	
	/**
	 * DO NOT MARK - WILL BE USEFUL LATER ON 
	 * 
	 * This method will check whether or not
	 * the opponent is in a check position. Still
	 * a beta version of check, will be used later
	 * on in the game to implement a full 
	 * check/checkmate detection. Now it is only
	 * used to display when there's a check.
	 * 
	 * @param p the piece that has just been
	 * moved.
	 * @param opponent the opponent player.
	 * @return true if p has a move to the 
	 * opponents king, false otherwise.
	 */
	public static boolean isCheck(Piece p, Player opponent){		
		
		// List of available moves
		ArrayList<Move> moves = p.availableMoves();
		
		// Opponents king
		Piece king = opponent.getPieces().getPiece(
				      opponent.getPieces().getNumPieces() -1);
		
		// The expected move if there's a check situation
		Move checkMove = new Move(p, p.getX(), p.getY(),
								  king.getX(), king.getY(), true);
		
		// finally checks if piece contains check move
		if(moves.contains(checkMove)) return true;
		else return false;
	}
	
	
	/**
	 * This method simply gets the location of the piece
	 * icon based on the piece name and colour. *Make
	 * sure you have all files in the right location.
	 * 
	 * @param p the piece object.
	 * @return string with relative location.
	 */
	public static String getImageLocation(Piece p){
		
		switch(p.getChar()){
		case 'p':
			return "images/white_pawn.png";
		case 'r':
			return "images/white_rook.png";
		case 'n':
			return "images/white_knight.png";
		case 'b':
			return "images/white_bishop.png";
		case 'q':
			return "images/white_queen.png";
		case 'k':
			return "images/white_king.png";
		case 'P':
			return "images/black_pawn.png";
		case 'R':
			return "images/black_rook.png";
		case 'N':
			return "images/black_knight.png";
		case 'B':
			return "images/black_bishop.png";
		case 'Q':
			return "images/black_queen.png";
		case 'K':
			return "images/black_king.png";
		default:
				return null;
		}
	}
}
