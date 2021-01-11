package svenGeorgMain;

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

public class StringToImageConverter {
	
	String inputString;
	
	public static void createImage(String inputString) throws IOException{
        Integer width = 160;
        Integer height = 195;
        Font font = new Font("Serif", Font.PLAIN, 15);
        String content = inputString;
        saveImage(drawPic2(width, height, content, font));
      /* try {
		testIt();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	}
	public static BufferedImage drawPic2(Integer width, Integer height, String content, Font font) {
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = (Graphics2D)bi.getGraphics();
	    g2.setBackground(Color.GRAY);
	    g2.clearRect(0, 0, width, height);
	    g2.setPaint(Color.BLACK);

	    FontRenderContext context = g2.getFontRenderContext();
	    Rectangle2D bounds = font.getStringBounds(content, context);
	    double x = (width - bounds.getWidth()) / 2;
	    System.out.print("X:" + x);
	    double y = (height - bounds.getHeight()) / 2;
	    System.out.print("Y:" + y);
	    double ascent = - bounds.getY();
	    double baseY = y + ascent;

	    g2.drawString(content, (int)x, (int)baseY);
	    g2.dispose();

	    return bi;
	}
	public static BufferedImage drawPic1(String inputString) {
		 //create String object to be converted to image
	       String sampleText = inputString;
	 
	        //Image file name
	       String fileName = "Image";
	        
	        //create a File Object
	        File newFile = new File("./" + fileName + ".jpeg");
	         
	        //create the font you wish to use
	        Font font = new Font("Tahoma", Font.PLAIN, 25);
	        
	        //create the FontRenderContext object which helps us to measure the text
	        FontRenderContext frc = new FontRenderContext(null, true, true);
	         
	        //get the height and width of the text
	        java.awt.geom.Rectangle2D bounds = font.getStringBounds(sampleText, frc);
	        int w = 160;
	        int h = 195;
	        
	        //create a BufferedImage object
	       BufferedImage image = new BufferedImage(w, h,   BufferedImage.TYPE_INT_RGB);
	       Graphics2D g2d = image.createGraphics();
	       FontMetrics fm = g2d.getFontMetrics();
	       System.out.println(fm.stringWidth(sampleText));
	       
	        
	        //calling createGraphics() to get the Graphics2D
	        Graphics2D g = image.createGraphics();
	        
	        //set color and other parameters
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, w, h);
	        g.setColor(Color.BLACK);
	        g.setFont(font);
	             
	       g.drawString(sampleText, (float) bounds.getX(), (float) -bounds.getY());
	       
	      //releasing resources
	      g.dispose();
	      g2d.dispose();
	       
	        //creating the file
	       try {
			ImageIO.write(image, "png", newFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
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
	public static void testIt() throws Exception {

	    String yourText = "java2s.com abcdefghijklmnop";
	    BufferedImage bufferedImage = new BufferedImage(160, 195,
	        BufferedImage.TYPE_INT_RGB);
	    
	    Graphics graphics = bufferedImage.getGraphics();
	    graphics.setColor(Color.LIGHT_GRAY);
	    graphics.fillRect(0, 0, 200, 50);
	    graphics.setColor(Color.BLACK);
	    graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
	    graphics.drawString(yourText, 10, 25);
	    
	    ImageIO.write(bufferedImage, "jpg", new File(
	        "./image.jpg"));
	    
	    System.out.println("Image Created");
	  }
}
