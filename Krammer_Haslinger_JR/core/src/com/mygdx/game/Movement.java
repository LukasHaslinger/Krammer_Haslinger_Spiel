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
    public static boolean JUMP;

    public void update(){

        UP = false;
        DOWN= false;
        LEFT = false;
        RIGHT = false;
        JUMP = false;
        // set boolean to true if key is touched
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            LEFT = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
           DOWN = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            RIGHT = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
           UP = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            JUMP = true;
        }

    }

}
