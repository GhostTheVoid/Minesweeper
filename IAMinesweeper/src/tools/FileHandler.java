/** required package class namespace */
package tools;


/** required imports */
import collections.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * FileHandler.java - various methods for saving and opening files
 *
 * @author Mr. Wachs
 * @since 14-May-2019 
 */
public class FileHandler 
{
    
    private String filename;
    
    
    /**
     * Default constructor for the class, sets class properties
     * 
     * @param filename the filename to associate the class with 
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Reads the data from the file and returns all the lines as a string array
     * 
     * @return a string array of all the lines from the file 
     */
    public LinkedList<String> read() {
        try {                                                   // error trap
            //File           file   = convertToFile(filename);    // create file
            File file = new File (filename);
            FileReader     reader = new FileReader(file);       // create reader
            BufferedReader buffer = new BufferedReader(reader); // create buffer
            LinkedList<String> lines = new LinkedList<>();            
            String line   = buffer.readLine();                  // read a line
            while (line != null) {
                lines.add(line);
                line = buffer.readLine(); 
            }
            buffer.close();                                     // close file
            return lines;                                       // return array
        }
        catch (IOException e) {                                 // no file
            System.out.println("I/O error");
        }
        catch (NullPointerException e) {                        // read a null
            System.out.println("Read Null error");
        }
        return null;                                            // error caught
    }

    /**
     * Writes the passed data to the file
     * 
     * @param data string array to write to the file
     */
    public void write(LinkedList<String> data) {
        try {                                               // error trap
            
            //File        file    = convertToFile(filename);  // create file
            
            File file = new File (filename);
            
            FileWriter  writer  = new FileWriter(file);     // create writer
            
            
            PrintWriter printer = new PrintWriter(writer);  // create printer
            for (int i = 0; i < data.size(); i++) {         // traverse array
                printer.println(data.get(i));               // write array index
            }
            printer.close();                                // close file
            
        }
        catch (IOException e) {                             // no file
            System.out.println("I/O error");
        }
        catch (NullPointerException e) {                    // read a null
            System.out.println("Write Null error");
        }
    }
    
    /**
     * Converts the file name string into a file object relative to the 
     * application package
     * 
     * @param filename the location of the file
     * @return a file object converted from the file name string 
     */
    public File convertToFile(String filename) {
        try {                                               // error trap
            URL  url  = getClass().getResource(filename);   // convert to URL
            URI  uri  = url.toURI();                        // convert to URI
            System.out.println("errorfix");
            File file = new File(uri);                      // create file
            if (!file.exists()) file.createNewFile();       // create new file
            return file;                                    // return new file
        }
        catch (URISyntaxException ex) {                     // URI error caught
            System.out.println("URI error");
        } 
        catch (IOException ex) {                            // file path error
            System.out.println("I/O error");
        }
        catch (NullPointerException e) {                    // read a null
            System.out.println("Write Null error URI" + e.toString());
        }
        return null;                                        // error caught
    }
    
    /**
     * Deletes a file from its folder
     * 
     * @param fileName the location and name of a file
     * @param reason The reason that the file was deleted
     */
    public void deleteFile(String fileName, String reason) {
        File myObj = new File(fileName);
        System.out.println("Running deleteFile on " + myObj.getName() +".......");
        if (myObj.delete()) {
            System.out.println("\tDeleted the file: " + myObj.getName());
            
            System.out.println("\nAn Error Occured reading CustomRecipe: " 
                    + myObj.getName() +"\n"
                    + "ERROR: " + reason 
                    + "\nRestarting now.....\n");
            
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}
