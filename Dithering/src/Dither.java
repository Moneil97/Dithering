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
			int last =0;
			for (int x = 0; x < width; x++) {
				int val = RGBToGrayValue(originalPixels[y][x]) + offset;
				if (val > 127){
					pixels[y][x] = WHITE;
					if (last == 0){
						offset = 0;
						last = 1;
					}
						
					offset = (val-255);
				}
				else{
					pixels[y][x] = BLACK;
					if (last == 1){
						offset = 0;
						last = 0;
					}
					offset = val;
				}
			}
		}
		return pixels;
	}
	
	
}
