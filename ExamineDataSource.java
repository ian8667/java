import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
//import javax.xml.stream.XMLStreamException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.InputSource;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
/**
 * <p>JTree representation of an Oracle Fusion Middleware version
 * 10.1.3 data source XML file. Written with Java SE 5.0 in mind</p>
 *
 * With thanks to http://www.javafaq.nu/java-example-code-998.html
 *
 * See also 'How to Use Trees' at
 * http://download.oracle.com/javase/tutorial/uiswing/components/tree.html
 *
 * See also 'Understanding the TreeModel'.
 * http://java.sun.com/products/jfc/tsc/articles/jtree/
 *
 * See also 'Understanding the TreeModel'.
 * http://java.sun.com/products/jfc/tsc/articles/jtree/
 *
 * http://woodstox.codehaus.org/4.0.7/javadoc/index.html
 *
 * See also 'Understanding the TreeModel'
 * http://java.sun.com/products/jfc/tsc/articles/jtree/
 *
 * <p>Note:<br>
 * Unpredictable results will occur if the file processed is not
 * an Oracle Fusion Middleware XML data source file.</p>
 *
 * <p>javac -Xlint:all ExamineDataSource.java</p>
 *
 * @author Ian Molloy November 2010
 * @version (#)ExamineDataSource.java     1.07 2012/08/11
 */
public class ExamineDataSource
{
   public static void main(String[] args)
   {
      JFrame var1 = new ExamineDataSource7();
      var1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      var1.setSize(600, 450); // width, height
      var1.setLocationRelativeTo(null);
      var1.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
      });
      var1.setVisible(true);
   }//end of main
}//end of class ExamineDataSource


class ExamineDataSource7 extends JFrame {
private JTree myTree;
private DefaultMutableTreeNode data_sources;
private DefaultMutableTreeNode connection_pools;
private DefaultMutableTreeNode currentNode;
private DefaultMutableTreeNode rootNode;
private String tempStr;
private ArrayList<String> wantedTags;
public static final long serialVersionUID = 20038L;
  /**
   * Constructor
   */
  public ExamineDataSource7() {
    super("Oracle Fusion Middleware Data Source");
    data_sources = null;
    connection_pools = null;
    currentNode = null;
    rootNode = null;
    tempStr = "";
    wantedTags = new ArrayList<String>();
    launchFrame();
  }

