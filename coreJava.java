// Copyright (c) 2002 MyHouse
//package ian;
//import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.DecimalFormat;
import java.time.temporal.JulianFields;
import java.time.LocalDate;
import java.time.LocalTime;
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
 * <p>
 *
 * JDK 14 Documentation
 * https://docs.oracle.com/en/java/javase/14/
 *
 * @author Ian Molloy April 2001
 * @version (#)coreJava.java        3.83 2020-05-22T13:21:32
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
   * A format of %03d will pad, for example, a 5 to 005.
   * topLevel.setLocationRelativeTo(null);
   */
  public void launchFrame() {
    System.out.printf("Start of test on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
//Java Unzip File Example
//https://www.journaldev.com/960/java-unzip-file-example
//Zip Slip Vulnerability (?)

System.out.println("\nTest one");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
LocalTime time = LocalTime.now();
System.out.println(formatter.format(time));


System.out.println("\nTest two");
LocalDateTime ldt = LocalDateTime.now();
System.out.println(ldt);
System.out.println(ldt.withNano(0));


System.out.println("\nTest three");
//Enum ChronoUnit
LocalDateTime ldt2 = LocalDateTime.now();
System.out.println(ldt2);
System.out.println(ldt2.truncatedTo(ChronoUnit.SECONDS));


System.out.println("\nTest four");
LocalDateTime ldt4 = LocalDateTime.now();
DateTimeFormatter df = DateTimeFormatter.ofPattern("D");
System.out.printf("Day number %s%n", df.format(ldt4));


System.out.println("\nTest five");
LocalDate ldt5 = LocalDate.now();
// JULIAN_DAY MODIFIED_JULIAN_DAY
DecimalFormat deci = new DecimalFormat("#######.0");
long jd = ldt5.getLong(JulianFields.JULIAN_DAY);
System.out.printf("Day number %s %n", deci.format(jd - 0.5));

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
