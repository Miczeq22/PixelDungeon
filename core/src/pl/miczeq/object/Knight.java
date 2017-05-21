package pl.miczeq.object;

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
        attackPower = 1;
    }

    protected void doAttack(Direction direction)
    {
        if(!AssetsManager.instance.sounds.knightAttack.isPlaying())
        {
            AssetsManager.instance.sounds.knightAttack.play();
        }

        switch (direction)
        {
            case RIGHT:
            {

            }
            break;

            case LEFT:
            {

            }
            break;

            case UP:
            {

            }
            break;

            case DOWN:
            {

            }
            break;
        }
    }

    public void update(float delta)
    {
        super.update(delta);
    }
}
