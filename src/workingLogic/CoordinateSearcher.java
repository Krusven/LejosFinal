package workingLogic;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CoordinateSearcher {
	/**
	 * Sucht im BufferedImage nach schwarzen Pixeln und gibt diese als Koordinaten Array zurück
	 * 
	 * @param bufferedImage
	 * @return Koordinaten Array
	 */
	public static ArrayList<Point2D> searchForColor(BufferedImage bufferedImage) {
		
		ArrayList<Point2D> coordinateArray = new ArrayList<Point2D>();
		
	    for (int x = 0; x < bufferedImage.getWidth(); ++x) {
	    	
	    	for (int y = 0; y < bufferedImage.getHeight(); ++y) {
	    		
		        if ((bufferedImage.getRGB(x, y) & 0x00FFFFFF) == 0x00000000) {
		        	
		        	Point2D p = new Point2D.Double(x,y);
		        	coordinateArray.add(p);
		        }
	    	}
	    }
		return coordinateArray;
	}
	
}

