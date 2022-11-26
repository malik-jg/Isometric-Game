package com.mygdx.isometricgame;

import java.util.Random;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import terra.noise.voronoi.VoronoiNoise;
import terra.noise.voronoi.VoronoiNoiseGenerator;


public class IsometricRenderer{
	private double[][] map;
	private OpenSimplex2 openSimplex2;
	private NoiseGenerator noiseGenerator;
	private int mapSize;
	private double seed;
	
	private int renderWidth = GameScreen.WIDTH + 320;
	private int renderHeight = GameScreen.HEIGHT + 180;
	private float renderX = -832;
	private float renderY = -485;
	
	
	
	
	private WorldGenerator worldGenerator;
	private double[][] humidity;
	private double[][] temperature;
	
	
	//TESTING VORONOI
	private double[][] voronoiNoise;
	private double[][] perlinNoise1;
	private double[][] perlinNoise2;
	
	
	public IsometricRenderer(){
		//openSimplex2 = new OpenSimplex2();
		//noiseGenerator = new NoiseGenerator();
		
		
		//map = generateMap(); 
		

		worldGenerator = new  WorldGenerator();
		//worldGenerator.generateMap();
		humidity = worldGenerator.getHumidityMap();
		temperature = worldGenerator.getTemperatureMap();
		
		
		
		//TESTING VORONOI
		voronoiNoise = worldGenerator.getVoronoiNoise();
		//perlinNoise1 = worldGenerator.getPerlinNoise1();
		//perlinNoise2 = worldGenerator.getPerlinNoise2();

	}
	