  /**
   * Main routine to start things off.
   */
  public void launchFrame() {
    try {
      init();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Provides a simple mechanism for the user to choose a file from a
   * directory structure. Multiple files cannot be selected.
   *
   * @param dialogTitle the string to use in the JFileChooser window's title bar
   * @return the filename selected.
   */
  public String getFileName(String dialogTitle) {

    String sTemp = "";
    int iTemp = 0;
    //FileFilter filter = new FileNameExtensionFilter("XML files", "xml");
    FileFilter xmlFilter = new FileFilter()
    {
       public boolean accept(File ff) {
           return (ff.getName().toLowerCase().endsWith("xml")) ||
                  (ff.isDirectory());
       }

       public String getDescription() {
           return "";
       }
    };

    JFileChooser choosex = new JFileChooser(new File("."));
    choosex.setFileFilter(xmlFilter);
    // Sets the string that goes in the JFileChooser window's title bar.
    choosex.setDialogTitle(dialogTitle);

    choosex.setMultiSelectionEnabled(false);
    choosex.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    iTemp = choosex.showOpenDialog(null);
    if (iTemp == JFileChooser.APPROVE_OPTION) {
      sTemp = choosex.getSelectedFile().getPath();
    }

    if (! new File(sTemp).canRead()) {
      System.err.println("Unable to read file selected");
      System.exit(1);
    }

    return sTemp;
  }//end of getFileName

  /**
   * Initialises and does some setting up.
   */
  public void init()
    throws FileNotFoundException,
           SAXException, IOException
  {
    // Remind the user what this progrm is designed to read
    // and process.
    StringBuilder userMsg = new StringBuilder(180);
    userMsg.append("This program is written to read and process")
           .append("\nan Oracle Fusion Middleware v10.1.3 data source")
           .append("\nXML file.\n\nUnpredictable results will occur")
           .append("\nif you select any other XML file.");

    JOptionPane reminder = new JOptionPane (
         (Object)userMsg,                //JOptionPane message.
         JOptionPane.WARNING_MESSAGE,    //messageType.
         JOptionPane.OK_CANCEL_OPTION);  //optionType.
    JDialog dialog = null;

    dialog = reminder.createDialog(null, "Data Source XML files");
    dialog.setVisible(true);

    final String INPUTFILE=getFileName("Get XML file");
    File ff = new File(INPUTFILE);
    boolean flag = false;
    dialog.dispose();

    // List of tags of interest that we want from the XML file.
    // All others we can ignore.
    flag=wantedTags.add("connection-pool-name");
    flag=wantedTags.add("jndi-name");
    flag=wantedTags.add("name");
    flag=wantedTags.add("user");
    flag=wantedTags.add("password");
    flag=wantedTags.add("url");

    rootNode = new DefaultMutableTreeNode(
        "XML Document: " + ff.getAbsolutePath(), true);

    // Build the tree model from our XML file.
    DefaultTreeModel defTreeModel = new DefaultTreeModel(rootNode);
    myTree = new JTree(defTreeModel);

    // We're really working with the same 'DefaultTreeModel'
    // all along but I've done this so as to 'rename' the variable.
    // Hopefuly this will remind us what we dealing with as we go
    // through the program. ie, we're really talking about the
    // 'current node'.
    currentNode = rootNode;

    // Construct the tree hierarchy
    buildTree(ff);

    // Expand the first row in the root node.
    myTree.expandRow(0);

    // Display the results in our GUI now we've built the tree.
    this.getContentPane().add(new JScrollPane(myTree), BorderLayout.CENTER);
  }//end of init

  /**
   * Parses the XML file.
   *
   * @param xmlFile the XML file to parse
   */
  public void buildTree(
    File xmlFile)
  throws FileNotFoundException,
         SAXException, IOException
  {
    // (http://www.saxproject.org/quickstart.html)
    XMLReader xReader = XMLReaderFactory.createXMLReader();
    mySaxHandler sxhandler = new mySaxHandler();
    xReader.setContentHandler(sxhandler);
    xReader.setErrorHandler(sxhandler);
    xReader.parse(new InputSource(xmlFile.getAbsolutePath()));

  }//end of buildTree

  /*
   * =========================
   * Start of inner class(es).
   * =========================
   */

  /*
   * Instead of implementing, ContentHandler directly and
   * cluttering up your code with irrelevant methods, you
   * can instead extend DefaultHandler. Then you only have
   * to override those methods you actually care about,
   * not all eleven.
   */
  class mySaxHandler extends DefaultHandler {

    /**
     * Processes a DTD event.
     */
    public void notationDecl(
         String name,
         String publicId,
         String systemId)
       throws SAXException
    {
       String tempStr = "DTD: " + name;
       DefaultMutableTreeNode dtd =
              new DefaultMutableTreeNode(tempStr);
       currentNode.add(dtd);
    }

    /**
     * Processes character data.
     * I'm just wondering if I really need this as it
     * seems to insert a blank child on the tree which
     * isn't right. All of the information I need seems
     * to be contained as attributes so can I do away
     * with this?
     */
    public void characters(
         char[] ch,
         int start,
         int length)
       throws SAXException
    {
       tempStr = new String(ch, start, length).trim();
       //DefaultMutableTreeNode chars =
       //            new DefaultMutableTreeNode(tempStr);
       //currentNode.add(chars);
    }

     /*
      * Receive notification of the start of an element.
      * Called when the starting of the Element is reached.
      * For example if we have tag called <title> ... </title>,
      * then this method is called when <Title> tag is encountered
      * while parsing the Current XML File. The AttributeList
      * parameter has the list of all attributes declared for the
      * current element in the XML file.
      */
    public void startElement (
         String uri,
         String localName,
         String qName,
         Attributes attrs)
       throws SAXException
    {
        String attrName = "";
        String attrValue = "";
        tempStr = "";

        attrName = attrs.getValue(0);
        tempStr = String.format("Element: %s %s", localName, attrName);
        DefaultMutableTreeNode element =
               new DefaultMutableTreeNode(tempStr);

        if (localName.equals("managed-data-source"))
           data_sources.add(element);
        else if (localName.equals("connection-pool"))
           connection_pools.add(element);
        else
           currentNode.add(element);

        currentNode = element;

        // Process any attributes found in the element. We
        // don't use all the attributes ...
        DefaultMutableTreeNode attrib = null;
        for (int m = 0; m < attrs.getLength(); m++) {
            attrName = attrs.getLocalName(m);
            attrValue = attrs.getValue(m);

            // ... only what is contained in variable 'wantedTags'.
            if (wantedTags.contains(attrName)) {
               tempStr = String.format("Attribute (name = '%s', value = '%s'",
                           attrs.getLocalName(m), attrValue);
               attrib = new DefaultMutableTreeNode(tempStr);
               currentNode.add(attrib);
            }

        }//end FOR loop.

    }//end of startElement

  /**
   * Processes a START_DOCUMENT event.
   */
  public void startDocument()
       throws SAXException
  {
      tempStr = "XML data-source document";
      DefaultMutableTreeNode version =
             new DefaultMutableTreeNode(tempStr);
      currentNode.add(version);

      tempStr = "data-sources";
      data_sources = new DefaultMutableTreeNode(tempStr);
      currentNode.add(data_sources);

      tempStr = "connection-pools";
      connection_pools = new DefaultMutableTreeNode(tempStr);
      currentNode.add(connection_pools);

  }//end of startDocument

  }//end of inner class mySaxHandler

  /*
   * =======================
   * End of inner class(es).
   * =======================
   */

}//end of class XmlTree7
