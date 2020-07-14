// Copyright (c) 2002 MyHouse
//package ian;
import java.nio.channels.FileChannel;
import java.io.File;
import java.io.FileInputStream;
/**
 * Obtains the last N bytes of a file writing those bytes to a
 * separate file. The number of bytes (N) to obtain is determined
 * by the user.
 *
 * @author Ian Molloy June 2019
 * @version (#)Get_Lastlines.java        1.00 2019-06-05
 */
public class Get_Lastlines {
private byte dummy;
private long ChunkSize;
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
   final int kb1 = 1024; // One Kilobyte
   final int mb1 = 1048576; // One Megabyte

   final String inputfile = "C:\\test\\small_sampledata.txt";
   final String outputfile = "C:\\test\\gashoutput.txt";
   // Number of bytes to obtain from end of file.
   final int lastbytes = (kb1 * 5);

   final long fileLength = (new File(inputfile).length());

   final long filepointer = (fileLength - lastbytes);
   long bytestransferred = 0L;
   System.out.println("");
   System.out.printf("Input file length is %d bytes%n", fileLength);
   System.out.printf("Number of bytes to obtain: %d%n", lastbytes);

   try (FileChannel inChan = (new FileInputStream(inputfile)).getChannel();
        FileChannel outChan = (new FileInputStream(outputfile)).getChannel(); )
   {

    bytestransferred = inChan.transferTo(filepointer,
                                         (long)lastbytes,
                                         outChan);

   } catch (Exception e1) {
      e1.printStackTrace();
   } finally {
      System.out.println("Files used");
      System.out.printf("Input: %s%nOutput: %s%n", inputfile, outputfile);
      System.out.println("All done now.");
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
