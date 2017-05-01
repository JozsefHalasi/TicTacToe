/**
 * @author Jozsef Halasi
 * 12 Mar 2016
 * Copyright (c) 2013 Jozsef Halasi. All rights reserved.
 */

package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class XOButton extends JButton{
	
	/**
	 * Buttons extending JButtons
	 */
	private static final long serialVersionUID = -1737454758956821127L;
	ImageIcon z = new ImageIcon(this.getClass().getResource("/resources/empty.png"));
	byte value = 0;
	public int id;
	
	public XOButton(String s){
		this.setIcon(z);
		this.setBorder(null);
		this.setBorderPainted( false );
		this.setFocusPainted( false );
		this.setContentAreaFilled(false);
	}
}
