package com.mygdx.isometricgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.Game;

public class IsometricGame extends Game {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}
