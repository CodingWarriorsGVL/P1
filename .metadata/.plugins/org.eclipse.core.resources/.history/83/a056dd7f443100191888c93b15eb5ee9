package main;

import java.util.Scanner;

import characters.Entity;
import level.Floor;

public class MainGame {
	
	Floor map;
	Entity player;
	Scanner scan;
	
	int ENEMY_WAIT = 1;
	int QUIT_MOVE = 0;
	

	public static void main(String[] args) {
		new MainGame();
	}
	
	public MainGame() {
		map = new Floor();
		
		scan = new Scanner(System.in);
		String name = getName();
		player = new Entity(name, map);
		
		boolean play = true;
		
		Entity enemy;
		int move;
		boolean isEnemyAlive;
		
		while (play) {
			map.displayRoom(player.getPosition());
			
			enemy = getEnemy();
			
			if (enemy == null) { //Move mode
				
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
				}
			}
		}
		
	}
	
	
	private String getName() {
		System.out.println("What's you character's name?");
		return scan.nextLine();
	}
	
}
