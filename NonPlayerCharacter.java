package group_project;

import java.io.*;
import java.util.*;

public class NonPlayerCharacter extends characters
{	
	Random random = new Random();
	private int numOfNPCs;
	private String monsterName;
	private String attackType;
	private String nameModifier;
	private String monsterNames[][][] = new String[3][3][3];
	Random dice = new Random();
	
	NonPlayerCharacter(FlizbazArrayList<NonPlayerCharacter> monsterGroup)
	{
		File file = new File("npcArrayInput.txt");
		
		try 
		{
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("\t");
            while (scanner.hasNextLine()) 
            {
        		String index1 = null;
        		String index2 = null;
        		String index3 = null;
            	String input = scanner.nextLine();
                StringTokenizer tokens = new StringTokenizer(input);
                index1 = tokens.nextToken();
                index2 = tokens.nextToken();
                index3 = tokens.nextToken();
                monsterName = tokens.nextToken();
                monsterNames[Integer.parseInt(index1)][Integer.parseInt(index2)][Integer.parseInt(index3)] = monsterName;
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
		
		if(maxHP < 5)
		{
			nameModifier = "Wounded-";
		}
		else if(maxHP > 12)
		{
			nameModifier = "Strong-";
		}
		else
		{
			nameModifier = "";
		}
		
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
		
		monsterName = nameModifier + monsterNames[in][dex][str];
		super.setName(monsterName);
		
		int chanceOfHavingWeapon = random.nextInt(3);
		if(chanceOfHavingWeapon == 2){
			hasWeapon = true;
		}
		else{
			hasWeapon = false;
		}
		
		int chanceOfHavingArmor = random.nextInt(3);
		if(chanceOfHavingArmor == 2){
			hasArmor = true;
		}
		else{
			hasArmor = false;
		}
		
		addMonsterToGroup(monsterGroup);
//		System.out.println("A" + super.getName() + " has appeared.");
		
	}
	
	
	public void addMonsterToGroup(FlizbazArrayList<NonPlayerCharacter> monsterGroup)
	{
		monsterGroup.add(this);
	}
	
	public int statRolls()
	{
		int numOfRolls = 0;
		int statTotal = 0;
		
		numOfRolls = dice.nextInt(6) + 1;
		
		for(int i = 1; i <= numOfRolls; i++){
			statTotal += dice.nextInt(3) + 1;
	//		System.out.println("Roll " + i + " total: " + statTotal);
		}
	
		return statTotal;
	}
	
	public void setNumOfNPCs(){
		numOfNPCs = random.nextInt(6) + 1;
	}

	public int getNumOfNPCs(){	
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
		int damage = 0;
		int miss = random.nextInt(20);
		if(miss < target.dexterity){
			damage = 0;
		}
		else{
			damage = (this.dexterity/3) + weapon.getModifier() - target.armor.getModifier();
		}
		target.currentHP -= damage;
		return damage;
	}
	public int castSpell(characters target, int targetID, int spellLevel) {
		int damage = (this.intelligence/3) * spellLevel;
		if(damage==0){
			damage=1;
		}
		target.currentHP -= damage;
		return damage;
	}
	
	public String getName(){
		return monsterName;
	}
	public FlizbazArrayList<Item> destruct(FlizbazArrayList<NonPlayerCharacter> theMonstersArray) {		
		GameScreen.addText(super.getName() + " has been defeated!");
		FlizbazArrayList<Item> items = new FlizbazArrayList<Item>();
		if(this.hasWeapon)
		{			
			items.add(weapon);
		}
		if(this.hasArmor)
		{		
			items.add(armor);
		}
		theMonstersArray.remove(this);
		return items;
	}
	public boolean isPlayer(){
		return false;
	}
	
	public boolean isMonster(){
		return true;
	}
}
