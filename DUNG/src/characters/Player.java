package characters;

import java.util.*;

import item.Armor;
import item.Item;
import item.Weapon;

public class Player extends Entity {

	Scanner scan = new Scanner(System.in);

	// Class Variables
	int intellect, perception, experience, mana, level;

	int damage = attackRating;
	int blocking = defense;

	public ArrayList playerInventory = new ArrayList();
	public ArrayList playerSpells = new ArrayList();

	Item[] equippedItems = new Item[5];
	MagicSpell[] equippedSpells = new MagicSpell[1];
	
	static final int
		HELMET = 0,
		ARMOR = 1,
		LEGGINGS = 2,
		SHIELD = 3,
		WEAPON = 4;

	public Player(String name, int health, int mana, int attackRating, int defense, int intellect, int perception,
			int experience, int level) {
		super(name, health, attackRating, defense);
		this.intellect = intellect;
		this.perception = perception;
		this.experience = experience;
		this.mana = mana;
		this.level = level;
	}

	// Getters and Setters

	public MagicSpell[] getEquippedSpells() {
		return equippedSpells;
	}

	public void setEquippedSpells(int location, MagicSpell spell) {
		equippedSpells[location] = spell;
	}

	public Item[] getEquippedItems() {
		return equippedItems;
	}

	public void setEquippedItems(int location, Item item) {
		equippedItems[location] = item;
		if (item instanceof Armor) {
			((Armor) item).setEquipped(true);
		}

		else if (item instanceof Weapon) {
			((Weapon) item).setEquipped(true);
		}
	}

	public void setPlayerSpells(MagicSpell spell) {
		playerSpells.add(spell);
	}

	public ArrayList getPlayerSpells() {
		return playerSpells;
	}

	public void setPlayerInventory(Item item) {
		playerInventory.add(item);
	}

	public ArrayList getPlayerInventory() {
		return playerInventory;
	}

	public void removeEquippedItems(int location) {
		if (equippedItems[location] instanceof Armor) {
			((Armor) equippedItems[location]).setEquipped(false);
		}

		else if (equippedItems[location] instanceof Weapon) {
			((Weapon) equippedItems[location]).setEquipped(false);
		}
		equippedItems[location] = null;
	}

	public int getIntellect() {
		return intellect;
	}

	public void setIntellect(int intellect) {
		this.intellect = intellect;
	}

	public int getPerception() {
		return perception;
	}

	public void setPerception(int perception) {
		this.perception = perception;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getMana() {
		return mana;
	}

	public void setPlayerDamage(Item[] equippedItems) {
		damage = 0;
		damage = ((Weapon) equippedItems[4]).getAttack() + attackRating;
	}

	public int getPlayerDamage() {
		return damage;
	}

	public void setPlayerBlocking(Item[] equippedItems) {
		int equipmentDefense = 0;
		blocking = 0;
		for (int i = 0; i < 3; i++) {
			equipmentDefense += ((Armor) equippedItems[i]).getDefense();
		}
		blocking = equipmentDefense + defense;
	}

	public int getPlayerBlocking() {
		return blocking;
	}

	// Other Methods

	public void displayEquippedItems(Item[] equippedItems) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Equipped Items *");
		for (int i = 0; i < equippedItems.length; i++) {
			System.out.println(equippedItems[i]);
		}
		System.out.println("--------------------------------------------------------------------");
	}

	public void displayPlayerInventory(ArrayList playerInventory) {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Player Inventory *");

		for (int i = 0; i < playerInventory.size(); i++) {
			System.out.println(i + 1 + ". " + ((Item) playerInventory.get(i)));
		}

		System.out.println("--------------------------------------------------------------------");

	}// End displayPlayerInventory

