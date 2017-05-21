package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.miczeq.util.Animator;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class Mage extends AbstractClass
{
    public Mage(float x, float y)
    {
        super(x, y, Constants.MAGE_WIDTH, Constants.MAGE_HEIGHT);
    }

    protected void init()
    {
        super.init();

        texture = AssetsManager.instance.mage.mage;

        frontMovingAnimation = new Animator(AssetsManager.instance.mage.frontMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);
        backMovingAnimation = new Animator(AssetsManager.instance.mage.backMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);
        sideMovingAnimation = new Animator(AssetsManager.instance.mage.sideMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);

        frontAttackAnimation = new Animator(AssetsManager.instance.mage.frontAttack, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);
        backAttackAnimation = new Animator(AssetsManager.instance.mage.backAttack, 9, Constants.MAGE_MOVING_ANIMATION_SPEED);
        sideAttackAnimation = new Animator(AssetsManager.instance.mage.sideAttack, 9, Constants.MAGE_MOVING_ANIMATION_SPEED);

        maxSpeed = 12.0f;
        step = 3.0f;

        attackTime = 0.0f;
        attackPower = 3;

        hp = 2;
    }

    protected void doAttack(Direction direction)
    {
        if(!AssetsManager.instance.sounds.mageShoot.isPlaying())
        {
            AssetsManager.instance.sounds.mageShoot.play();
        }

        if(attackTime <= 0.0f)
        {
            switch (direction)
            {
                case RIGHT:
                {
                    bullets.add(new Bullet(x + width - Constants.MAGE_BULLET_SIZE, y + height - Constants.MAGE_BULLET_SIZE, Constants.MAGE_BULLET_SPEED, 0.0f));
                }
                break;

                case LEFT:
                {
                    bullets.add(new Bullet(x, y + height - Constants.MAGE_BULLET_SIZE, -Constants.MAGE_BULLET_SPEED, 0.0f));
                }
                break;

                case DOWN:
                {
                    bullets.add(new Bullet(x + width - Constants.MAGE_BULLET_SIZE, y + height - Constants.MAGE_BULLET_SIZE, 0.0f, -Constants.MAGE_BULLET_SPEED));
                }
                break;

                case UP:
                {
                    bullets.add(new Bullet(x, y + height - Constants.MAGE_BULLET_SIZE, 0.0f, Constants.MAGE_BULLET_SPEED));
                }
                break;
            }
            attackTime = 0.3f;
        }
        else
        {
            attackTime -= Gdx.graphics.getDeltaTime();
        }
    }
}
