package com.mygdx.isometricgame;

import java.util.Random;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import terra.noise.perlin.PerlinNoise;
import terra.noise.perlin.PerlinNoiseGenerator;
import terra.noise.voronoi.VoronoiNoise;
import terra.noise.voronoi.VoronoiNoiseGenerator;

public class WorldGenerator {
	
	private final int mapSize = 2000;
	
	
	private double seedElevation;
	private double seedHumidity;
	private double seedTemperature;
	private NoiseGenerator noiseElevation;
	private NoiseGenerator noiseHumidity;
	private NoiseGenerator noiseTemperature;
	
	
	//TESTING VORONOI
	private VoronoiNoise voronoiNoise;
	private long seedVoronoi;
	
	private PerlinNoise perlinNoise1;
	private PerlinNoise perlinNoise2;
	private long seedPerlin1;
	private long seedPerlin2;
	
	//
	
	private double[][] humidityMap;
	private double[][] temperatureMap;
	
	
	public WorldGenerator() {
		seedElevation = new Random().nextGaussian() * 255;
		seedHumidity = new Random().nextGaussian() * 255;
		seedTemperature = new Random().nextGaussian() + 255;
		noiseElevation = new NoiseGenerator(seedElevation);
		noiseHumidity = new NoiseGenerator(seedHumidity);
		noiseTemperature = new NoiseGenerator(seedTemperature);
		humidityMap = new double[mapSize][mapSize];
		temperatureMap = new double[mapSize][mapSize];
		
		
		//TESTING VORONOI
		
		seedVoronoi = (long)(new Random().nextGaussian() * 255);
		voronoiNoise = new VoronoiNoiseGenerator()
				.height(mapSize)
				.width(mapSize)
				.seed(seedVoronoi)
				.frequency(5)
				.generate();
		
//		seedPerlin1 = (long)(new Random().nextGaussian() * 255);
//		seedPerlin2 = (long)(new Random().nextGaussian() * 255);
//		perlinNoise1 = new PerlinNoiseGenerator()
//				.height(mapSize)
//				.width(mapSize)
//				.seed(seedPerlin1)
//				.noiseMask(0)
//				.frequency(10)
//				.octaves(5)
//				.persistence(0.5)
//				.generate();
//		perlinNoise2 = new PerlinNoiseGenerator()
//				.height(mapSize)
//				.width(mapSize)
//				.seed(seedPerlin2)
//				.noiseMask(0)
//				.frequency(10)
//				.octaves(5)
//				.persistence(0.5)
//				.generate();
				
		
		//
	}
	public double[][] getPerlinNoise1() {
		return perlinNoise1.getNoise();
	}
	public double[][] getPerlinNoise2(){
		return perlinNoise2.getNoise();
	}
	public double[][] getVoronoiNoise(){
		return voronoiNoise.getNoise();
	}
	public double[][] getHumidityMap(){
		return humidityMap;
	}
	public double[][] getTemperatureMap(){
		return temperatureMap;
	}
	public int getMapSize() {
		return mapSize;
	}
	public void generateMap() {
		for(int i = mapSize / 2 - 1; i  >= -mapSize / 2 ; i--) {
			for(int j = mapSize / 2 - 1; j >= -mapSize / 2 ; j--) {
				double nx = i; /// mapSize - 0.5;
				double ny = j; /// mapSize - 0.5;
//				double elevation = (1.0 * noiseElevation.noise(1 * nx, 1 * ny))
//								 + (0.5 * noiseElevation.noise(2 * nx, 2 * ny))
//								 + (0.25 * noiseElevation.noise(4 * nx, 4 * ny))		 
//								 + (0.13 * noiseElevation.noise(8 * nx, 8 * ny))
//								 + (0.06 * noiseElevation.noise(16 * nx, 16 * ny))	
//								 + (0.03 * noiseElevation.noise(32 * nx, 32 * ny));
//				elevation = elevation / (1.0 + 0.5 + 0.25 + 0.13 + 0.06 + 0.3);
//				elevation = Math.pow(elevation, 5.0);
				double humidity = (1.0 * noiseHumidity.noise(1 * nx, 1 * ny))
						 		+ (0.5 * noiseHumidity.noise(2 * nx, 2 * ny))
						 		+ (0.25 * noiseHumidity.noise(4 * nx, 4 * ny))		 
						 		+ (0.13 * noiseHumidity.noise(8 * nx, 8 * ny))
						 		+ (0.06 * noiseHumidity.noise(16 * nx, 16 * ny))	
						 		+ (0.03 * noiseHumidity.noise(32 * nx, 32 * ny));
				humidity = humidity / (1.0 + 0.5 + 0.25 + 0.13 + 0.06 + 0.3);
				double temperature = (1.0 * noiseTemperature.noise(1 * nx, 1 * ny))
				 				+ (0.5 * noiseTemperature.noise(2 * nx, 2 * ny))
				 				+ (0.25 * noiseTemperature.noise(4 * nx, 4 * ny))		 
				 				+ (0.13 * noiseTemperature.noise(8 * nx, 8 * ny))
				 				+ (0.06 * noiseTemperature.noise(16 * nx, 16 * ny))	
				 				+ (0.03 * noiseTemperature.noise(32 * nx, 32 * ny));
				temperature = temperature / (1.0 + 0.5 + 0.25 + 0.13 + 0.06 + 0.3);
				
				//biomeMap[i + biomeMap.length / 2][j + biomeMap.length / 2] = tile(humidity, temperature, x, y);	
				humidityMap[i + mapSize / 2][j + mapSize / 2] = humidity;
				temperatureMap[i + mapSize / 2][j + mapSize / 2] = temperature;
			}
		}
	}
}
