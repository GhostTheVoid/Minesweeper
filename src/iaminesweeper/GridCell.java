/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import javax.swing.JLabel;
import iaminesweeper.Constants;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.CENTER;
import tools.Animation;
import tools.GameObject;

 
/**
 * GridCell.java - the cells that make up the grid. Cells may contain a bomb.
 * Depending on their surrounding cells, if there is a bomb in their 
 * surroundings, will contain a number showing how many bomb are touching them
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 17-Feb-2022
 */
public class GridCell extends GameObject{
    
    
    // GLOBAL VARIABLES
    // ================
    
    private JPanel          gamePanel;      // Reference to the panel for labels
    
    private final double RATIO = 4.85;                  // Ratio of labels
    private final Color  CELL_BACKGROUND = new Color(189,189,189); // Label background
    private final Color  CELL_BORDER     = Color.gray;  // Label border
    private final Color  CELL_BOMB       = Color.red;   // Bomb fill color
    private final String BOMB           = Constants.CELL_BOMB;             // Bomb cell text
    private final String BLANK          = Constants.CELL_BLANK;            // Blank cell text
    private final int    INVALID_NUMBER = Constants.CELL_INVALID_NUMBER;   // Invalid number
    
    private final int CELL_FLAG_TAG          = 0;            
    private final int CELL_UNKNOWN_TAG       = 1;  // I have no idea what this does        
    private final int CELL_UNKNOWN_CLICK_TAG = 2;            
    private final int CELL_BOMB_TAG          = 3;  
    private final int CELL_BOMB_HIT_TAG      = 4;  // The bomb that triggered the lost game         
    private final int CELL_BOMB_CHECKED_TAG  = 5;  // If a bomb was incorrectly flagged
    
    private LinkedList<String> settings; // The settings for the animations
    private int neighbours;              // How many bombs neighour this cell
    
            
    public JLabel label;
    public String matrix;      // Store values for the label
    public boolean isBomb;
    public boolean isFlagged;
    public boolean isClicked;
    public boolean canClick;
             
      
    

    /**
     * Default constructor, set class properties
     * @param label the label associated with the image for the character 
     * @param settings the list of setting values for the images
     */
    public GridCell(JLabel label, LinkedList<String> settings) {
        super(label);
        this.settings = settings;
        this.label = label;
        canClick = true;
    }
    
    /**
     * Clears the label for a new matrix generation
     */
    public void clearCell() {
        //sprite.animate(CELL_DEFAULT_TAG);
        label.setText("");     // Set bomb spot
        label.setBackground(CELL_BACKGROUND); // Starting color
        label.setBorder(BorderFactory.createBevelBorder(0)); 
    }
    
    /**
     * Sets this GridCell to act as a bomb
     */
    public void setBomb(){
        //sprite.animate(CELL_BOMB_TAG);
        //label.setBackground(CELL_BOMB);
        isBomb = true;
    }
    
    public void showBomb(){
        if (isBomb) {
            label.setText(BOMB);     // Set bomb spot
            label.setForeground(Color.BLACK);
            label.setBorder(BorderFactory.createBevelBorder(1));
        }
    }
    
