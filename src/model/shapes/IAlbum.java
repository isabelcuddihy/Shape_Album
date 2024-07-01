package model.shapes;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Interface contains contract for Album.
 */
public interface IAlbum {

  /**
   * Method creates a new image snapshot for the album.
   * @ param String
   * @ param List
   * @ return ICanvas
   */
  ICanvas createImage(String name, List<IShape> shapes);

  /**
   * Method gets current image at Album location using snapshot ID.
   * @ param snapshotID String
   * @ return ICanvas
   */
  ICanvas getImage(String snapshotID);

  /**
   * Method adds current image to end of album using snapshot ID.
   * @ param snapshotID String
   */
  void addImage(ICanvas snapshot);

  /**
   * Method returns list of all snapshot descriptions.
   * @ return List
   */
  List<String> getAllSnapshotsDescriptions();

  /**
   * Method returns hashmap of album.
   * @ return LinkedHash
   */
  LinkedHashMap<String, ICanvas> getAlbum();

  /**
   * Method returns list of all snapshot IDs.
   * @ return List
   */
  List<String> getIDs();


  /**
   * Method clears content of album.
   */
  void clearAlbum();
}
