package item;

public class Item {
	
	//Class Variables
	String name;
	int dropChance, sellValue, buyValue;
	
	public Item(String name, int dropChance, int sellValue, int buyValue){
		this.name = name;
		this. dropChance = dropChance;
		this.sellValue = sellValue;
		this.buyValue = buyValue;
	}
	
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDropChance() {
		return dropChance;
	}

	public void setDropChance(int dropChance) {
		this.dropChance = dropChance;
	}

	public int getSellValue() {
		return sellValue;
	}

	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
	}
	
}//End Class Item
