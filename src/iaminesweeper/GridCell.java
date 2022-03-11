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
import tools.GameCharacter;

 
/**
 * GridCell.java - the cells that make up the grid. Cells may contain a bomb.
 * Depending on their surrounding cells, if there is a bomb in their 
 * surroundings, will contain a number showing how many bomb are touching them
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 17-Feb-2022
 */
public class GridCell extends GameCharacter{
    
    private LinkedList<GridCell> gridCells;
    
    // GLOBAL VARIABLES
    // ================
    
    private JPanel          gamePanel;      // Reference to the panel for labels
        
    private final int    WIDTH  = 50;                   // Width of each label
    private final int    HEIGHT = WIDTH;                // Size of the labels
    private final double RATIO = 4.85;                  // Ratio of labels
    private final Color  CELL_BACKGROUND = Color.white; // Label background
    private final Color  CELL_BORDER     = Color.gray;  // Label border
    private final Color  CELL_BOMB       = Color.red;   // Bomb fill color
    private final String BOMB = "X";                    // Bomb text
    
    private JLabel label;
            
    public boolean isBomb;
    public boolean isFlagged;
    public boolean isClicked;
    
    
    
    private final int CELL_DEFAULT_TAG       = 0;
    private final int CELL_1_TAG             = 1;
    private final int CELL_2_TAG             = 2;
    private final int CELL_3_TAG             = 3;  
    private final int CELL_4_TAG             = 4;
    private final int CELL_5_TAG             = 5;
    private final int CELL_7_TAG             = 6;
    private final int CELL_8_TAG             = 7;            
    private final int CELL_CLICK_TAG         = 8;            
    private final int CELL_FLAG_TAG          = 9;            
    private final int CELL_UNKNOWN_TAG       = 10;            
    private final int CELL_UNKNOWN_CLICK_TAG = 11;            
    private final int CELL_BOMB_TAG          = 12;            
    private final int CELL_BOMB_HIT_TAG      = 13;            
    private final int CELL_BOMB_CHECKED_TAG  = 14;    
    

    /**
     * Default constructor, set class properties
     * @param gridLabel the label associated with the image for the character
     * @param settings the list of setting values for the images 
     */
    public GridCell(JLabel gridLabel, LinkedList<String> settings) {
        super(gridLabel, Constants.CELL_MOVE_AMOUNT,
                Constants.CELL_TIMER_DELAY);
        setAnimations(gridLabel, settings); // build all animations
        spawn();
    }
    
    @Override
    public void action() {
        
    }
    
    /** Pacman has lost the game (captured by a ghost) */
    private void loseGame() {
        
    }
     
    /**
     * Associates the gridCells list parameter with the class encapsulated 
     * property
     * 
     * @param gridCells the ghost list to associate with
     */
    public void setAllCells(LinkedList<GridCell> gridCells) {
        this.gridCells = gridCells;
    }
    
    /** changes the animation set to the appropriate animation based on direction */
    public void animate() {
        if (sprite == null) return;
        if (sprite.hasAnimations() == false) return;
        if (isBomb) {
            
        }
    }
    
    public void showBombs(){
        if (isBomb){
            if(isFlagged){
                sprite.animate(CELL_FLAG_TAG);
            }
            else if (isClicked){
                sprite.animate(CELL_BOMB_HIT_TAG);
            }
            else {
                sprite.animate(CELL_BOMB_TAG);
            }
        }
    }
    
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    private void setAnimations(JLabel label, LinkedList<String> settings) {
        String sheet = Constants.SPRITE_SHEET;
        int    delay = Constants.CELL_ANIMATION_DELAY; 
        String tag   = Constants.CELL_DEFAULT_TAG;        
        Animation cellDefault = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_1_TAG;        
        Animation cell1 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_2_TAG;        
        Animation cell2 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_3_TAG;        
        Animation cell3 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);        
        tag = Constants.CELL_4_TAG;        
        Animation cell4 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_5_TAG;        
        Animation cell5 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_6_TAG;        
        Animation cell6 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_7_TAG;        
        Animation cell7 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_8_TAG;        
        Animation cell8 = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_CLICK_TAG;        
        Animation cellClick = Animator.getAnimation(sheet, label, 
                                                       delay, settings, tag);
        tag = Constants.CELL_FLAG_TAG;        
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
        LinkedList<Animation> cellAnimations = new LinkedList<>(); 
        cellAnimations.add(cellDefault);
        cellAnimations.add(cell1);
        cellAnimations.add(cell2);
        cellAnimations.add(cell3);
        cellAnimations.add(cell4);
        cellAnimations.add(cell5);
        cellAnimations.add(cell6);
        cellAnimations.add(cell7);
        cellAnimations.add(cell8);
        cellAnimations.add(cellClick);
        cellAnimations.add(cellFlag);
        cellAnimations.add(cellUnknown);
        cellAnimations.add(cellUnknownClick);
        cellAnimations.add(cellBomb);
        cellAnimations.add(cellBombHit);
        cellAnimations.add(cellBombChecked);
        sprite.setAnimations(cellAnimations);             
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
     * Creates a label object on the panel of the passed size
     * 
     * @param x
     * @param y 
     */
    private void makeLabel(int x, int y){
        
    }
}
