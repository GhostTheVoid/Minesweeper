/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Dialogs.java - a collection of useful methods for working with dialogs
 *
 * @author Mr. Wachs
 * @since Dec 12, 2017
 * @instructor Mr. Wachs
 */
public class Dialogs {
    // Static (shared) private constants below.................................
    
    private static final  int    DEFAULT_FONT_SIZE    = 12;
    private static final  int    DEFAULT_FONT_TYPE    = Font.PLAIN;
    private static final  int    DEFAULT_STYLE        = 
            JOptionPane.INFORMATION_MESSAGE;
    private static final  int    DEFAULT_RED_COLOR    = 238;
    private static final  int    DEFAULT_GREEN_COLOR  = 238;
    private static final  int    DEFAULT_BLUE_COLOR   = 238;
    private static final  String DEFAULT_TITLE        = "Minesweeper";
    private static final  String DEFAULT_FONT_FACE    = "Segoe UI"; 
    private static final  Icon   DEFAULT_ICON_PICTURE = new ImageIcon
        ("/media/");
    
    private static final  String ERROR_1 = "Error, please try again\n\n";
    private static final  String ERROR_2 = "Error, invalid number\n\n";
    private static final  String ERROR_3 = "Error, number not inside range\n\n";
        
    private static final Font DEFAULT_FONT = new Font(
            DEFAULT_FONT_FACE,
            DEFAULT_FONT_TYPE,
            DEFAULT_FONT_SIZE);
    private static final Color DEFAULT_BACKGROUND = new Color(
            DEFAULT_RED_COLOR, 
            DEFAULT_GREEN_COLOR,
            DEFAULT_BLUE_COLOR);
    private static final Color DEFAULT_FOREGROUND = new Color(0,0,0);
    
    /** The title set at the top of the dialog */
    public static String title = DEFAULT_TITLE;
    
    /** The style (use an icon or not) of the dialog */
    public static int style = DEFAULT_STYLE;
    
    /** The font used when displaying text in the dialog */
    public static Font font = DEFAULT_FONT;
    
    /** The background color used in the dialog */
    public static Color background = DEFAULT_BACKGROUND;
    
    /** The foreground color (text color) used in the dialog */
    public static Color foreground = DEFAULT_FOREGROUND;
    
    /**
     * gets the size of the font
     * @return the size of the font
     */
    public static int getFontSize (){
        return DEFAULT_FONT_SIZE;
    }
    
