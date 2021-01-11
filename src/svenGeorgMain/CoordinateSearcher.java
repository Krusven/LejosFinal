package svenGeorgMain;

import java.awt.image.BufferedImage;

public class CoordinateSearcher {
	
	public static int[] searchForColor(BufferedImage bufferedImage) {
	    for (int x = 0; x < bufferedImage.getWidth(); ++x) {
	    	for (int y = 0; y < bufferedImage.getHeight(); ++y) {
		        if ((bufferedImage.getRGB(x, y) & 0x00FFFFFF) == 0x00000000)
		        	return new int[]{x, y};
		    }
	    }
		return null;
	}
	
}

