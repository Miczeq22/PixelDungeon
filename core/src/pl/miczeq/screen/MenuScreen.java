package pl.miczeq.screen;

import pl.miczeq.main.Main;
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

    public MenuScreen(Main game)
    {
        super(game);
    }

    protected void init()
    {
        super.init();

        playBtn = new MyButton(Constants.STAGE_WIDTH - playBtnWidth - 40.0f, 20.0f, playBtnWidth, playBtnHeight, AssetsManager.instance.stageUI.playBtn);
    }

    public void update(float delta)
    {
        if(playBtn.pointerIsIn(stageCamera))
        {
            playBtn.setTextureRegion(AssetsManager.instance.stageUI.playBtnHover);
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
        drawBG();
        playBtn.draw(batch);
    }

    private void drawBG()
    {
        batch.begin();
            batch.draw(AssetsManager.instance.stageUI.menuBG, 0.0f, 0.0f, Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
        batch.end();
    }
}
