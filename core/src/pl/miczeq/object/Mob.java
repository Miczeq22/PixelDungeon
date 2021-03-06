package pl.miczeq.object;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import pl.miczeq.util.AssetsManager;
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

    private Music[] sounds;

    private boolean soundPlaying;

    private boolean canMove;
    private float timeToRespawn;

    private boolean canHit;
    private float timeToNextHit;

    public Mob(float x, float y, float width, float height)
    {
        super(x, y, width, height);
        hp = 5;

        sounds = new Music[3];
        sounds[0] = AssetsManager.instance.sounds.mobGrowl;
        sounds[1] = AssetsManager.instance.sounds.mobGrowl2;
        sounds[2] = AssetsManager.instance.sounds.mobGrowl3;

        canMove = false;
        timeToRespawn = MathUtils.random(0.3f, 0.7f);
        canHit = true;
        timeToNextHit = 0.7f;
    }

    public void update(float delta)
    {
        if(timeToRespawn <= 0.0f)
        {
            x += velX * delta;
            y += velY * delta;
        }
        else
        {
            timeToRespawn -= delta;
        }

        if(!canHit)
        {
            if(timeToNextHit <= 0.0f)
            {
                canHit = true;
            }
            else
            {
                timeToNextHit -= delta;
            }
        }

        velX *= Constants.FRICTION;
        velY *= Constants.FRICTION;

        for(int i = 0; i < sounds.length; i++)
        {
            soundPlaying = true;
            if(!sounds[i].isPlaying())
            {
                soundPlaying = false;
            }
        }

        if(!soundPlaying)
        {
            int rand = MathUtils.random(0, 2);
            sounds[rand].play();
        }
    }

    public void followTarget(AbstractGameObject target)
    {
        float dx = target.x - x;
        float dy = target.y - y;

        float norm = (float) Math.sqrt(dx * dx + dy * dy);

        dx /= norm;
        dy /= norm;

        velX = dx * 5.0f;
        velY = dy * 5.0f;
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

    public boolean isInTarget(AbstractGameObject target)
    {
        return x > target.x && x < target.x + target.width && y > target.y && y < target.y + target.height;
    }

    public boolean isCanHit()
    {
        return canHit;
    }

    public void setCanHit(boolean canHit)
    {
        this.canHit = canHit;
    }
}