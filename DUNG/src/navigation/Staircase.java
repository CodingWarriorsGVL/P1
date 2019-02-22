/*
 * **************************************************************************************************************
 * Staircase.java | Author: brandonlewis | Date: 2019.02.15 | Rev: 2019.02.15
 * This file is a class of the navigation package for the "Dungeons of UNG" text-based game.
 * **************************************************************************************************************
 */
package navigation;

public class Staircase extends RoomFeature {
  
  private boolean leadsUp;
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Constructor - Instantiates a staircase object with the specified leadsUp boolean state.
   * ------------------------------------------------------------------------------------------------------------
   */
  public Staircase(boolean leadsUp) {
	this.leadsUp = leadsUp;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns the state of the leadsUp boolean.
   * ------------------------------------------------------------------------------------------------------------
   */
  public boolean getLeadsUp() {
	return this.leadsUp;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Sets the state of the staircase, leading up or down.
   * ------------------------------------------------------------------------------------------------------------
   */
  public void setLeadsUp(boolean leadsUp) {
	this.leadsUp = leadsUp;
  }
  
  /*
   * ------------------------------------------------------------------------------------------------------------
   * Returns a string representation of this object.
   * ------------------------------------------------------------------------------------------------------------
   */
  public String toString() {
	if (leadsUp) {
	  return "A steep spiral staircase rises to the floor above.";
	} else {
	  return "A steep spiral staircase descends to the floor below.";
	}
	
  }
  
}
