package main;

import java.util.Scanner;

import characters.Entity;
import characters.Player;
import item.Armor;
import item.Potion;
import item.Weapon;
import level.Floor;

public class MainGame {
	
	Floor map;
	Entity player;
	Scanner scan;
	
	int ENEMY_WAIT = 1;
	int QUIT_MOVE = 0;
	

	
	
	//Armor Objects
	
	//public Armor(String name, int dropChance, int sellValue, int buyValue, int defense, boolean isEquipped, int equippedItemSlot)
	Armor ruggedHelmet = new Armor("Rugged Helmet", 1, 1, 0, 1, true, 0);
	Armor ruggedArmor = new Armor("Rugged Armor", 1, 1, 0, 3, true, 1);
	Armor ruggedLeggings = new Armor("Rugged Leggings", 1, 1, 0, 2, true, 2);
	Armor ruggedShield = new Armor("Rugged Shield", 1, 1, 0, 1, true, 3);
	
	//These are for testing purposes
	Armor testHelmet = new Armor("Test Helmet", 1, 1, 1, 500, false, 0);
	Armor testArmor = new Armor("Test Armor", 1, 1, 0, 500, false, 1);
	Armor testLeggings = new Armor("Test Leggings", 1, 1, 0, 500, false, 2);
	Armor testShield = new Armor("Test Shield", 1, 1, 0, 500, false, 3);
	Weapon testSword = new Weapon("Test Sword", 1, 1, 0, 500, false, 4);
	
	
	
	//Weapon Objects
	
	//public Weapon(String name, int dropChance, int sellValue, int buyValue, int attack, boolean isEquipped, int equippedItemSlot)
	Weapon ruggedSword = new Weapon("Rugged Sword", 1, 1, 0, 3, true, 4);
	
	//Potion Objects
	
	//Potion(String name, int dropChance, int sellValue, int buyValue, int quantity, int healAmount, int equippedItemSlot)
	Potion healthPotion = new Potion("Health Potion", 5, 5, 10, 1, 100, 5);

	

	public static void main(String[] args) {
		new MainGame();
	}//End Main Method
	
	public MainGame() {
		map = new Floor();
		
		
		
		scan = new Scanner(System.in);
		//String name = getName();
		//player = new Entity(name, map);
		buildCharacter();
		boolean play = true;
		
		Entity enemy;
		int move;
		boolean isEnemyAlive;
		
		while (play) {
			map.displayRoom(player.getPosition());
			
			//enemy = getEnemy();
			
			/*if (enemy == null) { //Move mode
				
				int move = player.getMove();
				player.Move(move);
				
			} else { //Fight Mode
				
				Thread.sleep(ENEMY_WAIT);
				
				isEnemyAlive = true;
				
				while(isEnemyAlive) {
					
					enemy.display();
					move =  player.getAttack();
					enemy.attack(move);
					
					move = enemy.getAttack();
					player.attack(move);
				}*/
			}//End Loop While(play)
		}//End MainGame()
		
	//}
	
	
	/*private String getName() {
		System.out.println("What's you character's name?");
		return scan.nextLine();
	}*/
	
