package com.mygdx.isometricgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Tile extends GameObject{
	
	protected static final int TILE_WIDTH = 32;
	protected static final int TILE_HEIGHT = 32;
	protected Texture texture;
	protected float biomeX;
	protected float biomeY;
	
	public Tile() {
		
		
	}
	public Rectangle getBoundsHorizontal() {
		return null;
	}

	public Rectangle getBoundsVertical() {
		return null;
	}
}
