package characters;

import java.util.ArrayList;

import item.Item;

// Prototype for an Inventory class to better handle items and quantity.
// Do we want to hold money in the inventory, or the Entity? Either is pretty simple.


public class Inventory {
	protected ArrayList<Item> inventory;
	protected ArrayList<Integer> inventoryCount;

	public Inventory() {
		inventory = new ArrayList<Item>();
		inventoryCount = new ArrayList<Integer>();
	}
	public Inventory(Inventory addedInventory) {
		inventory = new ArrayList<Item>();
		inventoryCount = new ArrayList<Integer>();
		add(addedInventory);
	}
	
	public void add(Item item) {
		add(item, 1);
	}
	public void add(Item item, int count) {
		if (!inventory.contains(item)) {
			inventory.add(item);
			inventoryCount.add(count);
		}
		else inventoryCount.set(inventory.lastIndexOf(item), inventoryCount.get(inventory.lastIndexOf(item))+count );
	}
	public void add(Inventory addedInventory) {
		for (int i=0; i<addedInventory.size(); i++) {
			add(addedInventory.get(i), addedInventory.getCount(i));
		}
	}
	
	public void remove(Item item) {
		remove(item, 1);
	}
	public void remove(Item item, int count) {
		int index = inventory.lastIndexOf(item);
		if (inventoryCount.get(index) > 0)
			inventoryCount.set(index, inventoryCount.get(index)-count );
		if (inventoryCount.get(index) <= 0) {
			inventoryCount.remove(index);
			inventory.remove(item);
		}
	}
	
	public Item get(int index) {
		return inventory.get(index);
	}
	
	public int getCount(int index) {
		return inventoryCount.get(index);
	}
	public int getCount(Item item) {
		if (inventory.contains(item))
			return inventoryCount.get(inventory.lastIndexOf(item));
		else return 0;
	}
	
	public int lastIndexOf(Item item) {
		return inventory.lastIndexOf(item);
	}
	public int size() {
		return inventory.size();
	}
	
	public ArrayList<Item> getItemArray() {
		return inventory;
	}
	public ArrayList<Integer> getCountArray() {
		return inventoryCount;
	}
	
	public void clear() {
		inventory = new ArrayList<Item>();
		inventoryCount = new ArrayList<Integer>();
	}
	
}
