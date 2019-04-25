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
	public static void floorGenerator(int floorxsize, int floorysize){
		Floor generatedFloor = new Floor(floorxsize, floorysize);
		RoomFeature North, South, East, West;
		Random gen = new Random();
		int rando;
		for(int ycnt=0;ycnt<floorysize;ycnt++) {
			for(int xcnt=0;xcnt<floorxsize;xcnt++) {
				rando  = gen.nextInt(4)+1;
				switch (rando)
				{
					case 1:
						north = new Door;
						south = new Wall;
						east = new Wall;
						west = new Wall;
						break;
					case 2:
						north = new wall;
						south = new Door;
						east = new Wall;
						west = new Wall;
						break;
					case 3:
						north = new Wall;
						south = new Wall;
						east = new Door;
						west = new Wall;
						break;
					case 4:
						north = new Wall;
						south = new Wall;
						east = new Wall;
						west = new Door;
						break;
				}
				switch (xcnt)
				generatedFloor[xcnt][ycnt] = new Room(north, south, east, west, xcnt, ycnt)
			}
		}	
		
	}
	public static void roomGenerator(RoomFeature north, RoomFeature east, RoomFeature south, RoomFeature west, ArrayList<Entity> ent){
		
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
