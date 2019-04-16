package instance;

import java.util.ArrayList;
import characters.Entity;

public class RPGAction { //Wrapper to hold player and AI actions from their figures to make them perform said task. Not designed for movement or shopping.
	String action; //attack, special, run, inventory
	Integer inventorySlot; //inventory slot #, base 0. If inventory action.
	ArrayList<Entity> targets; //array of all the targets in group or initiative (depending on when accessed) currently being targeted.
	String inventoryAction; //drop, use, give
	int specialNum; //which special was selected to use.
	
	public RPGAction(String action, ArrayList<Entity> targets) {
		this.action = action;
		this.targets = targets;
	}
	public RPGAction(String action, ArrayList<Entity> targets, String inventoryAction, int inventorySlot) {
		this.action = action;
		this.targets = targets;
		this.inventoryAction = inventoryAction;
		this.inventorySlot = inventorySlot;
	}
	public RPGAction(String action, ArrayList<Entity> targets, int specialNum) {
		this.action = action;
		this.targets = targets;
		this.specialNum = specialNum;
	}
	
	// Retrieval Methods
	public String getActionType() {
		return action;
	}
	public ArrayList<Entity> getTargets() {
		return targets;
	}
	public int getSpecial() {
		return specialNum;
	}
	public int getInventorySlot() {
		return inventorySlot;
	}
	public String getInventoryAction() {
		return inventoryAction;
	}
	
	public boolean isValid() {
		if ((action.equals("attack")) && (targets.size() != 0))
			return true;
		else if ( (action.equals("special")) && (targets.size() != 0) && (specialNum >= 0) )
			return true;
		else if ( (action.equals("inventory")) && (targets.size() != 0) && (inventoryAction.equals("use") || inventoryAction.equals("drop") || inventoryAction.equals("give")) && (inventorySlot != null))
			return true;
		else if ((action.equals("run")))
			return true;
		else return false;
	}
}
