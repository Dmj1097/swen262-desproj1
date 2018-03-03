package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * AirportInfoRequest
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class AirportInfoRequest extends Request {

  /**
   * Create a new AirportInfoRequest object
   */
  public AirportInfoRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.receiver = new AirportInfoReceiver(storageCenter);
  }

  @Override
  public Response execute() {
    return receiver.execute();
  }
}
