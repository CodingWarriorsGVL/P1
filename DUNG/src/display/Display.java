package display;

import java.util.Scanner;

import characters.Entity;

public class Display {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void print(String str) {
		System.out.println(str);
	}
	
	public static String input(String str) {
		print(str);
		return scan.nextLine();
	}
}
