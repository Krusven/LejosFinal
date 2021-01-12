package svenGeorgMain;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import lejos.hardware.Sound;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;
import plott3r.Roboter;
import workingLogic.CoordinateSearcher;
import workingLogic.TranslationClassGoogle;

public class main {
	public static void main(String args[]) throws InterruptedException {
		/*try {
			Roboter roboter = new Roboter(new RemoteEV3("10.0.1.1"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String translatedText = TranslationClassGoogle.translateText();
		try {
			int fontsize = 15;
			ArrayList<Point2D> myArray = CoordinateSearcher.searchForColor(StringToImageConverter.createImage(translatedText, fontsize));
			for(int i = 0; i < myArray.size(); i++) {
				System.out.println(myArray.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void listFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String []fontFamilies = ge.getAvailableFontFamilyNames();
        for (int i = 0; i <fontFamilies.length; i++) 
        {
            System.out.println(fontFamilies[i]);
        }
	}
}
	
