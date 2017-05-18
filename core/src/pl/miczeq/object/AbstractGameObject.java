package pl.miczeq.object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public abstract class AbstractGameObject
{
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected TextureRegion textureRegion;

    public AbstractGameObject(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(textureRegion, x, y, width, height);
        batch.end();
    }

    public void drawDebug(ShapeRenderer sr, Color color)
    {
        sr.setColor(color);
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(x, y, width, height);
        sr.end();
    }
}
