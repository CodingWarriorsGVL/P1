// Written originally by Jared Hawkins, and copied over from another project to here with some heavy edits. 

package instance;

import java.util.ArrayList;
import display.Display;
import characters.Entity;
import item.*;
import characters.*;

public class Combat extends Instance {
	protected int victor; // Number of the team that has won this Combat
	protected ArrayList<Entity> dead = new ArrayList<Entity>(); //holds all the dead people, for counting and looting
	protected ArrayList<Item> droppedLoot = new ArrayList<Item>(); //for clean up looting inventories.
	private boolean allDead = false; //changes to true if everyone dies at the same time and is an extra check at the end to prevent an infinite loop.
	private Entity currentEntity; //the Entity currently taking a turn.
	private final int DAMAGE_REDUCTION_MULTIPLIER = 500;

	public void launch() {
		currentTeam = 0;
		int turnCount = 0;
		int currentInitiative = -1; // Set to negative one due to how initiative loops around. Could be changed, but works nicely for now, and there was some reason I decided this was better.
		int lastInitiative = 0;
		RPGAction currentAction;

		Display.print("Entering Combat!\n");
		//Display.setInstance(this); // Updates the current instance. Marked out from a previous project, but something that might need to stay.
		//Display.print("Current location: " + /*environment +*/ ".\n");

		while (checkActive()) { 

			do { // Cycle through InitiativeList
				currentInitiative++;
				if (currentInitiative >= initiativeList.size())
					currentInitiative = 0;
			} while (!initiativeList.get(currentInitiative).isAlive());
			currentEntity = initiativeList.get(currentInitiative);

			for (int i=0; i<team.size(); i++) // Find the team of the current Entity.
				if (team.get(i).contains(currentEntity)) 
					currentTeam = i;
			//Display.print("Team of current combatant: " + currentTeam + "\n");

			if ((currentInitiative < lastInitiative) || (turnCount == 0))  { // Tick at the end of the turn of the first person currently in the initiative (but not the first time)
				turnCount++;
				Display.print("\nTurn " + turnCount + ".\n");
			}

			// Action Decision Time
			currentAction = null; // In theory we should be overriding this no matter what in a moment, and clearing it like this is pointless. but....

			//Find available targets
			ArrayList<Entity> potentialTargets = new ArrayList<Entity>(initiativeList); // Does this load properly?
			for (Entity i: dead) //makes sure target is alive.
				potentialTargets.remove(i);
			for (Entity i: team.get(currentTeam)) //makes sure target isn't on same team.
				potentialTargets.remove(i);

			if (currentEntity.isAI()) {
				// A very rudimentary and temporary AI. Random function to attack someone in the potentialTargets list.
				int whoToAtk =  (int)Math.round((float)(Math.random()*(potentialTargets.size()-1))); //choose a random person to attack
				//Display.debug("Random Target: " + whoToAtk);
				ArrayList<Entity> tempTargets = new ArrayList<Entity>();
				tempTargets.add(potentialTargets.get(whoToAtk));
				currentAction = new RPGAction("attack", tempTargets);
			}

			// Start Player Action Menu
			if (!currentEntity.isAI()) { // For players to input.
				Display.println("Choose an action for " + currentEntity.getName() + ": ");
				//refreshGUI();
				do {
					ArrayList<Entity> targets = new ArrayList<Entity>();
					String tempActionType = Display.input("Input action (attack, special, run, inventory): ");
					//Display.println(tempActionType);
					if (tempActionType.equals("attack")) {
						targets.add(potentialTargets.get(pickTarget(potentialTargets)-1));
						currentAction = new RPGAction(tempActionType, targets);
					}

					else if (tempActionType.equals("special")) {
						// List spells
						for (MagicSpell i: currentEntity.getEquippedSpells())
							Display.println(i.getName() + ": " + i.getManaCost() + " mana.");
						// Choose spell
						int tempSpellNum;
						do {
							tempSpellNum = Display.inputInt("You have " + currentEntity.getMana()+"/"+currentEntity.getMaxMana() + " mana. Choose a spell 1 - " + currentEntity.getEquippedSpells().length + ": ")-1;
						} while (currentEntity.getEquippedSpells()[tempSpellNum] == null);

						if (currentEntity.getMana() >= currentEntity.getEquippedSpells()[tempSpellNum].getManaCost()) {
							// get targets from spell
							String tempTargetType = currentEntity.getEquippedSpells()[tempSpellNum].getTargetType();
							// pick x targets
							ArrayList<Entity> spellPotentialTargets = new ArrayList<Entity>(initiativeList);
							if (!currentEntity.getEquippedSpells()[tempSpellNum].getTargetDead()) // Changes target list depending on allowed to target dead.
								for (Entity i: dead)
									spellPotentialTargets.remove(i);

							if (tempTargetType.equals("self")) {
								targets.add(currentEntity);
							}
							else if (tempTargetType.equals("friendly")) {
								spellPotentialTargets = team.get(currentTeam); // Resets to only your team, as it's easier than removing all enemies from the combat (typically).
								if (!currentEntity.getEquippedSpells()[tempSpellNum].getTargetDead()) // Remove the dead again, because list was reset.
									for (Entity i: dead)
										spellPotentialTargets.remove(i);
								targets.add(spellPotentialTargets.get(pickTarget(spellPotentialTargets)-1)); // Target selection and set Target.
							}
							else if (tempTargetType.equals("enemy")) {
								for (Entity i: team.get(currentTeam))
									spellPotentialTargets.remove(i);
								targets.add(spellPotentialTargets.get(pickTarget(spellPotentialTargets)-1)); // Target selection and set Target.
							}
							else if (tempTargetType.equals("all")) {
								targets.addAll(spellPotentialTargets); // Just hits everyone.
							}
						} else Display.println("Not enough mana to cast that spell.");
						currentAction = new RPGAction(tempActionType, targets, tempSpellNum); 
					}

					else if (tempActionType.equals("run")) {
						currentAction = new RPGAction(tempActionType, null);
					}

					else if(tempActionType.equals("inventory")) {
						String actionOnItem;
						Item choosenItem;
						int itemNum;

						currentEntity.displayInventory(currentEntity.getInventory());
						itemNum = Display.inputInt("Choose an item number: ");
						choosenItem = currentEntity.getInventory().get(itemNum-1);

						Display.print("What would you like to do? Options: ");
						if (choosenItem.isConsumable() || choosenItem.isEquipable())
							Display.print("Use, ");
						Display.print("Drop, Give");
						do {
							actionOnItem = Display.input("").toLowerCase();
						} while (!((actionOnItem.equals("use") && (choosenItem.isConsumable() || choosenItem.isEquipable())) || actionOnItem.equals("drop") || actionOnItem.equals("give"))); // Watch the parentheses 

						if (false /*choosenItem.isTargetable()*/ || actionOnItem.equals("give")) {
							targets.add(initiativeList.get(pickTarget(initiativeList)-1));
						}
						else targets.add(currentEntity);

						currentAction = new RPGAction(tempActionType, targets, actionOnItem, itemNum-1);
					}

					else {
						Display.println("Invalid input, please enter again...");
						currentAction = new RPGAction("", null);
					}
				} while(!currentAction.isValid());
			}

			Display.debug(currentAction.toString()); // More debug to see what is happening in the background.
			// Redirect to perform currentAction
			if (currentAction.getActionType().equals("attack"))
				Display.print(fight(currentEntity, currentAction.getTargets().get(0)));
			else if (currentAction.getActionType().equals("special"))
				Display.print(useSpecial(currentEntity, currentAction));
			else if (currentAction.getActionType().equals("run")) {
				if (run(currentEntity))
					currentInitiative--;
			}
			else if (currentAction.getActionType().equals("inventory"))
				accessInventory(currentEntity, currentAction);

			checkDead();



			if (currentInitiative < lastInitiative) { // Tick at the end of the turn of the first person currently in the initiative (but not the first time)
				//gameTick(1); // Do we have a status effect system in the works? if not we can remove this.
				Display.print("Game ticks.\n");
				checkDead();
			}
			lastInitiative = currentInitiative;

			if (turnCount == 20) { //error catch so we can see what happened easier
				Display.print("Too many turns have happened. Exiting loop prematurely.\n");
				break; 
			}
			//Display.print(output);
		}
		endCombat();
		Display.print("End of combat.\n\n");
	}

