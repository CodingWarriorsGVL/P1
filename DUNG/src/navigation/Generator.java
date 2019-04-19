//INCOMPLETE
import java.util.ArrayList;
import characters.Entity;
import instance.Instance;


package navigation;

public class Generator {
	private String dungeon_name;
	private int dungeon_height, dungeon_width, floor_amt, xfloorfeed, yfloorfeed;
	
	
	public static void dungeonGenerator(){
		dungeon_name=("abcdeg");
		floor_amt=(1);
		Dungeon Thisdungeon = new Dungeon(dungeon_name, floor_amt);
	}
	public static void floorGenerator(int floorxsize, int floorysize){
		xfloorfeed = (floorxsize);
		yfloorfeed = (floorysize);
		Floor Firstfloor = new Floor(xfloorfeed, yfloorfeed);
	}
	/* 0 - No Door
	 * 1 - Door North
	 * 2 - Door East
	 * 3 - Door South
	 * 4 - Door West*/
	public static void roomGenerator(RoomFeature north, RoomFeature east, RoomFeature south, RoomFeature west, ArrayList<Entity> ent){
		Room thisroom = new Room(north, east, south, west);
		for (Entity i:ent){
		thisroom.addEntities(i);}

		for (int x = 0; x<xfloorfeed; x++)
			for (int y = 0; y<yfloorfeed; y++)
	}
	  /*
	   * ------------------------------------------------------------------------------------------------------------
	   * method that checks 2 rooms for adjacent doors.
	   * ------------------------------------------------------------------------------------------------------------
	   */
	public static boolean doorChecker(Room roomA, Room roomB){
		boolean isadjacent,validity;
		int adjcheckA[] = roomA.getCoords();
		int adjcheckB[] = roomB.getCoords();
		int aX = adjcheckA[0], aY = adjcheckA[1];
		int bX = adjcheckB[0], bY = adjcheckB[1];
		
		if((aX==bX++||aX==bX--)&&(aY==bY++||aY==bY--))
			isadjacent = true;
		else
			isadjacent = false;
		if (isadjacent = true)
		{
			if (aX==bX++){
				if (roomA.getRoomFeature(3) instanceof Door && roomB.getRoomFeature(1) instanceof Door)
					validity = true;
				else
					validity = false;}
			else if (aX==bX--){
				if (roomA.getRoomFeature(1) instanceof Door && roomB.getRoomFeature(3) instanceof Door)
					validity = true;
				else
					validity = false;}
			else if (aY==bY++){
				if (roomA.getRoomFeature(2) instanceof Door && roomB.getRoomFeature(0) instanceof Door)
					validity = true;
				else
					validity = false;}
			else if (aY==bY--){
				if (roomA.getRoomFeature(0) instanceof Door && roomB.getRoomFeature(2) instanceof Door)
					validity = true;
				else
					validity = false;}
		}
		else
			validity = false;
		return validity;
	}
}
