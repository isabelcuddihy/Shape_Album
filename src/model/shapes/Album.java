package model.shapes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;


/**
 * Class creates an album of shape images.
 */
public class Album implements IAlbum {

  private List<String> snapshotID;

  private LinkedHashMap<String, ICanvas> shapeAlbum = new LinkedHashMap<String, ICanvas>();

  //Creates single instance of album and prevents additional object creation
  private static final Album INSTANCE = new Album();

  private Album() {
    this.snapshotID = new ArrayList<>();
  }


  //Single album
  public static Album getInstance() {
    return INSTANCE;
  }

  /**
   * Creates an image for the album.
   * @ param String name
   * @ param List
   * @ return ICanvas
   */

  @Override
  public ICanvas createImage(String name, List<IShape> shapes) {
    //check if shapes exist
    if (shapes == null || shapes.isEmpty()) {
      throw new IllegalArgumentException("Image must contain at least one shape");
    }
    //create a new image canvas
    ICanvas newImage = new ShapeCanvas(name);
    List<IShape> finalShapes = shapes.stream().toList();
    for (IShape shape : finalShapes) {
      newImage.addShape(shape);
    }
    //Add a name for the image
    newImage.addDescription(invalidName(name));
    return newImage;
  }

  /**
   * Method gets current image at Album location using snapshot ID.
   * @ param snapshotID String
   * @ return ICanvas
   */
  @Override
  public ICanvas getImage(String snapshotID) {

    return shapeAlbum.get(snapshotID);
  }

  /**
   * Method adds current image to end of album using snapshot ID.
   * @ param snapshotID String
   */
  @Override
  public void addImage(ICanvas snapshot) {
    //Check if image already in album
    List<String> ImageNames = this.shapeAlbum.values().stream()
            .map(ICanvas::getDescription).toList();

    final Date currentTime = new Date();
    //Format timestamp information
    String timestamp = createTimestamp(currentTime);

    //Add image to album
    this.shapeAlbum.put(timestamp, snapshot);
    //add identifier to snapshotID list
    this.snapshotID.add(timestamp);
  }

  private String createTimestamp(Date currentTime) {
    //Format timestamp information
    final SimpleDateFormat timestampFormat =
            new SimpleDateFormat("MM-dd-yyyy hh:mm:ss:SSS");
    timestampFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    return timestampFormat.format(currentTime);
  }


  /**
   * Method returns list of all snapshot descriptions.
   * @ return List
   */
  @Override
  public List<String> getAllSnapshotsDescriptions() {
    //Create protected unmodifiable list using all snapshot descriptions
    List<String> descriptions = new ArrayList<>(this.shapeAlbum.values()
            .stream().map(ICanvas::getDescription).toList());


    return Collections.unmodifiableList(descriptions);
  }

  /**
   * Method returns hashmap of album.
   * @ return LinkedHash
   */
  @Override
  public LinkedHashMap<String, ICanvas> getAlbum() {
    return this.shapeAlbum;
  }

  /**
   * Method returns list of all snapshot IDs.
   * @ return List
   */
  @Override
  public List<String> getIDs() {
    return this.snapshotID;
  }

  /**
   * Method clears content of album.
   */
  @Override
  public void clearAlbum() { // delete album
    this.shapeAlbum.clear();
  }


  /**
   * Helper method prevent invalid naming of shapes.
   * @ param name String
   * @ return String
   */
  private String invalidName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    } else {
      return name;
    }
  }

  /**
   * Create string description of entire album.
   * @ return String
   */
  @Override
  public String toString() {
    String fullAlbum = "";
    int i;
    try {
      for (i = 0; i < this.snapshotID.size() - 1; i++) {
        fullAlbum = fullAlbum.concat(shapeAlbum.get(snapshotID.get(i)).toString()
                + "\n" + "Snapshot ID: " + snapshotID.get(i)
                + "\n");
      }
      //Last entry in album, no new lines at end
      fullAlbum = fullAlbum.concat(shapeAlbum.get(snapshotID.get(i)).toString()
                      + "\n" + "Snapshot ID: " + snapshotID.get(i)
              );

      return fullAlbum;
    } catch (IndexOutOfBoundsException e) {
      return "Album is empty, nothing to print";
    }
  }
}