package aca14md;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import layout.TableLayout;

import javax.swing.*;

public class UserInterface extends JFrame {
	
	
	public UserInterface(int height){
		
		// Boiler code for settings
		Dimension size = new Dimension((int) (10.0/7)*height, height);
		setSize(size);
		setResizable(false);
		
	
	}
	
	public static void main(String[] args){
		
		UserInterface ui = new UserInterface(500);
	}
}
