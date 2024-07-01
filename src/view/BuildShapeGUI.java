package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import model.shapes.Album;
import model.shapes.IAlbum;
import model.shapes.ICanvas;
import model.shapes.IShape;


/**
 * Creates GUI/Swing View of TXT data.
 */
public class BuildShapeGUI implements IView {


  /**
   * Builds a Album GUI of specific size.
   * @ param graphic_size_1 int
   * @ param graphic_size_2 int
   */
  public BuildShapeGUI(int graphicSize1, int graphicSize2) {

    Album album = Album.getInstance(); //Put in this album

    List<String> snapshotIDList = album.getIDs();
    List<ImagePanel> imagePanelList = new ArrayList<>();
System.out.println(album.getAllSnapshotsDescriptions());
    System.out.println(album.getIDs());
    //CREATES IMAGE PANEL CARDS
    for (String ids : snapshotIDList) {
      imagePanelList.add(new ImagePanel(ids, graphicSize1, graphicSize2));
    }

    SwingView s = new SwingView("Album", imagePanelList, graphicSize1, graphicSize2);
    s.setVisible(true);
  }

  /**
   * Currently useful for HTML, future refactoring to update an html file for export.
   * after altering (adding and moving shapes via interacting with GUI)
   * @ param album IAlbum
   * @ param endingFilename String (received via Path of Export Listener)
   */

  @Override
  public void writeHTMLFileData(IAlbum album, String endingFilename) {
  }

}

/**
 * Class contains creation process for Canvas Image Panel.
 */
class ImagePanel extends JPanel { //CARD - IMAGE WITH ALL INFO
  List<IShape> shapesList;
  Album album = Album.getInstance();
  String snapshotID;
  String description;
  int graphicSize1;
  int graphicSize2;

  /**
   * Constructors empty image panel to hold shapes.
   * @ param snapshotID String
   * @ param graphicSize1 int
   * @ param graphicSize2 int
   */
  ImagePanel(String snapshotID, int graphicSize1, int graphicSize2) {
    this.setPreferredSize(new Dimension(graphicSize1, graphicSize2));
    this.setBackground(Color.white);
    this.setBorder(new BasicBorders.FieldBorder(Color.black, Color.lightGray,
            Color.white, Color.lightGray));
    this.snapshotID = snapshotID;
    ICanvas image = album.getImage(snapshotID);
    this.description = image.getDescription();
    this.shapesList = image.getShapes();
    this.graphicSize1 = graphicSize1;
    this.graphicSize2 = graphicSize2;

  }

  /**
   * Method paints shape image to Image Panel.
   * @ param g the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    for (IShape shape : shapesList) {

      if (shape.getType().equalsIgnoreCase("Rectangle")) {
        g2D.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        g2D.setColor(new Color(shape.getColor().getRed(),
                shape.getColor().getGreen(), shape.getColor().getBlue()));
        g2D.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());

      } else if (shape.getType().equalsIgnoreCase("Oval")) {
        g2D.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        g2D.setColor(new Color(shape.getColor().getRed(),
                shape.getColor().getGreen(), shape.getColor().getBlue()));
        g2D.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
      }
    }

    //DRAW TEXT (SNAPSHOT ID AND DESCRIPTION)
    g2D.drawRect(0, 0, graphicSize1, graphicSize2);
    g2D.setColor(Color.pink);
    g2D.fillRect(0, 0, graphicSize1, 40);
    g2D.setColor(Color.black);
    g2D.drawString(snapshotID, 5, 15);
    g2D.drawString(description, 5, 35);

  }

}

/**
 * Class builds an interactive GUI with buttons to navigate the album.
 */
class SwingView extends JFrame {
  Album album = Album.getInstance();
  JPanel buttonPanel;

  private JPanel image;
  private final List<ImagePanel> imagePanelList;
  int counter = 0;

