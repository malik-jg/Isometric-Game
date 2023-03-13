package com.mygdx.isometricgame;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.BitmapFont;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import terra.noise.voronoi.VoronoiNoise;
import terra.noise.voronoi.VoronoiNoiseGenerator;


public class IsometricRenderer{
	
	private final int TILE_WIDTH = 32;
	private final int TILE_HEIGHT = 32;
	
	
	private final int renderBox = 1439;
	
	//1471 -> 92 x 92
	//1439 -> 90 x 90
	
	
	
	private int renderWidth = renderBox;
	private int renderHeight = renderBox;
	private float renderX = -renderBox / 2;
	private float renderY = -renderBox / 2;
	
	private double upperLimit = 1;
	private double lowerLimit = 0;
	private double range = upperLimit - lowerLimit; 
	private double numberOfBiomes = 13;
	
	private WorldGenerator worldGenerator;

	
	//TESTING VORONOI change
	private double[][] voronoiNoise;
	private double[][] elevation;
	private double[][] humidity;
	private double[][] temperature;
	
	
	
	private Vector2 origin;
	
	//testing rotation
	private double[][] voronoiRotationMap;
	private double[][] elevationRotationMap;
	
	//test
	
	private BitmapFont font = new BitmapFont();
	private int current = 1;
	
	
	public IsometricRenderer(){
		
		
		

		worldGenerator = new  WorldGenerator();
		
		origin = new Vector2(0, worldGenerator.getMapSize() * TILE_WIDTH / 4);
		
		//TESTING VORONOI
		voronoiNoise = worldGenerator.getVoronoiNoise();
		elevation = worldGenerator.getElevation();
		humidity = worldGenerator.getHumidity();
		temperature = worldGenerator.getTemperature();
		

	}
	
	public void render(SpriteBatch batch, TextureAtlas textureAtlas, TextureRegion textureRegion, TextureAtlas textureAtlasMegaBlocks) {
		createVoronoiMap(batch, textureAtlas, textureRegion, textureAtlasMegaBlocks);

	}
	
	public void update(Handler handler, Player player, Camera camera) {
		if(handler.isClockwiseRotation()) {

			
			
			
		
			
			
			
			
			player.aBottom.set(VectorUtils.rotateIsometricPoint(origin,player.aBottom));
			player.bBottom.set(VectorUtils.rotateIsometricPoint(origin,player.bBottom));
			player.cBottom.set(VectorUtils.rotateIsometricPoint(origin,player.cBottom));
			player.dBottom.set(VectorUtils.rotateIsometricPoint(origin,player.dBottom));
			
			
			if(current == 1) {
				camera.setPos(player.dBottom);
				player.setPos(player.dBottom);
				current = 4;
			}
			else if(current == 2) {
				camera.setPos(player.aBottom);
				player.setPos(player.aBottom);
				current = 1;
			}
			else if(current == 3) {
				camera.setPos(player.bBottom);
				player.setPos(player.bBottom);
				current = 2;
			}		
			else if(current == 4) {
				camera.setPos(player.cBottom);
				player.setPos(player.cBottom);
				current = 3;
			}
			voronoiNoise = rotateMapClockwise(voronoiNoise);
			elevation = rotateMapClockwise(elevation);
			
			handler.setClockwiseRotation(false);			
		}

	}
	
	private Vector2 findRotatedPosition(double[][] vn, double[][] e, double vv, double ev) {
		Vector2 found = null;
		loop:
		for(int i = 0; i < vn.length; i++) {
			for(int j = 0; j < vn.length; j++) {
				if(vn[i][j] == vv && e[i][j] == ev) {
					found = new Vector2(i,j);
					break loop;
				}
			}
		}
		return found;
	}

	
	
