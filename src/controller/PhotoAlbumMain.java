package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.shapes.Album;
import model.shapes.IAlbum;
import model.shapes.ICanvas;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import view.BuildShapeGUI;
import view.BuildShapeHTML;
import view.IView;

/**
 * Class contains information to create a Photo ALbum.
 */
public class PhotoAlbumMain implements IController {

  private final IAlbum model;
  private IView view;
  private final List<String> viewInput;

  /**
   * Photo Album Constructor.
   * @ param model Album
   * @ param viewInput parser for taking in input
   * @ throws IOException
   */
  public PhotoAlbumMain(IAlbum model, String viewInput) throws IOException {
    this.model = model;

    //Reads string args from command line
    this.viewInput = new ArrayList<>(List.of(viewInput.split(",")));

    //uses view input results to load file data into model for processing
    IShapeDataReader dataReader = new ReadShapeData(this.viewInput.get(1));

    //Launches viewer based on viewInput results, to view data within model
    go();

  }

  /**
   * Launches Viewer for Album.
   */
  @Override
  public void go() {

    //Filename to export HTML to (if necessary)
    String endingFilename = viewInput.get(2);

    //Sending to Graphic (GUI) viewer
    if (viewInput.get(0).equalsIgnoreCase("Graphic")) {
      this.view = new BuildShapeGUI(Integer.parseInt(viewInput.get(3)),
              Integer.parseInt(viewInput.get(4)));
    }

    //Sending to HTML viewer
    if (viewInput.get(0).equalsIgnoreCase("Web")) {
      this.view = new BuildShapeHTML(endingFilename);
    }

  }

  /**
   * Main method to run Photo Album.
   * @ param args String[]
   * @ throws IOException
   */
  public static void main(String[] args) throws IOException {
    //File arg information
    InputController inputParser = new InputController(args);

    //album model
    IAlbum model = Album.getInstance();

    //launch viewer based on info from filereader
    String viewInput = inputParser.findInfo();

    //process data from file through model and show via view
    PhotoAlbumMain runner = new PhotoAlbumMain(model, viewInput);

  }

}


/**
 * Class parses through text data in a file and sends it through the Album to be analyzed.
 */
class ReadShapeData implements IShapeDataReader {

  /**
   * Creates a Data Reader for text files.
   * @ param filename String
   * @ throws FileNotFoundException
   */
  public ReadShapeData(String filename) throws FileNotFoundException {
    try {
      File file = new File(filename);
      Scanner input = new Scanner(file);
      ArrayList<String> lines = new ArrayList<>();
      List<String> snapshotID = new ArrayList<>();
      Album album = Album.getInstance();

      //While still lines to be read in file
      while (input.hasNextLine()) {

        lines.add(input.nextLine());

      }

      splitLineData(lines, album, snapshotID);

      input.close();

    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("No file found.");
    }

  }


  /**
   * Method splits and builds album object based on text file data.
   * @ param fileData List
   * @ param album Album
   * @ param snapshotID List
   */
  @Override
  public void splitLineData(List<String> fileData, Album album, List<String> snapshotID) {

    ICanvas image;
    List<IShape> shapesList = new ArrayList<>();

    for (String line : fileData) {
      if (!line.isEmpty()) {
        line = line.trim();


        try {
          //if non-commented line
          if (line.charAt(0) != '#') {
            List<String> splitLine = new ArrayList<>(List.of(line.split(" ")));
            splitLine.removeIf(word -> word.equals(" ") || word.isEmpty());

            //lines containing shape data
            if (splitLine.contains("shape")) {
              shapesList.add(createNewShapes(splitLine));

              //move shape created in album based on data
            } else if (splitLine.contains("move")) {
              moveShapes(splitLine, shapesList);

              //resize shape created in album based on data
            } else if (splitLine.contains("resize")) {
              resizeShapes(splitLine, shapesList);

              //remove shape created in album based on data
            } else if (splitLine.contains("remove")) {
              removeShapes(splitLine, shapesList);

              //recolor shape created in album based on data
            } else if (splitLine.contains("color")) {
              recolorShapes(splitLine, shapesList);
            }

            //take snapshot and add to album based on previously created shapes
            if (splitLine.getFirst().equalsIgnoreCase("snapshot")) {

              image = createNewImage(album, createDescription(splitLine), shapesList);

              album.addImage(image);

            }

          }

        } catch (IndexOutOfBoundsException e) {
          throw new IndexOutOfBoundsException("No more data in file.");
        }

      }

    }

  }

