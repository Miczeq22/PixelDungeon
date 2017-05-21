package pl.miczeq.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.miczeq.object.Bullet;
import pl.miczeq.object.Mob;
import pl.miczeq.object.Particle;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class WorldRenderer
{
    private WorldController worldController;

    private OrthographicCamera hudCamera;

    public WorldRenderer(WorldController worldController)
    {
        this.worldController = worldController;

        initHudCamera();
    }

    private void initHudCamera()
    {
        hudCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        hudCamera.position.set(Constants.VIEWPORT_WIDTH / 2.0f, Constants.VIEWPORT_HEIGHT / 2.0f, 0.0f);
        hudCamera.update();
    }

    public void render(SpriteBatch batch, ShapeRenderer sr)
    {
        worldController.getDungeon().draw(batch);

        worldController.getPlayer().draw(batch);

        worldController.getPlayer().drawDebug(sr);

        for (Room room : worldController.getDungeon().getRooms())
        {
            for (Mob mob : room.getMobs())
            {
                mob.drawDebug(sr, Color.RED);
            }
        }

        for(Bullet bullet : worldController.getPlayer().getBullets())
        {
            bullet.drawDebug(sr, Color.RED);
        }

        for(Particle particle : worldController.getDungeon().getParticles())
        {
            particle.drawDebug(sr, Color.RED);
        }

        sr.setProjectionMatrix(hudCamera.combined);

        drawHUD(batch, sr);
    }

    private void drawHUD(SpriteBatch batch, ShapeRenderer sr)
    {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(0.0f, Constants.WORLD_HEIGHT, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT - Constants
                .WORLD_HEIGHT);
        sr.end();

        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();
            for(int i = 0; i < worldController.getPlayer().getHp(); i++)
            {
                batch.draw(AssetsManager.instance.room.hearth, i * (3.0f + 0.5f), Constants.WORLD_HEIGHT + 1.0f, 3.0f, 3.0f);
            }
        batch.end();
    }
}
