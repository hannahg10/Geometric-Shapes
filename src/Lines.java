// File name: Lines

// Written by: Hannah Guillen
 
// Description: Abstract class that extends DrawableShapes super class. It will display
// the class name with the points and color of the line.              
//              
// Challenges: Constructors that invokes superclass' coodinates and color. 
//
// Time Spent: 30 minutes

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 11/01/2020   (HG)                       
 * Added getPoints
 * 11/06/YYY    (HG)       
 * Set points to 0 in no argument constructor
 * DD/MM/YYY   (name)                       
*/    
import java.awt.*;

public class Lines extends DrawableShapes {
    
    public int width;
    
    Lines (){
        //no-argument constructor that invoke's DrawableShapes default constructor
        super();
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
        color = Color.BLACK;
    }
    Lines(int x1, int y1, int x2, int y2){
        super(x1, y1, x2, y2);
    }
    Lines(int x1, int y1, int x2, int y2, Color color, int width){
        super(x1, y1, x2, y2, color);
        this.width = width;
    }
    @Override
    public String getName(){
        return ("Line ");
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawLine(getPoint1().getX(), getPoint1().getY(), getPoint2().getX(), getPoint2().getY());
    }
    public String getPoints(){
        return(("(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")"));
    }
    @Override
    public String toString(){
        return ("Class name: Lines\n Points: (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 +")\n Color: " + color);
    }
}