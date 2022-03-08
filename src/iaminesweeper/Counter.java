/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import tools.Animation;
import tools.Coordinates;
import tools.Detector;
import tools.Reactor;
import tools.Sprite;

/**
 * Counter.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 28-Feb-2022
 */
public class Counter {
    
    /** Coordinates to store data on position and movement */    
    public Coordinates coordinates;
    /** The image used for this game object */
    public Sprite sprite1, sprite2, sprite3;
    /** Various methods to detect collision for the game object */
    public Detector detector;
    /** Various methods to react to collision for the game object */
    public Reactor reactor;    
    /** Flag determines if this object is alive in a game */
    public boolean isAlive;
    
    // Global properties (variables)
    protected int    ones, tens, hundreds;
    protected LinkedList<Animation> animations1, animations2, animations3;
    
    private LinkedList<JLabel> jLabels;

    /**
     * Default constructor, set class properties
     */
    public Counter(LinkedList<JLabel> jLabels, LinkedList<String> settings) {
        this.jLabels = jLabels;
//        sprite1      = new Sprite(jLabels.get(0));
//        sprite2      = new Sprite(jLabels.get(1));
//        sprite3      = new Sprite(jLabels.get(2));
//        
//        setAnimations(jLabels.get(0), settings, sprite1, animations1);  // build label animations
//        setAnimations(jLabels.get(1), settings, sprite2, animations2); // build label2 animations
//        setAnimations(jLabels.get(2), settings, sprite3, animations3); // build label3 animations
        
    }
    
    

    protected void getInt() {
        System.out.println(hundreds + "" + tens + "" + ones);
    }
    
    /**
     * Change the data of each spot in the counter, then update the associated
     * labels
     * 
     * @param ones     int representing the ones spot
     * @param tens     int representing the tens spot
     * @param hundreds int representing the hundreds spot
     */
    protected void setInt(int ones, int tens, int hundreds){
        this.ones = ones;
        this.tens = tens;
        this.hundreds = hundreds;
        
        updateAllLabels(ones, tens, hundreds);
    }
    
    /**
     * The logic associated with each update of the label object
     */
    protected void updateCounter(){
        ones++;                     // Increase ones
        if (ones == 10) {           // Roll over to next digit
            ones = 0;               // Rest ones
            tens++;                 // Increase tens
            if (tens == 10) {       // Roll over to next digit
                tens = 0;           // Reset tens
                hundreds++;         // Increase hundreds
            }
        }
        updateAllLabels(ones, tens, hundreds);
    }
    
    /**
     * Update one of the label objects to match with the data provided
     * 
     * @param num the int that will replace the current label
     * @param label 
     */
    private void updateLabel(int num, JLabel label) {
        label.setText(num+"");
    }
    
    /**
     * Update all the label objects to match with the data provided
     * 
     * @param ones the int that will be put into the ones spot
     * @param tens the int that will be put into the tens spot
     * @param hundreds the int that will be put into the hundreds spot
     */
    protected void updateAllLabels(int ones, int tens, int hundreds) {
        updateLabel(ones, jLabels.get(0));
        updateLabel(tens, jLabels.get(1));
        updateLabel(hundreds, jLabels.get(2));
    }
    
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    private void setAnimations(JLabel label, LinkedList<String> settings, Sprite sprite, LinkedList<Animation> timerAnimations) {
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
                                                       delay, settings, tag); //154
        timerAnimations = new LinkedList<>(); 
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
     
    /**
     * String representation of this object
     *
     * @return The object represented as a String
     */
    @Override
    public String toString() {
        return "Counter: " + hundreds + tens + ones;
    }
   
    /**
     * Deep comparison, determines if two objects are "equal" in this context
     *
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }
       
    /**
     * a Deep clone, creates a duplicate object using new memory
     *
     * @return a "clone" of the object using new memory
     */
    @Override
    public Counter clone() {
        return this;
    }
    
//    num1
//    1
//    0,0,13,23
//    num2
//    1
//    7,0,13,23
//    num3
//    1
//    18,0,13,23
//    num4
//    1
//    42,0,13,23
//    num5
//    1
//    56,0,13,23
//    num6
//    1
//    70,0,13,23
//    num7
//    1
//    84,0,13,23
//    num8
//    1
//    98,0,13,23
//    num9
//    1
//    112,0,13,23
//    num0
//    1
//    126,0,13,23
//    numDash
//    1
//    140,0,13,23
//    numBlank
//    1
//    140,0,13,23
}
