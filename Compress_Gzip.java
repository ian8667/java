// Copyright (c) 2002 MyHouse
//package ian;
//import javafx.beans.property.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
/**
 * Compresses a file using the GZIP file format.
 *
 * @author Ian Molloy May 2019
 * @version (#)Compress_Gzip.java        1.02 2019-06-01
 */
public class Compress_Gzip {
private byte dummy;
  /**
   * Constructor
   */
  public Compress_Gzip() {
    this.dummy = 99;
    launchFrame();
  }//end of constructor

  /**
   * Working method.
   */
  public void launchFrame() {
    System.out.printf("Start of compress on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
    String inputFile = "C:\\Test\\very_smalldata.txt";
    String outputFile = "C:\\Test\\gashoutput.gz";

    long bytestransferred = 0L;
    long filelength = (new File(inputFile)).length();
    long difference = 0L;

    try (// Input objects
         FileInputStream fis = new FileInputStream(inputFile);
         BufferedInputStream buffread = new BufferedInputStream(fis);
         // Output objects
         FileOutputStream fos = new FileOutputStream(outputFile);
         GZIPOutputStream gos = new GZIPOutputStream(fos);
         BufferedOutputStream buffwrite = new BufferedOutputStream(gos); )
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

    difference = Math.abs(filelength - bytestransferred);
    System.out.printf("\nInput file length: %d bytes%n", filelength);
    System.out.printf("Bytes transferred to Gzip: %d bytes%n", bytestransferred);
    System.out.printf("Difference: %d bytes%n%n", difference);

    // ---------------------------------------------------------------
    System.out.printf("End of compress on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
     Compress_Gzip kompress = new Compress_Gzip();
  }//end of main

}//end of class
