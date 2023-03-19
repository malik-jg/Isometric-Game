package com.mygdx.isometricgame;

import com.badlogic.gdx.math.Vector2;

public final class VectorUtils {
	private VectorUtils() {}
	
	
	//clockwise in the form: O(a,b) P(x,y) -> (a + (y - b), b - (x - a))
	//return new Vector2(origin.x + (point.y - origin.y), origin.y - (point.x - origin.x));
	public static Vector2 rotateIsometricPoint(Vector2 origin, Vector2 point) {
		float rx = origin.x + (point.y - origin.y);
		float ry = origin.y - (point.x - origin.x);
		if(point.x == origin.x) {
			return new Vector2(2 * rx, ry);
		}
		else if(point.y == origin.y) {
			return new Vector2(rx, (ry + origin.y) / 2 );
		}
		else if(point.x > 0){
			return new Vector2(2 * rx, ry + Math.abs(point.x / 2));
		}
		else if(point.x < 0) {
			return new Vector2(2 * rx, ry - Math.abs(point.x / 2));
		}
		return null;
	}
	
//	public static void rotateVectorGrid(Vector2 origin, Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
//		a = rotateIsometricPoint(origin,a);
//		b = rotateIsometricPoint(origin,b);
//		c = rotateIsometricPoint(origin,c);
//		d = rotateIsometricPoint(origin,d);
//	}

}
