package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.UUID;

public class ConnectionRequest extends Request {

  public ConnectionRequest(StorageCenter storageCenter) {
    super(storageCenter, null);
    this.clientID = "invalid";
  }

  @Override
  public Response execute() {

    return new Response("connect," + clientID);
  }
}
