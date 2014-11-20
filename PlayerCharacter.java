package group_project;

import java.util.Random;

public class PlayerCharacter extends characters {
	
	Random dice = new Random(); 
	private boolean hasPotion;
	private boolean hasSlept;
	
	PlayerCharacter(int characterClass, String characterName){
		strength = statRolls();
		intelligence = statRolls();
		dexterity = statRolls();
		currentHP = 20;
		maxHP = currentHP;
		hasWeapon = false;
		hasArmor = false;
		hasPotion = false;
		hasSlept = false;
		
		if(characterClass == 0){//Fighter
			strength++;
			intelligence--;
			currentHP += strength/3;
		}
		else if(characterClass == 1){//Thief
			dexterity++;
			intelligence++;
			strength--;
		}
		else if(characterClass == 2){//Wizard
			intelligence++;
			intelligence++;
			strength--;
		}
		
		super.setName(characterName);
		
		party.add(this);
	}

	public int statRolls(){
	//	dice.ints(1, 6);
		int statTotal = 0;
		statTotal += dice.nextInt(6);
//		System.out.println("First roll: " + statTotal);
		statTotal += dice.nextInt(6);
//		System.out.println("Second roll: " + statTotal);
		statTotal += dice.nextInt(6);
//		System.out.println("Third roll: " + statTotal);
	
		return statTotal;
	}

	public void fight() {
		//TODO, Remove from here and NPC class
	}
	public int meleeAttack(characters target, int targetID) {
		int damage = (this.strength/3) + weapon.getModifier() - target.armor.getModifier();
		if(damage==0){
			damage=1;
		}
		target.currentHP -= damage;
		return damage;
	}
	public int rangeAttack(characters target, int targetID) {
		int damage = (this.dexterity/3) + weapon.getModifier() - target.armor.getModifier();
		if(damage==0){
			damage=1;
		}
		target.currentHP -= damage;
		return damage;
	}
	public int castSpell() {
		return 0;
	}
	
	public boolean getHasPotion(){
		return hasPotion;
	}
	
	public void setHasPotion(boolean potion){
		this.hasPotion = potion;
	}

	public void destruct() {
		
		System.out.println("Oh no, " + super.getName() + " has died!");
		party.remove(this);
	}

	public void setHasSlept(boolean slept) {
		hasSlept = slept;
	}

	public boolean isPlayer(){
		return true;
	}
	
	public boolean isMonster(){
		return false;
	}
}
