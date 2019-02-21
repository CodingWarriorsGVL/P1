package item;

public class Item {
	
	//Class Variables
	String name;
	int dropChance, sellValue, buyValue, equippedItemSlot;
	
	/*
	 * equippedItemSlot is the designated location in the equippedItems array that the item will be. Helmets will be [0], Armor [1], Leggings [2],
	Shield [3], Weapon [4], and any items not equipable and will remain in inventory will be [5] (This would be for potions etc..).
	This is to help ensure that a player doesn't equip 2 helmets or anything like that.
	*/
	
	public Item(String name, int dropChance, int sellValue, int buyValue, int equippedItemSlot){
		this.name = name;
		this. dropChance = dropChance;
		this.sellValue = sellValue;
		this.buyValue = buyValue;
		this.equippedItemSlot = equippedItemSlot;		
	}
		
	//Getters and Setters
	
	public int getEquippedItemSlot(){
		return equippedItemSlot;
	}
	
	public void setEquippedItemSlot(int equippedItemSlot){
		this.equippedItemSlot = equippedItemSlot;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDropChance() {
		return dropChance;
	}

	public void setDropChance(int dropChance) {
		this.dropChance = dropChance;
	}

	public int getSellValue() {
		return sellValue;
	}

	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
	}
	
}//End Class Item
