package workingLogic;
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

public class main {
	public static void main(String args[]) throws Throwable {
		//RemoteEV3 remoteev3 = new RemoteEV3("10.0.1.1");
		//plott3r.Roboter roboter = new Roboter(remoteev3);
		try {
			
			//is simply dummy text of t 1500s galley of type
			String translatedText = "Hallo mein name ist Georg und ich bin blöd";//TranslationClassGoogle.translateText();
			ArrayList<Point2D> myArrayList = CoordinateSearcher.searchForColor(StringToImageConverter.createImage(translatedText, 25));
			ArrayList<Point2D> myNewArrayList = CoordinatePrinter.markLine(myArrayList);
			//Sketcher.drawCoordinateArray(myNewArrayList, roboter);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//roboter.getxAchse().close();
			//roboter.getyAchse().close();
			//roboter.getzAchse().close();
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
	public static void listCoordinates(ArrayList<Point2D> myArrayList) {
		for(int i = 0; i < myArrayList.size(); i++) {
			System.out.println(myArrayList.get(i));
		}
		for(int i = 0; i < myArrayList.size(); i++) {
		System.out.println(myArrayList.get(i));
		}
	}
}
	
