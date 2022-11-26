package com.mygdx.isometricgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Camera extends GameObject{
	
	private int zoomMultiplier;
	
	public Camera(Handler handler, OrthographicCamera camera, IsometricRenderer renderer) {
		super(handler, camera, renderer);
		x = 0;
		y = 0;
		speedMultiplier = 125;
		zoomMultiplier = 1;
	}
	
	public void render(SpriteBatch batch) {
		
	}

	public void update(float delta) {
		moveCamera(delta);
	}
	
	public void moveCamera(float delta) {
		if(handler.isUp()){
			speedMultiplier = 125;
			camera.translate(0, delta * speedMultiplier, 0);
			renderer.setRenderY(renderer.getRenderY() + delta * speedMultiplier);
			
        }
        else if(!handler.isDown()){
        	speedMultiplier = 0;
        	camera.translate(0, delta * speedMultiplier, 0);
			renderer.setRenderY(renderer.getRenderY() + delta * speedMultiplier);

        }
		if(handler.isLeft()){
			speedMultiplier = 125;
			camera.translate(-delta * speedMultiplier, 0, 0);
			renderer.setRenderX(renderer.getRenderX() - delta * speedMultiplier);
        }
        else if(!handler.isRight()){
        	speedMultiplier = 0;
        	camera.translate(-delta * speedMultiplier, 0, 0);
			renderer.setRenderX(renderer.getRenderX() - delta * speedMultiplier);

        }
        if(handler.isDown()){
        	speedMultiplier = 125;
        	camera.translate(0, -delta * speedMultiplier, 0);
        	renderer.setRenderY(renderer.getRenderY() - delta * speedMultiplier);
        }
        else if(!handler.isUp()){
        	speedMultiplier = 0;
        	camera.translate(0, -delta * speedMultiplier, 0);
        	renderer.setRenderY(renderer.getRenderY() - delta * speedMultiplier);

        }
		if(handler.isRight()){
			speedMultiplier = 125;
			camera.translate(delta * speedMultiplier, 0, 0);
			renderer.setRenderX(renderer.getRenderX() + delta * speedMultiplier);
        }
        else if(!handler.isLeft()){
        	speedMultiplier = 0;
        	camera.translate(delta * speedMultiplier, 0, 0);
			renderer.setRenderX(renderer.getRenderX() + delta * speedMultiplier);

        }
		if(handler.isZoomIn()) {
			zoomMultiplier = 1;
			camera.zoom -= delta * zoomMultiplier;
		}
		else if(!handler.isZoomOut()) {
			zoomMultiplier = 0;
			camera.zoom -= delta * zoomMultiplier;	
		}
		if(handler.isZoomOut()) {
			zoomMultiplier = 1;
			camera.zoom += delta * zoomMultiplier;
		}
		else if(!handler.isZoomIn()) {
			zoomMultiplier = 0;
			camera.zoom += delta * zoomMultiplier;
		}
	}


	
	public Rectangle getBoundsHorizontal() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Rectangle getBoundsVertical() {
		// TODO Auto-generated method stub
		return null;
	}
}
