/** Required package class namespace */
package tools;

 
/**
 * Coordinates.java - stores data on position and movement as well as methods 
 * to move those values
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Coordinates {

    public int x, y, width, height,
               left, right, top, bottom,
               centerX, centerY,
               amount;
    
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param x the x value coordinate
     * @param y the y value coordinate
     * @param width the width value coordinate
     * @param height the height value coordinate 
     * @param amount the starting amount of movement
     */
    public Coordinates(int x,
                       int y,
                       int width,
                       int height,
                       int amount) {
        this.x         = x;
        this.y         = y;
        this.width     = width;
        this.height    = height;
        this.amount    = amount;
        recalculate();
    }
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param x the x value coordinate
     * @param y the y value coordinate
     * @param width the width value coordinate
     * @param height the height value coordinate 
     */
    public Coordinates(int x,
                       int y,
                       int width,
                       int height) {
        this(x,y,width,height,0);
    }
    
    /**
     * Constructor for the class, sets class properties
     *  
     * @param amount the starting amount of movement
     * @param direction the starting direction of movement
     */
    public Coordinates(int amount) {
        this(0,0,0,0,amount);
    }
    
    /** Default constructor for the class, sets class properties */
    public Coordinates() {
        this(0,0,0,0,0);
    }
        
    /** Recalculates needed movement data */
    public void recalculate() {
        left    = x;
        top     = y;
        right   = left + width;
        bottom  = top  + height;
        centerX = x    + (width  / 2);
        centerY = y    + (height / 2);
    }    
}
