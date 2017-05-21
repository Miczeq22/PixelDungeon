package pl.miczeq.object;

import com.badlogic.gdx.math.MathUtils;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/20/17.
 * Pixel Dungeon
 */
public class Particle extends AbstractGameObject
{
    private float velX;
    private float velY;

    private float lifeTime;

    public Particle(float x, float y, float width, float height)
    {
        super(x, y, width, height);

        velX = MathUtils.random(-20.0f, 20.0f);
        velY = MathUtils.random(-20.0f, 20.0f);

        lifeTime = 2.0f;
    }

    public void update(float delta)
    {
        x += velX * delta;
        y += velY * delta;

        velX *= Constants.FRICTION;
        velY *= Constants.FRICTION;

        lifeTime -= delta;
    }

    public boolean isDead()
    {
        return lifeTime <= 0.0f;
    }
}