	public String fight(Entity attacker, Entity defender) {
		String output = "";
		int damage, damageFinal;
		Weapon weapon;

		if (defender.isAlive()) {
			//damage = (int)(Math.random()*(attacker.getStatI(attacker.getEquippedItems()[4].getStatS("primaryStat"))/2)+(attacker.getStatI(attacker.getEquippedItems()[4].getStatS("primaryStat"))/2)) + attacker.getEquippedItems()[4].getAttack();
			if (attacker.getEquippedItems()[4] != null)
				weapon = (Weapon)attacker.getEquippedItems()[4];
			else weapon = new Weapon("unarmed", 0, 0, 0, 0, 4, 0, false); // May seem excessive to make a new one each time, and we could just have one per a combat.
			damage = (int)(Math.random()*((weapon.getAttack()+attacker.getMelee())/2) + (weapon.getAttack()+attacker.getMelee())/2 +1); // Damage will be from half weapon attack + melee to full weapon attack + melee.
			if (damage < 0) damage = 0;
			double damageReduction = (defender.getBlocking()+DAMAGE_REDUCTION_MULTIPLIER)/DAMAGE_REDUCTION_MULTIPLIER; // Move blocking up to entity?
			damageFinal = (int) (damage/damageReduction);

			if (Math.random()*attacker.getMelee()+(attacker.getMelee()/2) > Math.random()*defender.getPerception()+(defender.getPerception()/2)) { // DP vs AM/2 + 1-AM
				defender.setHealth(defender.getHealth() - damageFinal);
				output += attacker.getName() + " strikes at " + defender.getName() + " with " + attacker.getEquippedItems()[4].getName() + " dealing " + damageFinal + " damage.\n"; 
				output += defender.getName() + " now has " + defender.getHealth() +"/"+ defender.getMaxHealth() + " health points.\n";
			}
			else {
				output += attacker.getName() + " strikes at " + defender.getName() + " with " + attacker.getEquippedItems()[4].getName() + " and misses.\n";
			}
		}
		else output += defender.getName() + " is already dead.\n"; // like an error catch

		return output;
	}
	
