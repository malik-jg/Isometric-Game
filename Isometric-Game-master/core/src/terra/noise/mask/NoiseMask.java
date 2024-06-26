package terra.noise.mask;
import terra.gui.Image;

/**
 * The immutable programmatic representation of a noise mask.
 * Objects store the results output by a {@link NoiseMaskGenerator} with the specific corresponding parameters.
 * 
 * @since 1.0
 * @author ChristopherWMM
 */
public class NoiseMask {
	/** The non-zero integer height of this {@link NoiseMask} object. */
	private final int height;

	/** The non-zero integer width of this {@link NoiseMask} object. */
	private final int width;

	/** The double intensity of this {@link NoiseMask} object. */
	private final double intensity;

	/** The 2D double array containing the individual mask values of this {@link NoiseMask} object. */
	private final double[][] maskArray;

	/**
	 * Constructs a new {@link NoiseMask} object with the given values.
	 * 
	 * @param height The non-zero integer height of this {@link NoiseMask} object.
	 * @param width The non-zero integer width of this {@link NoiseMask} object.
	 * @param intensity The double intensity of this {@link NoiseMask} object.
	 * @param maskArray The 2D double array containing the individual mask values of this {@link NoiseMask} object.
	 * @throws IllegalArgumentException if the given parameters are outside of the valid range.
	 * @since 1.0
	 */
	NoiseMask(final int height, final int width, final double intensity, final double[][] maskArray) {
		if (height < 1) {
			throw new IllegalArgumentException("A noise mask height must be a positive, non-zero value. " + height + " is too small.");
		} else if (width < 1) {
			throw new IllegalArgumentException("A noise mask width must be a positive, non-zero value. " + width + " is too small.");
		} else if (intensity < 0.0 || intensity > 1.0) {
			throw new IllegalArgumentException("A noise mask intensity must be a positive value between zero and one. " + intensity + " is outside that interval.");
		} else if (maskArray == null) {
			throw new IllegalArgumentException("The given noise array cannot be null.");
		} else if (maskArray.length == 0 || maskArray.length != height || maskArray[0].length == 0 || maskArray[0].length != width) {
			throw new IllegalArgumentException("The dimensions of the provided noise array is invalid!");
		}

		this.height = height;
		this.width = width;
		this.intensity = intensity;
		this.maskArray = maskArray;
	}

	/**
	 * Constructs a new {@link NoiseMask} object that is a deep copy based on the given {@link NoiseMask} object.
	 * 
	 * @param noiseMask The {@link NoiseMask} object being copied.
	 * @since 1.0
	 */
	NoiseMask(final NoiseMask noiseMask) {
		this.height = noiseMask.getHeight();
		this.width = noiseMask.getWidth();
		this.intensity = noiseMask.getIntensity();
		this.maskArray = this.copy2DArray(noiseMask.getMask());
	}

	/**
	 * Performs a deep copy on a each row of a 2D array using {@link System#arraycopy(Object, int, Object, int, int) System.arraycopy()}.
	 * 
	 * @param source The 2D array being copied into the given target.
	 * @return The deep copy of the source values.
	 * @since 1.0
	 */
	private double[][] copy2DArray(final double[][] source) {
		final double[][] target = new double[source.length][source[0].length];

		for (int x = 0; x < source.length; x++) {
			System.arraycopy(source[x], 0, target[x], 0, source[x].length);
		}

		return target;
	}

	/**
	 * Converts the given {@link NoiseMask} value into its corresponding grayscale ARGB color.
	 * 
	 * @param maskValue The {@link NoiseMask} value within the interval within the interval <b>[0.0 - 1.0]</b>.
	 * @return The grayscale integer ARGB color corresponding to the given {@link NoiseMask} value.
	 * @since 1.0
	 */
	private int getGrayscaleMaskColor(final double maskValue) {
		int blue = (int)(maskValue * 0xFF);
		int green = blue * 0x100;
		int red = blue * 0x10000;
		int alpha = 0xFF000000;

		return alpha + red + green + blue;
	}

	/**
	 * Generates the {@link BufferedImage} visual representation of this {@link NoiseMask} object. 
	 * 
	 * @param mask The 2D double array containing the individual mask values of this {@link NoiseMask}.
	 * @return The grayscale {@link BufferedImage} visual representation of the given 2D double array.
	 * @since 1.0
	 */
	private Image generateMaskImage(final double[][] mask) {
		Image maskImage = new Image(this.getWidth(), this.getHeight(), Image.TYPE_INT_ARGB);

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				maskImage.setRGB(x, y, getGrayscaleMaskColor(mask[y][x]));
			}
		}

		return maskImage;
	}

	/** 
	 * Returns the non-zero height of this {@link NoiseMask} object.
	 * 
	 * @return The non-zero integer height of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public int getHeight() {
		return this.height;
	}

	/** 
	 * Returns the non-zero width of this {@link NoiseMask} object.
	 * 
	 * @return The non-zero integer width of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Returns the intensity of this {@link NoiseMask} object. 
	 * Intensity is within the interval <b>[0.0 - 1.0]</b>.
	 * 
	 * @return The double intensity of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public double getIntensity() {
		return this.intensity;
	}

	/**
	 * Returns a 2D array containing the individual mask values of this {@link NoiseMask} object. 
	 * Values within the array are within the interval <b>[0.0 - 1.0]</b> where 1.0 indicates the corresponding value should be completely masked and 0.0 indicates the value should not be masked at all.
	 * 
	 * @return A 2D double array containing the individual mask values of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public double[][] getMask() {
		return this.maskArray;
	}

	/**
	 * Returns a visual representation of this {@link NoiseMask} object.
	 * 
	 * @return A {@link Image} visual representation of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public Image getMaskImage() {
		return this.generateMaskImage(this.maskArray);
	}

	/**
	 * Returns a new {@link NoiseMask} object that is a deep copy of this {@link NoiseMask} object.
	 * 
	 * @return A new {@link NoiseMask} object that is a deep copy of this {@link NoiseMask} object.
	 * @since 1.0
	 */
	public NoiseMask clone() {
		return new NoiseMask(this);
	}
}
