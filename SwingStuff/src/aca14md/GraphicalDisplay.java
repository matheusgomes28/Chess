package aca14md;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicalDisplay extends JFrame implements Display {
	
	
	// Size of the JPanel grid
	private final int GRID_SIZE = 8;
	
	// CellHolder
	JPanel[][] cellHolder = new JPanel[GRID_SIZE][GRID_SIZE];
	
	// Piece currently selected
	Piece selectedPiece = null;
	
	// Move string when making a move using the GUI 
	private String moveStr = "";
	
	
	
	/**
	 * This is the class for a graphical interface
	 * used to display the pieces in a window.
	 * 
	 * @param height The height of the window
	 * @param width The width of the window
	 */
	public GraphicalDisplay(int height, int width){
		
		
		// Basic settings of the window
		Container contentPane = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(8, 8));
		
		// Set constant size - not resizable
		setResizable(false);
		setSize(width, height);
		
		
		// Nested loop to set each cell
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				
				// Add a pael to the cell
				cellHolder[i][j] = new JPanel();
				
				// Change colour of this panel
				if((i+j) % 2 == 1)
					cellHolder[i][j].setBackground(Color.GRAY);
				
				// Add panel to window				
				contentPane.add(cellHolder[i][j]);
			}
		}
	}
	
	
	
	public JPanel[][] getCellHolder(){
		return cellHolder;
	}
	
	public String getMoveString(){
		return moveStr;
	}
	
	public Piece getSelectedPiece(){
		return selectedPiece;
	}
	
	public void setSelectedPiece(Piece p){
		selectedPiece = p;
	}
	
	public void setMoveString(String s){
		moveStr = s;
	}
	
	/**
	 * This method will set the piece to a 
	 * certain cell.
	 * 
	 * @param i the x position of the cell
	 * @param j the y position of the cell
	 * @param p the piece obj to be added
	 */
	public void setPieceToCell(int i, int j, Piece p){
		
		// Handling inpyt.output exceptions
		try{
			// Open the image using the awt.Image class
			// and locate image path with getImageLocation
			File imageFile = new File(UsefulCode.getImageLocation(p));
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
	 * @param height The height of the window
	 * @param width The width of the window
	 */
	public void showPiecesOnBoard(Piece[][] data) {
		
		// Going through each cell. i = y, j = x
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				
				// Set piece if present, otherwise remove all components
				if(data[j][i] != null)
					setPieceToCell(7-i, j, data[j][i]);
				else
					cellHolder[7-i][j].removeAll();
			}
		}
		
		// Repaint screen		
		revalidate();
		repaint();
	}
	
	
	/**
	 * This method is an improvement on the
	 * core showPieceOnBoard method. It only 
	 * repaints the cells changed in the GUI
	 * instead of the whole screen.	
	 */
	public void updateMove(int xFrom, int yFrom, int xTo, int yTo){
		
		System.out.println(xFrom+","+(7-yFrom));
		
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
	
	
	public void selectMoves(Piece p){
		
		// Change state of move string
		moveStr = "";
		
		// Get available moves of a piece
		ArrayList<Move> moves = p.availableMoves();
		
		// Loop to highlight pieces
		for(Move move : moves){
			// Positions to hightlight
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Add listener that changes the move string
			cellHolder[y][x].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					
					// Update move String
					moveStr = ""+p.getX()+""+p.getY()+
							  ""+x+""+(7-y);
					
					//Unselect and remove all listeners
					unselectMoves(p);
				}
			});
			
			
			// Change colour to green
			cellHolder[y][x].setBackground(Color.GREEN);
			cellHolder[y][x].revalidate();
			cellHolder[y][x].repaint();
		}
	}
	
	
	public void unselectMoves(Piece p){
		
		// Get available moves of a piece
		ArrayList<Move> moves = p.availableMoves();
		
		// Loop to highlight pieces
		if(moves != null) for(Move move : moves){
			// Positions to highlight
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Change colour to green
			if((x+y) % 2 == 1) cellHolder[y][x].setBackground(Color.GRAY);
			else cellHolder[y][x].setBackground(getBackground());
			
			cellHolder[y][x].revalidate();
			cellHolder[y][x].repaint();
			
			// Clear listeners that might have been added.
			clearMoveListeners(p);
		}
	}
	
		
	public void clearMoveListeners(Piece piece){
		
		for(Move move : piece.availableMoves()){
			
			// Get position to remove listeners
			int x = move.getXTo(),
				y = 7-move.getYTo();
			
			// Boiler code to remove all mouse listeners in a component
			MouseListener[] listeners = cellHolder[y][x].getMouseListeners();
			for(MouseListener listener : listeners)
				cellHolder[y][x].removeMouseListener(listener);
			
		}
	}

}
