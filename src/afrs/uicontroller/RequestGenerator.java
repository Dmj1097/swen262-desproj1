package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * RequestGenerator
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class RequestGenerator extends Observable {

  private StorageCenter storageCenter;

  /**
   * Create a new RequestGenerator object
   */
  public RequestGenerator(StorageCenter storageCenter) {
    this.storageCenter = storageCenter;
  }

  public void parseRequest(String input) {
    List<String> parameters = Arrays.asList(input.split(","));
    Request request = new InvalidRequest(storageCenter, parameters);

    if (parameters.size() >= 2) {
      String type = parameters.remove(0);

      switch (type) {
        case "airport":
          request = new AirportInfoRequest(storageCenter, parameters);
          break;
        case "info":
          request = new FlightInfoRequest(storageCenter, parameters);
          break;
        case "retrieve":
          request = new SearchReservationRequest(storageCenter, parameters);
          break;
        case "reserve":
          request = new CreateReservationRequest(storageCenter, parameters);
          break;
        case "delete":
          request = new DeleteReservationRequest(storageCenter, parameters);
          break;
        default:
          // Invalid
          break;
      }
    }
    
    setChanged();
    notifyObservers(request);
  }
}
