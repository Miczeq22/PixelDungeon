package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.miczeq.util.Animator;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/19/17.
 * Pixel Dungeon
 */
public class Knight extends AbstractClass
{
    private float attackTime;

    public Knight(float x, float y)
    {
        super(x, y, Constants.KNIGHT_WIDTH, Constants.KNIGHT_HEIGHT);
    }

    protected void init()
    {
        super.init();

        texture = AssetsManager.instance.knight.knight;

        frontMovingAnimation = new Animator(AssetsManager.instance.knight.frontMoving, 4, Constants.KNIGHT_MOVING_ANIMATION_SPEED);
        backMovingAnimation = new Animator(AssetsManager.instance.knight.backMoving, 4, Constants.KNIGHT_MOVING_ANIMATION_SPEED);
        sideMovingAnimation = new Animator(AssetsManager.instance.knight.sideMoving, 6, Constants.KNIGHT_MOVING_ANIMATION_SPEED);

        frontAttackAnimation = new Animator(AssetsManager.instance.knight.frontAttack, 8, Constants.KNIGHT_MOVING_ANIMATION_SPEED);
        backAttackAnimation = new Animator(AssetsManager.instance.knight.backAttack, 8, Constants.KNIGHT_MOVING_ANIMATION_SPEED);
        sideAttackAnimation = new Animator(AssetsManager.instance.knight.sideAttack, 8, Constants.KNIGHT_MOVING_ANIMATION_SPEED);

        maxSpeed = 12.0f;
        step = 4.0f;
        attackPower = 2;

        hp = 5;

        attackTime = 0.0f;
    }

    private void setHitBox(float x, float y, float width, float height)
    {
        hitBox.x = x;
        hitBox.y = y;
        hitBox.width = width;
        hitBox.height = height;
    }

    protected void doAttack(Direction direction)
    {
        if(!AssetsManager.instance.sounds.knightAttack.isPlaying())
        {
            AssetsManager.instance.sounds.knightAttack.play();
        }

        if(attackTime <= 0.0f)
        {
            switch (direction)
            {
                case RIGHT:
                {
                    setHitBox(x + width, y, 2.0f, height);
                }
                break;

                case LEFT:
                {
                    setHitBox(x - 1.0f, y, 2.0f, height);
                }
                break;

                case UP:
                {
                    setHitBox(x, y + height - 0.5f, width + 1.0f, 2.0f);
                }
                break;

                case DOWN:
                {
                    setHitBox(x + 0.2f, y - 1.5f, width + 0.8f, 2.0f);
                }
                break;
            }
            attackTime = 0.1f;
        }
        else
        {
            attackTime -= Gdx.graphics.getDeltaTime();
            setHitBox(x, y, 0.0f, 0.0f);
        }
    }

    public void update(float delta)
    {
        super.update(delta);
    }
}
