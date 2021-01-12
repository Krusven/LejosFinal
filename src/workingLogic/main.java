package workingLogic;
import java.awt.Color;
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
	public static void main(String args[]) throws InterruptedException {
		/*try {
			Roboter roboter = new Roboter(new RemoteEV3("10.0.1.1"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//TranslationClassGoogle.translateText();
		try {
			int fontsize = 15;
			ArrayList<Point2D> myArrayList = CoordinateSearcher.searchForColor(StringToImageConverter.createImage(" is simply dummy text of t 1500s galley of type", fontsize));
			ArrayList<Point2D> myNewArrayList = CoordinatePrinter.markLine(myArrayList);
			CoordinatePrinter.markLine(myArrayList);
//			for(int i = 0; i < myArrayList.size(); i++) {
//				System.out.println(myArrayList.get(i));
//			}
//			for(int i = 0; i < myNewArrayList.size(); i++) {
//				System.out.println(myNewArrayList.get(i));
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	
