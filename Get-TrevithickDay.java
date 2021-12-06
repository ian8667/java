// Copyright (c) 2002 MyHouse
//package ian;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;
import java.util.function.Consumer;

/**
 * <p>Trevithick Day.<br />
 * It’s the day of the year (usually the last Saturday in April)
 * when local engineering genius and inventor Richard Trevithick
 * (1771-1833) gets the recognition he deserves and Camborne
 * celebrates its importance as a major player in Cornwall’s
 * former mining industry. Lots of spluttering steam engines,
 * singing, dancing and parades.</p>
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
 * String md5Result = String.format("%032x%n", new BigInteger(1, myBytes));
 * String md5Result = new BigInteger(1, md5.digest()).toString(16);
 *
 * Documentation:
 *
 * JDK 17 Documentation
 * https://docs.oracle.com/en/java/javase/17/
 *
 * Java SE Version 17 API docs
 * https://docs.oracle.com/en/java/javase/17/docs/api/index.html
 *
 * The Java Tutorials
 * https://docs.oracle.com/javase/tutorial/
 *
 * Apache Hadoop Main 3.3.0 API
 * https://hadoop.apache.org/docs/current/api/
 *
 * Learning
 *
 * Java 8 Stream Tutorial
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 *
 * @author Ian Molloy December 2021
 * @version (#)coreJava.java        1.00 2021-12-03T11:04:04
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

  private String formatTrevithickDate(LocalDate reqDate) {
   String tdate = String.format("Trevithick day for %d is %s",
        reqDate.getYear(),
        reqDate.format(DateTimeFormatter.ofPattern("EEEE, MMM dd")));

   return tdate;
  }
    /**
     * Working test method.
     * Floating point formatting to decimal places: %.2f
     * A format of %03d will pad, for example, a 7 to 007.
     * topLevel.setLocationRelativeTo(null);
     */
    public void launchFrame() {
      System.out.printf("Start of test on %tc%n", new java.util.Date());
      // ---------------------------------------------------------------

  // long diff = ChronoUnit.DAYS.between (modifiedJulianEpoch , today);

  Consumer<Integer> myConsumer = new Consumer<Integer>() {
    @Override
    public void accept(Integer year) {
      // Get a LocalDate object for the last day of April
      // for the year in question.
      LocalDate locdate = YearMonth.of(year, Month.APRIL).atEndOfMonth();

      // Get the last Saturday in April for the year in question.
      LocalDate lastSaturday = locdate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));

      // Format a string object to present this information.
      String trevDate = formatTrevithickDate(lastSaturday);
      System.out.println(trevDate);

    }

  }; //end Consumer<Integer>

  final LocalDate today = LocalDate.now();
  final int currentyear = today.getYear();
  final int endyear = currentyear + 5;
  LocalDate locdate = null;
  String trevDate = null;
  LocalDate lastSaturday = null;

  System.out.printf("The current date is now: %s%n", today.toString());

  // The number of Trevithick Day dates (1 per year) to list.
  final int numYears = 4;
  IntStream.rangeClosed(currentyear, (currentyear + numYears)).forEach((yy) -> myConsumer.accept(yy));
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
