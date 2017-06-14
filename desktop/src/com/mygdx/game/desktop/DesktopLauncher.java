package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TokenSoccer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Token Soccer";
		config.height = 480;
		config.width = 640;
		new LwjglApplication(new TokenSoccer(), config);
		//new LwjglApplication(new Menu(new TokenSoccer()), config);
	}
}
