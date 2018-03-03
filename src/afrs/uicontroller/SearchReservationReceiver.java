package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;

/**
 * Created by Apiazza on 3/2/18.
 */
public class SearchReservationReceiver extends Receiver {

  protected SearchReservationReceiver(StorageCenter storageCenter) {
    super(storageCenter);
  }

  @Override
  public Response execute() {
    Response response = new Response();
    return response;
  }
}
