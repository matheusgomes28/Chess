package aca14md;

/*
 * This is the adapter class of the
 * chess board GUI. It holds methods
 * that change the state of cells.
 * Mainly used to change the board
 * object and to make code more cohesive.
 * 
 * Lecturer: Reichard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 14/05/2015
 */

	
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUIAdapter {
	
	// Board contained within adapter
	private BoardGUI board;
	private JPanel[][] cellHolder;
	
	// Get methods needed
	public JPanel[][] getCellholder(){return cellHolder;}
	public BoardGUI getBoardGUI(){return board;}
	
	
	public BoardGUIAdapter(BoardGUI board){
		// Boiler code to set instance variables
		this.board = board;
		this.cellHolder = board.getCellHolder();
	}
	
	
	/**
	 * This method is an improvement on the
	 * core showPieceOnBoard method. It only 
	 * repaints the cells changed in the GUI
	 * instead of the whole screen.	
	 * @param xFrom The initial x position of the piece
	 * @param yFrom The initial y position of the piece
	 * @param xTo The final x position of the piece
	 * @param yTo The final position of the piece
	 */
	public void updateMove(int xFrom, int yFrom, int xTo, int yTo){
		
		// Get all components inside the cell
		Component label = cellHolder[7-yFrom][xFrom].getComponent(0);
		
		// Removing components of both cells changed
		cellHolder[7-yFrom][xFrom].removeAll();
		cellHolder[7-yTo][xTo].removeAll();
		
		// Add label of piece changed to new position
		cellHolder[7-yTo][xTo].add(label);
		
		// Repaints both cells
		cellHolder[7-yTo][xTo].revalidate();
		cellHolder[7-yTo][xTo].repaint();
		cellHolder[7-yFrom][xFrom].revalidate();
		cellHolder[7-yFrom][xFrom].repaint();
	}
	
	
	/**
	 * This method will set the piece to a 
	 * certain cell.
	 * 
	 * @param i the x position of the cell
	 * @param j the y position of the cell
	 * @param p the piece object to be added
	 */
	public void setPieceToCell(int i, int j, Piece p){
		
		// Handling inpyt.output exceptions
		try{
			// Open the image using the awt.Image class
			// and locate image path with getImageLocation
			File imageFile = new File(getImageLocation(p));
			Image picture = ImageIO.read(imageFile);
			
			
			// Scale down image with smooth scale algorithm
			picture = picture.getScaledInstance(50,
											    50, 
											    java.awt.Image.SCALE_SMOOTH);
			
			// Important - Image is held as an Icon in a Label 
			// to be added to the panel of cell
			JLabel picLabel = new JLabel(new ImageIcon(picture));		
			
			
			cellHolder[i][j].removeAll(); // As only 1 piece per cell
			cellHolder[i][j].add(picLabel);
		}
		catch(IOException e){
			System.out.println("Error opening image, make sure you have all images in images/");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This is the class for a graphical interface
	 * used to display the pieces in a window.
	 * 
	 * @param data The two dimensional array 
	 * representing the board data.
	 */
	public void updateBoard(Piece[][] data) {
		
		// Going through each cell. i = y, j = x
		for(int i = 0; i < board.getGridSize(); i++){
			for(int j = 0; j < board.getGridSize(); j++){
				
				// Set piece if present, otherwise remove all components
				if(data[j][i] != null)
					setPieceToCell(7-i, j, data[j][i]);
				else
					cellHolder[7-i][j].removeAll();
			}
		}
		
		// Repaint screen		
		board.revalidate();
		board.repaint();
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
