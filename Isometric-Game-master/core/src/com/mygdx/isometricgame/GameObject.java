package com.mygdx.isometricgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject{
		
	protected float xPositionComponent;
	protected float yPositionComponent;
	protected int w;
	protected int h;
	protected int speedMultiplier;
	protected Texture image;
	
	protected Vector2 pos;
	protected Vector2 vel;
	protected Vector2 acc;

	
	protected Handler handler;
	protected OrthographicCamera camera;
	protected IsometricRenderer renderer;
	
	public GameObject() {
		
	}
	public GameObject(Handler handler) {
		this.handler = handler;
	}
	
	public GameObject(Handler handler, OrthographicCamera camera, IsometricRenderer renderer) {
		this.handler = handler;
		this.camera = camera;
		this.renderer = renderer;
		
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta);
	public abstract Rectangle getBoundsHorizontal();
    public abstract Rectangle getBoundsVertical();
    
	public float getXPositionComponent() {
		return xPositionComponent;
	}
	public void setXPositionComponent(float xPositionComponent) {
		this.xPositionComponent = xPositionComponent;
	}
	public float getYPositionComponent() {
		return yPositionComponent;
	}
	public void setYPositionComponent(float yPositionComponent) {
		this.yPositionComponent = yPositionComponent;
	}
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSpeedMultiplier() {
		return speedMultiplier;
	}
	public void setSpeedMultiplier(int speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(float x, float y) {
		if(this instanceof Camera) {
			this.camera.position.set(x, y, 10);
		}
		else {
			this.pos.x = x;
			this.pos.y = y;
		}
	}
	
	
	
	
}
