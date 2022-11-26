package com.mygdx.isometricgame;

public class Handler {
	private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean zoomIn = false;
    private boolean zoomOut = false;
    
    public void update(){
    	
    }  
    public void render(){
    	
    }
	public boolean isUp(){
		return up;
	}
	public void setUp(boolean up){
		this.up = up;
	}
	public boolean isDown(){
		return down;
	}
	public void setDown(boolean down){
		this.down = down;
	}
	public boolean isRight(){
		return right;
	}
	public void setRight(boolean right){
		this.right = right;
	}
	public boolean isLeft(){
		return left;
	}
	public void setLeft(boolean left){
		this.left = left;
	}
	public boolean isZoomIn() {
		return zoomIn;
	}
	public void setZoomIn(boolean zoomIn) {
		this.zoomIn = zoomIn;
	}
	public boolean isZoomOut() {
		return zoomOut;
	}
	public void setZoomOut(boolean zoomOut) {
		this.zoomOut = zoomOut;
	}  
	
}