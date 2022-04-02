/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.JLabel;

/**
 * FlagTracker.java - Tracks of how many flags the user has left. 
 * With every flag placed on the grid, the counter goes down, 
 * with every removed flag, the counter goes up.
 * 
 -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class FlagTracker extends Counter {
    
    private static int[] count = {0,0,0};
    private static int remainingFlags;

    /**
     * Default constructor, set class properties
     */
    public FlagTracker(LinkedList<JLabel> flagLabels) {
        super(flagLabels);
    }
    
    /**
     * Gets how many flags the user has left 
     * @return 
     */
    public static int getRemainingFlags(){
        return remainingFlags;
    }
    
    /**
     * Raises how many flags the user can place
     */
    public void raiseRemainingFlags(){
        setRemainingFlags(remainingFlags + 1);
    }
    
    /**
     * Lowers how many flags the user can place
     */
    public void lowerRemainingFlags(){
        setRemainingFlags(remainingFlags - 1);
    }
    
    /**
     * Changes the bomb count
     * @param i the new amount to set the bombs
     */
    public void setRemainingFlags(int i){
        remainingFlags = i;
        findCount();
    }
    
    /**
     * Divides up the count and sets the Associated JLabels
     */
    public void findCount(){
        // The counter cannot hold numbers greater than 999
        if (remainingFlags >= 999) setInt(9, 9, 9); 
        else count = getDigits(remainingFlags);
        
        setInt(count[0], count[1], count[2]);
    }
    
    /**
     * Divides up the count and sets the Associated JLabels
     * 
     * @param bombCount How many bombs there will be
     */
    public void findCount(int bombCount){
        remainingFlags = bombCount;
        
        // The counter cannot hold numbers greater than 999
        if (remainingFlags >= 999) setInt(9, 9, 9); 
        else count = getDigits(remainingFlags);
        
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
