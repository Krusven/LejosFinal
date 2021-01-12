package workingLogic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class StringToImageConverter {
	
	String inputString;
	
	public static BufferedImage createImage(String inputString, int fontsize) throws IOException{
        Integer width = 160;
        Integer height = 195;
        //Font.decode(inputString);
        //addFont();
        //System.out.print(Font.decode(inputString));
        Font font = new Font("Arial", Font.PLAIN, fontsize);
        String content = inputString;
        BufferedImage image = createGraphics(width, height, content, font);
        saveImage(image);
        return image;
	}
	public static void addFont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Assets\\NotoSans-hinted\\NotoSans-Light.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	
	
	public static BufferedImage createGraphics(Integer width, Integer height, String content, Font font) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = (Graphics2D)bi.getGraphics();
	    g2.setBackground(Color.GRAY);
	    g2.clearRect(0, 0, width, height);
	    g2.setPaint(Color.BLACK);
	    FontRenderContext context = g2.getFontRenderContext();
	    
	    double baseY = getY(getBounds(font, content, context));
	    String noSpace = content;
	    noSpace = noSpace.replaceAll(" ", "");

	    if(getLanguageType(noSpace)) {
	    	drawLinesWords(g2, content, font, baseY);
	    } else {
	    	drawLinesCharacter(g2, content, font, baseY);
	    }
	    
	    g2.dispose();

	    return bi;
	}
	
	
	public static boolean getLanguageType(String language) {
		if(language.matches("\\p{IsLatin}+")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	public static double getY(Rectangle2D bounds) {
		
		    double ascent = - bounds.getY();
		    double baseY = 0 + ascent;
		    return baseY;
	}
	
	
	
	public static Rectangle2D getBounds(Font font, String content, FontRenderContext context) {
		Rectangle2D bounds = font.getStringBounds(content, context);
		return bounds;
	}
	
	
	
	public static void drawLinesCharacter(Graphics2D g, String content, Font font, double baseY) {
		System.out.println("CharacterLine");
		char[] charArray = content.toCharArray();
		int fontsize = font.getSize();
		
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = g.getFontMetrics(font).getHeight();
		int x = 5;
		String currentContent = "";
		double y = baseY;
		ArrayList<String> subStrings = new ArrayList<String>();
		int j = 0;
		for(int i = 0; i<charArray.length; i++) {
			if(j >=subStrings.size()) {
				subStrings.add(j, "");
			}
			currentContent = subStrings.get(j);
			currentContent += charArray[i];
			subStrings.set(j, currentContent);
			if(metrics.stringWidth(subStrings.get(j))>=145) {
				g.drawString(subStrings.get(j), x, (int)y);
				j++;
				currentContent ="";
				y += lineHeight;
			}
			if(y>=195) {
				System.out.println("Sorry passt net! Neu versuchen mit anderem Font");
				break;
			}
		}
	}
	public static void drawLinesWords(Graphics2D g, String content, Font font, double baseY) {
		System.out.println("WordLine");
		System.out.println(content);
		String[] wordArray = content.split(" ");
		int fontsize = font.getSize();
		
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = g.getFontMetrics(font).getHeight();
		int x = 5;
		String currentContent = "";
		double y = baseY;
		ArrayList<String> subStrings = new ArrayList<String>();
		int j = 0;
		for(int i = 0; i<wordArray.length; i++) {
			
			if(j >=subStrings.size()) {
				subStrings.add(j, "");
			}
			System.out.println("Substring j: "+subStrings.get(j));
			if(metrics.stringWidth(subStrings.get(j))>=150 && metrics.stringWidth(wordArray[j])>15) {
				g.drawString(subStrings.get(j), x, (int)y);
				currentContent ="";
				y += lineHeight;
			/*}else if(metrics.stringWidth(subStrings.get(j))>=145) {
				g.drawString(subStrings.get(j), x, (int)y);
				currentContent ="";
				y += lineHeight;*/
			} else {
				currentContent = subStrings.get(j);
				currentContent += " " + wordArray[i];
				System.out.println(currentContent);
				subStrings.set(j, currentContent);
				if(metrics.stringWidth(subStrings.get(j))<=145) {
					g.drawString(subStrings.get(j), x, (int)y);
					j++;
					currentContent ="";
					y += lineHeight;
				}
				
			}
			if(y>=195) {
				System.out.println("Sorry passt net! Neu versuchen mit anderem Font");
				break;
			}
		}
	}
	
	
	
	public static void saveImage(BufferedImage buffImg) {
		 String fileName = "omage";
         File newFile = new File("./" + fileName + ".jpeg");
         try {
			ImageIO.write(buffImg, "png", newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}

}
//lol

