package workingLogic;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CoordinatePrinter {
	
	 /**
	 * markLineX durchsucht Array und identifiziert nebeneinanderliegende Koordinaten.
	 * Diese werden mit Startpunkt(x1,y1) und Endpunkt(x2,y2) zusammengefasst und durch (99999,99999) von anderen Punkten getrennt.
	 * z.B.:
	 * Punkte oder Linien
	 * (99999, 99999)   <-- Weiß, dass jetzt zwei Koordinaten kommen die eine Linie darstellen
	 * Startpunkt der Linie
	 * Endpunkt der Linie
	 * (99999,99999)    <-- Linie zu ende
	 * Weitere Punkte
	 * 
	 * 
	 * 
	 * @param coordinateList
	 * @return Koordinatenliste, wo Linien identifiziert sind
	 */
	public static ArrayList<Point2D> markLineX(ArrayList<Point2D> coordinateList) {

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
	/**
	 * Koordinaten im Array werden erst nach Y sortiert.
	 * 
	 * markLineY durchsucht Array und identifiziert nebeneinanderliegende Koordinaten.
	 * Diese werden mit Startpunkt(x1,y1) und Endpunkt(x2,y2) zusammengefasst und durch (99999,99999) von anderen Punkten getrennt.
	 * z.B.:
	 * Punkte oder Linien
	 * (99999, 99999)   <-- Weiß, dass jetzt zwei Koordinaten kommen die eine Linie darstellen
	 * Startpunkt der Linie
	 * Endpunkt der Linie
	 * (99999,99999)    <-- Linie zu ende
	 * Weitere Punkte
	 * 
	 * 
	 * 
	 * @param coordinateList
	 * @return Koordinatenliste, wo Linien identifiziert sind
	 */
	
	public static ArrayList<Point2D> markLineY(ArrayList<Point2D> coordinateInputList) {

		ArrayList<Point2D> newCoordinateList = new ArrayList<Point2D>();
		ArrayList<Point2D> coordinateList = new ArrayList<Point2D>();
		coordinateList = sortByYEasy(coordinateInputList);
		
		
		try {
			for(int i=0; i<coordinateList.size(); i++) {
				
				double x = coordinateList.get(i).getX();
				double y = coordinateList.get(i).getY();
				double nextX = coordinateList.get(i+1).getX();
				double nextY = coordinateList.get(i+1).getY();

				
				if(nextY == y) {
					
					if(x+1 == nextX) {
						
						newCoordinateList.add(new Point2D.Double(99999,99999));
						newCoordinateList.add(new Point2D.Double(x,y));
						
						while(x+1 == nextX && y == nextY) {
							
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
	public static ArrayList<Point2D> sortByY(ArrayList<Point2D> arrayY) {
		 int n = arrayY.size(); 
		 
		 
	        for (int i = 0; i < n-1; i++) 
	            for (int j = 0; j < n-i-1; j++) 
	                if (arrayY.get(j).getY() >= arrayY.get(j+1).getY()) 
	                { 
	                	
	                    Point2D temp = new Point2D.Double(arrayY.get(j).getX(), arrayY.get(j).getY());
	                    arrayY.set(j,arrayY.get(j+1)); 
	                    arrayY.set(j+1, temp); 
	                    
	                } 
	        
	        return arrayY;
		
	}
	/**
	 * Sortierfunktion um nach Y zu sortieren
	 * Unser Ansatz 
	 * 
	 * @param arrayY
	 * @return nach Y sortiertes Array
	 */
	public static ArrayList<Point2D> sortByYEasy(ArrayList<Point2D> arrayY) {
		ArrayList<Point2D> arrayYY = new ArrayList<Point2D>();
		arrayYY = arrayY;
		Collections.sort(arrayYY, new Comparator<Point2D>() {
				public int compare(Point2D p1, Point2D p2) {
					return Double.compare(p1.getY(), p2.getY());
		}
		
		});
		return arrayYY;
		
	}
	/**
	 * Sortier funktion um nach Y zu sortieren
	 * Gerlach und Florian Ansatz
	 * 
	 * @param arrayY
	 * @return nach Y sortiertes Array
	 */
	public static void listCoordinates(ArrayList<Point2D> myArrayList) {
		for(int i = 0; i < myArrayList.size(); i++) {
			System.out.println(myArrayList.get(i));
		}
		
	}
}

	
