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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ETSTERTETSETER {
	private static long seed = (long) (new Random().nextGaussian() * 255);
	private static VoronoiNoise voronoi = new VoronoiNoiseGenerator()
			.height(5000)
			.width(5000)
			.seed(111)
			.frequency(100)
			.generate();
	private static PerlinNoise perlin = new PerlinNoiseGenerator()
			.height(2000)
			.width(2000)
			.seed(seed)
			.noiseMask(0)
			.frequency(10)
			.octaves(5)
			.persistence(0.5)
			.generate();
	private static WhiteNoise white = new WhiteNoiseGenerator()
			.height(1440)
			.width(810)
			.seed(124)
			.noiseMask(0)
			.generate();
			
			
	public static void main(String[] args) throws IOException {
		ETSTERTETSETER abc=new ETSTERTETSETER();
		System.out.println(seed);
	}
	public ETSTERTETSETER() throws IOException
    {
        BufferedImage img=perlin.getNoiseImage();
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
