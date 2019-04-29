package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

	OrthographicCamera camera;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	String fs = System.getProperty("file.separator");
	SpriteBatch spriteBatch;
	public static float characterSpeed = 200.0f; // 10 pixels per second.
	public static float characterX = 0;
	public static float characterY = 200;
	Player player;
	Movement mov;

	@Override
	public void create () {

		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1000,700);

		//Map laden
		tiledMap = new TmxMapLoader().load("Map/Map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		//Objecterzeugung
		player = new Player();
		mov = new Movement();

	}

	@Override
	public void render () {

//		if(Gdx.input.isKeyPressed(Input.Keys.A))
//			characterX -= Gdx.graphics.getDeltaTime() * characterSpeed;
//		if(Gdx.input.isKeyPressed(Input.Keys.D))
//			characterX += Gdx.graphics.getDeltaTime() * characterSpeed;
//		if(Gdx.input.isKeyPressed(Input.Keys.W))
//			characterY += Gdx.graphics.getDeltaTime() * characterSpeed;
//		if(Gdx.input.isKeyPressed(Input.Keys.S))
//			characterY -= Gdx.graphics.getDeltaTime() * characterSpeed;
//		// Jumping
//		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//			characterY +=Gdx.graphics.getDeltaTime() + 7;
//		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Show the map
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		//updates
		mov.update();
		player.update(characterSpeed);

		//rendering
		spriteBatch.begin();
		player.render(spriteBatch);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
//		batch.dispose();
//		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

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
