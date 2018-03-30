package afrs.appcontroller;

/**
 * Interface that all storage objects inherit from
 *
 * @author Dylan Johnson
 */
public interface Storage {

  /**
   * Return the whichever object is being held in a storage
   */
  Object getInstance(Object ID);

  /**
   * Populate the storage object
   */
  void setupMap();

}
