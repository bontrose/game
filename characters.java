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
//	public static FlizbazArrayList<NonPlayerCharacter> monsters1 = new FlizbazArrayList<NonPlayerCharacter>();
//	public static FlizbazArrayList<NonPlayerCharacter> monsters2 = new FlizbazArrayList<NonPlayerCharacter>();
//	public static FlizbazArrayList<NonPlayerCharacter> monsters3 = new FlizbazArrayList<NonPlayerCharacter>();
	
	// set default values for Item(String itemName, boolean isArmor, boolean isWeapon, int durability, int modifier)
	protected Item weapon = new Item(null, false, true, false, 5, 0); 
	protected Item armor = new Item(null, true, false, false, 5, 0);
	
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
	
	//--------------------------------------------------------------------------------
	// implement fight() in subclasses
	// for PC:  gives options(melee/range attack/spell) and calls a method accordingly
	// for NPC: automatically (maybe randomly) selects an attack (based on if they have a 
	// 		 	weapon) (random chance of knowing a spell)
	//--------------------------------------------------------------------------------
	
	public abstract void fight();
	
	//-------------------------------------------------------------------
	// implement attack methods for both PCs and NPCs 
	// they return the amount of damage dealt
	// validate that the player is able to use them before calling
	//-------------------------------------------------------------------
	
	public abstract int meleeAttack(characters target, int targetID); // no restrictions
	
//	Ideas for implementation:
//	{
//		// generate a randomNumber between 1 and 20
//		if (randomNumber < dexterity) // check for hit or miss
//		{
//			return 0;				  // player missed
//		}
//		int damage;
//		if(hasWeapon)
//		{
//			damage = (strength/3) + weapon.getModifier() - armor.getModifier();
//		}
//		else
//		{
//			damage = (strength/3);
//		}
//		return damage;
//	}
	
	public abstract int rangeAttack(characters target, int targetID); // must have weapon to use

//	Ideas for implementation:
//	{
//		
//		if(hasWeapon == false)
//		{
//			throw new IllegalStateException();
//		}
//	  	// generate a randomNumber between 1 and 15
//		if (randomNumber < dexterity) // check for hit or miss
//		{
//			return 0;				  // player missed
//		}
//		int damage = (strength/3) + weapon.getModifier() - armor.getModifier();
//		return damage;
//	}

	public abstract int castSpell();	// maybe public int castSpell(Spell spellName)?
										// must know a spell to use
	
//	Ideas for implementation:
// 	damage = spellName.getDamage(); 
// 	We should make a Spell class for the number of rounds to learn
// 	each spell, the amount a damage each spell does, the name, etc.
	
	public abstract void destruct();
	
	//If currentHP < 0, remove from arraylist

}
