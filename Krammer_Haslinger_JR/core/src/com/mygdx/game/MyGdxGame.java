//Early-BETA
package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.concurrent.TimeUnit;

import static com.mygdx.game.Movement.DOWN;
import static com.mygdx.game.Movement.UP;
import static com.mygdx.game.ScreenEnum.GAME;


public class MyGdxGame extends Game implements InputProcessor {

	public static float SCROLLTRACKER_X;
	public static float SCROLLTRACKER_Y;
	OrthographicCamera camera;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	SpriteBatch spriteBatch;
	public static float characterSpeed = 200.0f; // 10 pixels per second.
	public static float characterX = 0; //0
	public static float characterY = 200; //200
	Player player;
	Movement mov;
	float delta;
	private TiledMap collisionMap;
	private OrthogonalTiledMapRenderer collisionMapRenderer;
	private TiledMapTileLayer collisionLayer;

	@Override
	public void create () {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen( ScreenEnum.MAIN_MENU );
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1000,700);

		//Map laden
		tiledMap = new TmxMapLoader().load("Map/Map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		//CollisionMap
		collisionMap = new TmxMapLoader().load("Map/CollisionMap_neu.tmx");
		collisionMapRenderer = new OrthogonalTiledMapRenderer(collisionMap);
		collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get("Collision");

		//Objecterzeugung
		player = new Player(collisionLayer);
		mov = new Movement();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		delta = Gdx.graphics.getDeltaTime();

		//Collision rendering
		//mit "show the map" tauschen um sichtbar zu machen
		collisionMapRenderer.setView(camera);
		collisionMapRenderer.render();

		//Show the map
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		//updates
		mov.update();
		player.update(characterSpeed);

		//rendering
		spriteBatch.begin();
		player.render(spriteBatch);
		spriteBatch.end();


		//Cam follows palyer
		//camera.position.x= characterX+500;
		//camera.translate(characterX,0);

		//Cam moves Automatically
		float scrollSpeed = 150 * delta;
		camera.translate(scrollSpeed, 0, 0);
		SCROLLTRACKER_X += scrollSpeed;
		camera.update();
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
