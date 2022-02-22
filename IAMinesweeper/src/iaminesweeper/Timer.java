/** Required package class namespace */
package iaminesweeper;

import javax.swing.JLabel;
import tools.Directions;
import tools.GameCharacter;

 
/**
 * Timer.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Timer extends GameCharacter{

    /**
     * Default constructor, set class properties
     */
    public Timer(JLabel timerLabel) {
        super(timerLabel, Constants.CELL_MOVE_AMOUNT, 
                Constants.CELL_TIMER_DELAY);
    }

    @Override
    public void action() {
    }
}
