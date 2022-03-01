/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import javax.swing.JLabel;
import iaminesweeper.Constants;
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
    
    public boolean isBomb;
    public boolean isFlagged;
    public boolean isClicked;
    
    private final int ANIMATE_BLANK         = 0;
    private final int ANIMATE_CLICK         = 1;
    private final int ANIMATE_FLAG          = 2;
    private final int ANIMATE_UNKNOWN       = 3;  
    private final int ANIMATE_UNKNOWN_CLICK = 4;
    private final int ANIMATE_BOMB          = 5;
    private final int ANIMATE_BOMB_HIT      = 6;
    private final int ANIMATE_BOMB_CHECKED = 7;            
    private final int RIGHT_PORTAL         = 0;
    private final int LEFT_PORTAL          = 1;
    

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
                sprite.animate(ANIMATE_BOMB_CHECKED);
            }
            else if (isClicked){
                sprite.animate(ANIMATE_BOMB_HIT);
            }
            else {
                sprite.animate(ANIMATE_BOMB);
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
}
