/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;
import tools.Animation;
import tools.GameCharacter;
import tools.GameObject;

/**
 * Status.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class Status extends GameCharacter{
    
    private JLabel statusLabel;
    private Timer clickDelay;
    
    public Boolean isClicked;
    
    

    /**
     * Default constructor, set class properties
     */
    public Status(JLabel statusLabel, LinkedList<String> settings) {
        super(statusLabel, Constants.FACE_MOVE_AMOUNT,
                Constants.FACE_TIMER_DELAY);
        this.statusLabel = statusLabel;
        
        ActionListener delayAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickDelayAction();
            }
        };
        clickDelay = new Timer(50, delayAction);
        
        setAnimations(statusLabel, settings); // build all animations
    }
    
    /**
     * When this label is clicked on, envolks this method, 
     * creating a short clicking animation
     * 
     * @param evt the event type
     */
    public void clickLbl(MouseEvent evt) {
        if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
            sprite.animate(1);
        }
        else if (evt.getID() == MouseEvent.MOUSE_RELEASED){
            clickDelay.start();
        } 
    }
    
    /** changes the animation set to the appropriate animation based on direction */
    public void animate() {
        
        
        
    }
    
    private void clickDelayAction() {
        sprite.animate(0);
        clickDelay.stop();
    }
    
     
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    private void setAnimations(JLabel label, LinkedList<String> settings) {
        String sheet = Constants.SPRITE_SHEET;
        int    delay = Constants.FACE_ANIMATION_DELAY; 
        String tag   = Constants.FACE_DEFAULT_TAG;        
        Animation faceDefault = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.FACE_CLICK_TAG;        
        Animation faceClick = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.FACE_RESET_TAG;        
        Animation faceReset = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.FACE_WIN_TAG;        
        Animation faceWin = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);        
        tag = Constants.FACE_LOSE_TAG;        
        Animation faceLose = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        LinkedList<Animation> statusAnimations = new LinkedList<>(); 
        statusAnimations.add(faceDefault);
        statusAnimations.add(faceClick);
        statusAnimations.add(faceReset);
        statusAnimations.add(faceWin);
        statusAnimations.add(faceLose);
        sprite.setAnimations(statusAnimations);             
    }

    @Override
    public void action() {
    }

    
}
