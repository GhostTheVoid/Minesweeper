/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tools.FileHandler;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.JButton;
import javax.swing.JLabel;

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
    private FileHandler          playerData;
    private FileHandler          settingsFile;
    
    private int rows, columns;
    
    public boolean gameStarted;
    
    

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
//        LinkedList<String> data = playerData.read();
//        if (data != null) {
//            JOptionPane.showMessageDialog(ui, "Previous score for " +
//                    data.get(0) + " was " + data.get(1) + " points!");
//        }

        for (int i = 0; i < cellLabels.size(); i++) {
            gridCells.add(new GridCell(cellLabels.get(i), settings));
        }
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
    
//    /** Player has lost the game (clicked a bomb) */
//    private void loseGame() {
//        mover.stop();                                   // stop pacman
//        for (int i = 0; i < gridCells.size(); i++) {    // traverse gridCells
//            gridCells.get(i).showBombs();               // show all bombs
//        }
//        player.playWAV(Constants.GAME_OVER_LOSE_SOUND); // play sound
//        String name = JOptionPane.showInputDialog("Enter name"); // get name
//        LinkedList<String> data = new LinkedList<>();   // create list
//        data.add(name);                                 // add values to list
//        data.add("" + points);
//        file.write(data);                               // save array to file
//        System.exit(0);                                 // exit application
//    }
    
    /**
     * Opens a webpage in the default browser
     * 
     * @return If the file opening was sucessful, return true. If unsucessful,
     *          return false.
     */
    public boolean openURI(String uri){
        Desktop desktop = Desktop.isDesktopSupported()? Desktop.getDesktop() : null;
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
    
    public void mouseClick(MouseEvent evt, Object gridObject){
        //If on grid
        if (evt.getSource() == gridObject){
            if (!gameStarted){
                gameStarted = true;
                timeTracker.start();
            }
        }
        
        
    }

    
}
