package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.miczeq.util.Animator;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

import java.util.ArrayList;

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

    protected int attackPower;

    protected boolean moving;
    protected boolean attacking;

    protected Animator sideMovingAnimation;
    protected Animator frontMovingAnimation;
    protected Animator backMovingAnimation;

    protected Animator sideAttackAnimation;
    protected Animator frontAttackAnimation;
    protected Animator backAttackAnimation;

    private float stateTime;

    private float textureWidth;
    private float textureHeight;

    protected ArrayList<Bullet> bullets;

    protected float attackTime;

    public enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    protected Direction direction;

    public AbstractClass(float x, float y, float textureWidth, float textureHeight)
    {
        super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
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

        bullets = new ArrayList<Bullet>();
    }

    public void update(float delta)
    {
        handleInput();

        x += velX * delta;
        y += velY * delta;

        velX *= Constants.FRICTION;
        velY *= Constants.FRICTION;

        for(Bullet bullet : bullets)
        {
            bullet.update(delta);
        }

        animationUpdate(delta);
    }

    private void animationUpdate(float delta)
    {
        stateTime += delta;

        frontMovingAnimation.setStateTime(stateTime);
        backMovingAnimation.setStateTime(stateTime);
        sideMovingAnimation.setStateTime(stateTime);

        frontAttackAnimation.setStateTime(stateTime);
        backAttackAnimation.setStateTime(stateTime);
        sideAttackAnimation.setStateTime(stateTime);
    }

    public void draw(SpriteBatch batch)
    {
        if(moving)
        {
            doAnimation(batch, true);
            if(!AssetsManager.instance.sounds.walking.isPlaying())
            {
                AssetsManager.instance.sounds.walking.play();
            }
        }
        else if(attacking)
        {
            doAnimation(batch, false);
            doAttack(direction);
        }
        else
        {
            batch.begin();
                batch.draw(texture, x, y, textureWidth, textureHeight);
            batch.end();
        }
    }

    protected abstract void doAttack(Direction direction);

    public void drawDebug(ShapeRenderer sr)
    {

    }

    private void doAnimation(SpriteBatch batch, boolean moving)
    {
        switch(direction)
        {
            case UP:
            {
                if(moving)
                {
                    drawTexture(backMovingAnimation.getCurrentFrame(), batch);
                }
                else
                {
                    drawTexture(backAttackAnimation.getCurrentFrame(), batch);
                }
            }break;

            case DOWN:
            {
                if(moving)
                {
                    drawTexture(frontMovingAnimation.getCurrentFrame(), batch);
                }
                else
                {
                    drawTexture(frontAttackAnimation.getCurrentFrame(), batch);
                }
            }break;

            case LEFT:
            {
                if(moving)
                {
                    sideMovingAnimation.getCurrentFrame().flip(!sideMovingAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideMovingAnimation.getCurrentFrame(), batch);
                }
                else
                {
                    sideAttackAnimation.getCurrentFrame().flip(!sideAttackAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideAttackAnimation.getCurrentFrame(), batch);
                }
            }break;

            case RIGHT:
            {
                if(moving)
                {
                    sideMovingAnimation.getCurrentFrame().flip(sideMovingAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideMovingAnimation.getCurrentFrame(), batch);
                }
                else
                {
                    sideAttackAnimation.getCurrentFrame().flip(sideAttackAnimation.getCurrentFrame().isFlipX(), false);
                    drawTexture(sideAttackAnimation.getCurrentFrame(), batch);
                }
            }break;
        }
    }

    private void drawTexture(TextureRegion texture, SpriteBatch batch)
    {
        batch.begin();
            batch.draw(texture, x, y, textureWidth, textureHeight);
        batch.end();
    }

    private void handleInput()
    {
        moving = false;
        attacking = false;

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

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            attacking = true;
            moving = false;
            direction = Direction.UP;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            attacking = true;
            moving = false;
            direction = Direction.DOWN;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            attacking = true;
            moving = false;
            direction = Direction.LEFT;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            attacking = true;
            moving = false;
            direction = Direction.RIGHT;
        }
    }

    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    public boolean isAttacking()
    {
        return attacking;
    }

    public float getVelX()
    {
        return velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public int getAttackPower()
    {
        return attackPower;
    }
}
