package com.cihan.satranc.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import com.cihan.satranc.util.*;

public class MoveStones {
	//queen   vezir
	//obstacles kendi takımının taşları
	
	public List<Point> pointKingList  = new ArrayList<Point>();
	public List<Point> pointQueenList = new ArrayList<Point>();
	public List<Point> pointRookList  = new ArrayList<Point>();
	
	public int stonesAttack(int n,Point stone ,Set<Point> obstacles,String stoneType) {
		int summ=0;
		if(stoneType.equals(Stones.KING.toString()))
		{
			summ=Arrays.stream(Direction.values()).mapToInt(d->kingAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.QUEEN.toString()))
		{
			summ=Arrays.stream(Direction.values()).mapToInt(d->queenAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.ROOK.toString()))
		{   Arrays.stream(Direction.values()).filter(s->s.toString().length()==1).forEach(System.out::println);
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().length()==1).mapToInt(d->queenAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.BISHOP.toString()))
		{   Arrays.stream(Direction.values()).filter(s->s.toString().length()==2).forEach(System.out::println);
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().length()==2).mapToInt(d->queenAttack(n, stone, obstacles,d)).sum();
		}
		return summ;	
	}
	
	
	private int queenAttack(int n,Point stone ,Set<Point> obstacles,Direction direction) {
		int result = 0;   //Her directionda kaç nokta var onları bulup topluyor. 
		Point point =direction.move(stone);
		// her direction için while yeniden girer. Kendi elemanına denk geldiğinde whiledan çıkar. 
		// Dolayısıyla kendi elemanının bulunduğu directiondaki arkadaki diğer noktalara bakmaz.
		while (isInside(n,point) && !obstacles.contains(point) ) {   
			result++;
			pointQueenList.add(point);
			point = direction.move(point);
		}
		return result;
	}
	
	private int kingAttack(int n,Point stone ,Set<Point> obstacles,Direction direction) {
		int result = 0;   
		Point point =direction.move(stone);
		if (isInside(n,point) && !obstacles.contains(point) ) 
		{   
			result++;
			pointKingList.add(point);
			point = direction.move(point);
		}
		return result;
	}
	
	
	private boolean isInside(int n,Point p) {
		return 0 <= p.getX() && p.getX()<=n && 0<= p.getY() && p.getY()<=n;
	}
}	
	
	
	
