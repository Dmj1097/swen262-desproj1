package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import afrs.uiview.Response;
import java.util.List;

public class DeleteReservationRequest extends Request {

  /**
   * Create a new Request object
   */
  public DeleteReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public Response execute() {
    Passenger passenger = storageCenter.getPassenger(parameters.get(0));
    if (passenger == null) {
      return new Response("error,reservation not found");
    }

    if (false) {//storageCenter.remove(parameters.get(0), parameters.get(1), parameters.get(2))) {
      complete = true;
      return new Response("delete,successful");
    } else {
      complete = true;
      return new Response("error,reservation not found");
    }
  }
}
