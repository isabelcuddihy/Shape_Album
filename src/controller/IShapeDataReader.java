package controller;

import java.util.List;
import model.shapes.Album;
import model.shapes.IShape;

/**
 * Interface contains methods to reader data and run through Album model.
 */
public interface IShapeDataReader {

  /**
   * Method splits and alters album content based on lines of text data.
   */
  void splitLineData(List<String> fileData, Album album, List<String> snapshotID);

  /**
   * Method creates a new shape data input for Album model.
   */
  IShape createNewShapes(List<String> line);

  /**
   * Method alters shape information within album - move shape location within album.
   */
  void moveShapes(List<String> line, List<IShape> listOfShapes);

  /**
   * Method alters shape information within album - resize shape object within album.
   */
  void resizeShapes(List<String> line, List<IShape> listOfShapes);

  /**
   * Method alters shape information within album - remove shape object from album.
   */
  void removeShapes(List<String> line, List<IShape> listOfShapes);

  /**
   * Method alters shape information within album - recolor shape within album.
   */
  void recolorShapes(List<String> line, List<IShape> listOfShapes);

  /**
   * Method creates string description for Image canvas within album.
   */
  String createDescription(List<String> line);
}
