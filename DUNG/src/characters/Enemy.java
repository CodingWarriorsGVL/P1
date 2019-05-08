package characters;

import item.Weapon;
import static display.WordProcessing.wildRandomColorfy;

public class Enemy extends Entity {
	
	public Enemy(String name, int health, int mana, int melee, int defense, int intellect, int perception, int level, boolean isAI) {
		super(name, health, mana, melee, defense, intellect, perception, level, isAI);
	}
	
	public static Enemy getGiantMouse() {
		Enemy giantMouse = new Enemy(wildRandomColorfy("Giant Mouse"), 30, 0, 10, 6, 1, 20, 1, true); // Make Enemy
		Weapon bite = new Weapon("bite", 0, 0, 0, 3, 0, false); // Make Weapon for Enemy
		giantMouse.setEquippedItems(bite); // Give Weapon to Enemy
		return giantMouse;
	}
	
	public static Enemy getGiantRoach() {
		Enemy giantRoach = new Enemy(wildRandomColorfy("Giant Roach"), 20, 0, 5, 6, 1, 20, 1, true); // Make Enemy
		Weapon bite = new Weapon("bite", 0, 0, 0, 3, 0, false); // Make Weapon for Enemy
		giantRoach.setEquippedItems(bite); // Give Weapon to Enemy
		return giantRoach;
	}
	
	public static Enemy getSkeleton() {
		Enemy skeleton = new Enemy(wildRandomColorfy("Skeleton"), 50, 10, 20, 4, 3, 40, 1, true);
		skeleton.setEquippedItems(Weapon.ruggedSword);
		return skeleton;
	}
	
	public static Enemy getGiantSpider() {
		Enemy spider = new Enemy(wildRandomColorfy("Giant Spider"), 40, 40, 30, 10, 5, 40, 1, true);
		spider.setEquippedItems(Weapon.ruggedSword);
		return spider;
	}
}
