package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Journey;
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
  public String execute() {
    /*Journey journey =
    if (journey != null) {
      //Reservation reservation =
      if (!contains(reservation)) {
        addReservation();
        complete = true;
        return "reserve,successful";
      } else {
        complete = true;
        return "error,duplicate reservation";
      }
    } else {
      complete = true;
      return "error,invalid id";
    }*/
    return "";
  }
}
