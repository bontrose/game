package group_project;

import java.io.*;
import java.util.*;


public class game {
	static room[][] map;
	static FlizbazArrayList<characters> Players;
	static FlizbazArrayList<characters> Monsters;
	static char d;
	private static int x = 0;
	private static int y = 0; // position
	static Random Die = new Random();
	private static boolean turnused=false;
	private static String yn="";
	
	public static void main(String[] args) {
		init();
		boolean quit=false;
		Scanner in = new Scanner(System.in);
		
		while(!(quit)){
			if(map[x][y].isFight()){
				Gameplay.fight(characters.party, map[x][y].getmonsters());
			}
			//Fight has to first and isEmpty second
			if(characters.party.isEmpty()){
				System.out.println("EVERYONE IS DEAD\n     YOU\n     LOSE");
				quit = true;
				break;
			}
			if(!turnused&&!map[x][y].isLooted()){
				System.out.print("would you like to loot? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn.equals("y")||yn.equals("yes")){
					turnused=true;
					Gameplay.search(map, 10, x, y);
					map[x][y].setSearch(true);
					System.out.println("Your cash is now " + Gameplay.cash);
				}
			}
			if(!turnused&&map[x][y].isExit()){
				System.out.print("would you like to Exit the dungeon? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn.equals("y")||yn.equals("yes")){
					quit=true;
					turnused=true;
				}
			}
			if(!turnused&&map[x][y].isEast()){
				System.out.print("would you like to Exit east? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				moveAsTurn("east");
			}
			if(!turnused&&map[x][y].isWest()){
				System.out.print("would you like to exit west? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				moveAsTurn("west");
			}
			if(!turnused&&map[x][y].isNorth()){
				System.out.print("would you like to exit north? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				moveAsTurn("north");
			}
			if(!turnused&&map[x][y].isSouth()){
				System.out.print("would you like to exit south? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				moveAsTurn("south");
			}
			turnused = false;
		}
		System.out.println("thank you for playing!");
		in.close();
	}
	public static void init(){//makes players, rooms, finds what room to start in
		boolean mapped=false;
		String name;
		x = 0;
		y = 0;
		int PCnum=0;
		int claz=-1;
		Scanner in = new Scanner(System.in); //Do not close within init(), it will close system.in and not reopen in main
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
		Players= new FlizbazArrayList<characters>(PCnum);
		for(int x2=0; x2<PCnum; x2++){
			System.out.print("player "+(x2+1)+" name: ");
			name=in.next();
			System.out.println("0:Fighter");
			System.out.println("1:Thief");
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
		for(int x2=0;x2<map.length;x2++){
			for(int y2=0;y2<map.length;y2++){
				if(map[x][y].isEntrance()){
					x=x2;
					y=y2;
				}
			}
		}
	}
	
	public static void moveAsTurn(String direction){
		if(yn.equals("y")||yn.equals("yes")){
			turnused=true;
			System.out.println("You moved " +direction + ".");
			Gameplay.move(map, 's', x, y);
			if(direction.equals("north")){
				x -= 1;
			} else if(direction.equals("south")){
				x += 1;
			} else if(direction.equals("east")){
				y += 1;
			} else if(direction.equals("west")){
				y -= 1;
			}
			
			System.out.println("x: " + x + " y: " + y);
		}
	}
	
}
