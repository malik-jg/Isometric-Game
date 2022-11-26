package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Player extends Entity{
	
	//private Items[] inventory;
	
	public Player(Handler handler){
		super(handler);
		x = -10;
		y = -48;;	
		health();
		speedMultiplier = 125;
		image = new Texture(Gdx.files.internal("pixel_front.png"));
		pos = new Vector2(x,y);
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y);
	}
	@Override
	public void update(float delta) {
		movePlayer(delta);
	}
	
	public void movePlayer(float delta) {
		if(handler.isUp()){
			speedMultiplier = 125;
			pos.y += delta *speedMultiplier;
        }
        else if(!handler.isDown()){
        	speedMultiplier = 0;
        	pos.y += delta *speedMultiplier;
        }
		if(handler.isLeft()){
			speedMultiplier = 125;
			pos.x -= delta *speedMultiplier;
        }
        else if(!handler.isRight()){
        	speedMultiplier = 0;
        	pos.x -= delta *speedMultiplier;
        }
        if(handler.isDown()){
        	speedMultiplier = 125;
        	pos.y -= delta *speedMultiplier;
        }
        else if(!handler.isUp()){
        	speedMultiplier = 0;
        	pos.y -= delta *speedMultiplier;
        }
		if(handler.isRight()){
			speedMultiplier = 125;
			pos.x += delta *speedMultiplier; 
        }
        else if(!handler.isLeft()){
        	speedMultiplier = 0;
        	pos.x += delta *speedMultiplier; 
        }
	}
	
	public void health() {
		
	}
	
	public Rectangle getBoundsHorizontal() {
		//float bx = x + delta * speedMultiplier;
        float by = y;
       // float bw = w + (delta * speedMultiplier) / 2;
        float bh = h;
        //return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
        return null;
	}
	public Rectangle getBoundsVertical() {
		float bx = x;
       // float by = y + delta * speedMultiplier;
        float bw = w;
      //  float bh = h + (delta * speedMultiplier) / 2;
       // return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
		return null;
	}

}
