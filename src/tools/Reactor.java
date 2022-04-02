/** Required package class namespace */
package tools;

 
/**
 * Reactor.java - methods to react to collisions in various ways
 * !TO BE EDITED!
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Reactor {

    private Coordinates source;
    private Coordinates target;
    private Detector    detector;
    
    
    /**
     * Constructor for the class, sets class properties
     * 
     * @param coordinates the coordinate data to assign to this class
     */
    public Reactor(Coordinates coordinates, 
                   Detector detector) {
        this.source             = coordinates;
        this.detector           = detector;
    }
    
    /**
     * Positions the coordinate data correctly against (sticks to) the targets
     * bottom edge coordinate data
     * 
     * @param hitbox the other game object to stick to
     */
    public void stickToBottom(GameObject hitbox) {
        target = hitbox.coordinates;
        source.y = target.bottom + 1;
        source.recalculate();
    }
    
    /**
     * Positions the coordinate data correctly against (sticks to) the targets
     * top edge coordinate data
     * 
     * @param hitbox the other game object to stick to
     */
    public void stickToTop(GameObject hitbox) {
        target = hitbox.coordinates;
        source.y = target.top - source.height - 1;
        source.recalculate();
    }

    /**
     * Positions the coordinate data correctly against (sticks to) the targets
     * left edge coordinate data
     * 
     * @param hitbox the other game object to stick to
     */
    public void stickToLeft(GameObject hitbox) {
        target = hitbox.coordinates;
        source.x = target.left - source.width  - 1;
        source.recalculate();
    }

    /**
     * Positions the coordinate data correctly against (sticks to) the targets
     * right edge coordinate data
     * 
     * @param hitbox the other game object to stick to
     */
    public void stickToRight(GameObject hitbox) {
        target = hitbox.coordinates;
        source.x = target.right + 1;
        source.recalculate();
    }

    /**
     * Puts this object's position in the middle both horizontally and 
     * vertically to the target
     * 
     * @param hitbox the other game object to stick to
     */
    public void centerOn(GameObject hitbox) {
        target = hitbox.coordinates;
        if      (target.width  > source.width)  source.x = target.centerX - (source.width  / 2);
        else if (source.width  > target.width)  source.x = target.centerX + (source.width  / 2);
        if      (target.height > source.height) source.y = target.centerY - (source.height / 2);
        else if (source.height > target.height) source.y = target.centerY + (source.height / 2);
        source.recalculate();
    }

    /**
     * Centers this object's position in the center and above the top of 
     * the target
     * 
     * @param hitbox the other game object to stick to
     */
    public void centerOnTop(GameObject hitbox) {
        target = hitbox.coordinates;
        centerOn(hitbox);
        source.y = target.top - source.height - 1;
        source.recalculate();
    }

    /**
     * Centers this object's position in the center and below the bottom of 
     * the target
     * 
     * @param hitbox the other game object to stick to
     */
    public void centerOnBottom(GameObject hitbox) {
        target = hitbox.coordinates;
        centerOn(hitbox);
        source.y = target.bottom + 1;
        source.recalculate();
    }

    /**
     * Centers this object's position in the center and to the left of the 
     * target
     * 
     * @param hitbox the other game object to stick to
     */
    public void centerOnLeft(GameObject hitbox) {
        target = hitbox.coordinates;
        centerOn(hitbox);
        source.x = target.left - source.width - 1;
        source.recalculate();
    }

    /**
     * Centers this object's position in the center and to the right of the 
     * target
     * 
     * @param hitbox the other game object to stick to
     */
    public void centerOnRight(GameObject hitbox) {
        target = hitbox.coordinates;
        centerOn(hitbox);
        source.x = target.right + 1;
        source.recalculate();
    }
}
