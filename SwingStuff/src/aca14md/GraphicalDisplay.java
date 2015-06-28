package aca14md;

/*
 * This class serves as the window
 * class for the game. It wraps 
 * menu and board GUI objects and their
 * adapters in order to display them to
 * the user.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 14/05/2015
 */
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

	
	// BoardGui and board adapter references needed
	private BoardGUI  board;
	private BoardGUIAdapter boardControl;
	
	// UserMenu and menu adapter references needed
	private UserMenu menu;
	private MenuAdapter menuControl;
	
	
	
	// Get methods needed
	public UserMenu getMenu(){return menu;}
	public MenuAdapter getMenuControl(){return menuControl;}
	public BoardGUI getBoard(){return board;}
	public BoardGUIAdapter getBoardControl(){return boardControl;}
	
	
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
		board = new BoardGUI(boardSize, 8);
		boardControl = new BoardGUIAdapter(board);
		
		// Menu settings
		menu = new UserMenu((int) (0.3*size.width), size.height);
		menuControl = new MenuAdapter(menu);
		
		
		/* Adding each component with constraints */
		contentPane.add(menu, BorderLayout.LINE_END);
		contentPane.add(board, BorderLayout.LINE_START);
		
		setResizable(false);
		pack();
	}
	
	/**
	 * This method uses a board adapter class to
	 * show the pieces on the GUI chess board.
	 * @param data The two dimensional array 
	 * representing the pieces on board
	 */
	public void showPiecesOnBoard(Piece[][] data){
		boardControl.updateBoard(data);
	}
	
	
	/**
	 * This method revalidates and refreshes all
	 * components of the window.
	 */
	public void updateWindow(){
		// Boiler code to redraw every component
		board.revalidate();
		board.repaint();
		menu.revalidate();
		menu.repaint();
	}
}
