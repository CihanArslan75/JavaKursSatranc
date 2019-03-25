package com.cihan.satranc.util;

import java.awt.Point;

public enum DirectionKnight {
		ENN(1,2) , 
		WNN(-1,2),
		ESS(1,-2),
		WSS(-1,-2),
		WWS(-2,-1),
		WWN(-2,1),
		EES(2,-1),
		EEN(2,1);
	 	
	    private int dx;
		private int dy;
		
		private DirectionKnight(int dx,int dy) {
			this.dx=dx;
			this.dy=dy;
		}
		public Point move(Point input) {
			Point result=(Point) input.clone();
			result.translate(dx, dy);
			return result;
		
		}	
		
}