package svenGeorgMain;
import java.awt.Color;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
			StringToImageConverter.createImage("ABCDEFGHIT");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	