	public float getRenderX() {
		return renderX;
	}
	public float getRenderY() {
		return renderY;
	}
	public void setRenderX(float x) {
		this.renderX = x;;
	}
	public void setRenderY(float y) {
		this.renderY = y;;
	}
	
	
	
	
	
	
	public void generateBox() {
		Vector2 v1 = new Vector2();
		Vector2 v2 = new Vector2();
		Vector2 v3 = new Vector2();
		Vector2 v4 = new Vector2();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//TESTING ISOMETRIC ROTATION
	
	
	
	
	
	
	//create grud 3 times map size then reotate along a cetain point where the cahratcer is
	//to get the new acharter perseptive 
	
	
	//TESTING VORONOI MAP
	public void createVoronoiMap(SpriteBatch batch, TextureAtlas textureAtlasBlocksTest, TextureRegion textureRegion, TextureAtlas textureAtlasMegaBlocks) {
		for(int i = worldGenerator.getMapSize() - 1; i >= 0; i--) {
			for(int j = worldGenerator.getMapSize() - 1; j >= 0; j--) {
				float x = i * 0.5f * TILE_WIDTH + j * -0.5f  * TILE_WIDTH - 0.5f*TILE_WIDTH;
				float y = i * 0.25f * TILE_HEIGHT + j * 0.25f * TILE_HEIGHT - 0.5f*TILE_WIDTH;
				//if((x >= renderX && x <= renderX + renderWidth) && (y >= renderY && y <= renderY + renderHeight)) {	
				drawVoronoiMap(batch, textureAtlasBlocksTest, textureRegion, voronoiNoise[i][j], x, y, elevation[i][j], textureAtlasMegaBlocks);
				//}
			}
		}
	}
	
	
	
//	public void createVoronoiMap(SpriteBatch batch, TextureAtlas textureAtlasBlocksTest, TextureRegion textureRegion, TextureAtlas textureAtlasMegaBlocks) {
//		for(int i = worldGenerator.getMapSize() / 2 - 1; i >= -worldGenerator.getMapSize() / 2; i--) {
//			for(int j = worldGenerator.getMapSize() / 2 - 1; j >= -worldGenerator.getMapSize() / 2; j--) {
//				float x = i * 0.5f * TILE_WIDTH + j * -0.5f  * TILE_WIDTH - 0.5f*TILE_WIDTH;
//				float y = i * 0.25f * TILE_HEIGHT + j * 0.25f * TILE_HEIGHT - 0.5f*TILE_WIDTH;
//				//if((x >= renderX && x <= renderX + renderWidth) && (y >= renderY && y <= renderY + renderHeight)) {	
//				drawVoronoiMap(batch, textureAtlasBlocksTest, textureRegion, voronoiNoise[i + voronoiNoise.length / 2][j + voronoiNoise.length / 2], x, y, elevation[i + elevation.length / 2][j + elevation.length / 2], textureAtlasMegaBlocks);
//				//}
//			}
//		}
//	}
	
	public void drawVoronoiMap(SpriteBatch batch, TextureAtlas textureAtlasBlocksTest, TextureRegion textureRegion, double noise, float x, float y, double elevation, TextureAtlas textureAtlasMegaBlocks) {
//		if(elevation > 0.9) {
//			y = y + TILE_HEIGHT/2 * 7;	
//		}
//		else if(elevation > 0.8) {
//			y = y + TILE_HEIGHT/2 * 6;	
//		}
//		else if(elevation > 0.7) {
//			y = y + TILE_HEIGHT/2 * 5;	
//		}
//		else if(elevation > 0.6) {
//			y = y + TILE_HEIGHT/2 * 4;	
//		}
//		else if(elevation > 0.5) {
//			y = y + TILE_HEIGHT/2 * 3;	
//		}
//		else if(elevation > 0.4) {
//			y = y + TILE_HEIGHT/2 * 2;	
//		}
//		else if(elevation > 0.3) {
//			y = y + TILE_HEIGHT/2;	
//		}

		
		
//		if(elevation > 0.5) {
//			y = y + TILE_HEIGHT/2 * 2;	
//		}
//		else {
//			y = y + TILE_HEIGHT/2;		
//		}
		
		
		if(noise > range * (numberOfBiomes - 1) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("TropicalRainForests");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Forest 1");
		}
		else if(noise > range * (numberOfBiomes - 2) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("DeciduousForests");
			textureRegion = textureAtlasMegaBlocks.findRegion("Autumn 1");
		}
		else if(noise > range * (numberOfBiomes - 3) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("MixedForests");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Grass 1");
		}
		else if(noise > range * (numberOfBiomes - 4) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("ConiferousForests");
			textureRegion = textureAtlasMegaBlocks.findRegion("Mountain 1");
		}
		else if(noise > range * (numberOfBiomes - 5) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("Tundra");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Stone 1");
		}
		else if(noise > range * (numberOfBiomes - 6) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("Ice");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Snowy Dirt 1");
		}
		else if(noise > range * (numberOfBiomes - 7) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("TropicalSeasonalForests");
			textureRegion = textureAtlasMegaBlocks.findRegion("Swamp 1");
		}
		else if(noise > range * (numberOfBiomes - 8) / (numberOfBiomes)) {
			//textureRegion = textureAtlasBlocksTest.findRegion("Chaparral");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Yellow Grass 1");
		}
		else if(noise > range * (numberOfBiomes - 9) / (numberOfBiomes)) {
			textureRegion = textureAtlasBlocksTest.findRegion("Steppes");	
		}
		else if(noise > range * (numberOfBiomes - 10) / (numberOfBiomes)) {
			textureRegion = textureAtlasBlocksTest.findRegion("Savanna");	
		}
		else if(noise > range * (numberOfBiomes - 11) / (numberOfBiomes)) {
			textureRegion = textureAtlasBlocksTest.findRegion("ColdParklands");	
		}
		else if(noise > range * (numberOfBiomes - 12) / (numberOfBiomes)) {
			textureRegion = textureAtlasBlocksTest.findRegion("CoolDeserts");	
		}
		else {
			//textureRegion = textureAtlasBlocksTest.findRegion("HotDeserts");	
			textureRegion = textureAtlasMegaBlocks.findRegion("Sand 1");
		}
		
		textureRegion = textureAtlasMegaBlocks.findRegion("Stone 1");
		
		
		batch.draw(textureRegion, x, y);

		
	}
	
	
	//TESTING ABOVE VORONOI
	
	
	private double[][] createRotationArray(int playerArrayX, int playerArrayY){
		int oldArrayLength = worldGenerator.getMapSize();
		int oldOriginXY = oldArrayLength / 2;
		int newArrayLength = 0;
		if(Math.abs(playerArrayX - oldOriginXY) >= Math.abs(playerArrayY - oldOriginXY)) {
			newArrayLength = Math.abs(playerArrayX - oldOriginXY) * 2 + oldArrayLength;
		}
		else {
			newArrayLength = Math.abs(playerArrayY - oldOriginXY) * 2 + oldArrayLength;
		}
		double[][] newArray = new double[newArrayLength][newArrayLength];
		return newArray;
	}
	
	private void placeOldInNew(double[][] oldArray, double[][] newArray, int playerArrayX, int playerArrayY, int newArrayOriginXY) {
		int translateX = newArrayOriginXY - playerArrayY;
		int translateY = newArrayOriginXY - playerArrayX;
		for(int i = 0; i < oldArray.length; i++) {
			for(int j = 0; j < oldArray.length; j++) {
				newArray[i + translateX][j + translateY] = oldArray[i][j];
			}
		}
	}
	private double[][] rotateMapClockwise(double [][] array) {
		int length = array.length;
		double[][] rotated = new double[length][length];
		for (int i = 0; i < length; i++) {
	        for (int j = 0; j < length; j++) {
	            rotated[j][length-1-i] = array[i][j];
	        }
	    }
		return rotated;
	}
	
	//end
	
	
	
	
	
	
	
	
}