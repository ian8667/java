// Copyright (c) 2002 MyHouse
//package ian;
//import javafx.beans.property.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
/**
 * Uncompresses (expands) a file which has been compressed
 * with the GZIP file format.
 *
 * @author Ian Molloy June 2019
 * @version (#)Uncompress_Gzip.java        1.00 2019-06-01
 */
public class Uncompress_Gzip {
private byte dummy;
  /**
   * Constructor
   */
  public Uncompress_Gzip() {
    this.dummy = 99;
    launchFrame();
  }//end of constructor

  /**
   * Working method.
   */
  public void launchFrame() {
    System.out.printf("Start of compress on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
    String inputFile = "C:\\Test\\gashoutputlarge.gz";
    String outputFile = "C:\\Test\\uncompressoutputlarge.txt";
    long bytestransferred = 0L;

    try (// Input objects
         FileInputStream fis = new FileInputStream(inputFile);
         GZIPInputStream gis = new GZIPInputStream(fis);
         BufferedInputStream buffread = new BufferedInputStream(gis);
         // Output objects
         FileOutputStream fos = new FileOutputStream(outputFile);
         BufferedOutputStream buffwrite = new BufferedOutputStream(fos); )
    {

         bytestransferred = buffread.transferTo(buffwrite);

    } catch (FileNotFoundException e) {
      System.err.println("File not found problem");
      e.printStackTrace();
    } catch (IOException e2) {
      System.err.println("IO problem of some kind");
      e2.printStackTrace();
    } //end try/catch block

    System.out.println("\nFiles used");
    System.out.printf("Input file: %s%nOutput file: %s%n", inputFile, outputFile);
    System.out.printf("bytestransferred: %d bytes%n", bytestransferred);

    // ---------------------------------------------------------------
    System.out.printf("End of compress on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
     Uncompress_Gzip kompress = new Uncompress_Gzip();
  }//end of main

}//end of class
