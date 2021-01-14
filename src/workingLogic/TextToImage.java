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
import java.util.Arrays;

import javax.imageio.ImageIO;
//lol
public class TextToImage {
	/* - Bold und Schriftgröße neue Breite 
	 * - Sondersprachen eigenen Font
	 * - 
	 */
	
	public static BufferedImage createImage(String inputString) throws IOException{
        
		BufferedImage image = createGraphics(inputString);
        saveImage(image);
        
        return image;
	}
	
	public static BufferedImage createGraphics(String inputString) {
		
		BufferedImage bufferedImage = new BufferedImage(160, 195, BufferedImage.TYPE_INT_RGB);
		Font font = new Font("Hiragino Sans GB" , Font.BOLD, 50);
	    Graphics2D graphics2D = prepareGraphics(bufferedImage, font);
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
	
	public static Graphics2D prepareGraphics(BufferedImage bufferedImage, Font font) {
		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
	    graphics2D.setBackground(Color.GRAY);
	    graphics2D.clearRect(0, 0, 160, 195);
	    graphics2D.setPaint(Color.BLACK);
	    graphics2D.setFont(font);
	    
	    return graphics2D;
	}
	
	public static boolean isLatin(String inputString) {
		
		ArrayList<String> languageList = new ArrayList<String>(
			    Arrays.asList("zh", "km", "my", "ja", "tl", "lo", "ne", "ml", "th", "zh-TW"));
	   
    	if(languageList.contains(main.targetLang)) {
    		return false;
    	} else {
    		return true;
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
		
		if(fontMetrics.stringWidth(content)<=165) {
			graphics2D.drawString(content, leftBorder, (int)leftTopCornerLine);
		} else {
		
			for(int i = 0; i < wordArray.length; i++) {
				
				int futureLength = fontMetrics.stringWidth(currentContent) + fontMetrics.stringWidth(wordArray[i]);
				
				if(leftTopCornerLine < 195) {
	
					if(fontMetrics.stringWidth(currentContent)>=165) {
						if(fontMetrics.stringWidth(wordArray[i])<=165-fontMetrics.stringWidth(currentContent)) {
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
						if(futureLength<=160) {
							currentContent +=wordArray[i] + " ";
							if(i==wordArray.length-1) {
								graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
							}
						} else {
							graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
							leftTopCornerLine += lineHeight;
							currentContent ="";
							currentContent += wordArray[i] + " ";
							graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						}
						
					}
				} else {
					System.out.println("Input was cut, because it was too long.");
					break;
				}
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
		System.out.println(fontMetrics.stringWidth(content));

		
		for(int i = 0; i < characterArray.length; i++) {
			
			int futureLength = fontMetrics.stringWidth(currentContent) + fontMetrics.stringWidth(characterArray[i]);
			
			if(leftTopCornerLine < 195) {

				if(fontMetrics.stringWidth(currentContent)>=165) {
					if(fontMetrics.stringWidth(characterArray[i])<=165-fontMetrics.stringWidth(currentContent)) {
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
					if(futureLength<=165) {
						currentContent += characterArray[i];
						if(i==characterArray.length-1) {
							graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						}
					} else {
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
						leftTopCornerLine += lineHeight;
						currentContent ="";
						currentContent += characterArray[i];
						graphics2D.drawString(currentContent, leftBorder, (int)leftTopCornerLine);
					}
				}
			} else {
				System.out.println("Input was cut, because it was too long.");
				break;
			}
		}
	}
	
}

