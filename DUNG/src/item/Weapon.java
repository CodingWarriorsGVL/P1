package item;

public class Weapon extends Item {
	
	//Class Variables
	int attack;
	boolean isEquipped;
	
	
	//Weapon Objects
	public static final Weapon ruggedSword = new Weapon("Rugged Sword", 1, 1, 0, 3, true, 4);
	
	public static final Weapon testSword = new Weapon("Test Sword", 1, 1, 0, 500, false, 4);

	public Weapon(String name, int dropChance, int sellValue, int buyValue, int attack, boolean isEquipped, int equippedItemSlot) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.attack = attack;
		this.isEquipped = isEquipped;
	}

	//Getters and Setters
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
	
	public String toString(){
		return name + " Attack: " + attack;
	}

}//End Class Weapon
