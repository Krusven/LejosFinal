package workingLogic;

import java.awt.geom.Point2D;
import java.rmi.RemoteException;
import java.util.ArrayList;

import plott3r.Position2D;
import plott3r.Position3D;
import plott3r.Roboter;

public class Sketcher {
	
	
	/**
	 * Geht fertige Koordinatenliste durch und ruft Methode auf um entweder Punkt oder Linien zu zeichnen
	 * 
	 * @param coordinateArray
	 * @param roboter
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	public static void drawCoordinateArray(ArrayList<Point2D> coordinateArray, Roboter roboter) throws RemoteException, InterruptedException {

		for(int i = 0;i < coordinateArray.size(); i++) {
			
			double x = coordinateArray.get(i).getX();
			
			if(x > 99990 && i<coordinateArray.size()-2) {
				drawLine(coordinateArray.get(i+1),coordinateArray.get(i+2), roboter);
				i = i + 2;
			} else {
				drawDot(coordinateArray.get(i), roboter);
			}
		}
	}
	
	
	/**
	 * Zeichnet Linie
	 * 
	 * @param point2d
	 * @param point2d2
	 * @param roboter
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	public static void drawLine(Point2D point2d, Point2D point2d2, Roboter roboter) throws RemoteException, InterruptedException {
		
		int startX =(int) point2d.getX();
		int startY =(int) point2d.getY();
		
		int endX =(int) point2d2.getX();
		int endY =(int) point2d2.getY();
		
		roboter.moveToPosition(new Position3D(startX, startY, false), 50);
		roboter.moveToPosition(new Position3D(endX, endY, true), 50);
		roboter.zAchse.deaktiviere();
	}
	
	
	/**
	 * Zeichnen einzelnen Punkt
	 * @param point2d
	 * @param roboter
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	public static void drawDot(Point2D point2d, Roboter roboter) throws RemoteException, InterruptedException {
		
		int x =(int) point2d.getX();
		int y =(int) point2d.getY();

		roboter.moveToPosition(new Position3D(x, y, false), 50);
		roboter.moveToPosition(new Position3D(x, y, true), 50);
		roboter.zAchse.deaktiviere();
	}
	
	
	
}
