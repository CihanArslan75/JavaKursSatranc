package com.cihan.satranc.util;

import java.awt.Point;
import java.util.Arrays;

public enum Direction {
		N(0,1) ,  // values N, NE
		NE(1,1),
		E(1,0),
		SE(1,-1),
		S(0,-1),
		SW(-1,-1),
		W(-1,0),
		NW(-1,1);
		private int dx;
		private int dy;
		
		private Direction(int dx,int dy) {
			this.dx=dx;
			this.dy=dy;
		}
	
		public Point move(Point input) {
			Point result=(Point) input.clone();
			result.translate(dx, dy);
			return result;
		
		}	
	
}
