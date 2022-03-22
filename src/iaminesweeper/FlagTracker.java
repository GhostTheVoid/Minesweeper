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
        
        if (count >= 999) {
            setInt(9, 9, 9);
        }
        else{
            countArry = getDigits(count);
        }
        
        System.out.println("ARRAY = " + countArry[2] + countArry[1] + countArry[0]);
        System.out.println("BOMB COUNT: ");
        System.out.print("Ones = " + ones);
        System.out.print(" || Tens = " + tens);
        System.out.println(" || Hundreds = " + hundreds);
    }
    
    
    private static int[] getDigits(int number) {
        String countText = "" + number;
        String[] countStg = countText.split("");
        int[] array = new int[3];
        
        array[0] = Integer.parseInt(countStg[0]);
        array[1] = 0;
        array[2] = 0;
        
        if (countText.length() == 3) {    
            array[1] = Integer.parseInt(countStg[1]);
            array[2] = Integer.parseInt(countStg[2]);
        }
        else if (countText.length() == 2) {    
            array[1] = Integer.parseInt(countStg[1]);
        }
        
        System.out.println("FINISHED ARRAY = " + array[0] + array[1] + array[2]);
        return array;
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
