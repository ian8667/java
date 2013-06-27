// Copyright (c) 2002 MyHouse
//package ian;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.Date;
import java.nio.charset.CharsetEncoder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
/**
 * <p>Displays information regarding characters in a string.
 * A record is read from a file and this string is parsed
 * to produce a list in four columns similar to the following:
 * <pre><code>
 * Col 1            Col 2  Col 3    Col 4
 *
 * (char pos     1) char=A dec= 65, hex=41
 * (char pos     2) char=r dec=114, hex=72
 * (char pos     3) char=m dec=109, hex=6D
 * (char pos     4) char=s dec=115, hex=73
 * (char pos     5) char=t dec=116, hex=74
 * (char pos     6) char=r dec=114, hex=72
 * (char pos     7) char=o dec=111, hex=6F
 * (char pos     8) char=n dec=110, hex=6E
 * (char pos     9) char=g dec=103, hex=67
 * </code></pre></p>
 *
 * <p>Column details:<br>
 * Column 1 - the postion within the string where the
 * character lies. In the above example, character position
 * 3 contains the letter 'm'. Position values start from 1
 * (1-based).<br>
 * Column 2 - the character at the position as indicated by
 * column 1.<br>
 * Column 3 - the decimal value of the character as found
 * in the ASCII character set.<br>
 * Column 4 - the hexadecimal value of the character as
 * found in the ASCII character set. Characters which cannot
 * be converted will be represented as two questions marks.
 * ie ??</p>
 *
 * <p><i>import</i> statements <b>must</b> go above this comment.</p>
 *
 * <p>javac -Xlint:all ExamineString.java</p>
 *
 * @author Ian Molloy June 2009
 * @version (#)ExamineString.java        1.13 2011/02/05
 */
public class ExamineString {
private byte dummy;
private String sTemp;
private FileWrite fout;
private int recordLen;
  /**
   * Constructor
   */
  public ExamineString() {
    dummy = 0;
    sTemp = "";
    fout = null;
    recordLen = 0;
  }//end of constructor

  /**
   * Displays the character, hex and decimal values of each
   * character in the string supplied. The information for
   * each character will be listed on it's own line, so the
   * whole display will be in a vertical configuration.
   * Character positions are 1-based.
   *
   * @param inString the string whose contents are read
   */
  private void DisplayString(String inString) {

    int remainingBytes = 0;
    byte readInt = 0;
    short flagCount = 0;
     //Gives the user the position within the string (1-based).
    String outrec = "";
    String flag = "";
    char mychar = '\u0020';
    ByteBuffer bBuff = null;

    try {
      CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();
      encoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
      bBuff = encoder.encode(CharBuffer.wrap(inString));

    } catch (CharacterCodingException e) {
      e.printStackTrace();
    }

    remainingBytes = bBuff.remaining();
    for (int m = 0; m < remainingBytes; m++) {
        readInt=bBuff.get();

        if ((readInt >= 32) && (readInt <= 126)) {
          flag="";
          mychar = (char)readInt;

        } else {
          // Character to be flagged.
          flagCount++;
          flag="*****";
          mychar = '\u003F'; // A question mark.
        }

        outrec=String.format("(char pos %5d) char=%s dec=%03d, hex=%02X %s",
               (m+1), mychar, readInt, readInt, flag);
        System.out.println(outrec);
        fout.writeLine(outrec);

    }

    System.out.println("<end of listing>");
    fout.writeLine("<end of listing>");

    outrec=String.format("%nLength of input string is %d characters%n",
           recordLen);
    System.out.print(outrec);
    fout.writeLine(outrec);

    outrec=String.format("%d characters flagged%n", flagCount);
    System.out.printf(outrec);
    fout.writeLine(outrec);

    outrec="Character positions are 1-based";
    System.out.println(outrec);
    fout.writeLine(outrec);

  }//end of DisplayString

  /**
   * Main working method.
   */
  public void launchFrame() {
    System.out.printf("Start of ExamineString on %tc%n", new Date());
    // ---------------------------------------------------------------

    Scanner scan = new Scanner(System.in);
    WorkUtil utils = new WorkUtil();
    final String FNAME = utils.getFileName("Get input file"); // Input file.
    final String OUTNAME = "c:\\ExamineString.tmp"; // Output file.
    int snippet_length = 30;
    int lineCount = 0;
    String inrec = "";
    System.out.printf("Inut filename %s%n", FNAME);
    System.out.printf("==> Line number in file to look at? ");
    final int targetLine = scan.nextInt();
    FileRead fin = new FileRead(FNAME);
    fout = new FileWrite(OUTNAME);

    scan.close();

    // Read loop to get to the line requested by the
    // user. We don't do anything with the records
    // read, just simply ignore them until we get to
    // the requested record.
    while ((lineCount < targetLine) && (inrec != null)) {
       lineCount++;
       inrec=fin.readLine();
    }

    if (inrec == null) {
      System.err.printf("Line number %d not found. Maximum lines found is %d%n",
           targetLine, (lineCount-1));

    } else if (inrec.length() == 0) {
      System.err.printf(
            "Record %d has length of 0 bytes. Nothing for me to look at%n",
             targetLine);

    } else {
      recordLen = inrec.length();
      sTemp=String.format("Temporary output file written on %tc%n", new Date());
      fout.writeLine(sTemp);
      DisplayString(inrec);

      // Just in case the current line length is less then the
      // snippet length required. Otherwise we'll get a Java
      // error.
      snippet_length =
            (snippet_length > recordLen) ? recordLen : snippet_length;

      sTemp=String.format("Snippet of line %d requested: <%s>%n",
           targetLine, inrec.substring(0, snippet_length));
      System.out.printf(sTemp);
      fout.writeLine(sTemp);

      sTemp="Files used:";
      System.out.println(sTemp);
      fout.writeLine(sTemp);

      sTemp=String.format("Input file: %s%n", FNAME);
      System.out.println(sTemp);
      fout.writeLine(sTemp);

      sTemp=String.format("Outut file: %s%n", OUTNAME);
      System.out.println(sTemp);
      fout.writeLine(sTemp);

    }

    fin.close();
    fout.close();

    // ---------------------------------------------------------------
    System.out.printf("End of ExamineString on %tc%n", new Date());
  }//end of launchFrame

  /**
   * main
   * @param args
   */
  public static void main(String[] args) {
    ExamineString var1 = new ExamineString();
    //
    var1.launchFrame();

    System.out.println("\nEnd of run for ExamineString");
    //System.exit(0);
  }//end of main

}//end of class
