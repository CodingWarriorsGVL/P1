package item;
import static characters.Player.*;


public class Armor extends Item {
	
	//Class Variables
	int defense;
	
	
	//Armor Objects
	public static final Armor ruggedHelmet = new Armor("Rugged Helmet", 1, 1, 0, 1, HELMET);
	public static final Armor ruggedArmor = new Armor("Rugged Armor", 1, 1, 0, 3, ARMOR);
	public static final Armor ruggedLeggings = new Armor("Rugged Leggings", 1, 1, 0, 2, LEGGINGS);
	public static final Armor ruggedShield = new Armor("Rugged Shield", 1, 1, 0, 1, SHIELD);
	
	public static final Armor plateHelmet = new Armor("Plate Helmet", 5, 30, 55, 7, HELMET);
	public static final Armor plateArmor = new Armor("Plate Armor", 5, 85, 110, 9, ARMOR);
	public static final Armor plateLeggings = new Armor("Plate Leggings", 5, 50, 75, 8, LEGGINGS);
	public static final Armor plateShield = new Armor("Plate Shield", 5, 60, 80, 10, SHIELD);
		
		//These are for testing purposes
	public static final Armor testHelmet = new Armor("Test Helmet", 1, 1, 1, 500, 0);
	public static final Armor testArmor = new Armor("Test Armor", 1, 1, 0, 500, 1);
	public static final Armor testLeggings = new Armor("Test Leggings", 1, 1, 0, 500, 2);
	public static final Armor testShield = new Armor("Test Shield", 1, 1, 0, 500, 3);

	public Armor(String name, int dropChance, int sellValue, int buyValue, int defense, int equippedItemSlot) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.defense = defense;
	}
	
	//Getters and Setters

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public String toString(){
		return name + " Defense: " + defense;
	}

	
}//End Class Armor
