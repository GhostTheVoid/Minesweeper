/** Required package class namespace */
package iaminesweeper;

 
/**
 * Timer.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Timer {

    /**
     * Default constructor, set class properties
     */
    public Timer() {
        
    }
     
    /**
     * String representation of this object
     *
     * @return The object represented as a String
     */
    @Override
    public String toString() {
        return "Timer: " + super.toString();
    }
   
    /**
     * Deep comparison, determines if two objects are "equal" in this context
     *
     * @param object the object to compare to
     * @return the objects are "equal" (true) or not (false)
     */
    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }
       
    /**
     * a Deep clone, creates a duplicate object using new memory
     *
     * @return a "clone" of the object using new memory
     */
    @Override
    public Timer clone() {
        return this;
    }
}
