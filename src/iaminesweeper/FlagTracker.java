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
public class FlagTracker extends Counter {
    
    private static int[] count = {0,0,0};
    private static int remainingBombs;

    /**
     * Default constructor, set class properties
     */
    public FlagTracker(LinkedList<JLabel> flagLabels) {
        super(flagLabels);
    }
    
    public static int getRemainingBombs(){
        return remainingBombs;
    }
    
    public static void raiseRemainingBombs(){
        setRemainingBombs(remainingBombs + 1);
    }
    
    public static void lowerRemainingBombs(){
        setRemainingBombs(remainingBombs - 1);
    }
    
    /**
     * Changes the bomb count
     * @param i the new amount to set the bombs
     */
    public static void setRemainingBombs(int i){
        remainingBombs = i;
        findCount();
    }
    
    /**
     * Divides up the count and sets the Associated JLabels
     */
    public static void findCount(){
        // The counter cannot hold numbers greater than 999
        if (remainingBombs >= 999) setInt(9, 9, 9); 
        else count = getDigits(remainingBombs);
        
        setInt(count[0], count[1], count[2]);
    }
    
    /**
     * Divides up the count and sets the Associated JLabels
     * 
     * @param bombCount How many bombs there will be
     */
    public static void findCount(int bombCount){
        remainingBombs = bombCount;
        
        // The counter cannot hold numbers greater than 999
        if (remainingBombs >= 999) setInt(9, 9, 9); 
        else count = getDigits(remainingBombs);
        
        setInt(count[0], count[1], count[2]);
    }
    
    
    /**
     * Gets each individual digit of a given number
     * 
     * @param number the number given
     * @return an array, in which index location holds one digit
     */
    private static int[] getDigits(int number) {
        int[] array         = {0,0,0};
        String countText    = "" + number;
        String[] countStg   = countText.split("");
        array[0]            = toInt(countStg[0]);
        
        if (countStg.length >= 2){     // If number has more than 2 digits
            array[0] = toInt(countStg[1]);
            array[1] = toInt(countStg[0]);
            if (countStg.length == 3){ // If number has more than 3 digits
                array[0]   = toInt(countStg[2]);
                array[1]   = toInt(countStg[1]);
                array[2]   = toInt(countStg[0]);
            }
        }
        return array;
    }
    
    /**
     * Turns the String into an int
     * If numTxt's value is above 0, if so, parses the String into an
     * integer. If equal to 0, returns a 0.
     * 
     * @param numTxt the number, contained as a string
     * @return The given number returned as an Int
     */
    private static int toInt(String numTxt){
        int num = 0; // Stays 0 if numtext is 0
        if (Integer.parseInt(numTxt) > 0) num = Integer.parseInt(numTxt);
        return num;
    } 
}
