/** Required package class namespace */
package iaminesweeper;
 
import collections.LinkedList;
import javax.swing.JLabel;

/**
 * Flag.java - description
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 18-Feb-2022
 */
public class Flag extends Counter{

    /**
     * Default constructor, set class properties
     */
    public Flag(LinkedList<JLabel> flagLabels,  LinkedList<String> settings) {
        super(flagLabels, settings);
    }
}
