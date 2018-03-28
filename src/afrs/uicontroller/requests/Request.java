package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * Request
 *
 * Created By Brian Taylor - 03/03/2018
 */
public abstract class Request {

  protected String clientID;
  protected StorageCenter storageCenter;
  protected List<String> parameters;

  public String getClientID() {
    return clientID;
  }


  /**
   * Create a new Request object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  protected Request(StorageCenter storageCenter, List<String> parameters) {
    this.parameters = parameters;
    this.storageCenter = storageCenter;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  public abstract Response execute();

  public void undo() {}

  public void redo() {}
}
