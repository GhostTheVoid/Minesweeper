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
 * ResetButton.java - description

 -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class ResetButton extends GameCharacter{
    
    private JLabel statusLabel;
    private Timer clickDelay;
    
    public Boolean isClicked;
    
    private final int FACE_DEFAULT_TAG   = 0;            
    private final int FACE_RESET_TAG     = 1;            
    private final int FACE_CLICK_TAG     = 2;    
    private final int FACE_WIN_TAG       = 3;  
    private final int FACE_LOSE_TAG      = 4;  

    /**
     * Default constructor, set class properties
     */
    public ResetButton(JLabel statusLabel) {
        super(statusLabel, Globals.FACE_MOVE_AMOUNT,
                Globals.FACE_TIMER_DELAY);
        this.statusLabel = statusLabel;
        
        ActionListener delayAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickDelayAction();
            }
        };
        clickDelay = new Timer(50, delayAction);
        
        setAnimations(statusLabel); // build all animations
    }
    
    /**
     * When this label is clicked on, envolks this method, 
     * creating a short clicking animation
     * 
     * @param evt the event type
     */
    public void clickStatus(MouseEvent evt) {
        if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
            animate(FACE_RESET_TAG);
        }
        else if (evt.getID() == MouseEvent.MOUSE_RELEASED){
            clickDelay.start();
        } 
    }
    
    public void leftMouseBtnDown(MouseEvent evt){
        if (evt.getID() == MouseEvent.MOUSE_PRESSED) {
            animate(FACE_CLICK_TAG);
        }
        else if (evt.getID() == MouseEvent.MOUSE_RELEASED){
            clickDelay.start();
        } 
    }
    
    /** changes the animation set to the appropriate animation based on direction */
    public void animate(int animation) {
        sprite.animate(animation);
    }
    
    private void clickDelayAction() {
        animate(FACE_DEFAULT_TAG);
        clickDelay.stop();
    }
    
     
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     */
    private void setAnimations(JLabel label) {
        String sheet = Globals.SPRITE_SHEET;
        int    delay = Globals.FACE_ANIMATION_DELAY; 
        String tag   = Globals.FACE_DEFAULT_TAG;        
        Animation faceDefault = Animator.getAnimation(sheet, label, 
                                                       delay, Globals.settings, tag);
        tag = Globals.FACE_RESET_TAG;        
        Animation faceReset = Animator.getAnimation(sheet, label, 
                                                       delay, Globals.settings, tag);
        tag = Globals.FACE_CLICK_TAG;        
        Animation faceClick = Animator.getAnimation(sheet, label, 
                                                       delay, Globals.settings, tag);
        tag = Globals.FACE_WIN_TAG;        
        Animation faceWin = Animator.getAnimation(sheet, label, 
                                                       delay, Globals.settings, tag);        
        tag = Globals.FACE_LOSE_TAG;        
        Animation faceLose = Animator.getAnimation(sheet, label, 
                                                       delay, Globals.settings, tag);
        LinkedList<Animation> statusAnimations = new LinkedList<>(); 
        statusAnimations.add(faceDefault);
        statusAnimations.add(faceReset);
        statusAnimations.add(faceClick);
        statusAnimations.add(faceWin);
        statusAnimations.add(faceLose);
        sprite.setAnimations(statusAnimations);             
    }

    @Override
    public void action() {
    }

    
}
