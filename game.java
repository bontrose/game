package group_project;

import java.io.*;
import java.util.*;


public class game {
	public static room[][] map;
	public static FlizbazArrayList<PlayerCharacter> Players;
	public static FlizbazArrayList<NonPlayerCharacter> Monsters;
	
	
	public static void main(String[] args) {
		init();

	}
	public static void init(){
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
		//PlayerCharacter player
		for(int x=0; x<PCnum; x++){
			System.out.print("player "+(x+1)+" name: ");
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
			//Players.add(element)
		}
		in.close();
	}
	
	int x = 0;
	int y = 0; // position
	
	public void sleep()
	{
		PlayerCharacter pc = new PlayerCharacter(0, null);
		map[x][y].sleep();
		Random random = new Random();
		FlizbazArrayList<NonPlayerCharacter> monsterGroup = null;
		
		int chanceOfMonstersGroup = random.nextInt(3);
		int chanceOfMonstersAppearing = random.nextInt(6);
		
		switch(chanceOfMonstersGroup)
		{
		case 0 : monsterGroup = characters.monsters1;
			break;
		case 1 : monsterGroup = characters.monsters2;
			break;
		case 2 : monsterGroup = characters.monsters3;
			break;
		}
		
		if(chanceOfMonstersAppearing == 5)
		{
			NonPlayerCharacter npc = new NonPlayerCharacter(monsterGroup);
		}
		else
		{
			pc.currentHP += 1;
		}
	}

}
