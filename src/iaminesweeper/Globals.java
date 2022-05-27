/** Required package class namespace */
package iaminesweeper;

import collections.LinkedList;

 
/**
 * Globals.java - Variables the game uses globally

 -----------------------
 * @author Marissa Rowles
 * @since 16-Feb-2022
 */
public class Globals {
    
    // SHARED GAME SETTINGS
    // ================
    
    public static GameEngine   gameEngine;
    public static GameGrid     gameGrid;
    public static Difficulties difficulties;
    
    public static ResetButton resetButton;
    public static TimeTracker timeTracker;
    public static FlagTracker flagTracker;
    
    // PUBLIC FINAL STATIC
    // ================
    
    public final static int    CELL_WIDTH  = 16;            // Width of each label
    public final static int    CELL_HEIGHT = CELL_WIDTH;    // Size of the labels
    public final static double CELL_RATIO  = 4.85;          // Ratio of labels
    public final static String CELL_BOMB           = "X";   // Bomb cell text
    public final static String CELL_BLANK          = "";    // Blank cell text
    public final static int    CELL_INVALID_NUMBER = -1;    // Invalid number

    public final static int    FACE_MOVE_AMOUNT         = 0;
    public final static int    FACE_TIMER_DELAY         = 100;
    public final static int    FACE_ANIMATION_DELAY     = 1000;
    
    public final static String PLAYER_DATA_FILE    = "/media/data.txt";
    public final static String SETTINGS_DATA_FILE  = "/media/settings.txt";
    public final static String SPRITE_SHEET        = "/media/minesweeper.png";
    
    public static LinkedList<String> settings; // The settings for the animations
    // Face Sprites
    public final static String FACE_DEFAULT_TAG         = "faceDefault";
    public final static String FACE_CLICK_TAG           = "faceReset";
    public final static String FACE_RESET_TAG           = "faceClick";
    public final static String FACE_WIN_TAG             = "faceWin";
    public final static String FACE_LOSE_TAG            = "faceLose";
}
