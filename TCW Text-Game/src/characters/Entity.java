package characters;

import level.Floor;

public class Entity {
	
	//Class Variables
	String name;
	int health, attackRating, defense;

	public Entity(String name, int health, int attackRating, int defense) {
		this.name = name;
		this.health = health;
		this.attackRating = attackRating;
		this.defense = defense;
		
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackRating() {
		return attackRating;
	}

	public void setAttackRating(int attackRating) {
		this.attackRating = attackRating;
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
		
	//Other Methods	
	public void attack(int attack) {
		
	}
}// End Class Entity
