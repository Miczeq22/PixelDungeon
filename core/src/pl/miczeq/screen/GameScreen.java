package pl.miczeq.screen;

import pl.miczeq.main.Main;
import pl.miczeq.util.Constants;
import pl.miczeq.world.WorldController;
import pl.miczeq.world.WorldRenderer;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class GameScreen extends AbstractScreen
{
    private final Constants.ClassType classType;

    private WorldController worldController;
    private WorldRenderer worldRenderer;

    public GameScreen(Main game, Constants.ClassType classType)
    {
        super(game);

        this.classType = classType;
    }

    protected void init()
    {
        super.init();

        worldController = new WorldController(Constants.ClassType.MAGE);
        worldRenderer = new WorldRenderer(worldController);
    }

    public void update(float delta)
    {
        worldController.update(delta);
    }

    public void render(float delta)
    {
        super.render(delta);

        batch.setProjectionMatrix(worldCamera.combined);
        worldRenderer.render(batch);
    }
}
