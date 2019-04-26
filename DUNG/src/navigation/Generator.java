//INCOMPLETE
package navigation;
import java.util.*;
import characters.Entity;
import instance.Instance;




public class Generator {
	private String dungeon_name;
	private int dungeon_height, dungeon_width, floor_amt, xfloorfeed, yfloorfeed;



	public static void dungeonGenerator(){
		//dungeon_name=("abcdeg");
		//floor_amt=(1);
		Dungeon Thisdungeon = new Dungeon(dungeon_name, floor_amt);
	}
	public static Floor floorGenerator(int floorxsize, int floorysize){
		Floor generatedFloor = new Floor(floorxsize, floorysize);
		Room placedRoom, previousRoom;
		RoomFeature North, South, East, West;
		for(int ycnt=0;ycnt<floorysize;ycnt++) {
			for(int xcnt=0;xcnt<floorxsize;xcnt++) {
				Switch(ycnt){
					case 0:
						north = new Wall();
						break;
					case floorysize:
						south = new Wall();
						break;
				}
				switch(xcnt){
					case 0:
						east = new Wall();
						break;
					case floorxsize:
						west = new Wall();
						break;
					}
				}
				}
				placedRoom = newRoom(north, east, south, west, xcnt, ycnt);
			}
	}

	/*public static Room roomGenerator(RoomFeature n, RoomFeature e, RoomFeature s, RoomFeature w, ArrayList<Entity> ent, int x, int y)
	{
		RoomFeature north = n;
		RoomFeature east = e;
		RoomFeature south = s;
		RoomFeature west = w;

		Room newRoom = new Room(north, east, south, west, ent, x, y);
		return newRoom;
	}*/

	  /*
	   * ------------------------------------------------------------------------------------------------------------
	   * method that checks 2 rooms for adjacent doors.
	   * ------------------------------------------------------------------------------------------------------------
	   */
	public static boolean doorChecker(Room roomA, Room roomB){
		boolean isadjacent,validity=false;
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
