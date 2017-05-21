package pl.miczeq.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.miczeq.object.AbstractGameObject;
import pl.miczeq.object.Door;
import pl.miczeq.object.Mob;
import pl.miczeq.object.Wall;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class Room
{
    private float x;
    private float y;
    private float width;
    private float height;

    private float centerX;
    private float centerY;

    private List<AbstractGameObject> walls;
    private List<Door> doors;

    private List<Mob> mobs;

    private boolean firstVisit;

    public Room(float x, float y)
    {
        this.x = x;
        this.y = y;

        init();
    }

    private void init()
    {
        width = Constants.VIEWPORT_WIDTH;
        height = Constants.WORLD_HEIGHT;

        centerX = x + width / 2.0f;
        centerY = y + Constants.VIEWPORT_HEIGHT / 2.0f;

        firstVisit = true;

        mobs = new ArrayList<Mob>();
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(AssetsManager.instance.room.room, x, y, width, height);
        batch.end();

        for(Door door : doors)
        {
            door.draw(batch);
        }
    }

    public void update()
    {
        for(int i = 0; i < mobs.size() - 1; i++)
        {
            for(int j = i + 1; j < mobs.size(); j++)
            {
                AbstractGameObject.collideWithObject(mobs.get(i), mobs.get(j));
            }
        }
    }

    public boolean targetIsIn(AbstractGameObject target)
    {
        return target.getX() > x && target.getX() < x + width && target.getY() > y && target.getY() < y + height;
    }

    public boolean targetInside(AbstractGameObject target)
    {
        return target.getX() > x + Constants.WALL_WIDTH && target.getX() < x + Constants.WALL_WIDTH + width - Constants.WALL_WIDTH &&
                target.getY() > y + Constants.WALL_WIDTH && target.getY() < y + Constants.WALL_WIDTH + height - Constants.WALL_WIDTH;
    }

    public void setWalls(List<AbstractGameObject> walls)
    {
        this.walls = walls;
    }

    public List<AbstractGameObject> getWalls()
    {
        return walls;
    }

    public List<Door> getDoors()
    {
        return doors;
    }

    public void setDoors(List<Door> doors)
    {
        this.doors = doors;
    }

    public float getCenterX()
    {
        return centerX;
    }

    public float getCenterY()
    {
        return centerY;
    }

    public boolean isFirstVisit()
    {
        return firstVisit;
    }

    public void setFirstVisit(boolean firstVisit)
    {
        this.firstVisit = firstVisit;
    }

    public boolean isRoomCleaned()
    {
        return mobs.size() <= 0;
    }

    public List<Mob> getMobs()
    {
        return mobs;
    }
}
