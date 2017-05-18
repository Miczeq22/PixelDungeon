package pl.miczeq.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class WorldRenderer
{
    private WorldController worldController;

    public WorldRenderer(WorldController worldController)
    {
        this.worldController = worldController;
    }

    public void render(SpriteBatch batch)
    {
        worldController.getRoom().draw(batch);
        worldController.getPlayer().draw(batch);
    }
}
