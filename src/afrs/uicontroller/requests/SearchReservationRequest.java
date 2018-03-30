package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.uicontroller.sort.AirportSort;
import afrs.uiview.Response;
import java.util.List;

/**
 * SearchReservationRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class SearchReservationRequest extends Request {

  /**
   * Create a new SearchReservationRequest object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public SearchReservationRequest(String clientID, StorageCenter storageCenter,
      List<String> parameters) {
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
    if (!(parameters.size() >= 1 && parameters.size() <= 3)) {
      return new Response(clientID + ",error,unknown request");
    }

    // Get [optional] parameters
    String name = parameters.get(0);
    String origin = "";
    String destination = "";
    if (parameters.size() > 1) {
      origin = parameters.get(1);
    }
    if (parameters.size() > 2) {
      destination = parameters.get(2);
    }

    // Validate passenger
    Passenger passenger = storageCenter.getPassenger(name);
    if (passenger == null) {
      return new Response(clientID + ",retrieve,0");
    }

    // Validate origin airport if exists
    if (!origin.equals("")) {
      Airport orig = storageCenter.getAirport(origin);
      if (orig == null) {
        return new Response(clientID + ",error,unknown origin");
      }
    }

    // Validate destination airport if exists
    if (!destination.equals("")) {
      Airport dest = storageCenter.getAirport(destination);
      if (dest == null) {
        return new Response(clientID + ",error,unknown destination");
      }
    }

    // Get list of journeys, sorted
    List<Journey> journeys = storageCenter.getReservations(name, origin, destination);
    journeys.sort(new AirportSort());

    // Create response
    StringBuilder result = new StringBuilder();
    for (Journey j : journeys) {
      result.append(j).append("\n");
    }
    return new Response(clientID + ",retrieve," + journeys.size() + "\n" + result);

  }
}
