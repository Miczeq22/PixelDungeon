package pl.miczeq.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import pl.miczeq.main.Main;

public class DesktopLauncher
{
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugLines = false;

	public static void main (String[] arg)
	{
		if(rebuildAtlas)
		{
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugLines;

			TexturePacker.process(settings, "/home/mikolaj/IdeaProjects/Pixel Dungeon/desktop/assets-raw/ui/",
					"/home/mikolaj/IdeaProjects/Pixel Dungeon/android/assets/", "ui.pack");
			TexturePacker.process(settings, "/home/mikolaj/IdeaProjects/Pixel Dungeon/desktop/assets-raw/classCards/",
					"/home/mikolaj/IdeaProjects/Pixel Dungeon/android/assets/classCards/", "cards.pack");

			TexturePacker.process(settings, "/home/mikolaj/IdeaProjects/Pixel Dungeon/desktop/assets-raw/rest/",
					"/home/mikolaj/IdeaProjects/Pixel Dungeon/android/assets/", "pixelDungeon.pack");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.title = "Pixel Dungeon";
		config.vSyncEnabled = true;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		config.forceExit = false;
		new LwjglApplication(new Main(), config);
	}
}
