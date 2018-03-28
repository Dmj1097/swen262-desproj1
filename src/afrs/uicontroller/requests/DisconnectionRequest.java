package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;

public class DisconnectionRequest extends Request {

  public DisconnectionRequest(String clientID, StorageCenter storageCenter) {
    super(storageCenter, null);
    this.clientID = clientID;
  }

  @Override
  public Response execute() {
    storageCenter.disconnectClient(clientID);
    return new Response(clientID + ",disconnect");
  }
}
