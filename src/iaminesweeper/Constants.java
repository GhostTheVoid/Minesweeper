/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;

 
/**
 * Constants.java - the various game constants
 *
 * -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Constants {
    
    //SHARED GAME SETTINGS
    public static GameEngine gameEngine;
    public static Difficulties difficulties;
    
    public final static int    CELL_WIDTH  = 16;            // Width of each label
    public final static int    CELL_HEIGHT = CELL_WIDTH;    // Size of the labels
    public final static double CELL_RATIO  = 4.85;          // Ratio of labels
    public final static String CELL_BOMB           = "X";   // Bomb cell text
    public final static String CELL_BLANK          = "";    // Blank cell text
    public final static int    CELL_INVALID_NUMBER = -1;    // Invalid number

    public final static int    TIMER_MOVE_AMOUNT        = 0;
    public final static int    FACE_MOVE_AMOUNT         = 0;
    public final static int    CELL_MOVE_AMOUNT         = 0;
    
    public final static int    TIMER_TIMER_DELAY        = 1000;
    public final static int    FACE_TIMER_DELAY         = 100;
    public final static int    CELL_TIMER_DELAY         = 100;
    
    public final static int    TIMER_ANIMATION_DELAY    = 1000;
    public final static int    FACE_ANIMATION_DELAY     = 1000;
    public final static int    CELL_ANIMATION_DELAY     = 1000;
    
    public final static String PLAYER_DATA_FILE    = "/media/data.txt";
    public final static String SETTINGS_DATA_FILE  = "/media/settings.txt";
    public final static String SPRITE_SHEET        = "/media/minesweeper.png";
    
    public static LinkedList<String> settings; // The settings for the animations
    
    // Num
    public final static String NUM_1_TAG                = "num1";
    public final static String NUM_2_TAG                = "num2";
    public final static String NUM_3_TAG                = "num3";
    public final static String NUM_4_TAG                = "num4";
    public final static String NUM_5_TAG                = "num5";
    public final static String NUM_6_TAG                = "num6";
    public final static String NUM_7_TAG                = "num7";
    public final static String NUM_8_TAG                = "num8";
    public final static String NUM_9_TAG                = "num9";
    public final static String NUM_0_TAG                = "num0";
    public final static String NUM_DASH_TAG             = "numDash";
    public final static String NUM_BLANK_TAG            = "numBlank";
    // Face
    public final static String FACE_DEFAULT_TAG         = "faceDefault";
    public final static String FACE_CLICK_TAG           = "faceReset";
    public final static String FACE_RESET_TAG           = "faceClick";
    public final static String FACE_WIN_TAG             = "faceWin";
    public final static String FACE_LOSE_TAG            = "faceLose";
    // Cells
    public final static String CELL_1_TAG               = "cell1";
    public final static String CELL_2_TAG               = "cell2";
    public final static String CELL_3_TAG               = "cell3";
    public final static String CELL_4_TAG               = "cell4";
    public final static String CELL_5_TAG               = "cell5";
    public final static String CELL_6_TAG               = "cell6";
    public final static String CELL_7_TAG               = "cell7";
    public final static String CELL_8_TAG               = "cell8";
    public final static String CELL_DEFAULT_TAG         = "cellDefault";
    public final static String CELL_CLICK_TAG           = "cellClick";
    public final static String CELL_FLAG_TAG            = "cellFlag";
    public final static String CELL_UNKNOWN_TAG         = "cellUnknown";
    public final static String CELL_UNKNOWN_CLICK_TAG   = "cellUnknownClick";
    public final static String CELL_BOMB_TAG            = "cellBomb";
    public final static String CELL_BOMB_HIT_TAG        = "cellBombHit";
    public final static String CELL_BOMB_CHECKED_TAG    = "cellBombChecked";
}
