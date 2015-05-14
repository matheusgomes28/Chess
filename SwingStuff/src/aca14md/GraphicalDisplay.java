package aca14md;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	// JPanel to hold the chess board
	private JPanel  board = new JPanel();
	
	// JPanel to hold the menu button, etc..
	UserMenu menu;
	
	
	
	// Get methods needed
	public UserMenu getMenu(){return menu;}
	public JPanel getBoard(){return board;} // MIGHT NOT NEED IT
	
	
	
	/**
	 * This is the class for a graphical interface
	 * used to display the pieces in a window.
	 * 
	 * @param height The height of the window
	 * @param width The width of the window
	 */
	public GraphicalDisplay(int width, int height){
		
		
		// Boiler code to set window settings
		Dimension size = new Dimension(width, height);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setSize(size);
		contentPane.setPreferredSize(size);
		
		// Board Settings
		Dimension boardSize  = new Dimension(height, height);
		board.setLayout(new GridLayout(8, 8));
		board.setSize(boardSize);
		System.out.println("Board size");
		System.out.println(board.getSize());
		
		// Menu settings
		menu = new UserMenu((int) (0.3*size.width), size.height);
		
		
		/* Adding each component with constraints */
		contentPane.add(menu, BorderLayout.LINE_END);
		contentPane.add(board, BorderLayout.LINE_START);
		
		System.out.println("Menu size");
		System.out.println(menu.getSize());

		
		
		// Nested loop to set each cell
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				
				// Add a panel to the cell
				cellHolder[i][j] = new JPanel();
				
				// Change colour of this panel
				if((i+j) % 2 == 1)
					cellHolder[i][j].setBackground(new Color(130, 82, 1));
				else
					cellHolder[i][j].setBackground(new Color(255, 189, 78));
				
				// Add panel to window				
				board.add(cellHolder[i][j]);
			}
		}
		
		System.out.println("Board size");
		System.out.println(board.getSize());
		
		setResizable(false);
		pack();
	}
	
	
	/** COMMENT HERE
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
	
	
	// Get methods needed
	public JPanel[][] getCellHolder(){return cellHolder;}
	
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
}
