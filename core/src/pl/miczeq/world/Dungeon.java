package pl.miczeq.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import pl.miczeq.object.*;
import pl.miczeq.util.Constants;
import pl.miczeq.util.RoomType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mikolaj on 5/20/17.
 * Pixel Dungeon
 */
public class Dungeon
{
    private List<Room> rooms;
    private AbstractClass player;
    private Room actualRoom;
    private List<Particle> particles;
    private boolean shake;

    public Dungeon(AbstractClass player)
    {
        this.player = player;
        init();
    }

    private void init()
    {
        rooms = new ArrayList<Room>();
        particles = new ArrayList<Particle>();
        shake = false;

        generateTestDungeon();
    }

    private void generateTestDungeon()
    {
        rooms.add(RoomType.getRoom(0.0f, 0.0f, RoomType.Type.FULL));
        rooms.get(0).setFirstVisit(false);
        rooms.add(RoomType.getRoom(0.0f, Constants.WORLD_HEIGHT, RoomType.Type.VERTICAL));
        rooms.add(RoomType.getRoom(0.0f, 2 * Constants.WORLD_HEIGHT, RoomType.Type.BOT));
        rooms.add(RoomType.getRoom(-Constants.VIEWPORT_WIDTH, 0.0f, RoomType.Type.HORIZONTAL));
        rooms.add(RoomType.getRoom(-2 * Constants.VIEWPORT_WIDTH, 0.0f, RoomType.Type.RIGHT));
        rooms.add(RoomType.getRoom(Constants.VIEWPORT_WIDTH, 0.0f, RoomType.Type.HORIZONTAL));
        rooms.add(RoomType.getRoom(2 * Constants.VIEWPORT_WIDTH, 0.0f, RoomType.Type.LEFT));
        rooms.add(RoomType.getRoom(0.0f, -Constants.WORLD_HEIGHT, RoomType.Type.VERTICAL));
        rooms.add(RoomType.getRoom(0.0f, -2 * Constants.WORLD_HEIGHT, RoomType.Type.TOP));

        actualRoom = rooms.get(0);
    }

    public void draw(SpriteBatch batch)
    {
        for (Room room : rooms)
        {
            room.draw(batch);

            for (Door door : room.getDoors())
            {
                door.draw(batch);
            }
        }
    }

    public void update(float delta)
    {
        AbstractGameObject.collideWithParticles(player, particles);
        actualRoom.update();

        cleanUp();



        for (Room room : rooms)
        {
            AbstractGameObject.collideWithObjects(player, room.getWalls());

            if (room.targetIsIn(player))
            {
                actualRoom = room;
            }
        }

        for (Door door : actualRoom.getDoors())
        {
            door.update(player);
            AbstractGameObject.collideWithParticles(door, particles);
        }

        for(Mob mob : actualRoom.getMobs())
        {
            mob.update(delta);
            mob.followTarget(player);
        }

        for(Mob mob : actualRoom.getMobs())
        {
            if(AbstractGameObject.collideWithObject(mob, player) != null)
            {
                if(mob.isCanHit())
                {
                    mob.setCanHit(false);
                    player.setVelX(mob.getVelX() * 5.0f);
                    player.setVelY(mob.getVelY() * 5.0f);
                    if(player.getArmor() > 0)
                    {
                        player.setArmor(player.getArmor() - 1);
                    }
                    else
                    {
                        player.setArmor(player.getMaxArmor());
                        player.setHp(player.getHp() - 1);
                    }
                }
            }

            if(AbstractGameObject.collideWithObject(mob, player.getHitBox()) != null)
            {
                mob.setVelX(player.getVelX() * 5.0f);
                mob.setVelY(player.getVelY() * 5.0f);
                mob.setHp(mob.getHp() - player.getAttackPower());
            }
        }

        if (actualRoom.targetInside(player) && actualRoom.isFirstVisit())
        {
            for (Door door : actualRoom.getDoors())
            {
                door.setClosed(true);
            }

            actualRoom.setFirstVisit(false);

            int max = MathUtils.random(1, 4);

            for(int i = 0; i < max; i++)
            {
                actualRoom.getMobs().add(new Mob(MathUtils.random(actualRoom.getCenterX() - 15.0f, actualRoom.getCenterX() + 15.0f),
                        MathUtils.random(actualRoom.getCenterY()- 5.0f, actualRoom.getCenterY() + 5.0f), 1.5f, 1.5f));
            }
        }

        if (actualRoom.targetInside(player) && !actualRoom.isFirstVisit() && actualRoom.isRoomCleaned())
        {
            for (Door door : actualRoom.getDoors())
            {
                door.setClosed(false);
            }
        }

        for(Particle particle : particles)
        {
            particle.update(delta);
        }

        for(AbstractGameObject wall : actualRoom.getWalls())
        {
            AbstractGameObject.collideWithParticles(wall, particles);
        }
    }

