package pl.miczeq.object;

import com.badlogic.gdx.Gdx;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 6/17/17.
 * Pixel Dungeon
 */
public class Archer extends AbstractClass
{
    public Archer(float x, float y)
    {
        super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);

        attackTime = 0.0f;
    }

    protected void init()
    {
        super.init();
    }

    protected void doAttack(Constants.PlayerDirection playerDirection)
    {
        if(attackTime <= 0.0f)
        {
            switch(direction)
            {
                case UP:
                {

                }break;

                case DOWN:
                {

                }break;

                case LEFT:
                {

                }break;

                case RIGHT:
                {

                }break;
            }
            attackTime = 0.3f;
        }
        else
        {
            attackTime -= Gdx.graphics.getDeltaTime();
        }
    }
}
