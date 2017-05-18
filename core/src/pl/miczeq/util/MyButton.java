package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class MyButton
{
    private float x;
    private float y;
    private float width;
    private float height;
    private TextureRegion textureRegion;

    public MyButton(float x, float y, float width, float height, TextureRegion textureRegion)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureRegion = textureRegion;
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(textureRegion, x, y, width, height);
        batch.end();
    }

    public boolean pointerIsIn(OrthographicCamera camera)
    {
        Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f));

        return pos.x > x && pos.x < x + width && pos.y > y && pos.y < y + height;
    }

    public void setTextureRegion(TextureRegion textureRegion)
    {
        this.textureRegion = textureRegion;
    }
}
