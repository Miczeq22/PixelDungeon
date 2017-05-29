package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import pl.miczeq.main.Main;
import pl.miczeq.util.Constants;

/**
 * Created by mikolaj on 5/17/17.
 * Pixel Dungeon
 */
public abstract class AbstractScreen implements Screen
{
    protected Main game;
    protected ShapeRenderer sr;
    protected SpriteBatch batch;

    protected OrthographicCamera worldCamera;
    protected OrthographicCamera stageCamera;

    protected Stage stage;

    public AbstractScreen(Main game)
    {
        this.game = game;
        init();
    }

    protected void init()
    {
        sr = new ShapeRenderer();
        batch = new SpriteBatch();

        initWorldCamera();
        initStageCamera();

        stage = new Stage(new StretchViewport(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT, stageCamera));

        Gdx.input.setInputProcessor(stage);
    }

    private void initWorldCamera()
    {
        worldCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        worldCamera.position.set(Constants.VIEWPORT_WIDTH / 2.0f, Constants.VIEWPORT_HEIGHT / 2.0f, 0.0f);
        worldCamera.update();
    }

    private void initStageCamera()
    {
        stageCamera = new OrthographicCamera(Constants.STAGE_WIDTH, Constants.STAGE_HEIGHT);
        stageCamera.position.set(Constants.STAGE_WIDTH / 2.0f, Constants.STAGE_HEIGHT / 2.0f, 0.0f);
        stageCamera.update();
    }

    @Override
    public void show()
    {

    }

    public abstract void update(float delta);

    @Override
    public void render(float delta)
    {
        update(delta);

        clearScreen();
    }

    private void clearScreen()
    {
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.6f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        sr.dispose();
        stage.dispose();
        game.dispose();
    }
}
