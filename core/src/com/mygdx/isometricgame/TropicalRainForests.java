package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TropicalRainForests extends Tile {

	public TropicalRainForests(float biomeX, float biomeY) {
		this.biomeX = biomeX;
		this.biomeY = biomeY;
		texture = new Texture(Gdx.files.internal("TropicalRainForests.png"));
	}
	public void render(SpriteBatch batch) {
		batch.draw(texture,biomeX,biomeY,TILE_WIDTH,TILE_HEIGHT);
	}
	public void update(float delta) {
		
	}
}
