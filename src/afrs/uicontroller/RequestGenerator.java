package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import java.util.ArrayList;
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
    if (input.endsWith(";")) {
      String[] parms = input.replace(";", "").split(",");
      String type = parms[0];
      List<String> parameters = new ArrayList<>(Arrays.asList(parms).subList(1, parms.length));
      Request request = new InvalidRequest(storageCenter, null);

      if (parameters.size() >= 1) {
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
        }
      }

      setChanged();
      notifyObservers(request);
    }
  }
}
