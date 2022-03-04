/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.event.KeyEvent;
import tools.FileHandler;
import tools.UserInput;

/**
 * Player.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class Player {
    
    /** Controls the user input actions for this game character */
    public UserInput input;
    
    private LinkedList<GridCell> gridCells;
    
    
    
    private FileHandler          file;
    /** Flag determines if this object is alive in a game */
    public boolean isAlive;

    /**
     * Default constructor, set class properties
     */
    public Player(LinkedList<GridCell> gridCells) {
        this.gridCells = gridCells;
        
    }
    
    /** User has lost the game (clicked on a bomb) */
    private void loseGame() {
        //Timer stop
        //set face to death
        //reveal all bombs
    }
    
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {
        if (isAlive) input.keypress(event);
    }
}
