package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.shapes.Album;
import model.shapes.IAlbum;
import model.shapes.ICanvas;
import model.shapes.IShape;

/**
 * Creates HTML View of TXT data.
 */

public class BuildShapeHTML implements IView {

  /**
   * Create an HTML builder.
   * @ param endingFilename String
   */
  public BuildShapeHTML(String endingFilename) {

    Album album = Album.getInstance(); //Put in this album


    writeHTMLFileData(album, endingFilename); //write HTML file using album data

  }

  /**
   * Method writes all album information to HTML file.
   * File is named based on specific string
   * @ param albumInfo List
   * @ param endingFilename String
   */
  public void writeHTMLFileData(IAlbum album, String endingFilename) {

    List<String> strings = album.getIDs();
    List<ICanvas> canvas = new ArrayList<>();
    StringBuilder shapeDescriptions = new StringBuilder();
    for (String string : strings) {
      canvas.add(album.getImage(string));
    }

    StringBuilder HTMLfile = new StringBuilder();

    try {
      String welcomeMessage = "<h1> Welcome to your album, "
              + "please enjoy! </h1>";
      FileWriter myWriter = new FileWriter(endingFilename);
      for (ICanvas image : canvas) {
        HTMLfile.append(createSnapshotID(image, Album.getInstance()));
        HTMLfile.append(createDescription(image, Album.getInstance()));

        //Border around album image
        HTMLfile.append("<svg width=\"1000\" height=\"1000\" style=\""
                + "border:5px solid black\">\n />");
        for (IShape shape : image.getShapes()) {
          shapeDescriptions.append(createNewShapes(shape));
        }
        HTMLfile.append(shapeDescriptions);
        HTMLfile.append("</svg>");
        shapeDescriptions.setLength(0);
      }
      //Add information within HTML brackets
      myWriter.write("<html>" + welcomeMessage + HTMLfile + "</html>");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }

  }

  /**
   * Method creates new shape HTML information.
   * @ param line List
   * @ return StringBuilder
   */
  public StringBuilder createNewShapes(IShape shape) {


    //build rectangle using HTML syntax
    if (shape.getType().equalsIgnoreCase("rectangle")) {
      //System.out.println(new Rectangle(shapeName, width, height, x, y, red, green, blue));
      StringBuilder rectangle = new StringBuilder("<rect x=\"");
      rectangle.append(shape.getX()).append("\" y=\"").append(shape.getY())
              .append("\" width=\"").append(shape.getWidth())
              .append("\" height=\"").append(shape.getHeight()).append("\" fill=\"")
              .append("rgb(")
              .append(shape.getColor().getRed()).append(",")
              .append(shape.getColor().getGreen()).append(",")
              .append(shape.getColor().getBlue()).append(")\"" + "/>");
      return rectangle;

      //build oval using HTML syntax
    } else if (shape.getType().equalsIgnoreCase("oval")) {
      //System.out.println(new Oval(shapeName, width, height, x, y, red, green, blue));
      StringBuilder oval = new StringBuilder("<ellipse cx=\"");
      oval.append(shape.getX()).append("\" cy=\"").append(shape.getY()).append("\" rx=\"")
              .append(shape.getWidth())
              .append("\" ry=\"").append(shape.getHeight()).append("\" fill=\"")
              .append("rgb(")
              .append(shape.getColor().getRed()).append(",").append(shape.getColor().getGreen())
              .append(",").append(shape.getColor().getBlue()).append(")\"" + "/>");

      return oval;
    }
    return null; //Shape Type didn't exist
  }


  /**
   * Method created the HTML line for the Snapshot ID.
   * @ param line Sting
   * @ return String
   */
  public String createSnapshotID(ICanvas image, IAlbum album) {
    for (String id : album.getIDs()) {
      //Find matching Snapshot ID for Canvas image
      if (album.getImage(id) == image) {
        return "<p style=\"font-size:25px;\"> " + "<b>" + id + "</b>" + "</p>";
      }
    }
    return null;
  }

  /**
   * Method created the HTML line for the Description.
   * @ param line Sting
   * @ return String
   */
  public String createDescription(ICanvas image, IAlbum album) {
    for (String id : album.getIDs()) {
      //Find matching Description for Canvas image
      if (album.getImage(id) == image) {
        return "<p style=\"font-size:20px;\"> " + image.getDescription() + "</p>";
      }
    }
    return null;

  }

}