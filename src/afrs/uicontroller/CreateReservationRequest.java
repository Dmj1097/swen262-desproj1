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
 * Created By Brian Taylor - 03/03/2018
 */
public class CreateReservationRequest extends Request {

  /**
   * Create a new CreateReservationRequest object
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public CreateReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  /**
   * Executes the command
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 2)) {
      complete = true;
      return new Response("error,unknown request");
    }

    try {
      // Attempt to get journey from latestJourneys
      Journey journey = storageCenter.getItinerary(Integer.parseInt(parameters.get(0)));

      // Validate passenger
      String name = parameters.get(1);
      Passenger passenger = storageCenter.getPassenger(name);
      if (passenger == null) {
        passenger = new Passenger(name);
      }

      // Attempt reservation
      Reservation reservation = new Reservation(passenger, journey);
      if (storageCenter.addPassengerOrReservation(name, reservation)) {
        complete = true;
        return new Response("reserve,successful");
      } else {
        complete = true;
        return new Response("error,duplicate reservation");
      }
    } catch (IndexOutOfBoundsException ignored) {
      complete = true;
      return new Response("error,invalid id");
    }
  }
}
