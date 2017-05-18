package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class MyButton extends Image
{
    private float x;
    private float y;
    private float width;
    private float height;
    private TextureRegion textureRegion;


    public MyButton(float x, float y, float width, float height, TextureRegion textureRegion)
    {
        super(textureRegion);
        this.setSize(width, height);
        this.setPosition(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean pointerIsIn(OrthographicCamera camera)
    {
        Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f));

        return pos.x > x && pos.x < x + width && pos.y > y && pos.y < y + height;
    }

    public void setTextureRegion(TextureRegion textureRegion)
    {
        this.setDrawable(new SpriteDrawable(new Sprite(textureRegion)));
    }
}
