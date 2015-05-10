package com.oracle.docs.start;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.util.Random;


public class ChangingContentPane {
	
	public static void createWindow(String name){
		
		// Crate JFrame and content pane
		JFrame frame = new JFrame(name);
		JPanel contentPane = new JPanel(new BorderLayout());
		
		// Setting the default content pane
		frame.setContentPane(contentPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create label and add to the pane
		JLabel label = new JLabel(name);
		contentPane.add(label);
		
		// Organizing layout nd srtting visible
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		// Create two windows
		createWindow("Hello world");
		createWindow("I Love Java");
	}

}
