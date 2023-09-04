// File name: Squares

// Written by: Hannah Guillen
 
// Description: Extends TwoDimensionalShapes. Invokes constructors from super class
// and displays description of shape with points, color, whether filled or not filled              
// and the values of area and perimeter.              
// Challenges: Need to get getHeight and getWidth from super class for calculation. 
// Calculation of area and perimeter is also different. 
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

public class Squares extends TwoDimensionalShapes {
    
    public int side;
    public int width;
    
    Squares() {
        //no-argument constructor
        super();
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = Color.BLACK;
    }
    Squares(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }
    Squares(int x1, int y1, int x2, int y2, Color color, boolean filled, int width) {
        super(x1, y1, x2, y2, color, filled);
        this.width = width;
    }
    
    public void setSide(double side) {
        super.getHeight();
        super.getWidth();
        
        if (getHeight() > getWidth()) //determines greater value of getWidth and getHeight
            this.side = getHeight();
        else
            this.side = getWidth();
    }
    public int getSide() { 
        setSide(0);
        return side;
    }
    @Override
    public double getArea() {
        return (2 * getSide()); //return calculated area
    }
    @Override
    public double getPerimeter() {
        return (4 * getSide()); //return calculated perimeter
    }
    @Override
    public String getName() {
        return ("Shape: Square");
    }
    @Override
    public void draw(Graphics2D g2d) {
        if (filled)
            g2d.fillRoundRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), 0, 0);
        else
            g2d.drawRoundRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), 0, 0);
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