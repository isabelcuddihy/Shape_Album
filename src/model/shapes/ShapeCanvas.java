package model.shapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class creates an image of shapes.
 */
public class ShapeCanvas implements ICanvas {
  private List<IShape> snapshot = new ArrayList<>();
  private String snapshotDescription = "";


  /**
   * Constructor creates a default blank image.
   */
  public ShapeCanvas() {

    this.snapshotDescription = "Unknown";
  }

  /**
   * Constructor creates a blank NAMED image.
   * @ param description String
   */
  public ShapeCanvas(String description) {
    this.snapshotDescription = invalidDescription(description);
  }


  /**
   * Add shape to image.
   * @ param shape IShape
   */
  @Override
  public void addShape(IShape shape) {
    IShape defensiveCopyShape = shape.copy();
    List<String> shapeNames = snapshot.stream().map(IShape::getName).toList();
    //Prevents duplicate shapes with default name from being added to canvas
    if (shapeNames.contains(shape.getName())) {
      throw new IllegalArgumentException("Cannot add shapes with same name to Canvas,"
              + " please rename shape");
    }

    //Prevents equal shapes from being added to canvas
    if (!snapshot.contains(shape)) {
      snapshot.add(defensiveCopyShape);
    }
  }

  /**
   * remove shape from image.
   * @ param shape IShape
   */
  @Override
  public void removeShape(IShape shape) {
    snapshot.remove(shape);
  }


  /**
   * Add description to image.
   * @ param description String
   */
  @Override
  public void addDescription(String description) {
    this.snapshotDescription = invalidDescription(description);
  }

  /**
   * Get list of shapes in image.
   * @ return List
   */
  @Override
  public List<IShape> getShapes() {
    return Collections.unmodifiableList(snapshot);
  }

  /**
   * Get description of image.
   * @ return String
   */
  @Override
  public String getDescription() {
    return this.snapshotDescription;
  }



  /**
   * Creates String description of image and it's elements.
   * @ return String
   */

  @Override
  public String toString() {
    return "Description: " + getDescription() + "\n"
            + printShapes(snapshot);
  }


  /**
   * Helper method prevents invlaid description of image.
   * @ param description String
   * @ return String
   */
  private String invalidDescription(String description) {
    if (description == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
    return description;

  }

  /**
   * Helper method prints all shapes in image as list.
   * @ param shapes List
   * @ return String
   */
  private String printShapes(List<IShape> shapes) {
    String shapeList = "";
    int i;
    for (i = 0; i < shapes.size() - 1; i++) {
      shapeList = shapeList.concat(shapes.get(i).toString() + "\n");
    }
    shapeList = shapeList.concat(shapes.get(i).toString());
    return shapeList;
  }
}
