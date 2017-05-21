package pl.miczeq.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.miczeq.object.*;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class WorldController
{
    private Constants.ClassType classType;

    private AbstractClass player;
    private Dungeon dungeon;

    public WorldController(Constants.ClassType classType)
    {
        this.classType = classType;

        init();
    }

    private void init()
    {
        switch (classType)
        {
            case MAGE:
            {
                player = new Mage(Constants.VIEWPORT_WIDTH / 2.0f, Constants.VIEWPORT_HEIGHT / 2.0f);
            }break;

            case KNIGHT:
            {
                player = new Knight(Constants.VIEWPORT_WIDTH / 2.0f, Constants.VIEWPORT_HEIGHT / 2.0f);
            }
        }

        dungeon = new Dungeon(player);
    }

    public void update(float delta)
    {
        player.update(delta);
        dungeon.update(delta);
    }

    public void updateCamera(OrthographicCamera camera)
    {
        dungeon.updateCamera(camera);
    }

    public AbstractClass getPlayer()
    {
        return player;
    }

    public Dungeon getDungeon()
    {
        return dungeon;
    }
}
