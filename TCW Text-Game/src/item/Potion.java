package item;
import java.util.Random;

public class Potion extends Item {
	
	//Class Objects
	Random rand = new Random();
	
	//Class Variables
	int quantity, healAmount;

	public Potion(String name, int dropChance, int sellValue, int buyValue, int quantity, int healAmount, int equippedItemSlot) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.quantity = quantity;
		this.healAmount = healAmount;
	}

	//Getters and Setters
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}
	
	//Other Methods
	public int heal(){
		return rand.nextInt(healAmount) + 1;
	}
	
	public String toString(){
		return name + ": " + quantity;
	}
	
}//End Class Potion
