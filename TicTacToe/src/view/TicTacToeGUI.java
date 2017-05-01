/**
 * @author Jozsef Halasi
 * 12 Mar 2016
 * Copyright (c) 2016 Jozsef Halasi. All rights reserved.
 */

package view;

import javax.swing.*;

import controller.*;



public class TicTacToeGUI {

	private JFrame window;
	private TicTacToeController controller;
	
	public TicTacToeGUI(TicTacToeController controller){
		this.controller = controller;
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public void startGUI(){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
	
	private void createAndShowGUI(){
		String title = "Tic Tac Toe";
		
		window = new JFrame(title);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TicTacToeMenuBar ticTacToeMenuBar = new TicTacToeMenuBar(this);
		TicTacToePanel ticTacToePanel = new TicTacToePanel(this, controller);
		Thread t1 = new Thread(ticTacToePanel);
		t1.start();
		window.add(ticTacToePanel);
		
		window.setJMenuBar(ticTacToeMenuBar);
		
		window.setSize(600, 600);
		window.setResizable(false);
		window.setLocationRelativeTo(null); 
		window.pack();
		window.setVisible(true);
		
	}
}
