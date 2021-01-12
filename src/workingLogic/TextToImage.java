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
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TextToImage {
	
	public static BufferedImage createImage(String inputString) throws IOException{
        
		BufferedImage image = createGraphics(inputString);
        saveImage(image);
        
        return image;
	}
	
	public static BufferedImage createGraphics(String inputString) {
		
		BufferedImage bufferedImage = new BufferedImage(160, 195, BufferedImage.TYPE_INT_RGB);
		Font font = new Font("Arial", Font.PLAIN, 25);
	    Graphics2D graphics2D = prepareGraphics(bufferedImage);
	    double baseY = getY(getBounds(font, inputString, graphics2D));
	    
	    if(isLatin(inputString)) {
	    	drawLinesAsWords(graphics2D, inputString, font, baseY);
	    } else {
	    	drawLinesAsCharacters(graphics2D, inputString, font, baseY);
	    }
	    
	    graphics2D.dispose();

	    return bufferedImage;
	}
	
	public static double getY(Rectangle2D bounds) {
		
	    double ascent = - bounds.getY();
	    double baseY = 0 + ascent;
	    return baseY;
	}
	
	public static Rectangle2D getBounds(Font font, String content, Graphics2D graphics2D) {
		FontRenderContext context = graphics2D.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(content, context);
		
		return bounds;
	}
	
	public static Graphics2D prepareGraphics(BufferedImage bufferedImage) {
		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
	    graphics2D.setBackground(Color.GRAY);
	    graphics2D.clearRect(0, 0, 160, 195);
	    graphics2D.setPaint(Color.BLACK);
	    
	    return graphics2D;
	}
	
	public static boolean isLatin(String inputString) {
	    String regex = "^.*\\p{IsLatin}.*";
	    boolean result = inputString.matches(regex);
		if(result) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void saveImage(BufferedImage bufferedImage) throws IOException {
		
		ImageIO.write(bufferedImage, "png", new File("./Image.jpeg"));
	}
	
	public static int getHeight(Font font, Graphics2D g) {
		
		int lineHeight = g.getFontMetrics(font).getHeight();
		return lineHeight;
	}

	public static void drawLinesAsWords(Graphics2D graphics2D, String content, Font font, double baseY) {
		
		int lineHeight = getHeight(font, graphics2D);
		int leftBorder = 5;
		String[] wordArray = content.split(" ");
		FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
		String currentContent = "";
		double leftTopCornerLine = baseY;
		
		for(int i = 0; i < wordArray.length; i++) {
			
			if(leftTopCornerLine < 195) {

				if(fontMetrics.stringWidth(currentContent)>=240) {
					if(fontMetrics.stringWidth(wordArray[i])<=240-fontMetrics.stringWidth(currentContent)) {
						currentContent += " " + wordArray[i];
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						currentContent ="";
						leftTopCornerLine += lineHeight;
					} else {
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						currentContent ="";
						leftTopCornerLine += lineHeight;
					}
				} else {
					int contentWidth = fontMetrics.stringWidth(currentContent);
					if(contentWidth<=260) {
						currentContent += " " + wordArray[i];
					}
				}
			} else {
				System.out.println("Input was cut, because it was too long.");
				break;
			}
		}
	}
	public static void drawLinesAsCharacters(Graphics2D graphics2D, String content, Font font, double baseY) {
		int lineHeight = getHeight(font, graphics2D);
		int leftBorder = 5;
		String[] characterArray = content.split("");
		FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
		String currentContent = "";
		double leftTopCornerLine = baseY;
		
		for(int i = 0; i < characterArray.length; i++) {
			
			if(leftTopCornerLine < 195) {

				if(fontMetrics.stringWidth(currentContent)>=210) {
					if(fontMetrics.stringWidth(characterArray[i])<=210-fontMetrics.stringWidth(currentContent)) {
						currentContent += characterArray[i];
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						currentContent ="";
						leftTopCornerLine += lineHeight;
					} else {
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						currentContent ="";
						leftTopCornerLine += lineHeight;
					}
				} else {
					int contentWidth = fontMetrics.stringWidth(currentContent);
					if(contentWidth<=230) {
						currentContent += characterArray[i];
					}
				}
			} else {
				System.out.println("Input was cut, because it was too long.");
				break;
			}
		}
	}
	
}

