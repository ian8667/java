// Copyright (c) 2002 MyHouse
//package ian;
//import javafx.beans.property.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Compresses a file using the GZIP file format.
 *
 * @author Ian Molloy May 2019
 * @version (#)Compress_Gzip.java        1.00 2019-05-22
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
   * Working test method.
   * Floating point formatting to decimal places: %.2f
   * A format of %03d will pad, for example, a 5 to 005.
   * topLevel.setLocationRelativeTo(null);
   */
  public void launchFrame() {
    System.out.printf("Start of test on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
    String inputFile = "C:\\Test\\small_sampledata.txt";
    String outputFile = "C:\\Test\\gashoutput.gz";
byte[] databuffer = new byte[1024 * 4];

int bytesread = 0;

try (FileInputStream fis = new FileInputStream(inputFile);
     FileOutputStream fos = new FileOutputStream(outputFile);
     GZIPOutputStream gos = new GZIPOutputStream(fos);)
{

while ((bytesread = fis.read(databuffer)) != -1) {
       gos.write(databuffer, 0, bytesread);
}

} catch (FileNotFoundException e) {
  System.err.println("File not found problem");
  e.printStackTrace();
} catch (IOException e2) {
  System.err.println("IO problem of some kind");
  e2.printStackTrace();

}

    // ---------------------------------------------------------------
    System.out.printf("End of test on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
     Compress_Gzip kompress = new Compress_Gzip();
  }//end of main

}//end of class
