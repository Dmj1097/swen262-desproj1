package afrs.uicontroller;

import afrs.uiview.ResponseHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * RequestHandler
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class RequestHandler implements Observer {

  private LinkedList<Request> requestQueue;
  private ResponseHandler handler;

  /**
   * Create a new RequestHandler object
   */
  public RequestHandler(RequestGenerator requestGenerator, ResponseHandler responseHandler) {
    requestQueue = new LinkedList<>();
    requestGenerator.addObserver(this);
    this.handler = responseHandler;
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof Request) {
      requestQueue.offer((Request) arg);
    }
    handler.writeResponse(requestQueue.poll().execute());
  }
}