	public void displayEquippedSpells(MagicSpell[] equippedSpells) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Equipped Spells *");
		for (int i = 0; i < equippedSpells.length; i++) {
			System.out.println(equippedSpells[i].getName());
		}
		System.out.println("--------------------------------------------------------------------");
	}

	public void displayPlayerSpells(ArrayList playerSpells) {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Spells that you know *");

		for (int i = 0; i < playerSpells.size(); i++) {
			System.out.println(i + 1 + ". " + ((MagicSpell) playerSpells.get(i)).getName());
		}
		System.out.println("--------------------------------------------------------------------");

	}// End displayEquippedSpells
	 
	public void changeEquippedItems(Player player1, Item[] equippedItems, ArrayList playerInventory) {

		boolean equippingItems = true;
		int input;

		Item[] currentlyEquipped = equippedItems;
		ArrayList currentPlayerInventory = playerInventory;
		
		while (equippingItems) {
			
			player1.displayPlayerInventory(currentPlayerInventory);
			player1.displayEquippedItems(equippedItems);
			
			System.out.println("* Which item would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			input = scan.nextInt();

			if (input > 0 && input <= playerInventory.size() && ((Item) playerInventory.get(input - 1)).getEquippedItemSlot() < 5) {
				playerInventory.add(currentlyEquipped[((Item) playerInventory.get(input - 1)).getEquippedItemSlot()]);
				player1.removeEquippedItems(((Item) playerInventory.get(input - 1)).getEquippedItemSlot());
				player1.setEquippedItems(((Item) playerInventory.get(input - 1)).getEquippedItemSlot(),
						((Item) playerInventory.get(input - 1)));
				playerInventory.remove(((Item) playerInventory.get(input - 1)));

				player1.setPlayerDamage(currentlyEquipped);
				player1.setPlayerBlocking(currentlyEquipped);

				System.out.println("* Your new Attack Rating is: " + player1.getPlayerDamage() + " *");
				System.out.println("* Your new Defense Rating is: " + player1.getPlayerBlocking() + " *");
			}

			else if (input > 0 && ((Item) playerInventory.get(input - 1)).getEquippedItemSlot() > 4) {
				System.out.println("* This item can not be equipped. *");
			}
			
			else if(input == 0) {
				equippingItems = false;
			}

			else
				System.out.println("* Invalid Selection! *");
		} // End while(equippingItems)

	}// End changeEquippedItems

	public void changeEquippedSpells(Player player1, MagicSpell[] equippedSpells, ArrayList playerSpells) {
		
		boolean equippingSpells = true;
		int input;

		MagicSpell[] currentlyEquippedSpells = equippedSpells;
		ArrayList currentlyKnownPlayerSpells = playerSpells;
		
		while (equippingSpells) {

			player1.displayPlayerSpells(currentlyKnownPlayerSpells);
			player1.displayEquippedSpells(currentlyEquippedSpells);

			System.out.println("* Which spell would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			input = scan.nextInt();

			if (input > 0 && input <= playerSpells.size()) {
				//player1.removeEquippedSpell(input - 1);
				player1.setEquippedSpells(input -1, ((MagicSpell)currentlyKnownPlayerSpells.get(input - 1)));
				


				//System.out.println("* Your new Attack Rating is: " + player1.getPlayerDamage() + " *");
				//System.out.println("* Your new Defense Rating is: " + player1.getPlayerBlocking() + " *");
			}

			else if (input == 0) {
				equippingSpells = false;
			}

			else
				System.out.println("* Invalid Selection! *");
		} // End while(equippingSpells)
		
	}//End changeEquippedSPells
	
	public void removeEquippedSpell(int location) {
		equippedSpells[location] = null;
	}
	
	public String toString() {
		return "\nName: " + name + "\nLevel: " + level + "\nExperience: " + experience + "\nHealth: " + health
				+ "\nMana: " + mana + "\nAttack Rating: " + attackRating + "\nDefense: " + defense + "\nIntellect: "
				+ intellect + "\nPerception: " + perception;
	}
}// End Class Player
