package com.mygdx.isometricgame;

import java.util.ArrayList;

import java.util.Collection;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class GameScreen extends ScreenAdapter{
	
	
	private final Vector2 mouseInWorld2D = new Vector2();
	private final Vector3 mouseInWorld3D = new Vector3();
	
	public static final int WIDTH = 320*4;
	public static final int HEIGHT = 180 * 4;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private IsometricRenderer renderer;
	private Player player;
	
	private BitmapFont font = new BitmapFont();
		
	private Handler handler;
	private KeyInput keyInput;
	private Camera cameraC;
	
	
	private AssetManager assetsManager;
	private TextureAtlas textureAtlas;
	private TextureRegion textureRegion;
	
	
	

	
	public GameScreen(SpriteBatch batch){
		this.batch = batch;
		renderer = new IsometricRenderer();
		handler = new Handler();
		keyInput = new KeyInput(handler);
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		cameraC = new Camera(handler,camera, renderer);
		
		
		assetsManager = new AssetManager();
		assetsManager.load("pack.atlas", TextureAtlas.class);
		assetsManager.finishLoading();
		
		
		textureAtlas = assetsManager.get("pack.atlas");
		
	}
	@Override
	public void show(){
		camera.position.set(0 ,0,10);
		player = new Player(handler);	
	}	
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		mouseInWorld3D.x = Gdx.input.getX();
		mouseInWorld3D.y = Gdx.input.getY();
		mouseInWorld3D.z = 0;
		camera.unproject(mouseInWorld3D);
		mouseInWorld2D.x = mouseInWorld3D.x;
		mouseInWorld2D.y = mouseInWorld3D.y;
		
		
		//handleInput(delta);
		cameraC.update(delta);
		camera.update();
		
		
		batch.begin();
		
		//renderer.drawGround(batch);

		//renderer.createWorld(batch, textureAtlas, textureRegion);
		renderer.createVoronoiMap(batch,textureAtlas,textureRegion);
		player.render(batch);
		player.update(delta);
		
		keyInput.update();
		
		
		font.draw(batch, mouseInWorld2D.x + " " + mouseInWorld2D.y, 0, 0);
		font.draw(batch, "FPS = " + Gdx.graphics.getFramesPerSecond(), 0 + 100, 0 + 100);
		font.draw(batch, "FPS = " + Gdx.graphics.getDeltaTime(), 0 + 200, 0 + 200);
		batch.end();
	}
	@Override
	public void dispose(){
		
	}
	
}
