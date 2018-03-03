package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
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
  }

  @Override
  public String execute() {
    Airport airport = storageCenter.getAirport(parameters.get(0));
    if (airport != null) {
      complete = true;
      return "airport," + airport.getName() + "," + airport.getWeather() + "," + airport.getDelayTime();
    } else {
      // Error
      complete = true;
      return "error,unknown airport";
    }
  }
}
