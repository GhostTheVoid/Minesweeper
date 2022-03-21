/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 * FlagTracker.java - description

 -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class FlagTracker extends Counter{
    
    private static int count;

    /**
     * Default constructor, set class properties
     */
    public FlagTracker(LinkedList<JLabel> flagLabels) {
        super(flagLabels);
    }
    
    public static void findCount(){
        System.out.println("DEFAULT = " + Difficulties.bombCount);
        count = Difficulties.bombCount;
        
        int[] countArry = {0,0,0};
        countArry = String.valueOf(count).chars().map(Character::getNumericValue).toArray();
        String text = "Count = ";
        for (int i = 0; i < countArry.length; i++) {
            text += countArry[i];
        }
        System.out.println(text);
        
        System.out.println("BOMB COUNT: ");
        System.out.print("Ones = " + ones);
        System.out.print(" || Tens = " + tens);
        System.out.println(" || Hundreds = " + hundreds);
    }
    
    
    
    private static void setCount(){
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
    private static void updateCount(){
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
