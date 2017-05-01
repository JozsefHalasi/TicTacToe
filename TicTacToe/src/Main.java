import controller.*;

/**
 * @author Jozsef Halasi¶
 * 12 Mar 2016
 * Copyright (c) 2016 Jozsef Halasi. All rights reserved.
 */

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startApp();
	}

	private static void startApp(){
		TicTacToeController controller = new TicTacToeController();
		controller.startDesktop();
	}
}
