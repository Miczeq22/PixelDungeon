package pl.miczeq.util;

import pl.miczeq.object.AbstractGameObject;
import pl.miczeq.object.Door;
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
        HORIZONTAL,
        TOP,
        BOT,
        LEFT,
        RIGHT
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

    private static List<AbstractGameObject> verticalRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getVerticalWall(x, y));
        walls.add(Wall.getVerticalWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH , y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return  walls;
    }

    private static List<AbstractGameObject> horizontalRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getHorizontalWall(x, y));
        walls.add(Wall.getHorizontalWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));
        walls.addAll(Wall.getVerticalOpenWall(x, y));
        walls.addAll(Wall.getVerticalOpenWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, y));

        return walls;
    }

    private static List<AbstractGameObject> leftRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.addAll(Wall.getVerticalOpenWall(x, y));
        walls.add(Wall.getVerticalWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH  , y));
        walls.add(Wall.getHorizontalWall(x, y));
        walls.add(Wall.getHorizontalWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return walls;
    }

    private static List<AbstractGameObject> rightRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getVerticalWall(x, y));
        walls.addAll(Wall.getVerticalOpenWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, y));
        walls.add(Wall.getHorizontalWall(x, y));
        walls.add(Wall.getHorizontalWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return walls;
    }

    private static List<AbstractGameObject> topRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getVerticalWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, y));
        walls.add(Wall.getVerticalWall(x, y));
        walls.add(Wall.getHorizontalWall(x, y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return walls;
    }

    private static List<AbstractGameObject> botRoom(float x, float y)
    {
        List<AbstractGameObject> walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getVerticalWall(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, y));
        walls.add(Wall.getVerticalWall(x, y));
        walls.addAll(Wall.getHorizontalOpenWall(x, y));
        walls.add(Wall.getHorizontalWall(x, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH));

        return walls;
    }

    private static List<Door> fullDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getTopDoors(x, y));
        doors.add(Door.getBotDoors(x, y));
        doors.add(Door.getLeftDoors(x, y));
        doors.add(Door.getRightDoors(x, y));

        return doors;
    }

    private static List<Door> verticalDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getTopDoors(x, y));
        doors.add(Door.getBotDoors(x, y));

        return doors;
    }

    private static List<Door> horizontalDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getLeftDoors(x, y));
        doors.add(Door.getRightDoors(x, y));

        return doors;
    }

    private static List<Door> leftDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getLeftDoors(x, y));

        return doors;
    }

    private static List<Door> rightDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getRightDoors(x, y));

        return doors;
    }

    private static List<Door> topDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getTopDoors(x, y));

        return doors;
    }

    private static List<Door> botDoors(float x, float y)
    {
        List<Door> doors = new ArrayList<Door>();

        doors.add(Door.getBotDoors(x, y));

        return doors;
    }

    public static Room getRoom(float x, float y, Type type)
    {
        Room room = new Room(x, y);

        switch (type)
        {
            case FULL:
            {
                room.setWalls(fullRoom(x, y));
                room.setDoors(fullDoors(x, y));
            }break;

            case VERTICAL:
            {
                room.setWalls(verticalRoom(x, y));
                room.setDoors(verticalDoors(x, y));
            }break;

            case HORIZONTAL:
            {
                room.setWalls(horizontalRoom(x, y));
                room.setDoors(horizontalDoors(x, y));
            }break;

            case LEFT:
            {
                room.setWalls(leftRoom(x, y));
                room.setDoors(leftDoors(x, y));
            }break;

            case RIGHT:
            {
                room.setWalls(rightRoom(x, y));
                room.setDoors(rightDoors(x, y));
            }break;

            case TOP:
            {
                room.setWalls(topRoom(x, y));
                room.setDoors(topDoors(x, y));
            }break;

            case BOT:
            {
                room.setWalls(botRoom(x, y));
                room.setDoors(botDoors(x, y));
            }break;
        }

        return room;
    }
}
