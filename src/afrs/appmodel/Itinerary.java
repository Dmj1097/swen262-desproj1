package afrs.appmodel;

import java.util.ArrayList;

/**
 * Itinerary
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Itinerary implements Journey{




  private ArrayList<Flight> flights;
  private String origin;
  private String destination;
  private int cost;
  private int connections;
  /**
   * Create a new Itinerary object
   */
  public Itinerary(String origin, String destination, int connections){
    this.origin = origin;
    this.destination = destination;
    this.connections = connections;
  }


  public void addFlight(Flight flight){
    flights.add(flight);
  }

  public void removeFlight(Flight flight){
    flights.remove(flight);
  }

  private void adjustCost(){
    int curr_cost = 0;
    for(Flight flight: flights){
      curr_cost += flight.getCost();
    }
    cost = curr_cost;
  }
  @Override
  public String toString(){
    String all = "";
    all.concat(cost + ","+connections);
    for(Flight flight: flights){
      all.concat(flight.toString());
    }
    return all;
  }

}
