/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import tools.Animation;
import tools.GameObject;

 
/**
 * TimeTracker.java - representation of a timer in minesweeper

 -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class TimeTracker extends Counter{
    
    private Timer timer;
    private final int DELAY = 1000;

    /**
     * Default constructor, set class properties
     * 
     * @param settings
     */
    public TimeTracker(LinkedList<JLabel> timerLabels, LinkedList<String> settings) {
        super(timerLabels, settings);
        
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDigits();
                System.out.println(hundreds + tens + ones);
            }
        });
    }
    
    public void start(){
        timer.start();
        ones = 0;
        tens = 0;
        hundreds = 0;
    }
    
    public void stop(){
        timer.stop();
    }
}
