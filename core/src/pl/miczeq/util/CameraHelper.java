package pl.miczeq.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by mikolaj on 5/18/17.
 * Pixel Dungeon
 */
public class CameraHelper
{
    private OrthographicCamera camera;
    private float camZoomSpeed;
    private final float camZoomSpeedAccelerator = 5.0f;

    public CameraHelper(OrthographicCamera camera)
    {
        this.camera = camera;
    }

    public void handleInput(float delta)
    {
        camZoomSpeed = 1.0f * delta;

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
        {
            camZoomSpeed *= camZoomSpeedAccelerator;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.COMMA))
        {
            camera.zoom = MathUtils.clamp(camera.zoom - camZoomSpeed, Constants.CAMERA_MAX_ZOOM_IN, Constants.CAMERA_MAX_ZOOM_OUT);
            camera.update();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.PERIOD))
        {
            camera.zoom = MathUtils.clamp(camera.zoom + camZoomSpeed, Constants.CAMERA_MAX_ZOOM_IN, Constants.CAMERA_MAX_ZOOM_OUT);
            camera.update();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SLASH))
        {
            camera.zoom = 1.0f;
            camera.update();
        }
    }
}
