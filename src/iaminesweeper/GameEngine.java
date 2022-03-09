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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    
    private int rows, columns;
    
    public boolean gameStarted;
    
    private JLabel[][] matrix;          // the 2D array of JLabel objects
    private final int WIDTH  = 6;
    private final int HEIGHT = WIDTH;   // the set sizes of the labels
    
    

    /**
     * Default constructor, set class properties
     */
    public GameEngine(JLabel statusLabel, JPanel gamePanel,
            LinkedList<JLabel> timerLabels, LinkedList<JLabel> flagLabels, 
            UserInterface ui) {
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
        ui.getContentPane().setSize(new Dimension(162, 204));
        ui.setResizable(false);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
        
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
    
    public void generate(){
        
    }
    
    /**
     * Sets the frame user interface properties
     * 
     * @param userInterface the user interface frame 
     * @param panel the panel to draw labels inside of
     */
    private void setFrame(UserInterface userInterface, JPanel panel) {
        // get the size of the panel to draw inside of, and adjust the frame 
        // size to match that with a few pixels as a buffer
        int frameWidth  = panel.getWidth()  + 40;
        int frameHeight = panel.getHeight() + 60;
        // set the frame look and feel
        userInterface.setSize(frameWidth, frameHeight);
        userInterface.setResizable(false);
        userInterface.setLocationRelativeTo(null);
    }
    
    /**
     * Initialize all objects in the solution
     */
    private void setGrid(JPanel panel) {        
        // calculate how many rows and columns we can draw of that size
        int rows   = panel.getHeight() / HEIGHT;
        int colums = panel.getWidth()  / WIDTH;
        // instantiate the matrix
        matrix = new JLabel[rows][colums];
        // now loop through and build all the labels
        int y = 0;
        // traverse all the rows
        for (int row = 0; row < matrix.length; row++) {
            int x = 0;
            // traverse all the columns
            for (int column = 0; column < matrix[row].length; column++) {
                createLabel(matrix, row, column, x, y, WIDTH, HEIGHT, panel);
                // move x location past this label
                x += WIDTH;
            }
            // move y location past this row for the next row
            y += HEIGHT;
        }        
    }

    /**
     * Creates a label object at this location in the matrix on the panel
     * of the passed size
     * 
     * @param matrix the matrix to add the label to
     * @param row the row in the matrix for the label
     * @param column the column in the matrix for the label
     * @param x the x coordinate to draw the label in the panel
     * @param y the y coordinate to draw the label in the panel
     * @param width the width to draw the label to
     * @param height the height to draw the label to 
     * @param panel the panel to draw labels inside of
     */
    private void createLabel(JLabel[][] matrix, int row, int column, 
                             int x, int y, int width, 
                             int height, JPanel panel) {
        matrix[row][column] = new JLabel();             // create label
        panel.add(matrix[row][column]);                 // add label to panel
        matrix[row][column].setOpaque(true);            // make color fillable
        matrix[row][column].setBackground(Color.white); // starting color
        matrix[row][column].addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mouseClick(e, "Cell");          // mouse click event
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
        matrix[row][column].setBounds(x, y, width, height); // position label
    }
}
