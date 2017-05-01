/**
 * @author Jozsef Halasi
 * 12 Mar 2016
 * Copyright (c) 2016 Jozsef Halasi. All rights reserved.
 */

package controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.MacroBoard;
import Model.MyBot;
import view.*;

public class TicTacToeController {


	public MacroBoard macro;
	public int currentPlayer;
	public MyBot bot;
	TicTacToeGUI vc;
	
	public void startDesktop(){
		
		macro = new MacroBoard();
		currentPlayer=1;
		vc = new TicTacToeGUI(this);
		// GUI elindítása
		vc.startGUI();
		startGame();
	}
	
	public void startGame(){
		
		bot = new MyBot(2, this, 4);
		Thread botThread = new Thread(bot);
		botThread.start();
		
		while(true){
			try {
				if(currentPlayer==2){
					bot.macro = macro;
					bot.string="action";
					
				}
				else{
					
				}
				Thread.sleep(350);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	// player clicks
	public void click(XOButton b){
		if(currentPlayer==1 && macro.getMacroBoard()[b.id/9]== 0){
			if(macro.set(b.id, currentPlayer)){
				int nyert = macro.checkWin();
				if(nyert != 0){
					vege(nyert);
				}
				else{
					bot.myTurn = true;
					currentPlayer = 2;	
				}

			}
		}
	}
		
	// MyBot move
	public void click(int mezo){
		if(currentPlayer==2){
			if(macro.set(mezo, 2)){
				int nyert = macro.checkWin();
				if(nyert != 0){
					vege(nyert);
				}
				else{
					bot.myTurn = false;
					currentPlayer = 1;
				}

			}
		}

	}
	// kiiratas
	public void vege(int ki){
		String[] options = {"OK"};
		ImageIcon x = new ImageIcon(this.getClass().getResource("/resources/x.png"));
		ImageIcon o = new ImageIcon(this.getClass().getResource("/resources/o.png"));
		int selectedOption=0;
		if(ki == 2){
			selectedOption = JOptionPane.showOptionDialog(vc.getWindow(), "Vesztettél", "Vége", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, x, options, "OK");
		}
		else if(ki == 1){
			selectedOption = JOptionPane.showOptionDialog(vc.getWindow(), "Nyertél", "Vége", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, o, options, "OK");
		}
		else{
			selectedOption = JOptionPane.showOptionDialog(vc.getWindow(), "Döntetlen", "Vége", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, o, options, "OK");
		}
		if(selectedOption==0){
			System.exit(0);
		}
	}
	

}