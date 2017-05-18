package pl.miczeq.util;

import pl.miczeq.object.AbstractGameObject;
import pl.miczeq.object.Wall;
import pl.miczeq.world.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikolaj on 5/19/17.
 * Pixel Dungeon
 */
public class RoomType
{
    public enum Type
    {
        FULL,
        VERTICAL,
        HORIZONTAL
    }

    private static List<AbstractGameObject> fullRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();
        walls.addAll(Wall.getVerticalOpenWall(x, y));
        walls.addAll(Wall.getVerticalOpenWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return walls;
    }

    public static Room getRoom(float x, float y, Type type)
    {
        switch (type)
        {
            case FULL:
            {
                Room room = new Room(x, y);
                room.setWalls(fullRoom(x, y));
                return room;
            }

            case VERTICAL:
            {

            }
        }

        return null;
    }
}
