package afrs.uicontroller;

import java.util.Observable;

/**
 * RequestGenerator
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class RequestGenerator extends Observable {

  /**
   * Create a new RequestGenerator object
   */
  public RequestGenerator() {

  }

  public void parseRequest(String input) {
    Request request = null;

    setChanged();
    notifyObservers(request);
  }
}
