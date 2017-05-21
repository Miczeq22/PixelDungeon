package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public Knight knight;

    public Sounds sounds;

    private AssetsManager() {}

    public class Sounds
    {
        public Music campfire;
        public Sound click;
        public Music knightAttack;
        public Music mageShoot;
        public Music walking;
        public Music dungeonTheme;

        public Sound cardSelected;
        public Music selectionTheme;

        public Sounds()
        {
            campfire = Gdx.audio.newMusic(Gdx.files.internal("sounds/campfire.ogg"));
            click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.ogg"));
            knightAttack = Gdx.audio.newMusic(Gdx.files.internal("sounds/knightAttack.ogg"));
            mageShoot = Gdx.audio.newMusic(Gdx.files.internal("sounds/mageShoot.ogg"));
            walking = Gdx.audio.newMusic(Gdx.files.internal("sounds/walk.ogg"));
            cardSelected = Gdx.audio.newSound(Gdx.files.internal("sounds/cardSelected.ogg"));

            dungeonTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/dungeonTheme.ogg"));
            selectionTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/selectionTheme.mp3"));
        }
    }

    public class Room
    {
        public final TextureRegion room;
        public final TextureRegion doorOpen;
        public final TextureRegion doorClosedVertical;
        public final TextureRegion doorClosedHorizontal;

        public Room(TextureAtlas atlas)
        {
            room = atlas.findRegion("room");
            doorOpen = atlas.findRegion("doorOpen");
            doorClosedVertical = atlas.findRegion("doorClosed");
            doorClosedHorizontal = atlas.findRegion("doorClosedR");
        }
    }

    public class Knight
    {
        public final Texture knight;

        public final Texture frontMoving;
        public final Texture backMoving;
        public final Texture sideMoving;

        public final Texture frontAttack;
        public final Texture backAttack;
        public final Texture sideAttack;

        public Knight()
        {
            knight = manager.get("knight/Knight.png");

            frontMoving = manager.get("knight/KnightFrontMoving.png");
            backMoving= manager.get("knight/KnightBackMoving.png");
            sideMoving = manager.get("knight/KnightSideMoving.png");

            frontAttack = manager.get("knight/KnightFrontAttack.png");
            backAttack = manager.get("knight/KnightBackAttack.png");
            sideAttack = manager.get("knight/KnightSideAttack.png");
        }
    }

    public class Mage
    {
        public final Texture mage;
        public final Texture frontMoving;
        public final Texture backMoving;
        public final Texture sideMoving;

        public final Texture frontAttack;
        public final Texture backAttack;
        public final Texture sideAttack;

        public Mage()
        {
            mage = manager.get("mage/Mage.png");

            frontMoving = manager.get("mage/MageFrontMoving.png");
            backMoving = manager.get("mage/MageBackMoving.png");
            sideMoving = manager.get("mage/MageSideMoving.png");

            frontAttack = manager.get("mage/MageFrontAttack.png");
            backAttack = manager.get("mage/MageBackAttack.png");
            sideAttack = manager.get("mage/MageSideAttack.png");
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
        manager.load("mage/MageSideAttack.png", Texture.class);
        manager.load("mage/MageFrontAttack.png", Texture.class);
        manager.load("mage/MageBackAttack.png", Texture.class);

        manager.load("knight/Knight.png", Texture.class);
        manager.load("knight/KnightBackMoving.png", Texture.class);
        manager.load("knight/KnightFrontMoving.png", Texture.class);
        manager.load("knight/KnightSideMoving.png", Texture.class);
        manager.load("knight/KnightBackAttack.png", Texture.class);
        manager.load("knight/KnightFrontAttack.png", Texture.class);
        manager.load("knight/KnightSideAttack.png", Texture.class);

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
        knight = new Knight();

        sounds = new Sounds();
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

        manager.unload("mage/Mage.png");
        manager.unload("mage/MageBackMoving.png");
        manager.unload("mage/MageFrontMoving.png");
        manager.unload("mage/MageSideMoving.png");
        manager.unload("mage/MageSideAttack.png");
        manager.unload("mage/MageFrontAttack.png");
        manager.unload("mage/MageBackAttack.png");
        manager.unload("pixelDungeon.pack.atlas");

        manager.unload("knight/Knight.png");
        manager.unload("knight/KnightBackMoving.png");
        manager.unload("knight/KnightFrontMoving.png");
        manager.unload("knight/KnightSideMoving.png");
        manager.unload("knight/KnightBackAttack.png");
        manager.unload("knight/KnightFrontAttack.png");
        manager.unload("knight/KnightSideAttack.png");

        manager.unload("BGAnimation.png");
        manager.unload("ScreenTransition.png");

        manager.dispose();
    }
}
