package workingLogic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import plott3r.Position2D;
import plott3r.Position3D;
import plott3r.Roboter;

public class Sketcher {
	
	public static void drawLine(Position2D startCoordinate, Position2D endCoordinate, Roboter roboter) throws RemoteException, InterruptedException {
		
		int startX =(int) startCoordinate.getX();
		int startY =(int) startCoordinate.getY();
		
		int endX =(int) endCoordinate.getX();
		int endY =(int) endCoordinate.getY();
		
		roboter.moveToPosition(new Position3D(startX, startY, false), 50);
		roboter.moveToPosition(new Position3D(endX, endY, true), 50);
		roboter.zAchse.deaktiviere();
	}
	
	public static void drawDot(Position2D coordinate, Roboter roboter) throws RemoteException, InterruptedException {
		
		int x =(int) coordinate.getX();
		int y =(int) coordinate.getY();

		roboter.moveToPosition(new Position3D(x, y, false), 50);
		roboter.moveToPosition(new Position3D(x, y, true), 50);
		roboter.zAchse.deaktiviere();
	}
	
	public static void drawCoordinateArray(ArrayList<Position2D> coordinateArray, Roboter roboter) throws RemoteException, InterruptedException {

		for(int i = 0;i < coordinateArray.size(); i++) {
			
			double x = coordinateArray.get(i).getX();
			
			if(x > 99990) {
				drawLine(coordinateArray.get(i+1),coordinateArray.get(i+2), roboter);
				i = i + 2;
			} else {
				drawDot(coordinateArray.get(i), roboter);
			}
		}
	}
	
	
	//lol
	
}