	public String useSpecial(Entity caster, RPGAction action) {
		String output = "";
		MagicSpell spell = caster.getEquippedSpells()[action.getSpecial()];
		caster.setMana(caster.getMana() - spell.getManaCost()); // Remove mana.
		output += caster.getName() + " casts " + spell.getName() + " for " + spell.getManaCost() + " mana.\n";
		
		if (spell instanceof DamageSpell) { // Damage and Healing spells.
			int damage, damageFinal;
			for (Entity i: action.getTargets()) {
				output += i.getName() + " was ";
				if (((DamageSpell)spell).getDamage() < 0) {// Checks for healing, and if healing would put over max HP then...
					damage = (int)(Math.random()*((Math.abs(((DamageSpell)spell).getDamage())+caster.getIntellect())/2) + (Math.abs(((DamageSpell)spell).getDamage())+caster.getIntellect())/2 +1); // Damage will be from half spell + intellect to full spell damage + intellect.
					if (damage + i.getHealth() > i.getMaxHealth()) 
						i.setHealth(i.getMaxHealth()); // ... set health to max.
					else i.setHealth(i.getHealth() + damage); // Heals.
					damageFinal = damage;
					output += "healed";
				}
				else if (((DamageSpell)spell).getDamage() > 0) {
					damage = (int)(Math.random()*((((DamageSpell)spell).getDamage()+caster.getIntellect())/2) + (((DamageSpell)spell).getDamage()+caster.getIntellect())/2 +1); // Damage will be from half spell + intellect to full spell damage + intellect.
					double damageReduction = (i.getBlocking()+DAMAGE_REDUCTION_MULTIPLIER)/DAMAGE_REDUCTION_MULTIPLIER; // Move blocking up to entity?
					damageFinal = (int) (damage/damageReduction);
					i.setHealth(i.getHealth() - damageFinal); // Deals damage.
					output += "damaged";
				}
				else { // Catch if the damage is 0 for some reason.
					damageFinal = ((DamageSpell)spell).getDamage();
					output += "hit";
				}
				output += " for " + damageFinal + " and now has " + i.getHealth() +"/"+ i.getMaxHealth() + " health points.\n";
			}
		}
		
		// Summon spells.
		
		
		return output;
	}

	public boolean run(Entity runner) {
		//runner compare perception stats against those of people of other team, roll number, if beats them:
		int highestEnemyDex = 0;
		Display.print(runner.getName() + " attempts to run from combat... ");
		for (int i=0; i<team.size(); i++)
			for (int j=0; j<team.get(i).size(); j++) //should add something here to skip over the entire currentTeam
				if (currentTeam != i)
					if (team.get(i).get(j).getPerception() > highestEnemyDex) // TODO replace perception with something that makes more sense?
						highestEnemyDex = team.get(i).get(j).getPerception();
		int runAttempt = (int)Math.round(Math.random()*runner.getPerception()*2); // TODO Adjust equation
		Display.debug(runAttempt + "\n");
		if (runAttempt >= highestEnemyDex) {
			Display.print(" Sucess!\n");
			initiativeList.remove(runner);
			team.get(currentTeam).remove(runner);
			return true;
		}
		else {
			Display.print(" Failure.\n");
			return false;
		}
	}

