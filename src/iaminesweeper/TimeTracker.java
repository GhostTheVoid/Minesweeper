/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

 
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
    public TimeTracker(LinkedList<JLabel> timerLabels) {
        super(timerLabels);
        
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //For every tick, update the inherited counter
                updateCounter();
            }
        });
    }
    
    /**
     * Checks if timer is already started, if not, resets the counter integers,
     * and then starts the timer.
     */
    public void start(){
        System.out.println("Reached");
        if (!timer.isRunning()) {
            super.setInt(0, 0, 0);
            timer.start();
            System.out.println("Timer Started!");
        }
        else System.out.println("Timer is already running!");
    }
    
    /**
     * Checks if timer is already stopped, if not, stops the timer
     */
    public void stop(){
        if (timer.isRunning()) {
            timer.stop();
            setInt(0, 0, 0);
            System.out.println("Timer Stopped!");
        }
        else System.out.println("Timer is already stopped!");
    }
}
