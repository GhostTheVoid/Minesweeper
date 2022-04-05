/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.Desktop;
import java.awt.Dimension;
import tools.FileHandler;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    private static ResetButton resetButton;
    private TimeTracker timeTracker;
    private FlagTracker flagTracker;
    // LABELS & PANELS
    private UserInterface   userInteface;   // Reference to the "view" (user interface)
    private JLabel          statusLabel;    // Reference to the label to update status
    private JPanel          gamePanel;      // Reference to the panel for labels
    // FILES
    private FileHandler             playerData;
    private FileHandler             settingsFile;
    
    
    
    public static boolean gameStarted; // Determines if the game is running or not

    /**
     * Constructor for the class, sets class property data
     * 
     * @param statusLabel the label image for the corresponding object
     * @param timerLabels the label image list for the corresponding object
     * @param flagLabels  the label image list for the corresponding object
     * @param gamePanel   the panel which contains the grid for the game
     * @param userInteface the user interface container for the game 
     */
    public GameEngine(JLabel statusLabel, 
                      LinkedList<JLabel> timerLabels, 
                      LinkedList<JLabel> flagLabels, 
                      JPanel gamePanel, 
                      UserInterface userInteface) {
        
        this.userInteface   = userInteface;     // Connect this class (engine)
        this.statusLabel    = statusLabel;      // or "form" references
        this.gamePanel      = gamePanel;
        
        playerData   = new FileHandler(Globals.PLAYER_DATA_FILE);
        settingsFile = new FileHandler(Globals.SETTINGS_DATA_FILE); 
        //check for game settings
        Globals.settings = settingsFile.read();
        
        // check for saved data
//        LinkedList<String> data = playerData.read();
//        if (data != null) {
//            JOptionPane.showMessageDialog(ui, "Previous score for " +
//                    data.get(0) + " was " + data.get(1) + " points!");
//        }
        Globals.timeTracker = new TimeTracker(timerLabels);
        Globals.flagTracker = new FlagTracker(flagLabels);
        Globals.resetButton = new ResetButton(statusLabel);
        Globals.gameGrid    = new GameGrid(gamePanel, timeTracker, flagTracker, resetButton);
        
        newGame();
        
        // set UI properties
        userInteface.getContentPane().setSize(new Dimension(162, 204));
        userInteface.setResizable(false);
        userInteface.setLocationRelativeTo(null);
        userInteface.setVisible(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Game States"> 
    
    public void newGame(){
        Globals.gameGrid.generateNewGrid(); 
        Globals.flagTracker.setRemainingFlags(Difficulties.bombCount);
        Globals.gameGrid.generate();
    }
    
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
            Globals.timeTracker.start();
        } 
    }
    
    /**
     * When statusLabel is clicked on, envolks this method, 
     * restarting the timer, remaking the board, and creating a small animation.
     * 
     * @param evt the event type
     */
    public void restartGame(MouseEvent evt){
        Globals.resetButton.clickStatus(evt);
        Globals.timeTracker.stop(); // Stop the timer
        gameStarted=false;
        // Check for new game board
        // Create new game board
        newGame();
        
        // Reset timer to zero
        
        // Reset Flags to default
    }
    
    
    public void lostGame(){
        Globals.timeTracker.stop();
        Globals.resetButton.animate(4);
        gameStarted = false;
        Globals.gameGrid.lostGame(); 
    }
    
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Events"> 
    /**
     * Evokes an action if the user clicks their mouse
     * 
     * @param evt       The MouseEvent evoked
     * @param object    The name of the object put through
     */
    public void mouseClick(MouseEvent evt, String object){
        //If on grid
        if (object.equals("Grid")){
            if (!gameStarted){
                startGame();
            }
        }
        else if (object.equals("Status")){
            restartGame(evt);
        }
    }
    
    /**
     * Opens a webpage in the default browser
     * 
     * @return If the file opening was successful, return true. If unsuccessful,
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
        //player.keypress(event);
    }
    
    // </editor-fold>  
}
