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

public class StringToImageConverter {
	
	String inputString;
	
	public static BufferedImage createImage(String inputString, int fontsize) throws IOException{
        Integer width = 160;
        Integer height = 195;
        Font font = new Font("Serif", Font.PLAIN, fontsize);
        String content = inputString;
        BufferedImage image = createGraphics(width, height, content, font);
        saveImage(image);
        return image;
	}

	
	
	
	
	
	public static BufferedImage createGraphics(Integer width, Integer height, String content, Font font) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = (Graphics2D)bi.getGraphics();
	    g2.setBackground(Color.WHITE);
	    g2.clearRect(0, 0, width, height);
	    g2.setPaint(Color.BLACK);
	    FontRenderContext context = g2.getFontRenderContext();
	    
	    double baseY = getY(getBounds(font, content, context));

	    //g2.drawString(content, (int)x, (int)baseY);
	    drawLines(g2, content, font, baseY);
	    g2.dispose();

	    return bi;
	}
	
	
	
	public static double getY(Rectangle2D bounds) {
		 double y = (0);
		    System.out.println("Y:" + y);
		    double ascent = - bounds.getY();
		    System.out.println("Ascent:" + ascent);
		    double baseY = y + ascent;
		    System.out.println("BaseY:" + baseY);
		    return baseY;
	}
	
	
	
	public static Rectangle2D getBounds(Font font, String content, FontRenderContext context) {
		Rectangle2D bounds = font.getStringBounds(content, context);
		return bounds;
	}
	
	
	
	public static void drawLines(Graphics g, String content, Font font, double baseY) {
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
	
	
	
	public static void saveImage(BufferedImage buffImg) {
		 String fileName = "Image";
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
