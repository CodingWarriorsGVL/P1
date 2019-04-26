package characters;

import display.Display;

import java.util.ArrayList;
import item.Armor;
import item.Item;
import item.Potion;
import item.Weapon;

public class Entity {
	
	//Class Variables
	String name;
	int health, melee, defense, intellect, perception, experience, mana, level;
	int maxHealth, money, maxMana, abilityPoints;

	boolean isAI;
	
	int meleeDamage = melee;
	int blocking = defense;
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public ArrayList<MagicSpell> spells = new ArrayList<MagicSpell>();
	
	final int LEVEL_TIER_INCREASE = 5, ABILITY_POINTS_PER = 10; // Sets the level up conditions.


	Item[] equippedItems = new Item[5];

	MagicSpell[] equippedSpells;

	//Locations in the equippedItems array that specific equipment is assigned to.
	public static final int
	HELMET = 0,
	ARMOR = 1,
	LEGGINGS = 2,
	SHIELD = 3,
	WEAPON = 4;

	public Entity(String name, int health, int mana, int melee, int defense, int intellect, int perception, int level, boolean isAI) {
		this.name = name;
		this.health = health;
		maxHealth = health;
		this.melee = melee;
		this.defense = defense;
		this.intellect = intellect;
		this.perception = perception;
		this.mana = mana;
		maxMana = mana;
		this.level = level;
		equippedSpells = new MagicSpell[level+1];
		this.isAI = isAI;
	}
	
	//Getters and Setters
	public int[] getPosition() {
		int[] out = {0,1};
		return out;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int max) {
		this.maxHealth = max;
	}
	
	public int getMaxMana() {
		return maxMana;
	}
	
	public void setMaxMana(int max) {
		this.maxMana = max;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMelee() {
		return melee;
	}

	public void setMelee(int melee) {
		this.melee = melee;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMove() {
		return 1;
	}
	
	public MagicSpell[] getEquippedSpells() {
		return equippedSpells;
	}

	public void setEquippedSpells(int location, MagicSpell spell) {
		equippedSpells[location] = spell;
	}

	public Item[] getEquippedItems() {
		return equippedItems;
	}

	public void setEquippedItems(Item item) {
		equippedItems[item.getEquippedItemSlot()] = item;
	}

	public void setSpells(MagicSpell spell) {
		spells.add(spell);
	}

	public ArrayList<MagicSpell> getSpells() {
		return spells;
	}

	public void setInventory(Item item) {
		inventory.add(item);
		item.setQuantity(item.getQuantity() + 1); // What is this mess?
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void clearInventory() {
		inventory = new ArrayList<Item>();
		money = 0;
	}
	
	public void displayInventory(ArrayList<Item> inventory) {
		Display.println("--------------------------------------------------------------------");
		Display.println("* Inventory *");

		for (int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getQuantity() == 0)
				inventory.remove(inventory.get(i));
			System.out.println(inventory.get(i));
		}

		Display.println("--------------------------------------------------------------------");
	}

	public void removeEquippedItems(int location) {
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
	
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return money;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return level;
	}
	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	
	public void addXP(int xp) {
		experience += xp;
		while (experience >= xpToLevel()) { // Loops if you have enough xp to keep leveling.
			levelUp(1); // Only levels you once.
		}
	}
	
	public int xpToLevel() { // The return value and equation for leveling.
		int newXP = (int)(100*2.0*level*(level/100.0)); // Fun xp needed to level equation. Don't ask - Jared.
		return newXP;
	}
	
	public void levelUp(int numLvls) {
		for (int i=0; i<numLvls; i++) { // Allows for multiple levels to be given at once.
			//abilityPoints = (int)(abilityPoints + Math.ceil(level/5.0)*10);
			abilityPoints = (int)(abilityPoints + Math.ceil(level/LEVEL_TIER_INCREASE)*ABILITY_POINTS_PER);
			experience -= xpToLevel(); // Subtracts the level up from your xp pool.
			if (experience < 0) // This is mostly as catch that should happen if forced to level to keep you from having negative xp.
				experience = 0;
			level += 1;
			if (isAI == false) // Only tells you when non AI controlled entities leveled. Since there is no way to check if they are on your team yet.
				Display.print(name + " has leveled up to level "+level+"!\n");
		}
	}

	public boolean isAI() {
		return isAI;
	}
		
	//Other Methods	
	public boolean isAlive() {
		return health > 0;
	}
	
	public boolean consumeItem(Item i, Entity e) {
		return i.consume(i,e);	
	}
	
	public void useItem(Item item, String action, ArrayList<Entity> targets) {
		Display.debug(action);
		if (action.equals("drop")) {
			//droppedLoot.add(item); // Drops it on the ground for someone to pick up at the end of combat (not recommended). TODO fix
			this.getInventory().remove(item);
			Display.println(this.getName() + " drops " + item.getName() + " on the ground.");
		}
		else if (action.equals("use")) {
			if (item.isEquipable()) {
				this.setEquippedItems(item); 
				Display.println(getName() + " equips " + item.getName() + ".");
			}
			else if (item.isConsumable()) {
				if (this.consumeItem(item, this)) 
					Display.println(this.getName() + " consumes " + item.getName() + ".");
				else Display.debug("Item was not consumable!!!");
			}
			// else if (item.isTargetable()) { 
		}
		else if (action.equals("give")) {
			targets.get(0).getInventory().add(item);
			this.getInventory().remove(item);
			Display.println(this.getName() + " gives " + item.getName() + " to " + targets.get(0).getName() + ".");
		}
		else Display.debug("action input wrong");
	}
	
	
}// End Class Entity
