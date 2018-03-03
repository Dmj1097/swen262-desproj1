package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;

public class DeleteReservationReceiver extends Receiver {

  protected DeleteReservationReceiver(StorageCenter storageCenter) {
    super(storageCenter);
  }

  @Override
  public Response execute() {
    Response response = new Response();
    return response;
  }
}
