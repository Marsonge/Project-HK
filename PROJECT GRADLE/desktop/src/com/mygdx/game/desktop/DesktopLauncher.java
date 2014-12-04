package com.mygdx.game.desktop;

import static com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Project HK";
                config.width = 800;
                config.height = 480;
                config.addIcon("icon.png",FileType.Internal);
		new LwjglApplication(new Game(), config);
	}
}
