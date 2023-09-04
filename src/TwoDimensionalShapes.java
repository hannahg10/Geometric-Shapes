// File name: TwoDimensionalShapes

// Written by: Hannah Guillen
 
// Description: Abstract class that extends DrawableShapes super class. It declares  
// methods that calculate upper-left coordinate, upper-right coordinate, width and height.             
//              
// Challenges: Implements the Comparable<T> interface. Also implements max method
// to find larger of two objects from TwoDimensionalShapes. 
// Time Spent: 2 hours

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 09/10/2020   (name)                       
 * Started the class; made constructors and get methods and started compareTo/max methods
 * 11/10/2020   (HG)                       
 * Attempted to fix the static max method to find the larger of two objects
 * 11/06/YYY    (HG)       
 * Set points to 0 in no argument constructor                   
*/      
import java.awt.*;
import java.lang.*;

public abstract class TwoDimensionalShapes extends DrawableShapes implements Comparable<TwoDimensionalShapes> { //<TwoDimensionalShapes> {
    
    boolean filled;
    
    TwoDimensionalShapes(){
        //no-argument constructor
        super();
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        color = Color.BLACK;
        filled = false;
    }
    TwoDimensionalShapes(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        filled = false;
    }
    TwoDimensionalShapes(int x1, int y1, int x2, int y2, Color color, boolean filled) {
        super.x1 = x1;
        super.x2 = x2;
        super.y1 = y1;
        super.y2 = y2;
        super.color = color;
        this.filled = filled;
    }
    public int getUpperLeftX(){ //smaller of the two  x-coordinate values
        if (x1 < x2)
            return x1;
        else if (x2 < x1)
            return x2;
        return 0;
    }
    public int getUpperLeftY() { //smaller of the two y-coordinate values
        if (y1 < y2)
            return y1;
        else if (y2 < y1)
            return y2;
        return 0;
    }
    public int getWidth() { //absolute value of the difference between x-coordinates
        return (Math.abs(x1-x2));
    }
    public int getHeight() { //absolute value of the difference between y-coordinates
        return (Math.abs(y1-y2));
    }
    
    public boolean isFilled() { //return value of instance variable
        return filled;
    }
    public void setFilled(boolean isFilled) { //assign instance variable's value
        this.filled = isFilled;
    }
    
    public abstract double getArea(); //tell TwoDimensionalShapes to return area value
    public abstract double getPerimeter(); //tell TwoDimensionalShapes to return perimeter value
    
    public int compareTo(TwoDimensionalShapes o){ //inherences from the interface Comparable<T>
        
        if(this.getArea() > o.getArea())
            return 1;
        else if(this.getArea() == o.getArea())
            return 0;
        else
            return -1;
    }
    public TwoDimensionalShapes max(TwoDimensionalShapes o1, TwoDimensionalShapes o2) {
       //finds the larger of TwoDimensionalShapes objects
       if(o1.compareTo(o2) > 0)
            return o1;
        else if (o2.compareTo(o1) > 0)
            return o2;
        return null;
    }
}