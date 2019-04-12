package item;
import static characters.Player.*;

public class Weapon extends Item {
	
	//Class Variables
	int attack;
	
	
	
	//Weapon Objects
	public static final Weapon ruggedSword = new Weapon("Rugged Sword", 1, 1, 0, 3, WEAPON);
	public static final Weapon steelSword = new Weapon("Steel Sword", 3, 15, 30, 10, WEAPON);
	public static final Weapon axe = new Weapon("Axe", 3, 20, 35, 25, WEAPON);
	
	public static final Weapon testSword = new Weapon("Test Sword", 1, 1, 0, 500, WEAPON);

	public Weapon(String name, int dropChance, int sellValue, int buyValue, int attack, int equippedItemSlot) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.attack = attack;
		
	}

	//Getters and Setters
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public String toString(){
		return name + " Attack: " + attack;
	}

}//End Class Weapon
