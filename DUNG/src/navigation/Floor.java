/*
 * **************************************************************************************************************
 * Floor.java | Author: brandonlewis | Date: 2019.02.15 | Rev: 2019.02.16
 * This file is a class of the navigation package for the "Dungeons of UNG" text-based game.
 * **************************************************************************************************************
 */
package navigation;

public class Floor {
  
  private Room[] rooms;
  private Room activeRoom;
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a floor object with the specified number of rooms and active room.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Floor(int roomCount, int activeRoomIndex) {
	this.rooms = new Room[roomCount];
	this.activeRoom = rooms[activeRoomIndex];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns the active room of this floor.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String getRoom() {
	return this.activeRoom.toString();
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the active room of this floor.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setRoom(Room activeRoom) {
	this.activeRoom = activeRoom;
  }

}