    private void cleanUp()
    {
        Iterator<Mob> mobIterator = actualRoom.getMobs().iterator();
        while (mobIterator.hasNext())
        {
            Mob mob = mobIterator.next();

            if (mob.getHp() <= 0)
            {
                int max = MathUtils.random(8, 20);
                addParticles(max, mob.getX(), mob.getY(), 0.1f, 0.1f);
                mobIterator.remove();
                shake = true;
            }
        }

        Iterator<Bullet> bulletIterator = player.getBullets().iterator();
        while(bulletIterator.hasNext())
        {
            Bullet bullet = bulletIterator.next();

            for (Mob mob : actualRoom.getMobs())
            {
                if ((mob.getHp() > 0 && AbstractGameObject.collideWithObject(bullet, mob) != null))
                {
                    mob.setVelX(bullet.getVelX() * 3.0f);
                    mob.setVelY(bullet.getVelY() * 3.0f);
                    mob.setHp(mob.getHp() - player.getAttackPower());
                    bulletIterator.remove();
                }
            }
        }

        bulletIterator = player.getBullets().iterator();
        while (bulletIterator.hasNext())
        {
            Bullet bullet = bulletIterator.next();

            for (Door door : actualRoom.getDoors())
            {
                if (door.isClosed() && AbstractGameObject.collideWithObject(bullet, door) != null)
                {
                    int max = MathUtils.random(4, 10);
                    addParticles(max, bullet.getX(), bullet.getY(), 0.25f, 0.25f);
                    bulletIterator.remove();
                }
            }
        }

        bulletIterator = player.getBullets().iterator();
        while (bulletIterator.hasNext())
        {
            Bullet bullet = bulletIterator.next();

            if(!actualRoom.targetIsIn(bullet))
            {
                bulletIterator.remove();
            }

            for (AbstractGameObject wall : actualRoom.getWalls())
            {
                if (AbstractGameObject.collideWithObject(bullet, wall) != null)
                {
                    int max = MathUtils.random(7, 15);
                    addParticles(max, bullet.getX(), bullet.getY(), 0.25f, 0.25f);
                    bulletIterator.remove();
                }
            }
        }

        Iterator<Particle> particleIterator = particles.iterator();
        while(particleIterator.hasNext())
        {
            Particle particle = particleIterator.next();

            if(particle.isDead())
            {
                particleIterator.remove();
            }
        }
    }

    private void addParticles(int number, float x, float y, float width, float height)
    {
        for(int i = 0; i < number; i++)
        {
            particles.add(new Particle(x, y, width, height));
        }
    }


    public void updateCamera(OrthographicCamera camera)
    {
        if (actualRoom.targetIsIn(player))
        {
            camera.position.x = camera.position.x + (actualRoom.getCenterX() - camera.position.x) * 0.1f;
            camera.position.y = camera.position.y + (actualRoom.getCenterY() - camera.position.y) * 0.1f;
            camera.update();
        }
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

    public List<Particle> getParticles()
    {
        return particles;
    }

    public boolean isShake()
    {
        return shake;
    }

    public void setShake(boolean shake)
    {
        this.shake = shake;
    }
}
