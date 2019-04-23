package characters;

public class MagicSpell {

	//Class Variables
	String name, description, targetType; // Target types: "self", "enemy", "friendly", "all".
	int manaCost, numTargets;
	boolean targetDead; // Allows the spell to target the dead in a combat or not.
	
	public MagicSpell(String name, int manaCost, String description, String targetType, int numTargets, boolean targetDead){
		this.name = name;
		this.manaCost = manaCost;
		this.description = description;
		this.targetType = targetType;
		this.numTargets = numTargets;
		this.targetDead = targetDead;
	}
	
	//Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	
	public String getTargetType() {
		return targetType;
	}
	
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	public int getNumTargets() {
		return numTargets;
	}
	
	public void setNumTargets(int numTargets) {
		this.numTargets = numTargets;
	}
	
	public boolean getTargetDead() {
		return targetDead;
	}
	
	public void setTargetDead(boolean targetDead) {
		this.targetDead = targetDead;
	}
	
	//Other Methods
	
	public void useMagic(MagicSpell spell){
		
		System.out.println(spell);
	}//End useMagic
	
	
}//End Class MagicSpell
