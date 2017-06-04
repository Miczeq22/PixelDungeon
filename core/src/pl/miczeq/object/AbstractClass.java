package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.scene.traversal.Direction;
import pl.miczeq.screen.GameScreen;
import pl.miczeq.util.Animator;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;
import pl.miczeq.util.HitBox;

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
    protected int hp;
    protected int armor;
    protected int maxArmor;

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
    protected HitBox hitBox;

    protected float attackTime;



    protected Constants.PlayerDirection direction;
    protected Constants.TouchpadDirection touchpadDirection;

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
        direction = Constants.PlayerDirection.DOWN;
        stateTime = 0.0f;

        bullets = new ArrayList<Bullet>();
        hitBox = new HitBox(x, y, width, 2.0f);
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

        if(!attacking)
        {
            hitBox.setWidth(0.0f);
            hitBox.setHeight(0.0f);
        }
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

    protected abstract void doAttack(Constants.PlayerDirection direction);

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

    private void getTouchpadDirection()
    {
        Vector2 touchpadVector = new Vector2(GameScreen.getMyTouchpad().getTouchpad().getKnobPercentX(), GameScreen.getMyTouchpad().getTouchpad().getKnobPercentY());
        float touchpadAngle = touchpadVector.angle();

        if(touchpadAngle >= 30 && touchpadAngle < 130)
        {
            touchpadDirection = Constants.TouchpadDirection.UP;
        }
        else if(touchpadAngle >= 230 && touchpadAngle <= 320)
        {
            touchpadDirection = Constants.TouchpadDirection.DOWN;
        }
        else if(touchpadAngle >= 130 && touchpadAngle < 230)
        {
            touchpadDirection = Constants.TouchpadDirection.LEFT;
        }
        else
        {
            touchpadDirection = Constants.TouchpadDirection.RIGHT;
        }
    }

    private void handleInput()
    {
        moving = false;
        attacking = false;

        if(GameScreen.getMyTouchpad().getTouchpad().isTouched())
        {
            getTouchpadDirection();
        }
        else
        {
            touchpadDirection = Constants.TouchpadDirection.NONE;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W) || touchpadDirection == Constants.TouchpadDirection.UP)
        {
            moving = true;
            direction = Constants.PlayerDirection.UP;
            if(velY <= maxSpeed)
            {
                velY += step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S) || touchpadDirection == Constants.TouchpadDirection.DOWN)
        {
            moving = true;
            direction = Constants.PlayerDirection.DOWN;
            if(velY >= -maxSpeed)
            {
                velY -= step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A) || touchpadDirection == Constants.TouchpadDirection.LEFT)
        {
            moving = true;
            direction = Constants.PlayerDirection.LEFT;
            if(velX >= -maxSpeed)
            {
                velX -= step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D) || touchpadDirection == Constants.TouchpadDirection.RIGHT)
        {
            moving = true;
            direction = Constants.PlayerDirection.RIGHT;
            if(velX <= maxSpeed)
            {
                velX += step;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) || (Gdx.input.isTouched() && GameScreen.getUpButton().pointerIsIn(GameScreen.getStageCamera())))
        {
            attacking = true;
            moving = false;
            direction = Constants.PlayerDirection.UP;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || (Gdx.input.isTouched() && GameScreen.getDownButton().pointerIsIn(GameScreen.getStageCamera())))
        {
            attacking = true;
            moving = false;
            direction = Constants.PlayerDirection.DOWN;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() && GameScreen.getLeftButton().pointerIsIn(GameScreen.getStageCamera())))
        {
            attacking = true;
            moving = false;
            direction = Constants.PlayerDirection.LEFT;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched() && GameScreen.getRightButton().pointerIsIn(GameScreen.getStageCamera())))
        {
            attacking = true;
            moving = false;
            direction = Constants.PlayerDirection.RIGHT;
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

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }

    public HitBox getHitBox()
    {
        return hitBox;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = armor;
    }

    public int getMaxArmor()
    {
        return maxArmor;
    }
}
