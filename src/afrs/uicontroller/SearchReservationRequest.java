package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Passenger;
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
    Passenger passenger = storageCenter.getPassenger(parameters.get(0));
    if (passenger == null) {
      complete = true;
      return new Response("retrieve,0");
    }

    if (parameters.size() == 3) {
      Airport orig = storageCenter.getAirport(parameters.get(1));
      if (orig == null) {
        complete = true;
        return new Response("error,unknown origin");
      }

      Airport dest = storageCenter.getAirport(parameters.get(2));
      if (dest == null) {
        complete = true;
        return new Response("error,unknown destination");
      }

      //getReservations
      complete = true;
      return new Response("");
    } else {
      Airport dest = storageCenter.getAirport(parameters.get(1));
      if (dest == null) {
        complete = true;
        return new Response("error,unknown destination");
      }

      //getReservations
      complete = true;
      return new Response("");
    }
  }
}
