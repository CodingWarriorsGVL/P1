package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import characters.AttackSpell;
import characters.HealingSpell;
import characters.MagicSpell;
import characters.Player;

import static characters.AttackSpell.fireBall;
import static characters.AttackSpell.testAttack;
import static characters.HealingSpell.lightHealing;
import static characters.HealingSpell.testHealing;
import static characters.Player.*;
import static item.Armor.ruggedArmor;
import static item.Armor.ruggedHelmet;
import static item.Armor.ruggedLeggings;
import static item.Armor.ruggedShield;
import static item.Armor.testArmor;
import static item.Armor.testHelmet;
import static item.Armor.testLeggings;
import static item.Armor.testShield;
import static item.Potion.healthPotion;
import static item.Weapon.ruggedSword;
import static item.Weapon.testSword;

import item.Armor;
import item.Potion;
import item.Weapon;

public class MainGame {
	
	//Dungeon dungeon;
	//Entity player;
	Scanner scan;
	
	int ENEMY_WAIT = 1;
	int QUIT_MOVE = 0;
	
	//This is my random comment somewhere
	public Player player1;
	
	public static void main(String[] args) {
		System.setOut(System.out);
		// Print Logo
		try {
			Scanner input;
			input = new Scanner(new File("src/data/DUNG ASCII Logo.txt"));
			while (input.hasNextLine()) {
				System.out.println(input.nextLine());
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error Loading Logo");
		}
		new MainGame();
	}
	
	public MainGame() {
		scan = new Scanner(System.in);
		player1 = buildCharacter(scan);
		
		//For Testing
		//player1 = new Player("", 50,50,50,50,50,50,50,2);
		
		
		//dungeon = new Dungeon();
		
		
		//String name = getName();
		//player = new Entity(name, map);
		
		
		
		//FOR TESTING PLAYER SPELL INVENTORY
		/*player1.setEquippedSpells(0, fireBall);
		player1.setEquippedSpells(1, lightHealing);
		player1.setPlayerSpells(fireBall);
		player1.setPlayerSpells(lightHealing);
		player1.setPlayerSpells(testAttack);
		player1.setPlayerSpells(testHealing);
		player1.changeEquippedSpells();*/
		
		/*player1.setEquippedSpells(0, fireBall);
		player1.setEquippedSpells(1, lightHealing);
		player1.displayEquippedSpells();*/
		
		
		//FOR TESTING PLAYER ITEM INVENTORY
		/*player1.setEquippedItems(ruggedHelmet.getEquippedItemSlot(), ruggedHelmet);
		player1.setEquippedItems(ruggedArmor.getEquippedItemSlot(), ruggedArmor);
		player1.setEquippedItems(ruggedLeggings.getEquippedItemSlot(), ruggedLeggings);
		player1.setEquippedItems(ruggedShield.getEquippedItemSlot(), ruggedShield);
		player1.setEquippedItems(ruggedSword.getEquippedItemSlot(), ruggedSword);
		player1.setPlayerBlocking(player1.getEquippedItems());
		player1.setPlayerDamage(player1.getEquippedItems());		
		player1.setPlayerInventory(healthPotion);
		healthPotion.setQuantity(3);
		player1.setPlayerInventory(testHelmet);
		player1.setPlayerInventory(testArmor);
		player1.setPlayerInventory(testLeggings);
		player1.setPlayerInventory(testShield);
		player1.setPlayerInventory(testSword);
		player1.changeEquippedItems();*/
		
		boolean play = true;
		
		
		//Entity enemy;
		int move;
		//boolean isEnemyAlive;
		
		while (play) {
			//map.displayRoom(player.getPosition());
			print("You are in a room");
			input("What do you want to do?");
			
			//enemy = getEnemy();
			
			//if (enemy == null) { //Move mode
				
				//getClass().int move = player.getMove();
				//player.Move(move);
				
			/*} else { //Fight Mode
				
				Thread.sleep(ENEMY_WAIT);
				
				isEnemyAlive = true;
				
				while(isEnemyAlive) {
					
					enemy.display();
					move =  player.getAttack();
					enemy.attack(move);
					
					move = enemy.getAttack();
					player.attack(move);
				}
			} */
		}
		
	}
	
	public void print(String str) {
		System.out.println("------------------------------\n" + str);
	}
	
	public String input(String str) {
		System.out.println("------------------------------\n" + str + ":");
		return scan.nextLine();
	}
	
	
}//End Class MainGame
