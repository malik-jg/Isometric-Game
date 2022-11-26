package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ice extends Tile{

	public Ice(float biomeX, float biomeY) {
		this.biomeX = biomeX;
		this.biomeY = biomeY;
		texture = new Texture(Gdx.files.internal("Ice.png"));
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture,biomeX,biomeY,TILE_WIDTH,TILE_HEIGHT);		
	}

	
	public void update(float delta) {
		
	}

}
