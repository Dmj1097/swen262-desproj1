package afrs.uicontroller.requests;

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

  private Reservation created;

  /**
   * Create a new CreateReservationRequest object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public CreateReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.created = null;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 2)) {
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
      this.created = new Reservation(passenger, journey);
      if (storageCenter.addPassengerOrReservation(created.getPassenger().getName(), created)) {
        return new Response("reserve,successful");
      } else {
        return new Response("error,duplicate reservation");
      }
    } catch (IndexOutOfBoundsException ignored) {
      return new Response("error,invalid id");
    }
  }

  @Override
  public void undo() {
    storageCenter.removeReservation(created.getPassenger().getName(), created.getOrigin(), created.getDestination());
  }

  @Override
  public void redo() {
    storageCenter.addPassengerOrReservation(created.getPassenger().getName(), created);
  }
}
