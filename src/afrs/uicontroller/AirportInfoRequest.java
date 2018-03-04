package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.uiview.Response;
import java.util.List;

/**
 * AirportInfoRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class AirportInfoRequest extends Request {

  /**
   * Create a new AirportInfoRequest object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public AirportInfoRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 1)) {
      complete = true;
      return new Response("error,unknown request");
    }

    // If airport exists, respond with information
    Airport airport = storageCenter.getAirport(parameters.get(0));
    if (airport != null) {
      complete = true;
      return new Response("airport," + airport.toString());
    } else {
      complete = true;
      return new Response("error,unknown airport");
    }
  }
}
