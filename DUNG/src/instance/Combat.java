// Written originally by Jared Hawkins, and copied over from another project to here with some heavy edits. 

package instance;

import java.util.ArrayList;
import static display.Display.*;
import static display.WordProcessing.*;
import characters.Entity;
import item.*;
import characters.*;

public class Combat extends Instance {
	protected int victor; // Number of the team that has won this Combat
	protected ArrayList<Entity> dead = new ArrayList<Entity>(); //holds all the dead people, for counting and looting
	//protected ArrayList<Item> droppedLoot = new ArrayList<Item>(); //for clean up looting inventories.
	protected Inventory droppedLoot = new Inventory(); //for clean up looting inventories.
	private boolean allDead = false; //changes to true if everyone dies at the same time and is an extra check at the end to prevent an infinite loop.
	private Entity currentEntity; //the Entity currently taking a turn.
	private final int DAMAGE_REDUCTION_MULTIPLIER = 500;
	private final static Weapon noWeapon = new Weapon("melee", 0, 0, 0, 0, 0, false); // Static filler weapon for if an Entity attacks without one.

	public void launch() {
		currentTeam = 0;
		int turnCount = 0;
		int currentInitiative = -1; // Set to negative one due to how initiative loops around. Could be changed, but works nicely for now, and there was some reason I decided this was better.
		int lastInitiative = 0;
		RPGAction currentAction;

		println("<font color = red>" + bar("Entering Combat!") + "</font>");
		//setInstance(this); // Updates the current instance. Marked out from a previous project, but something that might need to stay.
		//print("Current location: " + /*environment +*/ ".\n");

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
			//print("Team of current combatant: " + currentTeam + "\n");

			if ((currentInitiative < lastInitiative) || (turnCount == 0))  { // Tick at the end of the turn of the first person currently in the initiative (but not the first time)
				turnCount++;
				printbar("Turn " + turnCount );
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
				//debug("Random Target: " + whoToAtk);
				ArrayList<Entity> tempTargets = new ArrayList<Entity>();
				tempTargets.add(potentialTargets.get(whoToAtk));
				currentAction = new RPGAction("Attack", tempTargets);
			}

			// Start Player Action Menu
			if (!currentEntity.isAI()) { // For players to input.
				//refreshGUI();
				do {
					ArrayList<Entity> targets = new ArrayList<Entity>();
					String tempActionType = input("Choose an action for " + currentEntity.getName() + ": ", "Attack", "Special", "Run", "Inventory");
					//println(tempActionType);
					if (tempActionType.equals("Attack")) {
						targets.add(potentialTargets.get(Player.pickTarget(potentialTargets)-1));
						currentAction = new RPGAction(tempActionType, targets);
					}

					else if (tempActionType.equals("Special")) {
						// List spells
						for (MagicSpell i: currentEntity.getEquippedSpells())
							println(i.getName() + ": " + i.getManaCost() + " mana.");
						// Choose spell
						int tempSpellNum;
						do {
							tempSpellNum = inputInt("You have " + currentEntity.getMana()+"/"+currentEntity.getMaxMana() + " mana. Choose a spell 1 - " + currentEntity.getEquippedSpells().length + ": ")-1;
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
								targets.add(spellPotentialTargets.get(Player.pickTarget(spellPotentialTargets)-1)); // Target selection and set Target.
							}
							else if (tempTargetType.equals("enemy")) {
								for (Entity i: team.get(currentTeam))
									spellPotentialTargets.remove(i);
								targets.add(spellPotentialTargets.get(Player.pickTarget(spellPotentialTargets)-1)); // Target selection and set Target.
							}
							else if (tempTargetType.equals("all")) {
								targets.addAll(spellPotentialTargets); // Just hits everyone.
							}
						} else println("Not enough mana to cast that spell.");
						currentAction = new RPGAction(tempActionType, targets, tempSpellNum); 
					}

					else if (tempActionType.equals("Run")) {
						currentAction = new RPGAction(tempActionType, null);
					}

					else if(tempActionType.equals("Inventory")) {
						String actionOnItem;
						Item choosenItem;
						int itemNum;

						currentEntity.displayInventory();
						itemNum = inputInt("Choose an item number: ");
						choosenItem = currentEntity.inventory().get(itemNum-1);
						String inputOptions[] = new String[3];
						print("What would you like to do?");
						if (choosenItem.isConsumable() || choosenItem.isEquipable())
							inputOptions[0] = "Use";
							//print("Use, ");
						//print("Drop, Give");
						inputOptions[1] = "Drop";
						inputOptions[2] = "Give";
						do {
							actionOnItem = input("", inputOptions);
						} while (!((actionOnItem.equals("Use") && (choosenItem.isConsumable() || choosenItem.isEquipable())) || actionOnItem.equals("Drop") || actionOnItem.equals("Give"))); // Watch the parentheses 

						if (false /*choosenItem.isTargetable()*/ || actionOnItem.equals("Give")) {
							targets.add(initiativeList.get(Player.pickTarget(initiativeList)-1));
						}
						else targets.add(currentEntity);

						currentAction = new RPGAction(tempActionType, targets, actionOnItem, itemNum-1);
					}

					else {
						println("Invalid input, please enter again...");
						currentAction = new RPGAction("", null);
					}
				} while(!currentAction.isValid());
			}

			debug(currentAction.toString()); // More debug to see what is happening in the background.
			// Redirect to perform currentAction
			if (currentAction.getActionType().equals("Attack"))
				print(fight(currentEntity, currentAction.getTargets().get(0)));
			else if (currentAction.getActionType().equals("Special"))
				print(useSpecial(currentEntity, currentAction));
			else if (currentAction.getActionType().equals("Run")) {
				if (run(currentEntity))
					currentInitiative--;
			}
			else if (currentAction.getActionType().equals("Inventory"))
				accessInventory(currentEntity, currentAction);

