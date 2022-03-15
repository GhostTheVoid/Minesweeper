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
import tools.Image;

 
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
        
    private final static int    WIDTH  = 16;                   // Width of each label
    private final static int    HEIGHT = WIDTH;                // Size of the labels
    private final double RATIO = 4.85;                  // Ratio of labels
    private final Color  CELL_BACKGROUND = new Color(189,189,189); // Label background
    private final Color  CELL_BORDER     = Color.gray;  // Label border
    private final Color  CELL_BOMB       = Color.red;   // Bomb fill color
    private final String BOMB = "X";                    // Bomb text
    private LinkedList<String> settings;
    
    public JLabel label;
            
    public boolean isBomb;
    public boolean isFlagged;
    public boolean isClicked;
    
    private final int CELL_DEFAULT_TAG       = 0;
    private final int CELL_1_TAG             = 1;
    private final int CELL_2_TAG             = 2;
    private final int CELL_3_TAG             = 3;  
    private final int CELL_4_TAG             = 4;
    private final int CELL_5_TAG             = 5;
    private final int CELL_6_TAG             = 6;
    private final int CELL_7_TAG             = 7;
    private final int CELL_8_TAG             = 8;            
    private final int CELL_CLICK_TAG         = 9;            
    private final int CELL_BOMB_TAG          = 10;            
    private final int CELL_FLAG_TAG          = 11;            
    private final int CELL_UNKNOWN_CLICK_TAG = 12;            
    private final int CELL_UNKNOWN_TAG       = 13;            
    private final int CELL_BOMB_HIT_TAG      = 14;            
    private final int CELL_BOMB_CHECKED_TAG  = 15;    
    

    /**
     * Default constructor, set class properties
     * @param label the label associated with the image for the character 
     * @param settings the list of setting values for the images
     */
    public GridCell(JLabel label, LinkedList<String> settings) {
        super(label);
        this.settings = settings;
        this.label = label;
    }
    
    /**
     * Clears the label for a new matrix generation
     */
    public void clearCell() {
        //sprite2.hide();
        label.setBackground(CELL_BACKGROUND); // Starting color
        label.setBorder(BorderFactory.createBevelBorder(0)); 
    }
    
    /**
     * Sets this GridCell to act as a bomb
     */
    public void setBomb(){
        //sprite2.animate(CELL_BOMB_TAG);
        label.setText(BOMB);     // Set bomb spot
        label.setBackground(CELL_BOMB);
        isBomb = true;
    }
     
    /**
     * Sets this GridCell to act as a bomb
     * @return if text = BOMB return true, if not, return false
     */
    public boolean isItBomb(){
        return label.getText().equals(BOMB);
    }
    
    public void neighbours(int i){
        //sprite2.hide();
        
        label.setText(""+i);
    }
    
    /**
     * Gets the height of the labels
     * 
     * @return the height of the label
     */
    public static int getHeight(){
        return HEIGHT;
    }
    
    /**
     * Gets the Width of the labels
     * 
     * @return the Width of the label
     */
    public static int getWidth(){
        return WIDTH;
    }
    
    /**
     * Creates a label object on the panel of the passed size
     * 
     * @param x the x coordinate to draw the label in the panel
     * @param y the y coordinate to draw the label in the panel 
     */
    public void makeLabel(int x, int y){
        label.setOpaque(true);            // Make color fillable
        System.out.print("Opaque || ");
        label.setHorizontalAlignment(CENTER); // Align text
        System.out.print("Center || ");
        label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            /** When the user clicks on a spot of the grid, react */
            public void mousePressed(MouseEvent e)  { 
                if(e.getButton() == MouseEvent.BUTTON1){ // Left Click
                    System.out.println("Clicked on Grid, was cell a bomb?: " + isBomb);
                }
                if(e.getButton() == MouseEvent.BUTTON2) { // Middle Click
                    System.out.println("Button 2");
                }
                if(e.getButton() == MouseEvent.BUTTON3) { // Right Click
                    System.out.println("Button 3");
                }
            }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e)  { }
            public void mouseExited(MouseEvent e)   { }
        });
        System.out.print("Listen || ");
        label.setBounds(x, y, WIDTH, HEIGHT); // Position label
        System.out.print("Bounds || ");
        clearCell();
        //setAnimations();
        System.out.println("Animations");
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
        String tag   = Constants.CELL_DEFAULT_TAG;        
        Image cellDefault = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_1_TAG;        
        Image cell1 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_2_TAG;        
        Image cell2 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_3_TAG;        
        Image cell3 = Animator.getImage(sheet, label, settings, tag);        
        tag = Constants.CELL_4_TAG;        
        Image cell4 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_5_TAG;        
        Image cell5 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_6_TAG;        
        Image cell6 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_7_TAG;        
        Image cell7 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_8_TAG;        
        Image cell8 = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_CLICK_TAG;        
        Image cellClick = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_FLAG_TAG;        
        Image cellFlag = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_UNKNOWN_TAG;        
        Image cellUnknown = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_UNKNOWN_CLICK_TAG;        
        Image cellUnknownClick = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_BOMB_TAG;        
        Image cellBomb = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_BOMB_HIT_TAG;        
        Image cellBombHit = Animator.getImage(sheet, label, settings, tag);
        tag = Constants.CELL_BOMB_CHECKED_TAG;        
        Image cellBombChecked = Animator.getImage(sheet, label, settings, tag);
        System.out.print("(TAGS)");
        LinkedList<Image> cellImages = new LinkedList<>(); 
        cellImages.add(cellDefault);
        cellImages.add(cell1);
        cellImages.add(cell2);
        cellImages.add(cell3);
        cellImages.add(cell4);
        cellImages.add(cell5);
        cellImages.add(cell6);
        cellImages.add(cell7);
        cellImages.add(cell8);
        cellImages.add(cellClick);
        cellImages.add(cellFlag);
        cellImages.add(cellUnknown);
        cellImages.add(cellUnknownClick);
        cellImages.add(cellBomb);
        cellImages.add(cellBombHit);
        cellImages.add(cellBombChecked);
        sprite2.setImages(cellImages); 
        System.out.print("(SETS) ");
    }
}