  /**
   * Constructor of Swing GUI for Album.
   * @ param caption String
   * @ param imagePanelList List
   * @ param graphicSize1 int
   * @ param graphicSize2 int
   */
  public SwingView(String caption, List<ImagePanel> imagePanelList,
                   int graphicSize1, int graphicSize2) {
    super(caption);
    setSize(graphicSize1, graphicSize2);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.imagePanelList = imagePanelList;
    //Add images

    image = new JPanel();
    image.add(addFirstImage());

    this.add(image);

    //Images on top, buttons on bottom
    setLayout(new BorderLayout());


    buttonPanel = new JPanel();
    buttonPanel.setBorder(new BasicBorders.ButtonBorder(Color.black,
            Color.lightGray, Color.white, Color.lightGray));
    this.add(buttonPanel, BorderLayout.SOUTH);

    //Album Traversal Buttons
    JButton btnNext = new JButton(">> Next >>");
    btnNext.addActionListener(new NextListener());
    buttonPanel.add(btnNext);


    JButton btnPrevious = new JButton("<< Previous <<");
    btnPrevious.addActionListener(new PreviousListener());
    buttonPanel.add(btnPrevious);


    JMenu menuSelect = new JMenu("Select");
    for (String id : album.getIDs()) {
      JMenuItem snapID = new JMenuItem(id);
      snapID.addActionListener(new SelectListener());
      menuSelect.add(snapID);
    }
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menuSelect);
    buttonPanel.add(menuBar);


    JButton btnQuit = new JButton(" Quit ");
    btnQuit.addActionListener(new QuitListener());
    buttonPanel.add(btnQuit);


    JButton exportHTML = new JButton("Export to HTML");
    exportHTML.addActionListener(new ExportListener());
    buttonPanel.add(exportHTML);

    //ADD IMAGES
    image.add(addFirstImage());

    this.add(image);

  }

  /**
   * Method paints album canvas image to image panel.
   * Counter contains number of image in stack
   * @ param counter int
   */
  private void paintImage(int counter) {
    image.setVisible(false);
    image = imagePanelList.get(counter);
    image.setVisible(true);
    this.add(image);

  }

  /**
   * Method adds first image of album.
   * @ return ImagePanel
   */
  private ImagePanel addFirstImage() {
    return imagePanelList.getFirst();

  }

  /**
   * Class contains action taken when next button is clicked.
   */
  private class NextListener implements ActionListener {
    NextListener() {
    }

    /**
     * Method paints next canvas image from album to GUI.
     * @ param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      counter++;
      if (counter < album.getIDs().size()) {

        paintImage(counter);

      } else {
        counter--;
        paintImage(counter);
        JOptionPane.showMessageDialog(new JFrame(), "You have reached "
                        + "the end of the album",
                "End of the Album", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  /**
   * Class contains action taken when previous button is clicked.
   */
  private class PreviousListener implements ActionListener {
    PreviousListener() {
    }

    /**
     * Method paints previous canvas image from album to GUI.
     * @ param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      counter--;
      if (counter >= 0) {

        paintImage(counter);

      } else {
        counter++;
        paintImage(counter);
        JOptionPane.showMessageDialog(new JFrame(), "You have reached the "
                        + "beginning of the album",
                "Beginning of Album", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

  /**
   * Class contains action taken when select button is clicked.
   */
  private class SelectListener implements ActionListener {
    SelectListener() {
    }

    /**
     * Method paints selected canvas image from album to GUI.
     * @ param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      int index = album.getIDs().indexOf(e.getActionCommand());
      paintImage(index);
      counter = index;

    }
  }

  /**
   * Class contains action taken when quit button is clicked.
   */
  private static class QuitListener implements ActionListener {
    QuitListener() {
    }

    /**
     * Method closes GUI.
     * @ param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  /**
   * Class contains action taken when export button is clicked.
   */
  private static class ExportListener implements ActionListener {
    ExportListener() {
    }

    /**
     * Method exports album content to HTML file.
     * @ param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      String path = JOptionPane.showInputDialog("Enter an ending filename for your HTML file");
      if (path.isEmpty()) {
        path = "default.html";
      } else {
        path = path + ".html";
      }
      IView HTMLpackage = new BuildShapeHTML(path);

    }
  }
}