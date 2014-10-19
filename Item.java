package group_project;

public class Item {
	private String itemName;
	private boolean isArmor;
	private boolean isWeapon;
	private int durability;
	private int maxDurability;
	private int modifier;	//In the case of armor, increase armor. Weapon, increase damage
	
	public Item(String itemName, boolean isArmor, boolean isWeapon, int durability, int modifier){
		this.itemName = itemName;
		this.isArmor = isArmor;
		this.isWeapon = isWeapon;
		this.durability = durability;
		maxDurability = durability;
		this.modifier = modifier;
		
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
}
