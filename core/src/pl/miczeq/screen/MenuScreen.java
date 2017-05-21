package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import pl.miczeq.main.Main;
import pl.miczeq.util.Animator;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;
import pl.miczeq.util.MyButton;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class MenuScreen extends AbstractScreen
{
    private final float playBtnWidth = 150.0f;
    private final float playBtnHeight = 50.0f;

    private MyButton playBtn;

    private Animator bgAnimation;
    private float stateTime;

    private Image bgImg;

    public MenuScreen(Main game)
    {
        super(game);
    }

    protected void init()
    {
        super.init();

        playBtn = new MyButton(Constants.STAGE_WIDTH - playBtnWidth - 40.0f, 20.0f, playBtnWidth, playBtnHeight, AssetsManager.instance.stageUI.playBtn);
        bgAnimation = new Animator(AssetsManager.instance.stageUI.menuBgAnimaton, 4, 0.10f);
        stateTime = 0.0f;
        bgImg = new Image(bgAnimation.getCurrentFrame());
        bgImg.setSize(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
        bgImg.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(1.5f)));

        playBtn = new MyButton(Constants.STAGE_WIDTH - playBtnWidth - 40.0f, 20.0f, playBtnWidth, playBtnHeight, AssetsManager.instance.stageUI.playBtn);
        playBtn.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.delay(1.5f), Actions.fadeIn(0.5f)));

        stage.addActor(bgImg);
        stage.addActor(playBtn);

        AssetsManager.instance.sounds.campfire.setLooping(true);
        AssetsManager.instance.sounds.campfire.play();

    }

    public void update(float delta)
    {
        stage.act(delta);
        stateTime += delta;
        bgAnimation.setStateTime(stateTime);
        bgImg.setDrawable(new SpriteDrawable(new Sprite(bgAnimation.getCurrentFrame())));

        if(playBtn.pointerIsIn(stageCamera))
        {
            playBtn.setTextureRegion(AssetsManager.instance.stageUI.playBtnHover);
            if(Gdx.input.justTouched())
            {
                AssetsManager.instance.sounds.click.play();
                playBtn.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.run(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        game.setScreen(new ClassSelectionScreen(game));
                    }
                })));
            }
        }
        else
        {
            playBtn.setTextureRegion(AssetsManager.instance.stageUI.playBtn);
        }
    }

    public void render(float delta)
    {
        super.render(delta);

        batch.setProjectionMatrix(stageCamera.combined);

        batch.begin();
            stage.draw();
        batch.end();
    }
}
