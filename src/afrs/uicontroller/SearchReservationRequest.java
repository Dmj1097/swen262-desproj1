package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import afrs.uiview.Response;
import java.util.List;
import sun.security.provider.certpath.OCSPResponse.ResponseStatus;

/**
 * SearchReservationRequest
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class SearchReservationRequest extends Request {

  /**
   * Create a new SearchReservationRequest object
   */
  public SearchReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public Response execute() {
    String name = parameters.get(0);
    String origin = "";
    String destination = "";
    if (parameters.size() > 1) {
      origin = parameters.get(1);
      destination =  parameters.get(2);
    }

    Passenger passenger = storageCenter.getPassenger(name);
    if (passenger == null) {
      complete = true;
      return new Response("retrieve,0");
    }

    Airport dest = storageCenter.getAirport(destination);
    if (dest == null) {
      complete = true;
      return new Response("error,unknown destination");
    }

    if (parameters.size() == 3) {
      Airport orig = storageCenter.getAirport(origin);
      if (orig == null) {
        complete = true;
        return new Response("error,unknown origin");
      }
    }

    List<Journey> journeys = storageCenter.getReservations(name, origin, destination);
    StringBuilder result = new StringBuilder();
    for (Journey j : journeys) {
      result.append(j).append("\n");
    }
    complete = true;
    return new Response("info," + journeys.size() + "\n" + result);

  }
}
