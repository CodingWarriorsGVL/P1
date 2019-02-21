package characters;

public class AttackSpell extends MagicSpell {
	
	//Class Variables
	int damage;

	public AttackSpell(String name, int manaCost, String description, int damage) {
		super(name, manaCost, description);
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
		return "You " + description + " at the enemy for " + damage + " damage!";
	}
	

}//End Class AttackSpell
