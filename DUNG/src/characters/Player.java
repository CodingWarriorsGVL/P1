package characters;

import java.util.*;

import item.Armor;
import item.Item;
import item.Weapon;
import static item.Armor.*;
import static characters.HealingSpell.*;
import static characters.AttackSpell.*;
import static item.Weapon.*;
import static item.Potion.*;

public class Player extends Entity {

	Scanner scan = new Scanner(System.in);

	// Class Variables
	int intellect, perception, experience, mana, level;

	int meleeDamage = melee;
	int blocking = defense;



	public ArrayList inventory = new ArrayList();
	public ArrayList spells = new ArrayList();


	Item[] equippedItems = new Item[5];

	MagicSpell[] equippedSpells;

	static final int
	HELMET = 0,
	ARMOR = 1,
	LEGGINGS = 2,
	SHIELD = 3,
	WEAPON = 4;


	public Player(String name, int health, int mana, int melee, int defense, int intellect, int perception,int experience, int level) {
		super(name, health, melee, defense);
		this.intellect = intellect;
		this.perception = perception;
		this.experience = experience;
		this.mana = mana;
		this.level = level;
		equippedSpells = new MagicSpell[level+1];
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

	public void setSpells(MagicSpell spell) {
		spells.add(spell);
	}

	public ArrayList getSpells() {
		return spells;
	}

	public void setInventory(Item item) {
		inventory.add(item);
	}

	public ArrayList getInventory() {
		return inventory;
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

	public void setMeleeDamage(Item[] equippedItems) {
		meleeDamage = 0;
		meleeDamage = ((Weapon) equippedItems[WEAPON]).getAttack() + melee;
	}

	public int getMeleeDamage() {
		return meleeDamage;
	}

	public void setBlocking(Item[] equippedItems) {
		int equipmentDefense = 0;
		blocking = 0;
		for (int i = 0; i < 3; i++) {
			equipmentDefense += ((Armor) equippedItems[i]).getDefense();
		}
		blocking = equipmentDefense + defense;
	}

	public int getBlocking() {
		return blocking;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	// Other Methods

	public void displayEquippedItems() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Equipped Items *");
		for (int i = 0; i < equippedItems.length; i++) {
			System.out.println(equippedItems[i]);
		}
		System.out.println("--------------------------------------------------------------------");
	}

	public void displayInventory() {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Inventory *");

		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(i + 1 + ". " + ((Item) inventory.get(i)));
		}

		System.out.println("--------------------------------------------------------------------");

	}// End displayInventory

	public void displayEquippedSpells() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Equipped Spells *");
		for (int i = 0; i < equippedSpells.length; i++) {
			if (equippedSpells[i] != null) {
				System.out.println((i+1) + ". " + equippedSpells[i].getName());
			}
		}
		System.out.println("--------------------------------------------------------------------");
	}

	public void displaySpells() {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Spell  Book *");

		for (int i = 0; i < spells.size(); i++) {
			System.out.println(i + 1 + ". " + ((MagicSpell) spells.get(i)).getName());
		}
		System.out.println("--------------------------------------------------------------------");

	}// End displayEquippedSpells


