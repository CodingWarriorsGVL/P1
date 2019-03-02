package display;

import java.util.Scanner;

import characters.Entity;
import navigation.Decor;
import navigation.Door;
import navigation.Room;
import navigation.Staircase;
import navigation.Wall;

public class Display {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void print(String str) {
		System.out.print(str);
	}
	
	public static void println(String str) {
		System.out.println(str);
	}
	
	public static String input(String str) {
		println(str);
		return scan.nextLine();
	}
	
	public static void print(Entity ent) {
		String name = ent.getName();
		char firstletter = name.toLowerCase().charAt(0);
		if (firstletter == 'a' || firstletter == 'e' || firstletter == 'i' || firstletter == 'o' || firstletter == 'u') {
			println("There is an " + name);
		} else {
			println("There is a " + name);
		}
	}
	
	public static void print(Room room) {
		println("You are in a room");
		print("The North side is ");
		println(room.getNorth());
		
		print("The East side is ");
		println(room.getEast());
		
		print("The South side is ");
		println(room.getSouth());
		
		print("The West side is ");
		print(room.getWest());
		
	}
	
	public static void print(Door door) {
		println("Is a " + ((door.isLocked())? "locked" : "" ) + door.getMaterial() + " door");
	}
	public static void print(Decor decor) {
		println("Is a " + decor.getDescription());
	}
	public static void print(Wall wall) {
		println("wall");
	}
	public static void print(Staircase stair) {
		println("Staircase to another floor!");
	}
}
