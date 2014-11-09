package group_project;

import java.io.*;
import java.util.*;


public class game {
	static room[][] map;
	static FlizbazArrayList<PlayerCharacter> Players;
	static FlizbazArrayList<NonPlayerCharacter> Monsters;
	static int cash=0;
	static char d;
	static int x = 0;
	static int y = 0; // position
	static Random Die = new Random();
	
	
	public static void main(String[] args) {
		init();
		boolean quit=false;
		boolean turnused=false;
		Scanner in = new Scanner(System.in);
		String yn="";
		do{
			if(!turnused&&!map[x][y].isLooted()){
				System.out.print("would you like to loot? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					//search(10);
				}
			}
			if(!turnused&&map[x][y].isExit()){
				System.out.print("would you like to Exit? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					quit=true;
				}
			}
			if(!turnused&&map[x][y].isEast()){
				System.out.print("would you like to Exit? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					//move(e);
				}
			}
			if(!turnused&&map[x][y].isWest()){
				System.out.print("would you like to Exit? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					//move(w);
				}
			}
			if(!turnused&&map[x][y].isNorth()){
				System.out.print("would you like to Exit? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					//move(n);
				}
			}
			if(!turnused&&map[x][y].isSouth()){
				System.out.print("would you like to Exit? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn=="y"||yn=="yes"){
					turnused=true;
					//move(s);
				}
			}
			
		}while(!quit);
		System.out.println("thank you for playing!");
	}
	public static void init(){//makes players, rooms, finds what room to start in
		boolean mapped=false;
		String name;
		int PCnum=0;
		int claz=-1;
		Scanner in = new Scanner(System.in);
		do{
			System.out.print("file name: ");
			name=in.next();
			try {
				map=load.newMap(name);
				mapped=true;
			} catch (IOException e) {
				System.out.println("bad file name");
			}
		}while(!mapped);
		do{
			System.out.print("number of players?(1-6): ");
			try{
			PCnum=in.nextInt();
			}catch(InputMismatchException e){
				in = new Scanner(System.in);
			}
		}while (PCnum<1||PCnum>6);
		PlayerCharacter playa;
		Players= new FlizbazArrayList(PCnum);
		for(int x2=0; x2<PCnum; x2++){
			System.out.print("player "+(x2+1)+" name: ");
			name=in.next();
			System.out.println("0:Fighter");
			System.out.println("1:Theif");
			System.out.println("2:Wizard");
			do{
				System.out.print("class number): ");
				try{
				claz=in.nextInt();
				}catch(InputMismatchException e){
					in = new Scanner(System.in);
				}
			}while (claz<0||claz>2);
			playa = new PlayerCharacter(claz, name);
			Players.add(playa);
		}
		in.close();
		for(int x2=0;x2<map.length;x2++){
			for(int y2=0;y2<map.length;y2++){
				if(map[x][y].isEntrance()){
					x=x2;
					y=y2;
				}
			}
		}
	}
}
