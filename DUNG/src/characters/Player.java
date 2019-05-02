package characters;

import java.util.ArrayList;

import display.Display;
import item.Armor;
import item.Item;
import item.Weapon;
import static item.Armor.*;
import static characters.DamageSpell.*;
import static item.Weapon.*;
import static item.Potion.*;
import static item.Currency.*;
import navigation.Room;

import static display.Display.*;

public class Player extends Entity {

	// Class Variables
	int xPosition;
	int yPosition;
	Room currentRoom;

	static Player player;

	public Player(String name, int health, int mana, int melee, int defense, int intellect, int perception, int level) {
		super(name, health, mana, melee, defense, intellect, perception, level, false);
	}

	// Getters and Setters

	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}
	public Room getCurrentRoom() {
		return currentRoom;
	}



	// Other Methods

	public void displayEquippedItems() {
		println("--------------------------------------------------------------------");
		println("* Equipped Items *");
		for (int i = 0; i < equippedItems.length; i++) {
			println(equippedItems[i]);
		}
		println("--------------------------------------------------------------------");
	}



	public void displayEquippedSpells() {
		println("--------------------------------------------------------------------");
		println("* Equipped Spells *");
		for (int i = 0; i < equippedSpells.length; i++) {
			if (equippedSpells[i] != null) {
				println((i+1) + ". " + equippedSpells[i].getName());
			}
		}
		println("--------------------------------------------------------------------");
	}

	public void displaySpells() {

		println("--------------------------------------------------------------------");
		println("* Spell  Book *");

		for (int i = 0; i < spells.size(); i++) {
			println(i + 1 + ". " + ((MagicSpell) spells.get(i)).getName());
		}
		println("--------------------------------------------------------------------");

	}// End displayEquippedSpells


	public void changeEquippedItems() {

		boolean equippingItems = true;
		int input;

		while (equippingItems) {

			this.displayInventory();
			this.displayEquippedItems();

			println("* Which item would you like to equip? *");
			println("* Enter 0 when you're finished equipping items. *");
			input = inputInt("");

			if (input > 0 && input <= inventory.size() && ((Item) inventory.get(input - 1)).getEquippedItemSlot() < 5) {
				inventory.add(equippedItems[((Item) inventory.get(input - 1)).getEquippedItemSlot()]);
				this.removeEquippedItems(((Item) inventory.get(input - 1)).getEquippedItemSlot());
				this.setEquippedItems(((Item) inventory.get(input - 1)));
				inventory.remove(((Item) inventory.get(input - 1)));

				this.setMeleeDamage(equippedItems);
				this.setBlocking(equippedItems);

				println("* Your new Attack Rating is: " + this.getMeleeDamage() + " *");
				println("* Your new Defense Rating is: " + this.getBlocking() + " *");
			}

			else if (input > 0 && ((Item) inventory.get(input - 1)).getEquippedItemSlot() > 4) {
				println("* This item can not be equipped. *");
			}

			else if(input == 0) {
				equippingItems = false;
			}

			else
				println("* Invalid Selection! *");
		} // End while(equippingItems)

	}// End changeEquippedItems
	public void changeEquippedSpells() {

		boolean equippingSpells = true;
		int input;

		while (equippingSpells) {

			println("* You're allowed to equip a total of " + equippedSpells.length + " spells. *");
			displaySpells();
			displayEquippedSpells();
			input = inputInt("* Which spell would you like to equip? *\n* Enter 0 when you're finished equipping items. *");

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
				println("* Which spell do you want to un-equip? *");
				displayEquippedSpells();
				input = inputInt("");
				int ogLocation = input - 1;
				removeEquippedSpell(ogLocation);
				setEquippedSpells(ogLocation, ((MagicSpell) spells.get(ogInput - 1)));
			}

			if (input == 0) {
				equippingSpells = false;
			}

			//else
			//println("* Invalid Selection! *");
		} // End while(equippingSpells)

	}//End changeEquippedSPells

	public void removeEquippedSpell(int location) {
		equippedSpells[location] = null;
	}

	public int getXPosition() {
		return this.xPosition;
	}
	public void setXPosition(int x) {
		this.xPosition = x;
	}
	public int getYPosition() {
		return this.yPosition;
	}
	public void setYPosition(int y) {
		this.yPosition = y;
	}

	public String toString() {
		return "\nName: " + name + "\nLevel: " + level + "\nExperience: " + experience + "\nHealth: " + health
				+ "\nMana: " + mana + "\nMelee: " + melee + "\nDefense: " + defense + "\nIntellect: "
				+ intellect + "\nPerception: " + perception;
	}


	public static Player buildCharacter() {	
		String nameTemp = input("# Hello traveler, what is your name? #");
		if (!nameTemp.equals("Speedy")) { // Fast character creation
			player = new Player(" ", 0, 0, 0, 0, 0, 0, 1);
			player.setAbilityPoints(240);
			player.setName(nameTemp);
			String answer = " ";
			boolean checkingName = true;

			while(checkingName){
				println("# Ah so your name is " + player.getName() + ". #");
				answer = input("# Is that correct? #", "Yes", "No");

				if(answer.equals("No")){
					player.setName(input("# My apologies good friend, please tell me your name again. #"));
				}

				else
					checkingName = false;
			}//End while(checkingName)
			player.spendPoints();


		} else player = new Player("Speedy", 80, 30, 40, 40, 30, 40, 1);
		Display.println("\n# Now let's get you some starter gear! #");

		player.setEquippedItems(ruggedHelmet);
		player.setEquippedItems(ruggedArmor);
		player.setEquippedItems(ruggedLeggings);
		player.setEquippedItems(ruggedShield);
		player.setEquippedItems(testSword);
		player.setBlocking(player.getEquippedItems());
		player.setMeleeDamage(player.getEquippedItems());


		player.setInventory(healthPotion);
		//player.setInventory(goldCoin);

		healthPotion.setQuantity(3);
		//goldCoin.setQuantity(10);

		player.setMoney(10); // Gives 10 gold coins to work with.

		Display.println("# Here are the items that you now have equipped, I also gave you 3 Health Potions and some" +
				" Gold coins to get you started check your inventory to see them. #");

		player.displayEquippedItems();
		player.displayInventory();

		println("# Wait! Before you leave out on your adventure, I wanted to tell you that you have 2 starter spells. #");
		println("# These are your starter spells. #");
		player.setEquippedSpells(0, fireBolt);
		player.setEquippedSpells(1, lightHealing);
		player.setSpells(fireBolt);
		player.setSpells(lightHealing);
		player.setSpells(testAttack);
		player.setSpells(testHealing);
		//player.changeEquippedSpells();
		player.displayEquippedSpells();



		return player;
	}//End buildCharacter

	public void spendPoints() {
		boolean spendingPoints = true;
		String answer;
		Player player = this; // Yes this is bad, but it was FAST, TODO correct variables.

		while(spendingPoints){
			int spendablePoints = player.getAbilityPoints();
			int input = 0;
			answer = " ";

			println("# Ok " + player.getName() + " you have " + spendablePoints + " points to spend in: Health, Mana, "
					+ "Melee, Defense, Intellect, and Perception. #"
					+ "\n# Use them wisley! #");

			if(spendablePoints > 0) {									
				do {
					input = inputInt("\n# How many points would you like to add in health? #");	
					if(spendablePoints >= input) {
						player.setHealth(input + player.getHealth());
						player.setMaxHealth(input + player.getMaxHealth());							
					}
					else 
						Display.println("You do not have enough spendable points for the quantity that you entered.");
				}while(input > spendablePoints);	
				spendablePoints -= input;
				input = 0;
				println("# You now have " + spendablePoints + " points left. #");
			}

			if(spendablePoints > 0){
				do {
					input = inputInt("\n# How many points would you like to add in mana? #");
					if(spendablePoints >= input) {
						player.setMana(input + player.getMana()); // This can be cheesed!
						player.setMaxMana(input + player.getMaxMana());
					}
					else
						Display.println("You do not have enough spendable points for the quantity you entered.");
				}while(input > spendablePoints);
				spendablePoints -= input;
				println("# You now have " + spendablePoints + " points left. #");
				input = 0;
			}

			if(spendablePoints > 0){
				do {
					input = inputInt("\n# How many points would you like to add in melee? #");
					if(spendablePoints >= input) {
						player.setMelee(input + player.getMelee());
					}	
					else 
						Display.println("You do not have enough spendable points for the quantity that you entered.");
				}while(input > spendablePoints);	
				spendablePoints -= input;
				println("# You now have " + spendablePoints + " points left. #");
				input = 0;
			}

			if(spendablePoints > 0){			
				do {
					input = inputInt("\n# How many points would you like to add in defense? #");
					if(spendablePoints >= input) {
						player.setDefense(input + player.getDefense());
					}
					else 
						Display.println("You do not have enough spendable points for the quantity that you entered.");
				}while(input > spendablePoints);
				spendablePoints -= input;
				println("# You now have " + spendablePoints + " points left. #");
				input = 0;
			}

			if(spendablePoints > 0){
				do {				
					input = inputInt("\n# How many points would you like to add in intellect? #");
					if(spendablePoints >= input) {
						player.setIntellect(input + player.getIntellect());
					}
					else 
						Display.println("You do not have enough spendable points for the quantity that you entered.");
				}while(input > spendablePoints);
				spendablePoints -= input;
				println("# You now have " + spendablePoints + " points left. #");
				input = 0;
			}

			if(spendablePoints > 0){
				do {
					input = inputInt("\n# How many points would you like to add in perception? #");
					if(spendablePoints >= input) {
						player.setPerception(input + player.getPerception());				
					}
					else 
						Display.println("You do not have enough spendable points for the quantity that you entered.");
				}while(input > spendablePoints);
				spendablePoints -= input;
				println("# You now have " + spendablePoints + " points left. #");
				input = 0;
			}

			println("\n# Ok " + player.getName() + " here is your character build. #");
			println("--------------------------------------------------------------------");
			displayStats();
			println("--------------------------------------------------------------------");


			answer = input("\n# Are you happy with your character build? #", "Yes", "No", "Back");;
			if(answer.equals("No")) {
				spendingPoints = true;
			} else if (answer.equals("Yes")) {
				spendingPoints = false; 
				// TODO Set spent points in stone. 
			} else if (answer.equals("Back")) {
				spendingPoints = false;
				// TODO Revert the spent points
			}
			
			player.setAbilityPoints(spendablePoints);
		}//End while(spendingPoints)
	}

	public void characterMenu() {
		String input;
		Display.println("--------------------------------------------------------------------");
		input = Display.input("Character Menu, Choose an option (Inventory, Spells, Attributes, Back)", "Inventory", "Spells", "Attributes", "Back");

		if (input.toLowerCase().charAt(0)=='i') {
			characterInventory();
		}
		else if (input.toLowerCase().charAt(0)=='s') {
			changeEquippedSpells();
		}
		else if (input.toLowerCase().charAt(0)=='a') {
			spendPoints();
		}
	}

	public void characterInventory() {
		String actionOnItem;
		Item choosenItem;
		int itemNum;
		ArrayList<Entity> targets = new ArrayList<Entity>();
		ArrayList<Entity> potentialTargets; // Everyone in the room.
		if (currentRoom.getEnties() != null)
			potentialTargets = new ArrayList<Entity>(currentRoom.getEnties());
		else potentialTargets = new ArrayList<Entity>();

		this.displayInventory();
		itemNum = Display.inputInt("Choose an item number: ");
		choosenItem = this.getInventory().get(itemNum-1);

		Display.print("What would you like to do? Options: ");
		String inputOptions[] = new String[3];
		if (choosenItem.isConsumable() || choosenItem.isEquipable())
			inputOptions[0] = "use";
			//Display.print("Use, ");
		//Display.print("Drop, Give");
		inputOptions[1] = "drop";
		inputOptions[2] = "give";
		do {
			actionOnItem = Display.input("", inputOptions);
		} while (!((actionOnItem.equals("use") && (choosenItem.isConsumable() || choosenItem.isEquipable())) || actionOnItem.equals("drop") || actionOnItem.equals("give"))); // Watch the parentheses 

		if (/*choosenItem.isTargetable() ||*/ actionOnItem.equals("give")) {
			targets.add(potentialTargets.get(pickTarget(potentialTargets)-1));
		}
		else targets.add(this);

		useItem(getInventory().get(itemNum-1), actionOnItem, targets);
	}

	public static int pickTarget(ArrayList<Entity> targetList) {
		Display.println("Targets:");
		for (int i=0; i<targetList.size(); i++) {
			Display.println((i+1)+". " + targetList.get(i).getName());
		}

		int choice;
		if (targetList.size() > 1) {
			do {
				choice = Display.inputInt("Choose a target: (1 - " + targetList.size() + ")");
			} while (!(choice > 0 && choice <= targetList.size()));
		} else {
			choice = 1;
		}
		return choice;
	}


}// End Class Player
