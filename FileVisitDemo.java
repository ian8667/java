// Copyright (c) 2002 MyHouse
//package ian;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.FileVisitResult.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * <p>This program gives an example of using SimpleFileVisitor
 * (java.nio.file.SimpleFileVisitor<T>)</p>
 *
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
 * <p>javac -Xlint:all FileVisitDemo.java</p>
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
 * @author Ian Molloy April 2001
 * @version (#)FileVisitDemo.java        1.00 2013-07-24
 */
public class FileVisitDemo {
private byte dummy;
private List<String> pathTaken;
private String msg;
private int fileCount;
private int dirCount;
  /**
   * Constructor
   */
  public FileVisitDemo() {
    this.dummy = 99;
    this.fileCount = 0;
    this.dirCount = 0;
    this.pathTaken = new ArrayList<String>(100);
    this.msg = "";
System.out.println("blob constructor for FileVisitDemo complete");
    launchFrame();
  }//end of constructor

  /*
   * Adds an item to variable pathTaken.
   */
  private void append2Pathtaken(String item) {
     this.pathTaken.add(item);
  }

  /**
   * Working test method.
   * Floating point formatting to decimal places: %.2f
   * A format of %03d will pad, for example, a 5 to 005.
   * topLevel.setLocationRelativeTo(null);
   */
  public void launchFrame() {
    System.out.printf("Start of test on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
    /*
       I'm using this program to practice using java.nio.file.FileVisitor.

       A visitor of files. An implementation of this interface is
       provided to the Files.walkFileTree methods to visit each file in a
       file tree.
    */

    Path startDir = Paths.get("C:\\test_a");
    PrintFiles pfiles = new PrintFiles();

    try {
      Files.walkFileTree(startDir, pfiles);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("Should be all done now");
    }

/*
    if (pathTaken.size() > 0) {
       System.out.println("\nListing the contents of variable pathTaken");
       for (Iterator<String> iter = pathTaken.iterator(); iter.hasNext(); ) {
          System.out.println(iter.next());
       }
    }
*/

    System.out.printf("%nvariable dummy is now %d%n", dummy);

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
        new FileVisitDemo();
      }
    });
  }//end of main

// ==============================================
// Start of inner class
// ==============================================

public class PrintFiles extends SimpleFileVisitor<Path> {
private int dirCount;
private boolean printFilecount;
private String msg;
  /**
   * Constructor
   */
  public PrintFiles() {
    super();
    this.msg = "";
    this.printFilecount = true;
  }//end of constructor

  private void printBklanklines(int blanks) {

     for (int m = 0; m < blanks; m++) {
         System.out.println("");
     }
  }

  /**
   * Invoked for a directory before entries in the
   * directory are visited.
   */
  @Override
  public FileVisitResult preVisitDirectory(
           Path dir,
           BasicFileAttributes attrs) throws IOException
  {
      //printBklanklines(1);
//      msg = String.format("in --> We're just about to look in directory %s%n",
//                 dir.getFileName().toString());
      msg = String.format("in --> We're just about to look in directory %s%n",
                 dir.toAbsolutePath().toString());
      System.out.println(msg);
      append2Pathtaken(msg);
      //append2Pathtaken(msg);

      //resetFileCount();
      return CONTINUE;
  }// end of preVisitDirectory

  /**
   * Invoked for a file in a directory.
   */
  @Override
  public FileVisitResult visitFile(
           Path file,
           BasicFileAttributes attrs) throws IOException
  {

      msg = String.format("Looked at file: %s%n", file.getFileName().toString());
      System.out.println(msg);
//      append2Pathtaken(msg);
      //incrementFileCount();

      return CONTINUE;
  }// end of visitFile

  /**
   * Invoked for a directory after entries in the directory,
   * and ALL OF THEIR DESCENDANTS, have been visited.
   */
  @Override
  public FileVisitResult postVisitDirectory(
           Path dir,
           IOException exc) throws IOException
  {

      msg = String.format("out ++> %s done%n", dir.toAbsolutePath().toString());
      System.out.println(msg);
      append2Pathtaken(msg);

      return CONTINUE;
  }// end of postVisitDirectory

  /**
   * Invoked for a file that could not be visited.
   */
  @Override
  public FileVisitResult visitFileFailed(
           Path file,
           IOException exc)
  {
      msg = String.format("Snags with file %s%n", file.getFileName().toString());
      //append2Pathtaken(msg);

      System.err.println(exc);

      return CONTINUE;
  }// end of visitFileFailed

}

// ==============================================
// End of inner class
// ==============================================

}//end of class
