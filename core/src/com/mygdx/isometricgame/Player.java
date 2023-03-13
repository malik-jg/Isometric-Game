package com.mygdx.isometricgame;

import com.badlogic.gdx.Gdx;




import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;




public class Player extends Entity{
	
	private BitmapFont font = new BitmapFont();
	//private Items[] inventory;
	
	public Player(Handler handler){
		super(handler);
		x = -16;
		y = 8;	
		speedMultiplier = 125;
		image = new Texture(Gdx.files.internal("pixel_front.png"));
		//pos = new Vector2(x,y);
		//arrayPos = new Vector2((int)(pos.x / 32),(int)(pos.y / 32));
		
		aBottom = new Vector2(x,y);
		bBottom = new Vector2(x + 16,y + 8);
		cBottom = new Vector2(x + 32,y);
		dBottom = new Vector2(x + 16,y - 8);
		aMid = new Vector2(x,y + 16);
		bMid = new Vector2(x + 16,y + 24);
		cMid = new Vector2(x + 32,y + 16);
		dMid = new Vector2(x + 16,y + 8);
		
		pos = aBottom;
		
		// inventory = new Items[]
		
		
		//arrayPos = new Vector2((int)(pos.x / 32),(int)(pos.y / 32));
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y);
		
		
		font.draw(batch, "(PX,PY): (" + pos.x + " , " + pos.y + ")", pos.x -100, pos.y + 100);
		
		
		font.draw(batch, "a: (" + aBottom.x + " , " + aBottom.y + ")", aBottom.x + 100, aBottom.y + 100);
		font.draw(batch, "b: (" + bBottom.x + " , " + bBottom.y + ")", bBottom.x + 300, bBottom.y + 100);
		font.draw(batch, "c: (" + cBottom.x + " , " + cBottom.y + ")", cBottom.x + 100, cBottom.y + 300);
		font.draw(batch, "d: (" + dBottom.x + " , " + dBottom.y + ")", dBottom.x + 300, dBottom.y + 300);
		
	}
	@Override
	public void update(float delta) {
		if(!collision()) {
			movePlayer(delta);
		}
	}
	
	public boolean collision() {
		return false;
	}
	
	public void movePlayer(float delta) {	
		if(handler.isUp()){
			speedMultiplier = 125;
			//pos.y += delta *speedMultiplier;
			
			aBottom.y += delta *speedMultiplier;
			bBottom.y += delta *speedMultiplier;
			cBottom.y += delta *speedMultiplier;
			dBottom.y += delta *speedMultiplier;
			
			
			
			
			//arrayPos.y = ((int)(pos.y / 32));
        }
        else if(!handler.isDown()){
        	speedMultiplier = 0;
        	//pos.y += delta *speedMultiplier;
        	
        	aBottom.y += delta *speedMultiplier;
			bBottom.y += delta *speedMultiplier;
			cBottom.y += delta *speedMultiplier;
			dBottom.y += delta *speedMultiplier;
			
        	
        	
        	//arrayPos.y =  (int)(pos.y / 32);
        }
		if(handler.isLeft()){
			speedMultiplier = 125;
			//pos.x -= delta *speedMultiplier;
			
			aBottom.x -= delta *speedMultiplier;
			bBottom.x -= delta *speedMultiplier;
			cBottom.x -= delta *speedMultiplier;
			dBottom.x -= delta *speedMultiplier;
			
			
			//arrayPos.x = (int)(pos.x / 32);
        }
        else if(!handler.isRight()){
        	speedMultiplier = 0;
        	//pos.x -= delta *speedMultiplier;
        	
        	aBottom.x -= delta *speedMultiplier;
			bBottom.x -= delta *speedMultiplier;
			cBottom.x -= delta *speedMultiplier;
			dBottom.x -= delta *speedMultiplier;
			
			
			
			
        	//arrayPos.x = (int)(pos.x / 32);
        }
        if(handler.isDown()){
        	speedMultiplier = 125;
        	//pos.y -= delta *speedMultiplier;
        	
        	
        	aBottom.y -= delta *speedMultiplier;
			bBottom.y -= delta *speedMultiplier;
			cBottom.y -= delta *speedMultiplier;
			dBottom.y -= delta *speedMultiplier;
			
        	
        	//arrayPos.y = (int)(pos.y / 32);
        }
        else if(!handler.isUp()){
        	speedMultiplier = 0;
        	//pos.y -= delta *speedMultiplier;
        	
        	aBottom.y -= delta *speedMultiplier;
			bBottom.y -= delta *speedMultiplier;
			cBottom.y -= delta *speedMultiplier;
			dBottom.y -= delta *speedMultiplier;
			
        	
        	
        	//arrayPos.y = (int)(pos.y / 32);
        }
		if(handler.isRight()){
			speedMultiplier = 125;
			//pos.x += delta *speedMultiplier; 
			
			aBottom.x += delta *speedMultiplier;
			bBottom.x += delta *speedMultiplier;
			cBottom.x += delta *speedMultiplier;
			dBottom.x += delta *speedMultiplier;
			
			
			
			//arrayPos.x = (int)(pos.x / 32);
        }
        else if(!handler.isLeft()){
        	speedMultiplier = 0;
        	//pos.x += delta *speedMultiplier; 
        	
        	aBottom.x += delta *speedMultiplier;
			bBottom.x += delta *speedMultiplier;
			cBottom.x += delta *speedMultiplier;
			dBottom.x += delta *speedMultiplier;
			
        	
        	//arrayPos.x = (int)(pos.x / 32);
        }
	}
	public void actions() {
		
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
