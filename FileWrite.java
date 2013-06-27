// Copyright (c) 2001 MyHouse
//package training;
import java.io.*;
/**
 * <p>
 * Convenience class for writing to character (text) files.</p>
 *
 * @author Ian Molloy November 2001
 * @version (#)FileWrite.java        1.07 2009/06/04
 */
public class FileWrite {
private BufferedWriter buffout;
private int recCount;
  /**
   * Constructs a new FileWrite, given the filename to write to.
   *
   * @param outputFilename the file to write to
   */
  public FileWrite(String outputFilename) {
    recCount = 0;
    try {
      buffout = new BufferedWriter(new FileWriter(outputFilename));
    } catch (IOException e) {
      System.err.println("Error: problems opening output file "+outputFilename);
      e.printStackTrace();
    }
  }//end of constructor

  /**
   * Writes a line (record) to the output file.
   * @param outrec the line (record) to write to the file.
   */
  public void writeLine(String outrec) {
   try {
     buffout.write(outrec);
     buffout.newLine();
     recCount++;
   } catch (IOException e) {
	   e.printStackTrace();
   }
  }//end of writeLine

  /**
   * Close the output file.
   */
  public void close() {
    try {
      buffout.flush();
      buffout.close(); //Close output file.
      buffout = null;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }//end of close

  /**
   * Determines if the output stream is still open.
   * @return true if the stream is still open, false otherwise.
   */
  public boolean isOpen() {
    return (buffout != null);
  }//end of isOpen

  /**
   * Writes a blank line (record) to the output file.
   */
  public void writeBlankLine() {
   try {
     buffout.newLine();
     recCount++;
   } catch (IOException e) {
	   e.printStackTrace();
   }
  }//end of writeBlankLine

  /**
   * Obtains the current line (record) number.
   * @return the number of records read from the file
   */
  public int getRecordCount() {
    return recCount;
  }//end of getRecordCount

}//end of class
