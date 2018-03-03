package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Reservation;
import java.util.List;

public class DeleteReservationRequest extends Request {

  /**
   * Create a new Request object
   */
  public DeleteReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public String execute() {
    /*Reservation reservation =
    if (deleteReservation(reservation)) {
      complete = true;
      return "delete,successful";
    } else {
      complete = true;
      return "error,reservation not found";
    }
    */
    return "";
  }
}
