package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import afrs.uiview.TerminalClient;

public class ConnectionRequest extends Request {

  public ConnectionRequest(StorageCenter storageCenter) {
    super(storageCenter, null);
    this.clientID = "invalid";
  }

  @Override
  public Response execute() {
    this.clientID = new TerminalClient(storageCenter, null, false).getID();
    return new Response("connect," + clientID);
  }
}