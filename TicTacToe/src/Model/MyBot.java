/**
 * @author Jozsef Halasi
 * 13 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package Model;

import controller.TicTacToeController;

public class MyBot implements Runnable{

	public MacroBoard macro;
	public int NehezsegiFok = 4;
	
	int id=2;
	public volatile boolean myTurn=false;
	public String string="";
	TicTacToeController controller;

	public MyBot(int id, TicTacToeController controller, int nehezseg){
		this.id = id;
		this.controller = controller;
		this.NehezsegiFok = nehezseg;
	}
	public void run() {
		
		while (true) {
			
			if(myTurn==true){
				
				try {
	            String[] parts = string.split(" ");

	            switch(parts[0]) {
	                case "settings":
	                    // store game settings
	                    break;
	                case "update":
	                    // store game updates
	                    break;
	                case "action":
	                	// lepes
	                	MacroBoard uj;
	                	int lepes=0;
	                	int maximum=-10000;
	        			for ( int i=0; i<81; ++i){
	        				
	        				uj = new MacroBoard(macro);
	        				if(uj.getMicroBoard().getInt(i)==0 && uj.getMacroBoard()[i/9] == 0){
	        					// ha nics jó lépés
		        				//lepes = i;
	        					if(uj.set(i, 2)==false){
	        						maximum = 10000;
	        						lepes = i;
	        						System.out.println("egy");
	        						break;
	        						
	        					}else{
	        						int szamolo = miniMax(uj, -10000, 10000, 1, 4);
	        						if(maximum <= szamolo){
	        							maximum = szamolo;
	        							lepes = i;
	        						}
	        					}
	        				}	
	        			}
	                	Thread.sleep(100);
	                	controller.click(lepes);         	
	                    myTurn=false;
	                    break;
	                default:
	                    // error
	            	}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isMyTurn() {
		return myTurn;
	}
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	// miniMax algorithm with alpha beta pruning
	public int miniMax(MacroBoard m, int min, int max, int player, int melyseg){
		int bestvalue =0;
		// if leaf
		if(melyseg == 0){
			//	kiértékelés
			return m.kiertekels(2);
		}
		// if not leaf
		MacroBoard uj = new MacroBoard(m);
		melyseg--;
		// max player
		if(player == 2){
			bestvalue = min;
			for ( int i=0; i<81; ++i){
				uj = new MacroBoard(m);
				if(uj.getMicroBoard().getInt(i)==0 && uj.getMacroBoard()[i/9] == 0){
					if(uj.set(i, 2)==false){
						System.out.println("ketto");
						return 10000;
					}else{
						int seged = miniMax(uj, min, max, 1, melyseg);
						bestvalue = Math.max(seged, bestvalue);
						if(max <= bestvalue){
							break;		
						}
					}		
			}
		}
		// min player
		}else{
			bestvalue = max;		
			for ( int i=0; i<81; ++i){
				uj = new MacroBoard(m);	
				if(uj.getMicroBoard().getInt(i)==0 && uj.getMacroBoard()[i/9] == 0){
					if(uj.set(i, 1)==false){
						System.out.println("harom");
						return -10000;
					}else{
						int seged = miniMax(uj, min, max, 2, melyseg);
						bestvalue = Math.min(seged, bestvalue);
						if(bestvalue <= min){
							break;
						}
					}
				}
			}
		}
		return bestvalue;
	}
}
