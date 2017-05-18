package pl.miczeq.screen;

import com.badlogic.gdx.Gdx;
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
        classCards[0] = new ClassCard(20.0f, Constants.ClassType.ARCHER, AssetsManager.instance.classCards.archerCard, AssetsManager
                .instance.classCards.archerInfo);
        classCards[1] = new ClassCard(20.0f + Constants.CLASS_CARD_WIDTH + 20.0f, Constants.ClassType.KNIGHT,
                AssetsManager.instance.classCards.knightCard, AssetsManager.instance.classCards.knightInfo);
        classCards[2] = new ClassCard(20.0f + Constants.CLASS_CARD_WIDTH * 2.0f + 40.0f, Constants.ClassType.MAGE,
                AssetsManager.instance.classCards.mageCard, AssetsManager.instance.classCards.mageInfo);

        selectBtn = new MyButton(10.0f, 10.0f, buttonWidth, buttonHeight, AssetsManager.instance.stageUI.selectBtn);
        backBtn = new MyButton(Constants.STAGE_WIDTH - buttonWidth - 10.0f, 10.0f, buttonWidth, buttonHeight, AssetsManager.instance.stageUI.backBtn);

        cardSelected = false;
    }

    public void update(float delta)
    {
        for(int i = 0; i < classCards.length; i++)
        {
            if(classCards[i].pointerIsIn(stageCamera) && Gdx.input.isTouched())
            {
                selectedClass = classCards[i].getClassType();
                cardSelected = true;
            }
        }

        if(cardSelected)
        {
            if(selectBtn.pointerIsIn(stageCamera) && Gdx.input.isTouched())
            {

            }

            if(backBtn.pointerIsIn(stageCamera) && Gdx.input.isTouched())
            {
                cardSelected = false;
                selectedClass = Constants.ClassType.NONE;
            }
        }
    }

    public void render(float delta)
    {
        super.render(delta);

        batch.setProjectionMatrix(stageCamera.combined);
        switch(selectedClass)
        {
            case ARCHER:
            {
                classCards[0].drawInfo(batch);
                drawButtons();
            }break;

            case KNIGHT:
            {
                classCards[1].drawInfo(batch);
                drawButtons();
            }break;

            case MAGE:
            {
                classCards[2].drawInfo(batch);
                drawButtons();
            }break;

            case NONE:
            {
                drawCards();
            }break;
        }
    }

    private void drawCards()
    {
        for (int i = 0; i < classCards.length; i++)
        {
            classCards[i].drawCard(batch);
        }
    }

    private void drawButtons()
    {
        selectBtn.draw(batch);
        backBtn.draw(batch);
    }
}
