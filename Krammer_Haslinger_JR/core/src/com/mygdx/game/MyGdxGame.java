package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;

	OrthographicCamera camera;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	float stateTime;
	TextureRegion walkFrames[] = new TextureRegion[2];
	String fs = System.getProperty("file.separator");
	Animation<TextureRegion> walkAnimation;
	TextureRegion currentFrame;
	SpriteBatch spriteBatch;
	
	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		Texture walkSheet = new Texture(Gdx.files.internal( "."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Run1.png"));
		TextureRegion[][] tmp1 = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / 1,
				walkSheet.getHeight() / 1);

		Texture walkSheet2 = new Texture(Gdx.files.internal( "."+fs + "Characters" + fs +"Roboter" + fs + "png" + fs+ "Run2.png"));
		TextureRegion[][] tmp2 = TextureRegion.split(walkSheet2,
				walkSheet2.getWidth() / 1,
				walkSheet2.getHeight() / 1);


		walkFrames[0]=tmp1[0][0];
		walkFrames[1]=tmp2[0][0];

		walkAnimation = new Animation<TextureRegion>(0.15f, walkFrames);

		spriteBatch = new SpriteBatch();
		stateTime = 0f;

//		img = new Texture("Wueste14_sehrklein.tmx");

		camera = new OrthographicCamera();
		camera.setToOrtho(false,1000,700);
		camera.update();
		tiledMap = new TmxMapLoader().load("Map/Map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);


	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
		stateTime += Gdx.graphics.getDeltaTime();

//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentFrame = walkAnimation.getKeyFrame(stateTime, true);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 10, 200, 100, 100); // Draw current frame at (8, 7)
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.A)
			camera.translate(-32,0);
		if(keycode == Input.Keys.D)
			camera.translate(32,0);
		if(keycode == Input.Keys.S)
			camera.translate(0,-32);
		if(keycode == Input.Keys.W)
			camera.translate(0,32);
		if(keycode == Input.Keys.Q)
			camera.zoom += 0.02;
		if(keycode == Input.Keys.E)
			camera.zoom -= 0.02;

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
