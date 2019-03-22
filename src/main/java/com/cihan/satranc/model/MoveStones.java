package com.cihan.satranc.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.cihan.satranc.runner.Runner;
import com.cihan.satranc.util.*;

public class MoveStones {
	//queen   vezir
	//obstacles kendi takımının taşları
	
	public List<Point> pointList  = new ArrayList<Point>();   // taşın gidebileceği yerler
	public List<Point> pointPawnList = new ArrayList<Point>();   // piyonun karşı takımdan alabileceği koordinatlar
	Direction[][] KnightAttackArray = { {Direction.E,Direction.N,Direction.N},
										{Direction.W,Direction.N,Direction.N},
										{Direction.E,Direction.S,Direction.S},
										{Direction.W,Direction.S,Direction.S},
										{Direction.W,Direction.W,Direction.S},
										{Direction.W,Direction.W,Direction.N},
										{Direction.E,Direction.E,Direction.S},
										{Direction.E,Direction.E,Direction.N}};
	public List<Point> getPointList() {
		return pointList;
	}
		
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
		{  //Arrays.stream(Direction.values()).filter(s->s.toString().length()==1).forEach(System.out::println);
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().length()==1).mapToInt(d->queenAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.BISHOP.toString()))
		{   
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().length()==2).mapToInt(d->queenAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.KNIGHT.toString()))
		{  
			summ=Arrays.stream(KnightAttackArray).mapToInt(d->knightAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.PAWN.toString()) && Runner.player==1)
		{  
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().contains("N") ).mapToInt(d->pawnAttack(n, stone, obstacles,d)).sum();
		}
		else if(stoneType.equals(Stones.PAWN.toString()) && Runner.player==2)
		{  
			summ=Arrays.stream(Direction.values()).filter(s->s.toString().contains("S") ).mapToInt(d->pawnAttack(n, stone, obstacles,d)).sum();
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
			pointList.add(point);
			point = direction.move(point);
		}
		return result;
	}
	
	private int kingAttack(int n,Point stone ,Set<Point> obstacles,Direction direction) {
		int result = 0;   
		Point point =direction.move(stone);
		if (isInside(n,point) && !obstacles.contains(point)  ) 
		{   
			result++;
			pointList.add(point);
			point = direction.move(point);
		}
		return result;
	}
	
	private int knightAttack(int n,Point stone ,Set<Point> obstacles,Direction[] direction) {
		int result = 0;  
		int sayi=1;   
		Point point = null ;
		for (Direction direction1 : direction ) {
		      if(sayi==1 ) 
		      { 
		    	  point =direction1.move(stone);  // Her seferinde 3 hamle geliyor. ilk hamlede taşı ilerletip 
		      } 
		      else   
			  {   				
				point = direction1.move(point); // sonraki iki hamlede ilerleyen taşı ilerletiyoruz.
			  }
			  sayi++;
		}
		
		if(isInside(n,point) && !obstacles.contains(point))
		 {
			result++;
			pointList.add(point);
		 }
		 
		return result;
	}
	
	private int pawnAttack(int n,Point stone ,Set<Point> obstacles,Direction direction ) {
		int result = 0;
		Point point =direction.move(stone);
		if (isInside(n,point) && !obstacles.contains(point) && ( direction.toString().equals("N") || direction.toString().equals("S"))) {   
			result++;
			if(Runner.playCount.equals("FIRST")) 
			{
				point = direction.move(point);  //ilk seferde gidebileceği 2. hamle
			}
			pointList.add(point);             // piyonun gidebileceği koordinat (il hamlede 2 , sonrakilerde 1 (düz))
			
		}
		else if (isInside(n,point) && !obstacles.contains(point) ) {   
			result++; 
			pointPawnList.add(point);       // piyonun karşı takım ın taşını alabileceği koordinatlar (Çapraz)
		}
		return result;
	}
	
	private boolean isInside(int n,Point p) {
		return 0 <= p.getX() && p.getX()<=n && 0<= p.getY() && p.getY()<=n;
	}
}	
	
	
	
