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

    public Mob(float x, float y, float width, float height)
    {
        super(x, y, width, height);
        hp = 5;

        sounds = new Music[3];
        sounds[0] = AssetsManager.instance.sounds.mobGrowl;
        sounds[1] = AssetsManager.instance.sounds.mobGrowl2;
        sounds[2] = AssetsManager.instance.sounds.mobGrowl3;
    }

    public void update(float delta)
    {
        x += velX * delta;
        y += velY * delta;

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

        dx  /= norm;
        dy /= norm;

        velX = dx * 7.0f;
        velY = dy * 7.0f;
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
