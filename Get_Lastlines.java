// Copyright (c) 2002 MyHouse
//package ian;
import java.nio.channels.FileChannel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
/**
 * Obtains the last N bytes of a file writing those bytes to a
 * separate file. The number of bytes (N) to obtain is determined
 * by the user.
 *
 * Java SE API docs
 * https://docs.oracle.com/en/java/javase/14/docs/api/index.html
 *
 * @author Ian Molloy June 2019
 * @version (#)Get_Lastlines.java        1.01 2020-07-18T13:47:38
 */
public class Get_Lastlines {
private byte dummy;
  /**
   * Constructor
   */
  public Get_Lastlines() {
    this.dummy = 99;
    launchFrame();
  }//end of constructor
   
  /**
   * Working method.
   */
  public void launchFrame() {
    System.out.printf("Start of run on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
   System.out.println("Hello world");
   final int kb1 = 1024;     // One Kilobyte
   final int mb1 = 1048576;  // One Megabyte

   final String inputfile  = "C:\\test\\small_sampledata.txt";
   final String outputfile = "C:\\test\\gashoutput.txt";
   // Number of wanted bytes to obtain from end of file.
   final int wantedBytes = (kb1 * 1);
   final long fileLength = (new File(inputfile).length());

   //As the number of wanted bytes may be greater than the
   //file length, we make the assumption the whole file
   //is to be copied to the destination file. So this
   //means the file position will start from file position
   //zero (the beginning of the file and hence why a
   //file position of zero).
   long filePos = 0L;

   // Set the file position (pointer) to the required postion
   // in the file to give us the required number bytes to the
   // end of the file. In other words, we skip over the bytes
   // at the start of the file we're not interested in.
   if (wantedBytes < fileLength) {
      filePos = fileLength - wantedBytes;
   }

   long bytestransferred = 0L;

   System.out.println("");
   System.out.printf("Input file length is %d bytes%n", fileLength);
   System.out.printf("Number of bytes to transfer to output file: %d%n", wantedBytes);


   try (FileChannel inChan = (new FileInputStream(inputfile)).getChannel();
        FileChannel outChan = (new FileOutputStream(outputfile)).getChannel(); )
   {

    //Returns: The number of bytes, possibly zero, that were
    //actually transferred
    bytestransferred = inChan.transferTo(filePos, //position within the file at which the transfer is to begin
                                         (fileLength - filePos), //maximum number of bytes to be transferred
                                         outChan); //destination channel

   } catch (Exception e1) {
      e1.printStackTrace();
   } finally {
      System.out.println("\nFiles used");
      System.out.printf("Input: %s%nOutput: %s%n", inputfile, outputfile);
      System.out.printf("Actual bytes transferred: %d%n", bytestransferred);

      System.out.println("\nAll done now");
   }

    // ---------------------------------------------------------------
    System.out.printf("End of run on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
         new Get_Lastlines();
       }
    });
  }//end of main

}//end of class
