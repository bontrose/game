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
	
	public void sleep(){//
		PlayerCharacter pc = new PlayerCharacter(0, null);
		map[x][y].sleep();
		FlizbazArrayList<NonPlayerCharacter> Monsters = null;
		
		int chanceOfMonstersGroup = Die.nextInt(3);
		int chanceOfMonstersAppearing = Die.nextInt(6);
		
		switch(chanceOfMonstersGroup)
		{
		case 0 : Monsters = characters.monsters1;
			break;
		case 1 : Monsters = characters.monsters2;
			break;
		case 2 : Monsters = characters.monsters3;
			break;
		}
		
		if(chanceOfMonstersAppearing == 5)
		{
			NonPlayerCharacter npc = new NonPlayerCharacter(Monsters);
		}
		else
		{
			pc.currentHP += 1;
		}
	}
	public static void move(char d2){
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
	}
	public static void run(){
		//call the hit, capeesh?
		map[x][y].setmonsters(Monsters);
		move(d);
		
	}
	public static int search(int wisdom){
		int cash=Die.nextInt(20)+1;
		map[x][y].setLooted(true);
		if (wisdom<cash){
			cash=0;
		}
		return cash;
	}
}
