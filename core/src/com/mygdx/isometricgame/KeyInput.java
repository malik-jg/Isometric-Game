package com.mygdx.isometricgame;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;


public class KeyInput {
	
	private Handler handler;
	
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}	
	public void update() {
		move();
	}
	public void move() {
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			handler.setUp(true);
		}
		else {
			handler.setUp(false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			handler.setLeft(true);
		}
		else {
			handler.setLeft(false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			handler.setDown(true);
		}
		else {
			handler.setDown(false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			handler.setRight(true);
		}
		else {
			handler.setRight(false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			handler.setZoomIn(true);
		}
		else {
			handler.setZoomIn(false);
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
			handler.setZoomOut(true);
		}
		else {
			handler.setZoomOut(false);
		}
		}
	}
}
