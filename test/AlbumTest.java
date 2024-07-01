import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.shapes.Album;
import model.shapes.ICanvas;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import model.shapes.ShapeCanvas;

import static org.junit.jupiter.api.Assertions.*;



/**
 * DUE TO THE TIMESTAMP FUNCTIONALITY CONTAINING UP TO MILLISECONDS,
 * TESTS MAY NEED TO BE RUN INDIVIDUALLY TO CONFIRM
 */
class AlbumTest {
  IShape rect1;
  IShape rect2;
  IShape rect3;

  IShape oval1;
  IShape oval2;
  IShape oval3;


  ICanvas canvas1;

  ICanvas canvas2;

  ICanvas canvas3;

  Album album;


  @BeforeEach
  void setUp() {

    rect1 = new Rectangle(); //Default Version
    rect2 = new Rectangle("Rect2", 10,10,50,50,0,255,0);
    rect3 = new Rectangle("Rect3", 5, 3, 15, 8, 0,0,255);

    oval1 = new Oval(); //Default Version
    oval2 = new Oval("Oval2", 8, 4, 2, 20, 0,255,0);
    oval3 = new Oval("Oval3", 3, 3, 3, 3, 0,0,255);

    canvas1 = new ShapeCanvas("Canvas 1");
    canvas2 = new ShapeCanvas("Canvas 2");
    canvas3 = new ShapeCanvas("Canvas 3");

    album = Album.getInstance();
  }

  @Test
  void getInstance() {
    assertEquals(album, Album.getInstance());
    //Album newAlbum = new Album(); <--- Not runnable code b/c of private access only allowing one


  }

  @Test
  void createImage() {
    List<IShape> newShapes = new ArrayList<>();
    newShapes.add(rect1);
    newShapes.add(rect2);
    newShapes.add(rect3);
    newShapes.add(oval3);
    ICanvas newImage = album.createImage("Rectangles and Ovals", newShapes);
    album.addImage(newImage);
    Collection<ICanvas> snapshot1 = album.getAlbum().values();
    assertTrue(snapshot1.contains(newImage));


    assertThrows(IllegalArgumentException.class, () -> {
      album.createImage(null, newShapes);});
    assertThrows(IllegalArgumentException.class, () -> {
      album.createImage("Test", null);});
  }

  @Test
  void addImage() {
    album.clearAlbum();
    canvas1.addShape(rect1);
    canvas3.addShape(oval3);
    album.addImage(canvas1);
    album.addImage(canvas3);
    assertTrue(album.getAlbum().containsValue(canvas3));
    assertFalse(album.getAlbum().containsValue(canvas2));


  }

  @Test
  void getAllSnapshotsDescriptions() {
    album.clearAlbum();
    canvas1.addShape(oval1);
    canvas1.addShape(oval2);
    canvas1.addShape(oval3);
    canvas2.addShape(rect3);
    album.addImage(canvas1);
    album.addImage(canvas2);
    List<String> descriptions = new ArrayList<>(album.getAllSnapshotsDescriptions());
    assertTrue(descriptions.contains(canvas1.getDescription()));

    assertTrue(descriptions.contains(canvas2.getDescription()));
  }

  @Test
  void testGetAlbum() {
  album.clearAlbum();
    canvas1.addShape(rect1);
    canvas2.addShape(oval1);
    canvas3.addShape(rect2);
    album.addImage(canvas1);
    album.addImage(canvas2);
    assertEquals(2, album.getAlbum().size());

    album.addImage(canvas3);
    assertEquals(true, album.getAlbum().containsValue(canvas3));

  }

  @Test
  void testClearAlbum() {
    album.clearAlbum();
    assertEquals(0, album.getAlbum().size());
    canvas1.addShape(rect1);
    album.addImage(canvas1);

    assertEquals(1, album.getAlbum().size());

    //Clear all contents from album
    album.clearAlbum();
    assertEquals(0, album.getAlbum().size());
  }


  //RUN INDIVIDUALLY TO ALLOW FOR TIMESTAMPING

  @Test
  void testToString() {

    canvas1.addShape(rect1);
    canvas1.addShape(rect2);
    canvas1.addShape(rect3);
    canvas1.addDescription("Rectangles");

    canvas2.addShape(oval1);
    canvas2.addShape(oval2);
    canvas2.addShape(oval3);
    canvas2.addDescription("Ovals");

    canvas3.addShape(oval1);
    canvas3.addShape(rect2);
    canvas3.addShape(oval3);
    canvas3.addDescription("Ovals and Rectangles");

    album.addImage(canvas1);
    album.addImage(canvas2);
    album.addImage(canvas3);


    /*
    Impossible to test toString by itself since it uses timestamps, but testing if information
    contained within the toString result will be correctly formatted
    MUST RUN INDIVIDUALLY FROM OTHER TESTS <<<<-----uncomment code below and run
    */

    /*
    String albumInfo = album.toString();
    assertTrue(albumInfo.contains("Color: (1.0,0.0,0.0)"));

    assertTrue(albumInfo.contains("Center: (0.0,0.0), X radius: 1.0 , Y radius: 1.0, Color: (1.0,0.0,0.0)"));

    */

  }


}