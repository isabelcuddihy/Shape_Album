package model.shapes;

import java.awt.Color;

/**
 * Interface creates a new shape.
 */
public interface IShape {

  /**
   * Return copy of shape.
   * @ return IShape
   */
  IShape copy();

  /**
   * Return type of shape.
   * @ return String
   */

  String getType();

  /**
   * Gets width of shape.
   * @ return int
   */
  int getWidth();

  /**
   * gets height of shape.
   * @ return int
   */
  int getHeight();

  /**
   * gets x value of corner of shape/ x value of radius (if oval).
   * @ return int
   */
  int getX();

  /**
   * gets y value of corner of shape/ y value of radius (if oval).
   * @ return int
   */
  int getY();

  /**
   * Get x,y position.
   * @ return String
   */
  String getCoordinates();

  /**
   * Get name of shape.
   * @ return String
   */
  String getName();

  /**
   * Gets color of Shape.
   * @ return String
   */
  Color getColor();


  /**
   * Set new color for shape.
   * @ param RGB int
   */
  void setColor(int r, int g, int b);

  /**
   * set new width of shape.
   * @ param width int
   */
  void setWidth(int width);

  /**
   * set new height of shape.
   * @ param height int
   */
  void setHeight(int height);

  /**
   * set new x value of corner/ x value of radius (if oval).
   * @ param x int
   */
  void setX(int x);

  /**
   * set new y value of corner/ y value of radius (if oval).
   * @ param y int
   */
  void setY(int y);

  /**
   * Set new name for shape.
   * @ String name
   */
  void setName(String name);

  /**
   * Scale x/y values of shape by amount.
   * @ param scale int
   */
  void scale(int scale);

  /**
   * Reset x,y location of shape.
   * @ param x int
   * @ param y int
   */
  void moveShape(int x, int y);

}
