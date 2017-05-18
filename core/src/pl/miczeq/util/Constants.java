package pl.miczeq.util;

/**
 * Created by mikolaj on 5/17/17.
 * Pixel Dungeon
 */
public class Constants
{
    public static final float VIEWPORT_WIDTH = 55.0f;
    public static final float VIEWPORT_HEIGHT = 40.0f;

    public static final float WORLD_HEIGHT = 35.0f;

    public static final float PLAYER_WIDTH = 2.5f;
    public static final float PLAYER_HEIGHT = 3.0f;

    public static final float STAGE_WIDTH = 820.0f;
    public static final float STAGE_HEIGHT = 480.0f;

    public static final float CLASS_CARD_WIDTH = 245.0f;
    public static final float CLASS_CARD_HEIGHT = STAGE_HEIGHT - 40.0f;

    public static final float CLASS_CARD_Y = STAGE_HEIGHT / 2.0f - CLASS_CARD_HEIGHT / 2.0f;

    public static final float FRICTION = 0.92f;

    public static final float MAGE_MOVING_ANIMATION_SPEED = 0.05f;

    public static final float WALL_WIDTH = 4.5f;

    public static final float CAMERA_MAX_ZOOM_IN = 0.25f;
    public static final float CAMERA_MAX_ZOOM_OUT = 10.0f;

    public static final float DOOR_WIDTH = 3.0f;

    public enum ClassType
    {
        ARCHER,
        KNIGHT,
        MAGE,
        NONE
    }
}