  /**
   * Method creates a new shape data input for Album model.
   * @ param line List
   * @ return IShape
   */
  @Override
  public IShape createNewShapes(List<String> line) {

    line.removeIf(word -> word.equals(" ") || word.isEmpty());

    String shapeName = String.valueOf(line.get(1));

    String shapeType = String.valueOf(line.get(2));

    int width = Integer.parseInt(line.get(3));
    int height = Integer.parseInt(line.get(4));
    int x = Integer.parseInt(line.get(5));
    int y = Integer.parseInt(line.get(6));
    int red = Integer.parseInt(line.get(7));
    int green = Integer.parseInt(line.get(8));
    int blue = Integer.parseInt(line.get(9));


    //file request rectangle build
    if (shapeType.equalsIgnoreCase("rectangle")) {

      return new Rectangle(shapeName, width, height, x, y, red, green, blue);


      //file requests oval build
    } else if (shapeType.equalsIgnoreCase("oval")) {

      return new Oval(shapeName, width, height, x, y, red, green, blue);

    }
    //shape type did not currently exist as buildable object
    return null;
  }

  /**
   * Method alters shape information within album - move shape location within album.
   * @ param line List
   * @ param listOfShapes List
   */
  @Override
  public void moveShapes(List<String> line, List<IShape> listOfShapes) {
    line.removeIf(word -> word.equals(" ") || word.isEmpty());
    String shapeName = String.valueOf(line.get(1));
    int newX = Integer.parseInt(line.get(2));
    int newY = Integer.parseInt(line.get(3));
    for (IShape shapes : listOfShapes) {
      if (shapes.getName().equals(shapeName)) {
        shapes.setX(newX);
        shapes.setY(newY);
      }
    }
  }

  /**
   * Method alters shape information within album - resize shape object within album.
   * @ param line List
   * @ param listOfShapes List
   */
  @Override
  public void resizeShapes(List<String> line, List<IShape> listOfShapes) {
    line.removeIf(word -> word.equals(" ") || word.isEmpty());
    String shapeName = String.valueOf(line.get(1));
    int newWidth = Integer.parseInt(line.get(2));
    int newHeight = Integer.parseInt(line.get(3));
    for (IShape shapes : listOfShapes) {
      if (shapes.getName().equals(shapeName)) {
        shapes.setWidth(newWidth);
        shapes.setHeight(newHeight);
      }
    }
  }

  /**
   * Method alters shape information within album - remove shape object from album.
   * @ param line List
   * @ param listOfShapes List
   */
  @Override
  public void removeShapes(List<String> line, List<IShape> listOfShapes) {
    line.removeIf(word -> word.equals(" ") || word.isEmpty());
    String shapeName = String.valueOf(line.get(1));
    for (IShape shapes : listOfShapes) {
      if (shapes.getName().equals(shapeName)) {
        listOfShapes.remove(shapes);

      }
    }
  }

  /**
   * Method alters shape information within album - recolor shape within album.
   * @ param line List
   * @ param listOfShapes List
   */
  @Override
  public void recolorShapes(List<String> line, List<IShape> listOfShapes) {
    line.removeIf(word -> word.equals(" ") || word.isEmpty());
    String shapeName = String.valueOf(line.get(1));
    int redValue = Integer.parseInt(line.get(2));
    int greenValue = Integer.parseInt(line.get(3));
    int blueValue = Integer.parseInt(line.get(4));
    for (IShape shapes : listOfShapes) {
      if (shapes.getName().equals(shapeName)) {

        shapes.setColor(redValue, greenValue, blueValue);
      }
    }
  }

  /**
   * Method creates string description for Image canvas within album.
   * @ param line Lit
   * @ return String
   */
  @Override
  public String createDescription(List<String> line) {
    line.removeIf(word -> word.equalsIgnoreCase("snapshot") || word.isEmpty());
    StringBuilder description = new StringBuilder();
    for (String word : line) {

      description.append(word);
      if (line.indexOf(word) + 1 < line.size()) {
        description.append(" ");
      }
    }

    return String.valueOf(description);
  }

  /**
   * Method creates new image anvas within album.
   * @ param album Album
   * @ param description String
   * @ param listOfShapes List
   * @ return ICanvas
   */
  public static ICanvas createNewImage(Album album, String description, List<IShape> listOfShapes) {

    return album.createImage(description, listOfShapes);
  }


}

/**
 * Class determines which type of viewer to use based on args given in command line.
 */
class InputController {
  private final String[] args;

  InputController(String[] args) {
    this.args = args;
  }

  public String findInfo() {
    String filename = "";
    String endingFilename = "";
    for (int i = 0; i < args.length; i++) {
      if (args[i].contains(".txt")) {
        args[i] = args[i].replace("-in ", "");
        filename = args[i];

      } else if (args[i].contains(".html")) {
        args[i] = args[i].replace("-v ", "");
        endingFilename = args[i];


      } else if (args[i].contains("graphic")) {

        int graphicSize1 = 1000;
        int graphicSize2 = 1000;
        if ((i + 1) < args.length) {
          graphicSize1 = Integer.parseInt(args[i + 1]);

        }
        if ((i + 2) < args.length) {
          graphicSize2 = Integer.parseInt(args[i + 2]);
        }

        return "Graphic" + "," + filename + "," + endingFilename + ","
                + graphicSize1 + "," + graphicSize2;


      } else if (args[i].contains("web")) {

        return "Web" + "," + filename + ", " + endingFilename;

      }
    }
    return null;
  }
}