import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Manager {

	public Manager() {

		String sourceImageLocation = "src/Media/image.jpg";
		String extension = "jpg";
		int[][] originalpixels = null;
		BufferedImage image = null;

		// import image pixel array
		try {
			image = ImageIO.read(new File(sourceImageLocation));
			originalpixels = Tools.imageToRGBArray(image);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Strip off extension
		sourceImageLocation = sourceImageLocation.substring(0, sourceImageLocation.lastIndexOf("."));

		int[][] pixels = new int[originalpixels.length][originalpixels[0].length];

		// Convert to Black and white then save
		restore(originalpixels, pixels);
		image = Tools.RGBArrayToImage(Dither.toBW(pixels));
		try {
			ImageIO.write(image, extension, new File(sourceImageLocation + "BW." + extension));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Convert to BW w/ Simple Dithering then save
		restore(originalpixels, pixels);
		image = Tools.RGBArrayToImage(Dither.toBWSimpleDither(pixels));
		try {
			ImageIO.write(image, extension, new File(sourceImageLocation + "BWSimpleDither." + extension));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Convert to BW w/ Floyd-Steinberg Dithering then save
		restore(originalpixels, pixels);
		image = Tools.RGBArrayToImage(Dither.toBWFloydSteinbergDither(pixels));
		try {
			ImageIO.write(image, extension, new File(sourceImageLocation + "BWFloydSteinbergDither." + extension));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void restore(int[][] originalPixels, int[][] pixels) {
		for (int i = 0; i < originalPixels.length; i++)
			System.arraycopy(originalPixels[i], 0, pixels[i], 0, originalPixels[i].length);
	}

	public static void main(String[] args) {
		new Manager();
	}

}
