package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * Request
 *
 * Create By Alex Piazza - 03/01/2018
 */
public abstract class Request {

  protected boolean complete;
  protected List<String> parameters;
  protected StorageCenter storageCenter;

  /**
   * Create a new Request object
   */
  protected Request(StorageCenter storageCenter, List<String> parameters) {
    this.complete = false;
    this.parameters = parameters;
    this.storageCenter = storageCenter;
  }

  public abstract Response execute();

  public boolean hasCompleted() {
    return complete;
  }

}
