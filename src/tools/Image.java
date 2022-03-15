/** Required package class namespace */
package tools;

import collections.LinkedList;
import javax.swing.JLabel;

 
/**
 * Image.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since Mar. 14, 2022
 */
public class Image {
    
    private JLabel             label;
    private Sprite2             frame;
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the animation inside
     * @param imageFiles the list of relative image file names
     * @param delay the delay (in milliseconds) for the entire animation
     * @param shouldLoop should the animation loop (true) or not (false)
     */
    public Image(JLabel label, 
                     LinkedList<String> imageFiles) {
        if (isValid(label,imageFiles)) {            // check objects for nulls       
            this.label = label;                     // parameter to property
            setImageFiles(imageFiles);              // set all image files
        }
    }

    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the animation inside
     * @param spriteSheet the sprite sheet image file
     * @param imageX the list of all frames x coordinates for the frames
     * @param imageY the list of all frames y coordinates for the frames
     * @param imageWidth the list of all frames widths for the frames
     * @param imageHeight the list of all frames heights for the frames
     */
    public Image(JLabel label, 
                     String spriteSheet,
                     LinkedList<Integer> imageX,
                     LinkedList<Integer> imageY,
                     LinkedList<Integer> imageWidth,
                     LinkedList<Integer> imageHeight) {
        if (isValid(label,imageX, imageY, imageWidth, imageHeight)) {
            this.label = label;                     // parameter to property
            setImageFiles(spriteSheet, imageX, imageY, imageWidth, imageHeight);
        }
    }
    
    /** Restart the animation back to the first frame */
    public void restart() {
        //stop();                                     // stop animation
        frame.hide();            // hide curent image
        frame.show();            // show this frame
        //run();                                      // restart animation
    }
    
    /**
     * Sets all the frame image files for the entire animation
     * 
     * @param imageFiles the list of relative image file names
     */
    public void setImageFiles(LinkedList<String> imageFiles) {
        setFrames(imageFiles);                  // set all the frames
    }
    
    /**
     * Sets all the frame image files for the entire animation
     * 
     * @param spriteSheet the sprite sheet image file
     * @param imageX the list of all frames x coordinates for the frames
     * @param imageY the list of all frames y coordinates for the frames
     * @param imageWidth the list of all frames widths for the frames
     * @param imageHeight the list of all frames heights for the frames
     */
    public void setImageFiles(String spriteSheet,
                              LinkedList<Integer> imageX,
                              LinkedList<Integer> imageY,
                              LinkedList<Integer> imageWidth,
                              LinkedList<Integer> imageHeight) {
        setFrames(spriteSheet, imageX, imageY, imageWidth, imageHeight);  
    }
    
    /**
     * Resizes the image for all animation frames
     * 
     * @param width the new width to set to
     * @param height the new height to set to 
     */
    public void resize(int width, int height) {
        frame.resize(width, height);        // resize each frame
        resizeToContainer();                // resize images
    }
    
    /** Resizes image for all animation frames to the hitbox container */
    public void resizeToContainer() {
        frame.resizeToContainer();          // resize each frame
    }
    
    /**
     * Sets all the frames for the animation from the image files, sets the 
     * first frame to visible
     */
    private void setFrames(LinkedList<String> imageFiles) {
        for (int i = 0; i < imageFiles.size(); i++) {       // traverse list
            frame = new Sprite2(label, imageFiles.get(i));  // add frame
            frame.hide();                           // hide frame
        }
        frame.show();                    // show first frame
    }

    /**
     * Sets all the frames for the animation from the sprite sheet and all
     * the coordinates locations, then sets the first frame to visible
     * 
     * @param spriteSheet the sprite sheet image file
     * @param imageX the array of all frames x coordinates for the frames
     * @param imageY the array of all frames y coordinates for the frames
     * @param imageWidth the array of all frames widths for the frames
     * @param imageHeight the array of all frames heights for the frames 
     */
    private void setFrames(String spriteSheet,
                           LinkedList<Integer> imageX,
                           LinkedList<Integer> imageY,
                           LinkedList<Integer> imageWidth,
                           LinkedList<Integer> imageHeight) {
        for (int i = 0; i < imageX.size(); i++) {           // traverse list
            int x = imageX.get(i);
            int y = imageY.get(i);
            int w = imageWidth.get(i);
            int h = imageHeight.get(i);
            frame = new Sprite2(label, spriteSheet, x, y, h, h);
            frame.hide();                           // hide frame
        }
        frame.show();                    // show first frame
    }
    
    /**
     * Checks the various objects from the constructor to make sure they are
     * valid objects to continue the construction
     * 
     * @param hitbox the label hitbox use to display the animation inside
     * @param imageFiles the list of relative image file names
     * @return the objects are valid (true) or not (false)
     */
    private boolean isValid(JLabel hitbox, LinkedList<String> imageFiles) {
        if (hitbox == null) {
            System.out.println("Animation not created, Label null!");
            return false;
        }
        if (imageFiles == null) {
            System.out.println("Animation not created, imageFiles null!");
            return false;
        }
        if (imageFiles.size() == 0) {
            System.out.println("Animation not created, imageFiles size 0!");
            return false;
        }
        return true;
    }
    
    /**
     * Checks the various objects from the constructor to make sure they are
     * valid objects to continue the construction
     * 
     * @param label the label hitbox use to display the animation inside
     * @param imageX the array of all frames x coordinates for the frames
     * @param imageY the array of all frames y coordinates for the frames
     * @param imageWidth the array of all frames widths for the frames
     * @param imageHeight the array of all frames heights for the frames
     * @return the objects are valid (true) or not (false)
     */
    private boolean isValid(JLabel label, 
                            LinkedList<Integer> imageX,
                            LinkedList<Integer> imageY,
                            LinkedList<Integer> imageWidth,
                            LinkedList<Integer> imageHeight) {
        if (label == null) {
            System.out.println("Animation not created, Label null!");
            return false;
        }
        if (imageX == null || imageY == null || 
            imageWidth == null || imageHeight == null) {
            System.out.println("Animation not created, a list is null!");
            return false;
        }
        if (imageX.size() == 0 || imageY.size() == 0 || 
            imageWidth.size() == 0 || imageHeight.size() == 0) {
            System.out.println("Animation not created, a list is size 0!");
            return false;
        }
        return true;
    }
    
    /**
     * Sets the label image used on a user interface 
     * 
     * @param label the label image used on a user interface
     */
    public void setJLabel(JLabel label){
        this.label = label;
    }
            
}
