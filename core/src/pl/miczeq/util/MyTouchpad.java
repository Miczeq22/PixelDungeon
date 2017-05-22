package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by mikolaj on 5/22/17.
 * Pixel Dungeon
 */
public class MyTouchpad
{
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle style;
    private Skin skin;
    private Drawable bg;
    private Drawable knob;

    public MyTouchpad()
    {
        init();
    }

    private void init()
    {
        skin = new Skin();
        skin.add("bg", new Texture(Gdx.files.internal("touchBackground.png")));
        skin.add("knob", new Texture(Gdx.files.internal("touchKnob.png")));
        style = new Touchpad.TouchpadStyle();
        bg = skin.getDrawable("bg");
        knob = skin.getDrawable("knob");
        style.background = bg;
        style.knob = knob;
        touchpad = new Touchpad(10, style);
        touchpad.setBounds(10.0f, 10.0f, 150.0f, 150.0f);
    }

    public Touchpad getTouchpad()
    {
        return touchpad;
    }
}
