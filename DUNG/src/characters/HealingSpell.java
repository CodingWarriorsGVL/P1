package characters;

public class HealingSpell extends MagicSpell {

	//Class Variables
	int healAmount;
	
	public HealingSpell(String name, int manaCost, String description, int healAmount) {
		super(name, manaCost, description);
		this.healAmount = healAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}
	
	//Other Methods
	@Override
	public void useMagic(MagicSpell spell){
		System.out.println(spell);
	}
	
	public String toString(){
		return "You " + description + " yourself for " + healAmount + " health!";
	}
	

}//End Class HealingSpell
