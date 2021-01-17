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

public class TextToImage {
	

	/**
	 * Initialisierungsmethode und ruft methode zum speichern des BufferedImage auf
	 * 
	 * @param inputString
	 * @return Fertiges BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage createImage(String inputString) throws IOException{
        
		BufferedImage image = createGraphics(inputString);
        saveImage(image);
        
        return image;
	}
	
	/**
	 * Erstellt:
	 * - BufferedImage mit den richtigen Maßen
	 * - Graphics2D aus BufferedImage und Font
	 * 
	 * Ruft Methode auf um Referenzpixel zu erstellen
	 * 
	 * unterscheidet zwischen Zeichen und Wortsprachen
	 * befreit Ressourcen die von Graphics2D reserviert waren
	 * 
	 * @param inputString
	 * @return Fertiges BufferedImage
	 */
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
	
	/**
	 * Erstellt Referenzpixel (baseY ist die Koordinate der oberen linken Ecke, die sich je nach Fontgröße verschiebt)
	 * 
	 * @param bounds
	 * @return Referenzpixel
	 */
	public static double getY(Rectangle2D bounds) {
		
	    double ascent = - bounds.getY();
	    double baseY = 0 + ascent;
	    return baseY;
	}
	
	
	/**
	 * Erstellt den Render kontext und die durch Font und Renderkontext definierte Umgebung
	 * 
	 * @param font
	 * @param content
	 * @param graphics2D
	 * @return bounds
	 */
	public static Rectangle2D getBounds(Font font, String content, Graphics2D graphics2D) {
		FontRenderContext context = graphics2D.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(content, context);
		
		return bounds;
	}
	
	/**
	 * Erstellt Grafik und setzt dessen Eigenschaften
	 * 
	 * @param font
	 * @param content
	 * @param graphics2D
	 * @return Grafik mit "richtigen" Eigenschaften
	 */
	public static Graphics2D prepareGraphics(BufferedImage bufferedImage, Font font) {
		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
	    graphics2D.setBackground(Color.GRAY);
	    graphics2D.clearRect(0, 0, 160, 195);
	    graphics2D.setPaint(Color.BLACK);
	    graphics2D.setFont(font);
	    
	    return graphics2D;
	}
	
	/**
	 * Unterscheidet zwischen Sprachen, die auf Wörtern oder Zeichen basieren
	 * 
	 * @param inputString
	 * @return true(=Zeichensprache), false(=Wortsprache)
	 */
	public static boolean isLatin(String inputString) {
		
		ArrayList<String> languageList = new ArrayList<String>(
			    Arrays.asList("zh", "km", "my", "ja", "tl", "lo", "ne", "ml", "th", "zh-TW"));
	   
    	if(languageList.contains(main.targetLang)) {
    		return false;
    	} else {
    		return true;
    	}
	    
	}
	
	
	/**
	 * Speichert BufferedImage als PNG, um dieses einsehen zu können
	 * @param bufferedImage
	 * @throws IOException
	 */
	public static void saveImage(BufferedImage bufferedImage) throws IOException {
		
		ImageIO.write(bufferedImage, "png", new File("./Image.jpeg"));
	}
	
	
	/**
	 * Gibt die Höhe iner Linie zurück (ändert sich je nach Schriftgröße)
	 * 
	 * @param font
	 * @param g
	 * @return Linienhöhe
	 */
	public static int getHeight(Font font, Graphics2D g) {
		
		int lineHeight = g.getFontMetrics(font).getHeight();
		return lineHeight;
	}
	
	
	/**
	 * Trennt eingegebenen Text nach Leerzeichen, um Wörter nicht in der Mitte zu trennen
	 * und sorgt dafür, dass diese in den Rahmen passen.
	 * Falls sie zu breit sind, werden Zeilenumbrüche eingefügt.
	 * Wird die Höhe des Gesambildes überschritten, wird eingefügt was passt und der Rest des Textes ignoriert
	 * 
	 * 
	 * @param graphics2D
	 * @param content
	 * @param font
	 * @param baseY
	 */
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
	
	
	/**
	 * Trennt eingegebenen Text nach Zeichen.
	 * Bildet aus Zeichen Zeilen sorgt dafür, dass diese in den Rahmen passen.
	 * Falls sie zu breit sind, werden Zeilenumbrüche eingefügt.
	 * Wird die Höhe des Gesambildes überschritten, wird eingefügt was passt und der Rest des Textes ignoriert
	 * 
	 * @param graphics2D
	 * @param content
	 * @param font
	 * @param baseY
	 */
	public static void drawLinesAsCharacters(Graphics2D graphics2D, String content, Font font, double baseY) {
		int lineHeight = getHeight(font, graphics2D);
		int leftBorder = 5;
		String[] characterArray = content.split("");
		FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
		String currentContent = "";
		double leftTopCornerLine = baseY;
		

		
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

