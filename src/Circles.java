// File name: Circles

// Written by: Hannah Guillen
 
// Description: Extends TwoDimensionalShapes. Invokes superclass constructors for 
// three constructors. It will display a string of the shape name, points, color,              
// and whether filled or not and values of area and perimeter.             
// Challenges: Declare and find calculation of instance variable radius. 
//
// Time Spent: 30 minutes

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 09/10/2020   (HG)                       
 * Started and finished class
 * 11/01/2020   (HG)                       
 * Added getPoints
 * 11/06/YYY    (HG)       
 * Set points to 0 in no argument constructor
*/      
import java.awt.*;
public class Circles extends TwoDimensionalShapes
{
    public double radius;
    public int width;
    
    Circles() {
        super();
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = Color.BLACK;
    }
    Circles (int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    Circles (int x1, int y1, int x2, int y2, Color color, boolean filled, int width) {
        super(x1, y1, x2, y2, color, filled);
        this.width = width;
    }
    public double getRadius() { //returns half of the greater value of either width or height
        super.getWidth();
        super.getHeight();
        
        if (getWidth() > getHeight())
            return (.5 * getWidth());
        else if (getHeight() > getWidth())
            radius = (.5 * getHeight());
            return (.5 * getHeight()); 
        //return 0;
    }
    @Override
    public double getArea() {
        return (getRadius() * getRadius() * Math.PI); //returns calculated area
    }
    @Override
    public double getPerimeter() {
        return (2 * getRadius() * Math.PI); //returns calculated perimeter
    }
    @Override
    public String getName() {
        return("Shape: Circle");
    }
    @Override
    public void draw(Graphics2D g2d) {
        if (filled)

            g2d.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        else
            g2d.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //
    }
    //@Override
    public String getPoints(){
        return(("(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")"));
    }
    @Override
    public String toString() {
        String area = String.valueOf(getArea());
        String perimeter = String.valueOf(getPerimeter());
        
        return(getName() + "\n Points: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ") \n" + "Area: " + area + " Perimeter: " + perimeter);
    }
}