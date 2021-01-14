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
//lol
public class main {
	static String targetLang = "de";
	public static void main(String args[]) throws Throwable {
		//RemoteEV3 remoteev3 = new RemoteEV3("10.0.1.1");
		//plott3r.Roboter roboter = new Roboter(remoteev3);
		
		try {
			
			String translatedText = "Ich werde ihm ein Angebot machen, das er nicht ablehnen kann."; //TranslationClassGoogle.translateText();
			ArrayList<Point2D> myArrayList = CoordinateSearcher.searchForColor(TextToImage.createImage(translatedText));
			ArrayList<Point2D> myNewArrayList = CoordinatePrinter.markLine(myArrayList);
			//Sketcher.drawCoordinateArray(myNewArrayList, roboter);

		} catch (Throwable e) {
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
	public static void setTargetLang(String targetLangInput) {
		targetLang = targetLangInput;
	}
}
	
//S