package group_project;

import java.io.*;
import java.util.*;


public class game {
	static room[][] map;
	static FlizbazArrayList<characters> Players;
	static FlizbazArrayList<characters> Monsters;
	static char d;
	public static int x = 0;
	public static int y = 0; // position
	static Random Die = new Random();
	private static boolean turnused=false;
	private static String yn="";
	static GameScreen screen;
	
	public static void main(String[] args) {
		 screen =new GameScreen();
		init();
		boolean quit=false;
		Scanner in = new Scanner(System.in);
		
		while(!(quit)){
			if(map[x][y].isFight()){
				map[x][y].setFight(false);
				Gameplay.fight(characters.party, map[x][y].getmonsters());
			}
			//Fight has to first and isEmpty second
			if(characters.party.isEmpty()){
				GameScreen.addText("EVERYONE IS DEAD YOU LOSE");
				quit = true;
				break;
			}
			if(!turnused&&!map[x][y].isLooted()){
				GameScreen.addText("would you like to loot? y/n");
				yn=in.next();
				yn=yn.toLowerCase();
				if(yn.equals("y")||yn.equals("yes")){
					turnused=true;
					Gameplay.search(map, 10, x, y);
					map[x][y].setSearch(true);
					GameScreen.addText("Your cash is now " + Gameplay.cash);
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
		GameScreen.addText("thank you for playing!");
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
				GameScreen.addText("bad file name");
			}
		}while(!mapped);
		
		do{
			GameScreen.addText("number of players?(1-6): ");
			try{
			PCnum=in.nextInt();
			}catch(InputMismatchException e){
				in = new Scanner(System.in);
			}
		}while (PCnum<1||PCnum>6);
		
		PlayerCharacter playa;
		Players= new FlizbazArrayList<characters>(PCnum);
		for(int x2=0; x2<PCnum; x2++){
			GameScreen.addText("player "+(x2+1)+" name: ");
			name=in.next();
			GameScreen.addText("0:Fighter");
			GameScreen.addText("1:Thief");
			GameScreen.addText("2:Wizard");
			do{
				GameScreen.addText("class number): ");
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
					map[x][y].setFight(false);
					map[x][y].setLooted(true);
				}
			}
		}
		compass_setter(true);
		room_text();
	}
	
	public static void moveAsTurn(String direction){
		boolean moveable=false;
		//stops movement text when no movement is done
		if(direction.equals("north")&&map[x][y].isNorth()){
			moveable=true;
		} else if(direction.equals("south")&&map[x][y].isSouth()){
			moveable=true;
		} else if(direction.equals("east")&&map[x][y].isEast()){
			moveable=true;
		} else if(direction.equals("west")&&map[x][y].isWest()){
			moveable=true;
		}
		if(moveable){
			turnused=true;
			GameScreen.addText("You moved " +direction + ".");
			//Gameplay.move(map, 's', x, y);
			if(direction.equals("north")){
				x -= 1;
			} else if(direction.equals("south")){
				x += 1;
			} else if(direction.equals("east")){
				y += 1;
			} else if(direction.equals("west")){
				y -= 1;
			}
			room_text();
		}
		compass_setter(true);
		System.out.println("x: " + x + " y: " + y);
	}
	public static void room_text(){
		//I apologize
		if
		(	
			//hallway
			(
				//north south
				(map[x][y].isSouth()&&map[x][y].isNorth()) && !(map[x][y].isEast()||map[x][y].isWest())
			)||(
				//east west
				(map[x][y].isEast()&&map[x][y].isWest()) && !(map[x][y].isSouth()||map[x][y].isNorth())
			)
		)
		{
			GameScreen.addText("You are in a hallway");
		}else if
		(
			//death chamber
			!(map[x][y].isSouth()||map[x][y].isNorth()||map[x][y].isEast()||map[x][y].isWest())
		)
		{
			GameScreen.addText("You are ... in a small room with no exit");
		}else if
		(
			//all four exits
			map[x][y].isSouth()&&map[x][y].isNorth()&&map[x][y].isEast()&&map[x][y].isWest()
		)
		{
			GameScreen.addText("You are in a large room of which you cannot see the sides in the dark");
		}else if
		(
			//dead end
			(
				//north exit
				map[x][y].isNorth()&&!map[x][y].isSouth()&&!map[x][y].isEast()&&!map[x][y].isWest()
			)||(
				//south exit
				!map[x][y].isNorth()&&map[x][y].isSouth()&&!map[x][y].isEast()&&!map[x][y].isWest()
			)||(
				//east exit
				!map[x][y].isNorth()&&!map[x][y].isSouth()&&map[x][y].isEast()&&!map[x][y].isWest()
			)||(
				//west exit
				!map[x][y].isNorth()&&!map[x][y].isSouth()&&!map[x][y].isEast()&&map[x][y].isWest()
			)
		)
		{
			GameScreen.addText("You have hit a dead end, the only exit is from whence you came");
		}else{
			GameScreen.addText("You have a wall on one side, the others are shrouded in darkness");
		}
		if(map[x][y].isExit()){
			GameScreen.addText("You see a ladder hanging from the ceiling");
		}
	}
	public static void compass_setter(boolean on){
		if(on){
			screen.setNorth(map[x][y].isNorth());
			screen.setSouth(map[x][y].isSouth());
			screen.setEast(map[x][y].isEast());
			screen.setWest(map[x][y].isWest());
		}else{
			screen.setNorth(false);
			screen.setSouth(false);
			screen.setEast(false);
			screen.setWest(false);
		}
	}
	
}
