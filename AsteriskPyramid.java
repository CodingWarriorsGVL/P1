/*
 * Description: AsteriskPyramid.java is a ...
 * 
 * Author: Brandon Lewis | Created: (Date) | Modified: (N/A)
*/

public class AsteriskPyramid {
  public static void main(String[] args) {
    
    /* String literal demo
    System.out.println("    *    ");
    System.out.println("   ***   ");
    System.out.println("  *****  ");
    System.out.println(" ******* ");
    System.out.println("*********");
    */
    
    //declare and set height variable of 5
    final int HEIGHT = 5;
    //System.out.println("Height: " + HEIGHT);
    
    //declare and set variable character
    char character = '*';
    //System.out.println("Character: " + character);
    
    //declare and set padding variable based on height
    int padding = HEIGHT - 2;
    //System.out.println("Padding: " + padding);
    
    //declare and set charlength variable based on height
    int charlength = 1;
    //System.out.println("Character Length: " + charlength);
    
    //declare and set variable character
    char space = ' ';
    //System.out.println("Space: \"" + space + "\"");
    
    //for loop executing a number of times equal to the height
    for (int i = 0; i < HEIGHT; i++) {
      
      //print a decreasing quantity of spaces
      for (int q = padding; q >= 0; q--) {
        System.out.print(space);
      }
      
      //print an increasing quantity of characters
      for (int q = 0; q < charlength; q++) {
        System.out.print(character);
      }
      
      //print a decreasing quantity of spaces
      for (int q = padding; q >= 0; q--) {
        System.out.print(space);
      }
      
      System.out.println();
      charlength += 2; //increment quantity of characters for next loop
      padding -= 1; //decrement quantity of spaces for next loop
      
    }
  }
}