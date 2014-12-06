package group_project;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Gameplay {

	public static String d;
	public static int cash=0;
	public static FlizbazArrayList<Item> items = new FlizbazArrayList<Item>();
	
	public static void fight(FlizbazArrayList<PlayerCharacter> players, FlizbazArrayList<NonPlayerCharacter> npcs){
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
		Scanner scan = new Scanner(System.in);
		
		String str = "";
		StringBuilder strBd = new StringBuilder(str);
		for(int i = 0; i < npcs.size(); i++){
			strBd.append(npcs.get(i).getName() + " "); 
		}
		
		GameScreen.addText("Monsters: " + strBd + "\n\nTime to fight! Press enter!");
		//Until we can actually get the GUI up we can't test target selections feasibly
		//Nor is it worth implementing pausing between turns
		int turns = 0;
		while(!(npcs.isEmpty()) && !(players.isEmpty())){
			
			scan.nextLine();
			//Monster turn
			if(initiative.peek().isMonster()){
				boolean monsterTurn = true;
				//Monster died, can't remove from stack, bandaid
				if(!npcs.contains((NonPlayerCharacter) (initiative.peek()))){
					monsterTurn = false;
					break;
				}
				
				while(monsterTurn){
					Random dice = new Random();
					int target = dice.nextInt(players.size());
					//Attack type
					if(initiative.peek().intelligence > 8){
						int spellLevel = dice.nextInt(2);
						GameScreen.addText(initiative.peek().getName() + " casts magic at " + players.get(target).getName());
						initiative.pop().castSpell(players.get(target), players.indexOf(players.get(target)), spellLevel);
					}
					else if(initiative.peek().weapon.getIsRanged() == false){
						GameScreen.addText(initiative.peek().getName() + " attacks " + players.get(target).getName());
						initiative.pop().meleeAttack(players.get(target), players.indexOf(players.get(target)));
					}
					else{
						GameScreen.addText(initiative.peek().getName() + " shoots at " + players.get(target).getName());
						initiative.pop().rangeAttack(players.get(target), players.indexOf(players.get(target)));
					}
					
					if(players.get(target).getCurrentHP() > 0){
						GameScreen.addText(players.get(target).getName() + " is at " + players.get(target).currentHP + "HP");
					}
					else{
						players.get(target).destruct();
					}
					monsterTurn = false;
				}
			}
			//Player turn
			else if(initiative.peek().isPlayer()){
				boolean playerTurn = true;
				while(playerTurn){
					//Character died, can't remove from stack, bandaid
					if(!players.contains((PlayerCharacter) (initiative.peek()))){
						playerTurn = false;
						break;
					}
					GameScreen.addText("Run away? y/n");
					if(scan.next().toLowerCase().equals("y")){
						run(players, npcs);
						break;
					}
					
					boolean playerCastingSpell = false;
					int spellLevel = 0;
					if(((PlayerCharacter) initiative.peek()).getCharacterClass() == 2){
						GameScreen.addText("What level spell 1 2 or 3?");
						spellLevel = scan.nextInt();
						playerCastingSpell = true;
					}
					GameScreen.addText("Type in the name of the monster you want to attack");
					String targetString = scan.next();
					int i;
					for(i = 0; i < npcs.size(); i++){
						if(npcs.get(i).getName().equals(targetString)){
							//Attack type
							if(playerCastingSpell){
								initiative.pop().castSpell(npcs.get(i), npcs.indexOf(npcs.get(i)), spellLevel);
							}
							else if(initiative.peek().hasWeapon()){
								if(initiative.peek().weapon.getIsRanged() == false){
									initiative.pop().meleeAttack(npcs.get(i), npcs.indexOf(npcs.get(i)));
								}
								else{
									initiative.pop().rangeAttack(npcs.get(i), npcs.indexOf(npcs.get(i)));
								}
							}
							else{
								if(npcs.get(i).getName() != npcs.get(0).getName())
								{
									GameScreen.addText("Cannot attack " + npcs.get(i).getName() + " during a melee attack. " +
														"Attacking nearest enemy instead: " + npcs.get(0).getName());
								}								
								initiative.pop().meleeAttack(npcs.get(0), npcs.indexOf(npcs.get(0)));
								if(npcs.get(0).getCurrentHP() > 0)
								{
									GameScreen.addText(npcs.get(0).getName() + " is at " + npcs.get(0).currentHP + "HP");
								}
								else
								{
									items = npcs.get(0).destruct(npcs);
									pickUpItems(initiative, items.size());
								}
								playerTurn = false;
							}
							//Current enemy HP or dead 
							if(playerTurn == true)
							{
								if(npcs.get(i).getCurrentHP() > 0){
									GameScreen.addText(npcs.get(i).getName() + " is at " + npcs.get(i).currentHP + "HP");
								}
								else{
									items = npcs.get(i).destruct(npcs);
									pickUpItems(initiative, items.size());
								}
							}
							playerTurn = false;
							break;
						}
						//Input error
					}
				}
	
			}
			
			//turns++;
			try{
			GameScreen.addText("\n" + initiative.peek().getName() + " is up next!");
			}catch(IndexOutOfBoundsException e){
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
				}
				strBd = new StringBuilder(str);
				for(int i = 0; i < npcs.size(); i++){
					strBd.append(npcs.get(i).getName() + " "); 
				}
				GameScreen.addText("Monsters: " + strBd);
			}
		}
		npcs.clear();
		GameScreen.addText("Fight is done");
	}//End Fight
	
	public static void pickUpItems(Stack<characters> initiative, int size)
	{
		if(size >= 1)
		{
			if(items.get(0).isWeapon())
			{
				if(initiative.peek().isPlayer())
				{
					initiative.peek().weapon = items.get(0);
					GameScreen.addText(initiative.peek().getName() + " picked up a weapon!");
				}
			}
			else if(items.get(0).isArmor())
			{
				if(initiative.peek().isPlayer())
				{
					initiative.peek().armor = items.get(0);
					GameScreen.addText(initiative.peek().getName() + " picked up some armor!");
				}
			}
		}
		if(size == 2)
		{
			if(initiative.peek().isPlayer())
			{
				initiative.peek().armor = items.get(1);
				GameScreen.addText(initiative.peek().getName() + " picked up some armor!");
			}
		}
	}
	
	public static void run(FlizbazArrayList<PlayerCharacter> players, FlizbazArrayList<NonPlayerCharacter> npcs){
		for(int i = 0; i < npcs.size(); i++){
			Random rnd = new Random();
			int target = rnd.nextInt(players.size());
			if(npcs.get(i).intelligence > 8){
				int spellLevel = rnd.nextInt(2);
				GameScreen.addText(npcs.get(i) + " casts magic at " + players.get(target).getName());
				npcs.get(i).castSpell(players.get(target), players.indexOf(players.get(target)), spellLevel);
			}
			else if(npcs.get(i).weapon.getIsRanged() == false){
				GameScreen.addText(npcs.get(i).getName() + " attacks " + players.get(target).getName());
				npcs.get(i).meleeAttack(players.get(target), players.indexOf(players.get(target)));
			}
			else{
				GameScreen.addText(npcs.get(i).getName() + " shoots at " + players.get(target).getName());
				npcs.get(i).rangeAttack(players.get(target), players.indexOf(players.get(target)));
			}
			
			if(players.get(target).getCurrentHP() > 0){
				GameScreen.addText(players.get(target).getName() + " is at " + players.get(target).currentHP + "HP");
			}
			else{
				players.get(target).destruct();
			}
		}
				
		game.map[game.x][game.y].setFight(false);
		game.moveAsTurn(d);
	}//End Run
	
	public static void move(room[][] theMap, char d2, int x, int y){
		d2=Character.toUpperCase(d2);

		if(d2=='N'){
			x-=1;
			d="S";
		} else if(d2=='S'){
			x+=1;
			d="N";
		} else if(d2=='E'){
			y+=1;
			d="W";
		} else if(d2=='W'){
			y-=1;
			d="E";
		}
	}//End Move
	
	public static void sleep()
	{
		for(int i = 0; i < characters.party.size(); i++){
			characters.party.get(i).setHasSlept(true);
		}
		Random random = new Random();
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
	
	public static void search(room theMap[][], int wisdom, int x, int y){
		Random Die = new Random();
		int findLoot=Die.nextInt(20)+1;
		int cashAmount=Die.nextInt(20)+1;
		int findPotion=Die.nextInt(5)+1;
		int counter = 0; 
		int characterIndex = 0; // player that gets potion
		theMap[x][y].setLooted(true);
		if (wisdom > findLoot)
		{
			cash += cashAmount;
		}
		if(findPotion == 5)
		{	 
			do 
			{ 
				characterIndex = Die.nextInt(characters.party.size()); 
				counter++;
			} 
			while(characters.party.get(characterIndex).getHasPotion() == true || counter < 100);
			characters.party.get(characterIndex).setHasPotion(true);
			GameScreen.addText(characters.party.get(characterIndex).getName() + " picked up a potion!");
		}
	}//End Search
	
	public static int hide(int characterIndex, int numOfPlayers)
	{
		int newTarget = 0;
		int counter = 0;
		Random rand = new Random();		
		do
		{
			newTarget = rand.nextInt(numOfPlayers);
			counter++;
		}
		while(newTarget == characterIndex || counter < 100);
		
		return newTarget; //index of player that will be targeted
	}//End Hide
}
