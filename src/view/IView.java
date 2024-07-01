package view;

import model.shapes.IAlbum;
import model.shapes.ICanvas;
import model.shapes.IShape;

/**
 * Interface contains re-usable methods for all view classes.
 */
public interface IView {

  /**
   * Method creates an HTML file from album object.
   * @ param album IAlbum
   * @ param endingFilename String
   */
  void writeHTMLFileData(IAlbum album, String endingFilename);

}
