/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import javax.swing.JLabel;
import tools.Animation;
import tools.GameObject;

 
/**
 * Timer.java - representation of a timer in minesweeper
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Timer extends GameObject{
    
    

    /**
     * Default constructor, set class properties
     * 
     * @param timerLabel
     * @param settings
     */
    public Timer(JLabel timerLabel, LinkedList<String> settings) {
        super(timerLabel);
        setAnimations(timerLabel, settings); // build all animations
    }
    
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    private void setAnimations(JLabel label, LinkedList<String> settings) {
        String sheet = Constants.SPRITE_SHEET;
        int    delay = Constants.TIMER_ANIMATION_DELAY; 
        String tag   = Constants.NUM_1_TAG;        
        Animation num1 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_2_TAG;        
        Animation num2 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_3_TAG;        
        Animation num3 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_4_TAG;        
        Animation num4 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);        
        tag = Constants.NUM_5_TAG;        
        Animation num5 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_6_TAG;        
        Animation num6 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_7_TAG;        
        Animation num7 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_8_TAG;        
        Animation num8 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_9_TAG;        
        Animation num9 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_0_TAG;        
        Animation num0 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_DASH_TAG;        
        Animation numDash = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.NUM_BLANK_TAG;        
        Animation numBlank = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        LinkedList<Animation> timerAnimations = new LinkedList<>(); 
        timerAnimations.add(num1);
        timerAnimations.add(num2);
        timerAnimations.add(num3);
        timerAnimations.add(num4);
        timerAnimations.add(num5);
        timerAnimations.add(num6);
        timerAnimations.add(num7);
        timerAnimations.add(num8);
        timerAnimations.add(num9);
        timerAnimations.add(num0);
        timerAnimations.add(numDash);
        timerAnimations.add(numBlank);
        sprite.setAnimations(timerAnimations);             
    }
}
