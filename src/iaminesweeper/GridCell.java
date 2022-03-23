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
    
    private int row;            // The row index this cell is in
    private int column;         // The column index this cell is in
    private int neighbours;     // How many bombs neighour this cell
    
    public JLabel  label;
    public String  matrix;      // Store values for the label
    public boolean isBomb;
    public boolean isFlagged;
    public boolean isClicked;
    public boolean canClick;
    
    
    
    /**
     * Default constructor, set class properties
     * @param label the label associated with the image for the character 
     * @param row the row in the GameEngine matrix for the cell
     * @param column the column in the GameEngine matrix for the cell
     */
    public GridCell(JLabel label, int row, int column) {
        super(label);
        this.row        = row;
        this.column     = column;
        this.label      = label;
        setClickable(true);
    }
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Accessors/Mutators"> 
    /**
     * Tracks how many bombs neighbour this cell
     * 
     * @param num the amount of bombs neighbouring
     */
    public void setNeighbours(int num){
        neighbours = num;
    }
    
    /**
     * Sets variable "canClick" to determine if the user can click on the cell
     * 
     * @param click determines if the user is able to click on this cell 
     */
    public void setClickable(boolean click){
        canClick = click;
    }
    
    /**
     * Sets this <code>GridCell</code> to act as a bomb.
     */
    public void setBomb(){
        //sprite.animate(CELL_BOMB_TAG);
        //label.setBackground(CELL_BOMB);
        matrix = BOMB;
        isBomb = true;
    }
    
    public void setLblProperties(String text, Color foreground,
                                 Color background, int borderType){
        
        if (text.equals("null")) {}
        else label.setText(text);
        
        if (foreground != null) {
            label.setForeground(foreground);
        }
        if (background != null) {
            label.setBackground(background);
        }
        if (borderType == 0 || borderType == 1) {
            label.setBorder(BorderFactory.createBevelBorder(borderType)); 
        }
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
    
    /**
     * Sets this <code>GridCell</code> to act as a bomb
     * @return <code>true</code> if and only if <code>isBomb</code> is true
     */
    public boolean getBomb(){
        return isBomb;
    }
    // </editor-fold> 
     
    
    // <editor-fold defaultstate="collapsed" desc="Cell Events"> 
    /**
     * Clears the label
     */
    public void clearCell() {
        //sprite.animate(CELL_DEFAULT_TAG);
        setLblProperties("", null, CELL_BACKGROUND, 0);
        //label.setText("");     // Set bomb spot
        //label.setBackground(CELL_BACKGROUND); // Starting color
        //label.setBorder(BorderFactory.createBevelBorder(0)); 
    }
    
    /**
     * Reveals the cell as blank
     */
    public void showBlank(){
        label.setBackground(CELL_BACKGROUND); // Starting color
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setText("");
    }
    
    /**
     * Reveals the cell as blank
     */
    public void showNum(Color color){
        label.setText("" + this.neighbours);
        label.setForeground(color);
    }
    
    /**
     * Reveals that this cell is a bomb
     */
    public void showBomb(){
        if (isBomb) { // Error check
            setLblProperties(BOMB, Color.BLACK, null, 1);
        }
    }
    
    /**
     * Reveals the content of this cell
     */
    public void showCell(){
        if (isBomb) {
            //sprite.animate(CELL_BOMB_TAG);
            showBomb();
            label.setBackground(Color.RED);
            GameEngine.lostGame();
        } 
        else {
            switch (neighbours) {
                case 0:
                    showBlank();
                    break;
                case 1:
                    showNum(new Color(0, 0, 255));
                    break;
                case 2:
                    showNum(new Color(0, 128, 0));
                    break;
                case 3:
                    showNum(new Color(128, 0, 0));
                    break;
                case 4:
                    showNum(new Color(0, 0, 128));
                    break;
                case 5:
                    showNum(new Color(128, 0, 0));
                    break;
                case 6:
                    showNum(new Color(0, 128, 128));
                    break;
                case 7:
                    showNum(Color.BLACK);
                    break;
                case 8:
                    showNum(Color.DARK_GRAY);
                    break;
            }
        }
        
    }
    
    /**
     * Marks the cell, preventing it from being clicked on with 
     * <code>BUTTON1</code> (Left click).
     * <p>
     * If <code>isFlagged</code> is already <code>true</code>, 
     * <code>isFlagged</code> will be set to <code>false</code> and 
     * <code>canClick</code> will be set to <code>true</code>, 
     * allowing this cell to be clicked again.</p>
     */
    public void flagCell(){
        if (!isClicked) {
            if (FlagTracker.getRemainingBombs() > 0 || isFlagged) {
                if (isFlagged) {
                    FlagTracker.raiseRemainingBombs();
                    isFlagged = false;
                    setLblProperties("null", null, CELL_BACKGROUND, -1);
                    //label.setBackground(CELL_BACKGROUND); // Starting color
                    setClickable(true);
                } else if (!isFlagged) {
                    FlagTracker.lowerRemainingBombs();
                    isFlagged = true;
                    setLblProperties("null", null, Color.GREEN, 0);
                    label.setBackground(Color.GREEN);
                    setClickable(false);
                }
            }
        }
    }
    
    /**
     * If <code>canClick</code> is <code>true</code>, 
     * and <code>isClicked</code> is <code>false</code>;
     * the contents of this cell is revealed to the user
     * (a number, bomb, or blank)
     */
    public void revealCell(){
        if (canClick){
            if (!isClicked) {
                setClickable(false);
                label.setBackground(CELL_BACKGROUND); // Starting color
                label.setBorder(BorderFactory.createBevelBorder(1));
                showCell();
                isClicked = true;
            }     
        }
    }
    
    /**
     * Reveals what is at this cell location (a number, bomb, or blank) with 
     * text and a background color
     * 
     * @return this location is a blank (true) cell or not (false)
     */
    public boolean reveal() {
        if (neighbours == 0) {              // Blank spot
            revealCell();
            return true;                                // Continue revealing
        }
        else if (isBomb) {                   // Bomb spot
            revealCell();
            label.setBackground(Color.MAGENTA);
            return false;
        }
        else if (isFlagged){
            System.out.println("Cell flagged, unable to complete");
            return false;
        }
        else if (neighbours != INVALID_NUMBER) {            // A valid number spot
            revealCell();
            //label.setBackground(Color.blue);
            matrix = "" + isNumber(label.getText());
            return false;
        }
        else {                                          // Error check
            label.setBackground(Color.black);
            return false;
        }
    }
    
    public boolean reveal2(){
//        if (neighbours <= 8 && neighbours >= 0) {              // Blank spot
//            revealCell();
//            return true;                                // Continue revealing
//        }
        
        if (!isFlagged){
            return true;
        }
        else {
            return false;
        }
    }
    // </editor-fold> 
    
    
    // <editor-fold defaultstate="collapsed" desc="Create Labels"> 
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
                Constants.gameEngine.mouseClick(e, BOMB);
                if (e.getButton() == MouseEvent.BUTTON1) { // Left Click
                    // Mark status as Click
                    System.out.println("Clicked on Grid, was cell a bomb?: " + isBomb);
                    GameEngine.mouseClick(row, column);
                } else if (e.getButton() == MouseEvent.BUTTON2) { // Middle Click
                    // check neighbours
                    // Mark status as Click
                    //System.out.println("Button 2");
                    GameEngine.showNeighbours(row, column);
                } else if (e.getButton() == MouseEvent.BUTTON3) { // Right Click
                    flagCell();
                }
                
            }
            public void mouseReleased(MouseEvent e) { 
                if (e.getButton() == MouseEvent.BUTTON2) { // Middle Click
                    System.out.println("Neighbour = " + neighbours);
                    GameEngine.hideNeighbours();

                    // If neighbours have bomb, but bomb is flagged,
                    // mark isClicked on neighbours as true
                    // If bomb is incorrectly flagged, lose game
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
    // </editor-fold>  
    
    
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
                                                       delay, Constants.settings, tag);
        tag = Constants.CELL_UNKNOWN_TAG;        
        Animation cellUnknown = Animator.getAnimation(sheet, label, 
                                                       delay, Constants.settings, tag);
        tag = Constants.CELL_UNKNOWN_CLICK_TAG;        
        Animation cellUnknownClick = Animator.getAnimation(sheet, label, 
                                                       delay, Constants.settings, tag);
        tag = Constants.CELL_BOMB_TAG;        
        Animation cellBomb = Animator.getAnimation(sheet, label, 
                                                       delay, Constants.settings, tag);
        tag = Constants.CELL_BOMB_HIT_TAG;        
        Animation cellBombHit = Animator.getAnimation(sheet, label, 
                                                       delay, Constants.settings, tag);
        tag = Constants.CELL_BOMB_CHECKED_TAG;        
        Animation cellBombChecked = Animator.getAnimation(sheet, label, 
                                                       delay, Constants.settings, tag);
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
