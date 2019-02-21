package item;

public class Armor extends Item {
	
	//Class Variables
	int defense;
	boolean isEquipped;


	public Armor(String name, int dropChance, int sellValue, int buyValue, int defense, boolean isEquipped, int equippedItemSlot) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.defense = defense;
		this.isEquipped = isEquipped;
	}
	
	//Getters and Setters

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
	public String toString(){
		return name + " Defense: " + defense;
	}

	
}//End Class Armor
