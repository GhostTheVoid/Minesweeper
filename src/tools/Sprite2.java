/** Required package class namespace */
package tools;

import collections.LinkedList;
import java.awt.Color;
import javax.swing.JLabel;

 
/**
 * Sprite2.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since Mar. 14, 2022
 */
public class Sprite2 {
    
    private static LinkedList<Image>     images;
    private GameImage             gameImage;
    private int                   currentIndex;

    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox used to display the image
     * @param images the images associated with the object
     */
    public Sprite2(JLabel label, LinkedList<Image> images) {
        gameImage  = new GameImage(label);                  // set picturebox
        if (images != null) setImages(images);  // set images
    }
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the image
     * @param imageFile the relative image filename to display
     */
    public Sprite2(JLabel label, String imageFile) {
        gameImage = new GameImage(label,imageFile);     // set picturebox
    }
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the image
     * @param spriteSheet the sprite sheet image file to change the label to
     * @param x the x coordinate of the sprite sheet frame location
     * @param y the y coordinate of the sprite sheet frame location
     * @param width the width of the sprite sheet frame
     * @param height the height coordinate of the sprite sheet frame  
     */
    public Sprite2(JLabel label, String spriteSheet, int x, int y,
                  int width, int height) {
        gameImage = new GameImage(label, spriteSheet, x, y, width, height);
    }
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the image
     * @param text the text inside the hitbox 
     * @param background the background color of the hitbox
     */
    public Sprite2(JLabel label, String text, Color background) {
        gameImage = new GameImage(label, text, background);
    }
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param label the label hitbox use to display the image
     */
    public Sprite2(JLabel label) {
        gameImage  = new GameImage(label);               // set picturebox
    }
     
    /**
     * Sets the images from the passed data
     * 
     * @param images the animation objects for this game image
     */
    public void setImages(LinkedList<Image> images) {
        this.images = images;                   // assign to property
        for (int i = 0; i < images.size(); i++) {   // traverse array
            //images.get(i).stop();                   // stop each animation
        }   
        run();                                          // run first animation
    }
    
    /**
     * Animate the passed index value
     * 
     * @param index the index of the animation to run
     */
    public void animate(int index) {
        //images.get(currentIndex).stop(); // stop current animation
        restart(index);                      // run passed animation index
        currentIndex = index;                // remember passed index
//        if (!isRunning(index)) {
//            images.get(currentIndex).stop(); // stop current animation
//            restart(index);                      // run passed animation index
//            currentIndex = index;                // remember passed index
//        }
    }
    
    /**
     * Update the coordinates of the game images current location data
     * 
     * @param coordinates the coordinates object to update
     */
    public void update(Coordinates coordinates) {
        if (coordinates == null) coordinates = new Coordinates();
        gameImage.update(coordinates);
    }

    /**
     * Re-positions game image in it's container based on coordinate data 
     * 
     * @param coordinates the coordinates object to re-position to
     */
    public void redraw(Coordinates coordinates) {
        gameImage.redraw(coordinates);
    }
    
    /** 
     * Hides (makes invisible) the game image in the container and stops any 
     * running images
     */
    public void hide() {
        stop();
        gameImage.hide();
    }

    /** 
     * Shows (makes visible) the game image in the container and runs the first 
     * animation
     */
    public void show() {
        run();
        gameImage.show();
    }
    
    /**
     * Resizes the image files for the gameImage and any images
     * 
     * @param width the new width to set to
     * @param height the new height to set to 
     */
    public void resize(int width, int height) {
        gameImage.resize(width, height);                // resize the image
        if (images == null) return;                 // error trap
        for (int i = 0; i < images.size(); i++) {   // traverse images
            images.get(i).resize(width,height);     // resize each animation
        }
        resizeToContainer();                            // resize images
    }
    
    /** Resizes all the images and images to the hitbox container */
    public void resizeToContainer() {
        gameImage.resizeToContainer();                  // resize image
        if (images == null) return;                 // error trap
        for (int i = 0; i < images.size(); i++) {   // traverse images
            images.get(i).resizeToContainer();      // resize images
        }
    }
    
    /**
     * Sets the image in the game image gameImage
     * 
     * @param imageFile 
     */
    public void setImage(String imageFile) {
        gameImage.setImage(imageFile);
    }
    
    /**
     * Change the image inside a label to a new image from a sprite sheet and 
     * possibly resize the image to fit the label size
     * 
     * @param spriteSheet imageFile the new image file to change the label to
     * @param x the x coordinate of the sprite sheet frame location
     * @param y the y coordinate of the sprite sheet frame location
     * @param width the width of the sprite sheet frame
     * @param height the height coordinate of the sprite sheet frame  
     */
    public void setImage(
            String spriteSheet, 
            int x, 
            int y, 
            int width, 
            int height) {
        gameImage.setImage(spriteSheet, x, y, width, height);
    }
    
