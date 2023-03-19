package com.mygdx.isometricgame;

import terra.noise.perlin.PerlinNoise;
import terra.noise.perlin.PerlinNoiseGenerator;
import terra.noise.voronoi.VoronoiNoise;
import terra.noise.voronoi.VoronoiNoiseGenerator;
import terra.noise.white.WhiteNoise;
import terra.noise.white.WhiteNoiseGenerator;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.badlogic.gdx.math.Vector2;

public class ETSTERTETSETER {
	private static long seed = (long) (new Random().nextGaussian() * 255);
	private static VoronoiNoise voronoi = new VoronoiNoiseGenerator()
			.height(2000)
			.width(2000)
			.seed(seed)
			.frequency(100)
			.generate();
	private static PerlinNoise perlin = new PerlinNoiseGenerator()
			.height(500)
			.width(500)
			.seed(1234)
			.noiseMask(0)
			.frequency(5)
			.octaves(10)
			.persistence(0.5)
			.generate();
	private static WhiteNoise white = new WhiteNoiseGenerator()
			.height(2000)
			.width(2000)
			.seed(seed)
			.noiseMask(0.5)
			.generate();
	
	
	private static VoronoiNoise testRotation = new VoronoiNoiseGenerator()
			.height(7)
			.width(7)
			.seed(seed)
			.frequency(100)
			.generate();
			
	public static void main(String[] args) throws IOException {
		Vector2 origin = new Vector2(0,400);
		Vector2 start = new Vector2(-240,200);
		System.out.println("Origin" + origin);
		System.out.println();
		System.out.println(start);
		start = rotateIsometricPoint(origin,start);
		System.out.println(start);
		start = rotateIsometricPoint(origin,start);
		System.out.println(start);
		start = rotateIsometricPoint(origin,start);
		System.out.println(start);
		//start = rotatePoint(origin,start);
		//System.out.println(start);
	}
	private static Vector2 rotateIsometricPoint(Vector2 origin, Vector2 point) {
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
		//clockwise in the form: O(a,b) P(x,y) -> (a + (y - b), b - (x - a))
		//return new Vector2(origin.x + (point.y - origin.y), origin.y - (point.x - origin.x));
		}
	private static 	char[][] rotateMapClockwise(char [][] array) {
		int a = array.length;
		char[][] rotated = new char[a][a];
		for (int r = 0; r < a; r++) {
	        for (int c = 0; c < a; c++) {
	            rotated[c][a-1-r] = array[r][c];
	        }
	    }
		System.out.println("Rotated");

		return rotated;
	}
	private static char[][] fillArrayWithChar(char[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				array[i][j] = (char) (i + 97);
			}
		}
		return array;
	}
	private static void printArray(char[][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	private static void placeOldInNew(char[][] oldArray, char[][] newArray, int px, int py, int cxy) {
		int transX = cxy - py;
		int transY = cxy - px;
		for(int i = 0; i < oldArray.length; i++) {
			for(int j = 0; j < oldArray.length; j++) {
				newArray[i + transX][j + transY] = oldArray[i][j];
			}
		}
	}
	private static void drawNoise() throws IOException {
		ETSTERTETSETER abc=new ETSTERTETSETER();
		
		
		double[][] test = voronoi.getNoise();
		double min = test[0][0];
		double max = test[0][0];
		for(int i = 0; i < test.length; i++) {
			for(int j = 0; j < test.length; j++) {
				if(test[i][j] < min) {
					min = test[i][j];
				}
				else if(test[i][j] > max) {
					max = test[i][j];
				}
			}		
		}
		System.out.print("MINIMUM: " + min);
		System.out.print("MAXIMUM: " + max);
	}
	
	public void arrayRotationsByCreatingLargerArray() {
		//All of the code below was used to test translating a smaller array
				//into a larger array with the origin of the larger array being, a point
				//in the smaller array. The larger array was rotated so that the rotation 
				//would be around the origin (aka the point from the smaller array)
				final int length = 7;
				char[][] original = new char[length][length];
				original = fillArrayWithChar(original);
				int originX = length / 2;
				int originY = length / 2;
				
				
				
				
				Scanner scan = new Scanner(System.in);
				System.out.println("Character X?");
				int playerX = scan.nextInt();
				System.out.println("Character Y?");
				int playerY = scan.nextInt();
				
				int newLength = 0;
				if(Math.abs(playerX - originX) >= Math.abs(playerY - originY)) {
					newLength = Math.abs(playerX - originX) * 2 + length;
				}
				else {
					newLength = Math.abs(playerY - originY) * 2 + length;
				}
				System.out.println(newLength);
				char[][] willRotate = new char[newLength][newLength];
				printArray(original);
				System.out.println("MUST CENTER ON" + playerX + "," + playerY);
				
				int centerXY = newLength / 2;
				
				System.out.println(centerXY);
				
				int dX = playerX - originX;
				int dY = playerY - originY;
				
				System.out.println("DX" + dX);
				System.out.println("DY" + dY);
				
				printArray(willRotate);
				System.out.println("Placed Old In New");
				placeOldInNew(original, willRotate, playerX, playerY, centerXY);
				printArray(willRotate);
				System.out.println("Center: " + willRotate[centerXY][centerXY]);
				
				
				System.out.println("Now Rotated");
				willRotate = rotateMapClockwise(willRotate);
				printArray(willRotate);
				willRotate = rotateMapClockwise(willRotate);
				printArray(willRotate);
				
				
	}
	
	public ETSTERTETSETER() throws IOException
    {
        BufferedImage img=voronoi.getNoiseImage();
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setSize(16*40, 9*40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
