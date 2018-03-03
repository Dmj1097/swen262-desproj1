package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

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
    this.receiver = new SearchReservationReceiver(storageCenter);
  }

  @Override
  public Response execute() {
    return receiver.execute();
  }
}
