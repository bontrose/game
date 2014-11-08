package group_project;

import java.io.*;
import java.util.*;

public class NonPlayerCharacter extends characters
{	
	Random random = new Random();
	private int numOfNPCs;
	private String monsterName;
	private String attackType;
	private String monsterNames[][][];
	Random dice = new Random();
	
	NonPlayerCharacter(FlizbazArrayList<NonPlayerCharacter> monsterGroup){
			
		File file = new File("npcArrayInput.txt");
		
		try 
		{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" ");
            while (scanner.hasNextLine()) 
            {
        		String index1 = null;
        		String index2 = null;
        		String index3 = null;
            	String input = scanner.next();
                StringTokenizer tokens = new StringTokenizer(input);
                index1 = tokens.nextToken();
                index2 = tokens.nextToken();
                index3 = tokens.nextToken();
                monsterNames[Integer.parseInt(index1)][Integer.parseInt(index2)][Integer.parseInt(index3)] = tokens.nextToken();
            }
            scanner.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("The file was not found.");
		}
		
		strength = statRolls();
		intelligence = statRolls();
		dexterity = statRolls();
		maxHP = statRolls();
		currentHP = maxHP;
		
		int in, dex, str;
		
		if(intelligence<8)
		{
			in = 0;
		}
		else if(intelligence>12)
		{
			in = 2;
		}
		else
		{
			in = 1;
		}
		
		if(dexterity<8)
		{
			dex = 0;
		}
		else if(dexterity>12)
		{
			dex = 2;
		}
		else
		{
			dex = 1;
		}
		
		if(strength<8)
		{
			str = 0;
		}
		else if(strength>12)
		{
			str = 2;
		}
		else
		{
			str = 1;
		}
		
		monsterName = monsterNames[in][dex][str];
		
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
	
	public int statRolls()
	{
		int numOfRolls = 0;
		int statTotal = 0;
		
		numOfRolls = dice.nextInt(6);
		
		for(int i = 1; i <= numOfRolls; i++)
		{
			statTotal += dice.nextInt(3);
			System.out.println("Roll " + i + " total: " + statTotal);
		}
	
		return statTotal;
	}
	
	public void setNumOfNPCs()
	{
		numOfNPCs = random.nextInt(7);
	}

	public int getNumOfNPCs()
	{	
		return numOfNPCs;
	}
	public String getAttackType() {
		return attackType;
	}

	public void setAttackType(String attackType) {
		this.attackType = attackType;
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
