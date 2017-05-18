package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by mikolaj on 5/17/17.
 * Pixel Dungeon
 */
public class AssetsManager implements Disposable, AssetErrorListener
{
    public static final AssetsManager instance = new AssetsManager();

    public AssetManager manager;

    public StageUI stageUI;
    public ClassCards classCards;
    public Room room;

    public Mage mage;

    private AssetsManager() {}

    public class Room
    {
        public final TextureRegion room;

        public Room(TextureAtlas atlas)
        {
            room = atlas.findRegion("room");
        }
    }

    public class Mage
    {
        public final Texture mage;
        public final Texture frontMoving;
        public final Texture backMoving;
        public final Texture sideMoving;

        public Mage()
        {
            mage = manager.get("mage/Mage.png");
            frontMoving = manager.get("mage/MageFrontMoving.png");
            backMoving = manager.get("mage/MageBackMoving.png");
            sideMoving = manager.get("mage/MageSideMoving.png");
        }
    }

    public class ClassCards
    {
        public final TextureRegion archerCard;
        public final TextureRegion archerInfo;

        public final TextureRegion knightCard;
        public final TextureRegion knightInfo;

        public final TextureRegion mageCard;
        public final TextureRegion mageInfo;

        public ClassCards(TextureAtlas atlas)
        {
            archerCard = atlas.findRegion("archerCard");
            archerInfo = atlas.findRegion("ArcherInfo");

            knightCard = atlas.findRegion("knightCard");
            knightInfo = atlas.findRegion("KnightInfo");

            mageCard = atlas.findRegion("mageCard");
            mageInfo = atlas.findRegion("MageInfo");
        }
    }

    public class StageUI
    {
        public final TextureRegion logo;
        public final TextureRegion menuBG;
        public final TextureRegion playBtn;
        public final TextureRegion playBtnHover;
        public final TextureRegion selectBtn;
        public final TextureRegion backBtn;

        public final Texture menuBgAnimaton;
        public final Texture screenTransition;

        public StageUI(TextureAtlas atlas)
        {
            logo = atlas.findRegion("PixelDungeonLogo");
            menuBG = atlas.findRegion("MenuBG");
            playBtn = atlas.findRegion("PlayBtn");
            playBtnHover = atlas.findRegion("PlayBtnHover");
            selectBtn = atlas.findRegion("SelectBtn");
            backBtn = atlas.findRegion("BackBtn");
            menuBgAnimaton = manager.get("BGAnimation.png");
            screenTransition = manager.get("ScreenTransition.png");
        }
    }

    public void load()
    {
        manager = new AssetManager();
        manager.load("ui.pack.atlas", TextureAtlas.class);
        manager.load("classCards/cards.pack.atlas", TextureAtlas.class);
        manager.load("mage/Mage.png", Texture.class);
        manager.load("mage/MageBackMoving.png", Texture.class);
        manager.load("mage/MageFrontMoving.png", Texture.class);
        manager.load("mage/MageSideMoving.png", Texture.class);
        manager.load("pixelDungeon.pack.atlas", TextureAtlas.class);
        manager.load("BGAnimation.png", Texture.class);
        manager.load("ScreenTransition.png", Texture.class);
        manager.finishLoading();

        TextureAtlas uiAtlas = manager.get("ui.pack.atlas");
        TextureAtlas cardsAtlas = manager.get("classCards/cards.pack.atlas", TextureAtlas.class);
        TextureAtlas restAtlas = manager.get("pixelDungeon.pack.atlas", TextureAtlas.class);

        stageUI = new StageUI(uiAtlas);
        classCards = new ClassCards(cardsAtlas);
        room = new Room(restAtlas);
        mage = new Mage();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error("Assets", "Couldn't load asset: " + asset.fileName + "", throwable);
    }

    @Override
    public void dispose()
    {
        manager.unload("ui.pack.atlas");
        manager.unload("classCards/cards.pack.atlas");
        manager.dispose();
    }
}
