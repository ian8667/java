// Copyright (c) 2002 MyHouse
//package ian;
//import javafx.beans.property.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.channels.FileChannel;
// Hadoop imports
/**
 * <p>A file to practice my Java as I go through the book
 * 'Core Java Volume 2 - Advanced Features'.</p>
 * <p><i>import</i> statements <b>must</b> go above this comment.</p>
 *
 * <p>Testing a Swing Look and Feel - see https://laffy.dev.java.net/</p>
 *
 * <p>Sets the frame to the centre of the screen:<br />
 *   <JFrame>.setLocationRelativeTo(null);</p>
 *
 * <p><pre>Another useful 'main' method.
 *   public static void main(String[] args) {
 *     gmt var1 = new gmt();
 *     //
 *     var1.getTimeInfo();
 *     System.out.println("\nEnd of run for gmt");
 *     System.exit(0);
 *   }//end of main</pre></p>
 *
 * <p>1 millisecond=0.001 second. ie 5000 milliseconds=5 seconds.</p>
 *
 * <p>javac -Xlint:all coreJava.java</p>
 *
 * <p>enum Season { WINTER, SPRING, SUMMER, FALL }<br />
 * http://java.sun.com/j2se/1.5.0/docs/guide/language/enums.html</p>
 *
 * <p>java -Xrunhprof:help<br />
 * HPROF: Heap and CPU Profiling Agent (JVMTI Demonstration Code)</p>
 *
 * <p>String outputEncoding = "US-ASCII";</p>
 *
 * <blockquote>
 * <pre>
 *    Some sample text as Sun Microsystems, Inc does it.
 * </pre>
 * </blockquote>
 *
 * <p>JDK 12 Documentation<br />
 * https://docs.oracle.com/en/java/javase/12/</p>
 *
 * @author Ian Molloy April 2001
 * @version (#)coreJava.java        3.76 2019-05-18
 */
public class coreJava {
private byte dummy;
private String Inputfilename;
private long ChunkSize;
  /**
   * Constructor
   */
  public coreJava() {
    this.dummy = 99;
    Inputfilename = "";
    ChunkSize = 0L;
    launchFrame();
  }//end of constructor

private void getProperties() {

    final String propsfile = "C:\\family\\ian\\coreJava.properties";
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


private FileChannel getChunkChannel(String fname, int seqNum) throws FileNotFoundException {
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
   * Working test method.
   * Floating point formatting to decimal places: %.2f
   * A format of %03d will pad, for example, a 5 to 005.
   * topLevel.setLocationRelativeTo(null);
   */
  public void launchFrame() {
    System.out.printf("Start of test on %tc%n", new java.util.Date());
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
    System.out.printf("End of test on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
         new coreJava();
       }
    });
  }//end of main

}//end of class
