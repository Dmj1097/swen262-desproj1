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

  /** The storage of all relevant objects */
  private StorageCenter storageCenter;
  /** String representing part of a request that has not yet been terminated */
  private String partialRequestString;

  /**
   * Create a new RequestGenerator object
   * @param storageCenter - the object storage which will be queried
   * @return a request generator with a given storage center
   */
  public RequestGenerator(StorageCenter storageCenter) {
    this.storageCenter = storageCenter;
    this.partialRequestString = "";
  }

  /**
   * Given an input string from stdin, parse it into a request, then execute the request.
   * @param input - the string input from stdin
   */
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
      System.out.println("partial-request");  // Relevant output
    }
  }
}
