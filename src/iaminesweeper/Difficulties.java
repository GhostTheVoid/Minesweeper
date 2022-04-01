/** Required package class namespace */
package iaminesweeper;
 
import java.awt.Dimension;

/**
 * Difficulties.java - Tracks how many bombs, rows, and columns should be placed
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 21-Mar-2022
 */
public class Difficulties {
    
    public static int bombCount     = 10;
    public static int rowCount      = 9;
    public static int columnCount   = 9;

    private final static int BEG_BOMB_COUNT     = 10;
    private final static int BEG_ROW_COUNT      = 9;
    private final static int BEG_COLUMN_COUNT   = 9;
    
    private final static int MED_BOMB_COUNT     = 40;
    private final static int MED_ROW_COUNT      = 16;
    private final static int MED_COLUMN_COUNT   = 16;
    
    private final static int EX_BOMB_COUNT     = 99;
    private final static int EX_ROW_COUNT      = 30;
    private final static int EX_COLUMN_COUNT   = 16;
    
    /**
     * Gets how many bombs should be placed on generation
     * @return the bomb amount
     */
    public static int getBombCount(){
        return bombCount;
    }
    
    /**
     * Gets how many rows should be made on generation
     * @return the row amount
     */
    public static int getRowCount(){
        return rowCount;
    }
    
    /**
     * Gets how many columns should be made on generation
     * @return the columns amount
     */
    public static int getColumnCount(){
        return columnCount;
    }
    
    /**
     * Sets the difficulty (Row, Column, BombCount) 
     * that will be used in the next game
     * 
     * @param input which difficulty will be selected
     */
    public static void setDifficulty(int input){
        switch(input) {
            default:
                System.out.println("EASY SET");
                bombCount   = BEG_BOMB_COUNT;
                rowCount    = BEG_ROW_COUNT;
                columnCount = BEG_COLUMN_COUNT;
                break;
            case 1:
                System.out.println("MED SET");
                bombCount     = MED_BOMB_COUNT;
                rowCount      = MED_ROW_COUNT;
                columnCount   = MED_COLUMN_COUNT;
              break;
            case 2:
                System.out.println("HARD SET");
                bombCount     = EX_BOMB_COUNT;
                rowCount      = EX_ROW_COUNT;
                columnCount   = EX_COLUMN_COUNT;
              break;
          }
    }
}
