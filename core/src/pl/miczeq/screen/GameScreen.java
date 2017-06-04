package pl.miczeq.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import pl.miczeq.main.Main;
import pl.miczeq.util.*;
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

    private static MyButton upButton;
    private static MyButton downButton;
    private static MyButton leftButton;
    private static MyButton rightButton;

    public GameScreen(Main game, Constants.ClassType classType)
    {
        super(game);

        this.classType = classType;

        worldController = new WorldController(classType);

        worldRenderer = new WorldRenderer(worldController);

        myTouchpad = new MyTouchpad();
        createButtons();

        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            stage.addActor(myTouchpad.getTouchpad());
            stage.addActor(upButton);
            stage.addActor(downButton);
            stage.addActor(leftButton);
            stage.addActor(rightButton);
        }

        createScreenTransition();

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
        screenTransition.addAction(Actions.sequence(Actions.fadeOut(2.0f, Interpolation.pow5), Actions.hide()));

        stage.addActor(screenTransition);
    }

    private void createButtons()
    {
        upButton = new MyButton(Constants.STAGE_WIDTH - (2 * 70.0f) - 10.0f, 85.0f, 70.0f, 70.0f, AssetsManager.instance.stageUI.upBtn);
        downButton = new MyButton(Constants.STAGE_WIDTH - (2 * 70.0f) - 10.0f, 10.0f, 70.0f, 70.0f, AssetsManager.instance.stageUI.downBtn);
        leftButton = new MyButton(Constants.STAGE_WIDTH - (3 * 70.0f) - 15.0f, 30.0f, 70.0f, 70.0f, AssetsManager.instance.stageUI.leftBtn);
        rightButton = new MyButton(Constants.STAGE_WIDTH - 75.0f, 30.0f, 70.0f, 70.0f, AssetsManager.instance.stageUI.rightBtn);
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.BACK))
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

    public static MyButton getUpButton()
    {
        return upButton;
    }

    public static MyButton getDownButton()
    {
        return downButton;
    }

    public static MyButton getLeftButton()
    {
        return leftButton;
    }

    public static MyButton getRightButton()
    {
        return rightButton;
    }
}
