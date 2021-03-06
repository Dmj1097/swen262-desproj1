package afrs.uicontroller.requests;

import afrs.appcontroller.Client.Service;
import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Journey;
import afrs.uicontroller.sort.ArriveSort;
import afrs.uicontroller.sort.CostSort;
import afrs.uicontroller.sort.DepartSort;
import afrs.uiview.Response;
import java.util.Comparator;
import java.util.List;

/**
 * FlightInfoRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class FlightInfoRequest extends Request {

  /**
   * Create a new FlightInfoRequest object
   *
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public FlightInfoRequest(String clientID, StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
    this.clientID = clientID;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If invalid number of parameters
    if (!(parameters.size() >= 2 && parameters.size() <= 4)) {
      return new Response(clientID + ",error,unknown request");
    }

    // Validate origin airport
    Airport origin = storageCenter.getAirport(parameters.get(0));
    if (origin == null) {
      return new Response(clientID + ",error,unknown origin");
    }

    // Validate destination airport
    Airport destination = storageCenter.getAirport(parameters.get(1));
    if (destination == null) {
      return new Response(clientID + ",error,unknown destination");
    }

    // Validate connection limit if exists
    int connections = 2;
    if (parameters.size() > 2 && !parameters.get(2).equals("")) {
      connections = Integer.parseInt(parameters.get(2));
      if (connections < 0 || connections > 2) {
        return new Response(clientID + ",error,invalid connection limit");
      }
    }

    // Validate sort order if exists
    Comparator<Journey> sort = new DepartSort();
    if (parameters.size() > 3) {
      String type = parameters.get(3);
      switch (type) {
        case "departure":
          break;
        case "arrival":
          sort = new ArriveSort();
          break;
        case "airfare":
          sort = new CostSort();
          break;
        default:
          return new Response(clientID + ",error,invalid sort order");
      }
    }

    // Get list of journeys, sorted
    boolean mode = storageCenter.getClient(clientID).getService() == (Service.FAA);
    List<Journey> journeys = storageCenter
        .getLatestJourneys(origin.getAbbreviation(), destination.getAbbreviation(), connections,
            mode);
    journeys.sort(sort);
    storageCenter.getClient(clientID).setLatestJourneys(journeys);

    // Create response
    StringBuilder result = new StringBuilder();
    int index = 1;
    for (Journey j : journeys) {
      result.append(index).append(",").append(j).append("\n");
      index++;
    }
    return new Response(
        clientID + ",info," + journeys.size() + "\n" + result.toString().replaceFirst("\n$", ""));
  }
}
