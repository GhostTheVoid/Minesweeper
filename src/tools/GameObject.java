/** Required package class namespace */
package tools;

/** required imports */
import javax.swing.JLabel;
 
/**
 * GameObject.java - represents a typical object in a game
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class GameObject {

    /** Coordinates to store data on position and movement */    
    public Coordinates coordinates;
    /** The image used for this game object */
    public Sprite sprite;
    /** Various methods to detect collision for the game object */
    public Detector detector;
    /** Various methods to react to collision for the game object */
    public Reactor reactor;    
    /** Flag determines if this object is active in a game */
    public boolean isActive;
    
    
    /**
     * Constructor for the class, sets class property data
     * 
     * @param image picture image used on a user interface
     */
    public GameObject(JLabel image) {
        this(image,0);
    }
    
    /**
     * Constructor for the class, sets class property data
     * 
     * @param image picture image used on a user interface
     * @param amount the amount the game character will move
     */
    public GameObject(JLabel image, 
                      int amount) {
        coordinates = new Coordinates(amount);        
        sprite      = new Sprite(image);
        sprite.update(coordinates);
        detector    = new Detector(coordinates);
        reactor     = new Reactor(coordinates,detector); 
        spawn();
    } 
        
    /** Updates the current location of the coordinates for the image */
    public void update() {
        sprite.update(coordinates);
    }
    
    /** Re-positions image in it's container based on game character's data */
    public void redraw() {
        sprite.redraw(coordinates);
    }
    
    /** Spawns the game object, makes it alive and visible */
    public void spawn() {
        isActive = true;
        sprite.show();
    }
    
    /** De-spawns the game object, makes it not alive and invisible */
    public void despawn() {
        isActive = false;
        sprite.hide();
    }
}
