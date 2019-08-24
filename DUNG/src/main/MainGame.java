package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import characters.MagicSpell;
import characters.Player;
import display.Display;
import static display.Display.*;
import static display.WordProcessing.*;

import static characters.Player.*;
import static item.Armor.*;
import static item.Potion.*;
import static item.Weapon.*;
import static characters.Enemy.*;

import item.Armor;
import item.Potion;
import item.Weapon;
import navigation.Door;
import navigation.Dungeon;
import navigation.Floor;
import navigation.Room;
import navigation.Wall;

import instance.*;
import characters.*;
import item.*;
import java.util.ArrayList;

import static navigation.Generator.*;

public class MainGame {

	Scanner scan;

	int ENEMY_WAIT = 1;

	public Player player1;
	public Dungeon dungeon;

	public static void main(String[] args) {
		Display.initialize(); // creates display window.
		// Print Logo
		int failures = 0; //number of failed attempts to load logo.
		try { // Attempt to load src version.
			Scanner input;
			input = new Scanner(new File("data/DUNG ASCII Logo.txt"));
			while (input.hasNextLine()) {
				//println(rainbowfy(input.nextLine()));
				println(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			failures++;
		}
		try { // Attempt to load .jar version
			Scanner input;
			input = new Scanner(new File("src/data/DUNG ASCII Logo.txt"));
			while (input.hasNextLine()) {
				//println(rainbowfy(input.nextLine()));
				println(input.nextLine());
			}
			input.close();
		} catch (FileNotFoundException e) {
			failures++;
		}
		
		if (failures == 2)
			println("Error Loading Logo");
		
		new MainGame();
	}

	public MainGame() {

		player1 = buildCharacter();

		dungeon = generateDungeon();//new Dungeon("Scary Dungeon", 1);   //Quick and dirty dungeon build, all wall and door objects are the same which would mess up locking/unlocking, in the doors case
		/*
		dungeon.setFloor(0, new Floor(2,2));
		Door door = new Door(false, "wood");
		Wall wall = new Wall();

		dungeon.getFloor(0).setRoom(new Room(wall, door, door, wall),  0, 0);
		dungeon.getFloor(0).setRoom(new Room(wall, wall, door, door),  1, 0);
		dungeon.getFloor(0).setRoom(new Room(door, door, wall, wall),  0, 1);
		dungeon.getFloor(0).setRoom(new Room(door, wall, wall, door),  1, 1);
		 */
		player1.setXPosition(5);
		player1.setYPosition(9);

		//dungeon.getFloor(0).getRoom(1, 1).addInstances(testInstance); // Place Instance Somewhere.
		dungeon.getFloor(0).getRoom(5, 8).addEntities(getGiantRoach(), getGiantMouse());
		dungeon.getFloor(0).getRoom(5, 1).addEntities(getSkeleton(), getSkeleton(), getSkeleton(), getSkeleton(), getSkeleton());

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
		player1.inventory.add(testHelmet);
		player1.inventory.add(testArmor);
		player1.inventory.add(testLeggings);
		player1.inventory.add(testShield);
		player1.inventory.add(testSword);
		player1.inventory.add(axe);
		
		Room currentRoom;

		println(wildRainbowfy(bar("The Adventure Beigns")));

		boolean play = true;
		
		while (play) {
			//print(player.getPosition());

			currentRoom = dungeon.getFloor(0).getRoom(player1.getXPosition(), player1.getYPosition());
			player1.setCurrentRoom(currentRoom);

			// Activate Instances.
			//This seems like best location for now, just after you have entered the room.
			//			for (Instance i: currentRoom.getInstances()) {
			//
			//				i.addEntity(player1, 0);
			//				if (i.checkActive()) {
			//					i.launch();
			//					if (player1.isAlive() == false) {
			//						print(player1.getName() + " is dead. \nGame Over\n");
			//						play = false;
			//						break;
			//					}
			//				}
			//			}

			// Generate a combat from all entities in the room.
			Instance combat = new Combat();
			combat.addEntity(player1, 0);
			for (Entity i: currentRoom.getEnties())
				if (i instanceof Enemy && i.isAlive())
					combat.addEntity(i, 1);	
			
			if (combat.checkActive()) // If a combat is valid
				combat.launch(); // Play the combat.
			
			if (!player1.isAlive()) { // If the player is Dead
				println(player1.getName() + " is dead."); // Tells you, you are dead.
				play = false; // Stops loop.
				break; // Gets out of loop.
			}
			
			println("");
			println(currentRoom);

			// Generate the Options List for the room you are in.
			String inputOptions[] = new String[9]; // Number here can be as high as you want, but needed to at least cover all the options following.
			inputOptions[0] = "Quit";
			if (currentRoom.getNorth() instanceof Door) 
				inputOptions[1] = "North";
			if (currentRoom.getEast() instanceof Door) 
				inputOptions[2] = "East";
			if (currentRoom.getSouth() instanceof Door) 
				inputOptions[3] = "South";
			if (currentRoom.getWest() instanceof Door) 
				inputOptions[4] = "West";
			inputOptions[5] = "Inventory";
			inputOptions[6] = "Spells";
			inputOptions[7] = "Attributes";
			inputOptions[8] = "Condition";
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {};
			String input = input("What would you like to do?", inputOptions);
			//String input = input("Where would you like to go?", "Quit", "North", "East", "South", "West");

			// What the inputs do.
			if (input.equals("North")) {
				player1.setYPosition(player1.getYPosition() - 1);
			}
			else if (input.equals("East")) {
				player1.setXPosition(player1.getXPosition() + 1);
			}
			else if (input.equals("South")) {
				player1.setYPosition(player1.getYPosition() + 1);
			}
			else if (input.equals("West")) {
				player1.setXPosition(player1.getXPosition() - 1);
			}
			
			else if (input.equals("Quit")) {
				play = false;
			}
			else if (input.equals("Inventory")) {
				player1.characterInventory();
			}
			else if (input.equals("Spells")) {
				player1.changeEquippedSpells();
			}
			else if (input.equals("Attributes")) {
				player1.spendPoints();
			}
			else if (input.equals("Condition")) {
				player1.displayStats();
				player1.displayEquippedItems();
				player1.displayEquippedSpells();
			}
			
		} // End Play Loop
		endGame();
	}
	
	public void endGame() { // Anything needed at the end to wrap up a finished game, either from quiting or losing.
		printbar("<font color = red size = 5>Game Over</font>");
	}


}//End Class MainGame
