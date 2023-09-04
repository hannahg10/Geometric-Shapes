// File name: DrawableShapes

// Written by: Hannah Guillen
 
// Description: Abstract class that implements the GeometricObject interface. This class will set color
// of shapes with setter and getter methods to set and get any piece in the hierarchy.           
//              
// Challenges: Have to declare private instance variables of class Points using composition.
// Also implement constructor that sets color of shape and return point1 and point2 as string.
// Time Spent: 1 hour

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 08/10/2020   (HG)                       
 * Implemented constructor classes and set/get methods.
 * 11/10/2020   (HG)                       
 * Attempted to fix Points methods
 * 11/06/YYY    (HG)       
 * Set points to 0 in no argument constructor                
*/  

import java.awt.Color; //package for Color object

public abstract class DrawableShapes implements GeometricObject{
    
    int x1; 
    int x2;
    int y1;
    int y2;
    private Points point1;
    private Points point2;
    Color color;
    
    DrawableShapes() {
    // no argument constructor that sets all coordinate values to 0
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = color.BLACK;
        point1 = new Points(x1, y1);
        point2 = new Points(x2, y2);
    }
    DrawableShapes(int x1, int y1, int x2, int y2) { //initalizes coordinates to values of the arguments
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        color = color.BLACK; //initializes stroke color to black
        point1 = new Points(x1, y1);
        point2 = new Points(x2, y2);
    }
    DrawableShapes(int x1, int y1, int x2, int y2, Color color)
    { //initalizes coordinates to values of arguments supplied and stroke color to argument supplied
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        point1 = new Points(x1, y1);
        point2 = new Points(x2, y2);
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public Color getColor() {
        return color;
    } 
    public String getColorName() {
        return("Color is: " + color);
    }
    public Points getPoint1() {
        Points x = new Points();
        this.point2 = x;
        return x;
    }
    public Points getPoint2() {
        Points y = new Points();
        this.point2 = y;
        return y;
    }
    
    @Override
    public String toString() {
        return(" coordinates are: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") \n");
    }
    public String getCoordinatesAsString(){
        return("Point coordinates: (" + point1 + ", " + point2 + ")");
    }
}
