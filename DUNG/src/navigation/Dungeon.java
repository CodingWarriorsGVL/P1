/*
 * **************************************************************************************************************
 * Dungeon.java | Author: brandonlewis | Date: 2019.02.15 | Rev: 2019.02.16
 * This file is a class of the navigation package for the "Dungeons of UNG" text-based game.
 * **************************************************************************************************************
 */
package navigation;

public class Dungeon {
  
  private String name;
  private Floor[] floors;
  private Floor activeFloor;
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a single floor dungeon object with the specified name, using the default floorCount and activeFloor.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Dungeon(String name) {
	this.name = name;
	this.floors = new Floor[1];
	this.activeFloor = floors[0];
  }

  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a dungeon object with the specified name and floorCount, using the default activeFloor.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Dungeon(String name, int floorCount) {
	this.name = name;
	this.floors = new Floor[floorCount];
	this.activeFloor = floors[0];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a dungeon object with the specified name, floorCount, and activeFloor.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Dungeon(String name, int floorCount, int activeFloorIndex) {
	this.name = name;
	this.floors = new Floor[floorCount];
	this.activeFloor = floors[activeFloorIndex];
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns the name of this dungeon.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String getName() {
	return this.name;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the name of this dungeon.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setName(String name) {
	this.name = name;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns the active floor of this dungeon.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String getFloor() {
	return this.activeFloor.toString();
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the active floor of this dungeon.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setFloor(int activeFloorIndex) {
	this.activeFloor = floors[activeFloorIndex];
  }
  
}
