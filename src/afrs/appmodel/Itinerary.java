package afrs.appmodel;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;

/**
 * Itinerary
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Itinerary implements Journey{

  
  private ArrayList<Journey> flights = new ArrayList<Journey>();
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


  /*
   * Adds a flight and adds to the connection count
   * Adjusts cost
   */
  public void addFlight(Journey flight){
    flights.add(flight);
    adjustCost();
  }

  /*
   * Removes a flight and adds to the connection count.
   * Adjusts cost
   */
  public void removeFlight(Journey flight){
    flights.remove(flight);
    adjustCost();
  }

  private void adjustCost(){
    int curr_cost = 0;
    for(Journey flight: flights){
      curr_cost += flight.getCost();
    }
    cost = curr_cost;
  }
  
  @Override
  public String toString(){
    String all = "";
    all.concat(cost + ", " + connections);
    for(Journey flight: flights){
      all.concat(flight.toString());
    }
    return all;
  }

  /*
   * Updates origin and returns it
   */
  @Override
  public String getOrigin() {
    return origin;
  }

  /*
   * Updates destination and returns it
   */
  @Override
  public String getDestination() {
    return destination;
  }

  /*
   * Returns the cost of the entire journey
   */
  @Override
  public int getCost() {
    return cost;
  }
}
