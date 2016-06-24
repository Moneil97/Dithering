import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Manager {

	public Manager() {
		
		//int[][] originalpixels = null;
		
		//import image pixel array
		int[][] pixels = null;
		try {
			BufferedImage image = ImageIO.read(new File("src/Media/image.jpg"));
			//originalpixels = Tools.imageToRGBArray(image);
			pixels = Tools.imageToRGBArray(image);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		//Convert to BW w/ Floyd-Steinberg Dithering then save
		BufferedImage image = Tools.RGBArrayToImage(Dither.toBWFloydSteinbergDither(pixels));
		try {
			ImageIO.write(image, "jpg", new File("src/Media/imageBWFloydSteinbergDither.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		//Convert to BW w/ Simple Dithering then save
//		BufferedImage image = Tools.RGBArrayToImage(Dither.toBWSimpleDither(pixels));
//		try {
//			ImageIO.write(image, "jpg", new File("src/Media/imageBWSimpleDither.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		//Convert to Black and white then save
//		BufferedImage image = Tools.RGBArrayToImage(Dither.toBW(pixels));
//		try {
//			ImageIO.write(image, "jpg", new File("src/Media/imageBW.jpg"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
//	public void restore(int[][] Originalpixels, int[][] pixels){
//		System.arraycopy(Originalpixels, 0, pixels, 0, pixels.length*pixels[0].length);
//	}

	public static void main(String[] args) {
		new Manager();
	}

}
