package main;

import java.util.Scanner;

public class MainGame {
	
	//Dungeon dungeon;
	//Entity player;
	Scanner scan;
	
	int ENEMY_WAIT = 1;
	int QUIT_MOVE = 0;
	

	public static void main(String[] args) {
		new MainGame();
	}
	
	public MainGame() {
		
		//dungeon = new Dungeon();
		
		scan = new Scanner(System.in);
		//String name = getName();
		//player = new Entity(name, map);
		
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
	
	private String getName() {
		System.out.println("What's you character's name?");
		return scan.nextLine();
	}
	
}
