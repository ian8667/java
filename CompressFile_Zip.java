// Copyright (c) 2002 MyHouse
//package ian;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;

/**
 * Compresses a single file using the Zip file (compression) format
 *
 * Java Version 15 API docs
 * https://docs.oracle.com/en/java/javase/15/docs/api/index.html
 *
 * @author Ian Molloy January 2021
 * @version (#)CompressFile_Zip.java        1.01 2021-01-07T12:05:05
 */

/*
is this any good to me?
Filtering and sorting ZIP file contents
public void printEntries(PrintStream stream, String zip) {
    try (ZipFile zipFile = new ZipFile(zip)) {
        Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
        Predicate<ZipEntry> isJava = ze -> ze.getName().matches(".*java");
        Comparator<ZipEntry> bySize =
                (ze1, ze2) -> Long.valueOf(ze2.getSize() - ze1.getSize()).intValue();
        zipFile.stream()
               .filter(isFile.and(isJava))
               .sorted(bySize)
               .forEach(ze -> print(stream, ze));
    } catch (IOException e) {
        // error while opening a ZIP file
    }
}

private void print(PrintStream stream, ZipEntry zipEntry) {
    stream.println(zipEntry.getName() + ", size = " + zipEntry.getSize());
}
Source: https://blog.codeleak.pl/2014/06/listing-zip-file-content-java-8.html

*/

public class CompressFile_Zip {
private byte dummy;
  /**
   * Constructor
   */
  public CompressFile_Zip() {
    this.dummy = 99;
    launchFrame();
  }//end of constructor

  /**
   * Working method.
   */
  public void launchFrame() {
    System.out.printf("Start of compress on %tc%n", new java.util.Date());
    // ---------------------------------------------------------------
    final String baseDir = "C:\\gash";
    final String inf = "testinput.txt"; //input file
    final String outf = "gashoutputzip.zip";   //output file
    final Path inputPath = Paths.get(baseDir, inf);
    final Path outputPath = Paths.get(baseDir, outf);

    //Check the output path
    //In some cases, this may be a nuisance having to keep
    //deleting the output zip file before I can run this
    //program. But I've done this to ensure I don't overwrite
    //a zip file I really want to keep. In most cases though,
    //this won't be a problem in the way I work.
    //
    //If the output file does exist, it's up to the user to
    //decide whether to delete the existing file or create a
    //new zip file with a name that doesn't conflict with
    //the existing one.
    if (Files.exists(outputPath)) {
      System.err.printf("WARNING: output zip file %s exists%n", outputPath.toString());
      System.exit(1);
    }

    final int bufferSize = (1024 * 8);
    byte[] databuffer = new byte[bufferSize];
    long filelength = (new File(inputPath.toString())).length();
    // The number of bytes read, or -1 if the end of the
    // stream has been reached.
    int bytesread;
    final int EOF = -1;

    try (// Input objects
         BufferedInputStream buffread = new BufferedInputStream(
             new FileInputStream(inputPath.toString()));
         // Output objects
         FileOutputStream fos = new FileOutputStream(outputPath.toString());
         ZipOutputStream zos = new ZipOutputStream(fos);
         BufferedOutputStream buffwrite = new BufferedOutputStream(zos); )
    {

         ZipEntry zipent = new ZipEntry(inputPath.getFileName().toString());
         zipent.setComment("this is a gash zipentry comment");
         zos.putNextEntry(zipent);

         while((bytesread = buffread.read(databuffer)) != EOF) {
             buffwrite.write(databuffer, 0, bytesread);
         }

         //Set the zip file comment
         zos.setComment("this is the zip file comment");

    } catch (FileNotFoundException e1) {
      System.err.println("File not found problem");
      e1.printStackTrace();
    } catch (IOException e2) {
      System.err.println("IO problem of some kind");
      e2.printStackTrace();
    }
    //end try/catch block

    System.out.println("\nFile compressed. Files used");
    System.out.printf("Base directory is %s%n", baseDir);
    System.out.printf("Input file: %s%nOutput file: %s%n",
         inputPath.getFileName().toString(), outputPath.getFileName().toString());

    // ---------------------------------------------------------------
    System.out.printf("End of compress on %tc%n", new java.util.Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
     CompressFile_Zip kompress = new CompressFile_Zip();
  }//end of main

} //end of class
