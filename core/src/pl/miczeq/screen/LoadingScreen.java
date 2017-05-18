package pl.miczeq.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import pl.miczeq.main.Main;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/17/17.
 * Pixel Dungeon
 */
public class LoadingScreen extends AbstractScreen
{
    private float progress;
    private Vector2 barPosition;
    private Vector2 barSize;

    private float barDepth;

    public LoadingScreen(Main game)
    {
        super(game);

        AssetsManager.instance.load();
    }

    protected void init()
    {
        super.init();

        progress = 0.0f;
        barSize = new Vector2(Constants.STAGE_WIDTH - 40.0f, 60.0f);
        barPosition = new Vector2(20.0f, 100.0f);
        barDepth = 5.0f;
    }

    public void update(float delta)
    {
        progress = MathUtils.lerp(progress, AssetsManager.instance.manager.getProgress(), 0.05f);

        if(AssetsManager.instance.manager.update() && (progress >= AssetsManager.instance.manager.getProgress() - 0.01f))
        {

        }
    }

    public void render(float delta)
    {
        super.render(delta);

        batch.setProjectionMatrix(stageCamera.combined);

        batch.begin();
            batch.draw(AssetsManager.instance.stageUI.logo, Constants.STAGE_WIDTH / 2.0f - 200.0f, Constants.STAGE_HEIGHT / 2.0f, 400.0f, 200.0f);
        batch.end();

        sr.setProjectionMatrix(stageCamera.combined);

        drawProgressBar();
    }

    private void drawProgressBar()
    {
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.WHITE);
            sr.rect(barPosition.x, barPosition.y, barSize.x, barSize.y);

            sr.setColor(new Color(0.7f, 0.7f, 0.7f, 1.0f));
            sr.rect(barPosition.x + barDepth, barPosition.y + barDepth, barSize.x - (2 * barDepth), barSize.y - (2 * barDepth));

            sr.setColor(new Color(0.9f, 0.5f, 0.4f, 1.0f));
            sr.rect(barPosition.x + barDepth, barPosition.y + barDepth, (barSize.x - (2 * barDepth)) * progress, barSize.y - (2 * barDepth));
        sr.end();
    }
}
