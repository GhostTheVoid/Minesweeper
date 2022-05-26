/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.JLabel;
import tools.Animation;
import tools.Coordinates;
import tools.Detector;
import tools.Reactor;
import tools.Sprite;

/**
 * Counter.java - Breaks up numbers into their singular digits, 
 * and assigns these digits to labels. Resets with the grid.
 * 
 * -----------------------
 * @author Marissa Rowles
 * @since 28-Feb-2022
 */
public class Counter {
    
    // Global properties (variables)
    protected int            ones, tens, hundreds;
    private LinkedList<JLabel> jLabels;

    /**
     * Default constructor, set class properties
     * 
     * @param jLabels the set of JLabels the object will use
     */
    public Counter(LinkedList<JLabel> jLabels) {
        this.jLabels = jLabels;
        System.out.println(jLabels.toString());
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
     * @param num   the int that will replace the current label
     * @param label the label to be updated
     */
    private static void updateLabel(int num, JLabel label) {
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
        updateLabel(ones,     jLabels.get(0));
        updateLabel(tens,     jLabels.get(1));
        updateLabel(hundreds, jLabels.get(2));
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
}
