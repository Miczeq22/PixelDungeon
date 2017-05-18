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

    private AssetsManager() {}

    public class StageUI
    {
        public final TextureRegion logo;
        public final TextureRegion menuBG;
        public final TextureRegion playBtn;
        public final TextureRegion playBtnHover;

        public StageUI(TextureAtlas atlas)
        {
            logo = atlas.findRegion("PixelDungeonLogo");
            menuBG = atlas.findRegion("MenuBG");
            playBtn = atlas.findRegion("PlayBtn");
            playBtnHover = atlas.findRegion("PlayBtnHover");
        }
    }

    public void load()
    {
        manager = new AssetManager();
        manager.load("ui.pack.atlas", TextureAtlas.class);
        manager.load("classCards/cards.pack.atlas", TextureAtlas.class);
        manager.load("classCards/ArcherInfo.png", Texture.class);
        manager.load("classCards/KnightInfo.png", Texture.class);
        manager.load("classCards/MageInfo.png", Texture.class);
        manager.load("knight.pack.atlas", TextureAtlas.class);
        manager.load("mage.pack.atlas", TextureAtlas.class);
        manager.finishLoading();

        TextureAtlas atlas = manager.get("ui.pack.atlas");

        stageUI = new StageUI(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable)
    {
        Gdx.app.error("Assets", "Couldn't load asset: " + asset.fileName + "", throwable);
    }

    @Override
    public void dispose()
    {
        manager.unload("ui/PixelDungeonLogo.png");
        manager.dispose();
    }
}
