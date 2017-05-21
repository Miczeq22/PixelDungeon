package pl.miczeq.object;

import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/19/17.
 * Pixel Dungeon
 */
public class Mob extends AbstractGameObject
{
    private int hp;

    private float velX;
    private float velY;

    public Mob(float x, float y, float width, float height)
    {
        super(x, y, width, height);
        hp = 5;
    }

    public void update(float delta)
    {
        x += velX * delta;
        y += velY * delta;

        velX *= Constants.FRICTION;
        velY *= Constants.FRICTION;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public float getVelX()
    {
        return velX;
    }

    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }
}
