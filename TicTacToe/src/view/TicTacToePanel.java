/**
 * @author Jozsef Halasi
 * 12 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.TicTacToeController;

public class TicTacToePanel extends JPanel implements ActionListener, Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3867158525807817573L;
	private TicTacToeGUI ticTacToeGUI;
	private XOButton buttons[];
	TicTacToeController controller;
	ImageIcon blueLine = new ImageIcon(this.getClass().getResource("/resources/blueLine.png"));
	ImageIcon blueO = new ImageIcon(this.getClass().getResource("/resources/blueO.png"));
	ImageIcon blueRect = new ImageIcon(this.getClass().getResource("/resources/blueRect.png"));
	ImageIcon blueX = new ImageIcon(this.getClass().getResource("/resources/blueX.png"));
	ImageIcon empty = new ImageIcon(this.getClass().getResource("/resources/empty.png"));
	ImageIcon o = new ImageIcon(this.getClass().getResource("/resources/o.png"));
	ImageIcon redLine = new ImageIcon(this.getClass().getResource("/resources/redLine.png"));
	ImageIcon redO = new ImageIcon(this.getClass().getResource("/resources/redO.png"));
	ImageIcon redRect = new ImageIcon(this.getClass().getResource("/resources/redRect.png"));
	ImageIcon redX = new ImageIcon(this.getClass().getResource("/resources/redX.png"));
	ImageIcon x = new ImageIcon(this.getClass().getResource("/resources/x.png"));
	boolean onClick=true;
	
	public TicTacToePanel(TicTacToeGUI tictactoeGUI, TicTacToeController controller){
		super();
		this.controller = controller;
		this.ticTacToeGUI = ticTacToeGUI;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		panel.setBorder(new EmptyBorder(4,4,4,4));
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.GRAY);
		this.add(panel);
		
		buttons = new XOButton[81];
		JPanel p3x3[] = new JPanel[9];
		for(int i=0; i<9; ++i){
			p3x3[i] = new JPanel();
			p3x3[i].setBackground(Color.BLACK);
			p3x3[i].setForeground(Color.GRAY);
			p3x3[i].setLayout(new GridLayout(3,3));
			p3x3[i].setBorder(new EmptyBorder(4,4,4,4));
			panel.add(p3x3[i]);
			
		}
		for(int i=0; i<9; ++i){
			for(int j=0; j<9; ++j){	
				buttons[j+9*i]= new XOButton("O");
				buttons[j+9*i].id=j+9*i;
				buttons[j+9*i].setSize(4,4);
				buttons[j+9*i].setBorder(new EmptyBorder(2,2,2,2));
				buttons[j+9*i].addActionListener(this);
				buttons[j+9*i].setBackground(Color.BLACK);
				buttons[j+9*i].setForeground(Color.GRAY);
				p3x3[i].add(buttons[j+9*i]);		
			}		
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(onClick){
			onClick=false;
			for(XOButton button: buttons){
				if(e.getSource()==button && button.value == 0){
					controller.click(button);
				}
			}
			onClick=true;
			
		}	
	}
	// Update fields
	@Override
	public void run() {
		int a[];
		int m[];
		try{
			while(true){
				a = controller.macro.getMicroBoard().getArray();
				m = controller.macro.getMacroBoard();

				for(int i=0; i<81; ++i){
					if(m[i/9] == 2){
						if(a[i]==2){
							buttons[i].setIcon(blueX);
							buttons[i].value = 2;
						}else if(a[i]==1){
							buttons[i].setIcon(blueO);
							buttons[i].value = 1;
						}else{
							buttons[i].setIcon(blueRect);
							buttons[i].value = 0;
						}
					}else if(m[i/9] == 1){
						if(a[i]==2){
							buttons[i].setIcon(redX);
							buttons[i].value = 2;
						}else if(a[i]==1){
							buttons[i].setIcon(redO);
							buttons[i].value = 1;
						}else{
							buttons[i].setIcon(redRect);
							buttons[i].value = 0;
						}
					}else if(m[i/9] == 0){
						if(a[i]==2){
							buttons[i].setIcon(x);
							buttons[i].value = 2;
						}else if(a[i]==1){
							buttons[i].setIcon(o);
							buttons[i].value = 1;
						}else{
							if(controller.currentPlayer == 2){
								buttons[i].setIcon(blueLine);
								buttons[i].value = 0;
							}
							else{
								buttons[i].setIcon(redLine);
								buttons[i].value = 0;
							}
						}	
					}else if(m[i/9] == -1){
						if(a[i]==2){
							buttons[i].setIcon(x);
							buttons[i].value = 2;
						}else if(a[i]==1){
							buttons[i].setIcon(o);
							buttons[i].value = 1;
						}else{
							buttons[i].setIcon(empty);
							buttons[i].value = 0;
						}	
					}
				}
				Thread.sleep(100);
			}
		}catch(InterruptedException iex){}
		
	}
}
