package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.requests.AirportInfoRequest;
import afrs.uicontroller.requests.CreateReservationRequest;
import afrs.uicontroller.requests.DeleteReservationRequest;
import afrs.uicontroller.requests.FlightInfoRequest;
import afrs.uicontroller.requests.InvalidRequest;
import afrs.uicontroller.requests.Request;
import afrs.uicontroller.requests.SearchReservationRequest;
import afrs.uicontroller.requests.UndoRedoRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/**
 * RequestGenerator
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class RequestGenerator extends Observable {

  // The storage of all relevant objects
  private StorageCenter storageCenter;

  private RequestHandler requestHandler;

  // String representing part of a request that has not yet been terminated
  private String partialRequestString;

  /**
   * Create a new RequestGenerator object
   *
   * @param storageCenter the instance of StorageCenter
   */
  public RequestGenerator(StorageCenter storageCenter, RequestHandler requestHandler) {
    this.storageCenter = storageCenter;
    this.requestHandler = requestHandler;
    addObserver(requestHandler);
    this.partialRequestString = "";
  }

  /**
   * Given an input string from stdin, parse it into a request, then execute the request.
   *
   * @param input - the string input from stdin
   */
  public void parseRequest(String input) {
    // Non-partial request
    if (input.endsWith(";")) {

      // The current input is the end of a pre-existing, partially-complete request
      if (!partialRequestString.equals("")) {
        input = partialRequestString + input;
        partialRequestString = "";
      }

      // Get request type and create list of parameters
      String[] params = input.replace(";", "").split(",");
      String type = params[0];
      List<String> parameters = new ArrayList<>(Arrays.asList(params).subList(1, params.length));
      Request request = new InvalidRequest();
      System.out.println(type);

      // Validate request type and create request
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
          case "undo":
            request = new UndoRedoRequest(requestHandler, true);
            break;
          case "redo":
            request = new UndoRedoRequest(requestHandler, false);
            break;
        }
      }

      // Update RequestHandler
      setChanged();
      notifyObservers(request);
    }

    // Partial request
    else {
      // Track each input string as a continuous partial request
      this.partialRequestString += input;
      System.out.println("partial-request");  // Relevant output
    }
  }
}
