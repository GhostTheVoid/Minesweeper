/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
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
    
    JLabel statusLabel;
    Boolean isClicked;

    /**
     * Default constructor, set class properties
     */
    public Status(JLabel statusLabel, LinkedList<String> settings) {
        super(statusLabel, Constants.FACE_MOVE_AMOUNT,
                Constants.FACE_TIMER_DELAY);
        this.statusLabel = statusLabel;
        setAnimations(statusLabel, settings); // build all animations
    }
    
    public void restartGame(MouseEvent evt) {                                       
        try {
            if (!evt.getSource().equals(statusLabel)) {
                System.out.println("NO!!!");
            }
            else {
                System.out.println("Click");
                sprite.animate(1);
            
                Thread.sleep(500);
            
                sprite.animate(0);
            }
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException");
        }
    }
    
    /** changes the animation set to the appropriate animation based on direction */
    public void animate() {
        if (isClicked){
            
        }
        
        
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
