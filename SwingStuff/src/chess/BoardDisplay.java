package chess;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardDisplay extends JFrame{
	
	// Settings of the grid
	private final int GRID_SIZE = 8;
	
	public BoardDisplay(int height, int width){
		
		// Basic settings of the window
		Container contentPane = getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(8, 8));
		setResizable(false);
		setSize(500, 500);
		
		// Create the panel holder for each cell
		JPanel[][] cellHolder = new JPanel[GRID_SIZE][GRID_SIZE];
		
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				
				// Add a panel to the array
				cellHolder[i][j] = new JPanel();
				
				// Change colour for this panel
				if((i+j) % 2 == 1){
					cellHolder[i][j].setBackground(Color.GRAY);
				}
			
				add(cellHolder[i][j]); //Add each cell to the window
			}
		}
		
		
		
		try{
			Image pawnPic = ImageIO.read(new File(UsefulCode.));
			pawnPic = pawnPic.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(pawnPic));
			cellHolder[1][1].add(picLabel);
		}
		catch(IOException e){
			System.out.println("Error Opening file");
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args){
		BoardDisplay window = new BoardDisplay(1,1);
		window.setVisible(true);
	}
}
