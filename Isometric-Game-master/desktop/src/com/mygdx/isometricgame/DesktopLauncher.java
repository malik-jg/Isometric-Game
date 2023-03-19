package com.mygdx.isometricgame;



import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class DesktopLauncher {
	
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(120);
		config.setWindowedMode(GameScreen.WIDTH,GameScreen.HEIGHT);
		config.setTitle("Isometric Game");
		new Lwjgl3Application(new IsometricGame(), config);
		
	}
}