	private Player buildCharacter(){
		//Player(String name, int health, int mana, int attackRating, int defense, int intellect, int perception, int experience)
		Player player1 = new Player(" ", 0, 0, 0, 0, 0, 0, 0);
		
		boolean buildingCharacter = true;
		
		while(buildingCharacter){
			System.out.println("# Hello traveler, what is your name? #");
			player1.setName(scan.nextLine());
			String answer = " ";
			boolean checkingName = true;
			
			CHECKING_NAME:
			while(checkingName){
				System.out.println("# Ah so your name is " + player1.getName() + ". #" + "\n# Is that correct? #\n1.Yes\n2.No");
				answer = scan.nextLine();
				
				if(answer.equals("2")){
					System.out.println("# My apologies good friend, please tell me your name again. #");
					player1.setName(scan.nextLine());
				}
				
				else
					checkingName = false;
			}//End while(checkingName)
			
			
			
			boolean spendingPoints = true;
			SPEND_POINTS:
			while(spendingPoints){
				int spendablePoints = 240;
				int input = 0;
				answer = " ";
				
				System.out.println("# Ok " + player1.getName() + " you have " + spendablePoints + " points to spend in: Health, Mana, "
						+ "Attack Rating, Defense, Intellect, and Perception. #"
						+ "\n# Use them wisley! #");
				
				System.out.println("# How many points would you like to spend in health? #");
				input = scan.nextInt();
				player1.setHealth(input);
				spendablePoints -= input;
				System.out.println("# You now have " + spendablePoints + " points left. #");
				
				if(spendablePoints > 0){
					System.out.println("# How many points would you like to spend in mana? #");
					input = scan.nextInt();
					player1.setMana(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("# How many points would you like to spend in attack rating? #");
					input = scan.nextInt();
					player1.setAttackRating(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("# How many points would you like to spend in defense? #");
					input = scan.nextInt();
					player1.setDefense(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("# How many points would you like to spend in intellect? #");
					input = scan.nextInt();
					player1.setIntellect(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				if(spendablePoints > 0){
					System.out.println("# How many points would you like to spend in perception? #");
					input = scan.nextInt();
					scan.nextLine();
					player1.setPerception(input);
					spendablePoints -= input;
					System.out.println("# You now have " + spendablePoints + " points left. #");
				}
				
				System.out.println("# Ok " + player1.getName() + " here is your character build. #");
				System.out.println("Name: " + player1.getName());
				System.out.println("Health: " + player1.getHealth());
				System.out.println("Mana: " + player1.getMana());
				System.out.println("Attack Rating: " + player1.getAttackRating());
				System.out.println("Defense: " + player1.getDefense());
				System.out.println("Intellect: " + player1.getIntellect());
				System.out.println("Perception: " + player1.getPerception());
				
				System.out.println("# Are you happy with your character build? #\n1.Yes\n2.No");
				answer = scan.nextLine();
				
				if(answer.equals("2")){
					continue SPEND_POINTS;
				}
				
				else
					spendingPoints = false;
	
			}//End while(spendingPoints)
			//System.out.println("Current Attack Rating: " + player1.getPlayerDamage() + "\nCurrent Defense: " + player1.getPlayerBlocking());
			System.out.println("# Now let's get you some starter gear! #");
			
			player1.setEquippedItems(ruggedHelmet.getEquippedItemSlot(), ruggedHelmet);
			player1.setEquippedItems(ruggedArmor.getEquippedItemSlot(), ruggedArmor);
			player1.setEquippedItems(ruggedLeggings.getEquippedItemSlot(), ruggedLeggings);
			player1.setEquippedItems(ruggedShield.getEquippedItemSlot(), ruggedShield);
			player1.setEquippedItems(ruggedSword.getEquippedItemSlot(), ruggedSword);
			player1.setPlayerBlocking(player1.getEquippedItems());
			player1.setPlayerDamage(player1.getEquippedItems());
			
			player1.setPlayerInventory(healthPotion);
			healthPotion.setQuantity(3);
			
			//Testing Purposes Only
			player1.setPlayerInventory(testHelmet);
			player1.setPlayerInventory(testArmor);
			player1.setPlayerInventory(testLeggings);
			player1.setPlayerInventory(testShield);
			player1.setPlayerInventory(testSword);
			
			System.out.println("# Here are the items that you now have equipped, I also gave you 3 Health Potions, check your inventory"
					+ " to see them. #");
			player1.displayEquippedItems(player1.getEquippedItems());
			player1.displayPlayerInventory(player1, player1.getPlayerInventory());
			//System.out.println("Current Attack Rating: " + player1.getPlayerDamage() + "\nCurrent Defense: " + player1.getPlayerBlocking());		
		}//End while(buildingCharacter)
		return player1;
	}//End buildCharacter
}//End Class MainGame