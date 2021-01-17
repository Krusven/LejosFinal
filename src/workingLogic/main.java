package workingLogic;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import lejos.hardware.Sound;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;
import plott3r.Position3D;
import plott3r.Roboter;

public class main {
	static String targetLang;
	public static void main(String args[]) throws Throwable {
		//Instanziierung des Roboters
		RemoteEV3 remoteev3 = new RemoteEV3("10.0.1.1");
		plott3r.Roboter roboter = new Roboter(remoteev3);
				
		try {
			//Aufrufen der Übersetzer Methode
			String translatedText =TranslationClassGoogle.translateText();
			
			//Methoden die BufferedImage erstellen, Text einfügen und Koordinatenarray erstellen
			ArrayList<Point2D> coordinateArray = CoordinateSearcher.searchForColor(TextToImage.createImage(translatedText));

			//Erstellt neue Arrays, die Linien enthalten
			ArrayList<Point2D> arrayLinesX = CoordinatePrinter.markLineX(coordinateArray);
			ArrayList<Point2D> arrayLinesY = CoordinatePrinter.markLineY(coordinateArray);

			//Koordinaten mit Punkten und Linien werden gezeichnet
			Sketcher.drawCoordinateArray(arrayLinesX, roboter);
			Sketcher.drawCoordinateArray(arrayLinesY, roboter);

			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			roboter.stop();
			roboter.getxAchse().close();
			roboter.getyAchse().close();
			roboter.getzAchse().close();
		}
		
		
	}
	//Liste aller in Java zugänglichen Fonts
	public static void listFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String []fontFamilies = ge.getAvailableFontFamilyNames();
        for (int i = 0; i <fontFamilies.length; i++) 
        {
            System.out.println(fontFamilies[i]);
        }
	}
	//Um Koorinatenarrays ausdrucken zu können
	public static void listCoordinates(ArrayList<Point2D> myArrayList) {
		for(int i = 0; i < myArrayList.size(); i++) {
			System.out.println(myArrayList.get(i));
		}
	}
	//um in der Translation Klasse die Zielsprache festlegen zu können (Hier als Globale Variable)
	public static void setTargetLang(String targetLangInput) {
		targetLang = targetLangInput;
	}
}
	
//S