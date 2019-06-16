package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import static com.mygdx.game.Movement.*;
import static com.mygdx.game.MyGdxGame.*;

/**
 * Created by Lukas on 29.04.2019.
 */
public class Player {

    private Texture idle, run1, run2, jump1,jump2,jump3,jump4,jump5;
    String fs = System.getProperty("file.separator");
    TextureRegion walkFrames[] = new TextureRegion[2];
    TextureRegion idleFrame[] = new TextureRegion[1];
    TextureRegion jumpFrames[] = new TextureRegion[5];
    Animation<TextureRegion> walkAnimation, idleAnimation, jumpAnimation;
    TextureRegion currentFrame;
    float stateTime;
    float temp;
    private TiledMapTileLayer collisionLayer;

    public Player(TiledMapTileLayer collisionLayer) {

        this.collisionLayer = collisionLayer;
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
        if(JUMP) {
            temp = characterY;
            characterY = temp + 10;
        }



        checkCollisionMap();

    }



    public void render (SpriteBatch sb){

        stateTime += Gdx.graphics.getDeltaTime();
        if(!UP && !DOWN &&!RIGHT && !LEFT && !JUMP) {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);

        }else if(JUMP) {
            currentFrame = jumpAnimation.getKeyFrame(stateTime, true);

        }else {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);


        }

        sb.draw(currentFrame, characterX, characterY, 80, 80);
    }

    public void loadPlayerTextures(){

        idle = new Texture(Gdx.files.internal( "."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Idle1.png"));
        run1 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "run1.png");
        run2 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "run2.png");
        jump1 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Jump1.png");
        jump2 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Jump2.png");
        jump3 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Jump3.png");
        jump4 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Jump4.png");
        jump5 = new Texture("."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Jump5.png");

        TextureRegion[][] t_run1 = TextureRegion.split(run1,run1.getWidth(),run1.getHeight());
        TextureRegion[][] t_run2 = TextureRegion.split(run2, run2.getWidth(),	run2.getHeight());
        TextureRegion[][] t_idle1 = TextureRegion.split(idle, idle.getWidth(),	idle.getHeight());
        TextureRegion[][] t_jump1 = TextureRegion.split(jump1, jump1.getWidth(),	jump1.getHeight());
        TextureRegion[][] t_jump2 = TextureRegion.split(jump2, jump2.getWidth(),	jump2.getHeight());
        TextureRegion[][] t_jump3 = TextureRegion.split(jump3, jump3.getWidth(),	jump3.getHeight());
        TextureRegion[][] t_jump4 = TextureRegion.split(jump4, jump4.getWidth(),	jump4.getHeight());
        TextureRegion[][] t_jump5 = TextureRegion.split(jump5, jump5.getWidth(),	jump5.getHeight());

        walkFrames[0]=t_run1[0][0];
        walkFrames[1]=t_run2[0][0];
        idleFrame[0]= t_idle1[0][0];
        jumpFrames[0] = t_jump1[0][0];
        jumpFrames[1] = t_jump2[0][0];
        jumpFrames[2] = t_jump3[0][0];
        jumpFrames[3] = t_jump4[0][0];
        jumpFrames[4] = t_jump5[0][0];

        walkAnimation = new Animation<TextureRegion>(0.15f, walkFrames);
        idleAnimation = new Animation<TextureRegion>(0.15f, idleFrame);
        jumpAnimation = new Animation<TextureRegion>(0.15f, jumpFrames);
        stateTime = 0f;
    }
    public void checkCollisionMap(){
        float xWorld = characterX + SCROLLTRACKER_X;; //characterX+ SCROLLTRACKER_X;
        float yWorld = characterY; //characterY+ SCROLLTRACKER_Y;

        //Check For Collision
        boolean collisionWithMap = false;
        // check right side middle
        collisionWithMap = isCellBLocked(xWorld, yWorld);

        //React to Collision
        if (collisionWithMap) {
            System.out.println("player-map collision!!!");
        }
    }

    public boolean isCellBLocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(
                (int) (x / collisionLayer.getTileWidth()),
                (int) (y / collisionLayer.getTileHeight()));

        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

}


