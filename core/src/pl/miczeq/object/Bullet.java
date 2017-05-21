package pl.miczeq.object;

import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/19/17.
 * Pixel Dungeon
 */
public class Bullet extends AbstractGameObject
{
    private float velX;
    private float velY;

    public Bullet(float x, float y, float velX, float velY)
    {
        super(x, y, Constants.MAGE_BULLET_SIZE, Constants.MAGE_BULLET_SIZE);

        this.velX = velX;
        this.velY = velY;
    }

    public void update(float delta)
    {
        x += velX * delta;
        y += velY * delta;
    }

    public float getVelX()
    {
        return velX;
    }

    public float getVelY()
    {
        return velY;
    }
}
