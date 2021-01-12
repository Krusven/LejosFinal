package workingLogic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextToImage {
	public static BufferedImage createImage(String inputString) throws IOException{
        
  
       
        BufferedImage image = createGraphics(inputString);
        saveImage(image);
        
        return image;
	}
	public static BufferedImage createGraphics(String inputString) {
		
		BufferedImage bi = new BufferedImage(160, 195, BufferedImage.TYPE_INT_RGB);
		Font font = new Font("Arial", Font.PLAIN, 25);
	    Graphics2D g2 = setGraphics(bi);
	    double baseY = getY(getBounds(font, inputString, g2));

	    if(isLatin(inputString)) {
	    	drawLinesAsWords(g2, inputString, font, baseY);
	    } else {
	    	drawLinesAsCharacters(g2, inputString, font, baseY);
	    }
	    
	    g2.dispose();

	    return bi;
	}
	public static double getY(Rectangle2D bounds) {
		
	    double ascent = - bounds.getY();
	    double baseY = 0 + ascent;
	    return baseY;
	}
	public static Rectangle2D getBounds(Font font, String content, Graphics2D g2) {
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(content, context);
		return bounds;
	}
	public static Graphics2D setGraphics(BufferedImage bi) {
		Graphics2D g2 = (Graphics2D)bi.getGraphics();
	    g2.setBackground(Color.GRAY);
	    g2.clearRect(0, 0, 160, 195);
	    g2.setPaint(Color.BLACK);
	    return g2;
	}
	public static boolean isLatin(String inputString) {
		String noSpace = inputString;
	    noSpace = noSpace.replaceAll(" ", "");
		if(noSpace.matches("\\p{IsLatin}+")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void saveImage(BufferedImage buffImg) throws IOException {
		ImageIO.write(buffImg, "png", new File("./Image.jpeg"));
	}
	public static void drawLinesAsWords(Graphics2D g, String content, Font font, double baseY) {
		int lineHeight = getHeight(font, g);
	}
	public static void drawLinesAsCharacters(Graphics2D g, String content, Font font, double baseY) {
		int lineHeight = getHeight(font, g);
	}
	public static int getHeight(Font font, Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = g.getFontMetrics(font).getHeight();
		return lineHeight;
	}

}
