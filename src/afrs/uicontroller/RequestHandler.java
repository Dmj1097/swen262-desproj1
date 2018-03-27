package afrs.uicontroller;

import afrs.uicontroller.collection.RequestCollection;
import afrs.uicontroller.requests.CreateReservationRequest;
import afrs.uicontroller.requests.DeleteReservationRequest;
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

  private RequestCollection requests;
  private ResponseHandler handler;

  /**
   * Create a new RequestHandler object
   */
  public RequestHandler(ResponseHandler responseHandler) {
    this.requests = new RequestCollection();
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
      if (arg instanceof CreateReservationRequest || arg instanceof DeleteReservationRequest) {
        requests.add((Request) arg);
      }
    }
  }

  public boolean undo() {
    return requests.undo();
  }

  public boolean redo() {
    return requests.redo();
  }
}
