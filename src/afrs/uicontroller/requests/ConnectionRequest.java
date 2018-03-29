package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.UUID;

/**
 * Request that connects a new user to the system
 */
public class ConnectionRequest extends Request {

  public ConnectionRequest(StorageCenter storageCenter) {
    super(storageCenter, null);
    this.clientID = "invalid";
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {

    return new Response("connect," + clientID);
  }
}
