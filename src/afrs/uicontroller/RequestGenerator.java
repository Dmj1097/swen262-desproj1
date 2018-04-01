package afrs.uicontroller;

import afrs.appcontroller.Client;
import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.requests.*;
import afrs.uiview.TerminalClient;

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
  public Request parseRequest(String input) {
    if (input.equals("connect;")) {
      return new ConnectionRequest(storageCenter);
    }

    String[] params = input.split(",");
    if (params.length < 2) {
      return new InvalidRequest("error,invalid connection");
    }

    String clientID = params[0];
    Client client = storageCenter.getClient(clientID);
    if (client == null) {
      return new InvalidRequest("error,invalid connection");
    }

    // Non-partial request
    if (input.trim().endsWith(";")) {
      // Trim clientID from input
      input = input.replaceFirst(clientID + ",", "");

      // Append any pre-existing partial input
      input = client.getCompleteRequest() + input;

      params = input.replace(";", "").split(",");
      String type = params[0];
      List<String> parameters = new ArrayList<>(
              Arrays.asList(params).subList(1, params.length));
      switch (type) {
        case "disconnect":
          return new DisconnectionRequest(clientID, storageCenter);

        case "airport":
          return new AirportInfoRequest(clientID, storageCenter, parameters);
        case "server":
          return new AiportModeRequest(clientID, storageCenter, parameters);

        case "info":
          return new FlightInfoRequest(clientID, storageCenter, parameters);

        case "retrieve":
          return new SearchReservationRequest(clientID, storageCenter, parameters);
        case "reserve":
          return new CreateReservationRequest(clientID, storageCenter, parameters);
        case "delete":
          return new DeleteReservationRequest(clientID, storageCenter, parameters);

        case "undo":
          return new UndoRedoRequest(clientID, storageCenter, true);
        case "redo":
          return new UndoRedoRequest(clientID, storageCenter, false);
      }
      return new InvalidRequest(clientID + ",error,unknown request");
    }
    // Partial request
    else {
      // Trim clientID from input
      input = input.replaceFirst(clientID + ",", "");

      // Track each input string as a continuous partial request
      client.makePartialRequest(input);
      return new InvalidRequest(clientID + ",partial-request");
    }
  }
}
