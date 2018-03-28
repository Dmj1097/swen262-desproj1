package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.requests.Request;
import afrs.uiview.ResponseHandler;
import java.util.Observable;
import java.util.Observer;

/**
 * RequestHandler
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class RequestHandler implements Observer {

  private StorageCenter storageCenter;
  private ResponseHandler handler;

  /**
   * Create a new RequestHandler object
   */
  public RequestHandler(StorageCenter storageCenter, ResponseHandler responseHandler) {
    this.storageCenter = storageCenter;
    this.handler = responseHandler;
  }

  /**
   * Gets new request from RequestGenerator and executes it, handling its response
   */
  @SuppressWarnings("ConstantConditions")
  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof Request) {
      handler.writeResponse(((Request) arg).execute());
    }
  }
}
