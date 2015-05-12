package aca14md;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import layout.TableLayout;

import javax.swing.*;

public class UserInterface extends JFrame {
	
	
	public UserInterface(int height){
		
		// Boiler code for settings
		Dimension size = new Dimension((int) (height*4/7.0), height);
		Container contentPane = getContentPane();
		contentPane.setPreferredSize(size); // Size for packing
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		// Size for every part of the UI
		Dimension panelSize = new Dimension((int) (size.width*0.9), size.height/4);
		
		
		// Create the logo Test/Image
		JLabel logo = new JLabel("Chess Game");
		logo.setFont(new Font(logo.getFont().getName(), Font.BOLD, 32));
		logo.setAlignmentX(contentPane.CENTER_ALIGNMENT);
		logo.setForeground(new Color(255, 189, 78));
		logo.setSize(panelSize);
		contentPane.add(logo);
		
		// JPanel to hold each player's options
		JPanel playerOneOptions = new JPanel();
		JPanel playerTwoOptions = new JPanel();
		JPanel submitPanel = new JPanel();
		playerOneOptions.setLayout(new GridBagLayout());
		playerTwoOptions.setLayout(new GridBagLayout());
		submitPanel.setLayout(new GridBagLayout());
		playerOneOptions.setSize(panelSize);
		playerTwoOptions.setSize(panelSize);
		submitPanel.setSize(panelSize);
		
		System.out.println(playerOneOptions.getSize());
		System.out.println(playerTwoOptions.getSize());
		
		// Create constraints object for JPanel
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		// Create Components needed for player one JPanel
		JLabel titleOne = new JLabel("Player One (White):");
		titleOne.setAlignmentX(playerOneOptions.LEFT_ALIGNMENT);
		JComboBox<String> typeOne = new JComboBox<String>();
			typeOne.addItem("Human");
			typeOne.addItem("Random");
			typeOne.addItem("Aggressive");
		JTextField nameOne = new JTextField("PlayerOne");
		
		// Add these components with constraints
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(10,10,10,10);
		playerOneOptions.add(titleOne, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.6;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		playerOneOptions.add(typeOne, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		playerOneOptions.add(nameOne, constraints);
		
		
		
		// Create Components needed for player one JPanel
		JLabel titleTwo = new JLabel("Player Two (black):");
		JComboBox<String> typeTwo = new JComboBox<String>();
			typeTwo.addItem("Human");
			typeTwo.addItem("Random");
			typeTwo.addItem("Aggressive");
		JTextField nameTwo = new JTextField("PlayerTwo");
		
		// Add these components with constraints
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(10,10,10,10);
		playerOneOptions.add(titleTwo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.6;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		playerOneOptions.add(typeTwo, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weightx = 0.4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		playerOneOptions.add(nameTwo, constraints);
		
		
		// Finally add Start button
		JButton startButton = new JButton("Start Game!");
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;
		submitPanel.add(startButton, constraints);
		
		
		// Adding players options
		contentPane.add(playerOneOptions);
		contentPane.add(playerTwoOptions);
		contentPane.add(submitPanel);
		
		
		// Last settings - packing components
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		
		
		System.out.println(contentPane.getSize());
		
	
	}
	
	public static void main(String[] args){
		
		UserInterface ui = new UserInterface(500);
	}
}
