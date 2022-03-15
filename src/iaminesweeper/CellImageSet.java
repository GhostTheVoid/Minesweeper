/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;
import tools.Image;
import javax.swing.JLabel;

 
/**
 * CellImageSet.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since Mar. 14, 2022
 */
public class CellImageSet {
    
    private LinkedList<String> settings;
    private static LinkedList<Image> cellImages;
    
    private static boolean isCreated;
    
    public CellImageSet(LinkedList<String> settings){
        this.settings = settings;
    }

    
    /**
     * Set up all the animations for this character
     * 
     * @param label the label to associate the animation with
     * @param settings the list of animation settings
     */
    public LinkedList<Image> setImageSet(JLabel label) {
        if (!isCreated) {
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
            cellImages = new LinkedList<>(); 
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
            isCreated = true;
            //sprite2.setImages(cellImages); 
            return cellImages;
            //System.out.print("(SETS) ");
        } 
        else {
            System.out.println("Bloop");
            LinkedList<Image> newCell = cellImages.clone();
            for (int i = 0; i < newCell.size(); i++) {
                newCell.get(i).setJLabel(label);
                
            }
            return newCell;
        }
        
        
    }
}