			checkDead();


			if (currentInitiative < lastInitiative) { // Tick at the end of the turn of the first person currently in the initiative (but not the first time)
				//gameTick(1); // Do we have a status effect system in the works? if not we can remove this.
				debug("Game ticks."); // If status effects are added, consider main print.
				checkDead();
			}
			lastInitiative = currentInitiative;

			if (turnCount == 50) { // Error catch so we can see what happened easier
				println("Too many turns have happened. Exiting loop prematurely.");
				break; 
			}
		} // end of Combat turn loop
		endCombat();
		println(bar("End of combat"));
	}

	public String fight(Entity attacker, Entity defender) {
		String output = "";
		int damage, damageFinal;
		Weapon weapon;

		if (defender.isAlive()) {
			if (attacker.getEquippedItems()[4] != null)
				weapon = (Weapon)attacker.getEquippedItems()[4];
			else weapon = noWeapon;
			damage = (int)(Math.random()*((weapon.getAttack()+attacker.getMelee())/2) + (weapon.getAttack()+attacker.getMelee())/2 +1); // Damage will be from half weapon attack + melee to full weapon attack + melee.
			if (damage < 0) damage = 0;
			double damageReduction = (defender.getBlocking()+DAMAGE_REDUCTION_MULTIPLIER)/DAMAGE_REDUCTION_MULTIPLIER; // Move blocking up to entity?
			damageFinal = (int) (damage/damageReduction);

			if (Math.random()*attacker.getMelee()+(attacker.getMelee()/2) > Math.random()*defender.getPerception()+(defender.getPerception()/2)) { // Hit Chance: DP vs AM/2 + 1-AM
				defender.damage(damageFinal);
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
					i.damage(-damage);
					damageFinal = damage;
					output += "healed";
				}
				else if (((DamageSpell)spell).getDamage() > 0) {
					damage = (int)(Math.random()*((((DamageSpell)spell).getDamage()+caster.getIntellect())/2) + (((DamageSpell)spell).getDamage()+caster.getIntellect())/2 +1); // Damage will be from half spell + intellect to full spell damage + intellect.
					double damageReduction = (i.getBlocking()+DAMAGE_REDUCTION_MULTIPLIER)/DAMAGE_REDUCTION_MULTIPLIER; // Move blocking up to entity?
					damageFinal = (int) (damage/damageReduction);
					i.damage(damageFinal);
					output += "damaged";
				}
				else { // Catch if the damage is 0 for some reason.
					damageFinal = ((DamageSpell)spell).getDamage();
					output += "poked";
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
		print(runner.getName() + " attempts to run from combat... ");
		for (int i=0; i<team.size(); i++)
			for (int j=0; j<team.get(i).size(); j++) //should add something here to skip over the entire currentTeam
				if (currentTeam != i)
					if (team.get(i).get(j).getPerception() > highestEnemyDex) // TODO replace perception with something that makes more sense?
						highestEnemyDex = team.get(i).get(j).getPerception();
		int runAttempt = (int)Math.round(Math.random()*runner.getPerception()*2); // TODO Adjust equation
		debug(runAttempt + "\n");
		if (runAttempt >= highestEnemyDex) {
			print(" Sucess!\n");
			initiativeList.remove(runner);
			team.get(currentTeam).remove(runner);
			return true;
		}
		else {
			print(" Failure.\n");
			return false;
		}
	}

	public void accessInventory(Entity currentEntity, RPGAction currentAction) {
		currentEntity.useItem(currentEntity.inventory().get(currentAction.getInventorySlot()), currentAction.getInventoryAction(), currentAction.getTargets());
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
		//debug("Check active return: isActive: " + isActive + ". deadTeams: " + deadTeams + ". allDead: " + allDead);
		return isActive; 
	}

	public void checkDead() { // Checks to add dead to the dead list and declare them dead.
		for (int i=0; i<team.size(); i++) {
			for (int j=0; j<team.get(i).size(); j++) {
				if (!team.get(i).get(j).isAlive() && !dead.contains(team.get(i).get(j))) {
					print(team.get(i).get(j).getName() + " has died.\n");
					dead.add(team.get(i).get(j));
				}
			}
		}
	}

	public void endCombat() { //combat clean up
		if (allDead == false) {
			
			if (victor == 0) 
				print("You are the victor.\n");
			else print("The enemy team is the victor.\n");
			
			
			print("There are " + dead.size() + " dead.\n");

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

			print(xpPreSplit + "xp is split between all " + xpSplitWays + " of the victors recieve who each recieve " + xpSplit + "xp.\n");

			// All dead loot goes in one pile that the victor can pick it up.
			for(Entity i: dead) // Tally up all inventories into one pile.
				droppedLoot.add(i.inventory());
			int moneyDroped = 0;
			for (Entity i: dead) // Tally up all the money.
				moneyDroped += i.getMoney();

			for(Entity i: team.get(victor)) {
				if (i.isAlive()) {
					i.inventory().add(droppedLoot);
					i.setMoney(i.getMoney() + moneyDroped); 
					print(i.getName() + " picks up all of the loot, and " + moneyDroped + " coins.\n");
					break;
				}
			}
			
			for (Entity i: dead)
				i.inventory().clear();
		}
		else {
			println("All teams are dead.\nOops!");
		}
		//refresh GUI
	}

}
