/*
 * **************************************************************************************************************
 * Room.java | Author: brandonlewis | Date: 2019.02.15 | Rev: 2019.04.02 | Last Edit: Jared Hawkins
 * This file is a class of the navigation package for the "Dungeons of UNG" text-based game. The room class interfaces with the Entity and Instance classes which are not a part of the navigation package.
 * **************************************************************************************************************
 */
package navigation;

import java.util.ArrayList;
import characters.Entity;
import instance.Instance;

public class Room {
  
  private ArrayList<Entity> entities;
  private RoomFeature[] roomFeatures;
  private final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
  private ArrayList<Instance> instances;
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a room object with an empty list of entities and an empty array of roomFeatures.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Room() {
	this.entities = new ArrayList<Entity>();
	this.instances = new ArrayList<Instance>(); 
	this.roomFeatures = new RoomFeature[4];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Overloaded Constructor - Instantiates a room object with an empty list of entities and an specified array of roomFeatures.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Room(RoomFeature north, RoomFeature east, RoomFeature south, RoomFeature west) {
	this.entities = new ArrayList<Entity>();
	this.instances = new ArrayList<Instance>(); 
	this.roomFeatures = new RoomFeature[4];
	this.roomFeatures[NORTH] = north;
	this.roomFeatures[SOUTH] = south;
	this.roomFeatures[EAST] = east;
	this.roomFeatures[WEST] = west;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Adds the specified entity object(s) to the room.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void addEntities(Entity... newEntities) {
	for (int i = 0; i < newEntities.length; i++) {
	  this.entities.add(newEntities[i]);
	}
  }
  
  public ArrayList<Entity> getEnties() {
	  return this.entities; //Could change to just Instance return later, with the number call for the Instance being input.
  }
  // Adds an Instance to the room.
  public void addInstances(Instance... newInstances) {
	  for (int i=0; i<newInstances.length; i++)
		  this.instances.add(newInstances[i]); 
  }
  
  // Retrieves the Instances the room holds. 
  public ArrayList<Instance> getInstances() {
	  return instances; //Could change to just Instance return later, with the number call for the Instance being input.
  }
  
  
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the roomFeature at the specified location to the specified roomFeature.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setRoomFeature(int location, RoomFeature roomFeature) {
	this.roomFeatures[location] = roomFeature;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Gets the roomFeature at the specified location.
   * ------------------------------------------------------------------------------------------------------------
   */
  public RoomFeature getRoomFeature(int location) {
	return this.roomFeatures[location];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Gets the north roomFeature.
   * ------------------------------------------------------------------------------------------------------------
   */
  public RoomFeature getNorth() {
	return this.roomFeatures[NORTH];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Gets the east roomFeature.
   * ------------------------------------------------------------------------------------------------------------
   */
  public RoomFeature getEast() {
	return this.roomFeatures[EAST];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Gets the south roomFeature.
   * ------------------------------------------------------------------------------------------------------------
   */
  public RoomFeature getSouth() {
	return this.roomFeatures[SOUTH];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Gets the west roomFeature.
   * ------------------------------------------------------------------------------------------------------------
   */
  public RoomFeature getWest() {
	return this.roomFeatures[WEST];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns a string description of this room object's roomFeatures and entities.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String toString() {
	String result = "Features:\n";
	for (RoomFeature roomFeature : roomFeatures) {
	  result += roomFeature.toString() + "\n";
	}
	
	result += "\nEntities:\n";
	for (Entity entity : entities) {
	  result += entity.toString() + "\n";
	}
	
	result += "\n";
	return result;
  }
  
}
