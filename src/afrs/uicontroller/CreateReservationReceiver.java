package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;

/**
 * Created by Apiazza on 3/3/18.
 */
public class CreateReservationReceiver extends Receiver {

  public CreateReservationReceiver(StorageCenter storageCenter) {
    super(storageCenter);
  }


  @Override
  public Response execute() {
    Response response = new Response();
    return response;
  }
}
