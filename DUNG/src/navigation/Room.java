/*
 * **************************************************************************************************************
 * Room.java | Author: brandonlewis | Date: 2019.02.15 | Rev: 2019.02.16
 * This file is a class of the navigation package for the "Dungeons of UNG" text-based game. The room class interfaces with the Entity class which is not a part of the navigation package.
 * **************************************************************************************************************
 */
package navigation;

//import chacters.Entity;

public class Room {
  
  private ItemContainer[] itemContainers;
  private Entity[] entities; // This class is not defined in the navigation package.
  private RoomFeature[] roomFeatures;
  private final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a room object with empty arrays to hold containers, entities, and room features.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Room(int itemContainerCount, int entityCount, int featureCount) {
	this.itemContainers = new ItemContainer[itemContainerCount];
	this.entities = new Entity[entityCount];
	this.roomFeatures = new RoomFeature[featureCount];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the room's container array to the specified array of containers.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setItemContainers(ItemContainer[] itemContainers) {
	if ( itemContainers.length() == this.itemContainers.length() ) {
	  this.itemContainers = itemContainers;
	} else {
	  System.out.println("setItemContainers error: itemContainers.length() != this.itemContainers.length()");
	}
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the room's entity array to the specified array of entities.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setEntities(Entity[] entities) {
	if ( entities.length() == this.entities.length() ) {
	  this.entities = entities;
	} else {
	  System.out.println("setEntities error: entities.length() != this.entities.length()");
	}
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the room's feature array to the specified array of features.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setRoomFeatures(RoomFeature[] roomFeatures) {
	if ( roomFeatures.length() == this.roomFeatures.length() ) {
	  this.roomFeatures = roomFeatures;
	} else {
	  System.out.println("setRoomFeatures error: roomFeatures.length() != this.roomFeatures.length()");
	}
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns a string description of this room object.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String toString() {
	String result = "Features:\n";
	for (roomFeature : roomFeatures) {
	  result += roomFeature.toString() + "\n";
	}
	
	result += "\nContainers:\n";
	for (itemContainer : itemContainers) {
	  result += itemContainer.toString() + "\n";
	}
	
	result += "\nEntities:\n";
	for (entity : entities) {
	  result += entity.toString() + "\n";
	}
	
	result += "\n";
	return result;
  }
  
}
