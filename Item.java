package group_project;

import java.util.Random;

public class Item {
	private String itemName;
	private boolean isArmor;
	private boolean isWeapon;
	private boolean isRanged; //If false it is melee
	private int durability;
	private int maxDurability;
	private int modifier;	//In the case of armor, increase armor. Weapon, increase damage
	
	public Item(String itemName, boolean isArmor, boolean isWeapon, boolean isRanged, int durability, int modifier){
		this.itemName = itemName;
		this.isArmor = isArmor;
		this.isWeapon = isWeapon;
		this.isRanged = isRanged;
		this.durability = durability;
		maxDurability = durability;
		this.modifier = modifier;
		
		Random rand = new Random();
		int chance = rand.nextInt(2);
		if(chance == 1)
		{
			this.isRanged = true;
		}
		
		if(isArmor == true && isWeapon == true){	//Can't be both, primarily for us to catch our own errors
			throw new IllegalArgumentException();
		}
	}
	
	public void used(){
		durability--;
		
		if(durability <= 0){
		//	destroyItem();								//Method will likely belong to player, because it contains item
		//	Game.printmessage([item] was destroyed!);	//Thinking damage algorithm will include a if(itemEquipped){ totalDamage += item.modifier; item.used() }
		}
	}
	
	public int getModifier(){
		return modifier;
	}

	public boolean getIsRanged() {
		return isRanged;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isArmor() {
		return isArmor;
	}

	public void setArmor(boolean isArmor) {
		this.isArmor = isArmor;
	}

	public boolean isWeapon() {
		return isWeapon;
	}

	public void setWeapon(boolean isWeapon) {
		this.isWeapon = isWeapon;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getMaxDurability() {
		return maxDurability;
	}

	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}

	public void setRanged(boolean isRanged) {
		this.isRanged = isRanged;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

}
