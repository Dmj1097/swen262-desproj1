package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Journey;
import afrs.appmodel.Time;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * FlightStorage
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class FlightStorage {


  private Map<Integer, Flight> flights;

  /**
   * Create a new FlightStorage object
   */
  public FlightStorage(){
    flights = new HashMap<>();
    setupFlightMap();

  }

  private void setupFlightMap(){
    try {
      File file = new File("resources/flights.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        Time arrive = new Time(line_info[3]);
        Time depart = new Time(line_info[2]);
        flights.put(Integer.parseInt(line_info[4]), new Flight(Integer.parseInt(line_info[4]),line_info[0],line_info[1],Integer.parseInt(line_info[5]),depart,arrive));
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read flights.txt");
      e.printStackTrace();
    }
  }

  public Journey getFlight(int ID){
    return flights.get(ID);
  }

  public ArrayList<Flight> getFlightsFromOrigin(String ID){
    ArrayList<Flight> flightsFromOrigin = new ArrayList<>();
    for(Flight flight: flights.values()){
      if(flight.getOrigin().equals(ID)){
        flightsFromOrigin.add(flight);
      }
    }
    return flightsFromOrigin;
  }

}
