package terra;

import terra.gui.ImageFrame;
import terra.gui.NoiseFrame;

import terra.noise.BlendMode;
import terra.noise.DistanceFormula;
import terra.noise.Noise;
import terra.noise.mask.NoiseMask;
import terra.noise.mask.NoiseMaskGenerator;
import terra.noise.perlin.PerlinNoiseGenerator;
import terra.noise.voronoi.VoronoiNoiseGenerator;
import terra.noise.white.WhiteNoiseGenerator;

public class Terra {
	public static void main(String[] args) {
		Noise perlin = new PerlinNoiseGenerator()
							.height(256)
							.width(256)
							.seed(0)
							.frequency(2)
							.octaves(3)
							.persistence(.5)
							.lacunarity(2)
							.noiseMask(0.0)
							.generate();

		new NoiseFrame(perlin);

		NoiseMask mask = new NoiseMaskGenerator()
							.height(256)
							.width(256)
							.intensity(0.5)
							.generate();

		new ImageFrame("Noise Mask", mask.getMaskImage());

		Noise white = new WhiteNoiseGenerator()
							.height(256)
							.width(256)
							.seed(0)
							.noiseMask(0.5)
							.generate();

		new NoiseFrame(white);

		Noise voronoi = new VoronoiNoiseGenerator()
							.height(256)
							.width(256)
							.seed(0)
							.distanceFormula(DistanceFormula.Euclidean)
							.frequency(3)
							.noiseMask(0.0)
							.generate();

		new NoiseFrame(voronoi);

		new NoiseFrame(perlin.blend(BlendMode.Darken, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Multiply, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.ColorBurn, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.LinearBurn, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Lighten, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.ColorDodge, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.LinearDodge, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Screen, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Overlay, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.SoftLight, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.HardLight, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.VividLight, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.LinearLight, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.PinLight, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.HardMix, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Difference, voronoi));
		new NoiseFrame(perlin.blend(BlendMode.Exclusion, voronoi));
	}
}
