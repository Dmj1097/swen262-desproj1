package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import afrs.uiview.Response;
import java.util.List;

/**
 * CreateReservationRequest
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class CreateReservationRequest extends Request {

  /**
   * Create a new CreateReservationRequest object
   */
  public CreateReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public Response execute() {
    Journey journey = storageCenter.getItinerary(Integer.parseInt(parameters.get(0)));
    if (journey != null) {
      String name = parameters.get(1);
      Passenger passenger = storageCenter.getPassenger(name);
      if (passenger == null) {
        passenger = new Passenger(name);
      }
      Reservation reservation = new Reservation(passenger, journey);
      if (storageCenter.addPassengerOrReservation(name, reservation)) {
        complete = true;
        return new Response("reserve,successful");
      } else {
        complete = true;
        return new Response("error,duplicate reservation");
      }
    } else {
      complete = true;
      return new Response("error,invalid id");
    }
    return new Response("");
  }
}
