package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * Request
 *
 * Created By Brian Taylor - 03/03/2018
 */
public abstract class Request {

  protected StorageCenter storageCenter;
  protected List<String> parameters;
  protected boolean complete;

  /**
   * Create a new Request object
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  protected Request(StorageCenter storageCenter, List<String> parameters) {
    this.complete = false;
    this.parameters = parameters;
    this.storageCenter = storageCenter;
  }

  /**
   * Executes the command
   * @return the command's response
   */
  public abstract Response execute();

  /**
   * @return boolean representing execution completion
   */
  public boolean hasCompleted() {
    return complete;
  }

}
