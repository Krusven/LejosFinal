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
			int fontsize = 15;
			StringToImageConverter.createImage(" is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type", fontsize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		public void theFunktion() {
			TheGraphics jojo = new TheGraphics();
			try {
				System.out.print(searchForColor(jojo.createImage(myInput.getInput())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static int[] searchForColor(BufferedImage bi) {
		    for (int x = 0; x < bi.getWidth(); ++x)
			    for (int y = 0; y < bi.getHeight(); ++y) {
			        if ((bi.getRGB(x, y) & 0x00FFFFFF) == 0x00000000)
			        	return new int[]{x, y};
			    }
		    return null;
		}
	}*/
		
	}
}
	
