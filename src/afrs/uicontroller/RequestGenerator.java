package afrs.uicontroller;

import afrs.appcontroller.Client;
import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.requests.AiportModeRequest;
import afrs.uicontroller.requests.AirportInfoRequest;
import afrs.uicontroller.requests.CreateReservationRequest;
import afrs.uicontroller.requests.DeleteReservationRequest;
import afrs.uicontroller.requests.DisconnectionRequest;
import afrs.uicontroller.requests.FlightInfoRequest;
import afrs.uicontroller.requests.InvalidRequest;
import afrs.uicontroller.requests.Request;
import afrs.uicontroller.requests.SearchReservationRequest;
import afrs.uicontroller.requests.UndoRedoRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RequestGenerator
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class RequestGenerator {

  // The storage of all relevant objects
  private StorageCenter storageCenter;

  /**
   * Create a new RequestGenerator object
   *
   * @param storageCenter the instance of StorageCenter
   */
  public RequestGenerator(StorageCenter storageCenter) {
    this.storageCenter = storageCenter;
  }

  /**
   * Given an input string from stdin, parse it into a request, then execute the request.
   *
   * @param input - the string input from stdin
   */
  public Request parseRequest(String clientID, String input) {
    Client client = storageCenter.getClientServices(clientID);
    Request request = new InvalidRequest(clientID, "error,unknown request");

    // TODO check if an input is prefaced with a client id
    // If so, discard it
    // Else continue

    // If clientID is valid
    if (client != null) {
      // Non-partial request
      if (input.endsWith(";")) {
        // Append any pre-existing partial input
        input = client.getCompleteRequest() + input;

        // Get request type and create list of parameters
        String[] params = input.replace(";", "").split(",");

        // Confirm command has at least the type
        if (params.length >= 1) {

          // Validate request type and create request
          String type = params[0];
          List<String> parameters = new ArrayList<>(
              Arrays.asList(params).subList(1, params.length));
          switch (type) {
            case "disconnect":
              request = new DisconnectionRequest(clientID, storageCenter);
              break;

            case "airport":
              request = new AirportInfoRequest(clientID, storageCenter, parameters);
              break;
            case "server":
              request = new AiportModeRequest(clientID, storageCenter, parameters);
              break;

            case "info":
              request = new FlightInfoRequest(clientID, storageCenter, parameters);
              break;

            case "retrieve":
              request = new SearchReservationRequest(clientID, storageCenter, parameters);
              break;
            case "reserve":
              request = new CreateReservationRequest(clientID, storageCenter, parameters);
              break;
            case "delete":
              request = new DeleteReservationRequest(clientID, storageCenter, parameters);
              break;

            case "undo":
              request = new UndoRedoRequest(clientID, storageCenter, true);
              break;
            case "redo":
              request = new UndoRedoRequest(clientID, storageCenter, false);
              break;
          }
        }
      }
      // Partial request
      else {
        // Track each input string as a continuous partial request
        client.makePartialRequest(input);
        request = new InvalidRequest(clientID, "partial-request");
      }
    }
    // Invalid clientID
    else {
      request = new InvalidRequest(clientID, "error,invalid connection");
    }

    // Update RequestHandler
    return request;
  }
}
