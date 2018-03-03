package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

/**
 * FlightInfoRequest
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class FlightInfoRequest extends Request {

  /**
   * Create a new FlightInfoRequest object
   */
  public FlightInfoRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.receiver = new FlightInfoReceiver(storageCenter);

  }

  @Override
  public Response execute() {
    return receiver.execute();
  }
}
