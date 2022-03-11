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
 * GameEngine.java -  the logic connected to the user interface that runs game 
 * logic
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
    
    
    private JLabel[][] grid;        // A 2D array of JLabel objects
    
    private int rows;               // The number of rows for the matrix
    private int columns;            // The number of columns for the matrix
    
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
        setGrid(); 
        
        
        
        playerData   = new FileHandler(Constants.PLAYER_DATA_FILE);
        settingsFile = new FileHandler(Constants.SETTINGS_DATA_FILE); 
        //check for game settings
        LinkedList<String> settings = settingsFile.read();
        
        
        
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
        clearGrid();                                    // Clear the design
        // Calculate the number of bombs based on the size of the matrix
        int numberOfBombs = (int)(((double)(rows * columns)) / RATIO);
        Numbers numbers = new Numbers();    // Class to generate randoms
        int randomRow    = 0;               // Variables for random locations
        int randomColumn = 0;
        for (int i = 0; i < numberOfBombs; i++) {   // Traverse all bombs
            do {                                    // Loop while spot occupied
                randomRow    = numbers.random(0, rows-1);       // Random row
                randomColumn = numbers.random(0, columns-1);    // and column
            } while (grid[randomRow][randomColumn].getText().equals(BOMB));
            grid[randomRow][randomColumn].setText(BOMB);     // Set bomb spot
            grid[randomRow][randomColumn].setBackground(CELL_BOMB);
        }
        statusLabel.setText("Rows " + rows + " by Columns " + columns + 
                " with " + (rows * columns) + " cells, generated " + 
                numberOfBombs + " bombs");              // Update status
        countNeighbours();                              // Count all neighbors
    }

    /**
     * Initialize all label objects in the matrix
     */
    private void setGrid() {
        // Get the size of the panel to draw inside of, and calculate how many
        // labels (based on their sizes) we can have in each row/column
        rows   = gamePanel.getHeight() / HEIGHT;
        columns = gamePanel.getWidth()  / WIDTH;
        // Instantiate the matrix
        grid = new JLabel[rows][columns];
        // Now loop through and build all the labels each at a (x,y) location
        int y = 0;                                      
        // Traverse all the rows
        for (int row = 0; row < grid.length; row++) {
            int x = 0;
            // Traverse all the columns
            for (int column = 0; column < grid[row].length; column++) {
                createLabel(row, column, x, y);
                // Move x location past this label
                x += WIDTH;
            }
            // Move y location past this row for the next row
            y += HEIGHT;
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
        grid[row][column] = new JLabel();             // Create label
        gamePanel.add(grid[row][column]);             // Add label to panel
        grid[row][column].setOpaque(true);            // Make color fillable
        grid[row][column].setBackground(CELL_BACKGROUND); // Starting color
        grid[row][column].setHorizontalAlignment(CENTER); // Align text
        grid[row][column].setBorder(BorderFactory.createLineBorder(
                CELL_BORDER, 1));                           // Label border
        grid[row][column].addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mouseClick(row, column);                // Mouse click event
            }
            public void mousePressed(MouseEvent e)  { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e)  { }
            public void mouseExited(MouseEvent e)   { }
        });
        grid[row][column].setBounds(x, y, WIDTH, HEIGHT); // Position label
    }
    
    /**
     * Clears the label matrix for a new generation
     */
    private void clearGrid() {
        for (int row = 0; row < rows; row++) {                  // Traverse rows
            for (int column = 0; column < columns; column++) {  // and columns
                grid[row][column].setText("");                  // Remove text
                grid[row][column].setBackground(CELL_BACKGROUND);   // Color
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
                    grid[row][column].setText("" + bombCount);  // Display
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
                if (grid[row-1][column-1].getText().equals(BOMB)) {
                    count++;                        // Increase count
                }
            }
            if (grid[row-1][column].getText().equals(BOMB)) {
                count++;                            // Increase count
            }
            if (column+1 < columns)  {              // Not outside grid
                if (grid[row-1][column+1].getText().equals(BOMB)) {
                    count++;                        // Increase count
                }
            }      
        }
        // Check the same row        
        if (column-1 > 0)  {                        // Not outside grid
            if (grid[row][column-1].getText().equals(BOMB)) {
                count++;                            // Increase count
            }
        }
        if (column+1 < columns)  {                  // Not outside grid
            if (grid[row][column+1].getText().equals(BOMB)) {
                count++;                            // Increase count
            }
        }
        // Check the row below
        if (row+1 < rows) {                         // Not outside grid
            if (column-1 > 0)  {                    // Not outside grid
                if (grid[row+1][column-1].getText().equals(BOMB)) {
                    count++;                        // Increase count
                }
            }
            if (grid[row+1][column].getText().equals(BOMB)) {
                count++;                            // Increase count
            }
            if (column+1 < columns)  {              // Not outside grid
                if (grid[row+1][column+1].getText().equals(BOMB)) {
                    count++;                        // Increase count
                }
            }
        }  
        return count;                               /// Send back final count
    }
}
