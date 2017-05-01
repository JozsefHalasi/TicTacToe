/**
 * @author Jozsef Halasi
 * 13 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package Model;

public class Field {
	private int[] array;
	
	
	// konstruktor
	public Field(){
		array = new int[81];
		
		for(int i=0; i<81; ++i){
			array[i]=0;
		}
	}
	// copy konstruktor
	public Field(Field f){
		this.array = new int[81];
		for(int i=0; i<81; ++i){
			this.array[i]=f.array[i];
		}
	}
	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}
	
	public void setInt(int player, int ertek){
		this.array[ertek]=player;
	}
	
	public int getInt(int mezo){
		return array[mezo];
	}

	// microboard kiértékelése
	public int microKiertekeles(int mezo, int player){
		mezo = mezo*9;
		int ertek=0;
		for(int i=0; i<9; ++i){
			if(array[mezo+i]==player){
				if(i==0 || i==2 || i==6 || i==8){
					ertek+=3;
				}else if(i==1 || i==3 || i==5 || i==7){
					ertek+=2;
				}else{
					ertek+=4;
				}
			}else if(array[mezo+i]==0){

			}
			else{
				if(i==0 || i==2 || i==6 || i==8){
					ertek-=3;
				}else if(i==1 || i==3 || i==5 || i==7){
					ertek-=2;
				}else{
					ertek-=4;
				}
			}
		}
		return ertek;
	}
	// check if somebody win a tile
	public int checkWin(int a){
		// sorok
		if(array[a]*array[a+1]*array[a+2] == 1 || array[a]*array[a+1]*array[a+2] == 8){
			return array[a];
		}
		else if(array[a+3]*array[a+4]*array[a+5] == 1 || array[a+3]*array[a+4]*array[a+5] == 8){
			return array[a+3];
		}
		else if(array[a+6]*array[a+7]*array[a+8] == 1 || array[a+6]*array[a+7]*array[a+8] == 8){
			return array[a+6];
		}
		// oszlopok
		if(array[a]*array[a+3]*array[a+6] == 1 || array[a]*array[a+3]*array[a+6] == 8){
			return array[a];
		}
		else if(array[a+1]*array[a+4]*array[a+7] == 1 || array[a+1]*array[a+4]*array[a+7] == 8){
			return array[a+1];
		}
		else if(array[a+2]*array[a+5]*array[a+8] == 1 || array[a+2]*array[a+5]*array[a+8] == 8){
			return array[a+2];
		}
		// átlók
		if(array[a]*array[a+4]*array[a+8] == 1 || array[a]*array[a+4]*array[a+8] == 8){
			return array[a];
		}
		else if(array[a+2]*array[a+4]*array[a+6] == 1 || array[a+2]*array[a+4]*array[a+6] == 8){
			return array[a+2];
		}
		
		//nincs lépés
		for(int i=a; i<a+9; ++i){
			if(array[i]==0){
				// van
				return 0;
			}
		}
		//nincs
		return -1;
	}
}
