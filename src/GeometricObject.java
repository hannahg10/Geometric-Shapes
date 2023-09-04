// File name: GeometricObject

// Written by: Hannah Guillen
 
// Description: Class knows that two dimensional shapes should be drawable,
// but it does not know what specific shape to draw, so it cannot implement a              
// real draw method.              
// Challenges: 
//
// Time Spent: 10 minutes

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 09/10/2020   (HG)                       
 * Started and finished class
 * DD/MM/YYY   (name)                       
 * 
 * DD/MM/YYY   (name)                       
*/
import java.awt.Graphics2D;

interface GeometricObject {
    
    public abstract String getName(); //interface method (no body)
    public abstract void draw(Graphics2D g2d); //interface method (no body)
}
