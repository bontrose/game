package group_project;

import java.io.IOException;
import java.util.Random;

public class Move_Search_Run {
	public static int x;
	public static int y;
	public static char d;
	public static room[][] Map= new room[0][0];
	public static FlizbazArrayList Monster;
	public static void main(String[] args) {
		x=1;
		y=1;
		d='n';
		room in_progress;
		try {
			Map = load.newMap("small.maze");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final int size = Map.length;
		in_progress=Map[x][y];
		System.out.println(size);
		System.out.println("start"+x+","+y);
		in_progress=run();
		System.out.println("run  "+x+","+y);
		in_progress=run();
		System.out.println("run  "+x+","+y);
		in_progress=move('N');
		System.out.println("move "+x+","+y);
		in_progress=move('S');
		System.out.println("move "+x+","+y);
		in_progress=move('E');
		System.out.println("move "+x+","+y);
		in_progress=move('W');
		System.out.println("move "+x+","+y);
		System.out.println(search(10));
	}	
	
	public static room run(){
		//call the hit, capeesh?
		Map[x][y].setmonsters(Monster);
		return move(d);
		
	}
	
	public static room move(char d2){
		d2=Character.toUpperCase(d2);
		if(d2=='N'){
			x-=1;
			d='S';
		} else if(d2=='S'){
			x+=1;
			d='N';
		} else if(d2=='E'){
			y+=1;
			d='W';
		} else if(d2=='W'){
			y-=1;
			d='E';
		} else{
			
		}
		return Map[x][y];
	}
	
	public static int search(int wisdom){
		Random Die = new Random();
		int cash=Die.nextInt(20)+1;
		Map[x][y].setLooted(true);
		if (wisdom<cash){
			cash=0;
		}
		return cash;
	}

}
