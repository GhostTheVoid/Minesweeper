package iaminesweeper;

import collections.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tools.Numbers;

/**
 * GameGrid.java - Sets up and manages the game's playing grid
 *
 * @author Marissa Rowles
 * @since Mar. 30, 2022
 * @teacher Mr. Wachs
 */
public class GameGrid {
    
    private static GridCell[][]  grid;      // A 2D array of GridCell objects
    private static int rows, columns; // The number of rows & columns for the matrix
    private final JPanel GAME_PANEL; // Reference to the panel for labels
    
    public FlagTracker flagTracker;

    GameGrid(JPanel gamePanel) {
        this.GAME_PANEL = gamePanel;
    }
    
    GameGrid(JPanel gamePanel, FlagTracker flagTracker) {
        this.GAME_PANEL  = gamePanel;
        this.flagTracker = flagTracker;
    }
    
    public static void lostGame(){
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                grid[row][column].showBomb();
                grid[row][column].setClickable(false);
            }
        }  
    }
    
    /**
     * prepares and generates a new grid
     */
    public void generateNewGrid(){
        Difficulties.setDifficulty(0);
        setGrid();
    }
    
    /**
     * Sets the difficulty and adjusts the frame accordingly
     */
    public void prepareGrid(){
        Difficulties.setDifficulty(0);
    }
    
    /**
     * Method connected to the UI button to generate a new matrix
     */
    public void generate() {
        clearGrid(); // Clear the design
        // Calculate the number of bombs based on the size of the matrix
        //int numberOfBombs = (int)(((double)(rows * columns)) / Globals.CELL_RATIO);
        int numberOfBombs = 10; //TEMPORARY
        Numbers numbers = new Numbers();    // Class to generate randoms
        int ranRow = 0;               // Variables for random locations
        int ranCol = 0;
        int bombCount = 0;
        for (int i = 0; i < numberOfBombs; i++) {   // Traverse all bombs
            do {                                    // Loop while spot occupied
                ranRow = numbers.random(0, rows-1);       // Random row
                ranCol = numbers.random(0, columns-1);    // and column
            } while (grid[ranRow][ranCol].getBomb());
            grid[ranRow][ranCol].setBomb();
            bombCount++;
        }
        System.out.println("Rows " + rows + " by Columns " + columns + 
                " with " + (rows * columns) + " cells, generated " + 
                bombCount + " bombs");              // Update status
        countNeighbours();                              // Count all neighbors
        Globals.flagTracker.findCount(); // Find how many bombs will be in the grid
    }

    /**
     * Initialize all label objects in the matrix
     */
    private void setGrid() {
        // Get the size of the panel to draw inside of, and calculate how many
        // labels (based on their sizes) we can have in each row/column
        rows    = Difficulties.rowCount;
        columns = Difficulties.columnCount;
        // Instantiate the matrix
        grid = new GridCell[rows][columns];
        // Now loop through and build all the labels each at a (x,y) location
        int y = 0;                                      
        // Traverse all the rows
        for (int row = 0; row < grid.length; row++) {
            int x = 0;
            // Traverse all the columns
            for (int column = 0; column < grid[row].length; column++) {
                createLabel(row, column, x, y);
                // Move x location past this label
                x += GridCell.getWidth();
            }
            // Move y location past this row for the next row
            y += GridCell.getHeight();
        } 
    }
    
    /**
     * Creates a label object at this location in the matrix on the panel
     * of the passed size
     * 
     * @param row the row in the matrix for the label
     * @param column the column in the matrix for the label
     * @param x the x coordinate to draw the label in the panel
     * @param y the y coordinate to draw the label in the panel 
     */
    private void createLabel(int row, int column, int x, int y) {
        grid[row][column] = new GridCell(new JLabel(), row, column); // Create cell
        GAME_PANEL.add(grid[row][column].label);    // Add label to panel
        grid[row][column].makeLabel(x+2, y+2);
        
    }
    
    /**
     * Clears the label matrix for a new generation
     */
    private void clearGrid() {
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                grid[row][column].unclickCell();
            }
        }
    }
    
    /**
     * When the user clicks on a spot of the grid, react
     */
    public static void mouseClick(int row, int column) {
        String text = "Clicked cell (" + row + ", " + column + ")";
        if (grid[row][column].getBomb()) {    // Bomb spot
            grid[row][column].revealCell();
            text += " found bomb!";
        }
        else if (grid[row][column].reveal() == false) {
            // If the spot is a number, just reveal that spot
            grid[row][column].revealCell();
            text += " found number!";
        }
        else {
            // spot is a blank, reveal all spots around it
            grid[row][column].revealCell();
            text += " found blank, revealing all blanks!";
            boolean[][] checked = new boolean[rows][columns];
            revealCheckBomb(row, column, checked);
        }
        System.out.println(text);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Middle Click"> 
    
    public static void showNeighbours(int row, int column){
        if (grid[row][column].isCellFlagged()) {    // Flag spot
            boolean[][] checked = new boolean[rows][columns];
            revealBorder(row, column, checked);
        }
        else {
            // spot is a blank, reveal all spots around it
            grid[row][column].revealCell();
            boolean[][] checked = new boolean[rows][columns];
            revealBorder(row, column, checked);
        }
    }
    
    public static void hideNeighbours(int row, int column){
        if (grid[row][column].isCellFlagged()) {    // Flag spot
            boolean[][] checked = new boolean[rows][columns];
            revealBorder(row, column, checked);
        }
        else {
            // spot is a blank, reveal all spots around it
            grid[row][column].revealCell();
            boolean[][] checked = new boolean[rows][columns];
            revealBorder(row, column, checked);
        }
    }
    
    /**
     * Reveals the spots around this location (row,column) as number spots
     * or blank spots
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void revealBorder(int row, int column, boolean[][] checked) {
        // spots to check      coordinates of those spots
        // +---+---+---+    +---------+---------+---------+
        // | 1 | 2 | 3 |    | r-1,c-1 | r-1,c   | r-1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 4 | X | 5 |    | r  ,c-1 | r  ,c   | r  ,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 6 | 7 | 8 |    | r+1,c-1 | r+1,c   | r+1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        
        pressBorder(row-1,column-1,checked);      // Check spot #1
        pressBorder(row-1,column  ,checked);      // Check spot #2
        pressBorder(row-1,column+1,checked);      // Check spot #3
        pressBorder(row  ,column-1,checked);      // Check spot #4
        // DO NOT CHECK THE SAME SPOT WE ARE ON (row,column) ....
        pressBorder(row  ,column+1,checked);      // Check spot #5
        pressBorder(row+1,column-1,checked);      // Check spot #6
        pressBorder(row+1,column  ,checked);      // Check spot #7
        pressBorder(row+1,column+1,checked);      // Check spot #8
    }
    
    /**
     * Checks this spot (row,column) to make sure it is in bounds, and then
     * "presses" down on the cell if it is not flagged
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void pressBorder(int row, int column, boolean[][] checked) {    
        // First make sure we are not out of bounds in the spot to check
        if (rowIsInBounds(row) && columnIsInBounds(column)) {
            if (checked[row][column] == true) {     // Already checked here
                return;                             // Leave method
            }       
            else {                                  // Have not checked here
                checked[row][column] = true;        // Mark spot as checked
            }
            
            if (!grid[row][column].isCellFlagged()) {  // If spot is not flagged
                grid[row][column].showCell();
            }
        }
    }
    
    /**
     * Reveals the spots around this location (row,column) as number spots
     * or blank spots
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void hiBorder(int row, int column, boolean[][] checked) {
        // spots to check      coordinates of those spots
        // +---+---+---+    +---------+---------+---------+
        // | 1 | 2 | 3 |    | r-1,c-1 | r-1,c   | r-1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 4 | X | 5 |    | r  ,c-1 | r  ,c   | r  ,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 6 | 7 | 8 |    | r+1,c-1 | r+1,c   | r+1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        
        hideBorder(row-1,column-1,checked);      // Check spot #1
        hideBorder(row-1,column  ,checked);      // Check spot #2
        hideBorder(row-1,column+1,checked);      // Check spot #3
        hideBorder(row  ,column-1,checked);      // Check spot #4
        // DO NOT CHECK THE SAME SPOT WE ARE ON (row,column) ....
        hideBorder(row  ,column+1,checked);      // Check spot #5
        hideBorder(row+1,column-1,checked);      // Check spot #6
        hideBorder(row+1,column  ,checked);      // Check spot #7
        hideBorder(row+1,column+1,checked);      // Check spot #8
    }
    
    /**
     * Checks this spot (row,column) to make sure it is in bounds, and then
     * "presses" down on the cell if it is not flagged
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void hideBorder(int row, int column, boolean[][] checked) {    
        // First make sure we are not out of bounds in the spot to check
        if (rowIsInBounds(row) && columnIsInBounds(column)) {
            if (checked[row][column] == true) {     // Already checked here
                return;                             // Leave method
            }       
            else {                                  // Have not checked here
                checked[row][column] = true;        // Mark spot as checked
            }
            
            if (!grid[row][column].isCellFlagged()) {  // If spot is not flagged
                if (grid[row][column].neighboursFlagged)
                grid[row][column].showBlank();
            }
        }
    }
    
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Bomb Neighbours"> 
    
    /**
     * Traverse the matrix and count the number of neighbors that are bombs 
     * around each cell
     */
    private void countNeighbours() {
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                // Count spots of cells next to bombs, but not the bomb spots
                if (!grid[row][column].getBomb()) {
                    int bombCount = count(row,column);            // Count bombs
                    if (bombCount > 0) {                          // Not zero
                        grid[row][column].setNeighbours(bombCount);
                    }
                }
            }
        }
    }

    /**
     * Reveals the spots around this location (row,column) as number spots
     * or blank spots
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void revealCheckBomb(int row, int column, boolean[][] checked) {
        // spots to check      coordinates of those spots
        // +---+---+---+    +---------+---------+---------+
        // | 1 | 2 | 3 |    | r-1,c-1 | r-1,c   | r-1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 4 | X | 5 |    | r  ,c-1 | r  ,c   | r  ,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 6 | 7 | 8 |    | r+1,c-1 | r+1,c   | r+1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        
        checkBomb(row-1,column-1,checked);      // Check spot #1
        checkBomb(row-1,column  ,checked);      // Check spot #2
        checkBomb(row-1,column+1,checked);      // Check spot #3
        checkBomb(row  ,column-1,checked);      // Check spot #4
        // DO NOT CHECK THE SAME SPOT WE ARE ON (row,column) ....
        checkBomb(row  ,column+1,checked);      // Check spot #5
        checkBomb(row+1,column-1,checked);      // Check spot #6
        checkBomb(row+1,column  ,checked);      // Check spot #7
        checkBomb(row+1,column+1,checked);      // Check spot #8
    }
    
    /**
     * Checks this spot (row,column) to make sure it is in bounds, and then
     * reveals what is at this location (a number, bomb, or blank) and 
     * recursively checks the other blank spots around it
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void checkBomb(int row, int column, boolean[][] checked) {    
        // First make sure we are not out of bounds in the spot to check
        if (rowIsInBounds(row) && columnIsInBounds(column)) {
            if (checked[row][column] == true) {     // Already checked here
                return;                             // Leave method
            }       
            else {                                  // Have not checked here
                checked[row][column] = true;        // Mark spot as checked
            }
            if (grid[row][column].reveal() == true) {  // If spot is a space
                revealCheckBomb(row,column,checked);   // Check spots around it
            }
        }
    }
    // </editor-fold> 
    
    /**
     * Counts the number of neighbors that are bombs around this cell
     * 
     * @param row the row in the matrix for the label
     * @param column the column in the matrix for the label
     * @return the count of bombs around this cell
     */
    private static int count(int row, int column) {
        // spots to check      coordinates of those spots
        // +---+---+---+    +---------+---------+---------+
        // | 1 | 2 | 3 |    | r-1,c-1 | r-1,c   | r-1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 4 | X | 5 |    | r  ,c-1 | r  ,c   | r  ,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 6 | 7 | 8 |    | r+1,c-1 | r+1,c   | r+1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        
        int r     = row;                            // Temporary variables for
        int c     = column;                         // row and column
        int count = 0;                              // Start a count
        r = row - 1;                                // Check row above..........
        if (rowIsInBounds(r)) {                     // Not outside grid
            c = column - 1;                         // Check spot #1
            if (columnIsInBounds(c)) {              // Not outside grid
                if (grid[r][c].getBomb()) count++; 
            }
            c = column;                             // Check spot #2
            if (grid[r][c].getBomb()) count++; 
            c = column + 1;                         // Check spot #3
            if (columnIsInBounds(c)) {              // Not outside grid
                if (grid[r][c].getBomb()) count++;  
            }
        }
        r = row;                                    // Check the same row.......
        c = column - 1;                             // Check spot #4
        if (columnIsInBounds(c))  {                 // Not outside grid
            if (grid[r][c].getBomb()) count++;  
        }
        c = column + 1;                             // Check spot #5
        if (columnIsInBounds(c))  {                 // Not outside grid
            if (grid[r][c].getBomb()) count++;  
        }
        r = row + 1;                                // Check row below..........
        if (rowIsInBounds(r)) {                     // Not outside grid
            c = column - 1;                         // Check spot #6
            if (columnIsInBounds(c)) {              // Not outside grid
                if (grid[r][c].getBomb()) count++; 
            }
            c = column;                             // Check spot #7
            if (grid[r][c].getBomb()) count++;  
            c = column + 1;                         // Check spot #8
            if (columnIsInBounds(c)) {              // Not outside grid
                if (grid[r][c].getBomb()) count++;  
            }
        }
        return count;                               // Send back final count
    }

    /**
     * Checks the passed row to see if it is inside the bounds of the matrix
     * 
     * @param row the row in the matrix to check
     * @return the row is in bounds (true) or out of bounds (false)
     */
    private static boolean rowIsInBounds(int row) {
        if (row <  0)    return false;              // Before first row
        if (row >= rows) return false;              // After last row
        return true;                                // Valid row
    }
    
    /**
     * Checks the passed column to see if it is inside the bounds of the matrix
     * 
     * @param column the column in the matrix to check
     * @return the column is in bounds (true) or out of bounds (false)
     */
    private static boolean columnIsInBounds(int column) {
        if (column <  0)       return false;            // Before first column
        if (column >= columns) return false;            // After last column
        return true;                                    // Valid row
    }
}
