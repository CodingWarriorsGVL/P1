package item;

public class Armor extends Item {
	
	//Class Variables
	int defense;
	boolean isEquipped;
	
	//Armor Objects
	public static final Armor ruggedHelmet = new Armor("Rugged Helmet", 1, 1, 0, 1, true, 0);
	public static final Armor ruggedArmor = new Armor("Rugged Armor", 1, 1, 0, 3, true, 1);
	public static final Armor ruggedLeggings = new Armor("Rugged Leggings", 1, 1, 0, 2, true, 2);
	public static final Armor ruggedShield = new Armor("Rugged Shield", 1, 1, 0, 1, true, 3);
		
		//These are for testing purposes
	public static final Armor testHelmet = new Armor("Test Helmet", 1, 1, 1, 500, false, 0);
	public static final Armor testArmor = new Armor("Test Armor", 1, 1, 0, 500, false, 1);
	public static final Armor testLeggings = new Armor("Test Leggings", 1, 1, 0, 500, false, 2);
	public static final Armor testShield = new Armor("Test Shield", 1, 1, 0, 500, false, 3);

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
