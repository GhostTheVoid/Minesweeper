/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.JLabel;

/**
 * FlagTracker.java - description

 -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class FlagTracker extends Counter{
    
    private int count;

    /**
     * Default constructor, set class properties
     */
    public FlagTracker(LinkedList<JLabel> flagLabels,  LinkedList<String> settings) {
        super(flagLabels, settings);
    }
    
    private void findCount(){
        
    }
    
    private void setCount(){
        
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
     * The logic associated with each update of the label object
     */
    private void updateCount(){
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
}
