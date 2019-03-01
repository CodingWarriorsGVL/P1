/*
 * **************************************************************************************************************
 * Currency.java | Author: Shane Mccarty | Date: 2019.02.25 | Rev: 2019.02.25
 * This file is a class of the item package for the "Dungeons of UNG" text-based game.
 * **************************************************************************************************************
 */

package item;

public class Currency extends Item{
	
	//Class Variables
	int quantity;
	
	//Class Objects
	public static final Currency goldCoin = new Currency("Gold Coin", 3, 0, 0, 6, 0);

	public Currency(String name, int dropChance, int sellValue, int buyValue, int equippedItemSlot, int quantity) {
		super(name, dropChance, sellValue, buyValue, equippedItemSlot);
		this.quantity = quantity;
	}
	
	//Getters and Setters

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//Other Methods
	
	public String toString() {
		return name + "(s): " + quantity;
	}
	
	

}//End Class Currency
