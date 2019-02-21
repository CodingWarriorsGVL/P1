package characters;

import java.util.*;

import item.Armor;
import item.Item;
import item.Weapon;

public class Player extends Entity {
	
	Scanner scan = new Scanner(System.in);
	
	//Class Variables
	int intellect, perception, experience, mana;
	
	int damage = attackRating;
	int blocking = defense;
	
	public ArrayList playerInventory = new ArrayList();
	
	Item[] equippedItems = new Item[5];

	public Player(String name, int health, int mana, int attackRating, int defense, int intellect, int perception, int experience) {
		super(name, health, attackRating, defense);
		this.intellect = intellect;
		this.perception = perception;
		this.experience = experience;
		this.mana = mana;
	}
	
	//Getters and Setters
	
	public Item[] getEquippedItems(){
		return equippedItems;
	}
	
	
	public void setPlayerInventory(Item item){
		playerInventory.add(item);
	}
	
	public ArrayList getPlayerInventory(){
		return playerInventory;
	}
	
	
	public void removeEquippedItems(int location){
		if(equippedItems[location] instanceof Armor){
			((Armor)equippedItems[location]).setEquipped(false);
		}
		
		else if(equippedItems[location] instanceof Weapon){
			((Weapon)equippedItems[location]).setEquipped(false);
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
	
	public void setMana(int mana){
		this.mana = mana;
	}
	
	public int getMana(){
		return mana;
	}
	
	public void setPlayerDamage(Item[] equippedItems){
		damage = 0;
		damage = ((Weapon)equippedItems[4]).getAttack() + attackRating;
	}
	
	public int getPlayerDamage(){
		return damage;
	}
	
	public void setPlayerBlocking(Item[] equippedItems){
		int equipmentDefense = 0;
		blocking = 0;
		for(int i = 0; i < 3; i++){
			equipmentDefense += ((Armor)equippedItems[i]).getDefense();
		}
		blocking = equipmentDefense + defense;
	}
	
	public int getPlayerBlocking(){
		return blocking;
	}
	
	public void setEquippedItems(int location, Item item){
		equippedItems[location] = item;
		if(item instanceof Armor){
			((Armor)item).setEquipped(true);
		}
		
		else if(item instanceof Weapon){
			((Weapon)item).setEquipped(true);
		}
	}
	
	
	
	//Other Methods
	
	public void displayEquippedItems(Item[] equippedItems){
		System.out.println("--------------------------------------------------------------------");
		System.out.println("* Equipped Items *");
		for(int i = 0; i < equippedItems.length; i++){
			System.out.println(equippedItems[i]);
		}
		System.out.println("--------------------------------------------------------------------");
	}
	
	public void displayPlayerInventory(Player player1, ArrayList playerInventory){
		
		boolean viewingInventory = true;
		int input;
		
		Item[] currentlyEquipped = player1.getEquippedItems();
		
		while(viewingInventory){
			System.out.println("--------------------------------------------------------------------");
			System.out.println("* Player Inventory *");
			
			for(int i = 0; i < playerInventory.size(); i++){
				System.out.println(i + 1 + ". " + ((Item)playerInventory.get(i)));
			}
			
			System.out.println("--------------------------------------------------------------------");
			System.out.println("\n* What would you like to do? *\n1. Equip an item\n2. View equipped items\n3. Exit Inventory");
			
			input = scan.nextInt();
			
			if(input == 1){
				System.out.println("* Which item would you like to equip? *");
				input = scan.nextInt();
				
				if(input <= playerInventory.size() && ((Item)playerInventory.get(input - 1)).getEquippedItemSlot() < 5){
					playerInventory.add(currentlyEquipped[((Item)playerInventory.get(input - 1)).getEquippedItemSlot()]);
					player1.removeEquippedItems(((Item)playerInventory.get(input - 1)).getEquippedItemSlot());
					player1.setEquippedItems(((Item)playerInventory.get(input - 1)).getEquippedItemSlot(), ((Item)playerInventory.get(input - 1)));
					playerInventory.remove(((Item)playerInventory.get(input - 1)));
					
					player1.setPlayerDamage(currentlyEquipped);
					player1.setPlayerBlocking(currentlyEquipped);
					
					System.out.println("* Your new Attack Rating is: " + player1.getPlayerDamage() + " *");
					System.out.println("* Your new Defense Rating is: " + player1.getPlayerBlocking() + " *");
				}
				
				else if(((Item)playerInventory.get(input - 1)).getEquippedItemSlot() > 4){
					System.out.println("* This item can not be equipped. *");
				}
				
				else
					System.out.println("* Invalid Selection! *");
				
			}
			else if(input == 2){
				player1.displayEquippedItems(currentlyEquipped);
			}
				
			else if(input == 3)
				viewingInventory = false;
			
			else
				System.out.println("* Invalid Selection! *");
		}//End while(viewingInventory)
	}//End displayPlayerInventory
	
	public String toString(){
		return "\nName: " + name + "\nHealth: " + health + "\nMana: " + mana + "\nAttack Rating: " + attackRating + "\nDefense: " + defense +
				"\nIntellect: " + intellect + "\nPerception: " + perception;
	}
}// End Class Player