    /**
     * Debug mode for the game image only displaying text and a background
     * color
     * 
     * @param text the text inside the hitbox 
     * @param background the background color of the hitbox
     */
    public void debug(String text, Color background) {
        gameImage.setDebug(text, background);
    }
    
    /** Stops all images */
    public void stop() {
        if (images == null) return;                 // error trap
        for (int i = 0; i < images.size(); i++) {   // traverse images
            stop(i);                                    // stop animation
        }
    }
    
    /**
     * Stops the passed animation
     * 
     * @param index the animation index to stop
     */
    public void stop(int index) {
        if (images == null) return;                 // error trap
        //images.get(index).stop();                   // stop animation
    }
    
    /**
     * Runs the passed animation
     * 
     * @param index the animation index to run
     */
    public void run(int index) {
        if (images == null) return;                 // error trap
        stop();                                         // stop all images
        //images.get(index).run();                    // run animation
    }
    
    /** Runs the first animation */
    public void run() {
        if (images == null) return;                 // error trap
        stop();                                         // stop all images
        currentIndex = 0;                               // set first animation 
        run(currentIndex);                              // run first animation
    }
        
    /**
     * Restarts the passed animation back to the first frame
     * 
     * @param index the animation to restart
     */
    public void restart(int index) {
        images.get(index).restart();
    }
    
    /** Restarts all images back to the first frame  */
    public void restart() {
        stop();                                 // stops all images
        restart(0);                             // restarts first animation
    }
    
    /**
     * Sets the passed animation to loop (repeat from the last frame back to 
     * the first frame) or not
     * 
     * @param index the animation to set the loop to
     * @param shouldLoop should the animation loop (true) or not (false)
     */
    public void setLoop(int index, boolean shouldLoop) {
        //images.get(index).setLoop(shouldLoop);
    }
    
    /**
     * Sets all images to loop (repeat from the last frame back to 
     * the first frame) or not
     * 
     * @param shouldLoop should all images loop (true) or not (false)
     */
    public void setLoop(boolean shouldLoop) {
        for (int i = 0; i < images.size(); i++) {   // traverse images
            setLoop(i,shouldLoop);                      // set animation loop
        }
    }
    
    /**
     * Set the delay (in milliseconds) for the passed animation
     * 
     * @param index the animation to set the loop to
     * @param delay the delay (in milliseconds) for the passed animation
     */
    public void setDelay(int index, int delay) {
        //images.get(index).setDelay(delay);
    }
    
    /**
     * Set all delays (in milliseconds) for all images
     * 
     * @param delay the delay (in milliseconds) for all images
     */
    public void setDelay(int delay) {
        for (int i = 0; i < images.size(); i++) {   // traverse images
            setDelay(i,delay);                          // set animation delay
        }
    }
    
    /**
     * Sets all the frame image files for the passed animation
     * 
     * @param index the animation to set the image files to
     * @param imageFiles the array of relative image file names
     */
    private void setImageFiles(int index, LinkedList<String> imageFiles) {
        images.get(index).setImageFiles(imageFiles);
    }
    
    /**
     * Gets the frames per second (FPS) for the passed animation
     * 
     * @param index the animation to get the FPS from
     * @return the frames per second (FPS) for the passed animation
     */
//    public int getFPS(int index) {
//        return images.get(index).getFPS();
//    }
    
    /**
     * Determines if the passed animation is running (true) or not (false)
     * 
     * @param index the animation to determine if running
     * @return the passed animation is running (true) or not (false)
     */
//    public boolean isRunning(int index) {
//        return images.get(index).isRunning();
//    }

    /**
     * Sets the background color of the label (no images)
     * 
     * @param color the color to set to
     */
    public void setColor(Color color) {
        stop();
        gameImage.setColor(color);
    }

    /** removes any border for the label */
    public void removeBorder() {
        gameImage.removeBorder();
    }
    
    /** Sets the sprite to be an "invisible" but active game object */
    public void setClear() {
        gameImage.setClear();
    }

    /**
     * Sets the label image used on a user interface 
     * 
     * @param label the label image used on a user interface
     */
    public void setLabel(JLabel label) {
        gameImage.setLabel(label);
    }

    /**
     * Determines if this sprite has images or not
     * 
     * @return the sprite has images (true) or not (false) 
     */
    public boolean hasImages() {
        if (images == null) return false;
        return true;
    }
    
    public int getImageCount(){
        return images.size();
    }
}
