package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class MouseInput {
	private Handler handler;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	public void update() {
		justClicked();
	}
	
	public void justClicked() {
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			handler.leftClick(true);
		}
		else if(!Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			handler.leftClick(false);
		}
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			handler.rightClick(true);
		}
		else if(!Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
			handler.rightClick(false);
		}
	}
}
