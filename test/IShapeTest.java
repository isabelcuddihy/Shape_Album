import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;


import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;
import static org.junit.jupiter.api.Assertions.*;



class IShapeTest {

  IShape rect1;
  IShape rect2;
  IShape rect3;

  IShape oval1;
  IShape oval2;
  IShape oval3;


  @BeforeEach
  void setUp() {
    rect1 = new Rectangle(); //Default Version
    rect2 = new Rectangle("Rect2", 10,10,50,50,0,255,0);
    rect3 = new Rectangle("Rect3", 5, 3, 15, 8, 0,0,255);

    oval1 = new Oval(); //Default Version
    oval2 = new Oval("Oval2", 8, 4, 2, 20, 0,255,0);
    oval3 = new Oval("Oval3", 3, 3, 3, 3, 0,0,255);



    //Name can't be blank or null
    assertThrows(IllegalArgumentException.class, () -> {
      new Oval("",1,1,1,1,255,0,0);});
    assertThrows(IllegalArgumentException.class, () -> {
      new Rectangle(null,1,1,1,1,255,0,0);});
  }

  @Test
  void testCopy() {

    //Copied info
    assertEquals(rect1.toString(), rect1.copy().toString());
    IShape rect4 = rect1.copy();
    assertEquals(rect4.getX(), rect1.getX());

    //Info changing, only effects new copied object, no original
    rect4.setX(4);
    rect4.setColor(0,0,255);

    assertNotEquals(rect1.getX(), rect4.getX());
    assertNotEquals(rect1.getColor(), rect4.getColor());
  }

  @Test
  void testGetWidth() {

    //Defaults start at 1
    assertEquals(1, rect1.getWidth());
    assertEquals(1, oval1.getWidth());

    assertEquals(50, rect2.getWidth());
    assertEquals(2, oval2.getWidth());

    assertEquals(15, rect3.getWidth());
    assertEquals(3, oval3.getWidth());
  }

  @Test
  void testGetHeight() {
    //Defaults start at 1
    assertEquals(1, rect1.getHeight());
    assertEquals(1, oval1.getHeight());

    assertEquals(50, rect2.getHeight());
    assertEquals(20, oval2.getHeight());

    assertEquals(8, rect3.getHeight());
    assertEquals(3, oval3.getHeight());
  }

  @Test
  void testGetX() {

    //Defaults Start at 0
    assertEquals(0, rect1.getX());
    assertEquals(0, oval1.getX());

    assertEquals(10, rect2.getX());
    assertEquals(8, oval2.getX());

    assertEquals(5, rect3.getX());
    assertEquals(3, oval3.getX());
  }

  @Test
  void testGetY() {
    //Defaults Start at 0
    assertEquals(0, rect1.getY());
    assertEquals(0, oval1.getY());

    assertEquals(10, rect2.getY());
    assertEquals(4, oval2.getY());

    assertEquals(3, rect3.getY());
    assertEquals(3, oval3.getY());
  }

  @Test
  void testGetCoordinates() {

    assertEquals("(0,0)", rect1.getCoordinates());
    assertEquals("(0,0)", oval1.getCoordinates());

    assertEquals("(10,10)", rect2.getCoordinates());
    assertEquals("(8,4)", oval2.getCoordinates());

    assertEquals("(5,3)", rect3.getCoordinates());
    assertEquals("(3,3)", oval3.getCoordinates());
  }

  @Test
  void testGetName() {
    assertEquals("Rectangle", rect1.getName());
    assertEquals("Oval", oval1.getName());

    assertEquals("Rect2", rect2.getName());
    assertEquals("Oval2", oval2.getName());

    assertEquals("Rect3", rect3.getName());
    assertEquals("Oval3", oval3.getName());
  }

  @Test
  void testGetColor() {

    assertEquals("java.awt.Color[r=255,g=0,b=0]", rect1.getColor().toString());
    assertEquals("java.awt.Color[r=255,g=0,b=0]", oval1.getColor().toString());

    assertEquals("java.awt.Color[r=0,g=255,b=0]", rect2.getColor().toString());
    assertEquals("java.awt.Color[r=0,g=255,b=0]", oval2.getColor().toString());

    assertEquals("java.awt.Color[r=0,g=0,b=255]", rect3.getColor().toString());
    assertEquals("java.awt.Color[r=0,g=0,b=255]", oval3.getColor().toString());
  }



  @Test
  void testSetColor() {

    rect1.setColor(0,255,0);
    rect2.setColor(255,0,0);
    rect3.setColor(0,0,255); //color isn't changing

    oval1.setColor(0,0,255);
    oval2.setColor(255,0,0);
    oval3.setColor(0,0,255); //color isn't changing

    assertEquals(green, rect1.getColor());
    assertEquals(blue, oval1.getColor());

    assertEquals(red, rect2.getColor());
    assertEquals(red, oval2.getColor());

    assertEquals(blue, rect3.getColor());
    assertEquals(blue, oval3.getColor());

  }

