package item;

public class Weapon extends Item {
	
	//Class Variables
	int attack;
	boolean isEquipped;

	public Weapon(String name, int dropChance, int sellValue, int buyValue, int attack, boolean isEquipped) {
		super(name, dropChance, sellValue, buyValue);
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
	

}//End Class Weapon
