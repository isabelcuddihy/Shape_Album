package model.shapes;

import java.util.List;

/**
 * Interface creates canvas images.
 */
public interface ICanvas {

  /**
   * Add shape to image.
   * @ param shape IShape
   */
  void addShape(IShape shape);

  /**
   * remove shape from image.
   * @ param shape IShape
   */
  void removeShape(IShape shape);

  /**
   * Add description to image.
   * @ param description String
   */
  void addDescription(String description);


  /**
   * Get list of shapes in image.
   * @ return List
   */
  List<IShape> getShapes();

  /**
   * Get description of image.
   * @ return String
   */
  String getDescription();



}
