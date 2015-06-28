package aca14md;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * This class is serving as an adapter
 * to the menu class. It simply retrieves
 * data from a menu given into data that
 * the chess class needs. I've tried 
 * following the "adapter design" so that
 * I can split the responsibility and 
 * code into different classes.
 * 
 * Lecturer: Richard Clayton
 * Written By: Matheus Gomes
 * Last Edited: 13/05/2015
 */

public class MenuAdapter {
	
	// Needs the menu reference to get data
	UserMenu menu;
	
	public MenuAdapter(UserMenu menu){
		this.menu = menu;
	}
	
	/**
	 * This method will return a new
	 * object for the first player 
	 * depending on what the user has
	 * selected on the menu.
	 * @return A new Player object.
	 */
	public Player getPlayerOne(Pieces pieces, Board board){
		
		// Get player one type (and format it to compare)		
		String selected = menu.getPlayerOneType().toString();
		selected = selected.toLowerCase().trim();
		
		// Get name of player on the text field
		String name = menu.getPlayerOneName();

		// Return the player selected in the menu
		if(selected.equals("human"))
			return new HumanPlayer(name, pieces, board, null);
		else if(selected.equals("random"))
			return new RandomPlayer(name, pieces, board, null);
		else if(selected.equals("aggressive"))
			return new AggressivePlayer(name, pieces, board, null);
		else{
			System.out.println("PlayerOne selected is not valid");
			return null;
		}
		
	}

	
	/**
	 * This method will return a new
	 * object for the seconds player 
	 * depending on what the user has
	 * selected on the menu.
	 * @param piece The pieces object that will be
	 * used to create the player.
	 * @param board The board object that represents
	 * the game being played.
	 * @return A new Player object.
	 */
	public Player getPlayerTwo(Pieces pieces, Board board){
		
		// Get player one type (and format it to compare)		
		String selected = menu.getPlayerTwoType().toString();
		selected = selected.toLowerCase().trim();
		
		// Get name of player on the text field
		String name = menu.getPlayerTwoName();

		// Return the player selected in the menu
		if(selected.equals("human"))
			return new HumanPlayer(name, pieces, board, null);
		else if(selected.equals("random"))
			return new RandomPlayer(name, pieces, board, null);
		else if(selected.equals("aggressive"))
			return new AggressivePlayer(name, pieces, board, null);
		else{
			System.out.println("PlayerTwo selected is not valid");
			return null;
		}
		
	}
	
	/*
	 * This method will get the delay in miliseconds
	 * from the user menu.
	 */
	public int getDelay(){
		
		// get selected option and format string
		String input = menu.getDelayBox().getSelectedItem().toString();
		input = input.toLowerCase().trim();
		
		// Get int corresponding to input
		if(input.equals("none"))
			return 0;
		else if(input.equals("0.5 second"))
			return 500;
		else if(input.equals("1 second"))
			return 1000;
		else
			System.out.println("input error on delay");
		
		return 0;
	}
	
	/**
	 * This method changes the message on the
	 * menu.
	 * @param message The message to be changed
	 * to.
	 */
	public void setMessage(String message){
		
		// getting label that shows message in menu
		JLabel messageBox = menu.getMessageLabel();
		messageBox.setText(message);
	}
	
	
	/**
	 * This method just uses a semaphore
	 * to wait for the start/reset button
	 * to be clicked in the menu.
	 */
	public void waitCurrentButton(){
		
		// Semaphore using to wait for action performed
		Semaphore semaphore = new Semaphore(0);
		
		// Add listener to button that releases semaphore
		JButton button = menu.getCurrentButton();
		
		// Add listener that waits
		button.addActionListener(new ActionListener(){

			// Just used to release semaphore
			public void actionPerformed(ActionEvent e) {
				semaphore.release();
				menu.getButtonArea().removeAll();
			}});
		
		// Handles exception
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			System.out.println("Concurrency error, report:");
			e1.printStackTrace();
		}
	}
	
	/**
	 * This method just adds the reset and
	 * button to the menu.
	 */
	public void addResetButton(){
		// references needed
		JPanel area = menu.getButtonArea();
		area.removeAll();
		GridBagConstraints constraints = new GridBagConstraints();
		
		// The two buttons to be added
		JButton resetBoard = new JButton("Reset Board");
		
		// Adding button with constraints
		constraints.weightx = 1;
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		area.add(resetBoard, constraints);
		
		// Repaint area
		area.revalidate();
		area.repaint();
	}
	
	/*
	 * This method just adds the start button
	 * to the user menu.
	 */
	public void addStartButton(){
		
		// references needed
		JPanel area = menu.getButtonArea();
		area.removeAll();
		GridBagConstraints constraints = new GridBagConstraints();
		
		// The two buttons to be added
		JButton startButton = new JButton("Start Game");
		
		// Adding button with constraints
		constraints.weightx = 1;
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		area.add(startButton, constraints);
		
		// Repaint area
		area.revalidate();
		area.repaint();
	}
}
