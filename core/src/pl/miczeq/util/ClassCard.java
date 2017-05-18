package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class ClassCard extends Image
{
    private final float x;
    private float y;
    private final float width;
    private final float height;

    private TextureRegion cardTexture;
    private TextureRegion infoTexture;

    private final Constants.ClassType classType;

    private Image infoImage;

    public ClassCard(float x, Constants.ClassType classType, TextureRegion cardTexture, TextureRegion infoTexture)
    {
        super(cardTexture);

        this.x = x;
        this.cardTexture = cardTexture;
        this.infoTexture = infoTexture;

        this.classType = classType;

        y = 500.0f;
        width = Constants.CLASS_CARD_WIDTH;
        height = Constants.CLASS_CARD_HEIGHT;

        this.setPosition(x, y);
        this.setSize(width, height);

        infoImage = new Image(infoTexture);
        infoImage.setPosition(-Constants.STAGE_WIDTH, 80.0f);
        infoImage.setSize(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT - 80.0f);
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

    public void setY(float y)
    {
        this.y = y;
    }

    public Image getInfoImage()
    {
        return infoImage;
    }
}
