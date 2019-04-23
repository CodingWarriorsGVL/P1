package characters;

public class DamageSpell extends MagicSpell {
	//Class Variables
	int damage;
	
	public static final DamageSpell fireBolt = new DamageSpell("Fire Bolt", 10, "cast Fire Bolt", "enemy", 1, false, 15);
	public static final DamageSpell testAttack = new DamageSpell("Test Attack Spell", 1, "For Testing", "enemy", 1, false, 500);
	
	public static final DamageSpell lightHealing = new DamageSpell("Light Healing", 10, "cast Light Healing", "self", 0, false, -15);
	public static final DamageSpell testHealing = new DamageSpell("Test Healing Spell", 1, "For Testing", "friendly", 1, false, -100);

	public DamageSpell(String name, int manaCost, String description, String targetType, int numTargets, boolean targetDead, int damage) {
		super(name, manaCost, description, targetType, numTargets, targetDead);
		this.damage = damage;
	}
	
	//Getters and Setters
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	//Other Methods
	@Override
	public void useMagic(MagicSpell spell){
		System.out.println(spell);
	}
	
	public String toString(){
		String output = "";
		output += "You " + description + " at "; 
		if (targetType.equals("self")) 
			output += "yourself";
		else if (targetType.equals("friendly"))
			output += "an ally";
		else if (targetType.equals("enemy"))
			output += "the enemy";
		else if (targetType.equals("all"))
			output += "everyone";
		output += " for " + damage;
		if (damage > 0) 
			output += " damage!";
		if (damage < 0)
			output += " healing!";
		if (damage == 0)
			output += " nothing!";
		return output;
	}
	

}//End Class AttackSpell