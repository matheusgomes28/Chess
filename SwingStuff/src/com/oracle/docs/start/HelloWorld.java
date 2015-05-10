package com.oracle.docs.start;
import javax.swing.*;

public class HelloWorld {

	public static void main(String[] args) {

		// Creating frame
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Add the "Hello World" labels
		JLabel label = new JLabel("Hello World");
		frame.getContentPane().add(label);
		
		// Display the window
		frame.pack();
		frame.setVisible(true);
	}

}
