package com.github.qianniancc.execute;

import javax.swing.UIManager;

import com.github.qianniancc.execute.dialog.Main;

public class Launch {
	
	public static void main(String[] args) throws Exception{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		Main.getInstance();
	}

}
