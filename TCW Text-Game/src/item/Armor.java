package item;

public class Armor extends Item {
	
	//Class Variables
	int defense;
	boolean isEquipped;
	
	Armor ruggedHelmet = new Armor("Rugged Helmet", 1, 1, 0, 1, true);
	Armor ruggedArmor = new Armor("Rugged Armor", 1, 1, 0, 3, true);
	Armor ruggedLeggings = new Armor("Rugged Leggings", 1, 1, 0, 2, true);
	Armor ruggedShield = new Armor("Rugged Shield", 1, 1, 0, 1, true);

	public Armor(String name, int dropChance, int sellValue, int buyValue, int defense, boolean isEquipped) {
		super(name, dropChance, sellValue, buyValue);
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

	
}//End Class Armor
