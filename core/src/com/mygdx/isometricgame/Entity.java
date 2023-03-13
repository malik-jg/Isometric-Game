package com.mygdx.isometricgame;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity extends GameObject{

	
	
	protected int heatlh;
	
	protected Vector2 aBottom;
	protected Vector2 bBottom;
	protected Vector2 cBottom;
	protected Vector2 dBottom;
	protected Vector2 aMid;
	protected Vector2 bMid;
	protected Vector2 cMid;
	protected Vector2 dMid;
	
	
	public Entity(Handler handler) {
		super(handler);
	}	
}
