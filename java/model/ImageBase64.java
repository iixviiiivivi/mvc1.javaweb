package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageBase64 {
	
	public static String bytesArrayToBase64(byte[] byteArray) {	
		return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(byteArray);
	}
	
	public static String bytesArrayToBase64(byte[] byteArray, double widthRatio, double heightRaito) {
		String base64 = null;

		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(bais);
			
			int width = (int)(bufferedImage.getWidth() * widthRatio);
			int height = (int)(bufferedImage.getHeight()* heightRaito);
			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);			
			
			BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			g.dispose();		
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(newImage, "jpg", baos);
			base64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());				
		} catch (IOException e) {
			e.printStackTrace();
		}			
	
		return base64;
	}
}
