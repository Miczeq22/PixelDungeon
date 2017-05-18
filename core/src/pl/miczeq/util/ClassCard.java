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
public class ClassCard
{
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    private TextureRegion cardTexture;
    private TextureRegion infoTexture;

    private final Constants.ClassType classType;

    public ClassCard(float x, Constants.ClassType classType, TextureRegion cardTexture, TextureRegion infoTexture)
    {
        this.x = x;
        this.cardTexture = cardTexture;
        this.infoTexture = infoTexture;

        this.classType = classType;

        y = Constants.CLASS_CARD_Y;
        width = Constants.CLASS_CARD_WIDTH;
        height = Constants.CLASS_CARD_HEIGHT;
    }

    public void drawCard(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(cardTexture, x, y, width, height);
        batch.end();
    }

    public void drawInfo(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(infoTexture, 0.0f, 80.0f, Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT - 80.0f);
        batch.end();
    }

    public boolean pointerIsIn(OrthographicCamera camera)
    {
        Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0.0f));

        return pos.x > x && pos.x < x + width && pos.y > y && pos.y < y + height;
    }

    public Constants.ClassType getClassType()
    {
        return classType;
    }
}
