// Copyright (c) 2001 MyHouse
//package training;
import java.io.*;
/**
 * <p>
 * Convenience class for reading character (text) files.
 * From the calling program, loop condition for reading from file.
 * <pre><code>
 *    while ((inrec = myinput.readLine()) != null) {
 *        //do something
 *    }
 *
 * or
 *
 *    inrec = myinput.readLine();
 *    while (inrec != null) {
 *        //do something
 *        inrec = myinput.readLine();
 *     }
 * </code></pre>
 * </p>
 *
 * String outputEncoding = "US-ASCII";
 *
 * @author Ian Molloy November 2001
 * @version (#)FileRead.java        1.10 2009/07/19
 */
public class FileRead {
private String inrec;
private BufferedReader buffin;
private int recCount;
  /**
   * Constructs a new FileRead, given the filename to read from.
   *
   * @param inputFilename the file to read from
   */
  public FileRead(String inputFilename) {
    inrec = "";
    recCount = 0;
    try {
      buffin = new BufferedReader(new FileReader(inputFilename));
    } catch (FileNotFoundException e) {
      System.err.printf("Error: problems opening input file %s\n", inputFilename);
      e.printStackTrace();
    }
  }//end of constructor

  /**
   * Read a line (record) from file.
   * @return the line (record) read from the file.
   */
  public String readLine() {
    try {
      inrec = buffin.readLine();
      recCount++;
    } catch (IOException e) {
      System.err.println("Problems reading from file");
      e.printStackTrace();
    }
    return inrec;
  }//end of readLine

  /**
   * Close the input file.
   */
  public void close() {
    try {
      buffin.close();
      buffin = null;
    } catch (IOException e) {
      System.err.println("Problems closing the file");
      e.printStackTrace();
    }
  }//end of close

  /**
   * Obtains the current line (record) number.
   * @return the number of records read from the file
   */
  public int getRecordCount() {
    return recCount;
  }//end of getRecordCount

  /**
   * Determines if the input stream is still open.
   * @return true if the stream is still open, false otherwise.
   */
  public boolean isOpen() {
    return (buffin != null);
  }//end of isOpen
}//end of class
