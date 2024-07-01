package model.shapes;

import java.awt.Color;

/**
 * Class contains information on Oval shapes.
 */
public class Oval extends AbstractShape {
  private static final String type = "OVAL";

  private int width;
  private int height;


  /**
   * Constructor for Oval shape.
   * @ param name String
   * @ param x int
   * @ param y int
   * @ param width double
   * @ param height double
   * @ param color Color
   */
  public Oval(String name, int x, int y, int width, int height, int r, int g, int b) {
    super(name, x, y, r, g, b);
    this.width = invalidWHNumber(width);
    this.height = invalidWHNumber(height);

  }
  /**
   * Default Oval constructor.
   */

  public Oval() {
    super("Oval", 0, 0, 255, 0, 0); //creates RED colored Oval
    this.width = 1;
    this.height = 1;
  }

  /**
   * Copy constructor of Oval.
   * @ return Oval
   */
  public Oval copy() {
    return new Oval(getName(), getX(), getY(), getWidth(),
            getHeight(), getColor().getRed(), getColor().getGreen(), getColor().getBlue());
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
   * Gets width of shape.
   * @ return double
   */
  @Override
  public int getWidth() {

    return this.width;
  }

  /**
   * gets height of shape.
   * @ return int
   */
  @Override
  public int getHeight() {
    return this.height;
  }



  /**
   * set new width of shape.
   * @ param width int
   */
  @Override
  public void setWidth(int width) {
    this.width = invalidWHNumber(width);
  }

  /**
   * set new height of shape.
   * @ param height int
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
   * Method create string description of oval.
   * @ return String
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: Oval" + "\n"
            + "Center: " + getCoordinates() + ", X radius: "
            + getWidth() + " , Y radius: " + getHeight()
            + ", Color: " + getColor();
  }


}
