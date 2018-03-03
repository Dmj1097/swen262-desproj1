package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
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
    this.receiver = new CreateReservationReceiver(storageCenter);
  }

  @Override
  public Response execute() {
    return receiver.execute();
  }
}