  @Test
  void testSetWidth() {
    
    rect1.setWidth(300);
    assertThrows(IllegalArgumentException.class, () -> {
      rect2.setWidth(-400);});

    rect2.setWidth(225);

    rect3.setWidth(25);

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.setWidth(0);});

    oval1.setWidth(1000);
    oval2.setWidth(12);
    oval3.setWidth(2);

    assertEquals(300, rect1.getWidth());
    assertEquals(1000, oval1.getWidth());

    assertEquals(225, rect2.getWidth());
    assertEquals(12, oval2.getWidth());

    assertEquals(25, rect3.getWidth());
    assertEquals(2, oval3.getWidth());

  }

  @Test
  void testSetHeight() {

    rect1.setHeight(27);
    assertThrows(IllegalArgumentException.class, () -> {
      rect2.setHeight(-343);});

    rect2.setHeight(343);

    rect3.setHeight(99);

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.setHeight(0);});

    oval1.setHeight(15);
    oval2.setHeight(22);
    oval3.setHeight(87);

    assertEquals(27, rect1.getHeight());
    assertEquals(15, oval1.getHeight());

    assertEquals(343, rect2.getHeight());
    assertEquals(22, oval2.getHeight());

    assertEquals(99, rect3.getHeight());
    assertEquals(87, oval3.getHeight());
  }

  @Test
  void testSetX() {

    rect1.setX(4);
    assertThrows(IllegalArgumentException.class, () -> {
      rect2.setX(-3);});

    rect2.setX(0);

    rect3.setX(9);

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.setX(-488);});

    oval1.setX(6);
    oval2.setX(77);
    oval3.setX(1);

    assertEquals(4, rect1.getX());
    assertEquals(6, oval1.getX());

    assertEquals(0, rect2.getX());
    assertEquals(77, oval2.getX());

    assertEquals(9, rect3.getX());
    assertEquals(1, oval3.getX());
  }

  @Test
  void testSetY() {

    rect1.setY(10);
    assertThrows(IllegalArgumentException.class, () -> {
      rect2.setY(-8);});

    rect2.setY(5);

    rect3.setY(0);

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.setY(-52);});

    oval1.setY(7);
    oval2.setY(0);
    oval3.setY(9);

    assertEquals(10, rect1.getY());
    assertEquals(7, oval1.getY());

    assertEquals(5, rect2.getY());
    assertEquals(0, oval2.getY());

    assertEquals(0, rect3.getY());
    assertEquals(9, oval3.getY());
  }

  @Test
  void testSetName() {

    assertThrows(IllegalArgumentException.class, () -> {
      rect2.setName(null);});
    assertThrows(IllegalArgumentException.class, () -> {
      oval1.setName("");});

    rect1.setName("Rectangle 47");
    rect2.setName("Happy Days");
    oval2.setName("Round");
    oval1.setName("365");
    assertEquals("Rectangle 47", rect1.getName());
    assertEquals("365", oval1.getName());

    //rect1's name was changed permanently
    assertNotEquals("Rectangle", rect1.getName());
  }

  @Test
  void testScale() {
    rect2.scale(100);
    oval2.scale(5);
    assertEquals(5000.0, rect2.getWidth());
    assertEquals(10.0, oval2.getWidth());

    rect3.scale(3);
    oval3.scale(1000);
    assertEquals(24.0, rect3.getHeight());
    assertEquals(3000.0, oval3.getHeight());

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.scale(-52);});

    assertThrows(IllegalArgumentException.class, () -> {
      rect1.scale(0);});
  }

  @Test
  void testMoveShape() {

    rect1.moveShape(3, 3);
    oval1.moveShape(20, 20);

    assertEquals(3, rect1.getY());
    assertEquals(20, oval1.getY());

    rect2.moveShape(100, 40);
    oval2.moveShape(5, 4);
    assertEquals("(100,40)", rect2.getCoordinates());
    assertEquals("(5,4)", oval2.getCoordinates());

    rect3.moveShape(3, 30);
    oval3.moveShape(1011, 2);
    assertEquals(3, rect3.getX());
    assertEquals(1011, oval3.getX());

    assertThrows(IllegalArgumentException.class, () -> {
      oval1.moveShape(-52, 0);});

    assertThrows(IllegalArgumentException.class, () -> {
      rect1.moveShape(0, -87);});
  }

  @Test
  void testToString() {

    assertEquals(
            "Name: Rectangle\n" +
                    "Type: Rectangle\n" +
                    "MinCorner: (0,0), Width: 1 , Height: 1, Color: java.awt.Color[r=255,g=0,b=0]"
            , rect1.toString());
    assertEquals("Name: Rect2\n" +
                    "Type: Rectangle\n" +
                    "MinCorner: (10,10), Width: 50 , Height: 50, Color: java.awt.Color[r=0,g=255,b=0]"
            , rect2.toString());

    assertEquals(
            "Name: Oval\n" +
                    "Type: Oval\n" +
                    "Center: (0,0), X radius: 1 , Y radius: 1, Color: java.awt.Color[r=255,g=0,b=0]"
            , oval1.toString());
    assertEquals("Name: Oval2\n" +
                    "Type: Oval\n" +
                    "Center: (8,4), X radius: 2 , Y radius: 20, Color: java.awt.Color[r=0,g=255,b=0]"
            , oval2.toString());
  }

  @Test
  void testEquals() {

    IShape rect4 = new Rectangle("Rect2", 10,10,50,50,0,255,0);
    IShape oval4 = new Oval("Oval2", 8, 4, 2, 20, 0,255,0);

    IShape rect5 = new Rectangle();
    IShape oval5 = new Oval();

   //assertEquals(false, oval2.equals(oval4));
   //assertEquals(false, rect4.equals(rect2));

    //Defaults are identical,
   // assertEquals(true, oval1.equals(oval5));
   // assertEquals(true, rect1.equals(rect5));


  }
}