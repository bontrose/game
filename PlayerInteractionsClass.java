package group_project;

//This is chris's version of the class that implements all of the gameplay methods.

public class PlayerInteractionsClass {
	PlayerInteractionsClass(){
		boolean exists = true; //I dunno man
	}
	
	public static void main(String[] args){
		characters.party.add(new PlayerCharacter(0, "Fighter_1"));
		characters.party.add(new PlayerCharacter(0, "Fighter_2"));
		characters.party.add(new PlayerCharacter(1, "Thief_1"));
		characters.party.add(new PlayerCharacter(1, "Thief_1"));
		characters.party.add(new PlayerCharacter(2, "Wizard_1"));
		characters.party.add(new PlayerCharacter(2, "Wizard_1"));
		
		new NonPlayerCharacter(characters.monsters1);
		new NonPlayerCharacter(characters.monsters1);
		new NonPlayerCharacter(characters.monsters1);
		PlayerInteractionsClass duh = new PlayerInteractionsClass();
		duh.fight(characters.party, characters.monsters1);
	}
	/*
	 * There is an unused variable in melee and ranged attacks called ID that is an int. It is a pseudo implementation that assumes
	 * the player will be selecting their targets and the monsters will also be selecting their targets (at random).
	 */
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
	}//end fight
}
