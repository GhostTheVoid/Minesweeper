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
    
    private Player                  player;
    private Status                  status;
    private TimeTracker             timeTracker;
    private Flag                    flag;
    private LinkedList<GridCell>    gridCells;
    private FileHandler             playerData;
    private FileHandler             settingsFile;
    private UserInterface   userInteface;   // Reference to the "view" (user interface)
    private JLabel          statusLabel;    // Reference to the label to update status
    private JPanel          gamePanel;      // Reference to the panel for labels
    
    private GridCell[][] grid;        // A 2D array of JLabel objects
    
    private int rows;               // The number of rows for the matrix
    private int columns;            // The number of columns for the matrix
    
    private final double RATIO = 4.85;      // Ratio of labels
    
    public boolean gameStarted;

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
        LinkedList<String> settings = settingsFile.read();
        setGrid(settings); 
        
        
        // check for saved data
//        LinkedList<String> data = playerData.read();
//        if (data != null) {
//            JOptionPane.showMessageDialog(ui, "Previous score for " +
//                    data.get(0) + " was " + data.get(1) + " points!");
//        }

        timeTracker = new TimeTracker(timerLabels, settings);
        flag        = new Flag(flagLabels, settings);
        status      = new Status(statusLabel, settings);
        
        // set UI properties
        userInteface.getContentPane().setSize(new Dimension(162, 204));
        userInteface.setResizable(false);
        userInteface.setLocationRelativeTo(null);
        userInteface.setVisible(true);
        
        System.out.println("SHOW");
    }
    
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
                gameStarted = true;
                timeTracker.start();
            }
        }
        else if (object.equals("Status")){
            restartGame(evt);
        }
        else if (object.equals("Grid")){
            System.out.println("Grid index " + "ddd" + "was clicked! Was it a bomb?: " + "True");
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
     * When statusLabel is clicked on, envolks this method, 
     * restarting the timer, remaking the board, and creating a small animation.
     * 
     * @param evt the event type
     */
    public void restartGame(MouseEvent evt){
        status.clickLbl(evt);
        timeTracker.stop();
        
        // Check for new game board
        // Create new game board
        generate();
        
        // Reset timer to zero
        
        // Reset Flags to default
    }
    
    public void lostGame(){
        timeTracker.stop();
        gameStarted = false;
    }
    
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {
        player.keypress(event);
    }
    
    
    /**
     * Method connected to the UI button to generate a new matrix
     */
    public void generate() {
        clearGrid(); // Clear the design
        // Calculate the number of bombs based on the size of the matrix
        int numberOfBombs = (int)(((double)(rows * columns)) / RATIO);
        Numbers numbers = new Numbers();    // Class to generate randoms
        int ranRow    = 0;               // Variables for random locations
        int ranCol = 0;
        for (int i = 0; i < numberOfBombs; i++) {   // Traverse all bombs
            do {                                    // Loop while spot occupied
                ranRow = numbers.random(0, rows-1);       // Random row
                ranCol = numbers.random(0, columns-1);    // and column
            } while (grid[ranRow][ranCol].isItBomb());
            grid[ranRow][ranCol].setBomb();
        }
        System.out.println("Rows " + rows + " by Columns " + columns + 
                " with " + (rows * columns) + " cells, generated " + 
                numberOfBombs + " bombs");              // Update status
        countNeighbours();                              // Count all neighbors
    }

    /**
     * Initialize all label objects in the matrix
     */
    private void setGrid(LinkedList<String> settings) {
        // Get the size of the panel to draw inside of, and calculate how many
        // labels (based on their sizes) we can have in each row/column
        rows   = gamePanel.getHeight() / GridCell.getHeight();
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
                createLabel(row, column, x, y, settings);
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
     * @param settings the list of setting values for the images
     */
    private void createLabel(int row, int column, int x, int y, 
                             LinkedList<String> settings) {
        grid[row][column] = new GridCell(new JLabel(), settings);// Create label
        gamePanel.add(grid[row][column].label);    // Add label to panel
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
     * Traverse the matrix and count the number of neighbors that are bombs 
     * around each cell
     */
    private void countNeighbours() {
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                int bombCount = count(row,column);              // Count bombs
                if (bombCount > 0) {                            // Not zero
                    grid[row][column].label.setText("" + bombCount);  // Display
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
    private int count(int row, int column) {
        int count = 0;                              // Start a count
        // Check the row above
        if (row-1 > 0) {                            // Not outside grid
            if (column-1 > 0)  {                    // Not outside grid
                if (grid[row-1][column-1].isItBomb()) {
                    count++;                        // Increase count
                }
            }
            if (grid[row-1][column].isItBomb()) {
                count++;                            // Increase count
            }
            if (column+1 < columns)  {              // Not outside grid
                if (grid[row-1][column+1].isItBomb()) {
                    count++;                        // Increase count
                }
            }      
        }
        // Check the same row        
        if (column-1 > 0)  {                        // Not outside grid
            if (grid[row][column-1].isItBomb()) {
                count++;                            // Increase count
            }
        }
        if (column+1 < columns)  {                  // Not outside grid
            if (grid[row][column+1].isItBomb()) {
                count++;                            // Increase count
            }
        }
        // Check the row below
        if (row+1 < rows) {                         // Not outside grid
            if (column-1 > 0)  {                    // Not outside grid
                if (grid[row+1][column-1].isItBomb()) {
                    count++;                        // Increase count
                }
            }
            if (grid[row+1][column].isItBomb()) {
                count++;                            // Increase count
            }
            if (column+1 < columns)  {              // Not outside grid
                if (grid[row+1][column+1].isItBomb()) {
                    count++;                        // Increase count
                }
            }
        }  
        return count;                               /// Send back final count
    }
}
