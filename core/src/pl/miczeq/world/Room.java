package pl.miczeq.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

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

    public Room(float x, float y)
    {
        this.x = x;
        this.y = y;
        width = Constants.VIEWPORT_WIDTH;
        height = Constants.WORLD_HEIGHT;
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(AssetsManager.instance.room.room, x, y, width, height);
        batch.end();
    }
}
