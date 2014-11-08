package group_project;

import java.io.IOException;
import java.util.Random;

public class NonPlayerCharacter extends characters
{

	Random random = new Random(); 
	private int numOfNPCs;
	private String monsterName;
	private String attackType;
	
	NonPlayerCharacter(FlizbazArrayList<NonPlayerCharacter> monsterGroup)
	{
		//set name and stats from file using String tokenizer
		
				int chanceOfHavingWeapon = random.nextInt(3);
				if(chanceOfHavingWeapon == 2)
				{
					hasWeapon = true;
				}
				else
				{
					hasWeapon = false;
				}
				
				int chanceOfHavingArmor = random.nextInt(3);
				if(chanceOfHavingArmor == 2)
				{
					hasArmor = true;
				}
				else
				{
					hasArmor = false;
				}
				
				monsterGroup.add(this);
	}
	
	NonPlayerCharacter(String filename, FlizbazArrayList<NonPlayerCharacter> monsterGroup){
		
		try 
		{
			String monsterStats = FileRead.open(filename);
		} 
		catch (IOException e) 
		{
			System.out.println("There was a problem reading from " + filename + ".");
		}
		
		//set name and stats from file using String tokenizer
		
		int chanceOfHavingWeapon = random.nextInt(3);
		if(chanceOfHavingWeapon == 2)
		{
			hasWeapon = true;
		}
		else
		{
			hasWeapon = false;
		}
		
		int chanceOfHavingArmor = random.nextInt(3);
		if(chanceOfHavingArmor == 2)
		{
			hasArmor = true;
		}
		else
		{
			hasArmor = false;
		}
		
		monsterGroup.add(this);
	}
	
	public void setNumOfNPCs()
	{
		numOfNPCs = random.nextInt(7);
	}

	public int getNumOfNPCs()
	{	
		return numOfNPCs;
	}
	public int meleeAttack(characters target, int targetID) {
		int damage = (this.strength/3) + weapon.getModifier() - target.armor.getModifier();
		target.currentHP -= damage;
		return damage;
	}
	public int rangeAttack(characters target, int targetID) {
		int damage = (this.dexterity/3) + weapon.getModifier() - target.armor.getModifier();
		target.currentHP -= damage;
		return damage;
	}
	public int castSpell() {
		return 0;
	}
	
	public String getName(){
		return monsterName;
	}

	public void destruct() 
	{		
		System.out.println(monsterName + " has been defeated!");
		
		if(monsters1.size() > 0)
		{
			monsters1.remove(this);
		}
		else if(monsters2.size() > 0)
		{
			monsters2.remove(this);
		}
		else if(monsters3.size() > 0)
		{
			monsters3.remove(this);
		}
	}


}
