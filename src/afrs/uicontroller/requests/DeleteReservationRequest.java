package afrs.uicontroller.requests;

import afrs.appcontroller.StorageCenter;
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

  private Reservation deleted;
  public Reservation getDeleted() {
    return deleted;
  }

  /**
   * Create a new Request object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public DeleteReservationRequest(String clientID, StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.clientID = clientID;
    this.deleted = null;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() == 3)) {
      return new Response(clientID + ",error,unknown request");
    }

    // Validate passenger
    Passenger passenger = storageCenter.getPassenger(parameters.get(0));
    if (passenger == null) {
      return new Response(clientID + ",error,reservation not found");
    }

    // Attempt deletion of reservation
    this.deleted = storageCenter.getReservation(parameters.get(0), parameters.get(1), parameters.get(2));
    if (deleted != null && storageCenter.removeReservation(parameters.get(0), parameters.get(1), parameters.get(2))) {
      storageCenter.getClientServices(clientID).makeRequest(this);
      return new Response(clientID + ",delete,successful");
    } else {
      return new Response(clientID + ",error,reservation not found");
    }
  }

  @Override
  public void undo() {
    storageCenter.addPassengerOrReservation(deleted.getPassenger().getName(), deleted);
  }

  @Override
  public void redo() {
    storageCenter.removeReservation(parameters.get(0), parameters.get(1), parameters.get(2));
  }
}
