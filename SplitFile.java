// Copyright (c) 2002 MyHouse
//package ian;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.channels.FileChannel;
/**
 * As the name suggests, this program is used to split or break a
 * large text file into the pieces in the same manner as the Linux
 * and Unix systems command 'split'.
 * <p>
 * This program requires the use of properties file SplitFile.properties
 * <p>
 * java.util Class Properties is used to define the following
 * two properties:
 * <ol>
 *   <li>inputfilename - input text file to be split into pieces</li>
 *   <li>chunksize - how big these pieces (chunks) are</li>
 * </ol>
 *
 * @author Ian Molloy May 2019
 * @version (#)SplitFile.java        1.00 2019-05-19
 */
public class SplitFile {
private byte dummy;
private String Inputfilename;
private long ChunkSize;
  /**
   * Constructor
   */
  public SplitFile() {
    this.dummy = 99;
    Inputfilename = "";
    ChunkSize = 0L;
    launchFrame();
  }//end of constructor

  /**
   * Obtains the properties required by the program from a
   * properties file
   *
   * @throws IOException if there is a problem with the properties file
   */
  private void getProperties() {

    // Properties file required by the program.
    final String propsfile = "C:\\family\\ian\\SplitFile.properties";
    Properties props = new Properties();

    try (FileInputStream fstream = new FileInputStream(propsfile)) {

        // load a properties file
        props.load(fstream);

        // get the property value and print it out
        Inputfilename = props.getProperty("inputfilename");
        ChunkSize = Long.parseLong(props.getProperty("chunksize"));

        System.out.printf("Input filename: %s%n", Inputfilename);
        System.out.printf("Chunk size: %d bytes%n", ChunkSize);

    } catch (IOException ex) {
        ex.printStackTrace();
    }

  } //end method getProperties

  /**
   * Creates a FileChannel object which will be used to write
   * the next chunk of the file being split up to disk.
   *
   * @param fname the input filename from which the chunk filename is derived
   * @param seqNum a sequence number which helps number the chnunks created
   *        and forms part of the chunk filename
   * @return a filechannel object
   * @throws FileNotFoundException if the FileChannel chunk cannot be created
   */
  private FileChannel getChunkChannel(String fname, int seqNum) throws FileNotFoundException
  {
    StringBuilder sb = new StringBuilder(fname);
    String newNum = String.format("_%04d", seqNum);
   
    int pos = fname.lastIndexOf(".");
    if (pos == -1) {
      // We don't appear to have a file extension.
      sb.append(newNum);
    } else {
      sb.insert(pos, newNum);
    }
   
    return (new FileOutputStream(sb.toString()).getChannel());
   } //end method getChunkChannel
   
  /**
   * Working method.
   */
  public void launchFrame() {
    System.out.printf("Start of run on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
   System.out.println("Hello world");
   // Get the properties required for the program.
   getProperties();

   int chunkIndex = 0;

   // Position of where we are in the file.
   long filePosition = 0L;

   long fileLength = (new File(Inputfilename).length());
   System.out.printf("Input file length is %d bytes%n", fileLength);

   FileChannel chunkChan;

   System.out.println("");

   try (FileChannel inChan = (new FileInputStream(Inputfilename)).getChannel())
   {

    // loop to process the input file.
    while (filePosition < fileLength) {

      // Get the next chunk channel to write to.
      chunkIndex++;
      chunkChan = getChunkChannel(Inputfilename, chunkIndex);

      System.out.printf("Writing to chunk# %d%n", chunkIndex);

      // Write a portion (chunk) of the input file as a file to disk.
      try {
         filePosition += inChan.transferTo(filePosition, ChunkSize, chunkChan);
      } catch (Exception e3) {
         e3.printStackTrace();
      } finally {
         chunkChan.close();
      }

    } //end while loop

    System.out.printf("%nInput file split into %d chunks%n", chunkIndex);

   } catch (FileNotFoundException e1) {
      e1.printStackTrace();
   } catch (IOException e2) {
      e2.printStackTrace();
   } finally {
      String parent = (new File(Inputfilename).getParent());
      System.out.printf("%nSee directory %s for the files concerned%n", parent);
      System.out.println("All done now. Input file split complete");
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
         new SplitFile();
       }
    });
  }//end of main

}//end of class
