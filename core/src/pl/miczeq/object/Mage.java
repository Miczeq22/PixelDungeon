package pl.miczeq.object;

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
        super(x, y);
    }

    protected void init()
    {
        super.init();

        texture = AssetsManager.instance.mage.mage;

        frontMovingAnimation = new Animator(AssetsManager.instance.mage.frontMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);
        backMovingAnimation = new Animator(AssetsManager.instance.mage.backMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);
        sideMovingAnimation = new Animator(AssetsManager.instance.mage.sideMoving, 6, Constants.MAGE_MOVING_ANIMATION_SPEED);

        maxSpeed = 12.0f;
        step = 3.0f;
    }
}
