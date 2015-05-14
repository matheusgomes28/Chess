package aca14md;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

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
	
	public void setMessage(String message){
		
		// getting label that shows message in menu
		JLabel messageBox = menu.getMessageLabel();
		messageBox.setText(message);
	}
	
	
	/**
	 * This method just uses a semaphore
	 * to wait for the start button
	 * to be clicked in the menu.
	 */
	public void waitStartButton(){
		
		// Semaphore using to wait for action performed
		Semaphore semaphore = new Semaphore(0);
		
		// Add listener to button that releases semaphore
		menu.getStartButton().addActionListener(new ActionListener(){

			// Just used to release semaphore
			public void actionPerformed(ActionEvent e) {
				semaphore.release();
			}});
		
		// Handles exception
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			System.out.println("Concurrency error, report:");
			e1.printStackTrace();
		}
	}
}
