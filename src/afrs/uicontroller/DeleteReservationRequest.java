package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

public class DeleteReservationRequest extends Request {

  /**
   * Create a new Request object
   */
  protected DeleteReservationRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.receiver = new DeleteReservationReceiver(storageCenter);
  }

  @Override
  public Response execute() {
    return receiver.execute();
  }
}
