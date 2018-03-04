package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Airport;
import afrs.appmodel.Journey;
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
   * @param storageCenter the StorageCenter instance
   * @param parameters the list of parameters to the command
   */
  public FlightInfoRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  /**
   * Executes the command
   * @return the command's response
   */
  @Override
  public Response execute() {
    if (!(parameters.size() >= 2 && parameters.size() <= 4)) {
      complete = true;
      return new Response("error,unknown request");
    }

    Airport origin = storageCenter.getAirport(parameters.get(0));
    if (origin == null) {
      complete = true;
      return new Response("error,unknown origin");
    }

    Airport destination = storageCenter.getAirport(parameters.get(1));
    if (destination == null) {
      complete = true;
      return new Response("error,unknown destination");
    }

    int connections = 2;
    if (parameters.size() > 2 && !parameters.get(2).equals("")) {
      connections = Integer.parseInt(parameters.get(2));
      if (connections < 0 || connections > 2) {
        complete = true;
        return new Response("error,invalid connection limit");
      }
    }

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
          complete = true;
          return new Response("error,invalid sort order");
      }
    }

    List<Journey> journeys = storageCenter.getLatestJourneys(origin.getAbbreviation(), destination.getAbbreviation(), connections);
    journeys.sort(sort);
    StringBuilder result = new StringBuilder();
    int index = 1;
    for (Journey j : journeys) {
      result.append(index).append(",").append(j).append("\n");
      index++;
    }
    complete = true;
    return new Response("info," + journeys.size() + "\n" + result);
  }
}
