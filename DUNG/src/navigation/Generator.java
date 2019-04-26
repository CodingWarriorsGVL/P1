//INCOMPLETE
package navigation;
import java.util.*;
import characters.Entity;
import instance.Instance;
import navigation.Room;
import static navigation.Room.*;
import static characters.Enemy.*;





public class Generator {
	private static String dungeon_name;
	private int dungeon_height, dungeon_width;
	private static int floor_amt;
	private int xfloorfeed;
	private int yfloorfeed;
	
	
	
	public static Dungeon generateDungeon() {
		Dungeon dungeon = new Dungeon("Dungeon of Death", 1);
		Floor floor = new Floor(10,10);
		dungeon.setFloor(0, floor);
		Random enmy = new Random();
		int chance;
		
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				Room room = new Room();
				room.setRoomFeature(NORTH, new Wall());
				room.setRoomFeature(SOUTH, new Wall());
				room.setRoomFeature(EAST, new Wall());
				room.setRoomFeature(WEST, new Wall());

				floor.setRoom(room, x, y);
			}
		}
		
		for (int y=8; y>1; y--) {
			floor.getRoom(5, y).setRoomFeature(NORTH, new Door(false));
			floor.getRoom(5, y).setRoomFeature(SOUTH, new Door(false));
			chance = (int) Math.pow((enmy.nextInt(10)+1),2);
			if(chance<70)
			{
			floor.getRoom(5, y).addEntities(getGiantRoach());
			}
			else if(chance<50)
			{
			floor.getRoom(5, y).addEntities(getGiantMouse());
			}
			else if(chance<30)
			{
			floor.getRoom(5, y).addEntities(getSkeleton());
			}
			else if(chance<10)
			{
			floor.getRoom(5, y).addEntities(getGiantSpider());
			}
		}
		
		for (int x=1; x<9; x++) {
			floor.getRoom(x, 5).setRoomFeature(EAST, new Door(false));
			floor.getRoom(x, 5).setRoomFeature(WEST, new Door(false));
			chance = (int) Math.pow((enmy.nextInt(10)+1),2);
			if(chance<70)
			{
			floor.getRoom(x, 5).addEntities(getGiantRoach());
			}
			else if(chance<50)
			{
			floor.getRoom(x, 5).addEntities(getGiantMouse());
			}
			else if(chance<30)
			{
			floor.getRoom(x, 5).addEntities(getSkeleton());
			}
			else if(chance<10)
			{
			floor.getRoom(x, 5).addEntities(getGiantSpider());
			}
		}
		for (int x=1; x<9; x++) {
			floor.getRoom(x, 0).setRoomFeature(WEST, new Door(false));
			floor.getRoom(x, 0).setRoomFeature(EAST, new Door(false));
			chance = (int) Math.pow((enmy.nextInt(10)+1),2);
			if(chance<70)
			{
			floor.getRoom(x, 0).addEntities(getGiantRoach());
			}
			else if(chance<50)
			{
			floor.getRoom(x, 0).addEntities(getGiantMouse());
			}
			else if(chance<30)
			{
			floor.getRoom(x, 0).addEntities(getSkeleton());
			}
			else if(chance<10)
			{
			floor.getRoom(x, 0).addEntities(getGiantSpider());
			}
		}
		for (int x=1; x<9; x++) {
			floor.getRoom(x, 9).setRoomFeature(WEST, new Door(false));
			floor.getRoom(x, 9).setRoomFeature(EAST, new Door(false));
			chance = (int) Math.pow((enmy.nextInt(10)+1),2);
			if(chance<70)
			{
			floor.getRoom(x, 9).addEntities(getGiantRoach());
			}
			else if(chance<50)
			{
			floor.getRoom(x, 9).addEntities(getGiantMouse());
			}
			else if(chance<30)
			{
			floor.getRoom(x, 9).addEntities(getSkeleton());
			}
			else if(chance<10)
			{
			floor.getRoom(x, 9).addEntities(getGiantSpider());
			}
		}
		
		floor.getRoom(5, 9).setRoomFeature(NORTH, new Door(false));
		floor.getRoom(5, 0).setRoomFeature(SOUTH, new Door(false));
		floor.getRoom(0, 5).setRoomFeature(EAST, new Door(false));
		floor.getRoom(9, 5).setRoomFeature(WEST, new Door(false));
		floor.getRoom(0, 0).setRoomFeature(EAST, new Door(false));
		floor.getRoom(9, 0).setRoomFeature(WEST, new Door(false));
		floor.getRoom(0, 9).setRoomFeature(EAST, new Door(false));
		floor.getRoom(9, 9).setRoomFeature(WEST, new Door(false));
		
		
		return dungeon;
	}/*



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
				switch(ycnt){
					case 0:
						North = new Wall();
						break;
					case floorysize:
						South = new Wall();
						break;}
				switch(xcnt){
					case 0:
						East = new Wall();
						break;
					case floorxsize:
						West = new Wall();
						break;}
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
	/*
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
	}*/
}