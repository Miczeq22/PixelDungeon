package pl.miczeq.world;

import pl.miczeq.object.AbstractClass;
import pl.miczeq.object.Mage;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class WorldController
{
    private Constants.ClassType classType;

    private AbstractClass player;

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
        }
    }

    public void update(float delta)
    {
        player.update(delta);
    }

    public AbstractClass getPlayer()
    {
        return player;
    }
}
