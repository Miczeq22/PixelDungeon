package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import pl.miczeq.util.Animator;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public abstract class AbstractClass extends AbstractGameObject
{
    protected float velX;
    protected float velY;
    protected float maxSpeed;
    protected float step;

    protected boolean moving;
    protected boolean attacking;

    protected Animator sideMovingAnimation;
    protected Animator frontMovingAnimation;
    protected Animator backMovingAnimation;

    private float stateTime;

    public enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    protected Direction direction;

    public AbstractClass(float x, float y)
    {
        super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        init();
    }

    protected void init()
    {
        velX = 0.0f;
        velY = 0.0f;
        moving = false;
        attacking = false;
        direction = Direction.DOWN;
        stateTime = 0.0f;
    }

    public void update(float delta)
    {
        handleInput();

        x += velX * delta;
        y += velY * delta;

        velX *= Constants.FRICTION;
        velY *= Constants.FRICTION;

        animationUpdate(delta);
    }

    private void animationUpdate(float delta)
    {
        stateTime += delta;

        frontMovingAnimation.setStateTime(stateTime);
        backMovingAnimation.setStateTime(stateTime);
        sideMovingAnimation.setStateTime(stateTime);
    }

    public void draw(SpriteBatch batch)
    {
        if(moving)
        {
            switch(direction)
            {
                case UP:
                {
                    drawTexture(backMovingAnimation.getCurrentFrame(), batch);
                }break;

                case DOWN:
                {
                    drawTexture(frontMovingAnimation.getCurrentFrame(), batch);
                }break;

                case LEFT:
                {
                    sideMovingAnimation.getCurrentFrame().flip(!sideMovingAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideMovingAnimation.getCurrentFrame(), batch);
                }break;

                case RIGHT:
                {
                    sideMovingAnimation.getCurrentFrame().flip(sideMovingAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideMovingAnimation.getCurrentFrame(), batch);
                }break;
            }
        }
        else if(attacking)
        {

        }
        else
        {
            drawTexture(textureRegion, batch);
        }
    }

    private void drawTexture(TextureRegion texture, SpriteBatch batch)
    {
        batch.begin();
            batch.draw(texture, x, y, width, height);
        batch.end();
    }

    private void handleInput()
    {
        moving = false;
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            moving = true;
            direction = Direction.UP;
            if(velY <= maxSpeed)
            {
                velY += step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            moving = true;
            direction = Direction.DOWN;
            if(velY >= -maxSpeed)
            {
                velY -= step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            moving = true;
            direction = Direction.LEFT;
            if(velX >= -maxSpeed)
            {
                velX -= step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
             moving = true;
            direction = Direction.RIGHT;
            if(velX <= maxSpeed)
            {
                velX += step;
            }
        }
    }
}
