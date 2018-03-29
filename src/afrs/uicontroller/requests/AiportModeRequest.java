package afrs.uicontroller.requests;

import afrs.appcontroller.Client;
import afrs.appcontroller.Client.Service;
import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * class representing a request that changes the current server that a user is using for airport info and reservation making
 *
 * Created By Brian Taylor - 3/27/2018
 */
public class AiportModeRequest extends Request {

  public AiportModeRequest(String clientID, StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.clientID = clientID;
  }

  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 1)) {
      return new Response(clientID + ",error,unknown request");
    }

    String mode = parameters.get(0);
    if (!mode.equals("local") && !mode.equals("faa")) {
      return new Response(clientID + ",error,unknown information server");
    }

    Client client = storageCenter.getClient(clientID);
    Service service = client.getService();
    if ((service == Service.Local && mode.equals("faa"))
        || (service == Service.FAA && mode.equals("local"))) {
      client.switchService();
    }

    return new Response(clientID + ",server,successful");
  }
}
