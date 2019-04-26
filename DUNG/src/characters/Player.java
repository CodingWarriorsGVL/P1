package characters;

import java.util.*;

import item.Armor;
import item.Item;
import item.Weapon;
import static item.Armor.*;
import static characters.DamageSpell.*;
import static item.Weapon.*;
import static item.Potion.*;
import static item.Currency.*;

import static display.Display.*;

public class Player extends Entity {

	// Class Variables
	int xPosition;
	int yPosition;
	
	static Player player;

	public Player(String name, int health, int mana, int melee, int defense, int intellect, int perception, int level) {
		super(name, health, mana, melee, defense, intellect, perception, level, false);
	}

	// Getters and Setters




	// Other Methods

	public void displayEquippedItems() {
		println("--------------------------------------------------------------------");
		println("* Equipped Items *");
		for (int i = 0; i < equippedItems.length; i++) {
			print(equippedItems[i]);
		}
		println("--------------------------------------------------------------------");
	}

	public void displayInventory() {

		println("--------------------------------------------------------------------");
		println("* Inventory *");

		for (int i = 0; i < inventory.size(); i++) {
			println(i + 1 + ". " + inventory.get(i));
		}

		println("--------------------------------------------------------------------");

	}// End displayInventory

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
				this.setEquippedItems(((Item) inventory.get(input - 1)).getEquippedItemSlot(),
						((Item) inventory.get(input - 1)));
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
	

	public static Player buildCharacter(){	
		


		String nameTemp = input("# Hello traveler, what is your name? #");
		if (!nameTemp.equals("Speedy")) { // Fast character creation
			player = new Player(" ", 0, 0, 0, 0, 0, 0, 1);
			player.setAbilityPoints(240);
			player.setName(nameTemp);
			String answer = " ";
			boolean checkingName = true;

			while(checkingName){
				answer = input("\n# Ah so your name is " + player.getName() + ". #" + "\n\n# Is that correct? #\n1.Yes\n2.No");

				if(answer.equals("2")){
					player.setName(input("\n# My apologies good friend, please tell me your name again. #"));
				}

				else
					checkingName = false;
			}//End while(checkingName)

			boolean spendingPoints = true;

			while(spendingPoints){

				int spendablePoints = player.getAbilityPoints();
				int input = 0;
				answer = " ";

				println("# Ok " + player.getName() + " you have " + spendablePoints + " points to spend in: Health, Mana, "
						+ "Melee, Defense, Intellect, and Perception. #"
						+ "\n# Use them wisely! #");

				
				if(spendablePoints > 0) {									
					do {
						input = inputInt("\n# How many points would you like to spend in health? #");	
						if(spendablePoints >= input) {
							player.setHealth(input);
							player.setMaxHealth(input);							
						}
						else 
							println("You do not have enough spendable points for the quantity that you entered.");
							
					}while(input > spendablePoints);	
					spendablePoints -= input;
					input = 0;
					println("# You now have " + spendablePoints + " points left. #");
				}

				if(spendablePoints > 0){
					do {
						input = inputInt("\n# How many points would you like to spend in mana? #");
						if(spendablePoints >= input) {
							player.setMana(input);
							player.setMaxMana(input);
						}
						else
							println("You do not have enough spendable points for the quantity you entered.");
					}while(input > spendablePoints);
					spendablePoints -= input;
					println("# You now have " + spendablePoints + " points left. #");
					input = 0;
				}

				if(spendablePoints > 0){
					do {
						input = inputInt("\n# How many points would you like to spend in melee? #");
						if(spendablePoints >= input) {
							player.setMelee(input);
						}	
						else 
							println("You do not have enough spendable points for the quantity that you entered.");
					}while(input > spendablePoints);	
					spendablePoints -= input;
					println("# You now have " + spendablePoints + " points left. #");
					input = 0;
				}

				if(spendablePoints > 0){			
					do {
						input = inputInt("\n# How many points would you like to spend in defense? #");
						if(spendablePoints >= input) {
							player.setDefense(input);
						}
						else 
							println("You do not have enough spendable points for the quantity that you entered.");
					}while(input > spendablePoints);
					spendablePoints -= input;
					println("# You now have " + spendablePoints + " points left. #");
					input = 0;
				}

				if(spendablePoints > 0){
					do {				
						input = inputInt("\n# How many points would you like to spend in intellect? #");
						if(spendablePoints >= input) {
							player.setIntellect(input);
						}
						else 
							println("You do not have enough spendable points for the quantity that you entered.");
					}while(input > spendablePoints);
					spendablePoints -= input;
					println("# You now have " + spendablePoints + " points left. #");
					input = 0;
				}

				if(spendablePoints > 0){
					do {
						input = inputInt("\n# How many points would you like to spend in perception? #");
						if(spendablePoints >= input) {
							player.setPerception(input);				
						}
						else 
							println("You do not have enough spendable points for the quantity that you entered.");
					}while(input > spendablePoints);
					spendablePoints -= input;
					println("# You now have " + spendablePoints + " points left. #");
					input = 0;
				}

				println("\n# Ok " + player.getName() + " here is your character build. #");
				println("--------------------------------------------------------------------");
				print(player);
				println("--------------------------------------------------------------------");
				answer = input("\n# Are you happy with your character build? #\n1.Yes\n2.No");

				if(answer.equals("2")){
					spendingPoints = true;
				} else 
					spendingPoints = false;
			}//End while(spendingPoints)
		} else  {
			player = new Player("Speedy", 80, 30, 40, 40, 30, 40, 1);
		}
		
			println("\n# Now let's get you some starter gear! #");

			player.setEquippedItems(ruggedHelmet.getEquippedItemSlot(), ruggedHelmet);
			player.setEquippedItems(ruggedArmor.getEquippedItemSlot(), ruggedArmor);
			player.setEquippedItems(ruggedLeggings.getEquippedItemSlot(), ruggedLeggings);
			player.setEquippedItems(ruggedShield.getEquippedItemSlot(), ruggedShield);
			player.setEquippedItems(ruggedSword.getEquippedItemSlot(), ruggedSword);
			player.setBlocking(player.getEquippedItems());
			player.setMeleeDamage(player.getEquippedItems());


			player.setInventory(healthPotion);
			player.setInventory(goldCoin);

			healthPotion.setQuantity(3);
			goldCoin.setQuantity(10);



			println("# Here are the items that you now have equipped, I also gave you 3 Health Potions and some" +
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

}// End Class Player