	public void accessInventory(Entity currentEntity, RPGAction currentAction) {
		Item item = currentEntity.getInventory().get(currentAction.getInventorySlot());

		if (currentAction.getInventoryAction().equals("drop")) {
			droppedLoot.add(item); // Drops it on the ground for someone to pick up at the end of combat (not recommended).
			currentEntity.getInventory().remove(item);
			Display.println(currentEntity.getName() + " drops " + item.getName() + " on the ground.");
		}
		else if (currentAction.getInventoryAction().equals("use")) {
			if (item.isEquipable()) {
				currentEntity.setEquippedItems(item.getEquippedItemSlot(), item); 
				// TODO rearrange equipping so it's not just a menu, but can be used here too? An auto check on entity that equips an item to it's proper place based on what it is.
			}
			else if (item.isConsumable()) {
				if (currentEntity.consumeItem(item, currentEntity)) 
					Display.println(currentEntity.getName() + " consumes " + item.getName() + ".");
				else Display.debug("Item was not consumable!!!");
			}
			// else if (item.isTargetable()) { 
		}
		else if (currentAction.getInventoryAction().equals("give")) {
			currentAction.getTargets().get(0).getInventory().add(item);
			currentEntity.getInventory().remove(item);
			Display.println(currentEntity.getName() + " gives " + item.getName() + " to " + currentAction.getTargets().get(0).getName() + ".");
		}
		else Display.debug("ERROR: improper inventory action given!"); // We need to establish something bigger for error catching probably.
	}

	public int pickTarget(ArrayList<Entity> targetList) {
		Display.println("Targets:");
		for (Entity i: targetList) {
			Display.print(i);
		}

		int choice;
		if (targetList.size() > 1) {
			do {
				choice = Display.inputInt("Choose a target: (1 - " + targetList.size() + ")");
			} while (!(choice > 0 && choice <= targetList.size()));
		} else {
			choice = 1;
		}
		return choice;
	}

	public boolean checkActive() { // TODO redo something here, I think there might be issue.
		int deadCount = 0, deadTeams = 0;
		boolean isActive = true; //is this combat still active?

		for (int i=0; i<team.size(); i++) {
			deadCount = 0;
			for (int j=0; j<team.get(i).size(); j++) {
				if (team.get(i).get(j).isAlive()) {
					victor = i;
					break;
				}
				else deadCount++;
				if (deadCount == team.get(i).size()) {
					deadTeams++;
				}
			}
			if (team.get(i).size() == 0)
				deadTeams++;
		}
		if (deadTeams >= team.size()-1 || team.size() == 1)
			isActive = false;
		if (deadTeams >= team.size())
			allDead = true;
		//Display.debug("Check active return: isActive: " + isActive + ". deadTeams: " + deadTeams + ". allDead: " + allDead);
		return isActive; 
	}

	public void checkDead() { // Checks to add dead to the dead list and declare them dead.
		for (int i=0; i<team.size(); i++) {
			for (int j=0; j<team.get(i).size(); j++) {
				if (!team.get(i).get(j).isAlive() && !dead.contains(team.get(i).get(j))) {
					Display.print(team.get(i).get(j).getName() + " has died.\n");
					dead.add(team.get(i).get(j));
				}
			}
		}
	}

	public void endCombat() { //combat clean up
		if (allDead == false) {
			Display.print("Team " + victor + " is the victor.\n");
			Display.print("There are " + dead.size() + " dead.\n");

			int xpPreSplit = 0;
			for (Entity i: dead) // total up the xp value of the kills.
				if (!team.get(victor).contains(i))
					xpPreSplit += i.getLevel(); 

			int xpSplitWays = 0;
			for (Entity i: team.get(victor))
				if (i.isAlive()) 
					xpSplitWays++;

			int xpSplit = xpPreSplit/xpSplitWays;
			for (Entity i: team.get(victor))
				if (i.isAlive())
					i.addXP(xpSplit);

			Display.print(xpPreSplit + "xp is split between all " + xpSplitWays + " of the victors recieve who each recieve " + xpSplit + "xp.\n");
			//Display.print(dead.toString() + "\n");
			//Display.print("\n");

			// All dead loot goes in one pile that the victor can pick it up.
			for(Entity i: dead) 
				for(Item j: i.getInventory())
					droppedLoot.add(j);
			int moneyDroped = 0;
			for (Entity i: dead)
				moneyDroped += i.getMoney();

			for(Entity i: team.get(victor)) {
				if (i.isAlive()) {
					for(Item j: droppedLoot)
						i.getInventory().add(j);
					i.setMoney(i.getMoney() + moneyDroped); 
					Display.print(i.getName() + " picks up all of the loot, and " + moneyDroped + " coins.\n");
					break;
				}
			}
		}
		else {
			Display.print("All teams are dead.\nOops!\n");
		}
		//refresh GUI
	}

}
