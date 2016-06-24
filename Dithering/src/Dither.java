import java.awt.Color;

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
		return pixels;
	}

	public static int[][] toBWSimpleDither(int[][] originalPixels) {
		int[][] pixels = originalPixels;

		int height = originalPixels.length;
		int width = originalPixels[0].length;

		for (int y = 0; y < height; y++) {
			int offset = 0;
			for (int x = 0; x < width; x++) {
				int val = RGBToGrayValue(originalPixels[y][x]) + offset;
				if (val > 127) {
					pixels[y][x] = WHITE;
					offset = (val - 255);
				} else {
					pixels[y][x] = BLACK;
					offset = val;
				}
			}
		}
		return pixels;
	}

	public static int[][] toBWFloydSteinbergDither(int[][] originalPixels) {

		int height = originalPixels.length;
		int width = originalPixels[0].length;

		int[][] pixels = originalPixels;
		int[][] offset = new int[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int val = RGBToGrayValue(originalPixels[y][x]) + offset[y][x];
				int offVal = 0;
				if (val > 127) {
					pixels[y][x] = WHITE;
					offVal = (val - 255);
				} else {
					pixels[y][x] = BLACK;
					offVal = val;
				}

				if (x < width-1)
					offset[y][x + 1] += offVal * 7 / 16;
				if (y < height-1) {
					if (x > 0)
						offset[y + 1][x - 1] += offVal * 3 / 16;
					offset[y + 1][x] += offVal * 5 / 16;
					if (x < width-1)
						offset[y + 1][x + 1] += offVal * 1 / 16;
				}

			}
		}
		return pixels;
	}

}