	public void render() {
		
	}
	public void update() {
		
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
	

	public void createWorld(SpriteBatch batch, TextureAtlas textureAtlas, TextureRegion textureRegion) {
		for(int i = worldGenerator.getMapSize() / 2 - 1; i  >= -worldGenerator.getMapSize() / 2 ; i--){
			for(int j = worldGenerator.getMapSize() / 2 - 1; j >= -worldGenerator.getMapSize() / 2 ; j--) {
				float x = i * 0.5f * Tile.TILE_WIDTH + j * -0.5f  * Tile.TILE_WIDTH - 0.5f*Tile.TILE_WIDTH;
				float y = i * 0.25f * Tile.TILE_HEIGHT + j * 0.25f * Tile.TILE_HEIGHT - 0.5f*Tile.TILE_WIDTH;;
				//if((x > renderX && x < renderX + renderWidth) && (y > renderY && y < renderY + renderHeight)){
					drawBiome(batch, textureAtlas, textureRegion, humidity[i + humidity.length / 2][j + humidity.length / 2], temperature[i + temperature.length / 2][j + temperature.length / 2], x, y);
				//}
				
			}
		}
	}
	//TESTING VORONOI MAP
	
	public void createVoronoiMap(SpriteBatch batch, TextureAtlas textureAtlas, TextureRegion textureRegion) {
		for(int i = worldGenerator.getMapSize() / 2 - 1; i >= -worldGenerator.getMapSize() / 2; i--) {
			for(int j = worldGenerator.getMapSize() / 2 - 1; j >= -worldGenerator.getMapSize() / 2; j--) {
				float x = i * 0.5f * Tile.TILE_WIDTH + j * -0.5f  * Tile.TILE_WIDTH - 0.5f*Tile.TILE_WIDTH;
				float y = i * 0.25f * Tile.TILE_HEIGHT + j * 0.25f * Tile.TILE_HEIGHT - 0.5f*Tile.TILE_WIDTH;;
				drawVoronoiMap(batch, textureAtlas, textureRegion, voronoiNoise[i + voronoiNoise.length / 2][j + voronoiNoise.length / 2], x, y);
			}
		}
	}
	public void drawVoronoiMap(SpriteBatch batch, TextureAtlas textureAtlas, TextureRegion textureRegion, double noise, float x, float y) {
		if(noise > .75) {
			textureRegion = textureAtlas.findRegion("TropicalRainForests");
		}
		else if(noise > 0.5){
			textureRegion = textureAtlas.findRegion("Tundra");
		}
		else if(noise > 0.25) {
			textureRegion = textureAtlas.findRegion("ConiferousForests");
		}
		else {
			textureRegion = textureAtlas.findRegion("Ice");
		}
		batch.draw(textureRegion, x, y);
	}
	
	
	//TESTING ABOVE VORONOI
	
	
	//draw the textures here
	public void drawBiome(SpriteBatch batch, TextureAtlas textureAtlas, TextureRegion textureRegion, double humidity, double temperature, float biomeX, float biomeY) {
		textureRegion = textureAtlas.findRegion("Ice");		
		batch.draw(textureRegion, biomeX, biomeY);
		if(humidity > 0.875) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);		
			}
		else if(humidity > 0.750) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
		}
		else if(humidity > 0.625) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
					
		}
		else if(humidity > 0.500) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
				
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
			
		}
		else if(humidity > 0.375) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
			
		}
		else if(humidity > 0.250) {
			if(temperature > 0.666) {
				//return new TropicalRainForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalRainForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
				
		}
		else if(humidity > 0.125) {
			if(temperature > 0.666) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalSeasonalForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalSeasonalForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new DeciduousForests(x,y);
				textureRegion = textureAtlas.findRegion("DeciduousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > 0.000) {
			if(temperature > 0.666) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalSeasonalForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalSeasonalForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new Chaparral(x,y);
				textureRegion = textureAtlas.findRegion("Chaparral");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new MixedForests(x,y);
				textureRegion = textureAtlas.findRegion("MixedForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
			
		}
		else if(humidity > -0.125) {
			if(temperature > 0.666) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new TropicalSeasonalForests(x,y);
				textureRegion = textureAtlas.findRegion("TropicalSeasonalForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new Chaparral(x,y);
				textureRegion = textureAtlas.findRegion("Chaparral");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new Steppes(x,y);
				textureRegion = textureAtlas.findRegion("Steppes");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ConiferousForests(x,y);
				textureRegion = textureAtlas.findRegion("ConiferousForests");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > -0.250) {
			if(temperature > 0.666) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				///return new Chaparral(x,y);
				textureRegion = textureAtlas.findRegion("Chaparral");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new Steppes(x,y);
				textureRegion = textureAtlas.findRegion("Steppes");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ColdParklands(x,y);
				textureRegion = textureAtlas.findRegion("ColdParklands");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > -0.375) {
			if(temperature > 0.666) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.500) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new Chaparral(x,y);
				textureRegion = textureAtlas.findRegion("Chaparral");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new CoolDeserts(x,y);
				textureRegion = textureAtlas.findRegion("CoolDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ColdParklands(x,y);
				textureRegion = textureAtlas.findRegion("ColdParklands");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -1.000) {
				//return new Ice(x,y);
				textureRegion = textureAtlas.findRegion("Ice");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > -0.500) {
			if(temperature > 0.666) {
				//return new Savanna(x,y);
				textureRegion = textureAtlas.findRegion("Savanna");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new CoolDeserts(x,y);
				textureRegion = textureAtlas.findRegion("CoolDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ColdParklands(x,y);
				textureRegion = textureAtlas.findRegion("ColdParklands");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.666) {
				//return new Tundra(x,y);
				textureRegion = textureAtlas.findRegion("Tundra");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > -0.625) {
			if(temperature > 0.666) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}	
			else if(temperature > 0.333) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new CoolDeserts(x,y);
				textureRegion = textureAtlas.findRegion("CoolDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > -0.333) {
				//return new ColdParklands(x,y);
				textureRegion = textureAtlas.findRegion("ColdParklands");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
		}
		else if(humidity > -0.75) {
			if(temperature > 0.666) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.000) {
				//return new CoolDeserts(x,y);
				textureRegion = textureAtlas.findRegion("CoolDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else if(humidity > -0.875) {
			if(temperature > 0.666) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			else if(temperature > 0.333) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}
		else{
			if(temperature > 0.666) {
				//return new HotDeserts(x,y);
				textureRegion = textureAtlas.findRegion("HotDeserts");		
				batch.draw(textureRegion, biomeX, biomeY);
			}
			
		}	
	}
}