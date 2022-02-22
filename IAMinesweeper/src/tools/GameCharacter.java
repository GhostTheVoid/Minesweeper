/** Required package class namespace */
package tools;

/** required imports */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.Timer;


/**
 * GameCharacter.java - representation of a character in a game 
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public abstract class GameCharacter extends GameObject
{
    
    /** Controls the user input actions for this game character */
    public UserInput input;
    /** Timer times the actions of this game character */
    public Timer timer;
            

    /**
     * Constructor for the class, sets class property data
     * 
     * @param image the label associated with the image for the game character
     * @param amount the amount the game character will move
     * @param delay the delay in milliseconds of the character's timer
     */
    public GameCharacter(JLabel image, 
                         int amount,
                         int delay) {
        super(image, amount);
        input = new UserInput(super.coordinates);
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
        timer.start();
    }
    
    /** The abstract action this character does in it's timer */
    public abstract void action();
        
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {
        if (isAlive) input.keypress(event);
    }
}