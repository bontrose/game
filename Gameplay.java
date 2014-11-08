package group_project;

import java.io.IOException;
import java.util.Random;

public class Gameplay {

	public static int x;
	public static int y;
	public static char d;
	public static room[][] Map= new room[0][0];
	
	public void fight(FlizbazArrayList<PlayerCharacter> players, FlizbazArrayList<NonPlayerCharacter> npcs){
		//Initiative
		Stack<characters> initiative = new Stack<characters>();
		for(int highestDex = 0; highestDex < 20; highestDex++){
			for(int i = 0; i < players.size(); i++){
				if(players.get(i).dexterity == highestDex){
					initiative.push(players.get(i));
				}
			}	
			for(int i = 0; i < npcs.size(); i++){
				if(npcs.get(i).dexterity == highestDex){
					initiative.push(npcs.get(i));
				}
			}
		}//end initiative	
	
		//Until we can actually get the GUI up we can't test target selections feasibly
		//Nor is ti worth implementing pausing between turns
		int turns = 0;
		while(turns < players.size() + npcs.size() && !(npcs.isEmpty()) && !(players.isEmpty())){
			if(initiative.peek().hasWeapon()){
				if(initiative.peek().weapon.getIsRanged() == false){
					initiative.pop().meleeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
					System.out.println(npcs.get(0).currentHP);
				}
				else{
					initiative.pop().rangeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
					System.out.println(npcs.get(0).currentHP);
				}
				System.out.println(npcs.get(0).currentHP);
			}
			else{
				initiative.pop().meleeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
				System.out.println(npcs.get(0).currentHP);
			}
			
			if(npcs.get(0).currentHP < 0)
			{
				System.out.println("Hooray NPC " + npcs.get(0).getName() + " died!");
				npcs.remove(0);
			}
			turns++;
		}
		System.out.println("Fight is done");
	}//End Fight
	
	public void runFight(FlizbazArrayList<NonPlayerCharacter> npcs){
		
	}
	

	
	public static room run(){
		//call the hit, capeesh?
		
		//This is temporary\\
		//This need to reference the actual map when we start rendering it\\
		Map[x][y].setmonsters(characters.monsters1);
		return move(d);
	}//End Run
	
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
	}//End Move
	
	public void sleep()
	{
		for(int i = 0; i < characters.party.size(); i++){
			characters.party.get(i).setHasSlept(true);
		}
		Random random = new Random();
		String fileName = null;
		FlizbazArrayList<NonPlayerCharacter> sleepMonsterEncounter = null;
		
		int chanceOfMonstersAppearing = random.nextInt(6);
		
		if(chanceOfMonstersAppearing == 5)
		{
			int whichMonsterGroup = random.nextInt(3);
			switch(whichMonsterGroup)
			{
			case 0 : sleepMonsterEncounter = characters.monsters1;
				fight(characters.party, sleepMonsterEncounter);
				break;
			case 1 : sleepMonsterEncounter = characters.monsters2;
				fight(characters.party, sleepMonsterEncounter);
				break;
			case 2 : sleepMonsterEncounter = characters.monsters3;
				fight(characters.party, sleepMonsterEncounter);
				break;
			}
		}
		else
		{
			for(int i = 0; i < characters.party.size(); i++){
				characters.party.get(i).currentHP += 1;
			}
		}
	}//End Sleep
	
	public static int search(int wisdom){
		Random Die = new Random();
		int cash=Die.nextInt(20)+1;
		Map[x][y].setLooted(true);
		if (wisdom<cash){
			cash=0;
		}
		return cash;
	}//End Search
}
