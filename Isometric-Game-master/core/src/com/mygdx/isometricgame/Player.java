package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;





import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;




public class Player extends Entity{
	
	private BitmapFont font = new BitmapFont();
	
	
	
	private float xVelocityComponent = 0;
	private float yVelocityComponent = 0;
	private float xAccelerationComponent = 0;
	private float yAccelerationComponent = 0;
	
	
	private final float max_velocity = 400;
	
	
	public Player(Handler handler){
		super(handler);
		
		xPositionComponent = -16;
		yPositionComponent = 8;	
		
		xVelocityComponent = 0;
		
		yVelocityComponent = 50;
		
		xAccelerationComponent = 50;
		
		
		yAccelerationComponent = 0;
		
		
		speedMultiplier = 125;
		
		
		image = new Texture(Gdx.files.internal("character.png"));
		pos = new Vector2(xPositionComponent,yPositionComponent);
		vel = new Vector2(xVelocityComponent,yVelocityComponent);
		acc = new Vector2(xAccelerationComponent,yAccelerationComponent);

	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y);
		
		
		font.draw(batch, "(PX,PY): (" + pos.x + " , " + pos.y + ")", pos.x -100, pos.y + 100);
		font.draw(batch, "(VX,VY): (" + vel.x + " , " + yVelocityComponent + ")", pos.x -100, pos.y + 200);
		font.draw(batch, "(AX,AY): (" + acc.x + " , " + yAccelerationComponent + ")", pos.x -100, pos.y + 400);
		
	}
	@Override
	public void update(float delta) {
		movePlayer(delta);
	}
	
	public void movePlayer(float delta) {	
		
		if(handler.isLeft() && !handler.isRight()){
			if(vel.x <= 0) {
				if(Math.abs(vel.x) < max_velocity) {
					vel.x -= acc.x;	
				}
				else if(Math.abs(vel.x) > max_velocity){
					vel.x = -max_velocity;
				}
			}
			else if(vel.x > 0) {
				vel.x -= acc.x;
			}		
			pos.x += vel.x * delta;	
        }	
		if(handler.isRight() && !handler.isLeft()){
			if(vel.x >= 0) {
				if(Math.abs(vel.x) < max_velocity) {
					vel.x += acc.x;
				}
				else if(Math.abs(vel.x)> max_velocity) {
					vel.x = max_velocity;
				}
			}
			else if(vel.x < 0) {
				vel.x += acc.x;
			}		
			pos.x += vel.x * delta;
			
        }
		if(!handler.isLeft() && !handler.isRight()) {
			if(vel.x!=0) {
				if(vel.x > 0) {
					vel.x -= acc.x;
				}
				else if(vel.x < 0) {
					vel.x += acc.x;
				}
			}
			pos.x +=vel.x * delta;
		}	
		if(handler.isLeft() && handler.isRight()) {
			vel.x = 0;
		}
		
		
		
		if(handler.isUp()){
			speedMultiplier = 125;
			pos.y += delta *speedMultiplier;		
        }
        else if(!handler.isDown()){
        	speedMultiplier = 0;
        	pos.y += delta *speedMultiplier;	
        }
		if(handler.isDown()){
	        speedMultiplier = 125;
	        pos.y -= delta *speedMultiplier;	
	    }
	    else if(!handler.isUp()){
	        speedMultiplier = 0;
	        pos.y -= delta *speedMultiplier;	
	    }
	}
	public void actions() {
		
	}
	public void health() {
		
	}
	
	public Rectangle getBoundsHorizontal() {
		//float bx = x + delta * speedMultiplier;
        float by = yPositionComponent;
       // float bw = w + (delta * speedMultiplier) / 2;
        float bh = h;
        //return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
        return null;
	}
	public Rectangle getBoundsVertical() {
		float bx = xPositionComponent;
       // float by = y + delta * speedMultiplier;
        float bw = w;
      //  float bh = h + (delta * speedMultiplier) / 2;
       // return new Rectangle((int)bx, (int)by, (int)bw, (int)bh);
		return null;
	}

}
