package pl.miczeq.object;

import pl.miczeq.util.Constants;

import java.util.ArrayList;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class Wall extends AbstractGameObject
{
    public Wall(float x, float y, float width, float height)
    {
        super(x, y, width, height);
    }

    public static Wall getVerticalWall(float x, float y)
    {
        return new Wall(x, y, Constants.WALL_WIDTH, Constants.WORLD_HEIGHT);
    }

    public static Wall getHorizontalWall(float x, float y)
    {
        return new Wall(x, y, Constants.VIEWPORT_WIDTH, Constants.WALL_WIDTH);
    }

    public static ArrayList<Wall> getHorizontalOpenWall(float x, float y)
    {
        ArrayList<Wall> walls = new ArrayList<Wall>();

        walls.add(new Wall(x, y, Constants.VIEWPORT_WIDTH / 2.0f - Constants.DOOR_WIDTH, Constants.WALL_WIDTH));
        walls.add(new Wall(x + Constants.VIEWPORT_WIDTH / 2.0f + Constants.DOOR_WIDTH, y,
                Constants.VIEWPORT_WIDTH / 2.0f - Constants.DOOR_WIDTH, Constants.WALL_WIDTH));

        return walls;
    }

    public static ArrayList<Wall> getVerticalOpenWall(float x, float y)
    {
        ArrayList<Wall> walls = new ArrayList<Wall>();

        walls.add(new Wall(x, y, Constants.WALL_WIDTH, Constants.WORLD_HEIGHT / 2.0f - Constants.DOOR_WIDTH));
        walls.add(new Wall(x, y + Constants.WORLD_HEIGHT / 2.0f + Constants.DOOR_WIDTH, Constants.WALL_WIDTH, Constants.WORLD_HEIGHT / 2.0f - Constants.DOOR_WIDTH));

        return walls;
    }
}
