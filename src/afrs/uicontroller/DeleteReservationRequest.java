package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import afrs.uiview.Response;
import java.util.List;

/**
 * DeleteReservationRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class DeleteReservationRequest extends Request {

  /**
   * Create a new Request object
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public DeleteReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  /**
   * Executes the command
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 3)) {
      complete = true;
      return new Response("error,unknown request");
    }

    // Validate passenger
    Passenger passenger = storageCenter.getPassenger(parameters.get(0));
    if (passenger == null) {
      return new Response("error,reservation not found");
    }

    // Attempt deletion of reservation
    if (storageCenter.removeReservation(parameters.get(0), parameters.get(1), parameters.get(2))) {
      complete = true;
      return new Response("delete,successful");
    } else {
      complete = true;
      return new Response("error,reservation not found");
    }
  }
}