    public void showCell(){
        switch (neighbours) {
            case 0:
                label.setText("");     // Set bomb spot
                //Find Blank Neighbours
                break;
            case 1:
                System.out.println("1");
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(0, 0, 255));
                break;
            case 2:
                System.out.println("2");
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(0, 128, 0));
                break;
            case 3:
                System.out.println("4");
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(128, 0, 0));
                break;
            case 4:
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(0, 0, 128));
                break;
            case 5:
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(128, 0, 0));
                break;
            case 6:
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(new Color(0, 128, 128));
                break;
            case 7:
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(Color.BLACK);
                break;
            case 8:
                label.setText("" + neighbours);     // Set bomb spot
                label.setForeground(Color.DARK_GRAY);
                break;
        }
    }
    
    public void revealCell(){
        canClick = false;
        label.setBackground(CELL_BACKGROUND); // Starting color
        label.setBorder(BorderFactory.createBevelBorder(1));
        if (isBomb) {
            //sprite.animate(CELL_BOMB_TAG);
            showBomb();
            label.setBackground(Color.RED);
            GameEngine.lostGame();
        } else {
            showCell();
        }
    }
    
    public void flagCell(){
        if (isFlagged) {
            
        } else {
            
        }
        
    }
    
    /**
     * tracks how many bombs neighbour this cell
     * 
     * @param num the amount of bombs neighbouring
     */
    public void setNeighbours(int num){
        neighbours = num;
        System.out.println(neighbours);
    }
     
    /**
     * Sets this GridCell to act as a bomb
     * @return if text = BOMB return true, if not, return false
     */
    public boolean isItBomb(){
        return isBomb;
    }
    
    /**
     * Gets the height of the labels
     * 
     * @return the height of the label
     */
    public static int getHeight(){
        return Constants.CELL_HEIGHT;
    }
    
    /**
     * Gets the Width of the labels
     * 
     * @return the Width of the label
     */
    public static int getWidth(){
        return Constants.CELL_WIDTH;
    }
    
    public void setClickable(boolean click){
        canClick = click;
    }
    
    
    /**
     * Sets the design of the labels for when it is either clicked or not
     */
    public void clickedOn(){
        if (isClicked) {
            canClick = false;
            label.setBackground(CELL_BACKGROUND); // Starting color
            label.setBorder(BorderFactory.createBevelBorder(1));
            if (isBomb) {
                //sprite.animate(CELL_BOMB_TAG);
                label.setText(BOMB);     // Set bomb spot
                label.setForeground(Color.BLACK);
                label.setBackground(Color.RED);
                GameEngine.lostGame();

            } else {
                switch (neighbours) {
                    case 0:
                        label.setText("");     // Set bomb spot
                        //Find Blank Neighbours
                        break;
                    case 1:
                        System.out.println("1");
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(0, 0, 255));
                        break;
                    case 2:
                        System.out.println("2");
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(0, 128, 0));
                        break;
                    case 3:
                        System.out.println("4");
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(128, 0, 0));
                        break;
                    case 4:
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(0, 0, 128));
                        break;
                    case 5:
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(128, 0, 0));
                        break;
                    case 6:
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(new Color(0, 128, 128));
                        break;
                    case 7:
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(Color.BLACK);
                        break;
                    case 8:
                        label.setText("" + neighbours);     // Set bomb spot
                        label.setForeground(Color.DARK_GRAY);
                        break;
                }
            }
        }
        else if (isFlagged){
            //sprite.animate(CELL_FLAG_TAG);
        }
        else {
            label.setBackground(CELL_BACKGROUND); // Starting color
            label.setBorder(BorderFactory.createBevelBorder(0)); 
        }
    }
    
    /**
     * Creates a label object on the panel of the passed size
     * 
     * @param x the x coordinate to draw the label in the panel
     * @param y the y coordinate to draw the label in the panel 
     */
    public void makeLabel(int x, int y){
        label.setOpaque(true);            // Make color fillable
        //System.out.print("Opaque || ");
        label.setBackground(CELL_BACKGROUND); // Starting color
        label.setHorizontalAlignment(CENTER); // Align text
        //System.out.print("Center || ");
        label.setBorder(BorderFactory.createBevelBorder(0)); 
        label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            /** When the user clicks on a spot of the grid, react */
            public void mousePressed(MouseEvent e)  { 
                if (canClick) {
                    if(e.getButton() == MouseEvent.BUTTON1){ // Left Click
                        // Mark status as Click
                        System.out.println("Clicked on Grid, was cell a bomb?: " + isBomb);
                        isClicked = true;
                        clickedOn();
                    }
                    else if(e.getButton() == MouseEvent.BUTTON2) { // Middle Click
                        // check neighbours
                        // Mark status as Click
                        System.out.println("Button 2");
                        label.setBackground(CELL_BACKGROUND); // Starting color
                        label.setBorder(BorderFactory.createBevelBorder(1));
                    }
                    else if(e.getButton() == MouseEvent.BUTTON3) { // Right Click
                        System.out.println("Button 3");
                        isFlagged = true;
                        clickedOn();
                    }
                }
                
            }
            public void mouseReleased(MouseEvent e) { 
                if (canClick) {
                    if(e.getButton() == MouseEvent.BUTTON2) { // Middle Click
                        // If neighbours have bomb, but bomb count is flagged,
                        // mark isClicked on neighbours as true

                        // If bomb is incorrectly flagged, lose game
                        System.out.println("Button 2");
                        label.setBackground(CELL_BACKGROUND); // Starting color
                        label.setBorder(BorderFactory.createBevelBorder(0));
                    }
                }
            }
            public void mouseEntered(MouseEvent e)  { }
            public void mouseExited(MouseEvent e)   { }
        });
        //System.out.print("Listen || ");
        label.setBounds(x, y, Constants.CELL_WIDTH, Constants.CELL_HEIGHT); // Position label
        //System.out.print("Bounds || ");
        //setAnimations();
        //System.out.println("Animations");
    }
    
    
    
    /**
     * Reveals what is at this cell location (a number, bomb, or blank) with 
     * text and a background color
     * 
     * @param row the row in the matrix to reveal
     * @param column the column in the matrix to reveal
     * @return this location is a blank (true) cell or not (false)
     */
    private boolean reveal(int row, int column) {
        String text   = label.getText();    // Text from label
        int    number = isNumber(text);                 // Possible number
        if (text.equals(BLANK)) {                       // Blank spot
            label.setBackground(CELL_REVEAL_BLANK);
            label.setText(BLANK);
            matrix = BLANK;
            return true;                                // Continue revealing
        }
        else if (text.equals(BOMB)) {                   // Bomb spot
            showBomb();
            label.setBackground(Color.MAGENTA);
            return false;
        }
        else if (number != INVALID_NUMBER) {            // A valid number spot
            label.setBackground();
            matrix = "" + isNumber(label.getText());
            return false;
        }
        else {                                          // Error check
            label.setBackground(Color.black);
            return false;
        }
    }
    
    /**
     * Determines if the passed text is a valid number or not
     * 
     * @param text the text to check
     * @return the valid numbers (1-8) or not a valid number (-1) 
     */
    private int isNumber(String text) {
        if      (text == null)     return INVALID_NUMBER;
        else if (text.equals("1")) return Integer.parseInt(text);
        else if (text.equals("2")) return Integer.parseInt(text);
        else if (text.equals("3")) return Integer.parseInt(text);
        else if (text.equals("4")) return Integer.parseInt(text);
        else if (text.equals("5")) return Integer.parseInt(text);
        else if (text.equals("6")) return Integer.parseInt(text);
        else if (text.equals("7")) return Integer.parseInt(text);
        else if (text.equals("8")) return Integer.parseInt(text);
        else                       return INVALID_NUMBER;
    }
    
    
    
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    private void setAnimations() {
        System.out.print("(START)");
        String sheet = Constants.SPRITE_SHEET;
        int    delay = Constants.CELL_ANIMATION_DELAY; 
        String tag   = Constants.CELL_FLAG_TAG;        
        Animation cellFlag = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_UNKNOWN_TAG;        
        Animation cellUnknown = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_UNKNOWN_CLICK_TAG;        
        Animation cellUnknownClick = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_BOMB_TAG;        
        Animation cellBomb = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_BOMB_HIT_TAG;        
        Animation cellBombHit = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_BOMB_CHECKED_TAG;        
        Animation cellBombChecked = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        System.out.print("(TAGS)");
        LinkedList<Animation> cellAnimations = new LinkedList<>(); 
        cellAnimations.add(cellFlag);
        cellAnimations.add(cellUnknown);
        cellAnimations.add(cellUnknownClick);
        cellAnimations.add(cellBomb);
        cellAnimations.add(cellBombHit);
        cellAnimations.add(cellBombChecked);
        sprite.setAnimations(cellAnimations); 
        System.out.print("(SETS) ");
    }
}
