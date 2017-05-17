package pl.miczeq.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.miczeq.main.Main;

public class DesktopLauncher
{
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.title = "Pixel Dungeon";
		config.vSyncEnabled = true;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		new LwjglApplication(new Main(), config);
	}
}
