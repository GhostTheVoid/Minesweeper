/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import tools.FileHandler;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    private Timer                   timer;
    private Flag                    flag;
    private LinkedList<GridCell>    gridCells;
    private FileHandler          playerData;
    private FileHandler          settingsFile;

    /**
     * Default constructor, set class properties
     */
    public GameEngine(JLabel statusLabel, LinkedList<JLabel> cellLabels,
            LinkedList<JLabel> timerLabels, LinkedList<JLabel> flagLabels, 
            UserInterface ui) {
        playerData   = new FileHandler(Constants.PLAYER_DATA_FILE);
        settingsFile = new FileHandler(Constants.SETTINGS_DATA_FILE); 
        //check for game settings
        LinkedList<String> settings = settingsFile.read();
        
        gridCells = new LinkedList<>();
        
        // check for saved data
        LinkedList<String> data = playerData.read();
        if (data != null) {
            JOptionPane.showMessageDialog(ui, "Previous score for " +
                    data.get(0) + " was " + data.get(1) + " points!");
        }
        // set UI properties
        //ui.setSize(Constants.UI_WIDTH,Constants.UI_HEIGHT);
        ui.setResizable(false);
        ui.setLocationRelativeTo(null);
        ui.setVisible(true);
    }
    
    /**
     * The user's keyboard event of pressing a key to respond to
     * 
     * @param event the keyboard event registered
     */
    public void keypress(KeyEvent event) {
        player.keypress(event);
    }
     
    
    
    /** User has reset the game (clicked on the status button) */
    private void resetGame() {
        // generate new map
        // If user selected a new size, game will reload with new grid
        // Set All to blank
    }
}
