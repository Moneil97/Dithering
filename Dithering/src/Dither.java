import java.awt.Color;
import java.util.Arrays;

public class Dither {

	private static int WHITE = Color.white.getRGB();
	private static int BLACK = Color.black.getRGB();

	public static int RGBToGrayValue(int rgb) {
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		return (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
	}

	public static int[][] toBW(int[][] originalPixels) {
		int[][] pixels = originalPixels;

		int height = originalPixels.length;
		int width = originalPixels[0].length;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[y][x] = RGBToGrayValue(originalPixels[y][x]) > 127 ? WHITE : BLACK;
			}
		}
		System.out.println(Arrays.toString(pixels[150]));
		System.out.println(BLACK);
		return pixels;
	}

}
