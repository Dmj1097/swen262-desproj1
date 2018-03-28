package afrs.uicontroller.requests;

import afrs.appcontroller.ClientServices;
import afrs.appcontroller.ClientServices.Service;
import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

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

    ClientServices client = storageCenter.getClientServices(clientID);
    Service service = client.getService();
    if ((service == Service.Local && mode.equals("faa"))
        || (service == Service.FAA && mode.equals("local"))) {
      client.switchService();
    }

    return new Response(clientID + ",server,successful");
  }
}