    /**
     * Ask the user a yes and no question, in a confirm dialog box
     * 
     * @param text the yes no question to ask in the dialog
     * @param icon the icon used in the dialog
     * @return true (yes), false (no)
     */
    public static boolean yesNo(String text, ImageIcon icon) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = Dialogs.formatArea(text);
        // Store the user's response in a variable from what they clicked on
        // when the dialog appears only showing "yes" and "no" buttons for 
        // the user to choose from  
        int response = JOptionPane.showConfirmDialog(null,area,title,
                JOptionPane.YES_NO_OPTION,style, icon);
        if (response == JOptionPane.YES_OPTION) return true;
        else                                    return false;
    }
    
    /**
     * Asks the user for a string input in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param icon the icon used in the dialog
     * @return a valid string
     */
    public static String input(String text, Icon icon) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = Dialogs.formatArea(text);
        // Store the user's response in a variable from what they typed into
        // a input dialog
        String value = (String) JOptionPane.showInputDialog(null, area, title, 
                style, icon, null, "");
        // Force a loop if the user left the dialog empty and clicked "ok" or
        // they clicked "cancel" or the "X"
        while (value == null || value.equals("")) {
            // Recreate the graphical display area
            area  = Dialogs.formatArea(ERROR_1 + text);
            value = (String) JOptionPane.showInputDialog(null, area, title, 
                    style, icon, null, "");
        }
        return value;           // Once they have entered a value, return it
    }
    
    /**
     * Asks the user for a number (integer) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param icon the icon used in the dialog
     * @return a valid integer
     */
    public static int inputInteger(String text, ImageIcon icon) {
        String value = input(text, icon);     // Get user's response
        // Force a loop if the user did not enter a valid integer
        while (Numbers.isInteger(value) == false) {
            value = input(ERROR_1 + text, icon);
        }
        int number = Integer.parseInt(value);       // Convert to integer
        return number;                              // Return number value       
    }
    
    /**
     * Asks the user for a number (double) in a input
     * dialog box
     * 
     * @param text the text for the dialog box
     * @param icon the icon used in the dialog
     * @return a valid integer
     */
    public static double inputDouble(String text, ImageIcon icon) {
        String value = input(text, icon);     // Get user's response
        // Force a loop if the user did not enter a valid double
        while (Numbers.isDouble(value) == false) {
            value = input(ERROR_1 + text, icon);
        }
        double number = Double.parseDouble(value);       // Convert to double
        return number;                                   // Return number value
    }
    
    /**
     * Asks the user for a number (integer) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param minimum the lowest value in the input range
     * @param maximum the highest value in the input range
     * @param icon the icon used in the dialog
     * @return a valid integer
     */
    public static int inputInteger(String text, int minimum, int maximum, ImageIcon icon) {
        String value = input(text, icon);                 // Get user's response
        int number = 0;                             // Variable to store value
        boolean done = false;                       // Flag to keep trying
        while (!done) {                             // Loop until valid
            if (Numbers.isInteger(value) == false) {    // Not a valid integer
                value = input(ERROR_2 + text, icon);      // Input again with message
            }
            else {
                number = Integer.parseInt(value);   // Convert to integer
                if (Numbers.inRange(number, minimum, maximum)) {
                    done = true;                    // Number is in range
                }
                else {
                    value = input(ERROR_3 + text, icon); // Not in range, input again
                }
            }            
        }
        return number;                          // Return valid integer
    }
    
    /**
     * Asks the user for a number (integer) in a input dialog box
     * 
     * @param text the text for the dialog box
     * @param minimum the lowest value in the input range
     * @param maximum the highest value in the input range
     * @param icon the icon used in the dialog
     * @return a valid double
     */
    public static double inputDouble(String text, int minimum, int maximum, ImageIcon icon) {
        String value = input(text, icon);           // Get user's response
        double number = 0d;                         // Variable to store value
        boolean done = false;                       // Flag to keep trying
        while (!done) {                             // Loop until valid
            if (Numbers.isDouble(value) == false) { // Not a valid double
                value = input(ERROR_2 + text, icon);// Input again with message
            }
            else {
                number = Double.parseDouble(value); // Convert to double
                if (Numbers.inRange(number, minimum, maximum)) {
                    done = true;                    // Number is in range
                }
                else {
                    value = input(ERROR_3 + text, icon); // Not in range, input again
                }
            }            
        }
        return number;                              // Return valid double       
    }
    
    /**
     * Outputs text in a more visual graphical dialog 
     * 
     * @param text the text to display
     * @param icon the icon used in the dialog
     */
    public static void output(String text, ImageIcon icon) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = Dialogs.formatArea(text);
        // Add the display area to the dialog to show the user
        JOptionPane.showMessageDialog(null,area,title,style, 
                icon);
    }
    
    /**
     * @author Marissa
     * If an error is to occur, A message dialog will 
     * appear detailing what happened.
     * 
     * @param text the text to display
     */
    public static void outputError(String text) {
        // Add the display area to the dialog to show the user
        JOptionPane.showMessageDialog(null, text, 
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * @author Marissa
     * Outputs text in a more visual graphical dialog 
     * 
     * @param text the text to display
     * @param icon the icon used in the dialog
     * @param width the width of the dialog
     * @param height the height of the dialog
     */
    public static void output(String text, ImageIcon icon, 
            int width, int height) {
        // Create graphical display area with formatted text to put in dialog
        JScrollPane label = formatArea(text, width, height);
        // Add the display area to the dialog to show the user
        JOptionPane.showMessageDialog(null, label, title, style, icon);
    }
    
    /**
     * @author Marissa 
     * 
     * @param text the string of text to format
     * @param width the width of the dialog to format
     * @param height the height of the dialog to format
     * @return the formatted text area for display
     */
    public static JScrollPane formatArea (String text, int width, int height){
        // Setup a display area
        JLabel label = new JLabel("<html>" + text + "</html>");
        label.setFont(font);
        label.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        label.setPreferredSize(new Dimension(width,height));
        label.setBackground(background);
        label.setForeground(foreground);
        
        // Setup a scrollable area
        JScrollPane scroller = new JScrollPane(label);
        scroller.setPreferredSize(new Dimension(500, 400));
        //scroller.setBorder(BorderFactory.createEmptyBorder());
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setWheelScrollingEnabled(true);
        scroller.setFont(font);  
        
        return scroller;
    }
    
    /**
     * @author Marissa 
     * 
     * @param text the string of text to format
     * @param width the width of the dialog to format
     * @param height the height of the dialog to format
     * @return the formatted text area for display
     */
    public static JScrollPane formatArea2 (String text, int width, int height){
        
        // create a JEditorPane that renders HTML and defaults to the system font.
        JEditorPane editorPane
                = new JEditorPane(new HTMLEditorKit().getContentType(), text);
        // set the text of the JEditorPane to the given text.
        editorPane.setText(text);
    
        // Setup a display area
        JTextPane pane = new JTextPane();
        pane.setContentType("text/html"); // let the text pane know this is what you want
        //Font font = new Font("Serif", Font.ITALIC, 18);
        //pane.setFont(font);
        pane.setText("<html> <font face=\"Segoe UI\" size=\"9px\">" + text + "</font></html>");
        pane.setEditable(false); // as before
        pane.setBackground(null); // this is the same as a JLabel
        pane.setBorder(null); // remove the border
        pane.setPreferredSize(new Dimension(width,height));
        pane.setBackground(background);
        pane.setForeground(foreground);
        
        // Setup a scrollable area
        JScrollPane scroller = new JScrollPane(pane);
        scroller.setPreferredSize(new Dimension(500, 400));
        //scroller.setBorder(BorderFactory.createEmptyBorder());
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setWheelScrollingEnabled(true);
        scroller.setFont(font);  
        
        return scroller;
    }
        
    /**
     * Sets up a fancy display area for the text to display
     * 
     * @param text the string of text to format
     * @return the formatted text area for display
     */
    public static JTextArea formatArea(String text) {
        // Create a graphics object to display the graphical things
        JTextArea area = new JTextArea();
        
        area.setFont(font);
        area.setBackground(background);
        area.setText(text);
        return area;
    }
        
    /**
     * Ask the user if they want to play again, in a dialog box
     * 
     * @param icon the icon used in the dialog
     * @return true (yes, play again), false (no)
     */
    public static boolean playAgain(ImageIcon icon) {
        // Send the yesNo method the play again message
        return yesNo("Do you want to play again?", icon);
    }
    
    /**
     * Ask the user if they want to go back to the kitchen, in a dialog box
     * 
     * @param text the context of what you are doing
     * @param icon the icon used in the dialog
     * @return true (yes, go back to the kitchen), false (no)
     */
    public static boolean goBack(String text, ImageIcon icon) {
        // Send the yesNo method the play again message
        return yesNo(text + "Go Back?", icon);
    }
            
    /**
     * Presents an input dialog with a drop down selection of options
     * 
     * @param text the text to show in the dialog
     * @param options the array of option to have in the drop down
     * @param icon the icon used in the dialog
     * @return the option they choose
     */
    public static String choices(String text, String[] options, ImageIcon icon) {
        // Create graphical display area with formatted text to put in dialog
        JTextArea area = Dialogs.formatArea(text);
        // Add display area to dialog to show user which gives a drop-down
        // in the dialog and returns an "object"
        Object object = JOptionPane.showInputDialog(null, area, title, 
                                             style, icon, 
                                             options, options[0]);
        if (object == null)  return "";                 // User selects "X"
        else                 return object.toString();  // Return user's choice
    } 
    
    /**
     * Turns the string from Dialogs.choices into an int from the array used.
     * 
     * @param context the context of which goes into choices
     * @param array which array will be used
     * @param textCase (true) runs an if statement that turns text uppercase.
     * @return returns an int of which relates to position of the array
     */
    public static int chooseArray (String context, String[] array, 
            boolean textCase, ImageIcon icon){
        String type = choices(context, array, icon);
        if (textCase == true) type.toUpperCase();
        
        // Compares the "type" to the contents of the "array", 
        // when it finds a match, "array" will change to match the spot "i" is in
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (type.equals(array[i])) index = i;
        }
        return index;
    }
}
