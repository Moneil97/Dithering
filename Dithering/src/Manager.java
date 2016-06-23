import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Manager {

	public Manager() {
		
		//import image pixel array
		int[][] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new File("src/Media/image.jpg"));
			pixels = Tools.imageToRGBArray(image);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Convert to Black and white then save
		BufferedImage image = Tools.RGBArrayToImage(Dither.toBW(pixels));
		try {
			ImageIO.write(image, "jpg", new File("src/Media/imageBW.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new Manager();
	}

}
