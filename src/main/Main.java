package main;

import config.Config;
import gui.Window;

public class Main {
	public static void main(String[] args) {
		
		
		Window gameMainGUI = new Window("REBORN");
		Thread gameThread = new Thread(gameMainGUI);
		gameThread.start();
		
	}
}
