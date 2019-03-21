package com.cihan.satranc.runner;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import com.cihan.satranc.model.MoveStones;
import com.cihan.satranc.util.*;

public class Runner {
	public static void main(String[] args) {
		MoveStones s= new MoveStones();
		int n= 8;
		Point stone = new Point(4, 3);
		Set<Point> obstacles= new HashSet<>();
		obstacles.add(new Point(5, 5));
		obstacles.add(new Point(4, 2));
		obstacles.add(new Point(2, 3));
		int result =s.stonesAttack(n, stone, obstacles,Stones.BISHOP.toString());
		System.out.println("aaa:"+s.pointRookList);
		System.out.println("result:"+result);
	}

}
