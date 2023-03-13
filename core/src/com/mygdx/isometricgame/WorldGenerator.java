package com.mygdx.isometricgame;

import java.util.Random;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import terra.noise.perlin.PerlinNoise;
import terra.noise.perlin.PerlinNoiseGenerator;
import terra.noise.voronoi.VoronoiNoise;
import terra.noise.voronoi.VoronoiNoiseGenerator;

public class WorldGenerator {
	
	private final int mapSize = 50;
	
	
	private double seedElevation;
	private double seedHumidity;
	private double seedTemperature;
	private NoiseGenerator noiseElevation;
	private NoiseGenerator noiseHumidity;
	private NoiseGenerator noiseTemperature;
	
	
	//TESTING VORONOI
	private VoronoiNoise voronoiNoise;
	private long seedVoronoi;
	
	private PerlinNoise elevation;
	private long seedPerlinElevation;
	
	private PerlinNoise humidity;
	private long seedPerlinHumidity;
	
	private PerlinNoise temperature;
	private long seedPerlinTemperature;
	
	
	
	//
	
	
	public WorldGenerator() {
		
		
		
		//TESTING VORONOI
		
		seedVoronoi = (long)(new Random().nextGaussian() * 255);
		voronoiNoise = new VoronoiNoiseGenerator()
				.height(mapSize)
				.width(mapSize)
				.seed(1)
				.frequency(5)
				.generate();
		
		seedPerlinElevation = (long)(new Random().nextGaussian() * 255);
		elevation = new PerlinNoiseGenerator()
				.height(mapSize)
				.width(mapSize)
				.seed(1)
				.noiseMask(0)
				.frequency(50)
				.octaves(10)
				.persistence(0.5)
				.generate();
		
		seedPerlinHumidity = (long)(new Random().nextGaussian() * 255);
		humidity = new PerlinNoiseGenerator()
				.height(mapSize)
				.width(mapSize)
				.seed(1)
				.noiseMask(0)
				.frequency(3)
				.octaves(10)
				.persistence(0.5)
				.generate();
		
		seedPerlinTemperature = (long)(new Random().nextGaussian() * 255);
		temperature = new PerlinNoiseGenerator()
				.height(mapSize)
				.width(mapSize)
				.seed(1)
				.noiseMask(0)
				.frequency(3)
				.octaves(10)
				.persistence(0.5)
				.generate();
		
	}

	public double[][] getVoronoiNoise(){
		return voronoiNoise.getNoise();
	}
	public double getVoronoiValue(int i, int j) {
		return voronoiNoise.getNoise()[i][j];
	}
	public double[][] getElevation(){
		return elevation.getNoise();
	}
	public double getElevationValue(int i, int j) {
		return elevation.getNoise()[i][j];
	}
	
	
	public double[][] getHumidity(){
		return humidity.getNoise();
	}
	public double[][] getTemperature(){
		return temperature.getNoise();
	}
	
	
	
	public int getMapSize() {
		return mapSize;
	}
	
			
		
	
}
