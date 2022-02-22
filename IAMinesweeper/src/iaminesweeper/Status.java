/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.JLabel;
import tools.Animation;
import tools.GameCharacter;

/**
 * Status.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class Status extends GameCharacter{

    /**
     * Default constructor, set class properties
     */
    public Status(JLabel statusLabel, LinkedList<String> settings) {
        super(statusLabel, Constants.FACE_MOVE_AMOUNT,
                Constants.FACE_TIMER_DELAY);
        setAnimations(statusLabel, settings); // build all animations
    }
    
    @Override
    public void action() {
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

    
}
