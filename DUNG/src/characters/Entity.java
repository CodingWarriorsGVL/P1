package characters;

import java.util.ArrayList;
import item.Armor;
import item.Item;
import item.Weapon;

public class Entity {
	
	//Class Variables
	String name;
	int health, melee, defense, intellect, perception, experience, mana, level;
	int maxHealth, money;

	boolean isAI;
	
	int meleeDamage = melee;
	int blocking = defense;
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public ArrayList<MagicSpell> spells = new ArrayList<MagicSpell>();


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

	public void setEquippedItems(int location, Item item) {
		equippedItems[location] = item;
	}

	public void setSpells(MagicSpell spell) {
		spells.add(spell);
	}

	public ArrayList<MagicSpell> getSpells() {
		return spells;
	}

	public void setInventory(Item item) {
		inventory.add(item);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
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
	
	public void addXP(int xp) {
		experience += xp;
		//TODO add level up here?
	}

	public boolean isAI() {
		return isAI;
	}
		
	//Other Methods	
	public void attack(int attack) {
		
	}
	
	public boolean isAlive() {
		return health > 0;
	}
}// End Class Entity
