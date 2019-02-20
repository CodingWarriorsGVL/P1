package characters;

import java.util.ArrayList;

import item.Armor;

public class Player extends Entity {
	
	//Class Variables
	int intellect, perception, experience, mana;
	
	public ArrayList playerInventory = new ArrayList();

	public Player(String name, int health, int mana, int attackRating, int defense, int intellect, int perception, int experience) {
		super(name, health, attackRating, defense);
		this.intellect = intellect;
		this.perception = perception;
		this.experience = experience;
		this.mana = mana;
	}
	
	//Getters and Setters
	
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
	
	public void setPlayerInventory(Object item){
		playerInventory.add(item);
	}
	
	public ArrayList getPlayerInventory(){
		return playerInventory;
	}
	
	//Other Methods
	
	
}// End Class Player
