/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tools.FileHandler;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import tools.Numbers;

/**
 * GameEngine.java -  this class runs all the logic for this game. It received
 * references from the other class to modify those objects from this class.
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class GameEngine {
    
    // GAME OBJECTS
    private Player                  player;
    private static Status           status;
    private static TimeTracker      timeTracker;
    private static GridCell[][] grid;        // A 2D array of GridCell objects
    private FlagTracker             flagTracker;
    private LinkedList<GridCell>    gridCells;
    // LABELS & PANELS
    private UserInterface   userInteface;   // Reference to the "view" (user interface)
    private JLabel          statusLabel;    // Reference to the label to update status
    private JPanel          gamePanel;      // Reference to the panel for labels
    // FILES
    private FileHandler             playerData;
    private FileHandler             settingsFile;
    
    
    
    private String[][] matrix;      // A 2D array to store values for the labels
    
    private static int rows;               // The number of rows for the matrix
    private static int columns;            // The number of columns for the matrix
    
    public static boolean gameStarted; // Determines if the game is running or not

    /**
     * Default constructor, set class properties
     */
    public GameEngine(JLabel statusLabel, JPanel gamePanel,
            LinkedList<JLabel> timerLabels, LinkedList<JLabel> flagLabels, 
            UserInterface userInteface) {
        
        this.userInteface   = userInteface;     // Connect this class (engine)
        this.statusLabel    = statusLabel;      // or "form" references
        this.gamePanel      = gamePanel;
        
        playerData   = new FileHandler(Constants.PLAYER_DATA_FILE);
        settingsFile = new FileHandler(Constants.SETTINGS_DATA_FILE); 
        //check for game settings
        Constants.settings = settingsFile.read();
        setGrid(); 
        
        
        // check for saved data
//        LinkedList<String> data = playerData.read();
//        if (data != null) {
//            JOptionPane.showMessageDialog(ui, "Previous score for " +
//                    data.get(0) + " was " + data.get(1) + " points!");
//        }

        timeTracker = new TimeTracker(timerLabels);
        flagTracker = new FlagTracker(flagLabels);
        status      = new Status(statusLabel);
        
        generate();
        
        // set UI properties
        userInteface.getContentPane().setSize(new Dimension(162, 204));
        userInteface.setResizable(false);
        userInteface.setLocationRelativeTo(null);
        userInteface.setVisible(true);
        
        
        
        System.out.println("SHOW");
    }
    
    // <editor-fold defaultstate="collapsed" desc="Game States"> 
    
    /**
     * On the first click
     */
    public static void startGame(){
        if (gameStarted) {
            System.out.println("Game already running!");
        }
        else {
            System.out.println("Game has started!");
            gameStarted = true;
            timeTracker.start();
        }
        
    }
    
    /**
     * When statusLabel is clicked on, envolks this method, 
     * restarting the timer, remaking the board, and creating a small animation.
     * 
     * @param evt the event type
     */
    public void restartGame(MouseEvent evt){
        status.clickStatus(evt);
        timeTracker.stop();
        
        // Check for new game board
        // Create new game board
        setGrid();
        generate();
        
        // Reset timer to zero
        
        // Reset Flags to default
    }
    
    
    public static void lostGame(){
        timeTracker.stop();
        gameStarted = false;
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                grid[row][column].showBomb();
                grid[row][column].setClickable(false);
            }
        }  
    }
    
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Events"> 
    /**
     * Envolks an action if the user clicks their mouse
     * 
     * @param evt       The MouseEvent envoked
     * @param object    The name of the object put through
     */
    public void mouseClick(MouseEvent evt, String object){
        //If on grid
        if (object.equals("Grid")){
            if (!gameStarted){
                startGame();
            }
            System.out.println("Grid index " + "ddd" + "was clicked! Was it a bomb?: " + "True");
        }
        else if (object.equals("Status")){
            restartGame(evt);
        }
    }
    
    /**
     * Opens a webpage in the default browser
     * 
     * @return If the file opening was sucessful, return true. If unsucessful,
     *          return false.
     */
    public boolean openURI(String uri){
        Desktop desktop = Desktop.isDesktopSupported()?Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(uri));
                System.out.println("File Opening Sucessful!");
                return true;
            } catch (Exception e) {
                System.out.println("File Opening Unsucsessful: " + e.toString());
            }
        }
        return false;
    }
    
    
    
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {
        player.keypress(event);
    }
    
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Grid Cells"> 
    /**
     * Method connected to the UI button to generate a new matrix
     */
    public void generate() {
        clearGrid(); // Clear the design
        // Calculate the number of bombs based on the size of the matrix
        //int numberOfBombs = (int)(((double)(rows * columns)) / Constants.CELL_RATIO);
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
    }

    /**
     * Initialize all label objects in the matrix
     */
    private void setGrid() {
        // Get the size of the panel to draw inside of, and calculate how many
        // labels (based on their sizes) we can have in each row/column
        rows    = gamePanel.getHeight() / GridCell.getHeight();
        columns = gamePanel.getWidth()  / GridCell.getWidth();
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
        gamePanel.add(grid[row][column].label);    // Add label to panel
        grid[row][column].makeLabel(x+2, y+2);
        
    }
    
    /**
     * Clears the label matrix for a new generation
     */
    private void clearGrid() {
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                grid[row][column].clearCell();
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
            revealCheck(row, column, checked);
        }
        System.out.println(text);
    }
    
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
     * Reveals the spots around this location (row,column) as number spots
     * or blank spots
     * 
     * @param row the row in the matrix to check
     * @param column the column in the matrix to check
     * @param checked the matrix of flagged spots to check or not
     */
    private static void revealCheck(int row, int column, boolean[][] checked) {
        // spots to check      coordinates of those spots
        // +---+---+---+    +---------+---------+---------+
        // | 1 | 2 | 3 |    | r-1,c-1 | r-1,c   | r-1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 4 | X | 5 |    | r  ,c-1 | r  ,c   | r  ,c+1 |
        // +---+---+---+    +---------+---------+---------+
        // | 6 | 7 | 8 |    | r+1,c-1 | r+1,c   | r+1,c+1 |
        // +---+---+---+    +---------+---------+---------+
        
        check(row-1,column-1,checked);      // Check spot #1
        check(row-1,column  ,checked);      // Check spot #2
        check(row-1,column+1,checked);      // Check spot #3
        check(row  ,column-1,checked);      // Check spot #4
        // DO NOT CHECK THE SAME SPOT WE ARE ON (row,column) ....
        check(row  ,column+1,checked);      // Check spot #5
        check(row+1,column-1,checked);      // Check spot #6
        check(row+1,column  ,checked);      // Check spot #7
        check(row+1,column+1,checked);      // Check spot #8
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
    private static void check(int row, int column, boolean[][] checked) {    
        // First make sure we are not out of bounds in the spot to check
        if (rowIsInBounds(row) && columnIsInBounds(column)) {
            if (checked[row][column] == true) {     // Already checked here
                return;                             // Leave method
            }       
            else {                                  // Have not checked here
                checked[row][column] = true;        // Mark spot as checked
            }
            if (grid[row][column].reveal() == true) {  // If spot is a space
                revealCheck(row,column,checked);       // Check spots around it
            }
        }
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
    
    // </editor-fold>  
}
