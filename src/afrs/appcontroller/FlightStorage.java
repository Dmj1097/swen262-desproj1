package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Journey;
import afrs.appmodel.Time;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * FlightStorage class that stores all individual flight objects that are read in from flights.txt
 *
 * @author Dylan Johnston
 */
public class FlightStorage {


  private Map<Integer, Flight> flights; //map of flights to their unique number

  /**
   * Create a new FlightStorage object
   */
  public FlightStorage() {
    flights = new HashMap<>();
    setupFlightMap();
  }

  /**
   * setup for the flight map. reads each line, parses it, creates the object, and stores it
   */
  private void setupFlightMap() {
    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/flights.txt")));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        Time arrive = new Time(
            line_info[3]); //creates time objects that will be used during itinerary generation
        Time depart = new Time(line_info[2]);
        flights.put(Integer.parseInt(line_info[4]),
            new Flight(Integer.parseInt(line_info[4]), line_info[0], line_info[1],
                Integer.parseInt(line_info[5]), depart, arrive));
      }                                             // new flight object created with info from parsed line
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read flights.txt");
      e.printStackTrace();
    }
  }

  /**
   * finds a journey object from the flight storage based on the given ID
   *
   * @param ID flight number
   * @return corresponding flight
   */
  public Journey getFlight(int ID) {
    return flights.get(ID);
  }

  /**
   * gets all flights with given airport as destination
   *
   * @param ID Origin Id name
   * @return all flights that start at origin airport
   */
  public ArrayList<Flight> getFlightsFromOrigin(String ID) {
    ArrayList<Flight> flightsFromOrigin = new ArrayList<>();
    for (Flight flight : flights.values()) {
      if (flight.getOrigin().equals(ID)) {
        flightsFromOrigin.add(flight);
      }
    }
    return flightsFromOrigin;
  }

}
