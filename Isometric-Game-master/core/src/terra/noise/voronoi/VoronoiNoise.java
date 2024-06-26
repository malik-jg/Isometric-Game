package terra.noise.voronoi;

import terra.noise.DistanceFormula;
import terra.noise.Noise;
import terra.noise.mask.NoiseMask;

/**
 * The immutable programmatic representation of voronoi noise.
 * Objects store the results output by a {@link VoronoiNoiseGenerator} with the specific corresponding parameters.
 * 
 * @since 1.0
 * @author ChristopherWMM
 */
public class VoronoiNoise extends Noise {
	/** The methodology for calculating distance within this {@link VoronoiNoise} object. */
	private final DistanceFormula distanceFormula;

	/** The non-zero integer initial frequency of this {@link VoronoiNoise} object. */
	private final int frequency;

	/**
	 * Constructs a new {@link VoronoiNoise} object with the given values.
	 * 
	 * @param height The non-zero integer height of this {@link VoronoiNoise} object.
	 * @param width The non-zero integer width of this {@link VoronoiNoise} object.
	 * @param seed The long seed used to generate this {@link VoronoiNoise} object.
	 * @param noise The 2D double array containing the individual mask values of this {@link VoronoiNoise} object.
	 * @param noiseMask The {@link NoiseMask} being applied to this {@link VoronoiNoise} object.
	 * @param frequency The non-zero integer initial frequency of this {@link VoronoiNoise} object.
	 * @throws IllegalArgumentException if the given parameters are outside of the valid range.
	 * @since 1.0
	 */
	VoronoiNoise(final int height, final int width, final long seed, final double[][] noiseArray, final NoiseMask noiseMask, final DistanceFormula distanceFormula, final int frequency) {
		super(height, width, seed, noiseArray, noiseMask);

		if (frequency < 1) {
			throw new IllegalArgumentException("A voronoi noise map initial frequency must be a positive, non-zero value. " + frequency + " is too small.");
		} else if (distanceFormula == null) {
			throw new IllegalArgumentException("A voronoi noise distance mode cannot be null.");
		}

		this.distanceFormula = distanceFormula;
		this.frequency = frequency;
	}

	/**
	 * Constructs a new {@link VoronoiNoise} object that is a deep copy based on the given {@link VoronoiNoise} object.
	 * 
	 * @param perlinNoise The {@link VoronoiNoise} object being copied.
	 * @since 1.0
	 */
	VoronoiNoise(final VoronoiNoise voronoiNoise) {
		super(voronoiNoise);

		this.distanceFormula = voronoiNoise.getDistanceFormula();
		this.frequency = voronoiNoise.getFrequency();
	}

	public DistanceFormula getDistanceFormula() {
		return this.distanceFormula;
	}

	/**
	 * Returns the non-zero initial frequency of this {@link VoronoiNoise} object.
	 * 
	 * @return The non-zero integer initial frequency of this {@link VoronoiNoise} object.
	 * @since 1.0
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Returns a new {@link VoronoiNoise} object that is a deep copy of this {@link VoronoiNoise} object.
	 * 
	 * @return A new {@link VoronoiNoise} object that is a deep copy of this {@link VoronoiNoise} object.
	 * @since 1.0
	 */
	public VoronoiNoise clone() {
		return new VoronoiNoise(this);
	}
}
