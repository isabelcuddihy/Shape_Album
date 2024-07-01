package model.shapes;


import java.awt.Color;
import java.util.Objects;

/**
 * Class creates a shape.
 */
public abstract class AbstractShape implements IShape {
  private String name;
  private final Point2D origin;
  private java.awt.Color color;



  /**
   * Creates a shape.
   * @ param name String
   * @ param x int
   * @ param y int
   * @ param color Color
   */
  public AbstractShape(String name, int x, int y, int r, int g, int b) {
    this.name = invalidName(name);
    this.origin = new Point2D(invalidXY(x), invalidXY(y));
    this.color = new Color(r, g, b);

  }

  /**
   * Default constructor for Shape (for future shapes).
   */
  public AbstractShape() {
    this.name = "Shape";
    this.origin = new Point2D();
    this.color = java.awt.Color.RED; //Default RED color
  }

  /**
   * gets x value of corner of shape/ x value of radius (if oval).
   * @ return int
   */
  @Override
  public int getX() {

    return this.origin.getX();
  }

  /**
   * gets y value of corner of shape/ y value of radius (if oval).
   * @ return int
   */
  @Override
  public int getY() {
    return this.origin.getY();
  }

  /**
   * Get x,y position.
   * @ return String
   */
  @Override
  public String getCoordinates() {
    return "(" + getX() + "," + getY() + ")";
  }

  /**
   * Get name of shape.
   * @ return String
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets color of Shape.
   * @ return Color
   */
  @Override
  public java.awt.Color getColor() {

    return this.color;
  }

  /**
   * Set new color for shape.
   * @ param RGB String
   */
  @Override
  public void setColor(int r, int g, int b) {

    this.color = new java.awt.Color(r, g, b);
  }

  /**
   * set new x value of corner/ x value of radius (if oval).
   * @ param x int
   */
  @Override
  public void setX(int x) {
    this.origin.setX(invalidXY(x));
  }

  /**
   * set new y value of corner/ y value of radius (if oval).
   * @ param y int
   */
  @Override
  public void setY(int y) {
    this.origin.setY(invalidXY(y));
  }


  /**
   * Set new name for shape.
   * @ param String name
   */

  public void setName(String name) {
    this.name = invalidName(name);
  }

  /**
   * Reset x,y location of shape.
   * @ param x int
   * @ param y int
   */
  @Override
  public void moveShape(int x, int y) {
    setX(x);
    setY(y);
  }

  /**
   * Helper method to protect against invalid X/Y input.
   * @ param coordinate int
   * @ return int
   */
  private int invalidXY(int coordinate) {
    if (coordinate < 0) {
      throw new IllegalArgumentException("Entered Coordinate must be 0 or higher");
    }
    return coordinate;
  }

  /**
   * Helper method to protect against invalid shape names.
   * @ param name String
   * @ return String
   */
  private String invalidName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name must contain at least 1 letter");
    }
    return name;
  }


  /**
   * Two shapes are equivalent if they have the same name, size, color, and x/y location.
   * @ param o Object
   * @ return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IShape)) {
      return false;
    }
    IShape that = (IShape) o;
    return getWidth() == that.getWidth() && getHeight() == that.getHeight()
            && getColor() == that.getColor() && that.getX() == getX() && that.getY() == getY();
  }

  /**
   * Returns HashCode of Object.
   * @ return int
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), origin, getWidth(), getHeight(), getColor());
  }

}
