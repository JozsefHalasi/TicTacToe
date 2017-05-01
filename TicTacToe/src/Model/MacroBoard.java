/**
 * @author Jozsef Halasi
 * 13 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package Model;

public class MacroBoard {
	private int[] macroBoard;
	private Field microBoard;
	
	// MacroBoard constructor
	public MacroBoard(){
		this.setMicroBoard(new Field());
		this.setMacroBoard(new int[9]);
		for(int i=0; i<9; ++i){
			getMacroBoard()[i] = 0;
		}
	}
	
	// copy constructor
	public MacroBoard (MacroBoard m) {
		this.setMicroBoard(new Field(m.getMicroBoard()));
		this.setMacroBoard(new int[9]);
		for(int i=0; i<9; ++i){
			this.getMacroBoard()[i] = m.getMacroBoard()[i];
		}
	}
	
	// tábla kiértékelése a megadott játékosra
	public int kiertekels(int player){
		int sum=0;
		// bejárjuk a macroboardot
		if(checkWin()==2){
			return 10000;
		}else if(checkWin()==1){
			return -10000;
		}
		
		for(int i=0; i<9; ++i){
			switch(getMacroBoard()[i]){
			case -1:
				sum += getMicroBoard().microKiertekeles(i, player)+ 2*macroKiertekeles(i, player);
				break;
			case 0:
				sum += getMicroBoard().microKiertekeles(i, player)+ 2*macroKiertekeles(i, player);
				break;
			case 1:
				if(player == 1){
					sum+=500;
				}else{
					sum-=500;
				}
				break;
			case 2:
				if(player == 2){
					sum+=500;
				}else{
					sum-=500;
				}
				break;
			}
		}
		return sum;
	}
	
	public int macroKiertekeles(int mezo, int player){
		int sum=0;
		switch(mezo){
		case 0:
			sum=3;
			sum+=oszlop(0, player);
			sum+=sor(0, player);
			sum+=atlo(0, player);
			break;
		case 1:
			sum=2;
			sum+=oszlop(1, player);
			sum+=sor(0, player);
			break;
		case 2:
			sum=3;
			sum+=oszlop(2, player);
			sum+=sor(0, player);
			sum+=atlo(1, player);
			break;
		case 3:
			sum=2;
			sum+=oszlop(0, player);
			sum+=sor(1, player);
			break;
		case 4:
			sum=4;
			sum+=oszlop(1, player);
			sum+=sor(1, player);
			sum+=atlo(0, player);
			sum+=atlo(1, player);
			break;
		case 5:
			sum=2;
			sum+=oszlop(2, player);
			sum+=sor(1, player);
			break;
		case 6:
			sum=3;
			sum+=oszlop(0, player);
			sum+=sor(2, player);
			sum+=atlo(1, player);
			break;
		case 7:
			sum=2;
			sum+=oszlop(1, player);
			sum+=sor(2, player);
			break;
		case 8:
			sum=3;
			sum+=oszlop(2, player);
			sum+=sor(2, player);
			sum+=atlo(0, player);
			break;
		default:
			sum=0;
			break;
		}	
		return sum;
	}
	
	// oszlop vizsgálata
	public int oszlop(int oszlop, int player){
		int sum=0;
		for(int i=oszlop; i<9; i+=3){
			if(getMacroBoard()[i] == -1){
				
			}else{
				sum+=getMacroBoard()[i]*getMacroBoard()[i];
			}
		}

		switch(sum){
		case 8:	
			return 3;
		case 5:
			return -1;
		case 4:
			if(player == 2){
				return 1;
			}
			else{
				return -2;
			}
		case 2:
			return 3;
		case 1:
			if(player == 1){
				return 1;
			}
			else{
				return -2;
			}
		default:
			return 0;
		}
	}
	// sor vizsgálata
	public int sor(int sor, int player){
		int sum=0;
		for(int i=sor*3; i<sor*3+3; ++i){
			if(getMacroBoard()[i] == -1){
				
			}else{
				sum+=getMacroBoard()[i]*getMacroBoard()[i];
			}
		}

		switch(sum){
		case 8:	
			return 3;
		case 5:
			return -1;
		case 4:
			if(player == 2){
				return 1;
			}
			else{
				return -2;
			}
		case 2:
			return 3;
		case 1:
			if(player == 1){
				return 1;
			}
			else{
				return -2;
			}
		default:
			return 0;
		}
	}
	// átló vizsgálata
	public int atlo(int atlo, int player){
		int sum=0;
		if(getMacroBoard()[4] == -1){
			
		}else{
			sum+=getMacroBoard()[4]*getMacroBoard()[4];
		}
		
		if(atlo == 0){
			if(getMacroBoard()[0] == -1){
				
			}else{
				sum+=getMacroBoard()[0]*getMacroBoard()[0];
			}
			if(getMacroBoard()[8] == -1){
				
			}else{
				sum+=getMacroBoard()[8]*getMacroBoard()[8];
			}
		}else{
			if(getMacroBoard()[2] == -1){
				
			}else{
				sum+=getMacroBoard()[2]*getMacroBoard()[2];
			}
			if(getMacroBoard()[6] == -1){
				
			}else{
				sum+=getMacroBoard()[6]*getMacroBoard()[6];
			}
		}

		switch(sum){
		case 8:	
			return 3;
		case 5:
			return -1;
		case 4:
			if(player == 2){
				return 1;
			}
			else{
				return -2;
			}
		case 2:
			return 3;
		case 1:
			if(player == 1){
				return 1;
			}
			else{
				return -2;
			}
		default:
			return 0;
		}
	}
	
	
	public int value(int player){
		int ossz=0;
		for(int a: getMacroBoard()){
			if(a == player){
				if(a==1 || a==3 || a==5 || a==7){
					ossz+=2;
				}
				else if(a==0 || a==2 || a==6  || a==8){
					ossz+=3;
				}
				else{
					ossz+=4;
				}
			}
		}
		return ossz;
	}
	
	public int checkWin(){
		// sorok
		if((getMacroBoard()[0]== 1 && getMacroBoard()[1]==1 && getMacroBoard()[2] == 1) || getMacroBoard()[0]+getMacroBoard()[1]+getMacroBoard()[2] == 6){
			return getMacroBoard()[0];
		}
		else if((getMacroBoard()[3]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[5] == 1) || getMacroBoard()[3]+getMacroBoard()[4]+getMacroBoard()[5] == 6){
			return getMacroBoard()[3];
		}
		else if((getMacroBoard()[6]== 1 && getMacroBoard()[7]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[6]+getMacroBoard()[7]+getMacroBoard()[8] == 6){
			return getMacroBoard()[6];
		}
		// oszlopok
		if((getMacroBoard()[0]== 1 && getMacroBoard()[3]==1 && getMacroBoard()[6] == 1) || getMacroBoard()[0]+getMacroBoard()[3]+getMacroBoard()[6] == 6){
			return getMacroBoard()[0];		
		}
		else if((getMacroBoard()[1]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[7] == 1) || getMacroBoard()[1]+getMacroBoard()[4]+getMacroBoard()[7] == 6){
			return getMacroBoard()[1];		
		}
		else if((getMacroBoard()[2]== 1 && getMacroBoard()[5]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[2]+getMacroBoard()[5]+getMacroBoard()[8] == 6){
			return getMacroBoard()[2];
		}
		// átlók
		if((getMacroBoard()[0]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[0]+getMacroBoard()[4]+getMacroBoard()[8] == 6){
			return getMacroBoard()[0];
		}
		else if((getMacroBoard()[2]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[6] == 1) || getMacroBoard()[2]+getMacroBoard()[4]+getMacroBoard()[6] == 6){
			return getMacroBoard()[2];
		}
		// döntetlen

		for(int i=0; i<9; ++i){
			if(getMacroBoard()[i] == 0){
				for(int j=i*9; j<i*9+9; ++j){
					if(getMicroBoard().getInt(j) == 0){
						return 0;
					}
				}
			}
		}
		return -1;
	}
	
	
	public boolean set(int a, int player){


		int macro = a/9;
		if(getMacroBoard()[macro] != 0) return false;
		if(getMicroBoard().getInt(a) != 0) return false;
		getMicroBoard().setInt(player, a);
		
		
		if(getMicroBoard().checkWin(macro*9) == 1){
			getMacroBoard()[macro] = 1;
		}else if(getMicroBoard().checkWin(macro*9) == 2){
			getMacroBoard()[macro] = 2;
		}
		/*// check if WIN
		// sorok
		if((getMacroBoard()[0]== 1 && getMacroBoard()[1]==1 && getMacroBoard()[2] == 1) || getMacroBoard()[0]+getMacroBoard()[1]+getMacroBoard()[2] == 6){
			return false;
		}
		else if((getMacroBoard()[3]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[5] == 1) || getMacroBoard()[3]+getMacroBoard()[4]+getMacroBoard()[5] == 6){
			return false;
		}
		else if((getMacroBoard()[6]== 1 && getMacroBoard()[7]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[6]+getMacroBoard()[7]+getMacroBoard()[8] == 6){
			return false;
		}
		// oszlopok
		if((getMacroBoard()[0]== 1 && getMacroBoard()[3]==1 && getMacroBoard()[6] == 1) || getMacroBoard()[0]+getMacroBoard()[3]+getMacroBoard()[6] == 6){
			return false;		
		}
		else if((getMacroBoard()[1]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[7] == 1) || getMacroBoard()[1]+getMacroBoard()[4]+getMacroBoard()[7] == 6){
			return false;		
		}
		else if((getMacroBoard()[2]== 1 && getMacroBoard()[5]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[2]+getMacroBoard()[5]+getMacroBoard()[8] == 6){
			return false;
		}
		// átlók
		if((getMacroBoard()[0]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[8] == 1) || getMacroBoard()[0]+getMacroBoard()[4]+getMacroBoard()[8] == 6){
			return false;
		}
		else if((getMacroBoard()[2]== 1 && getMacroBoard()[4]==1 && getMacroBoard()[6] == 1) || getMacroBoard()[2]+getMacroBoard()[4]+getMacroBoard()[6] == 6){
			return false;
		}
*/
		if(getMacroBoard()[a%9] > 0){
			for(int i=0; i<9; ++i){
				if(getMacroBoard()[i] < 1){
					getMacroBoard()[i] = 0;
				}
			}
		}
		else{
			for(int i=0; i<9; ++i){
				if(getMacroBoard()[i] < 1){
					getMacroBoard()[i] = -1;
				}
			}
			getMacroBoard()[a%9] = 0;
		}
		return true;
	}

	public int[] getMacroBoard() {
		return macroBoard;
	}

	public void setMacroBoard(int[] macroBoard) {
		this.macroBoard = macroBoard;
	}

	public Field getMicroBoard() {
		return microBoard;
	}

	public void setMicroBoard(Field microBoard) {
		this.microBoard = microBoard;
	}
}
