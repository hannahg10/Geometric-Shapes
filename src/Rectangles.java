// File name: Rectangles

// Written by: Hannah Guillen
 
// Description: Extends TwoDimensionalShapes. Invokes constructors from super class
// and displays description of shape with points, color, whether filled or not filled              
// and the values of area and perimeter.          
//              
// Challenges: Need to get getHeight and getWidth from super class for calculation. 
// Calculation of area and perimeter is also different. 
//
// Time Spent: 45 minutes

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 11/10/2020   HG                       
 * Started and finished class
 * 11/01/2020   (HG)                       
 * Added getPoints
 * 11/06/YYY    (HG)       
 * Set points to 0 in no argument constructor                  
*/
import java.awt.*;

public class Rectangles extends TwoDimensionalShapes{
    
    public int width;
    
    Rectangles() {
        //no-argument constructor
        super();
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = Color.BLACK;
    }
    Rectangles(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    Rectangles(int x1, int y1, int x2, int y2, Color color, boolean filled, int width) {
        super(x1, y1, x2, y2, color, filled);
        this.width = width;
    }
    @Override
    public double getArea() {
        super.getWidth();
        super.getHeight();
        
        double getArea = Double.valueOf(getHeight() * getWidth()); //cast to double type
        return getArea; //return calculated area
    }
    @Override
    public double getPerimeter() {
        super.getWidth();
        super.getHeight();
        
        double getPerimeter = Double.valueOf((2 * getWidth()) + (2 * getHeight())); //cast to double type
        return (getPerimeter); //return calculated perimeter
    }
    @Override
    public String getName() { //implement from TwoDimensionalShapes and display name
        return ("Shape: Rectangle");
    }
    @Override
    public void draw(Graphics2D g2d) {
        if (!filled)
            g2d.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draw unfilled rectangle
        else
            g2d.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight()); //draw solid rectangle
    }
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
    