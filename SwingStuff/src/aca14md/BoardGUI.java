package aca14md;



/*
 * This class is used to create
 * new chess boards. It was created
 * mainly for the purpose of making 
 * the board more independent from
 * the GraphicalDisplay.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 14/05/2015
 */

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class BoardGUI extends JPanel {
	
	// JPanel containing cells and pieces
	private JPanel[][] cellHolder;
	private int GRID_SIZE;
	
	// Get methods needed
	public JPanel[][] getCellHolder(){return cellHolder;}
	public int getGridSize(){return GRID_SIZE;}
	
	public BoardGUI(Dimension boardSize, int size){
		
		// Set the grid size
		GRID_SIZE = size;
		
		// Initialise the cellHolder
		cellHolder = new JPanel[GRID_SIZE][GRID_SIZE];
		
		// Board Settings
		setLayout(new GridLayout(8, 8));
		setSize(boardSize);
		
		
		// Nested loop to set each cell
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				
				// Add a panel to the cell
				cellHolder[i][j] = new JPanel();
				
				// Change colour of this panel and borders
				// so no boder resizing later on
				Color cellColor;
				if((i+j) % 2 == 1){
					cellColor = new Color(130, 82, 1);
					cellHolder[i][j].setBackground(cellColor);
				}
				else{
					cellColor = new Color(255, 189, 78);
					cellHolder[i][j].setBackground(cellColor);
				}
				cellHolder[i][j].setBorder(BorderFactory.createLineBorder(cellColor));
				
				// Add panel to board				
				add(cellHolder[i][j]);
			}
		}
	}
	
}
