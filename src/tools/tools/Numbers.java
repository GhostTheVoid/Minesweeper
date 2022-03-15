/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 * Numbers.java - a collection of useful methods for working with numbers
 *
 * @author Mr. Wachs
 * @since Nov. 21, 2019, 3:49:35 p.m.
 */
public class Numbers {
    
    // Encapsulated global class properties below..............................
    
    private static final char NEGATIVE = '-';
    private static final char DECIMAL  = '.';
    
    // Public and static (shared, does not an object) methods below............ 
    
    /**
     * Checks the value to see if it contains numerical characters or a "-"
     * 
     * @param value the string of characters to check
     * @return is a number (true) or not (false)
     */
    public static boolean isInteger(String value) {
        // Convert string into array of char values (built-in string method)
        char[] characters = value.toCharArray();
        // Loop (traverse) through this array from start to end
        for (int i = 0; i < characters.length; i++) {    
            // Use built-in method to see if this character is a digit (0-9)
            if (Character.isDigit(characters[i]) == false) {
                // Character was not a digit, but is it the negative sign 
                if (characters[i] != NEGATIVE) {
                    return false;       // Not a valid character for a integer
                }
            }
        }
        return true;                    // All characters valid for a integer
    }
    
    /**
     * Checks the value to see if it contains numerical characters or a "-" 
     * or a "."
     * 
     * @param value the string of characters to check
     * @return is a number (true) or not (false)
     */
    public static boolean isDouble(String value) {
        // Convert string into array of char values (built-in string method)
        char[] characters = value.toCharArray();
        // Loop (traverse) through this array from start to end
        for (int i = 0; i < characters.length; i++) {         
            // Use built-in method to see if this character is a digit (0-9)
            if (Character.isDigit(characters[i]) == false) {
                // Character was not a digit, but is it the negative sign or
                // the decimal symbol
                if (characters[i] != DECIMAL && 
                    characters[i] != NEGATIVE) {
                    return false;       // Not a valid character for a double
                }
            }
        }
        return true;                    // All characters valid for a double
    }
    
    /**
     * Determines if a number if odd or even
     * 
     * @param number the number to check
     * @return true (if even), false (if odd)
     */
    public static boolean isEven(int number) {
        if (number % 2 == 0) return true;   // Number divides evenly by zero
        else                 return false;  // Number does not divide by zero
    }
    
    /**
     * Checks to make sure the number is in the range
     * 
     * @param number the number to check
     * @param low lowest in the range
     * @param high highest in the range
     * @return in range (true) or not (false)
     */
    public static boolean inRange(int number, int low, int high) {
        if (number >= low && number <= high) return true;
        return false;
    }
    
    /**
     * Checks to make sure the number is in the range
     * 
     * @param number the number to check
     * @param low lowest in the range
     * @param high highest in the range
     * @return in range (true) or not (false)
     */
    public static boolean inRange(double number, double low, double high) {
        if (number >= low && number <= high) return true;
        return false;
    }
    
    /**
     * Generate a random number (a double) in a range
     * 
     * @param low the lowest double in the range
     * @param high the highest double in the range
     * @return random double in the range
     */
    public static double random(double low, double high) {
        return ((high - low + 1d) * Math.random() + low);
    }
    
    /**
     * Generate a random number (an integer) in a range
     * 
     * @param low the lowest integer in the range
     * @param high the highest integer in the range
     * @return random integer in the range
     */
    public static int random(int low, int high) {
        return (int)(random((double)low, (double)high));
    }
    
    /**
     * Generates an array of random integers in the range between low and high
     * 
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @param size the size to make the array
     * @return an array of random integers
     */
    public static int[] random(int low, int high, int size) {
        int[] numbers = new int[size];      // Create empty array of passed size
        for (int i = 0; i < size; i++) {    // Traverse array 
            numbers[i] = random(low,high);  // Assign random value to each index
        }
        return numbers;                     // Return completed array
    }
    
    /**
     * Generates an array of random doubles in the range between low and high
     * 
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @param size the size to make the array
     * @return an array of random doubles
     */
    public static double[] random(double low, double high, int size) {
        double[] numbers = new double[size]; // Create empty array 
        for (int i = 0; i < size; i++) {    // Traverse array 
            numbers[i] = random(low,high);  // Assign random value to each index
        }
        return numbers;                     // Return completed array
    }
   
    /**
     * Generates a matrix of random integers in the range between low and high
     * 
     * @param rows the number of rows for the matrix
     * @param columns the number of columns for the matrix 
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @return a matrix of random integers
     */
    public static int[][] random(int rows, int columns, int low, int high) {
        int[][] matrix = new int[rows][columns];    // Create empty matrix
        for (int row = 0; row < rows; row++) {      // Traverse rows
            matrix[row] = random(low, high, columns);   // Create random row
        }
        return matrix;                              // Return completed matrix
    }
    
    /**
     * Generates a matrix of random doubles in the range between low and high
     * 
     * @param rows the number of rows for the matrix
     * @param columns the number of columns for the matrix 
     * @param low the lowest number in the range
     * @param high the highest number in the range
     * @return a matrix of random doubles
     */
    public static double[][] random(int rows, int columns, double low, 
            double high) {
        double[][] matrix = new double[rows][columns];    // Create empty matrix
        for (int row = 0; row < rows; row++) {      // Traverse rows
            matrix[row] = random(low, high, columns);   // Create random row
        }
        return matrix;                              // Return completed matrix
    }
}
