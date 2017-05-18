package pl.miczeq.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class Animator
{
    private Texture animationTexture;
    private final int frameCols;
    private final float animationSpeed;
    private float stateTime;

    private Animation<TextureRegion> animation;

    public Animator(Texture animationTexture, int frameCols, float animationSpeed)
    {
        this.animationTexture = animationTexture;
        this.frameCols = frameCols;
        this.animationSpeed = animationSpeed;
        init();
    }

    private void init()
    {
        stateTime = 0.0f;

        TextureRegion[][] tmp = TextureRegion.split(animationTexture, animationTexture.getWidth() / frameCols, animationTexture.getHeight());

        TextureRegion[] animationFrames = new TextureRegion[frameCols];
        int index = 0;

        for(int i = 0; i < frameCols; i++)
        {
            animationFrames[index++] = tmp[0][i];
        }

        animation = new Animation<TextureRegion>(animationSpeed, animationFrames);
    }

    public void setStateTime(float stateTime)
    {
        this.stateTime = stateTime;
    }

    public TextureRegion getCurrentFrame()
    {
        return animation.getKeyFrame(stateTime, true);
    }
}
