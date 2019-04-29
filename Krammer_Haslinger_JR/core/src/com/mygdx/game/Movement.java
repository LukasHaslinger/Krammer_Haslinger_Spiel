package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


/**
 * Created by Lukas on 29.04.2019.
 */

public class Movement {


    public static boolean UP;
    public static boolean DOWN;
    public static boolean LEFT;
    public static boolean RIGHT;

    public void update(){

        UP = false;
        DOWN= false;
        LEFT = false;
        RIGHT = false;

        // set boolean to true if key is touched
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            LEFT = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            DOWN = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            RIGHT = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            UP = true;
        }

    }

}
