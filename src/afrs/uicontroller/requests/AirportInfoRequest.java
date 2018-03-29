package afrs.uicontroller.requests;

import afrs.appcontroller.Client.Service;
import afrs.appcontroller.FAAWeatherCenter;
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
  public AirportInfoRequest(String clientID, StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.clientID = clientID;
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
      return new Response(clientID + ",error,unknown request");
    }

    String airportCode = parameters.get(0);

    // If airport exists, respond with information
    if (!storageCenter.getAirportKeys().contains(airportCode)) {
      return new Response(clientID + ",error,unknown airport");
    }

    // Get airport based on Service mode
    Airport airport;
    boolean mode = storageCenter.getClient(clientID).getService() == Service.FAA;
    if (mode) {
      airport = FAAWeatherCenter.getInstance(airportCode);
    } else {
      airport = storageCenter.getAirport(airportCode);
    }

    return new Response(clientID + ",airport," + airport.getString(storageCenter.getClient(clientID).getAirportWeatherIterator(airportCode)));
  }
}
