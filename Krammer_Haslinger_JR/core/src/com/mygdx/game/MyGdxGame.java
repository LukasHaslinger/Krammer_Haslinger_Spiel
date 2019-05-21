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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import static com.mygdx.game.Movement.DOWN;
import static com.mygdx.game.Movement.UP;

public class MyGdxGame extends ApplicationAdapter implements InputProcessor {

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

		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1000,700);

		//Map laden
		tiledMap = new TmxMapLoader().load("Map/Map1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		//CollisionMap
		collisionMap = new TmxMapLoader().load("Map/CollisionMap1.tmx");
		collisionMapRenderer = new OrthogonalTiledMapRenderer(collisionMap);
		collisionLayer = (TiledMapTileLayer) collisionMap.getLayers().get("collision");

		//Objecterzeugung
		player = new Player(collisionLayer);
		mov = new Movement();

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		delta = Gdx.graphics.getDeltaTime();

		//Show the map
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
//		collisionMapRenderer.setView(camera);
//		collisionMapRenderer.render();

		//updates
		mov.update();
		player.update(characterSpeed);

		//rendering
		spriteBatch.begin();
		player.render(spriteBatch);
		spriteBatch.end();

		//Collision rendering
		collisionMapRenderer.setView(camera);
		collisionMapRenderer.render();

		//Cam moves automatically
		//camera.translate(100 * delta,0,0);
		//camera.update();


		//Cam follows palyer
		camera.position.x = /*Gdx.graphics.getDeltaTime() * characterSpeed +*/ characterX+500; //characterX+500
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
