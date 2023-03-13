package com.mygdx.isometricgame;

public class Handler {
	private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean zoomIn = false;
    private boolean zoomOut = false;
    private boolean clockwiseRotation = false;
    private boolean counterClockwiseRotation = false;		
    private boolean leftClick = false;
    private boolean rightClick = false;
    
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
	public boolean isClockwiseRotation() {
		return clockwiseRotation;
	}
	public void setClockwiseRotation(boolean clockwiseRotation) {
		this.clockwiseRotation = clockwiseRotation;
	}
	public boolean isCounterClockwiseRotation() {
		return counterClockwiseRotation;
	}
	public void setCounterClockwiseRotation(boolean counterClockwiseRotation) {
		this.counterClockwiseRotation = counterClockwiseRotation;
	}  
	public boolean leftClick() {
		return leftClick;
	}
	public void leftClick(boolean leftClick) {
		this.leftClick = leftClick;
	}
	public boolean rightClick() {
		return rightClick;
	}
	public void rightClick(boolean rightClick) {
		this.rightClick = rightClick;
	}
	
}