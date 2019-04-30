package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Movement.*;
import static com.mygdx.game.MyGdxGame.characterX;
import static com.mygdx.game.MyGdxGame.characterY;

/**
 * Created by Lukas on 29.04.2019.
 */
public class Player {

    private Texture idle, run1, run2;
    String fs = System.getProperty("file.separator");
    TextureRegion walkFrames[] = new TextureRegion[2];
    Animation<TextureRegion> walkAnimation;
    TextureRegion currentFrame;
    float stateTime;

    public Player() {
        loadPlayerTextures();
    }

    public void update (float characterSpeed){

        if(LEFT)
			characterX -= Gdx.graphics.getDeltaTime() * characterSpeed; //delta
		if(RIGHT)
			characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
		if(UP)
			characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
		if(DOWN)
			characterY -= Gdx.graphics.getDeltaTime() * characterSpeed;

    }

    public void render (SpriteBatch sb){

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);

        sb.draw(currentFrame, characterX, characterY, 80, 80);
    }

    public void loadPlayerTextures(){

        idle = new Texture(Gdx.files.internal( "."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Idle1.png"));
        run1 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "run1.png");
        run2 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "run2.png");

        TextureRegion[][] tmp1 = TextureRegion.split(run1,run1.getWidth(),run1.getHeight());
        TextureRegion[][] tmp2 = TextureRegion.split(run2, run2.getWidth(),	run2.getHeight());

        walkFrames[0]=tmp1[0][0];
        walkFrames[1]=tmp2[0][0];

        walkAnimation = new Animation<TextureRegion>(0.15f, walkFrames);
        stateTime = 0f;
    }
}


