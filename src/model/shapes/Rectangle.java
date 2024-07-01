package model.shapes;

import java.awt.Color;

/**
 * Class contains information for Rectangle shapes.
 */
public class Rectangle extends AbstractShape {
  private static final String type = "RECTANGLE";

  private int width;
  private int height;

  /**
   * Constructor creates a rectangle.
   * @ param name String
   * @ param x int
   * @ param y int
   * @ param width double
   * @ param height double
   * @ param color Color
   */
  public Rectangle(String name, int x, int y, int width, int height, int r, int g, int b) {
    super(name, x, y, r, g, b);
    this.width = invalidWHNumber(width);
    this.height = invalidWHNumber(height);

  }
  /**
   * Default Rectangle constructor.
   */

  public Rectangle() {
    super("Rectangle", 0, 0, 255, 0, 0); //Default RED color
    this.width = 1;
    this.height = 1;
  }

  /**
   * Gets type of shape.
   * @ return double
   */
  @Override
  public String getType() {

    return type;
  }

  /**
   * Copy constructor of Rectangle.
   * @ return Rectangle
   */
  public Rectangle copy() {
    return new Rectangle(getName(), getX(), getY(), getWidth(),
            getHeight(), getColor().getRed(), getColor().getGreen(), getColor().getBlue());
  }

  /**
   * Gets width of shape.
   * @ return double
   */
  @Override
  public int getWidth() {

    return this.width;
  }

  /**
   * gets height of shape.
   * @ return double
   */
  @Override
  public int getHeight() {

    return this.height;
  }


  /**
   * set new width of shape.
   * @ param width double
   */
  @Override
  public void setWidth(int width) {

    this.width = invalidWHNumber(width);
  }

  /**
   * set new height of shape.
   * @ param height double
   */
  @Override
  public void setHeight(int height) {

    this.height = invalidWHNumber(height);
  }

  /**
   * Scale x/y values of shape by amount.
   * @ param scale int
   */
  @Override
  public void scale(int scale) {
    this.height = getHeight() * invalidScaleNumber(scale);
    this.width = getWidth() * invalidScaleNumber(scale);

  }

  /**
   * Helper method to prevent invalid scale numbers.
   * @ param double
   * @ return double
   */
  private int invalidScaleNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("Value must be greater than 0.");
    }
    return number;
  }

  /**
   * Helper method to prevent invalid width/height numbers.
   * @ param double
   * @ return double
   */
  private int invalidWHNumber(int number) {
    if (number <= 0) {
      throw new IllegalArgumentException("Value must be greater than 0.");
    }
    return number;
  }

  /**
   * Method returns string description of shape.
   * @ return String
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: Rectangle" + "\n"
            + "MinCorner: " + getCoordinates() + ", Width: "
            + getWidth() + " , Height: " + getHeight()
            + ", Color: " + getColor().toString();
  }



}
