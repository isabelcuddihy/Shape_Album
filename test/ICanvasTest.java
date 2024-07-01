import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import model.shapes.ICanvas;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeCanvas;

import static org.junit.jupiter.api.Assertions.*;


class ICanvasTest {


  IShape rect1;
  IShape rect2;
  IShape rect3;

  IShape oval1;
  IShape oval2;
  IShape oval3;


  ICanvas canvas1;

  ICanvas canvas2;

  ICanvas canvas3;
  @BeforeEach
  void setUp() {
    rect1 = new Rectangle(); //Default Version
    rect2 = new Rectangle("Rect2", 10,10,50,50,0,255,0);
    rect3 = new Rectangle("Rect3", 5, 3, 15, 8, 0,0,255);

    oval1 = new Oval(); //Default Version
    oval2 = new Oval("Oval2", 8, 4, 2, 20, 0,255,0);
    oval3 = new Oval("Oval3", 3, 3, 3, 3, 0,0,255);

    canvas1 = new ShapeCanvas();
    canvas2 = new ShapeCanvas();
    canvas3 = new ShapeCanvas();
  }

  @Test
  void addShape() {
    canvas1.addShape(rect2);
    canvas2.addShape(oval1);
    canvas3.addShape(rect2);

    canvas1.addShape(rect3);
    canvas2.addShape(oval3);

    //can't add shape twice
    assertThrows(IllegalArgumentException.class, () -> {
      canvas3.addShape(rect2);});

    //Shape with same name as oval3
    IShape oval4 = new Oval("Oval3", 3, 3, 3, 3, 0,0,255);

    canvas3.addShape(oval3);

    //can't add shape with same name
    assertThrows(IllegalArgumentException.class, () -> {
      canvas3.addShape(oval4);});

    canvas3.addShape(oval2);

    assertTrue(canvas1.getShapes().toString().contains(rect2.toString()));
    assertTrue( canvas1.getShapes().toString().contains(rect3.toString()));

    assertTrue(canvas2.getShapes().toString().contains(oval1.toString()));
    assertTrue( canvas2.getShapes().toString().contains(oval3.toString()));

    assertTrue(canvas3.getShapes().toString().contains(rect2.toString()));
    assertTrue(canvas3.getShapes().toString().contains(oval2.toString()));
  }

  @Test
  void testRemoveShape() {
    canvas1.addShape(rect1);
    canvas1.addShape(oval1);
    canvas1.addShape(rect2);
    canvas1.addShape(rect3);

    canvas1.removeShape(oval2);
    canvas1.removeShape(oval3); //can't remove shape not on canvas
    canvas1.removeShape(oval1);


    assertTrue(canvas1.getShapes().toString().contains(rect1.toString()));
    assertTrue( canvas1.getShapes().toString().contains(rect3.toString()));

    assertFalse( canvas1.getShapes().toString().contains(oval2.toString()));

    canvas1.addShape(oval2); //can re-add shape after removing it

    assertTrue(canvas1.getShapes().toString().contains(oval2.toString()));
  }

  @Test
  void addDescription() {
    assertEquals("Unknown", canvas1.getDescription()); // Default Description
    canvas1.addDescription("Canvas of Rectangles");
    canvas2.addDescription("Canvas of Ovals");
    canvas3.addDescription("Mixed Canvas of Ovals and Rectangles");

    assertEquals("Canvas of Rectangles", canvas1.getDescription());
    assertEquals("Canvas of Ovals", canvas2.getDescription());
    assertEquals("Mixed Canvas of Ovals and Rectangles", canvas3.getDescription());

    //Name can't be null
    assertThrows(IllegalArgumentException.class, () -> {
      canvas1.addDescription(null);});
  }

  @Test
  void getShapes() {

    List<IShape> canvas1Shapes = canvas1.getShapes();
    List<IShape> canvas2Shapes = canvas2.getShapes();
    List<IShape> canvas3Shapes = canvas3.getShapes();

    assertEquals(canvas1Shapes, canvas1.getShapes());
    assertEquals(canvas2Shapes, canvas2.getShapes());
    assertEquals(canvas3Shapes, canvas3.getShapes());
  }

  @Test
  void getDescription() {

    assertEquals("Unknown", canvas1.getDescription());
    assertEquals("Unknown", canvas2.getDescription());
    assertEquals("Unknown", canvas3.getDescription());

    canvas1.addDescription("Canvas of Rectangles");
    canvas2.addDescription("Canvas of Ovals");
    canvas3.addDescription("Mixed Canvas of Ovals and Rectangles");

    assertEquals("Canvas of Rectangles", canvas1.getDescription());
    assertEquals("Canvas of Ovals", canvas2.getDescription());
    assertEquals("Mixed Canvas of Ovals and Rectangles", canvas3.getDescription());
  }


}