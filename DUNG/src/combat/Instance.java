package combat;

import java.util.ArrayList;
import java.util.List;
import characters.Entity;
import display.Display;

public abstract class Instance {
	public boolean awaitingInput;
	protected List<List<Entity>> team = new ArrayList<List<Entity>>(); //array for teams that holds array for Entitys
	protected ArrayList<Entity> InitiativeList = new ArrayList<Entity>();
	protected RPGAction currentAction;
	protected int currentTeam;
	
	public ArrayList<Entity> getInitiativeList() {
		return InitiativeList;
	}
	public List<List<Entity>> getTeams() {
		return team;
	}
	public int getCurrentTeam() {
		return currentTeam;
	}
	public Entity getEntity(int i, int teamNum) { //unclear why anything would request this as of yet.
		return team.get(teamNum).get(i);
	}

	
	public void addEntity(Entity add, int teamNum) { // Add people to the combat.
		if (teamNum>(team.size()-1))
			for (int i=0; i<(teamNum-(team.size()-1)); i++)
				team.add(new ArrayList<Entity>());
		team.get(teamNum).add(add);
		Display.print(add.getName() + " was added to combat.\n"); // Change this line if we add more people.
		
		InitiativeList.add(add);
		//Collections.sort(InitiativeList, )
		//InitiativeList.sort(Comparator.comparing(Entity::getStatI("init")));
	}
	
}
