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

	int damage = attackRating;
	int blocking = defense;
	
	public static final Player player1 = new Player(" ", 0, 0, 0, 0, 0, 0, 0, 1);

	public ArrayList playerInventory = new ArrayList();
	public ArrayList playerSpells = new ArrayList();
	

	Item[] equippedItems = new Item[5];
	
	MagicSpell[] equippedSpells = new MagicSpell[2];
	
	static final int
	HELMET = 0,
	ARMOR = 1,
	LEGGINGS = 2,
	SHIELD = 3,
	WEAPON = 4;
	
	
	public Player(String name, int health, int mana, int attackRating, int defense, int intellect, int perception,int experience, int level) {
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

	public void displayPlayerInventory() {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Player Inventory *");

		for (int i = 0; i < playerInventory.size(); i++) {
			System.out.println(i + 1 + ". " + ((Item) playerInventory.get(i)));
		}

		System.out.println("--------------------------------------------------------------------");

	}// End displayPlayerInventory

	public void displayEquippedSpells() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println(equippedSpells.length);
		System.out.println("* Equipped Spells *");
		for (int i = 0; i < equippedSpells.length; i++) {
			System.out.println((i+1) + ". " + equippedSpells[i].getName());
		}
		System.out.println("--------------------------------------------------------------------");
	}

	public void displayPlayerSpells() {

		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Spells that you know *");

		for (int i = 0; i < playerSpells.size(); i++) {
			System.out.println(i + 1 + ". " + ((MagicSpell) playerSpells.get(i)).getName());
		}
		System.out.println("--------------------------------------------------------------------");

	}// End displayEquippedSpells
	 
	
	public void changeEquippedItems() {

		boolean equippingItems = true;
		int input;

		//Item[] currentlyEquipped = equippedItems;
		//ArrayList currentPlayerInventory = playerInventory;
		
		while (equippingItems) {
			
			player1.displayPlayerInventory();
			player1.displayEquippedItems();
			
			System.out.println("* Which item would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			input = scan.nextInt();

			if (input > 0 && input <= playerInventory.size() && ((Item) playerInventory.get(input - 1)).getEquippedItemSlot() < 5) {
				playerInventory.add(equippedItems[((Item) playerInventory.get(input - 1)).getEquippedItemSlot()]);
				player1.removeEquippedItems(((Item) playerInventory.get(input - 1)).getEquippedItemSlot());
				player1.setEquippedItems(((Item) playerInventory.get(input - 1)).getEquippedItemSlot(),
						((Item) playerInventory.get(input - 1)));
				playerInventory.remove(((Item) playerInventory.get(input - 1)));

				player1.setPlayerDamage(equippedItems);
				player1.setPlayerBlocking(equippedItems);

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
	public void changeEquippedSpells() {
		
		equippedSpells = new MagicSpell[level + 1];
		boolean equippingSpells = true;
		int input;
		
		while (equippingSpells) {
			
			System.out.println("* You're allowed to equip a total of " + equippedSpells.length + " spells. *");
			System.out.println("* Which spell would you like to equip? *");
			System.out.println("* Enter 0 when you're finished equipping items. *");
			displayPlayerSpells();
			displayEquippedSpells();
			input = scan.nextInt();
			
			//Check if there are any empty spots in the array first, if there is put the spell there.
			//If there are no empty spots in the array, make the player un-equip an spell.
			
			if(input == 0) {
				equippingSpells = false;
			}
			
			else if (equippedSpells[equippedSpells.length - 1] == null) {
				setEquippedSpells(equippedSpells.length - 1, ((MagicSpell) playerSpells.get(input - 1)));
			}
			
			else {
				int ogInput = input;
				System.out.println("* Which spell do you want to un-equip? *");
				displayEquippedSpells();
				input = scan.nextInt();
				int ogLocation = input - 1;
				removeEquippedSpell(ogLocation);
				setEquippedSpells(ogLocation, ((MagicSpell) playerSpells.get(ogInput - 1)));
			}
			
			
				
				//System.out.println("* Your new Attack Rating is: " + player1.getPlayerDamage() + " *");
				//System.out.println("* Your new Defense Rating is: " + player1.getPlayerBlocking() + " *");

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
				+ "\nMana: " + mana + "\nAttack Rating: " + attackRating + "\nDefense: " + defense + "\nIntellect: "
				+ intellect + "\nPerception: " + perception;
	}
	
public static void buildCharacter(Scanner scan){	
		
		boolean buildingCharacter = true;
		
		while(buildingCharacter){
			System.out.println("# Hello traveler, what is your name? #");
			player1.setName(scan.nextLine());
			String answer = " ";
			boolean checkingName = true;
			
			while(checkingName){
				System.out.println("\n# Ah so your name is " + player1.getName() + ". #" + "\n\n# Is that correct? #\n1.Yes\n2.No");
				answer = scan.nextLine();
				
				if(answer.equals("2")){
					System.out.println("\n# My apologies good friend, please tell me your name again. #");
					player1.setName(scan.nextLine());
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
				
				System.out.println("# Ok " + player1.getName() + " you have " + spendablePoints + " points to spend in: Health, Mana, "
						+ "Attack Rating, Defense, Intellect, and Perception. #"
						+ "\n# Use them wisley! #");
				
				System.out.println("\n# How many points would you like to spend in health? #");
				input = scan.nextInt();
				player1.setHealth(input);
				spendablePoints -= input;
				System.out.println("# You now have " + spendablePoints + " points left. #");
				
				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in mana? #");
					input = scan.nextInt();
					player1.setMana(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in attack rating? #");
					input = scan.nextInt();
					player1.setAttackRating(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in defense? #");
					input = scan.nextInt();
					player1.setDefense(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in intellect? #");
					input = scan.nextInt();
					player1.setIntellect(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("\n# How many points would you like to spend in perception? #");
					input = scan.nextInt();
					scan.nextLine();
					player1.setPerception(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				System.out.println("\n# Ok " + player1.getName() + " here is your character build. #");
				System.out.println("--------------------------------------------------------------------");
				System.out.println(player1);
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
						
			player1.setEquippedItems(ruggedHelmet.getEquippedItemSlot(), ruggedHelmet);
			player1.setEquippedItems(ruggedArmor.getEquippedItemSlot(), ruggedArmor);
			player1.setEquippedItems(ruggedLeggings.getEquippedItemSlot(), ruggedLeggings);
			player1.setEquippedItems(ruggedShield.getEquippedItemSlot(), ruggedShield);
			player1.setEquippedItems(ruggedSword.getEquippedItemSlot(), ruggedSword);
			player1.setPlayerBlocking(player1.getEquippedItems());
			player1.setPlayerDamage(player1.getEquippedItems());
			
			player1.setPlayerInventory(healthPotion);
			healthPotion.setQuantity(3);
			
			
						
			System.out.println("# Here are the items that you now have equipped, I also gave you 3 Health Potions, check your inventory"
					+ " to see them. #");
			
			player1.displayEquippedItems();
			
			System.out.println("# Wait! Before you leave out on your adventure, I wanted to tell you that you have 2 starter spells. #");
			System.out.println("# These are your starter spells. #");
			player1.setEquippedSpells(0, fireBall);
			player1.setEquippedSpells(1, lightHealing);
			player1.setPlayerSpells(fireBall);
			player1.setPlayerSpells(lightHealing);
			player1.setPlayerSpells(testAttack);
			player1.setPlayerSpells(testHealing);
			player1.changeEquippedSpells();
			//player1.displayEquippedSpells();
			
			buildingCharacter = false;	
		}//End while(buildingCharacter)
	}//End buildCharacter
	
}// End Class Player
