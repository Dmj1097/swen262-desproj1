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
  private String partialRequestString;

  /**
   * Create a new RequestGenerator object
   */
  public RequestGenerator(StorageCenter storageCenter) {
    this.storageCenter = storageCenter;
    this.partialRequestString = "";
  }

  public void parseRequest(String input) {
    if (input.endsWith(";")) {

      // The current input is the end of a pre-existing, partially-complete request
      if(!partialRequestString.equals("")){
        input = String.format("%s%s", partialRequestString, input);
        partialRequestString = "";
      }

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

    // Partial requests
    else{
      // Track each input string as a continuous partial request
      this.partialRequestString = String.format("%s%s", this.partialRequestString, input);
    }
  }
}
