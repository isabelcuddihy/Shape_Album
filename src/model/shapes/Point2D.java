package model.shapes;

/**
 * Point2D class contains information on x,y coordinate points.
 */
public class Point2D {
  private int x;
  private int y;

  /**
   * Constructor creates a Point2D coordinate.
   * @ param x int
   * @ param y int
   */
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Default Point2D starts at (0,0).
   */
  public Point2D() {

    this(0, 0);
  }

  /**
   * Copy constructor of Point2D.
   * @ param original Point2D
   */
  public Point2D(Point2D original) {

    this(original.x, original.y);
  }

  /**
   * Get x value.
   * @ return int
   */
  public int getX() {
    return this.x;
  }

  /**
   * Get y value.
   * @ return int
   */
  public int getY() {
    return this.y;
  }

  /**
   * Set x value.
   * @ param x int
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Set y value.
   * @ param y int
   */
  public void setY(int y) {
    this.y = y;
  }
}
