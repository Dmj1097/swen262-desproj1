package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Journey;
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
  }

  @Override
  public String execute() {
    String response = null;
    //Journey journey =
    return response;
  }
}
