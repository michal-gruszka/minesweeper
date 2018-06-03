package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MinesweeperGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MinesweeperGame.WIDTH;
		config.height = MinesweeperGame.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MinesweeperGame(), config);
	}
}
