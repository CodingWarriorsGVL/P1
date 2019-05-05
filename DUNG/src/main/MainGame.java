package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import characters.MagicSpell;
import characters.Player;
import display.Display;
import display.WordProcessing;

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

import static display.Display.*;

import static navigation.Generator.*;

public class MainGame {

	//Dungeon dungeon;
	//Entity player;
	Scanner scan;

	int ENEMY_WAIT = 1;

	//This is my random comment somewhere
	public Player player1;
	public Dungeon dungeon;

	public static void main(String[] args) {
		Display.initialize();
		// Print Logo
		try {
			Scanner input;
			input = new Scanner(new File("src/data/DUNG ASCII Logo.txt"));
			while (input.hasNextLine()) {
				//println(WordProcessing.rainbowfy(input.nextLine()));
				println(input.nextLine());
			}
			input.close();

		} catch (FileNotFoundException e) {
			println("Error Loading Logo");
		}
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
		player1.setInventory(testHelmet);
		player1.setInventory(testArmor);
		player1.setInventory(testLeggings);
		player1.setInventory(testShield);
		player1.setInventory(testSword);
		player1.setInventory(axe);

		boolean play = true;


		//Entity enemy;
		//int move;
		Room currentRoom;
		//boolean isEnemyAlive;
		
		Display.println(WordProcessing.rainbowfy("======================= The Adventure Beigns ======================="));
		
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
			
			Instance combat = new Combat();
			combat.addEntity(player1, 0);
			
			for (Entity i: currentRoom.getEnties()) {
				if (i instanceof Enemy) {
					combat.addEntity(i, 1);	
				}
			}
			
			if (combat.checkActive()) {
				combat.launch();
				if (player1.isAlive() == false) {
					print(player1.getName() + " is dead. \nGame Over\n");
					play = false;
					break;
				}
			}
			
			if (!player1.isAlive()) { 
				play = false;
				break;
			}
			println(currentRoom);
			/*
			println("Your options are:");

			if (currentRoom.getNorth() instanceof Door) {
				println("You can go north");
			}
			if (currentRoom.getEast() instanceof Door) {
				println("You can go east");
			}
			if (currentRoom.getSouth() instanceof Door) {
				println("You can go south");
			}
			if (currentRoom.getWest() instanceof Door) {
				println("You can go west");
			}
			*/
			/*
			String inputOptions[] = new String[6];
			inputOptions[0] = "Quit";
			inputOptions[1] = "Character Menu";
			if (currentRoom.getNorth() instanceof Door) 
				inputOptions[2] = "North";
			if (currentRoom.getEast() instanceof Door) 
				inputOptions[3] = "East";
			if (currentRoom.getSouth() instanceof Door) 
				inputOptions[4] = "South";
			if (currentRoom.getWest() instanceof Door) 
				inputOptions[5] = "West";
			*/
			// TODO make it take the correction options for directions here, for some reason the override below is not working.
			String inputOptions[] = {"Quit", "Character Menu", "North", "East", "South", "West"}; 
			if (!(currentRoom.getNorth() instanceof Door)) 
				inputOptions[2] = "";
			if (!(currentRoom.getEast() instanceof Door)) 
				inputOptions[3] = "";
			if (!(currentRoom.getSouth() instanceof Door)) 
				inputOptions[4] = "";
			if (!(currentRoom.getWest() instanceof Door)) 
				inputOptions[5] = "";

			//String input = input("Where would you like to go?", inputOptions);
			String input = input("Where would you like to go?", "Quit", "Character Menu", "North", "East", "South", "West");

			if (input.toLowerCase().charAt(0)=='n') {
				player1.setYPosition(player1.getYPosition() - 1);
			}
			else if (input.toLowerCase().charAt(0)=='e') {
				player1.setXPosition(player1.getXPosition() + 1);
			}
			else if (input.toLowerCase().charAt(0)=='s') {
				player1.setYPosition(player1.getYPosition() + 1);
			}
			else if (input.toLowerCase().charAt(0)=='w') {
				player1.setXPosition(player1.getXPosition() - 1);
			}
			
			else if (input.toLowerCase().charAt(0)=='q') {
				play = false;
			}
			else if (input.toLowerCase().charAt(0)=='c') {
				player1.characterMenu();
			}

			//getClass().int move = player.getMove();
			//player.Move(move);

		}
		Display.println("Game Over");
	}


}//End Class MainGame
