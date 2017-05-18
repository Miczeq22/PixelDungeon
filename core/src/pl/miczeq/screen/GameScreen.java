package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pl.miczeq.main.Main;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.CameraHelper;
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

    private Image screenTransition;

    private CameraHelper cameraHelper;

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
        screenTransition = new Image(AssetsManager.instance.stageUI.screenTransition);
        screenTransition.setSize(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
        screenTransition.setPosition(0.0f, 0.0f);
        screenTransition.addAction(Actions.fadeOut(2.0f, Interpolation.pow5));
        stage.addActor(screenTransition);

        cameraHelper = new CameraHelper(worldCamera);
    }

    public void update(float delta)
    {
        cameraHelper.handleInput(delta);

        stage.act(delta);
        worldController.update(delta);
    }

    public void render(float delta)
    {
        super.render(delta);

        batch.setProjectionMatrix(worldCamera.combined);
        sr.setProjectionMatrix(worldCamera.combined);
        worldRenderer.render(batch, sr);

        batch.setProjectionMatrix(stageCamera.combined);
        batch.begin();
            stage.draw();
        batch.end();
    }
}
