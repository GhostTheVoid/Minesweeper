/** Required package class namespace */
package tools;

/** required imports */
import java.awt.event.KeyEvent;

/**
 * UserInput.java - methods to detect user inputs in various ways
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class UserInput {

    private Coordinates source;
    
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param source the coordinate data to assign to this class
     */
    public UserInput(Coordinates source) {
        this.source             = source;     
    }
    
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {   
        int key = event.getKeyCode();
        //if (key == KeyEvent.VK_ESCAPE)
        
    }
}
