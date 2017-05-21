package pl.miczeq.object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public abstract class AbstractGameObject
{
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected Texture texture;

    public AbstractGameObject(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static String collideWithObject(AbstractGameObject objA, AbstractGameObject objB)
    {
        String direction = null;

        double vX = (objA.x + (objA.width / 2.0f)) - (objB.x + (objB.width / 2.0f));
        double vY = (objA.y + (objA.height / 2.0f)) - (objB.y + (objB.height / 2.0f));
        double hW = (objA.width / 2.0f) + (objB.width / 2.0f);
        double hH = (objA.height / 2.0f) + (objB.height / 2.0f);

        if(Math.abs(vX) < hW && Math.abs(vY) < hH)
        {
            double oX = hW - Math.abs(vX);
            double oY = hH - Math.abs(vY);

            if(oX >= oY)
            {
                if(vY > 0)
                {
                    direction = "top";
                    objA.y += oY;
                }
                else
                {
                    direction = "bot";
                    objA.y -= oY;
                }
            }
            else
            {
                if(vX > 0)
                {
                    direction = "left";
                    objA.x += oX;
                }
                else
                {
                    direction = "right";
                    objA.x -= oX;
                }
            }
        }

        return direction;
    }

    public static String collideWithObjects(AbstractGameObject objA, List<AbstractGameObject> objects)
    {
        String direction = null;

        for(AbstractGameObject object : objects)
        {
            direction = collideWithObject(objA, object);
        }

        return direction;
    }

    public static String collideWithParticles(AbstractGameObject object, List<Particle> particles)
    {
        String direction = null;

        for(Particle particle : particles)
        {
            direction = collideWithObject(particle, object);
        }

        return direction;
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            batch.draw(texture, x, y, width, height);
        batch.end();
    }

    public void drawDebug(ShapeRenderer sr, Color color)
    {
        sr.setColor(color);
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.rect(x, y, width, height);
        sr.end();
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }
}
