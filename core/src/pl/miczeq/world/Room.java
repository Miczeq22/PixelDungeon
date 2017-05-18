package pl.miczeq.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.miczeq.object.AbstractGameObject;
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

    private List<AbstractGameObject> walls;

    public Room(float x, float y)
    {
        this.x = x;
        this.y = y;
        width = Constants.VIEWPORT_WIDTH;
        height = Constants.WORLD_HEIGHT;

        walls = new ArrayList<AbstractGameObject>();

        walls.add(Wall.getVerticalWall(0.0f, 0.0f));
        walls.addAll(Wall.getHorizontalOpenWall(0.0f, 0.0f));
        walls.addAll(Wall.getVerticalOpenWall(Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH, 0.0f));
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(AssetsManager.instance.room.room, x, y, width, height);
        batch.end();
    }

    public void drawDebug(ShapeRenderer sr)
    {
        for(AbstractGameObject wall : walls)
        {
            wall.drawDebug(sr, Color.RED);
        }
    }

    public void setWalls(List<AbstractGameObject> walls)
    {
        this.walls = walls;
    }

    public List<AbstractGameObject> getWalls()
    {
        return walls;
    }
}
