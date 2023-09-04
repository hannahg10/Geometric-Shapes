// File name: Points

// Written by: Hannah Guillen
 
// Description: Will contain two instance variables for x and y coordinates. Two 
// different constructors for the values with setter and getter method for x and 
// y values. Will also contain a toString() to display the coordinate format.             
//              
// Challenges: Create set and get methods for x and y values. 
//
// Time Spent: 30 minutes

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 08/10/2020   (HG)                       
 * Started and finished the class.
 * DD/MM/YYY   (name)                       
 * 
 * DD/MM/YYY   (name)                       
*/      
public class Points {
    
    static int x; //x value coordinate
    static int y; //y value coordinate
   
   Points(int x, int y){ //constructor with two values of x and y
       this.x = x;
       this.y = y;
   }
   Points() {
       //no-argument constructor sets all coordinates to 0
       x = 0;
       y = 0;
   }
   public void setX(int x){ //Setter method for X value
       this.x = x;
   }
   public void setY(int y){ //Setter method for Y value
       this.y = y;
   }
   public int getX(){ //Getter method for X coordinate value
       return x;
   }
   public int getY(){ //Getter method for Y coordinate value
       return y;
   }
   
   @Override
   public String toString() {
       return ("(" + x + ", " + y + ")"); //returns values
   }
}

