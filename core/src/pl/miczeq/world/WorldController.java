package pl.miczeq.world;

import pl.miczeq.object.AbstractClass;
import pl.miczeq.object.AbstractGameObject;
import pl.miczeq.object.Mage;
import pl.miczeq.util.Constants;
import pl.miczeq.util.RoomType;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class WorldController
{
    private Constants.ClassType classType;

    private AbstractClass player;
    private Room room;

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

        room = RoomType.getRoom(0.0f, 0.0f, RoomType.Type.FULL);
    }

    public void update(float delta)
    {
        player.update(delta);

        AbstractGameObject.collideWithObjects(player, room.getWalls());
    }

    public AbstractClass getPlayer()
    {
        return player;
    }

    public Room getRoom()
    {
        return room;
    }
}
