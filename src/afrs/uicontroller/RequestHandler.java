package afrs.uicontroller;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * RequestHandler
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class RequestHandler implements Observer {

  private LinkedList<Request> requestQueue;

  /**
   * Create a new RequestHandler object
   */
  public RequestHandler(RequestGenerator requestGenerator) {
    requestQueue = new LinkedList<>();
    requestGenerator.addObserver(this);
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof Request) {
      requestQueue.push((Request) arg);
    }
  }
}
