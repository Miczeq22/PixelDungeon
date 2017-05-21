package pl.miczeq.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javafx.geometry.Pos;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

import javax.swing.text.Position;

/**
 * Created by mikolaj on 5/19/17.
 * Pixel Dungeon
 */
public class Door extends AbstractGameObject
{
    private boolean closed;

    public enum Position
    {
        TOP,
        BOT,
        LEFT,
        RIGHT
    }

    public Position position;

    public Door(float x, float y, Position position)
    {
        super(x, y, Constants.DOOR_WIDTH * 2.0f, Constants.WALL_WIDTH);

        this.position = position;
        closed = false;

        if(position == Position.TOP || position == Position.BOT)
        {
            width = Constants.DOOR_WIDTH * 2.0f;
            height = Constants.WALL_WIDTH;
        }
        else
        {
            width = Constants.WALL_WIDTH;
            height = Constants.DOOR_WIDTH * 2.0f;
        }
    }

    public void update(AbstractClass target)
    {
        if(closed)
        {
            AbstractGameObject.collideWithObject(target, this);
        }
        else
        {
            if(targetIsIn(target))
            {
                switch (position)
                {
                    case TOP:
                    {
                        target.y += 10.0f;
                    }break;

                    case BOT:
                    {
                        target.y -= 10.0f;
                    }break;

                    case LEFT:
                    {
                        target.x -= 9.0f;
                    }break;

                    case RIGHT:
                    {
                        target.x += 9.0f;
                    }break;
                }
            }
        }
    }

    public void draw(SpriteBatch batch)
    {
        batch.begin();
            if(closed)
            {
                batch.draw((position == Position.TOP || position == Position.BOT) ?
                                AssetsManager.instance.room.doorClosedVertical : AssetsManager.instance.room.doorClosedHorizontal, x, y, width, height);
            }
            else
            {
                batch.draw(AssetsManager.instance.room.doorOpen, x, y, width, height);
            }
        batch.end();
    }

    public boolean targetIsIn(AbstractGameObject target)
    {
        return target.x > x && target.x < x + width && target.y > y && target.y < y + height;
    }

    public static Door getTopDoors(float x, float y)
    {
        return new Door(x + Constants.VIEWPORT_WIDTH / 2.0f - Constants.DOOR_WIDTH, y + Constants.WORLD_HEIGHT - Constants.WALL_WIDTH - 0.5f, Door.Position.TOP);
    }

    public static Door getBotDoors(float x, float y)
    {
        return new Door(x + Constants.VIEWPORT_WIDTH / 2.0f - Constants.DOOR_WIDTH, y + 0.5f, Door.Position.BOT);
    }

    public static Door getLeftDoors(float x, float y)
    {
        return new Door(x + 0.5f, y + Constants.WORLD_HEIGHT / 2.0f - Constants.DOOR_WIDTH , Position.LEFT);
    }

    public static Door getRightDoors(float x, float y)
    {
        return new Door(x + Constants.VIEWPORT_WIDTH - Constants.WALL_WIDTH - 0.5f, y + Constants.WORLD_HEIGHT / 2.0f - Constants.DOOR_WIDTH, Position.RIGHT);
    }

    public boolean isClosed()
    {
        return closed;
    }

    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }
}
