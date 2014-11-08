package group_project;

public abstract class characters
{
	protected int strength;
	protected int intelligence;
	protected int dexterity;
	protected int currentHP;
	protected int maxHP;
	protected boolean hasWeapon;
	protected boolean hasArmor;
	
	public static FlizbazArrayList<PlayerCharacter> party = new FlizbazArrayList<PlayerCharacter>();
	public static FlizbazArrayList<NonPlayerCharacter> monsters1 = new FlizbazArrayList<NonPlayerCharacter>();
	public static FlizbazArrayList<NonPlayerCharacter> monsters2 = new FlizbazArrayList<NonPlayerCharacter>();
	public static FlizbazArrayList<NonPlayerCharacter> monsters3 = new FlizbazArrayList<NonPlayerCharacter>();
	
	// set default values for public Item(String itemName, boolean isArmor, 
	//	    boolean isWeapon, boolean isRanged, int durability, int modifier)
	protected Item weapon = new Item(null, false, true, false, 5, 0); 
	protected Item armor = new Item(null, true, false, false, 5, 0);
	
	protected Stack<Item> itemStack = new Stack<Item>();
	
	public int getStrength() 
	{
		return strength;
	}
	
	public void setStrength(int strength) 
	{
		this.strength = strength;
	}
	
	public int getIntelligence() 
	{
		return intelligence;
	}
	
	public void setIntelligence(int intelligence) 
	{
		this.intelligence = intelligence;
	}
	
	public int getDexterity() 
	{
		return dexterity;
	}
	
	public void setDexterity(int dexterity) 
	{
		this.dexterity = dexterity;
	}
	
	public int getCurrentHP() 
	{
		return currentHP;
	}
	
	public void setCurrentHP(int currentHP) 
	{
		this.currentHP = currentHP;
	}
	
	public int getMaxHP() 
	{
		return maxHP;
	}
	
	public void setMaxHP(int maxHP) 
	{
		this.maxHP = maxHP;
	}
	
	public boolean hasWeapon() 
	{
		return hasWeapon;
	}
	
	public void setHasWeapon(boolean hasWeapon) 
	{
		this.hasWeapon = hasWeapon;
	}
	
	public boolean hasArmor() 
	{
		return hasArmor;
	}
	
	public void setHasArmor(boolean hasArmor) 
	{
		this.hasArmor = hasArmor;
	}
	
	public Item getWeapon() 
	{
		return weapon;
	}
	
	public void setWeapon(Item weapon) 
	{
		this.weapon = weapon;
	}
	
	public Item getArmor() 
	{
		return armor;
	}
	
	public void setArmor(Item armor) 
	{
		this.armor = armor;
	}
	
	//-------------------------------------------------------------------
	// implement attack methods for both PCs and NPCs 
	// they return the amount of damage dealt
	// validate that the player is able to use them before calling
	//-------------------------------------------------------------------
	
	public abstract int meleeAttack(characters target, int targetID); // no restrictions, can use melee weapon or no weapon
	
	
	public abstract int rangeAttack(characters target, int targetID); // must have a range weapon to use


	public abstract int castSpell();	// must know a spell to use									
	
	public void changeHP(int changeInHP)
	{
		if (changeInHP > 0)
		{
			currentHP += changeInHP; // adds health from potion
		}
		else
		{
			currentHP -= changeInHP; // takes damage
		}
	}
	
	public abstract void destruct();
	
	//If currentHP < 0, remove from arraylist
	
	public void addItemToStack(Item item)
	{
		itemStack.push(item); // pushes dead player's item on stack
	}

}
