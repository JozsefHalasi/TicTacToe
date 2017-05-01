/**
 * @author Jozsef Halasi
 * 12 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package view;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToeMenuBar extends JMenuBar implements ActionListener{

	private TicTacToeGUI ticTacToeGUI;
	
	private static final long serialVersionUID = -2714226332088688810L;

	
	public TicTacToeMenuBar(TicTacToeGUI ticTacToeGUI){
		super();
		this.ticTacToeGUI = ticTacToeGUI;
		createMenuPoint("File","New Game");
		
	}
	private void createMenuPoint(String name, String... subnames){
		JMenu menu = new JMenu(name);
		this.add(menu);
		for(String subname : subnames){
			JMenuItem menuItem = new JMenuItem(subname);
			menu.add(menuItem);
			menuItem.addActionListener(this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
