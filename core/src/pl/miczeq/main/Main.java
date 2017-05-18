package pl.miczeq.main;

import com.badlogic.gdx.Game;
import pl.miczeq.screen.LoadingScreen;

public class Main extends Game
{
    @Override
    public void create()
    {
        this.setScreen(new LoadingScreen(this));
    }
}
