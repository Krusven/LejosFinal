package svenGeorgMain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CoordinateSearcher {
	public static ArrayList<Integer> searchForColor(BufferedImage bufferedImage) {
		ArrayList<Integer> coordinateArray = new ArrayList<Integer>();
		int i = 0;
	    for (int x = 0; x < bufferedImage.getWidth(); ++x) {
	    	for (int y = 0; y < bufferedImage.getHeight(); ++y) {
		        if ((bufferedImage.getRGB(x, y) & 0x00FFFFFF) == 0x00000000) {
		        	
		        	
		        }
		        	
		    }
	    }
		return coordinateArray;
	}
	
}

