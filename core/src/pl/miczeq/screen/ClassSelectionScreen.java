package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import pl.miczeq.main.Main;
import pl.miczeq.util.AssetsManager;
import pl.miczeq.util.ClassCard;
import pl.miczeq.util.Constants;
import pl.miczeq.util.MyButton;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class ClassSelectionScreen extends AbstractScreen
{
    private Constants.ClassType selectedClass;
    private ClassCard[] classCards;

    private final float buttonWidth = 150.0f;
    private final float buttonHeight = 50.0f;

    private MyButton selectBtn;
    private MyButton backBtn;

    private boolean cardSelected;

    public ClassSelectionScreen(Main game)
    {
        super(game);
    }

    protected void init()
    {
        super.init();

        selectedClass = Constants.ClassType.NONE;
        classCards = new ClassCard[3];
        classCards[0] = new ClassCard(20.0f, Constants.ClassType.ARCHER, AssetsManager.instance.classCards
                .archerCard, AssetsManager
                .instance.classCards.archerInfo);
        classCards[1] = new ClassCard(20.0f + Constants.CLASS_CARD_WIDTH + 20.0f, Constants.ClassType.KNIGHT,
                AssetsManager.instance.classCards.knightCard, AssetsManager.instance.classCards.knightInfo);
        classCards[2] = new ClassCard(20.0f + Constants.CLASS_CARD_WIDTH * 2.0f + 40.0f, Constants.ClassType.MAGE,
                AssetsManager.instance.classCards.mageCard, AssetsManager.instance.classCards.mageInfo);

        selectBtn = new MyButton(10.0f, 10.0f, buttonWidth, buttonHeight, AssetsManager.instance.stageUI.selectBtn);
        backBtn = new MyButton(Constants.STAGE_WIDTH - buttonWidth - 10.0f, 10.0f, buttonWidth, buttonHeight,
                AssetsManager.instance.stageUI.backBtn);

        cardSelected = false;

        for (int i = 0; i < classCards.length; i++)
        {
            classCards[i].addAction(Actions.sequence(Actions.moveTo(20.0f + i * (Constants.CLASS_CARD_WIDTH + 20.0f),
                    Constants.CLASS_CARD_Y, 1.5f, Interpolation.exp5)));
            classCards[i].setY(Constants.CLASS_CARD_Y);
            stage.addActor(classCards[i]);
            stage.addActor(classCards[i].getInfoImage());
        }

        stage.addActor(selectBtn);
        stage.addActor(backBtn);
    }

    public void update(float delta)
    {
        stage.act(delta);

        for (int i = 0; i < classCards.length; i++)
        {
            if (classCards[i].pointerIsIn(stageCamera) && Gdx.input.justTouched())
            {
                selectedClass = classCards[i].getClassType();
                cardSelected = true;
            }
        }

        if (cardSelected)
        {
            if (selectBtn.pointerIsIn(stageCamera) && Gdx.input.justTouched())
            {
                game.setScreen(new GameScreen(game, selectedClass));
            }

            if (backBtn.pointerIsIn(stageCamera) && Gdx.input.justTouched())
            {
                cardSelected = false;
                selectedClass = Constants.ClassType.NONE;
            }
        }
    }

    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        batch.setProjectionMatrix(stageCamera.combined);
        switch (selectedClass)
        {
            case ARCHER:
            {
                showInfoCard(0);
            }
            break;

            case KNIGHT:
            {
                showInfoCard(1);
            }
            break;

            case MAGE:
            {
                showInfoCard(2);
            }
            break;

            case NONE:
            {
                showCards();
            }
            break;
        }

        batch.begin();
        stage.draw();
        batch.end();
    }

    private void showInfoCard(int i)
    {
        hideCards();
        classCards[i].getInfoImage().addAction(Actions.parallel(Actions.fadeIn(0.3f), Actions.moveTo(0.0f, 80.0f, 0.3f)));
        showButtons();
    }

    private void showCards()
    {
        for (int i = 0; i < classCards.length; i++)
        {
            classCards[i].addAction(Actions.show());
            classCards[i].getInfoImage().addAction(Actions.parallel(Actions.fadeOut(0.3f), Actions.moveTo(-Constants.STAGE_WIDTH, 80.0f, 0.3f)));
        }

        selectBtn.addAction(Actions.fadeOut(0.1f));
        backBtn.addAction(Actions.fadeOut(0.1f));
    }

    private void hideCards()
    {
        for (int i = 0; i < classCards.length; i++)
        {
            classCards[i].addAction(Actions.hide());
        }
    }

    private void showButtons()
    {
        selectBtn.addAction(Actions.parallel(Actions.fadeIn(1.0f)));
        backBtn.addAction(Actions.parallel(Actions.fadeIn(1.0f)));
    }
}
