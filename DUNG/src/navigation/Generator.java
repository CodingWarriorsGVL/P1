//INCOMPLETE


package navigation;

public class Generator {
	private String dungeon_name;
	private int dungeon_height, dungeon_width, floor_amt, xfloorfeed, yfloorfeed;
	private 
	
	
	public void dungeonGenerator(){
		dungeon_name=("abcdeg");
		floor_amt=(1);
		Dungeon Thisdungeon = new Dungeon(dungeon_name, floor_amt);
	}
	public void floorGenerator(){
		xfloorfeed = (1);
		yfloorfeed = (1);
		Floor Firstfloor = new Floor(xfloorfeed, yfloorfeed);
	}
	/*
	 * 0 - No Door
	 * 1 - Door North
	 * 2 - Door East
	 * 3 - Door South
	 * 4 - Door West
	 */
	public void roomGenerator(){
		for (int x = 0; x<xfloorfeed; x++)
			for (int y = 0; y<yfloorfeed; y++)
	}
}