	public void changeEquippedItems() {

		boolean equippingItems = true;
		int input;

		while (equippingItems) {

			this.displayInventory();
			this.displayEquippedItems();

			System.out.println("* Which item would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			input = scan.nextInt();

			if (input > 0 && input <= inventory.size() && ((Item) inventory.get(input - 1)).getEquippedItemSlot() < 5) {
				inventory.add(equippedItems[((Item) inventory.get(input - 1)).getEquippedItemSlot()]);
				this.removeEquippedItems(((Item) inventory.get(input - 1)).getEquippedItemSlot());
				this.setEquippedItems(((Item) inventory.get(input - 1)).getEquippedItemSlot(),
						((Item) inventory.get(input - 1)));
				inventory.remove(((Item) inventory.get(input - 1)));

				this.setMeleeDamage(equippedItems);
				this.setBlocking(equippedItems);

				System.out.println("* Your new Attack Rating is: " + this.getMeleeDamage() + " *");
				System.out.println("* Your new Defense Rating is: " + this.getBlocking() + " *");
			}

			else if (input > 0 && ((Item) inventory.get(input - 1)).getEquippedItemSlot() > 4) {
				System.out.println("* This item can not be equipped. *");
			}

			else if(input == 0) {
				equippingItems = false;
			}

			else
				System.out.println("* Invalid Selection! *");
		} // End while(equippingItems)

	}// End changeEquippedItems
	public void changeEquippedSpells() {
		
		boolean equippingSpells = true;
		int input;

		while (equippingSpells) {

			System.out.println("* You're allowed to equip a total of " + equippedSpells.length + " spells. *");
			System.out.println("* Which spell would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			displaySpells();
			displayEquippedSpells();
			input = scan.nextInt();

			//Check if there are any empty spots in the array first, if there is put the spell there.
			//If there are no empty spots in the array, make the player un-equip an spell.

			if(input == 0) {
				equippingSpells = false;
			}

			else if (equippedSpells[equippedSpells.length - 1] == null) {
				setEquippedSpells(equippedSpells.length - 1, ((MagicSpell) spells.get(input - 1)));
			}

			else {
				int ogInput = input;
				System.out.println("* Which spell do you want to un-equip? *");
				displayEquippedSpells();
				input = scan.nextInt();
				int ogLocation = input - 1;
				removeEquippedSpell(ogLocation);
				setEquippedSpells(ogLocation, ((MagicSpell) spells.get(ogInput - 1)));
			}

			if (input == 0) {
				equippingSpells = false;
			}

			//else
				//System.out.println("* Invalid Selection! *");
		} // End while(equippingSpells)

	}//End changeEquippedSPells

	public void removeEquippedSpell(int location) {
		equippedSpells[location] = null;
	}

	public String toString() {
		return "\nName: " + name + "\nLevel: " + level + "\nExperience: " + experience + "\nHealth: " + health
				+ "\nMana: " + mana + "\nMelee: " + melee + "\nDefense: " + defense + "\nIntellect: "
				+ intellect + "\nPerception: " + perception;
	}

	public static Player buildCharacter(Scanner scan){	


		Player player = new Player(" ", 0, 0, 0, 0, 0, 0, 0, 1);

		System.out.println("# Hello traveler, what is your name? #");
		player.setName(scan.nextLine());
		String answer = " ";
		boolean checkingName = true;

		while(checkingName){
			System.out.println("\n# Ah so your name is " + player.getName() + ". #" + "\n\n# Is that correct? #\n1.Yes\n2.No");
			answer = scan.nextLine();

			if(answer.equals("2")){
				System.out.println("\n# My apologies good friend, please tell me your name again. #");
				player.setName(scan.nextLine());
			}

			else
				checkingName = false;
		}//End while(checkingName)

		boolean spendingPoints = true;

		SPEND_POINTS:
			while(spendingPoints){

				int spendablePoints = 240;
				int input = 0;
				answer = " ";

				System.out.println("# Ok " + player.getName() + " you have " + spendablePoints + " points to spend in: Health, Mana, "
						+ "Melee, Defense, Intellect, and Perception. #"
						+ "\n# Use them wisley! #");

				System.out.println("\n# How many points would you like to spend in health? #");
				input = scan.nextInt();
				player.setHealth(input);
				spendablePoints -= input;
				System.out.println("# You now have " + spendablePoints + " points left. #");

				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in mana? #");
					input = scan.nextInt();
					player.setMana(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}

				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in melee? #");
					input = scan.nextInt();
					player.setMelee(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}

				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in defense? #");
					input = scan.nextInt();
					player.setDefense(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}

				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in intellect? #");
					input = scan.nextInt();
					player.setIntellect(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}

				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in perception? #");
					input = scan.nextInt();
					scan.nextLine();
					player.setPerception(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}

				System.out.println("\n# Ok " + player.getName() + " here is your character build. #");
				System.out.println("--------------------------------------------------------------------");
				System.out.println(player);
				System.out.println("--------------------------------------------------------------------");
				System.out.println("\n# Are you happy with your character build? #\n1.Yes\n2.No");
				answer = scan.nextLine();

				if(answer.equals("2")){
					continue SPEND_POINTS;
				}

				else
					spendingPoints = false;

			}//End while(spendingPoints)

		System.out.println("\n# Now let's get you some starter gear! #");

		player.setEquippedItems(ruggedHelmet.getEquippedItemSlot(), ruggedHelmet);
		player.setEquippedItems(ruggedArmor.getEquippedItemSlot(), ruggedArmor);
		player.setEquippedItems(ruggedLeggings.getEquippedItemSlot(), ruggedLeggings);
		player.setEquippedItems(ruggedShield.getEquippedItemSlot(), ruggedShield);
		player.setEquippedItems(ruggedSword.getEquippedItemSlot(), ruggedSword);
		player.setBlocking(player.getEquippedItems());
		player.setMeleeDamage(player.getEquippedItems());

		player.setInventory(healthPotion);
		healthPotion.setQuantity(3);



		System.out.println("# Here are the items that you now have equipped, I also gave you 3 Health Potions, check your inventory"
				+ " to see them. #");

		player.displayEquippedItems();

		System.out.println("# Wait! Before you leave out on your adventure, I wanted to tell you that you have 2 starter spells. #");
		System.out.println("# These are your starter spells. #");
		player.setEquippedSpells(0, fireBall);
		player.setEquippedSpells(1, lightHealing);
		player.setSpells(fireBall);
		player.setSpells(lightHealing);
		player.setSpells(testAttack);
		player.setSpells(testHealing);
		//player.changeEquippedSpells();
		player.displayEquippedSpells();



		return player;
	}//End buildCharacter

}// End Class Player
