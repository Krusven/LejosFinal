package workingLogic;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CoordinatePrinter {

	public static ArrayList<Point2D> markLine(ArrayList<Point2D> coordinateList) {

		ArrayList<Point2D> newCoordinateList = new ArrayList<Point2D>();
		try {
			for(int i=0; i<coordinateList.size(); i++) {
				
				double x = coordinateList.get(i).getX();
				double y = coordinateList.get(i).getY();
				double nextX = coordinateList.get(i+1).getX();
				double nextY = coordinateList.get(i+1).getY();

				
				if(nextX == x) {
					
					if(y+1 == nextY) {
						
						newCoordinateList.add(new Point2D.Double(99999,99999));
						newCoordinateList.add(new Point2D.Double(x,y));
						
						while(y+1 == nextY && x == nextX) {
							
							x = coordinateList.get(i).getX();
							y = coordinateList.get(i).getY();
							nextX = coordinateList.get(i+1).getX();
							nextY = coordinateList.get(i+1).getY();
							i++;
						}
						
						newCoordinateList.add(new Point2D.Double(x,y));
					} else {
						
						newCoordinateList.add(new Point2D.Double(x,y));
					}
					
				} else {
					
					newCoordinateList.add(new Point2D.Double(x,y));
				}
			}
			
		}catch (Exception e){
			
		}

		return newCoordinateList;
	}
	
}

	
