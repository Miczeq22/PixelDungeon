package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import pl.miczeq.main.Main;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.CameraHelper;
import pl.miczeq.util.Constants;
import pl.miczeq.util.MyTouchpad;
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

    private float shakeElapsed;

    private static MyTouchpad myTouchpad;

    public GameScreen(Main game, Constants.ClassType classType)
    {
        super(game);

        this.classType = classType;

        worldController = new WorldController(classType);

        worldRenderer = new WorldRenderer(worldController);

        myTouchpad = new MyTouchpad();
        stage.addActor(myTouchpad.getTouchpad());
        createScreenTransition();
        stage.addActor(screenTransition);

        cameraHelper = new CameraHelper(worldCamera);

        AssetsManager.instance.sounds.selectionTheme.stop();
        AssetsManager.instance.sounds.dungeonTheme.setLooping(true);
        AssetsManager.instance.sounds.dungeonTheme.play();

        shakeElapsed = 0.0f;
    }

    private void createScreenTransition()
    {
        screenTransition = new Image(AssetsManager.instance.stageUI.screenTransition);
        screenTransition.setSize(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
        screenTransition.setPosition(0.0f, 0.0f);
        screenTransition.addAction(Actions.parallel(Actions.fadeOut(2.0f, Interpolation.pow5), Actions.hide()));
    }

    public void update(float delta)
    {
        cameraHelper.handleInput(delta);

        stage.act(delta);
        worldController.update(delta);
        worldController.updateCamera(worldCamera);

        if(worldController.getDungeon().isShake())
        {
            shakeCamera(delta);

            if(shakeElapsed >= Constants.SHOOT_SHAKE_DURATION)
            {
                worldController.getDungeon().setShake(false);
                shakeElapsed = 0.0f;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new MenuScreen(game));
        }
    }

    private void shakeCamera(float delta)
    {
        if(shakeElapsed < Constants.SHOOT_SHAKE_DURATION)
        {
            float currentPower = Constants.SHOOT_SHAKE_POWER * worldCamera.zoom * ((Constants.SHOOT_SHAKE_DURATION - shakeElapsed) / Constants.SHOOT_SHAKE_DURATION);
            float x = (MathUtils.random() - 0.5f) * currentPower;
            float y = (MathUtils.random() - 0.5f) * currentPower;

            worldCamera.translate(x, y);

            shakeElapsed += delta;
        }
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

    public static MyTouchpad getMyTouchpad()
    {
        return myTouchpad;
    }
}
