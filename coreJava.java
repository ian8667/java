// Copyright (c) 2002 MyHouse
//package ian;
//import java.time.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Consumer;
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
 * <p>
 *
 * Which one of these is best to use:
 * String result = String.format("%032x%n", new BigInteger(1, myBytes));
 * String md5Result = new BigInteger(1, md5.digest()).toString(16);
 *
 * JDK 14 Documentation
 * https://docs.oracle.com/en/java/javase/14/
 * Java SE API docs
 * https://docs.oracle.com/en/java/javase/14/docs/api/index.html
 *
 * Apache Hadoop Main 3.2.1 API
 * https://hadoop.apache.org/docs/stable/api/index.html
 *
 * @author Ian Molloy April 2001
 * @version (#)coreJava.java        3.90 2020-07-20T13:14:33
 */
public class coreJava {
private byte dummy;
  /**
   * Constructor
   */
  public coreJava() {
    this.dummy = 99;
    launchFrame();
  }//end of constructor

  /**
   * Working test method.
   * Floating point formatting to decimal places: %.2f
   * A format of %07d will pad, for example, a 7 to 007.
   * topLevel.setLocationRelativeTo(null);
   */
  public void launchFrame() {
    System.out.printf("Start of test on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
//Java Unzip File Example
//https://www.journaldev.com/960/java-unzip-file-example
//Zip Slip Vulnerability (?)


String fred2 = String.format("fred formatted number is %,d", 2000000);
System.out.printf("%s%n", fred2);
    // ---------------------------------------------------------------
    System.out.printf("End of test on %tc%n", new java.util.Date());
  } //end of launchFrame

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
  } //end of main

} //end of class
